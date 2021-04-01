package dev.evertonsavio.app.utils;

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

    private static byte getType(String timeString){
        return (byte) Integer.parseInt(timeString.substring(8, 10), 16);

    }

    private static String getUuidFromBytes(String s){

        String uuid = s.substring(14, 46);
        uuid = uuid.toLowerCase();

        StringBuilder sb = new StringBuilder();
        sb.append(uuid.substring(0, 8));
        sb.append("-");
        sb.append(uuid.substring(8, 12));
        sb.append("-");
        sb.append(uuid.substring(12, 16));
        sb.append("-");
        sb.append(uuid.substring(16, 20));
        sb.append("-");
        sb.append(uuid.substring(20));

        return sb.toString();
    }

    private static byte[] getBytesDate(String s, int byteLen){

        //Data Bytes -> 4 primeiros bytes chegam invertidos;
        int a = 3;
        byte[] myByte = new byte[byteLen];
        for(int i = 0; i < 8 ; i = i + 2){
//            System.out.println(Integer.parseInt(s.substring(i, i + 2), 16));
            myByte[a] = (byte) Integer.parseInt(s.substring(i, i + 2), 16);
            a--;
        }

        //Type
        a = 4;
        myByte[a] = (byte) Integer.parseInt(s.substring(8, 10), 16);
        a++;

        //Method
        myByte[a] = (byte) Integer.parseInt(s.substring(10,12), 16);
        a++;

        //Locked
        myByte[a] = (byte) Integer.parseInt(s.substring(12,14), 16);
        a++;

        //UserUuid
        for(int i = 0; i < 32; i = i + 2){
            myByte[a] = (byte) Integer.parseInt(s.substring(14 + i, 16 + i), 16);
            a++;
        }

        //Counter
        for(int i = 0; i < 8; i = i + 2){
            myByte[a] = (byte) Integer.parseInt(s.substring(46 + i, 48 + i), 16);
            a++;
        }

        int c = 0;
        for(byte b: myByte){
            System.out.println("Count: " + c + ": " + b);
            c++;
        }

        return myByte;
    }

    private static byte[] getMyFBytes(String s){

    if(s.length()/2 == 27){
        System.out.println(27);
        byte[] my27Byte = getBytesDate(s, 27);


    }else if(s.length()/2 == 44){
        System.out.println(44);
        byte[] my44byte = getBytesDate(s, 44);
    }



    return null;

    }

    public static void main(String[] args) {

        //setStamp("34AC54600203008684F9803DC1417BA3F75CFCD1E0B0EC00000000");
        //getType("34AC54600203008684F9803DC1417BA3F75CFCD1E0B0EC00000000");
        getMyFBytes("34AC54600203008684F9803DC1417BA3F75CFCD1E0B0EC00000000");
        getUuidFromBytes("34AC54600203008684F9803DC1417BA3F75CFCD1E0B0EC00000000");
        //
    }
}