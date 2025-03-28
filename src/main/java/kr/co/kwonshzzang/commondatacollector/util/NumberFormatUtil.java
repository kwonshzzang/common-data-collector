package kr.co.kwonshzzang.commondatacollector.util;

public class NumberFormatUtil {
    public static boolean isNumberic(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
