package util;

import java.util.Random;

public class RandomUtils {
	
	/**
	 * 常见字符 ascii 码表
	 * 0-9 48-57  0x30-0x39
     * A-Z 65-90  0x41-0x5A
     * a-z 97-122 0x61-0x7A
     */
	public static String randomValue(int len){
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int val = ran.nextInt(26);
			char ch = (char) (97 + val);
			sb.append(ch);
		}
		return sb.toString();
	}
	
	
}
