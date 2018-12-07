package util;


import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

@SuppressWarnings("restriction")
@Component
public class RsaClientUtil {
    //PUBLICKEY
    public static final String key2 = "";

    private static PublicKey publicKey; // Public Key Class
    @SuppressWarnings("unused")
    private static Signature sign; // Signature, used to sign the data
    private static String publicKeyStr;

    /**
     * 将公钥转换为XML字符串
     *
     * @param key
     * @return
     */
    public static String encodePublicKeyToXml(PublicKey key) {
        if (!RSAPublicKey.class.isInstance(key)) {
            return null;
        }
        RSAPublicKey pubKey = (RSAPublicKey) key;
        StringBuilder sb = new StringBuilder();
        sb.append("<RSAKeyValue>")
                .append("<Modulus>")
                .append(Base64.encodeBase64(pubKey.getModulus().toByteArray()))
                .append("</Modulus>")
                .append("<Exponent>")
                .append(Base64.encodeBase64(pubKey.getPublicExponent().toByteArray()))
                .append("</Exponent>");
        sb.append("</RSAKeyValue>");
        return sb.toString();

    }

    /**
     * 从XML字符串得到公钥
     *
     * @param xml
     * @return
     */
    public static PublicKey decodePublicKeyFromXml(String xml) throws Exception {
        xml = xml.replaceAll("\r", "").replaceAll("\n", "");
        BigInteger modulus = new BigInteger(1, Base64Helper.decode(ParamUtils
                .substringBetween(xml, "<Modulus>", "</Modulus>")));
        BigInteger publicExponent = new BigInteger(1,
                Base64Helper.decode(ParamUtils.substringBetween(xml,
                        "<Exponent>", "</Exponent>")));
        RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus,
                publicExponent);
        KeyFactory keyf;
        keyf = KeyFactory.getInstance("RSA");
        return keyf.generatePublic(rsaPubKey);
    }


    // 用公钥加密

    public static byte[] encryptData(byte[] data, PublicKey pubKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    // 用公钥加密
    public String encodeNet(String data) throws Exception {
        if (data == null) {
            return null;
        }
        try {
            if (publicKey == null) {
                String pubKeyXml = key2;
                publicKey = decodePublicKeyFromXml(pubKeyXml);
            }
            if (publicKeyStr == null) {
                publicKeyStr = getKeyString(publicKey);
            }
            return encode(publicKeyStr, data).replaceAll("\r", "").replaceAll("\n", "");
            // return Base64Helper.encode(encryptData(Base64Helper.decode(data),
            // publicKey));
        } catch (Exception e) {
            throw new Exception("加密失败：" + e.getMessage());
        }
    }

    // 用公钥加密
    public static String encode(String pubKey, String data) {
        try {
            if (publicKey == null)
                publicKey = getPublicKey(pubKey);
            if (publicKeyStr == null) {
                publicKeyStr = getKeyString(publicKey);
            }
            return Base64Helper.encode(encryptData(data.getBytes(), publicKey));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到密钥字符串（经过base64编码）
     *
     * @return
     */
    private static String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        String s = (new BASE64Encoder()).encode(keyBytes);
        return s;
    }

}
