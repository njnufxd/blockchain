package com.free.fs.blockchain.Model.Account.Operation;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EtherTransferExample {

    public String transETH(String toAddress) {
        Web3j web3 = Web3j.build(new HttpService("HTTP://39.101.122.245:8545"));
        try {

            //String privateKey = "3a223f149be1397e693cec9beb5451e139efc524e0fffb27826e766e85fdc3a5";
            Credentials credentials = Credentials.create("0x35e9b5622c0411f5e336c83e8a85bcebb0657bacaed99122e49447d3a62cbfb3");
            String fromAddress = credentials.getAddress();
            //String toAddress = "0x6A5Ed3Ff542b10F6a45591203561bEE66262C322";
            BigDecimal amountInEther = new BigDecimal("0.2");
            BigInteger amountInWei = Convert.toWei(amountInEther, Convert.Unit.ETHER).toBigInteger();
            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(fromAddress,
                    DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            TransactionReceipt transactionReceipt = Transfer.sendFunds(
                    web3, credentials, toAddress,
                    amountInEther, Convert.Unit.ETHER).send();
            EthGetBalance ethGetBalance = web3.ethGetBalance(fromAddress, DefaultBlockParameterName.LATEST).send();
            BigInteger balance = ethGetBalance.getBalance();

            System.out.println("余额:"+balance);
            return"转账成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
}

