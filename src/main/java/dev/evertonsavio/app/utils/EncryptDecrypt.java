package dev.evertonsavio.app.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

///84m6oRkn3gvIHxf/HuUnSko8PfSY5A1oAESvL4t0pHFbBbuX0kF4NXyPoYfD6u7
public class EncryptDecrypt {

    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] { 'P', 'A', 'D', 'O', 'T', 'E', 'C', 'H', '2', '0', '2', '0', '2', '0', '2', '0' };

    /**
     * Encrypt a string using AES encryption algorithm.
     *
     * @param pwd the password to be encrypted
     * @return the encrypted string
     */
    public static String encrypt(String pwd) {
        String encodedPwd = "";
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(pwd.getBytes());
            encodedPwd = Base64.getEncoder().encodeToString(encVal);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return encodedPwd;

    }

    /**
     * Decrypt a string with AES encryption algorithm.
     *
     * @param encryptedData the data to be decrypted
     * @return the decrypted string
     */
    public static String decrypt(String encryptedData) {
        String decodedPWD = "";
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            decodedPWD = new String(decValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedPWD;
    }

    /**
     * Generate a new encryption key.
     */
    private static Key generateKey() {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGO);

/*        String inputString = "PADOTECH20202020";
        byte[] byteArrray = inputString.getBytes();
        System.out.println("O BYTE ARRAY É: " + byteArrray);*/
        return key;
    }

}

