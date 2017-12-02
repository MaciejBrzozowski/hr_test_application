package pl.brzozowski.maciej.clis.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
    private String token;
    @OneToOne(targetEntity = UserDetails.class)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;

}
