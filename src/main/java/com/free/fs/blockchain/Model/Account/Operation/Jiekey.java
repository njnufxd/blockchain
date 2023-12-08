package com.free.fs.blockchain.Model.Account.Operation;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.crypto.exception.CipherException;

import java.io.IOException;

public class Jiekey {

    String privateKey;

    public String pravite_key(String filename,String password) {
        try {

            String BasePath = "C:\\Users\\15152298604\\Desktop\\pravitechain\\datas1\\keystore\\";
            String keystorePath = BasePath + filename;
            //  keystore �ļ�
            Credentials credentials = WalletUtils.loadCredentials(password, keystorePath);
            privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);

        } catch (IOException | CipherException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

}
