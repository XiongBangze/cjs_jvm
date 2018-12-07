package util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化
 */
public class AesCBCUtils {
	private static final String ENCODING = "UTF-8";
	private static final String MODEL = "AES/CBC/NoPadding";	// 加解密算法/模式/填充方式 AES/CBC/PKCS5Padding AES/CBC/PKCS7Padding

	/**
	 * 加密
	 * @param source
	 * @return
	 */
	public static String encrypt(String source, String key, String iv) {
		if (source == null || key == null || iv == null) {
			return null;
		}
		
		try {
			Cipher cipher = Cipher.getInstance(MODEL);
			int blockSize = cipher.getBlockSize();
			
			// source的字节数组必须是16的倍数，差的补0，byte默认值是0
			byte[] sourceBytes = source.getBytes(ENCODING);
			int len = sourceBytes.length;
			if (len % blockSize != 0) {
				len = len + (blockSize - (len % blockSize));
			}
			byte[] plainBytes = new byte[len];
			System.arraycopy(sourceBytes, 0, plainBytes, 0, sourceBytes.length);
			
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
			
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			byte[] encrypted = cipher.doFinal(plainBytes);
			byte[] targetBytes = Base64.getEncoder().encode(encrypted);	// 此处使用BASE64做转码
			return new String(targetBytes, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解密
	 * @param password
	 * @return
	 */
	public static String decrypt(String password, String key, String iv) {
		if (password == null || key == null || iv == null) {
			return null;
		}
		
		try {
			Cipher cipher = Cipher.getInstance(MODEL);
			
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			
			// 先用base64解密
			//byte[] passwordBytes = password.getBytes(ENCODING);
			byte[] encrypted = Base64.getDecoder().decode(password);
			byte[] original = cipher.doFinal(encrypted);
			return new String(original, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
