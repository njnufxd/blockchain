package com.free.fs.blockchain.Model.Account.Operation;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class AdressGet {
    public String addressget(String privatekey) {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));
        BigInteger privateKey = Numeric.toBigInt(privatekey);
        ECKeyPair ecKeyPair = ECKeyPair.create(privateKey);
        String address = Keys.getAddress(ecKeyPair);
        return "0x"+address;
    }
}
