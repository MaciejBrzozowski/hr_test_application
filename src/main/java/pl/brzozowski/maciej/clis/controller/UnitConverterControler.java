package pl.brzozowski.maciej.clis.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brzozowski.maciej.clis.services.UnitConverter;
import pl.brzozowski.maciej.clis.utilities.UnitConversionObject;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/convert/{unitIn}/to/{unitOut}")
public class UnitConverterControler {

    @Autowired
    private UnitConverter unitConverter;
    private UnitConversionObject response;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private Gson gson = new Gson();

    @GetMapping
    public String returnConversionRateUnits(@PathVariable("unitIn") String unitIn,
                                            @PathVariable("unitOut") String unitOut) {
        String response = unitIn + " " + unitOut;
        return response;
    }

    @GetMapping("/quantity/{quantity:.+}")
    public ResponseEntity convertUnits(@PathVariable("unitIn") String unitIn,
                                       @PathVariable("unitOut") String unitOut,
                                       @PathVariable("quantity") BigDecimal quantity) {

        logger.info("unitIn: " + unitIn + "| unitOut: " + unitOut + "| quantity: " + quantity);
        response = unitConverter.getConvertedUnitAsObject(quantity, unitIn, unitOut);
        logger.info(response.toString());
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(gson.toJson(response), httpHeaders, OK);

    }

}
