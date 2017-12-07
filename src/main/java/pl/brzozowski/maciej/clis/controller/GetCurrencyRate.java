package pl.brzozowski.maciej.clis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.brzozowski.maciej.clis.services.Curency;
import pl.brzozowski.maciej.clis.services.RequestOut;

@RestController
public class GetCurrencyRate {

    RequestOut reqestOut = new RequestOut();


    @GetMapping("/currencyIn/{currencyIn}/currencyOut/{currencyOut}")
    public String getCurrencyRate(@PathVariable("currencyIn") Curency curencyIn, @PathVariable("currencyOut") Curency curencyOut) {

        return String.valueOf(reqestOut.getCurrencyRate(curencyIn, curencyOut));
    }
}
