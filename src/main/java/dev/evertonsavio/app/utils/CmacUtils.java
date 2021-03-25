package dev.evertonsavio.app.utils;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.params.KeyParameter;

import java.util.Base64;

public class CmacUtils {

    public String calculateCmac (String uuid, byte role, Long uctTime, Long uctFinal, byte days, String secret) {

        //secret bytes
        int a = 0;
        byte[] secretByte = new byte[16];
        for(int i = 0; i < 32; i = i+2){
            secretByte[a] = (byte) Integer.parseInt(secret.substring(i, i+2), 16);
            a++;
        }

        uuid = uuid.replace("-", "");

        //msg bytes
        a = 0;
        byte[] msgByte = new byte[26];
        for(int i = 0; i < 32; i = i+2){
            msgByte[a] = (byte) Integer.parseInt(uuid.substring(i, i+2), 16);
            a++;
        }

        //user role bytes
        msgByte[16] = role;
        a++;

        String myUctTimeHex = Long.toHexString(uctTime);

        if(myUctTimeHex.length() == 1){
            for(int i = 0; i< 2; i++){
                msgByte[a] = (byte) Integer.parseInt(myUctTimeHex.substring(0, 1), 16);
                a++;
            }
        }
        else{
            for(int i = 0; i< myUctTimeHex.length(); i = i + 2){
                msgByte[a] = (byte) Integer.parseInt(myUctTimeHex.substring(i, i+2), 16);
                a++;
            }
        }

        String myUctDeltaHex = Long.toHexString(uctFinal);
        for(int i = 0; i< myUctDeltaHex.length(); i = i + 2){
            msgByte[a] = (byte) Integer.parseInt(myUctDeltaHex.substring(i, i+2), 16);
            a++;
        }

        //days bytes
        msgByte[a] = days;

        CipherParameters params = new KeyParameter(secretByte);
        BlockCipher aes = new AESEngine();
        CMac mac = new CMac(aes);
        mac.init(params);
        mac.update(msgByte, 0, msgByte.length);

        byte[] out = new byte[mac.getMacSize()];
        mac.doFinal(out, 0);

        StringBuilder s19 = new StringBuilder();
        for (byte b : msgByte){ s19.append(String.format("%02X", b)); }
        for (byte b : out) { s19.append(String.format("%02X", b)); }

        int d = 0;
        byte[] cmacByte = new byte[s19.length()/2];

        for(int i = 0; i < s19.length(); i = i+2){
            cmacByte[d] = (byte) Integer.parseInt(s19.substring(i, i+2), 16);
            d = d +1;
        }

        return Base64.getEncoder().encodeToString(cmacByte);
    }

}
