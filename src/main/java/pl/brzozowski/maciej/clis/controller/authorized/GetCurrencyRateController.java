package pl.brzozowski.maciej.clis.controller.authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brzozowski.maciej.clis.services.Currency;
import pl.brzozowski.maciej.clis.services.RequestOut;

@RestController
@RequestMapping(value = "/auth/currencyIn/{currencyIn}/currencyOut/{currencyOut}")
public class GetCurrencyRateController {

    @Autowired
    private RequestOut requestOut;
    private final String BASE_URL_AMOUNT = "/amount/{amount:.+}";


    @GetMapping
    public String getCurrencyRate(@PathVariable("currencyIn") Currency currencyIn, @PathVariable("currencyOut") Currency currencyOut) {

        return String.valueOf(requestOut.getCurrencyRate(currencyIn, currencyOut));
    }

    @GetMapping(BASE_URL_AMOUNT)
    public String calculateExchange(@PathVariable("currencyIn") Currency currencyIn,
                                    @PathVariable("currencyOut") Currency currencyOut,
                                    @PathVariable("amount") double amount) {


        return String.valueOf(amount * requestOut.getCurrencyRate(currencyIn, currencyOut));
    }


}