package com.free.fs.blockchain.Model.Account.Operation;

import com.free.fs.blockchain.Model.Account.Model.Account;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.File;
import java.math.BigInteger;

public class CreateAccount {

    public Account creatAccount(String password) throws Exception {

        Account account = new Account();
        AdressGet adressGet = new AdressGet();
        Web3j web3j = Web3j.build(new HttpService("HTTP://39.101.122.245:8545"));
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
        String privateKey = privateKeyInDec.toString(16);
        String publicKey = Numeric.toHexStringWithPrefix(ecKeyPair.getPublicKey());
        String destinationDirectory = "static/upload/keystore/";
        String fileName = WalletUtils.generateNewWalletFile(password, new File(destinationDirectory));
        Credentials credentials = WalletUtils.loadCredentials(password, destinationDirectory + "/" + fileName);

        account.setPrivatekey(privateKey);
        account.setPublickey(publicKey);
        account.setFilename(fileName);
        account.setAddress(adressGet.addressget(privateKey));

        return account;
    }
}


