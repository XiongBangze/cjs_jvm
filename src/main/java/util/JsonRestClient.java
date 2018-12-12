//package util;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Map;
//
//
//@Component
//public class JsonRestClient {
//
//	public <T> T getJson(String url, Class<T> type) throws IOException {
//		String jsonStr = OkHttpUtils.get(url);
//		return JsonUtils.toObject(jsonStr, type);
//	}
//
//	public <T> T getJson(String url, Class<T> type, Map<String, String> headers) throws IOException {
//		String jsonStr = OkHttpUtils.get(url, headers);
//		return JsonUtils.toObject(jsonStr, type);
//	}
//
//	public <T> T postJson(String url, Object data, Class<T> type) throws IOException {
//		String jsonStr = OkHttpUtils.post(url, JsonUtils.toJson(data));
//		return JsonUtils.toObject(jsonStr, type);
//	}
//
//	public <T> T postJson(String url, Object data, Class<T> type, Map<String, String> headers) throws IOException {
//		String jsonStr = "" ;
//		if(data == null){
//			jsonStr = OkHttpUtils.post(url, "", headers);
//		}else{
//			jsonStr = OkHttpUtils.post(url, JsonUtils.toJson(data), headers);
//		}
//		return JsonUtils.toObject(jsonStr, type);
//	}
//
//	/**
//	 * 泛型
//	 */
//	public <T> T getJson(String url, TypeReference<T> typeReference) throws IOException {
//		String jsonStr = OkHttpUtils.get(url);
//		return JsonUtils.toObject(jsonStr, typeReference);
//	}
//
//	public <T> T getJson(String url, TypeReference<T> typeReference, Map<String, String> headers) throws IOException {
//		String jsonStr = OkHttpUtils.get(url, headers);
//		return JsonUtils.toObject(jsonStr, typeReference);
//	}
//
//	public <T> T postJson(String url, Object data, TypeReference<T> typeReference) throws IOException {
//		String jsonStr = OkHttpUtils.post(url, JsonUtils.toJson(data));
//		return JsonUtils.toObject(jsonStr, typeReference);
//	}
//
//	public <T> T postJson(String url, Object data, TypeReference<T> typeReference, Map<String, String> headers) throws IOException {
//		String jsonStr = OkHttpUtils.post(url, JsonUtils.toJson(data), headers);
//		return JsonUtils.toObject(jsonStr, typeReference);
//	}
//
//	/* 发送post 请求，并可将请求对象按属性排序  */
//	public <T> T postSortJson(String url, Object data, Class<T> type, Map<String, String> headers) throws IOException {
//		try {
//			Map<String, Object> map = ObjectUtils.objectToMap(data,true);
//			return postJson(url,map,type,headers);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//}
