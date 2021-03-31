package dev.evertonsavio.app.utils;

import java.nio.ByteBuffer;
import java.util.Date;

public class ByteUtils {

    private static long convertByteArrayToLong(byte[] longBytes){
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.put(longBytes);
        byteBuffer.flip();
        return byteBuffer.getLong();
    }

    public static long[] convertByteArrayToLongArray(byte[] data) {
        if (data == null || data.length % Long.BYTES != 0) return null;
        // ----------
        long[] longs = new long[data.length / Long.BYTES];
        for (int i = 0; i < longs.length; i++)
            longs[i] = ( convertByteArrayToLong(new byte[] {
                    data[(i*Long.BYTES)],
                    data[(i*Long.BYTES)+1],
                    data[(i*Long.BYTES)+2],
                    data[(i*Long.BYTES)+3],
                    data[(i*Long.BYTES)+4],
                    data[(i*Long.BYTES)+5],
                    data[(i*Long.BYTES)+6],
                    data[(i*Long.BYTES)+7],
            } ));
        return longs;
    }

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