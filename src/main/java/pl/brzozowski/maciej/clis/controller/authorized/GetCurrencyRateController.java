package pl.brzozowski.maciej.clis.controller.authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brzozowski.maciej.clis.services.Currency;
import pl.brzozowski.maciej.clis.services.CurrencyService;

import static java.lang.String.valueOf;
import static pl.brzozowski.maciej.clis.configuration.UrlMaping.*;

@RestController
@RequestMapping(value = AUTH)
public class GetCurrencyRateController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping(CURRENCY_URL)
    public String getCurrencyRate(@PathVariable("currencyIn") Currency currencyIn, @PathVariable("currencyOut") Currency currencyOut) {
        String result = valueOf(currencyService.getCurrencyRate(currencyIn, currencyOut));
        return result;
    }

    @GetMapping(CURRENCY_URL + BASE_URL_AMOUNT)
    public String calculateExchange(@PathVariable("currencyIn") Currency currencyIn,
                                    @PathVariable("currencyOut") Currency currencyOut,
                                    @PathVariable("amount") double amount) {
        String result = valueOf(amount * currencyService.getCurrencyRate(currencyIn, currencyOut));
        return result;
    }

    @GetMapping(HELP)
    public Currency[] getAvailableCurrency() {
        return Currency.values();
    }

}
