package dev.evertonsavio.app.utils;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateBytes {

//    public static byte[] longToBytes(long x) {
//        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
//        buffer.putLong(x);
//        return buffer.array();
//    }
//    public static long bytesToLong(byte[] bytes) {
//        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
//        buffer.put(bytes);
//        buffer.flip();//need flip
//        return buffer.getLong();
//    }

    public void getDate(){

        Date datenow = new Date();
        System.out.println(datenow);
        System.out.println(datenow.getTime());
        //datenow = datenow.getTime() + (60 * 1000);
        Long newDate = datenow.getTime() + (1 * 1000);
        System.out.println(newDate);
        System.out.println(new Date(newDate));
        Long year = (newDate / 1000 / 60 / 60 / 24 / 365) + 1970;
        System.out.println(year);

        LocalDate localDate = datenow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        System.out.println(month);


        //byte[] meuByte = longToBytes(year);
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

}
