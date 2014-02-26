package com.micmiu.pdf.itext;

public class TestCN {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "test 。some chinese charater 中文";
		int n=0;
		for(int i=0; i<s.length(); i++) {
			n = (int)s.charAt(i);
			if((19968 <= n && n <40623)) {
				System.out.println("第"+i+"个字符是中文");
			}
		}


	}

}
