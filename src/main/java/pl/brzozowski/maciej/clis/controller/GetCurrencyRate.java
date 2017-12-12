package pl.brzozowski.maciej.clis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.brzozowski.maciej.clis.services.Curency;
import pl.brzozowski.maciej.clis.services.RequestOut;

@RestController
public class GetCurrencyRate {

    private RequestOut requestOut = new RequestOut();
    private final String BASE_URL = "/currencyIn/{currencyIn}/currencyOut/{currencyOut}";
    private final String BASE_URL_AMOUNT = "/amount/{amount}";



    @GetMapping(BASE_URL)
    public String getCurrencyRate(@PathVariable("currencyIn") Curency curencyIn, @PathVariable("currencyOut") Curency curencyOut) {

        return String.valueOf(requestOut.getCurrencyRate(curencyIn, curencyOut));
    }

 @GetMapping(BASE_URL+BASE_URL_AMOUNT)
    public String calculateExchange (@PathVariable("currencyIn") Curency curencyIn,
                                     @PathVariable("currencyOut") Curency curencyOut,
                                     @PathVariable("amount") double amount) {


        return String.valueOf(amount * requestOut.getCurrencyRate(curencyIn, curencyOut));
    }
}
