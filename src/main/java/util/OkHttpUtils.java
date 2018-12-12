//package util;
//
//import okhttp3.*;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//public class OkHttpUtils {
//
//	private static OkHttpClient client = new OkHttpClient.Builder()
//			.connectTimeout(10, TimeUnit.SECONDS)
//			.writeTimeout(30, TimeUnit.SECONDS)
//			.readTimeout(120, TimeUnit.SECONDS)
//			.build();
//
//	/**
//	 * 同步Get
//	 */
//	public static String get(String url) throws IOException{
//		Request request = new Request.Builder().url(url).build();
//		return execute(request);
//	}
//
//	/**
//	 * 同步Get 且 自定义Headers
//	 */
//	public static String get(String url, Map<String, String> headers) throws IOException{
//		if (headers == null) {
//			return get(url);
//		}
//		Request request = new Request.Builder().url(url).headers(Headers.of(headers)).build();
//		return execute(request);
//	}
//
//	/**
//	 * 同步Post JSON
//	 */
//	public static String post(String url, String json) throws IOException{
//		return post(url, json, ContentType.JSON);
//	}
//
//	/**
//	 * 同步Post JSON/XML/STREAM
//	 */
//	public static String post(String url, String json, ContentType contentType) throws IOException{
//		RequestBody body = RequestBody.create(contentType.parse(), json);
//		Request request = new Request.Builder().url(url).post(body).build();
//		return execute(request);
//	}
//
//	/**
//	 * 同步Post JSON/XML/STREAM 且 自定义Headers
//	 */
//	public static String post(String url, String json, Map<String, String> headers) throws IOException{
//		if (headers == null) {
//			return post(url, json);
//		}
//		MediaType mediaType = getMediaType(headers);
//		RequestBody body = RequestBody.create(mediaType, json);
//		Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(body).build();
//		return execute(request);
//	}
//
//	/**
//	 * 同步Put JSON
//	 */
//	public static String put(String url, String json) throws IOException{
//		return put(url, json, ContentType.JSON);
//	}
//
//	/**
//	 * 同步Put JSON/XML/STREAM
//	 */
//	public static String put(String url, String json, ContentType contentType) throws IOException{
//		RequestBody body = RequestBody.create(contentType.parse(), json);
//		Request request = new Request.Builder().url(url).put(body).build();
//		return execute(request);
//	}
//
//	/**
//	 * 同步Put JSON/XML/STREAM 且 自定义Headers
//	 */
//	public static String put(String url, String json, Map<String, String> headers) throws IOException{
//		if (headers == null) {
//			return put(url, json);
//		}
//		MediaType mediaType = getMediaType(headers);
//		RequestBody body = RequestBody.create(mediaType, json);
//		Request request = new Request.Builder().url(url).headers(Headers.of(headers)).put(body).build();
//		return execute(request);
//	}
//
//	/**
//	 * 同步Delete
//	 */
//	public static String delete(String url) throws IOException{
//		Request request = new Request.Builder().url(url).delete().build();
//		return execute(request);
//	}
//
//	/**
//	 * 同步Delete 且 自定义Headers
//	 */
//	public static String delete(String url, Map<String, String> headers) throws IOException{
//		if (headers == null) {
//			return delete(url);
//		}
//		Request request = new Request.Builder().url(url).headers(Headers.of(headers)).delete().build();
//		return execute(request);
//	}
//
//	/**
//	 * 通用同步请求
//	 */
//	public static String execute(Request request) throws IOException {
//		Response response = client.newCall(request).execute();
//		if (response.isSuccessful()) {
//			return response.body().string();
//		} else {
//			response.body().close();
//			throw new IOException("Unexpected code " + response);
//		}
//	}
//
//	/**
//	 * 从headers取MediaType
//	 */
//	public static MediaType getMediaType(Map<String, String> headers){
//		for (String key : headers.keySet()) {
//			if ("content-type".equalsIgnoreCase(key)) {
//				return MediaType.parse(headers.get(key));
//			}
//		}
//		return ContentType.JSON.parse();
//	}
//
//	public enum ContentType{
//		JSON("application/json"),
//		XML("application/xml"),
//		STREAM("application/octet-stream");
//
//		private String code;
//
//		private ContentType(String code){
//			this.code = code;
//		}
//
//		public String getCode() {
//			return code;
//		}
//
//		public MediaType parse(){
//			return MediaType.parse(code);
//		}
//	}
//
//}
