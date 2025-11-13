package org.dd.bre.Service;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.*;
import org.dd.bre.Exception.InvalidOrderException;
import org.dd.bre.Exception.InvalidPaymentMethodException;
import org.dd.bre.Exception.UserNotFoundException;
import org.dd.bre.Mapper.OrderHistoryMapper;
import org.dd.bre.Repo.OrderRepo;
import org.dd.bre.Repo.UserRepo;
import org.dd.bre.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderHistoryMapper orderHistoryMapper;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final OrderItemService orderItemService;
    private final VariantService variantService;
    private final ShippingAddressService shippingAddressService;
    private final CartService cartService;
    private final EmailService emailService;
    private final int scale = 2;
    private final RoundingMode roundingMode = RoundingMode.HALF_UP;
    @Value("${app.order.free-shipping}")
    private BigDecimal FREE_SHIPPING;
    @Value("${app.order.tax-rate}")
    private BigDecimal TAX_RATE;
    @Value("${app.order.shipping-rate}")
    private BigDecimal SHIPPING_RATE;

    public OrderSummary calculateOrderSummary(List<PurchaseItem> items) {

        BigDecimal subtotal = calculateSubTotal(items);

        BigDecimal tax = calculateTax(subtotal);

        BigDecimal shipping = calculateShipping(subtotal);

        BigDecimal total = calculateTotalPrice(subtotal, tax, shipping);

        return new OrderSummary(
                subtotal,
                tax,
                shipping,
                total
        );
    }

    public OrderPageResponse getOrdersHistory(UserDetails userDetails, Pageable pageable) {
        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Page<Long> pageIds = orderRepo.findPageOfIds(user.getId(), pageable);

        List<Order> orders = Collections.emptyList();
        if (!pageIds.isEmpty()) {
            orders = orderRepo.findAllByIdIn(pageIds.getContent());
        }

        List<OrderHistoryDto> dtos = orders.stream()
                .map(orderHistoryMapper::mapToOrderHistoryDTO)
                .toList();

        return new OrderPageResponse(
                dtos,
                pageIds.getNumber(),
                pageIds.getSize(),
                pageIds.getTotalElements(),
                pageIds.getTotalPages(),
                pageIds.hasNext()
        );


    }

    @Transactional
    public OrderHistoryDto createOrder(UserDetails userDetails, PurchaseReq purchaseReq) throws MessagingException{

        User user = userRepo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (purchaseReq.getItems() == null || purchaseReq.getItems().isEmpty()) {
            throw new InvalidOrderException("Cannot create order with no items");
        }

        if (purchaseReq.getPaymentMethod() == null)
            throw new IllegalArgumentException("Payment method is required.");

        String payMethod = purchaseReq.getPaymentMethod();

        String method = purchaseReq.getPaymentMethod();
        PaymentMethod paymentMethod = PaymentMethod.fromString(method);
        if (paymentMethod == PaymentMethod.NOT_EXIST) {
            throw new InvalidPaymentMethodException("Unsupported payment method: " + payMethod);
        }

        BigDecimal subTotal = calculateSubTotal(purchaseReq.getItems());

        BigDecimal taxAmount = calculateTax(subTotal);

        BigDecimal shipping = calculateShipping(subTotal);

        BigDecimal totalPrice = calculateTotalPrice(subTotal, taxAmount, shipping);

        ShippingAddress shippingAddress = shippingAddressService.getShippingAddress(purchaseReq);

        Order order = new Order();
        order.setUser(user);
        order.setOrder_number(UUID.randomUUID().toString());
        order.setOrder_status(OrderStatus.PENDING);
        order.setPaymentMethod(paymentMethod);
        order.setShippingPrice(SHIPPING_RATE);
        order.setTaxAmount(taxAmount);
        order.setTotalPrice(totalPrice);
        order.setShippingAddress(shippingAddress);

        Set<OrderItem> orderItems = purchaseReq.getItems().stream()
                .map(item -> orderItemService.createOrderItem(order, item))
                .collect(Collectors.toSet());

        order.setItems(orderItems);

        variantService.decrementQuantityOfProductVariant(purchaseReq.getItems());

        orderRepo.save(order);

        cartService.clearCart(user.getId());
        try {
            emailService.sendEmail(user.getEmail(),getEmailSubjectForOrder(),getEmailMessageForOrder());
        }
        catch (MessagingException e) {
            throw new MessagingException("Unable to send email to " + user.getEmail(), e);
        }


        return orderHistoryMapper.mapToOrderHistoryDTO(order);
    }

    private String getEmailMessageForOrder() {
        return """
    <div style="font-family: Arial, sans-serif; text-align: center; padding: 20px;">
        <h2 style="color: #28a745;">✅ Order Successful!</h2>
        <p>Thank you for your purchase. Your order has been successfully placed and is now being processed.</p>
        <p><strong>Order ID:</strong> #123456</p>
        <p>We’ll send you an update when your order ships.</p>
        <a href="/orders" style="display: inline-block; margin-top: 15px; 
            padding: 10px 20px; background-color: #28a745; color: white; 
            text-decoration: none; border-radius: 5px;">
            View My Orders
        </a>
    </div>
""";
    }

    private String getEmailSubjectForOrder() {
        return "Order Confirmation - Thank You for Your Purchase!";
    }

    private BigDecimal calculateTotalPrice(BigDecimal subTotal,BigDecimal taxAmount,BigDecimal shipping) {
        return subTotal.add(taxAmount).add(shipping).setScale(scale,roundingMode);
    }

    private BigDecimal calculateTax(BigDecimal subTotal) {
        return subTotal.multiply(TAX_RATE).setScale(scale, roundingMode);
    }

    private BigDecimal calculateShipping(BigDecimal subTotal) {
        return subTotal.compareTo(FREE_SHIPPING) >= 0
                ? BigDecimal.ZERO
                : SHIPPING_RATE.setScale(scale, roundingMode);
    }
    private BigDecimal calculateSubTotal(List<PurchaseItem> items) {
        return orderItemService.getSubTotal(items).setScale(scale, roundingMode);
    }
}
