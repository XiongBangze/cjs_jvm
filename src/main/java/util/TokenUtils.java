package util;

public class TokenUtils {
    
    public static String genEmailToken(String realIp){
        String token = MD5Utils.encode(realIp);
        return token;
    }

}
