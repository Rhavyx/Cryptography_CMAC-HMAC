package dev.evertonsavio.app.utils;

import java.nio.ByteBuffer;
import java.util.Date;

public class ByteUtils {

    private static void setStamp(String timeString){

        System.out.println(timeString);

        int a = 3;
        byte[] myDate = new byte[4];
        for(int i = 0; i < 8 ; i=i+2){
            System.out.println(Integer.parseInt(timeString.substring(i, i+2), 16));
            myDate[a] = (byte) Integer.parseInt(timeString.substring(i, i+2), 16);
            a--;
        }

        StringBuilder s19 = new StringBuilder();
        for(byte b: myDate){ s19.append(String.format("%02X", b)); }

        long num = Integer.parseInt(s19.toString(),16);
        System.out.println(new Date((num * 1000)));

        System.out.println(s19.toString());

    }

    public static void main(String[] args) {

        setStamp("34AC54600203008684F9803DC1417BA3F75CFCD1E0B0EC00000000");
        //
    }
}