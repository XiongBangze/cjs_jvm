package util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectUtils {
	
	public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null) {
			return null;
		}

		Object obj = beanClass.newInstance();
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			Method setter = property.getWriteMethod();
			if (setter != null) {
				setter.invoke(obj, map.get(property.getName()));
			}
		}
		return obj;
	}

	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter != null ? getter.invoke(obj) : null;
			if (value != null) {
				map.put(key, value);
			}
		}
		return map;
	}
	
	public static Map<String,Object> objectToMap(Object obj,boolean isSort) throws Exception {
		if (obj == null) {
			return null;
		}
		if(!isSort){
			return objectToMap(obj);
		}
		Map<String, Object> map = objectToMap(obj);
		Map<String, Object> sortMap = new LinkedHashMap<>();
		map.keySet().stream()
				.sorted((a, b) -> {
					return a.compareTo(b);
				})
				.forEach(key -> {
					sortMap.put(key, map.get(key));
				});
		return sortMap;
	}
}
