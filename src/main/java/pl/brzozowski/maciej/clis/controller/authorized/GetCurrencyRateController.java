package pl.brzozowski.maciej.clis.controller.authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.brzozowski.maciej.clis.services.Currency;
import pl.brzozowski.maciej.clis.services.CurrencyService;

@RestController
@RequestMapping(value = "/auth")
public class GetCurrencyRateController {

    @Autowired
    private CurrencyService currencyService;
    private final String CURRENCY_URL = "/currencyIn/{currencyIn}/currencyOut/{currencyOut}";
    private final String BASE_URL_AMOUNT = "/amount/{amount:.+}";


    @GetMapping(CURRENCY_URL)
    public String getCurrencyRate(@PathVariable("currencyIn") Currency currencyIn, @PathVariable("currencyOut") Currency currencyOut) {
        return String.valueOf(currencyService.getCurrencyRate(currencyIn, currencyOut));
    }

    @GetMapping(CURRENCY_URL + BASE_URL_AMOUNT)
    public String calculateExchange(@PathVariable("currencyIn") Currency currencyIn,
                                    @PathVariable("currencyOut") Currency currencyOut,
                                    @PathVariable("amount") double amount) {
        return String.valueOf(amount * currencyService.getCurrencyRate(currencyIn, currencyOut));
    }

    @GetMapping("/currency/help")
    public Currency[] getAvailableCurrency() {
        return Currency.values();
    }


}
