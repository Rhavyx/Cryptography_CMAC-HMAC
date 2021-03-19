```

//    private static final byte[] HEX_CHAR = new byte[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//
//    public static final String dumpBytes(byte[] buffer) {
//        if (buffer == null) {
//            return "";
//        }
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < buffer.length; i++) {
//            sb.append("0x").append((char) (HEX_CHAR[(buffer[i] & 0x00F0) >> 4])).append(
//                    (char) (HEX_CHAR[buffer[i] & 0x000F])).append(" ");
//        }
//        System.out.println("string-hexa: " + sb.toString());
//
//        return sb.toString();
//    }

        //String permission = "f481f78e-dc92-4ee7-9814-c5b991703f30" + "00" + "FFFFFFFF" + "010307E5" + "FFFF" + "173b" + "0x7F";

       // byte[] msg = new byte[]{0x29, 0x5e, 0x40, (byte) 0xe0, (byte) 0xa1, (byte) 0xec, (byte) 0xf6, (byte) 0xf9, (byte) 0xc9, 0x12,
        //        0x7e, (byte) 0xed, 0x13, 0x58, (byte) 0xad, (byte) 0x8c};
        ----

        //System.out.println("BYTES: "+ Arrays.toString(perma));

        //String myUUID = "295e40e0a1ecf6f9c9127eed1358ad8c";
        //String myUUID = "941089c5-d813-4c65-b0c2-fc51c43d49f8";
        //myUUID = myUUID.replace("-", "");
        //System.out.println(myUUID.length());
        //String[] arr = new String[myUUID.length()/2];



//        for(int i = 0; i < arr.length; i++){
//            arr[i] = myUUID.
//        }

//        StringBuffer sb = new StringBuffer();
//        char ch[] = myUUID.toCharArray();
//
//        System.out.println(ch);
//        for(int i = 0; i < ch.length; i++) {
//            String hexString = Integer.toHexString(ch[i]);
//            sb.append(hexString);
//        }
//        System.out.println(sb);
//        String result = sb.toString();
//        System.out.println(result);
//
//        byte[] myBytesUUID = myUUID.getBytes(StandardCharsets.UTF_8);
//        System.out.println(dumpBytes(myBytesUUID));
//
//        byte test = 0x29;

//        List<String> byteArray = new ArrayList<>();
//        for(int i = 0; i < 32; i = i + 2){
//            byteArray.add("0x" + myUUID.substring(i, i + 2));
//        }
//        for(String str : byteArray){
//            System.out.println(Byte.parseByte(str));
//        }
        //byte[] testandoConversao = new byte[]{(byte) byteArray.get(0), }

        //StringBuilder s20 = new StringBuilder();

        //for (byte b : testByte){ s20.append(String.format("%02X", b)); }
        //System.out.println("ecrypted Kmac 00 :" + s20.toString());
```