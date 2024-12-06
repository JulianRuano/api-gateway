package ms.order.entity;


import java.util.UUID;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {
    
    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
    private Double price;

    @OneToMany(mappedBy = "product")
    private OrderItemEntity orderItem;

    public void updatePrice(Double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("Price must be greater than 0.0");
        }
        this.price = price;
    }

}
