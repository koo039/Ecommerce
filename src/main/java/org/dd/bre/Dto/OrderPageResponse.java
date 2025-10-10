package org.dd.bre.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderPageResponse {
        private List<OrderHistoryDto> data;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean hasNext;

}
