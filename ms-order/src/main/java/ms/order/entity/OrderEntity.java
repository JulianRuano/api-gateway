package ms.order.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNo;
    private Date orderDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    // Get the total quantity of the order
    public Integer getTotal() {     
        return orderItems.stream().mapToInt(item -> item.getQuantity()).sum();
    }

    // Get the total amount of the order
    public Double getTotalAmount() {
        return orderItems.stream().mapToDouble(item -> item.getAmount()).sum();
    }
}
