package util;

import org.springframework.util.StringUtils;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc 用于获取客户端的相关信息
 * @author wexiaodong.zhang
 *
 */
public class ClientUtils {
    

    public static String getRealIp(HttpServletRequest request){
        
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(!StringUtils.isEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(!StringUtils.isEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
    
    public static String getUserAgent(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent").replaceAll(" ", "").trim();
        return userAgent;
    }
    
     public static String getOuterIp(HttpServletRequest request){
            String XFor = request.getHeader("X-Forwarded-For");
            if(!StringUtils.isEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
                //多次反向代理后会有多个ip值，取第一个外网ip
                int index = XFor.indexOf(",");
                if(index != -1){
                    String[] ips = XFor.split(",");
                    String targetIp = ips[0];
                    for(String ip : ips) {
                        if(!(internalIp(ip))){
                            targetIp = ip;
                            break;
                        }
                    }
                    return targetIp;
                }else{
                    return XFor;
                }
            }
            String Xip = request.getHeader("X-Real-IP");
            if(!StringUtils.isEmpty(Xip) && !"unKnown".equalsIgnoreCase(Xip)){
                return Xip;
            }
            Xip = request.getRemoteAddr();
            return Xip;
        }
        
        private static boolean internalIp(String ip) {
            byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
            return internalIp(addr);
        }


        public static boolean internalIp(byte[] addr) {
            final byte b0 = addr[0];
            final byte b1 = addr[1];
            //10.x.x.x/8
            final byte SECTION_1 = 0x0A;
            //172.16.x.x/12
            final byte SECTION_2 = (byte) 0xAC;
            final byte SECTION_3 = (byte) 0x10;
            final byte SECTION_4 = (byte) 0x1F;
            //192.168.x.x/16
            final byte SECTION_5 = (byte) 0xC0;
            final byte SECTION_6 = (byte) 0xA8;
            switch (b0) {
                case SECTION_1:
                    return true;
                case SECTION_2:
                    if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                        return true;
                    }
                case SECTION_5:
                    switch (b1) {
                        case SECTION_6:
                            return true;
                    }
                default:
                    return false;
            }
        }
}
