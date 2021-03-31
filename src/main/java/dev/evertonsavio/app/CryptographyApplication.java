package dev.evertonsavio.app;

import dev.evertonsavio.app.utils.CmacUtils;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.params.KeyParameter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;

public class CryptographyApplication {

    public static void main(String[] args) throws IOException {

//          HexStringToByteArray hex = new HexStringToByteArray();
//          hex.hexStringToByte();

//        DateBytes dateBytes = new DateBytes();
//        dateBytes.getDate();

//        getCMAC();


        CmacUtils cmacUtils = new CmacUtils();

        String cmac = cmacUtils.calculateCmac("af5e40e0a1ecf6f9c9127eed1358ad8c", (byte) 0x00, 4294967295L,
                4294967295L, Byte.valueOf((byte) 127), "875e40e0a1ecf6f9c1227eed1358ad10");

        System.out.println(cmac);

    }

}
