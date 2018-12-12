//package util;
//
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class GroupMap<K, V> extends HashMap<K, List<V>> {
//
//	private static final long serialVersionUID = 6559903117273594356L;
//
//	private static String GROUP_ARRAY_KEY = "GROUP_ARRAY_KEY";
//
//	public void putGroup(K k, V v) {
//		if (k == null || v == null || StringUtils.isEmpty(String.valueOf(k))) {
//			return;
//		}
//		if (containsKey(k)) {
//			get(k).add(v);
//		} else {
//			List<V> values = new ArrayList<V>();
//			values.add(v);
//			put(k, values);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public void putArray(K k,V v){
//		if(k == null || v == null || StringUtils.isEmpty(String.valueOf(k))){
//			return;
//		}
//		if(!containsKey(k)){
//			put(k,null);
//			if(containsKey(GROUP_ARRAY_KEY)){
//				get(GROUP_ARRAY_KEY).add(v);
//			} else {
//				List<V> values = new ArrayList<V>();
//				values.add(v);
//				put((K) GROUP_ARRAY_KEY,values);
//			}
//		}
//	}
//
//	public List<V> getArray() {
//		return super.get(GROUP_ARRAY_KEY);
//	}
//}
