package ch.heigvd.amt.chillout.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Entity
public class ClientEntity implements Serializable {

    /**
     * Creates an empty ClientEntity
     */
    public ClientEntity(){}

    @Id
    private String email;
}
