package util;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

public class UrlParamUtils {
	
	public static String toGetParam(Map<String, Object> map){
		return toGetParam("", map);
	}

	public static String toGetParam(String prefix, Map<String, Object> map){
		StringBuffer buff = new StringBuffer();
		for (String key : map.keySet()) {
			if (! StringUtils.isEmpty(map.get(key))) {
				String joinstr = (buff.length() == 0) ? prefix : "&";
				buff.append(joinstr + key + "=" + String.valueOf(map.get(key)));
			}
		}
		return buff.toString();
	}
	
	public static <T> String toGetParam(T obj) throws Exception{
		Map<String, Object> map = ObjectUtils.objectToMap(obj);
		return toGetParam(map);
	}
	
	public static <T> String toGetParam(T obj, boolean isSort) throws Exception{
		if (! isSort) {
			return toGetParam(obj);
		}
		Map<String, Object> map = ObjectUtils.objectToMap(obj);
		String result = map.keySet().stream()
			.sorted((a, b) -> {
				return a.compareTo(b);
			})
			.map(s -> {
				if (StringUtils.isEmpty(map.get(s))) {
					return "";
				} else {
					return s + "=" + map.get(s);
				}
			})
			.collect(Collectors.joining("&"));
		return result;
	}
	
	public static <T> String toGetParam(String prefix, T obj) throws Exception{
		Map<String, Object> map = ObjectUtils.objectToMap(obj);
		return toGetParam(prefix, map);
	}
	
	public static <T> String toGetJsonParam(T obj,boolean isSort) throws Exception {
		Map<String, Object> map = ObjectUtils.objectToMap(obj,isSort);
		return JsonUtils.toJson(map);
	}
	

}
