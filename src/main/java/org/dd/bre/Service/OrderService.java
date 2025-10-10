package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.OrderHistoryDto;
import org.dd.bre.Dto.OrderPageResponse;
import org.dd.bre.Dto.ProductPageResponse;
import org.dd.bre.Exception.UserNotFoundException;
import org.dd.bre.Mapper.OrderHistoryMapper;
import org.dd.bre.Repo.OrderRepo;
import org.dd.bre.Repo.UserRepo;
import org.dd.bre.model.Order;
import org.dd.bre.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderHistoryMapper orderHistoryMapper;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;

    public OrderPageResponse getOrdersHistory(Long userId, Pageable pageable) {
        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        Page<Order> pageResult = orderRepo.findAllByUserId(user.getId(),pageable);

        List<OrderHistoryDto> dtos =  new ArrayList<>();
        if(!pageResult.isEmpty()){
            dtos = pageResult.stream().map(orderHistoryMapper::mapToOrderHistoryDTO).toList();
        }
        return new OrderPageResponse(
                dtos,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.hasNext()
        );

    }
}
