package com.github.yc_39.utils;

/**
 * 获取大写金额
 */
public class MoneyUtils {

	/** 大写数字 */
	private static final String[] NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	/** 整数部分的单位 */
	private static final String[] IUNIT = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟" };
	/** 小数部分的单位 */
	private static final String[] DUNIT = { "角", "分", "厘" };

	/**
	 * 得到大写金额
	 */
	public static String toChinese(String str) {
		str = str.replaceAll(",", ""); // 去掉","
		String integerStr; // 整数部分数字
		String decimalStr; // 小数部分数字

		// 初始化,分离整数部分和小数部分
		if (str.indexOf(".") > 0) {
			integerStr = str.substring(0, str.indexOf("."));
			decimalStr = str.substring(str.indexOf(".") + 1);
		} else if (str.indexOf(".") == 0) {
			integerStr = "";
			decimalStr = str.substring(1);
		} else {
			integerStr = str;
			decimalStr = "";
		}
		// integerStr去掉首0,不必去掉decimalStr的尾0(超出部分舍去)
		if (!integerStr.equals("")) {
			integerStr = Long.toString(Long.parseLong(integerStr));
			if (integerStr.equals("0")) {
				integerStr = "";
			}
		}
		// overflow超出处理能力
		if (integerStr.length() > IUNIT.length) {
			throw new IllegalArgumentException("超出处理范围");
		}

		int[] integers = toArray(integerStr);// 整数部分数字
		boolean isMust5 = isMust5(integerStr);// 设置万单位
		int[] decimals = toArray(decimalStr);// 小数部分数字
		return getChineseInteger(integers, isMust5) + getChineseDecimal(decimals);
	}

	/**
	 * 整数部分和小数部分转换为数组，从高位至低位
	 */
	private static int[] toArray(String number) {
		int[] array = new int[number.length()];
		for (int i = 0, len = number.length(); i < len; i++) {
			array[i] = Integer.parseInt(number.substring(i, i + 1));
		}
		return array;
	}

	/**
	 * 得到中文金额的整数部分。
	 */
	private static String getChineseInteger(int[] integers, boolean isMust5) {
		StringBuilder chineseInteger = new StringBuilder();
		for (int i = 0, len = integers.length; i < len; i++) {
			// 0出现在关键位置: 1234(万)5678(亿)9012(万)3456(元)
			// 特殊情况: 10(拾元、壹拾元、壹拾万元、拾万元)
			String key = "";
			if (integers[i] == 0) {
				if ((len - i) == 13) // 万(亿)(必填)
				key = IUNIT[4];
				else if ((len - i) == 9) // 亿(必填)
				key = IUNIT[8];
				else if ((len - i) == 5 && isMust5) // 万(不必填)
				key = IUNIT[4];
				else if ((len - i) == 1) // 元(必填)
				key = IUNIT[0];
				// 0遇非0时补零，不包含最后一位
				if ((len - i) > 1 && integers[i + 1] != 0) key += NUMBERS[0];
			}
			chineseInteger.append(integers[i] == 0 ? key : (NUMBERS[integers[i]] + IUNIT[len - i - 1]));
		}
		return chineseInteger.toString();
	}

	/**
	 * 得到中文金额的小数部分。
	 */
	private static String getChineseDecimal(int[] decimals) {
		StringBuilder chineseDecimal = new StringBuilder();
		for (int i = 0, len = decimals.length; i < len; i++) {
			// 舍去3位小数之后的
			if (i == 3) break;
			chineseDecimal.append(decimals[i] == 0 ? "" : (NUMBERS[decimals[i]] + DUNIT[i]));
		}
		return chineseDecimal.toString();
	}

	/**
	 * 判断第5位数字的单位"万"是否应加。
	 */
	private static boolean isMust5(String integerStr) {
		int len = integerStr.length();
		if (len > 4) {
			String subInteger = "";
			if (len > 8) {
				// 取得从低位数,第5到第8位的字串
				subInteger = integerStr.substring(len - 8, len - 4);
			} else {
				subInteger = integerStr.substring(0, len - 4);
			}
			return Integer.parseInt(subInteger) > 0;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(toChinese("123456789"));
		System.out.println(toChinese("200214503"));
		System.out.println(toChinese("203010"));
		System.out.println(toChinese("10"));
	}

}
