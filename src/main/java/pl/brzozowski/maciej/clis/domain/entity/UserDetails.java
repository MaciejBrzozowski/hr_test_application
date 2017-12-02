package pl.brzozowski.maciej.clis.domain.entity;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String avatar;
    @OneToOne(targetEntity = User.class, cascade = ALL, orphanRemoval = true)
    private User user;

}
