package org.dd.bre.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String order_number;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "shipping_price", precision = 10, scale = 2)
    private BigDecimal shippingPrice;

    @Column(name = "tax_amount", precision = 10, scale = 2)
    private BigDecimal taxAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod = PaymentMethod.CASH_ON_DELIVERY;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false,nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus order_status = OrderStatus.PENDING;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    private Payment payment;
}
