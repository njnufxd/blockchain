package com.free.fs.blockchain.erc;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import java.math.BigInteger;

public class SolidityRequestService {
    private static SolidityRequestService contractUtil = new SolidityRequestService();
    private SolidityRequestService(){
    }
    public static SolidityRequestService getInstance() {
        return contractUtil;
    }
    // 网络地址
    private static String netWorkUrl = "HTTP://127.0.0.1:8545";
    // 钱包私钥
    private static String walletKey = "6145106caefe77092fef78b9ab58e764ba873d8f081ba52422fa716f71d7b6a0";
    // 合约地址
    private static String contractAddress = "0x46146E84b6BbB36c542bb88eC58a9b1362251895";

    public static void useContract(){
        try {
            //连接对应节点
            Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
            Credentials credentials = Credentials.create(walletKey);
            TransactionManager transactionManager = new RawTransactionManager(
                    web3, credentials,3);
            BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
            /*SolidityERC721 contract = SolidityERC721.load(contractAddress, web3,
                    transactionManager, new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));*/
            //调用合约方法
            /*RemoteFunctionCall<BigInteger> Word = contract.showint();
            BigInteger send = Word.send();
            System.out.println(send);*/

            /*RemoteFunctionCall<TransactionReceipt> setWord = contract.getRootHashString(temple);
            System.out.println("名称："+setWord.send());*/


          /*  RemoteFunctionCall<BigInteger> bigIntegerRemoteFunctionCall = contract.balanceOf("0x657cF811dBB5Ebdd83ba0Aa616A5a097f3C5387E");
            BigInteger send1 = bigIntegerRemoteFunctionCall.send();
            System.out.println("余额："+send1);

            RemoteCall<TransactionReceipt> setWord = contract.newGreeting("hello world");
            TransactionReceipt transactionReceipt = setWord.send();
            String transactionHash = transactionReceipt.getTransactionHash();
            System.out.println(transactionHash);*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SolidityRequestService instance = SolidityRequestService.getInstance();
        instance.useContract();
    }


}
