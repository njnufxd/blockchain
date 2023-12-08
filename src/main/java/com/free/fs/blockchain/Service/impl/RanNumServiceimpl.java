package com.free.fs.blockchain.Service.impl;

import com.free.fs.blockchain.Model.RanInfo;
import com.free.fs.blockchain.Service.RanNumService;
import com.free.fs.blockchain.erc.contract.RanNumClaim;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RanNumServiceimpl implements RanNumService {

    @Override
    public boolean rannumclaim(String walletKey, String accountAddress,int size) throws Exception {
        String netWorkUrl = "HTTP://39.101.122.245:8545";
        // 合约地址
        String contractAddress = "0xcd363CE6cCf50269Ba968C506ad72c3B17c91DcE";
        Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
        Credentials credentials = Credentials.create(walletKey);
        TransactionManager transactionManager = new RawTransactionManager(
                web3, credentials,1337);
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        RanNumClaim contract = RanNumClaim.load(contractAddress, web3,
                transactionManager, new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));
        RemoteFunctionCall<TransactionReceipt> Word = contract.generateRandomNumbers(BigInteger.valueOf(size),accountAddress);
        TransactionReceipt send = Word.send();
        return send != null;
    }

    @Override
    public RanInfo numMonitor(String walletKey) throws IOException {
        String netWorkUrl = "HTTP://39.101.122.245:8545";
        String contractAddress = "0xcd363CE6cCf50269Ba968C506ad72c3B17c91DcE";
        Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
        Credentials credentials = Credentials.create(walletKey);
        TransactionManager transactionManager = new RawTransactionManager(
                web3, credentials,1337);
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        RanNumClaim contract = RanNumClaim.load(contractAddress, web3,
                transactionManager, new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));

        // 事件过滤器
        EthFilter filter = new EthFilter(DefaultBlockParameter.valueOf(BigInteger.ZERO), DefaultBlockParameter.valueOf("latest"), contractAddress);
        filter.addSingleTopic(EventEncoder.encode(RanNumClaim.RANDOMNUMBERSGENERATED_EVENT));

        List<BigInteger> uint256List = new ArrayList<>();
        AtomicReference<String> addressBack = new AtomicReference<>();
        // 监听事件
        web3.ethLogFlowable(filter).subscribe(log -> {
            EventValues eventValues = contract.staticExtractEventParameters(RanNumClaim.RANDOMNUMBERSGENERATED_EVENT, log);
            List<Type> indexedParams = eventValues.getIndexedValues();
            List<Type> nonIndexedParams = eventValues.getNonIndexedValues();

            System.out.println(indexedParams+" " +nonIndexedParams);

            // 处理索引参数
            for (Type indexedParam : indexedParams) {
                if (indexedParam instanceof Uint256) {
                    Uint256 uintValue = (Uint256) indexedParam;
                    BigInteger value = uintValue.getValue();
                    System.out.println("Indexed Param: " + value);
                }
            }

            // 处理非索引参数
            for (Type nonIndexedParam : nonIndexedParams) {
                if (nonIndexedParam instanceof Utf8String) {
                    addressBack.set(null);
                    Utf8String stringValue = (Utf8String) nonIndexedParam;
                    String value = stringValue.getValue();
                    addressBack.set(value);
                    System.out.println("Non-Indexed Param: " + value);
                }  else if (nonIndexedParam instanceof DynamicArray) {
                    // 处理动态数组类型的参数
                    DynamicArray<?> dynamicArray = (DynamicArray<?>) nonIndexedParam;
                    try {
                        List<Uint256> uintList = (List<Uint256>) dynamicArray.getClass()
                                .getMethod("getValue")
                                .invoke(dynamicArray);
                        uint256List.clear();
                        System.out.print("Non-Indexed Param: [");
                        for (int i = 0; i < uintList.size(); i++) {
                            Uint256 uintValue = uintList.get(i);
                            BigInteger value = uintValue.getValue();
                            uint256List.add(value);
                            System.out.print(value);
                            if (i != uintList.size() - 1) {
                                System.out.print(", ");
                            }
                        }

                        System.out.println("]");
                    } catch (Exception e) {
                        // 处理异常情况
                        e.printStackTrace();
                    }
                }
            }
        });

        RanInfo ranNumClaim = new RanInfo();
        ranNumClaim.setUint256List(uint256List);
        ranNumClaim.setAddress(addressBack.get());

        return ranNumClaim;
    }

}
