package ch.heigvd.amt.chillout.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class OrderItemEntity implements Serializable {

    /**
     * Creates an OrderItemEntity
     */
    public OrderItemEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;

    @OneToOne
    private ProductEntity productEntity;
}

