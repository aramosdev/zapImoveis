package br.com.aramosdev.zapimoveis.util;

import java.util.List;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class TextUtils {

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty() || text.trim().length() == 0;
    }

    public static boolean isEmptyOrNull(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isEmptyOrNull(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
