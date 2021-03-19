package dev.evertonsavio.app;

import dev.evertonsavio.app.utils.HexStringToByteArray;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.params.KeyParameter;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class CryptographyApplication {


    private static void getCMAC() {
        byte[] msg = new byte[]{0x29, 0x5e, 0x40, (byte) 0xe0, (byte) 0xa1, (byte) 0xec, (byte) 0xf6, (byte) 0xf9,
                (byte) 0xc9, 0x12, 0x7e, (byte) 0xed, 0x13, 0x58, (byte) 0xad, (byte) 0x8c, 0x00, 0x01, 0x03, 0x07, (byte)
                0xe5, 0x01, 0x03, 0x07, (byte) 0xe5, 0x00, 0x00, 0x17, 0x3b, 0x7f};

        byte[] keydata = new byte[]{0x2b, 0x7e, 0x15, 0x16, 0x28, (byte) 0xae, (byte) 0xd2, (byte) 0xa6, (byte) 0xab,
                (byte) 0xf7, 0x15, (byte) 0x88, 0x09, (byte) 0xcf, 0x4f, 0x3c};

        System.out.println("MESSAGE 1 byte: " + msg[0]);
        System.out.println("SECRET 1 byte: " + keydata[0]);

        CipherParameters params = new KeyParameter(keydata);
        BlockCipher aes = new AESEngine();
        CMac mac = new CMac(aes);
        mac.init(params);
        mac.update(msg, 0, msg.length);

        byte[] out = new byte[mac.getMacSize()];
        mac.doFinal(out, 0);

        StringBuilder s19 = new StringBuilder();

        for (byte b : msg){
            s19.append(String.format("%02X", b));
        }
        System.out.println("ecrypted msg cmac: " + s19.toString());
        for (byte b : out) {
            s19.append(String.format("%02X", b));
        }
        System.out.println("ecrypted secret appended:" + s19.toString());


        String encodedPwd = Base64.getEncoder().encodeToString(s19.toString().getBytes());

        System.out.println("STRING 64 ENCODED: " + encodedPwd);
        String decodedPwd = new String(Base64.getDecoder().decode(encodedPwd));
        System.out.println("STRING 64 DECODED: " + decodedPwd);

        int a = 0;
        byte[] testByte = new byte[s19.length()/2];
        System.out.println(s19.length());
        for(int i = 0; i < s19.length(); i = i+2){
            System.out.println(Integer.parseInt(s19.substring(i, i+2), 16) + " " + s19.substring(i, i+2));
            testByte[a] = (byte) Integer.parseInt(s19.substring(i, i+2), 16);
            a = a +1;
        }
        System.out.println("TEST BYTE SIZE: " + testByte.length);
        String encodedTESTBYTE = Base64.getEncoder().encodeToString(testByte);

        System.out.println("BYTE 64 ENCODED: " + encodedTESTBYTE);

    }

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public static void getDate(){

        Date datenow = new Date();
        System.out.println(datenow);
        System.out.println(datenow.getTime());
        //datenow = datenow.getTime() + (60 * 1000);
        Long newDate = datenow.getTime() + (1 * 1000);
        System.out.println(newDate);
        System.out.println(new Date(newDate));
        Long year = (newDate / 1000 / 60 / 60 / 24 / 365) + 1970;
        System.out.println(year);
        byte[] meuByte = longToBytes(year);
        System.out.println(Long.toHexString(year));
        String longHEX = Long.toHexString(65535L);
        System.out.println(longHEX);

        int a = 0;
        byte[] testByte = new byte[2];
        for(int i = 0; i < 4; i = i+2){
            System.out.println(Integer.parseInt(longHEX.substring(i, i+2), 16) + " " + longHEX.substring(i, i+2));
            testByte[a] = (byte) Integer.parseInt(longHEX.substring(i, i+2), 16);
            a = a +1;
        }

    }

    private static String calculateCmac (String uuid, byte role, String uctTime, String uctDelta, String timeInit,
                                  String timeDelta, byte days, String secret){

        int a = 0;
        byte[] secretByte = new byte[16];
        for(int i = 0; i < 32; i = i+2){
            //System.out.println(Integer.parseInt(uuid.substring(i, i+2), 16) + " " + uuid.substring(i, i+2));
            secretByte[a] = (byte) Integer.parseInt(secret.substring(i, i+2), 16);
            a = a +1;
        }

        a = 0;
        byte[] msgByte = new byte[30];
        for(int i = 0; i < 32; i = i+2){
            //System.out.println(Integer.parseInt(uuid.substring(i, i+2), 16) + " " + uuid.substring(i, i+2));
            msgByte[a] = (byte) Integer.parseInt(uuid.substring(i, i+2), 16);
            a = a + 1;
        }

        msgByte[16] = role;

		String longHEX = Long.toHexString(65535L);
		System.out.println(longHEX);

		for(int i = 0; i < 4; i = i+2){
			//System.out.println(Integer.parseInt(uctTime.substring(i, i+2), 16) + " " + uctTime.substring(i, i+2));
			msgByte[a] = (byte) Integer.parseInt(uctTime.substring(i, i+2), 16);
			a = a +1;
		}
        for(int i = 0; i < 4; i = i+2){
            //System.out.println(Integer.parseInt(uctDelta.substring(i, i+2), 16) + " " + uctDelta.substring(i, i+2));
            msgByte[a] = (byte) Integer.parseInt(uctDelta.substring(i, i+2), 16);
            a = a +1;
        }
        System.out.println("TIME SIZE:" + timeInit.length());
        for(int i = 0; i < 1; i = i+1){
            System.out.println(Integer.parseInt(timeInit.substring(i, i+1), 16) + " " + timeInit.substring(i, i+1));
            msgByte[a] = (byte) Integer.parseInt(timeInit.substring(i, i+timeInit.length()), 16);
            a = a + 1;
        }
        System.out.println("DELTA SIZE: " + timeDelta.length());


        CipherParameters params = new KeyParameter(secretByte);
        BlockCipher aes = new AESEngine();
        CMac mac = new CMac(aes);
        mac.init(params);
        mac.update(msgByte, 0, msgByte.length);

        byte[] out = new byte[mac.getMacSize()];
        mac.doFinal(out, 0);

        StringBuilder s19 = new StringBuilder();
        for (byte b : msgByte){
            s19.append(String.format("%02X", b));
        }
        System.out.println("ecrypted msg cmac: " + s19.toString());
        for (byte b : out) {
            s19.append(String.format("%02X", b));
        }
        System.out.println("ecrypted secret appended:" + s19.toString());

        int d = 0;
        byte[] testByte = new byte[s19.length()/2];
        System.out.println(s19.length());
        for(int i = 0; i < s19.length(); i = i+2){
            System.out.println(Integer.parseInt(s19.substring(i, i+2), 16) + " " + s19.substring(i, i+2));
            testByte[d] = (byte) Integer.parseInt(s19.substring(i, i+2), 16);
            d = d +1;
        }

        System.out.println("TEST BYTE SIZE: " + testByte.length);
        String encodedTESTBYTE = Base64.getEncoder().encodeToString(testByte);

        return encodedTESTBYTE;
    }

    public static void main(String[] args) {

        //HexStringToByteArray hex = new HexStringToByteArray();
        //hex.hexStringToByte();

        //getCMAC();
        //getDate();

        String cmac = calculateCmac("295e40e0a1ecf6f9c9127eed1358ad8c", (byte) 0x00, Long.toHexString(16975845L),
                Long.toHexString(16975845L), Long.toHexString(0), Long.toHexString(5947L), Byte.valueOf("127"),
                "295e40e0a1ecf6f9c1227eed1358ad8c");

        System.out.println(cmac);
    }

}
