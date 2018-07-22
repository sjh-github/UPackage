package com.wwdlb.hongruan.rsa;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * RSA私钥加解密
 */
public class RSACoder {
    private static final String KEY_ALGORITHM = "RSA";
    private static String privateKeyString = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEApiWgGxAB5UPCdO37F+iJq3FzeFC6fe3joWt3dUdRsOGBX1vggFLpn9hVfZqW1oyNhNXYZHcmXKcBOpDV69gAfwIDAQABAkEAm/AYcw31GX8dVa2+y1O/Up/qpnPudR7/VJOOzQ4hCAc7xOBxAbn3rfI/LklCjQCrCSY0PazntD9wsouY3v33QQIhANAlPiH25fsTvk7rFdUtHzVwq7nUlTl0TKDQOtC77TwPAiEAzFh9McE9vxOjo6IB5u9XpuXnWzfrbO6VNKYM0qArRJECIA+gPCHgnzNSMB9Mlr8CPC2O39V7mBhQGtCX0VKKSS0RAiAnawJrcJ1amI0+aWf4hj7gQQUKAZV2Y+eASm+6yY7PIQIgKtL6hXmPGXe+6tFLunn6Z9dIU35Jf5dTGT4e1nwB+lU=";
    private static PrivateKey privateKey = null;
    private static Cipher cipher = null;
    private static PKCS8EncodedKeySpec keySpec = null;
    private static KeyFactory keyFactory = null;
    private static byte[] keyBytes = null;

    //将base64编码后的私钥字符串转成PrivateKey实例
    private static PrivateKey getPrivateKey(String privateKey) throws Exception{
        keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        keySpec = new PKCS8EncodedKeySpec(keyBytes);
        keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    //私钥解密
    public static byte[] decryptByPrivateKey(byte[] content) throws Exception{
        privateKey = getPrivateKey(privateKeyString);
        cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    //私钥加密
    public static byte[] encryptByPrivateKey(byte[] content) throws Exception {
        privateKey = getPrivateKey(privateKeyString);
        cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }
}
