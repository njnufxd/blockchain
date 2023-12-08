package com.free.fs.blockchain.Service.impl;

import com.free.fs.blockchain.Model.ConfirmInfo;
import com.free.fs.blockchain.Model.Merkle.MerkleTree;
import com.free.fs.blockchain.Model.Merkle.PathNode;
import com.free.fs.blockchain.Model.RanInfo;
import com.free.fs.blockchain.Service.RootdbService;
import com.free.fs.blockchain.erc.contract.Rootdb;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RootdbServiceimpl implements RootdbService {

    @Override
    public boolean setvalue(String address, byte[] hash,String walletKey) throws Exception {
        String netWorkUrl = "HTTP://39.101.122.245:8545";
        //String netWorkUrl = "HTTP://127.0.0.1:7545";
        // 合约地址
        String contractAddress = "0x29f6Ff8ed5A21767A89C1F11a837c055eCE0584A";
        Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
        Credentials credentials = Credentials.create(walletKey);
        TransactionManager transactionManager = new RawTransactionManager(
                web3, credentials,1337);
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        Rootdb contract = Rootdb.load(contractAddress, web3,
                transactionManager, new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));

        RemoteFunctionCall<TransactionReceipt> Word = contract.updateMapping(address,hash);
        TransactionReceipt send = Word.send();
        System.out.println("Word1: "+send);

        RemoteFunctionCall<byte[]> Word2 = contract.revalue(address);
        byte[] send1 = Word2.send();

        System.out.println("back"+ MerkleTree.bytesToHex(send1));
        return Arrays.equals(send1,hash);
    }
    @Override
    public boolean PDPConfirm(String walletKey,String address,int Size) throws IOException {
        String netWorkUrl = "HTTP://39.101.122.245:8545";
        // 合约地址
        String contractAddress = "0x29f6Ff8ed5A21767A89C1F11a837c055eCE0584A";
        Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
        Credentials credentials = Credentials.create(walletKey);
        TransactionManager transactionManager = new RawTransactionManager(
                web3, credentials,1337);
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        Rootdb contract = Rootdb.load(contractAddress, web3,
                transactionManager, new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));

        //事件过滤
        EthFilter filter = new EthFilter(DefaultBlockParameter.valueOf(BigInteger.ZERO), DefaultBlockParameter.valueOf("latest"), contractAddress);
        filter.addSingleTopic(EventEncoder.encode(Rootdb.VERIFICATIONRESULT_EVENT));


        AtomicInteger sizeAccount = new AtomicInteger();

        AtomicReference<String> addressBack = new AtomicReference<>();

        List<Boolean> booleanList = new ArrayList<>(Size);
        AtomicInteger account= new AtomicInteger();

        //Disposable disposable =web3.ethLogFlowable(filter).debounce(100, TimeUnit.MILLISECONDS)
        web3.ethLogFlowable(filter).subscribe(log -> {
            EventValues eventValues = contract.staticExtractEventParameters(Rootdb.VERIFICATIONRESULT_EVENT, log);
            List<Type> indexedParams = eventValues.getIndexedValues();
            List<Type> nonIndexedParams = eventValues.getNonIndexedValues();

            System.out.println(indexedParams+" " +nonIndexedParams);


            // 索引参数
            for (Type indexedParam : indexedParams) {
                if (indexedParam instanceof Address) {
                    Address addressValue = (Address) indexedParam;
                    String value = addressValue.getValue();
                    System.out.println("Indexed Param (Address): " + value);
                    addressBack.set(value);
                }
            }

            // 非索引参数
            for (Type nonIndexedParam : nonIndexedParams) {
                if (nonIndexedParam instanceof Uint256) {
                    Uint256 uintValue = (Uint256) nonIndexedParam;
                    BigInteger value = uintValue.getValue();
                    System.out.println("Non-Indexed Param (Uint256): " + value);
                } else if (nonIndexedParam instanceof Utf8String) {
                    Utf8String stringValue = (Utf8String) nonIndexedParam;
                    String value = stringValue.getValue();
                    System.out.println("Non-Indexed Param (String): " + value);
                }  else if (nonIndexedParam instanceof Bool) {
                    if (account.get() ==Size){
                        booleanList.clear();
                        account.set(0);
                    }
                    account.getAndIncrement();
                    Bool boolValue = (Bool) nonIndexedParam;
                    boolean value = boolValue.getValue();
                    System.out.println("Non-Indexed Param (Bool): " + value);
                    booleanList.add(value);
                }

            }
        });

        for (Boolean result:booleanList)
            if (!result) return false;
        return true;
    }

    @Override
    public boolean infoConfirm(String superWalletKey, MerkleTree merkleTree, RanInfo ranInfo) throws Exception {

        String address = ranInfo.getAddress();
        List<BigInteger> num = ranInfo.getUint256List();

        //节点验证类
        List<ConfirmInfo> confirmInfo = new ArrayList<>();

        //数字对应的叶子节点
        List<byte[]> leafNode = new ArrayList<>();
        for (BigInteger integer : num){
            leafNode.add(merkleTree.numtoPath(integer.intValue()));
        }


        for (byte[] checkNode:leafNode) {
            ConfirmInfo confirmInfo1 = new ConfirmInfo();
            List<PathNode> verificationPath = merkleTree.getVerificationPath(checkNode);
            List<byte[]> proofs = new ArrayList<>();
            List<Boolean> locations = new ArrayList<>();
            for (PathNode pathNode:verificationPath){
                proofs.add(pathNode.data);
                locations.add(pathNode.isleft); // 将isleft转换为单个元素的列表形式
            }
            confirmInfo1.setLeafNode(checkNode);
            confirmInfo1.setProofs(proofs);
            confirmInfo1.setLocations(locations);
            confirmInfo.add(confirmInfo1);
        }
        String netWorkUrl = "HTTP://39.101.122.245:8545";
        // 合约地址
        String contractAddress = "0x29f6Ff8ed5A21767A89C1F11a837c055eCE0584A";
        String result = null;

        Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
        Credentials credentials = Credentials.create(superWalletKey);
        TransactionManager transactionManager = new RawTransactionManager(
                web3, credentials,1337);
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        Rootdb contract = Rootdb.load(contractAddress, web3,
                transactionManager, new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));
        for (ConfirmInfo confirmInfo1:confirmInfo) {
            RemoteFunctionCall<TransactionReceipt> pdp = contract.confirm(confirmInfo1.getLeafNode(),confirmInfo1.getProofs(), confirmInfo1.getLocations(), address);
            TransactionReceipt pdpsend = pdp.send();
            System.out.println(pdpsend);
            result = pdpsend.getTransactionHash();
        }
        return result != null;
    }
}
