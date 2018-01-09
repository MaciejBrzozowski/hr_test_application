package pl.brzozowski.maciej.clis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnitsIn {

    private String unitIn;
    private String unitOut;
    private double quantity;

    @Override
    public String toString() {
        return "{" +
                "unitIn='" + unitIn + '\'' +
                ", unitOut='" + unitOut + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
