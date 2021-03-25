package dev.evertonsavio.app;

import dev.evertonsavio.app.utils.CmacUtils;
import dev.evertonsavio.app.utils.DateBytes;
import dev.evertonsavio.app.utils.HexStringToByteArray;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.params.KeyParameter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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

    private static byte[] longToByteArray ( final long i ) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeLong(i);
        dos.flush();
        return bos.toByteArray();
    }

    public static void main(String[] args) throws IOException {

        //HexStringToByteArray hex = new HexStringToByteArray();
        //hex.hexStringToByte();

        DateBytes dateBytes = new DateBytes();
        dateBytes.getDate();

        //getCMAC();
//        getDate();

        CmacUtils cmacUtils = new CmacUtils();

        String cmac = cmacUtils.calculateCmac("af5e40e0a1ecf6f9c9127eed1358ad8c", (byte) 0x00, 4294967295L,
                4294967295L, Byte.valueOf((byte) 127), "875e40e0a1ecf6f9c1227eed1358ad10");

        System.out.println(cmac);
    }

}
