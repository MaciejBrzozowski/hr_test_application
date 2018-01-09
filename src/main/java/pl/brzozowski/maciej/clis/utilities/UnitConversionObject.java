package pl.brzozowski.maciej.clis.utilities;

import lombok.ToString;

import java.math.BigDecimal;

import static java.lang.Double.valueOf;
import static pl.brzozowski.maciej.clis.utilities.StringCalculations.divide;


@ToString
public class UnitConversionObject {
    private String conversionType;
    private String unitInName;
    private String unitInSymbol;
    private String unitOutName;
    private String unitOutSymbol;
    private double factor;
    private double delta;
    private BigDecimal quantity;
    private BigDecimal result;

    public UnitConversionObject() {
    }

    public UnitConversionObject(String conversionType, String unitInName, String unitInSymbol, String unitOutName, String unitOutSymbol, double factor, double delta) {
        this.conversionType = conversionType;
        this.unitInName = unitInName;
        this.unitInSymbol = unitInSymbol;
        this.unitOutName = unitOutName;
        this.unitOutSymbol = unitOutSymbol;
        this.factor = factor;
        this.delta = delta;
    }

    public UnitConversionObject createObject(String conversionData) {
        String data[] = conversionData.split(",");
        conversionType = data[0];
        unitInName = data[1];
        unitInSymbol = data[2];
        unitOutName = data[3];
        unitOutSymbol = data[4];
        factor = valueOf(divide(data[5]));
        delta = valueOf(data[6]);
        return this;
    }

    public BigDecimal convertToDouble(BigDecimal quantity) {
        this.quantity = quantity;
        return this.result = quantity.multiply(BigDecimal.valueOf(this.factor))
                                     .add(BigDecimal.valueOf(this.delta));
    }

    public String convertToString(BigDecimal quantity) {
        return String.valueOf(quantity);
    }
}
