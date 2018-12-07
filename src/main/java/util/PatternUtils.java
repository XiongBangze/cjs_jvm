package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
	
	public static String getMatchItem(String source, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source);
		if (m.find()) {
			return m.group();
		}
		return null;
	}
	
}
