//package util;
//
//import com.panda.common.enums.ChannelCodeEnum;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Component
//public class HeaderUtil {
//    private static String appId;
//    private static String token;
//
//    @Value(value = "${member.appId}")
//	public void setAppId(String appId) {
//		HeaderUtil.appId = appId;
//	}
//
//    @Value(value = "${member.token}")
//	public void setToken(String token) {
//		HeaderUtil.token = token;
//	}
//
//
//	private static RsaClientUtil rsaClientUtils = new RsaClientUtil();
//
//	public static String getUserAgent(HttpServletRequest request){
//		return request.getHeader("User-Agent");
//	}
//
//	public static Map<String, String> full(Map<String, String> headers,HttpServletRequest request){
//		headers.put("x-user-source", "web");
//		if(request == null) {
//			return headers;
//		}
//		String xforwarded = request.getHeader("X-Forwarded-For");
//		if(StringUtils.isNotEmpty(xforwarded)) {
//			headers.put("X-Forwarded-For", xforwarded);
//			String[] ips = xforwarded.split(",");
//			headers.put("x-user-ip", ips[0]);
//		}
//		String ua = request.getHeader("User-Agent");
//		if(StringUtils.isNotEmpty(ua)) {
//			headers.put("User-Agent", ua);
//		}
//		return headers;
//	}
//
//    public static Map<String,String> getHeaders(String apigwkey, String remoteIp){
//    	Map<String,String> headers = new HashMap<>();
//    	headers.put("Content-Type", "application/json;charset=UTF-8");
//    	headers.put("charset", "UTF-8");
//    	headers.put("Accept-Language", "UTF-8");
//    	headers.put("Accept", "application/json; charset=UTF-8");
//
//		headers.put("apigwkey", apigwkey);
//    	headers.put("x-auth-header", genRsaPassword());
//
//    	String tempSessionId = UUID.randomUUID().toString();
//    	headers.put("JJE-Chain-Info", nullSafe(tempSessionId + "#" + remoteIp));
//    	headers.put("JJE-SessionID", nullSafe(tempSessionId));
//    	return headers;
//    }
//
//	public static Map<String,String> getHeadersByChannelCode(String apigwkey, String remoteIp,String channelCode) {
//		Map<String, String> headers = getHeaders(apigwkey, remoteIp);
//
//		return headers;
//	}
//
//	public static Map<String,String> getHeaders(String apigwkey){
//    	Map<String,String> headers = new HashMap<>();
//    	headers.put("Content-Type", "application/json;charset=UTF-8");
//    	headers.put("apigwkey", apigwkey);
//    	return headers;
//    }
//
//    public static Map<String,String> getOrderHeaders(String apigwkey, String sysName){
//    	Map<String,String> headers = new HashMap<>();
//    	headers.put("Content-Type", "application/json;charset=UTF-8");
//    	headers.put("charset", "UTF-8");
//    	headers.put("Accept-Language", "UTF-8");
//    	headers.put("Accept", "application/json; charset=UTF-8");
//    	headers.put("apigwkey", apigwkey);
//    	headers.put("sysName", sysName);
//    	return headers;
//    }
//
//    public static Map<String,String> getOuterOrderHeaders(String apigwkey, String sysName, String memberId){
//    	Map<String,String> headers = new HashMap<>();
//    	headers.put("Content-Type", "application/json;charset=UTF-8");
//    	headers.put("charset", "UTF-8");
//    	headers.put("Accept-Language", "UTF-8");
//    	headers.put("Accept", "application/json; charset=UTF-8");
//    	headers.put("apigwkey", apigwkey);
//    	headers.put("sysName", sysName);
//    	headers.put("mid", memberId);
//    	return headers;
//    }
//
//
//    public static Map<String,String> getFormHeaders(String apigwkey){
//    	Map<String,String> headers = new HashMap<>();
//    	headers.put("Content-Type", "application/x-www-form-urlencoded");
//    	headers.put("charset", "UTF-8");
//    	headers.put("Accept-Language", "UTF-8");
//    	headers.put("apigwkey", apigwkey);
//    	return headers;
//    }
//
//    public static Map<String,String> getH5CouponHeaders(String apigwkey,ChannelCodeEnum channelCode,String token, String source){
//    	Map<String,String> headers = new HashMap<>();
//    	headers.put("Content-Type", "application/json;charset=UTF-8");
//    	headers.put("charset", "UTF-8");
//    	headers.put("Accept-Language", "UTF-8");
//    	headers.put("Accept", "application/json; charset=UTF-8");
//    	headers.put("apigwkey", apigwkey);
//    	headers.put("token", token);
//    	if(channelCode != null) {
//    		headers.put("channelCode", channelCode.name());
//    	}
//    	headers.put("source", source);
//    	return headers;
//    }
//
//    public static Map<String,String> getProductHeaders(String token, ChannelCodeEnum channelCode, String source,String apigwkey){
//    	Map<String,String> headers = new HashMap<>();
//    	headers.put("Content-Type", "application/json;charset=UTF-8");
//    	headers.put("charset", "UTF-8");
//    	headers.put("Accept-Language", "UTF-8");
//    	headers.put("Accept", "application/json; charset=UTF-8");
//    	headers.put("token", token);
//    	if(channelCode != null) {
//    		headers.put("channelCode", channelCode.name());
//    	}
//    	headers.put("source", source);
//    	headers.put("apigwkey", apigwkey);
//    	return headers;
//    }
//
//    public static Map<String, String> getCouponHeaders(String apigwkey, String xAuthHeader){
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Content-Type", "application/json;charset=UTF-8");
//    	headers.put("charset", "UTF-8");
//    	headers.put("Accept-Language", "UTF-8");
//    	headers.put("Accept", "application/json; charset=UTF-8");
//		headers.put("apigwkey", apigwkey);
//		headers.put("x-auth-header", xAuthHeader);
//		return headers;
//	}
//
//    private static String nullSafe(String str) {
//        if (str == null) {
//            return "";
//        } else {
//            return str;
//        }
//    }
//
//    private static String genRsaPassword() {
//        try {
//            String rsaCode = rsaClientUtils.encodeNet(token + "," + appId + "," + DateUtils.getNowDate().getTime());
//            return rsaCode;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//}
