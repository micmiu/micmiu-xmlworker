package com.micmiu.pdf.itext;

import java.util.regex.Pattern;

/**
 * 中文字符判断
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class ChineseUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strArr = new String[] { "www.micmiu.com",
				"!@#$%^&*()_+{}[]|\"'?/:;<>,.",
				"！￥……（）——：；“”‘’《》，。？、","不要啊", "やめて","韩佳人", "한가인" };
		for (String str : strArr) {
			System.out.println("===========> 测试字符串：" + str);
			System.out.println("正则判断结果：" + isChineseByREG(str));
			System.out.println("Unicode判断结果 ：" + isChinese(str));
			System.out.println("详细判断列表：");
			char[] ch = str.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				char c = ch[i];
				System.out.println(c + " --> " + (isChinese(c) ? "是" : "否"));
			}
		}

	}

	// 根据Unicode编码完美的判断中文汉字和符号
	private static final boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	// 完整的判断中文汉字和符号
	public static final boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	// 正则表达式判断：只能判断包含中文汉字，符号不能识别
	public static boolean isChineseByREG(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\u3400-\\u9FBF]+");
		return pattern.matcher(str.trim()).find();
	}

}
