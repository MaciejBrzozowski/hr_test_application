package pl.brzozowski.maciej.clis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.brzozowski.maciej.clis.services.UnitConverter;

@RestController
@RequestMapping(value = "/convert/{unitIn}/to/{unitOut}")
public class UnitConverterControler {

    @Autowired
    private UnitConverter unitConverter;

    @GetMapping
    public String returnConversionRateUnits(@PathVariable("unitIn") String unitIn,
                                            @PathVariable("unitOut") String unitOut) {
        String response = unitIn + " " + unitOut;


        return response;
    }

    @GetMapping("/quantity/{quantity}")
    public String convertUnits(@PathVariable("unitIn") String unitIn,
                               @PathVariable("UnitOut") String unitOut,
                               @PathVariable("quantity") Long quantity) {
        String response = String.valueOf(quantity);


        return response;
    }

}
