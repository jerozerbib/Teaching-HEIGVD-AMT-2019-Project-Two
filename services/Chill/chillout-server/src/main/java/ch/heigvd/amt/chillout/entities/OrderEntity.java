package ch.heigvd.amt.chillout.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class OrderEntity implements Serializable {

    public OrderEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ClientEntity clientEntity;

    @OneToMany
    private List<OrderItemEntity> orderItems;
}
