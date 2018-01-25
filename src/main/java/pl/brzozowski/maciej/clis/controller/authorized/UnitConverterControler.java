package pl.brzozowski.maciej.clis.controller.authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.brzozowski.maciej.clis.entity.UnitsIn;
import pl.brzozowski.maciej.clis.entity.UnitsOut;
import pl.brzozowski.maciej.clis.services.UnitConverterService;
import pl.brzozowski.maciej.clis.utilities.UnitConversionObject;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;
import static pl.brzozowski.maciej.clis.configuration.UrlMaping.*;

@RestController
@RequestMapping(value = BASE_UNIT_CONVERSION_URL)
public class UnitConverterControler {

    @Autowired
    private UnitConverterService unitConverterService;
    private String response;
    private UnitConversionObject unitConversionObject;
    private String errorResponse = "Given units can not be converted";
    private Logger logger = getLogger(this.getClass().getName());

    @GetMapping(UNIT_URL)
    public String returnConversionRateUnits(@PathVariable("unitIn") String unitIn,
                                            @PathVariable("unitOut") String unitOut) {
        response = unitConverterService.getConvertedUnit(BigDecimal.valueOf(1), unitIn, unitOut);
        return response.isEmpty() ? errorResponse : response;
    }

    @GetMapping(UNIT_URL + UNIT_CONVERSION_QUANTITY)
    public String convertUnits(@PathVariable("unitIn") String unitIn,
                               @PathVariable("unitOut") String unitOut,
                               @PathVariable("quantity") BigDecimal quantity) {
        logger.info("unitIn: " + unitIn + "| unitOut: " + unitOut + "| quantity: " + quantity);
        response = unitConverterService.getConvertedUnit(quantity, unitIn, unitOut);
        logger.info(response);
        return response.isEmpty() ? errorResponse : response;
    }

    @PostMapping
    public UnitsOut convertUnitsPostMethod(@RequestBody UnitsIn unitsIn) {
        logger.info(unitsIn.toString());
        unitConversionObject = unitConverterService.getConvertedUnitAsObject(unitsIn.getQuantity(), unitsIn.getUnitIn(), unitsIn.getUnitOut());
        logger.info(unitConversionObject.getResult().toString());
        return new UnitsOut(unitConversionObject);
    }

}
