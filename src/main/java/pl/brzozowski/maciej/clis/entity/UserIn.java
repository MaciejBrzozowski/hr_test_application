package pl.brzozowski.maciej.clis.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserIn {
    private String email;
    private String password;
}
