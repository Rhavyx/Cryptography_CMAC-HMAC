HMAC // CMAC - JAVA & Golang
---
### References  

* https://stackoverflow.com/questions/60670117/cmac-aes-rfc-4493-calculation-in-java
* https://web.fe.up.pt/~ee96100/projecto/Tabela%20ascii.htm  
* https://stackoverflow.com/questions/6933039/convert-two-ascii-hexadecimal-characters-two-ascii-bytes-in-one-byte
* https://mkyong.com/java/how-do-convert-byte-array-to-string-in-java/  
* https://stackoverflow.com/questions/2817752/java-code-to-convert-byte-to-hexadecimal  
* https://www.netjstech.com/2016/09/converting-string-to-bytearray-java.html  
* https://www.loratools.nl/#/hex

```
    @Test
    void contextLoads() {

        byte a = (byte) 0b10010110;

        System.out.println("A byte representation is: " +  a);
        int i = 150;
        byte b = (byte) i; //0b11111101;

        System.out.println("Decimal representation: " + b);
        System.out.println("Decimal unsigned representation: " + Byte.toUnsignedInt(b));

        System.out.println("HEX OF 150: " + Integer.toHexString(150 & 0xFF));
        System.out.println("HEX OF -106: " + Integer.toHexString(-106 & 0xFF));

        System.out.println("-----------------------------------------");

        String hex = "FF";
        short i1 = Short.parseShort(hex.substring(0, 2), 16);
        System.out.println(i1);
        byte myByte = (byte) i1;
        System.out.println(myByte);
        System.out.println(Integer.toHexString(myByte & 0xFF));

        byte b2 = -1;
        byte b3 = (byte) 0b11111111;
        int s = 255;
        byte b4 = (byte) s;
        System.out.println(Byte.toUnsignedInt(b4));

        System.out.println("b3: " + b3);
        System.out.println(Integer.toHexString(b2 & 0xFF));
        String hexCode = String.format("%02x", Byte.parseByte(String.valueOf(b2)));
        System.out.println(hexCode);

    }
```
