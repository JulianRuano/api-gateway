package ms.order.broker;

import ms.order.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent{
    private ProductDto productDto;
    private ProductEventType eventType;
}
