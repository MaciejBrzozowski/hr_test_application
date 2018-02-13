package pl.brzozowski.maciej.clis.entity;

import lombok.Getter;
import lombok.ToString;
import pl.brzozowski.maciej.clis.utilities.UnitConversionObject;

import java.math.BigDecimal;

@ToString
@Getter
public class UnitsOut {

    private String unitInName;
    private String unitOutName;
    private BigDecimal quantityIn;
    private BigDecimal quantityOut;

    public UnitsOut(UnitConversionObject uco) {
        this.unitInName = uco.getUnitInName();
        this.unitOutName = uco.getUnitOutName();
        this.quantityIn = uco.getQuantity();
        this.quantityOut = uco.getResult();
    }
}
