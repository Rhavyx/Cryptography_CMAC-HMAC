package dev.evertonsavio.app.utils;

import java.util.Arrays;

public class HexStringToByteArray {

    byte[] msgByte = new byte[]{0x29, 0x5e, 0x40, (byte) 0xe0, (byte) 0xa1, (byte) 0xec, (byte) 0xf6, (byte) 0xf9, (byte) 0xc9, 0x12,
            0x7e, (byte) 0xed, 0x13, 0x58, (byte) 0xad, (byte) 0x8c};

    public void hexStringToByte(){

        System.out.println("MSG BYTE COMPARISON: " + msgByte);
        System.out.println("MESSAGE First Byte: " + msgByte[0]);

        String myUUID = "295e40e0a1ecf6f9c9127eed1358ad8c";

        int a = 0;
        byte[] testByte = new byte[16];
        for(int i = 0; i < 32; i = i+2){
            System.out.println(Integer.parseInt(myUUID.substring(i, i+2), 16) + " " + myUUID.substring(i, i+2));
            testByte[a] = (byte) Integer.parseInt(myUUID.substring(i, i+2), 16);
            a = a +1;
        }
        System.out.println("TEST BYTE SIZE: " + testByte.length + ". Test BYTE first byte : " + testByte[0]);
        System.out.println("TEST BYTE COMPARISON: " + testByte);
        System.out.println(Arrays.equals(msgByte, testByte));
    }

}
