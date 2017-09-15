package br.com.aramosdev.zapimoveis.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class PriceUtils {

    public static String getPriceAsString(int price) {
        Locale locale = new Locale("pt", "BR");
        String currency = NumberFormat.getCurrencyInstance(locale).getCurrency().getSymbol(locale).trim();
        String number = NumberFormat.getCurrencyInstance(locale).format(price).replace(currency, "").trim();
        return String.format("%s %s", currency, number);
    }
}
