package fupp.mvp.base.util;

import java.util.regex.Pattern;

/**
 * 数字工具类
 */

public class DigitalUtil {

    static Pattern p = Pattern.compile("[0-9]*");

    /**
     * 判断是否是数字
     *
     * @param strDigital
     * @return
     */
    public static boolean isDigital(String strDigital) {
        return p.matcher(strDigital).matches();
    }

    /**
     * 获取第一个数字
     * @param strDigital
     * @return
     */
    public static char firstDigital(String strDigital) {
        char c = strDigital.charAt(0);
        return c;
    }
}