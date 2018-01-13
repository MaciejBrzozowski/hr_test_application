package pl.brzozowski.maciej.clis.controller.authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.brzozowski.maciej.clis.entity.UnitsIn;
import pl.brzozowski.maciej.clis.services.UnitConverter;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;
import static pl.brzozowski.maciej.clis.configuration.UrlMaping.*;

@RestController
@RequestMapping(value = BASE_UNIT_CONVERSION_URL)
public class UnitConverterControler {

    @Autowired
    private UnitConverter unitConverter;
    private String response;
    private String errorResponse = "Given units can not be converted";
    private Logger logger = getLogger(this.getClass().getName());

    @GetMapping(UNIT_URL)
    public String returnConversionRateUnits(@PathVariable("unitIn") String unitIn,
                                            @PathVariable("unitOut") String unitOut) {
        response = unitConverter.getConvertedUnit(BigDecimal.valueOf(1), unitIn, unitOut);
        return response.isEmpty() ? errorResponse : response;
    }

    @GetMapping(UNIT_URL + UNIT_CONVERSION_QUANTITY)
    public String convertUnits(@PathVariable("unitIn") String unitIn,
                               @PathVariable("unitOut") String unitOut,
                               @PathVariable("quantity") BigDecimal quantity) {
        logger.info("unitIn: " + unitIn + "| unitOut: " + unitOut + "| quantity: " + quantity);
        response = unitConverter.getConvertedUnit(quantity, unitIn, unitOut);
        logger.info(response);
        return response.isEmpty() ? errorResponse : response;
    }

    @PostMapping
    public String convertUnitsPostMethod(@RequestBody UnitsIn unitsIn) {
        logger.info(unitsIn.toString());
        response = unitConverter.getConvertedUnit(unitsIn.getQuantity(), unitsIn.getUnitIn(), unitsIn.getUnitOut());
        logger.info(response);
        return response.isEmpty() ? errorResponse : response;
    }

}
