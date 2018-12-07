package util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
public class XmlRestClient {
	
	public <T> T get(String url, Class<T> type) throws IOException {
		String xmlStr = OkHttpUtils.get(url);
		return JaxbUtils.convertToObject(xmlStr, type);
	}
	
	public <T> T get(String url, Class<T> type, Map<String, String> headers) throws IOException {
		String xmlStr = OkHttpUtils.get(url, headers);
		return JaxbUtils.convertToObject(xmlStr, type);
	}
	
	public <T> T post(String url, Object data, Class<T> type) throws IOException {
		String xmlStr = OkHttpUtils.post(url, JsonUtils.toJson(data), OkHttpUtils.ContentType.XML);
		return JaxbUtils.convertToObject(xmlStr, type);
	}
	
	public <T> T post(String url, Object data, Class<T> type, Map<String, String> headers) throws IOException {
		boolean hasContentType = false;
		for (String key : headers.keySet()) {
			if ("content-type".equalsIgnoreCase(key)) {
				hasContentType = true;
				break;
			}
		}
		if (! hasContentType) {
			headers.put("content-type", "application/xml;charset=utf-8");
		}
		String xmlStr = OkHttpUtils.post(url, JsonUtils.toJson(data), headers);
		return JaxbUtils.convertToObject(xmlStr, type);
	}
	
}
