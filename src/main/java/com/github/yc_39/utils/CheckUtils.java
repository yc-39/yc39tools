package com.github.yc_39.utils;

public class CheckUtils {

    /**
     * Don't let anyone instantiate this class.
     */
    private CheckUtils() {}


    public static boolean isEmpty(String str) {
        if (str==null) return true;
        str = str.trim();
        if (str.length()==0) return true;
        return false;
    }


    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * isInteger
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if (isEmpty(str)) return false;
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {}
        return false;
    }

    /**
     * isDouble
     * @param str
     * @return
     */
    public  static boolean isDouble(String str) {
        if (isEmpty(str)) return false;
        try {
            Double.valueOf(str);
            return true;
        } catch (Exception e) {}
        return false;
    }

    // 数字表达式
    private static final String numericRegex = "^[0-9]*$";
    /**
     * 数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) return false;
        return str.matches(numericRegex) ;
    }


    // 正整数表达式
    private static final String positiveIntegerRegex = "^[1-9][0-9]*$";
    /**
     * 正整数
     * @param str
     * @return
     */
    public static boolean isPositiveInteger(String str) {
        if (isEmpty(str)) return false;
        return str.matches(positiveIntegerRegex);
    }


    private static final String letterRegex = "^[A-Za-z]+$";
    /**
     * 大小写字母
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {
        if (isEmpty(str)) return false;
        return str.matches(letterRegex);
    }

    private static final String upperLetterRegex = "^[A-Z]+$";
    /**
     * 大写字母
     * @param str
     * @return
     */
    public static boolean isUpperLetter(String str) {
        if (isEmpty(str)) return false;
        return str.matches(upperLetterRegex);
    }

    private static final String lowerLetterRegex = "^[a-z]+$";
    /**
     * 小写字母
     * @param str
     * @return
     */
    public static boolean isLowerLetter(String str) {
        if (isEmpty(str)) return false;
        return str.matches(lowerLetterRegex);
    }



    // 日期表达式
    private static final String dateYMDRegex = "^\\\\d{4}[0-1][0-9][0-3][0-9]$";
    /**
     * 日期
     * 格式：yyyyMMdd
     * @param str
     * @return
     */
    public static boolean isDateYMD(String str) {
        if (isEmpty(str)) return false;
        return str.matches(dateYMDRegex);
    }

    private static final String dateYMDRegex_ = "^\\\\d{4}(-|.|/)\\\\d{2}(-|.|/)\\\\d{2}$";
    /**
     * 日期
     * 格式：yyyy-MM-dd 或 yyyy/MM/dd 或 yyyy.MM.dd
     * @param str
     * @return
     */
    public static boolean isDateYMD_(String str) {
        if (isEmpty(str)) return false;
        return str.matches(dateYMDRegex_);
    }

    private static final String dateYMDRegex_45 = "^\\d{4}(-)\\d{2}(-)\\d{2}$";
    /**
     * 日期
     * 格式：yyyy-MM-dd
     * @param str
     * @return
     */
    public static boolean isDateYMD_45(String str) {
        if (isEmpty(str)) return false;
        return str.matches(dateYMDRegex_45);
    }

    private static final String dateYMDRegex_46 = "^\\d{4}(.)\\d{2}(.)\\d{2}$";
    /**
     * 日期
     * 格式：yyyy.MM.dd
     * @param str
     * @return
     */
    public static boolean isDateYMD_46(String str) {
        if (isEmpty(str)) return false;
        return str.matches(dateYMDRegex_46);
    }

    private static final String dateYMD_47 = "^\\d{4}(/)\\d{2}(/)\\d{2}$";
    /**
     * 日期
     * 格式：yyyy/MM/dd
     * @param str
     * @return
     */
    public static boolean isDateYMD_47(String str) {
        if (isEmpty(str)) return false;
        return str.matches(dateYMD_47);
    }


    private static final String dateYM = "^\\d{4}[0-1][0-9]$";
    /**
     * 日期
     * 格式：yyyyMM
     * @param str
     * @return
     */
    public static boolean isDateYM(String str) {
        if (isEmpty(str)) return false;
        return str.matches(dateYM);
    }

    private static final String dateYM_ = "^\\d{4}(-|.|/)[0-1][0-9]$";
    /**
     * 日期
     * 格式：yyyy-MM 或 yyyy/MM 或 yyyy.MM
     * @param str
     * @return
     */
    public static boolean isDateYM_(String str) {
        if (isEmpty(str)) return false;
        return str.matches(dateYM_);
    }

    private static final String isDateYM_45 = "^\\d{4}(-)[0-1][0-9]$";
    /**
     * 日期
     * 格式：yyyy-MM
     * @param str
     * @return
     */
    public static boolean isDateYM_45(String str) {
        if (isEmpty(str)) return false;
        return str.matches(isDateYM_45);
    }

    private static final String isDateYM_46 = "^\\d{4}(.)[0-1][0-9]$";
    /**
     * 日期
     * 格式：yyyy.MM
     * @param str
     * @return
     */
    public static boolean isDateYM_46(String str) {
        if (isEmpty(str)) return false;
        return str.matches(isDateYM_46);
    }

    private static final String isDateYM_47 = "^\\d{4}(/)[0-1][0-9]$";
    /**
     * 日期
     * 格式：yyyy/MM
     * @param str
     * @return
     */
    public static boolean isDateYM_47(String str) {
        if (isEmpty(str)) return false;
        return str.matches(isDateYM_47);
    }



    // 邮箱表达式
//	private static final String mailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    private static final String mailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    /**
     * 邮箱
     * @param str
     * @return
     */
    public static boolean isMail(String str) {
        if (isEmpty(str)) return false;
        return str.matches(mailRegex);
    }


    // ip地址表达式
    private static final String ipAddressRegex = "[1-9](\\\\d{1,2})?\\\\.(0|([1-9](\\\\d{1,2})?))\\\\.(0|([1-9](\\\\d{1,2})?))\\\\.(0|([1-9](\\\\d{1,2})?))";
    /**
     * ip地址
     * @param str
     * @return
     */
    public static boolean isIpAddress(String str) {
        if (isEmpty(str)) return false;
        return str.matches(ipAddressRegex);
    }



    private static final String actionPhoneRegex = "^(13|15|18)\\d{9}$";
    /**
     * 手机号码
     * @param str
     * @return
     */
    public static boolean isActionPhone(String str) {
        if (isEmpty(str)) return false;
        return str.matches(actionPhoneRegex);
    }

    private static final String bankNoRegex = "^[0-9]{16,19}$";
    /**
     * 银行卡号
     * @param str
     * @return
     */
    public static boolean isBankNo(String str) {
        if (isEmpty(str)) return false;
        return str.matches(bankNoRegex);
    }

    private static final String chineseRegex = "^[\\u4e00-\\u9fa5],{0,}$";
    /**
     * 中文汉字
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        if (isEmpty(str)) return false;
        return str.matches(chineseRegex);
    }


    public static void main(String[] args) {
        String str = "19920531";
        boolean flag = CheckUtils.isNumeric(str);
        System.out.println(flag);
    }
}
