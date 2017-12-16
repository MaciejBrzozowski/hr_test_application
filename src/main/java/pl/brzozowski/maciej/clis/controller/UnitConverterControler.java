package pl.brzozowski.maciej.clis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brzozowski.maciej.clis.services.UnitConverter;
import pl.brzozowski.maciej.clis.utilities.FindIContent;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/convert/{unitIn}/to/{unitOut}")
public class UnitConverterControler {

    @Autowired
    private UnitConverter unitConverter;
    @Autowired
    private FindIContent findIContent;
    private String response;
    private String errorResponse = "Given units can not be converted";
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping
    public String returnConversionRateUnits(@PathVariable("unitIn") String unitIn,
                                            @PathVariable("unitOut") String unitOut) {
        String response = unitIn + " " + unitOut;
        return response;
    }

    @GetMapping("/quantity/{quantity}")
    public String convertUnits(@PathVariable("unitIn") String unitIn,
                               @PathVariable("unitOut") String unitOut,
                               @PathVariable("quantity") double quantity) {

        response = unitConverter.getConvertedUnit(quantity, unitIn, unitOut);
        response = response.replace("<b>", "").replace("</b>", "");
        logger.info(response);
        response = findIContent.findStringByRegex(response, quantity, unitIn, unitOut);
        logger.info(response);

        return response.isEmpty() ? errorResponse : (quantity + " " + response);
    }

}
