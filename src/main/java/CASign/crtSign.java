package CASign;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class crtSign {

    static String cerPath = "D:\\test.crt";		//证书文件路径
    static String storePath = "D:\\test.keystore";	//证书库文件路径
    static String alias = "test1";		//证书别名
    static String storePw = "123456";	//证书库密码
    static String keyPw = "123456";	//证书密码


    public static void main(String[] args) throws Exception {



        PublicKey publicKey = getPublicKey(cerPath);
        System.out.println("pk:" + new Base64Utils().encode(publicKey.getEncoded()));
        PrivateKey privateKey = getPrivateKey(storePath, alias, storePw, keyPw);
        System.out.println("pk:" + new Base64Utils().encode(privateKey.getEncoded()));

        byte[] publicKeyEncode = publicKeyEncode(publicKey);
        String  privateKeydecode= privateKeydecode(privateKey,publicKeyEncode);
        System.out.println(privateKeydecode);
        System.out.println("==============================================================================");

        RSAUtils rsaUtils1 = new RSAUtils();
        String encrypt = rsaUtils1.encrypt("之后之后之后", publicKey);
        System.out.println("=====================================encrypt========================================="+encrypt);
        String decrypt = rsaUtils1.decrypt(encrypt, privateKey);
        System.out.println("=====================================decrypt========================================="+decrypt);
//        testSign.sign(keyStore);

        String srcData = "张三";

        byte[] bytes = rsaUtils1.encryptByPublicKey(srcData.getBytes(), new Base64Utils().encode(publicKey.getEncoded()));
        System.out.println("加密之后的内容:" + new Base64Utils().encode(bytes));

        byte[] bytes1 = rsaUtils1.decryptByPrivateKey(bytes, new Base64Utils().encode(privateKey.getEncoded()));
        System.out.println("解密之后的内容 : " + new String(bytes1));


        String priSign = "你好";
        byte[] jiamiDate = rsaUtils1.encryptByPrivateKey(priSign.getBytes(), privateKey);
        System.out.println("私钥加密之后的内容 : " + new Base64Utils().encode(jiamiDate));
        byte[] bytes2 = rsaUtils1.decryptByPublicKey(jiamiDate, publicKey);
        System.out.println("公钥解密之后的内容 : " + new String(bytes2));
        byte[] sign = rsaUtils1.sign(priSign.getBytes(),  privateKey);
        System.out.println("用私钥对信息生成数字签名之后的内容 : " + new Base64Utils().encode(sign));
        boolean verify = rsaUtils1.verify(jiamiDate, publicKey, sign);
        System.out.println("校验数字签名 : " + verify);

    }


    /**
     * 签名和验证签名
     *
     * @throws Exception
     */
    public static void sign(PrivateKey priKey,PublicKey publicKey) throws Exception {

        //需要签名的信息的内容
        String message = "中国移动通信研究院";

        //用私钥签名
        Signature signature = Signature.getInstance("NONEwithRSA");
        signature.initSign(priKey);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(message);
        signature.update(byteArrayOutputStream.toByteArray());
        String result = Hex.encodeHexString(signature.sign());
        System.out.println("签名之后的内容:" + result);


        //用公钥来验证签名
        Signature signature1 = Signature.getInstance("NONEwithRSA");
        signature1.initVerify(publicKey);
        System.out.println("公钥:" + Hex.encodeHexString(publicKey.getEncoded()));
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream1 = new DataOutputStream(byteArrayOutputStream1);
        dataOutputStream1.writeUTF(message);
        signature1.update(byteArrayOutputStream1.toByteArray());

        System.out.println("验证结果:   " + signature1.verify(Hex.decodeHex(result.toCharArray())));
    }

    private static byte[] publicKeyEncode(PublicKey publicKey) throws Exception{
        String input = "慧与(中国)有限公司";
        Cipher cipher = Cipher.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) publicKey;
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        //加密后的内容
        System.out.println("加密之后的内容:" + Hex.encodeHexString(cipherText));
        return cipherText;
    }


    private static String privateKeydecode(PrivateKey privateKey,byte[] cipherText) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("解密之后的内容 : " + new String(plainText));
        return  new String(plainText);
    }


    private static PublicKey getPublicKey(String cerPath) throws Exception {
        CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
        FileInputStream fis = new FileInputStream(cerPath);
        X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(fis);
        PublicKey pk = Cert.getPublicKey();
//        String publicKey = new BASE64Encoder().encode(pk.getEncoded());
        return pk;
    }

    private static PrivateKey getPrivateKey(String storePath, String alias, String storePw, String keyPw) throws Exception {
        FileInputStream is = new FileInputStream(storePath);
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(is, storePw.toCharArray());
        is.close();
        PrivateKey key = (PrivateKey) ks.getKey(alias, keyPw.toCharArray());
//        System.out.println("privateKey:" + new BASE64Encoder().encode(key.getEncoded()));
//        String privateKey = new BASE64Encoder().encode(key.getEncoded());
        return key;

    }


}
