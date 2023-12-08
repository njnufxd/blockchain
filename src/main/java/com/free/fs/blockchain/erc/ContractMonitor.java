package com.free.fs.blockchain.erc;
import com.free.fs.blockchain.erc.contract.Event.Event;
import com.free.fs.blockchain.erc.contract.SolidityERC721;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.List;
public class ContractMonitor {
    private static final String CONTRACT_ADDRESS = "0x9624703ed25F6259A93d666E110aF3Dfafec895F";  // 替换为你的智能合约地址
    private static final String INFURA_ENDPOINT = "HTTP://127.0.0.1:7545"; // 替换为你的 Infura 终端节点

    public static void main(String[] args) throws Exception {

        Web3j web3j = Web3j.build(new HttpService(INFURA_ENDPOINT));;
        Credentials credentials = Credentials.create("0xd913a148d6047d37e623ae331f6c88eb94d2c297a57a9ee63a0ea82c5e1a3308");  // 替换为你的私钥

        // 加载合约
        Event contract = Event.load(CONTRACT_ADDRESS, web3j, credentials, new DefaultGasProvider());

        // 事件过滤器
        EthFilter filter = new EthFilter(DefaultBlockParameter.valueOf(BigInteger.ZERO), DefaultBlockParameter.valueOf("latest"), CONTRACT_ADDRESS);
        filter.addSingleTopic(EventEncoder.encode(SolidityERC721.RANDOMNUMBERSGENERATED_EVENT));
        web3j.ethLogFlowable(filter).subscribe(log -> {
            EventValues eventValues = contract.staticExtractEventParameters(SolidityERC721.RANDOMNUMBERSGENERATED_EVENT, log);
            List<Type> indexedParams = eventValues.getIndexedValues();
            List<Type> nonIndexedParams = eventValues.getNonIndexedValues();

            System.out.println(indexedParams+" " +nonIndexedParams);

            // 索引参数
            for (Type indexedParam : indexedParams) {
                if (indexedParam instanceof Uint256) {
                    Uint256 uintValue = (Uint256) indexedParam;
                    BigInteger value = uintValue.getValue();
                    System.out.println("Indexed Param: " + value);
                }
            }

            // 处理非索引参数
            for (Type nonIndexedParam : nonIndexedParams) {
                if (nonIndexedParam instanceof Uint256) {
                    Uint256 uintValue = (Uint256) nonIndexedParam;
                    BigInteger value = uintValue.getValue();
                    System.out.println("Non-Indexed Param: " + value);
                } else if (nonIndexedParam instanceof Utf8String) {
                    Utf8String stringValue = (Utf8String) nonIndexedParam;
                    String value = stringValue.getValue();
                    System.out.println("Non-Indexed Param: " + value);
                }  else if (nonIndexedParam instanceof DynamicArray) {
                    // 处理动态数组类型的参数
                    DynamicArray<?> dynamicArray = (DynamicArray<?>) nonIndexedParam;
                    try {
                        List<Uint256> uintList = (List<Uint256>) dynamicArray.getClass()
                                .getMethod("getValue")
                                .invoke(dynamicArray);
                        System.out.print("Non-Indexed Param: [");
                        for (int i = 0; i < uintList.size(); i++) {
                            Uint256 uintValue = uintList.get(i);
                            BigInteger value = uintValue.getValue();
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



    }
}