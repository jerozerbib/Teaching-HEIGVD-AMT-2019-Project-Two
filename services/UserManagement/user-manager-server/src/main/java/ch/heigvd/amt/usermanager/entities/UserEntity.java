package ch.heigvd.amt.usermanager.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    public UserEntity(String email, String password) { this.email = email; this.password = password;}

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String salt;
    private boolean isAdmin;
    private boolean isBlocked;
}
