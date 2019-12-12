package ch.heigvd.amt.chillout.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@Setter
@Entity
public class ProductEntity implements Serializable {

    public ProductEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal unitPrice;
    private String description;
}