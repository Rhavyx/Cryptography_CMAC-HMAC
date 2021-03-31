package dev.evertonsavio.app.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncrypyDecrypt {

    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    public static byte[] getKey(String inputString){
        byte[] byteKey = new byte[inputString.length()/2];
        int a = 0;
        for(int i = 0; i < inputString.length() ; i= i + 2){
            System.out.println(Integer.parseInt(inputString.substring(i, i+2), 16) + " " + inputString.substring(i, i+2));
            byteKey[a] = (byte) Integer.parseInt(inputString.substring(i, i+2), 16);
            a++;
        }
        return byteKey;
    }

    public static String encrypt(String value) {
        try {
            //IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            IvParameterSpec iv = new IvParameterSpec(getKey("9280B7D1160C05F5E9A3C53A22099052"));
            //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            SecretKeySpec skeySpec = new SecretKeySpec(getKey("9280B7D1160C05F5E9A3C53A22099052"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            //IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            IvParameterSpec iv = new IvParameterSpec(getKey("9280B7D1160C05F5E9A3C53A22099052"));
            //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            SecretKeySpec skeySpec = new SecretKeySpec(getKey("9280B7D1160C05F5E9A3C53A22099052"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(byte[] encrypted) {
        try {
            //IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            IvParameterSpec iv = new IvParameterSpec(getKey("9280B7D1160C05F5E9A3C53A22099052"));
            //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            SecretKeySpec skeySpec = new SecretKeySpec(getKey("9280B7D1160C05F5E9A3C53A22099052"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(encrypted);

            StringBuilder s19 = new StringBuilder();
            for(byte b: original){
                s19.append(String.format("%02X", b));
            }

            //return new String(original);
            return s19.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
//        String originalString = "password";
//        System.out.println("Original String to encrypt - " + originalString);
//        String encryptedString = encrypt(originalString);

        byte[] encryptedString = getMsgBytes("5DC97095BB7EBE81A7D33E7827D5C6080113486AD70A1217709D050006201EE2");

        System.out.println("Encrypted String - " + encryptedString);
        String decryptedString = decrypt(encryptedString);
        System.out.println("After decryption - " + decryptedString);
    }

    public static byte[] getMsgBytes(String inputString){
        byte[] byteKey = new byte[inputString.length()/2];
        int a = 0;
        for(int i = 0; i < inputString.length() ; i= i + 2){
            System.out.println(Integer.parseInt(inputString.substring(i, i+2), 16) + " " + inputString.substring(i, i+2));
            byteKey[a] = (byte) Integer.parseInt(inputString.substring(i, i+2), 16);
            a++;
        }
        return byteKey;
    }

}
