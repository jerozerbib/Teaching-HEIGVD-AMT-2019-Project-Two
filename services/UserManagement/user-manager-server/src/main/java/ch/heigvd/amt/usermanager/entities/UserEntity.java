package ch.heigvd.amt.usermanager.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@EqualsAndHashCode
@Entity
public class UserEntity implements Serializable {

    /**
     * Default constructor
     */
    public UserEntity() {}

    @Id
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private int isAdmin;
    private int isBlocked;
}
