package com.free.fs.blockchain.erc.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.7.
 */
@SuppressWarnings("rawtypes")
public class MerklePDP_Event extends Contract {
    public static final String BINARY = "{\r\n"
            + "  \"linkReferences\": {},\r\n"
            + "  \"object\": \"608060405234801561001057600080fd5b50610652806100206000396000f300608060405260043610610041576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063cc2fdac114610046575b600080fd5b34801561005257600080fd5b5061006d60048036036100689190810190610488565b610083565b60405161007a919061053e565b60405180910390f35b600080600080889250600091505b87518210156102945787828151811015156100a857fe5b90602001906020020151905086828151811015156100c257fe5b90602001906020020151156101ae57600283826040516020018083600019166000191681526020018260001916600019168152602001925050506040516020818303038152906040526040518082805190602001908083835b602083101515610140578051825260208201915060208101905060208303925061011b565b6001836020036101000a0380198251168184511680821785525050505050509050019150506020604051808303816000865af1158015610184573d6000803e3d6000fd5b5050506040513d601f19601f820116820180604052506101a7919081019061045f565b9250610287565b600281846040516020018083600019166000191681526020018260001916600019168152602001925050506040516020818303038152906040526040518082805190602001908083835b60208310151561021d57805182526020820191506020810190506020830392506101f8565b6001836020036101000a0380198251168184511680821785525050505050509050019150506020604051808303816000865af1158015610261573d6000803e3d6000fd5b5050506040513d601f19601f82011682018060405250610284919081019061045f565b92505b8180600101925050610091565b8473ffffffffffffffffffffffffffffffffffffffff167fe41b8ac779069cbbc13495ce67864b788f4ac7e77e4506f6ca7c165847b2d2a387600019168560001916146040516102e4919061053e565b60405180910390a28560001916836000191614935050505095945050505050565b600061031182356105e2565b905092915050565b600082601f830112151561032c57600080fd5b813561033f61033a82610586565b610559565b9150818183526020840193506020810190508385602084028201111561036457600080fd5b60005b83811015610394578161037a8882610423565b845260208401935060208301925050600181019050610367565b5050505092915050565b600082601f83011215156103b157600080fd5b81356103c46103bf826105ae565b610559565b915081818352602084019350602081019050838560208402820111156103e957600080fd5b60005b8381101561041957816103ff8882610437565b8452602084019350602083019250506001810190506103ec565b5050505092915050565b600061042f8235610602565b905092915050565b6000610443823561060e565b905092915050565b6000610457825161060e565b905092915050565b60006020828403121561047157600080fd5b600061047f8482850161044b565b91505092915050565b600080600080600060a086880312156104a057600080fd5b60006104ae88828901610437565b955050602086013567ffffffffffffffff8111156104cb57600080fd5b6104d78882890161039e565b945050604086013567ffffffffffffffff8111156104f457600080fd5b61050088828901610319565b935050606061051188828901610437565b925050608061052288828901610305565b9150509295509295909350565b610538816105d6565b82525050565b6000602082019050610553600083018461052f565b92915050565b6000604051905081810181811067ffffffffffffffff8211171561057c57600080fd5b8060405250919050565b600067ffffffffffffffff82111561059d57600080fd5b602082029050602081019050919050565b600067ffffffffffffffff8211156105c557600080fd5b602082029050602081019050919050565b60008115159050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b60008190509190505600a265627a7a723058205a7c70b11e0b0ff981f5dc18d4d8120da1a77ea1c2141c90c0570e62c3f7fd806c6578706572696d656e74616cf50037\",\r\n"
            + "  \"opcodes\": \"PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH2 0x10 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x652 DUP1 PUSH2 0x20 PUSH1 0x0 CODECOPY PUSH1 0x0 RETURN STOP PUSH1 0x80 PUSH1 0x40 MSTORE PUSH1 0x4 CALLDATASIZE LT PUSH2 0x41 JUMPI PUSH1 0x0 CALLDATALOAD PUSH29 0x100000000000000000000000000000000000000000000000000000000 SWAP1 DIV PUSH4 0xFFFFFFFF AND DUP1 PUSH4 0xCC2FDAC1 EQ PUSH2 0x46 JUMPI JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x52 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x6D PUSH1 0x4 DUP1 CALLDATASIZE SUB PUSH2 0x68 SWAP2 SWAP1 DUP2 ADD SWAP1 PUSH2 0x488 JUMP JUMPDEST PUSH2 0x83 JUMP JUMPDEST PUSH1 0x40 MLOAD PUSH2 0x7A SWAP2 SWAP1 PUSH2 0x53E JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x0 DUP1 DUP9 SWAP3 POP PUSH1 0x0 SWAP2 POP JUMPDEST DUP8 MLOAD DUP3 LT ISZERO PUSH2 0x294 JUMPI DUP8 DUP3 DUP2 MLOAD DUP2 LT ISZERO ISZERO PUSH2 0xA8 JUMPI INVALID JUMPDEST SWAP1 PUSH1 0x20 ADD SWAP1 PUSH1 0x20 MUL ADD MLOAD SWAP1 POP DUP7 DUP3 DUP2 MLOAD DUP2 LT ISZERO ISZERO PUSH2 0xC2 JUMPI INVALID JUMPDEST SWAP1 PUSH1 0x20 ADD SWAP1 PUSH1 0x20 MUL ADD MLOAD ISZERO PUSH2 0x1AE JUMPI PUSH1 0x2 DUP4 DUP3 PUSH1 0x40 MLOAD PUSH1 0x20 ADD DUP1 DUP4 PUSH1 0x0 NOT AND PUSH1 0x0 NOT AND DUP2 MSTORE PUSH1 0x20 ADD DUP3 PUSH1 0x0 NOT AND PUSH1 0x0 NOT AND DUP2 MSTORE PUSH1 0x20 ADD SWAP3 POP POP POP PUSH1 0x40 MLOAD PUSH1 0x20 DUP2 DUP4 SUB SUB DUP2 MSTORE SWAP1 PUSH1 0x40 MSTORE PUSH1 0x40 MLOAD DUP1 DUP3 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP4 DUP4 JUMPDEST PUSH1 0x20 DUP4 LT ISZERO ISZERO PUSH2 0x140 JUMPI DUP1 MLOAD DUP3 MSTORE PUSH1 0x20 DUP3 ADD SWAP2 POP PUSH1 0x20 DUP2 ADD SWAP1 POP PUSH1 0x20 DUP4 SUB SWAP3 POP PUSH2 0x11B JUMP JUMPDEST PUSH1 0x1 DUP4 PUSH1 0x20 SUB PUSH2 0x100 EXP SUB DUP1 NOT DUP3 MLOAD AND DUP2 DUP5 MLOAD AND DUP1 DUP3 OR DUP6 MSTORE POP POP POP POP POP POP SWAP1 POP ADD SWAP2 POP POP PUSH1 0x20 PUSH1 0x40 MLOAD DUP1 DUP4 SUB DUP2 PUSH1 0x0 DUP7 GAS CALL ISZERO DUP1 ISZERO PUSH2 0x184 JUMPI RETURNDATASIZE PUSH1 0x0 DUP1 RETURNDATACOPY RETURNDATASIZE PUSH1 0x0 REVERT JUMPDEST POP POP POP PUSH1 0x40 MLOAD RETURNDATASIZE PUSH1 0x1F NOT PUSH1 0x1F DUP3 ADD AND DUP3 ADD DUP1 PUSH1 0x40 MSTORE POP PUSH2 0x1A7 SWAP2 SWAP1 DUP2 ADD SWAP1 PUSH2 0x45F JUMP JUMPDEST SWAP3 POP PUSH2 0x287 JUMP JUMPDEST PUSH1 0x2 DUP2 DUP5 PUSH1 0x40 MLOAD PUSH1 0x20 ADD DUP1 DUP4 PUSH1 0x0 NOT AND PUSH1 0x0 NOT AND DUP2 MSTORE PUSH1 0x20 ADD DUP3 PUSH1 0x0 NOT AND PUSH1 0x0 NOT AND DUP2 MSTORE PUSH1 0x20 ADD SWAP3 POP POP POP PUSH1 0x40 MLOAD PUSH1 0x20 DUP2 DUP4 SUB SUB DUP2 MSTORE SWAP1 PUSH1 0x40 MSTORE PUSH1 0x40 MLOAD DUP1 DUP3 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP4 DUP4 JUMPDEST PUSH1 0x20 DUP4 LT ISZERO ISZERO PUSH2 0x21D JUMPI DUP1 MLOAD DUP3 MSTORE PUSH1 0x20 DUP3 ADD SWAP2 POP PUSH1 0x20 DUP2 ADD SWAP1 POP PUSH1 0x20 DUP4 SUB SWAP3 POP PUSH2 0x1F8 JUMP JUMPDEST PUSH1 0x1 DUP4 PUSH1 0x20 SUB PUSH2 0x100 EXP SUB DUP1 NOT DUP3 MLOAD AND DUP2 DUP5 MLOAD AND DUP1 DUP3 OR DUP6 MSTORE POP POP POP POP POP POP SWAP1 POP ADD SWAP2 POP POP PUSH1 0x20 PUSH1 0x40 MLOAD DUP1 DUP4 SUB DUP2 PUSH1 0x0 DUP7 GAS CALL ISZERO DUP1 ISZERO PUSH2 0x261 JUMPI RETURNDATASIZE PUSH1 0x0 DUP1 RETURNDATACOPY RETURNDATASIZE PUSH1 0x0 REVERT JUMPDEST POP POP POP PUSH1 0x40 MLOAD RETURNDATASIZE PUSH1 0x1F NOT PUSH1 0x1F DUP3 ADD AND DUP3 ADD DUP1 PUSH1 0x40 MSTORE POP PUSH2 0x284 SWAP2 SWAP1 DUP2 ADD SWAP1 PUSH2 0x45F JUMP JUMPDEST SWAP3 POP JUMPDEST DUP2 DUP1 PUSH1 0x1 ADD SWAP3 POP POP PUSH2 0x91 JUMP JUMPDEST DUP5 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH32 0xE41B8AC779069CBBC13495CE67864B788F4AC7E77E4506F6CA7C165847B2D2A3 DUP8 PUSH1 0x0 NOT AND DUP6 PUSH1 0x0 NOT AND EQ PUSH1 0x40 MLOAD PUSH2 0x2E4 SWAP2 SWAP1 PUSH2 0x53E JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG2 DUP6 PUSH1 0x0 NOT AND DUP4 PUSH1 0x0 NOT AND EQ SWAP4 POP POP POP POP SWAP6 SWAP5 POP POP POP POP POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x311 DUP3 CALLDATALOAD PUSH2 0x5E2 JUMP JUMPDEST SWAP1 POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 DUP3 PUSH1 0x1F DUP4 ADD SLT ISZERO ISZERO PUSH2 0x32C JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP2 CALLDATALOAD PUSH2 0x33F PUSH2 0x33A DUP3 PUSH2 0x586 JUMP JUMPDEST PUSH2 0x559 JUMP JUMPDEST SWAP2 POP DUP2 DUP2 DUP4 MSTORE PUSH1 0x20 DUP5 ADD SWAP4 POP PUSH1 0x20 DUP2 ADD SWAP1 POP DUP4 DUP6 PUSH1 0x20 DUP5 MUL DUP3 ADD GT ISZERO PUSH2 0x364 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0x394 JUMPI DUP2 PUSH2 0x37A DUP9 DUP3 PUSH2 0x423 JUMP JUMPDEST DUP5 MSTORE PUSH1 0x20 DUP5 ADD SWAP4 POP PUSH1 0x20 DUP4 ADD SWAP3 POP POP PUSH1 0x1 DUP2 ADD SWAP1 POP PUSH2 0x367 JUMP JUMPDEST POP POP POP POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 DUP3 PUSH1 0x1F DUP4 ADD SLT ISZERO ISZERO PUSH2 0x3B1 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP2 CALLDATALOAD PUSH2 0x3C4 PUSH2 0x3BF DUP3 PUSH2 0x5AE JUMP JUMPDEST PUSH2 0x559 JUMP JUMPDEST SWAP2 POP DUP2 DUP2 DUP4 MSTORE PUSH1 0x20 DUP5 ADD SWAP4 POP PUSH1 0x20 DUP2 ADD SWAP1 POP DUP4 DUP6 PUSH1 0x20 DUP5 MUL DUP3 ADD GT ISZERO PUSH2 0x3E9 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0x419 JUMPI DUP2 PUSH2 0x3FF DUP9 DUP3 PUSH2 0x437 JUMP JUMPDEST DUP5 MSTORE PUSH1 0x20 DUP5 ADD SWAP4 POP PUSH1 0x20 DUP4 ADD SWAP3 POP POP PUSH1 0x1 DUP2 ADD SWAP1 POP PUSH2 0x3EC JUMP JUMPDEST POP POP POP POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x42F DUP3 CALLDATALOAD PUSH2 0x602 JUMP JUMPDEST SWAP1 POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x443 DUP3 CALLDATALOAD PUSH2 0x60E JUMP JUMPDEST SWAP1 POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH2 0x457 DUP3 MLOAD PUSH2 0x60E JUMP JUMPDEST SWAP1 POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 DUP5 SUB SLT ISZERO PUSH2 0x471 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x0 PUSH2 0x47F DUP5 DUP3 DUP6 ADD PUSH2 0x44B JUMP JUMPDEST SWAP2 POP POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x0 DUP1 PUSH1 0x0 PUSH1 0xA0 DUP7 DUP9 SUB SLT ISZERO PUSH2 0x4A0 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x0 PUSH2 0x4AE DUP9 DUP3 DUP10 ADD PUSH2 0x437 JUMP JUMPDEST SWAP6 POP POP PUSH1 0x20 DUP7 ADD CALLDATALOAD PUSH8 0xFFFFFFFFFFFFFFFF DUP2 GT ISZERO PUSH2 0x4CB JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x4D7 DUP9 DUP3 DUP10 ADD PUSH2 0x39E JUMP JUMPDEST SWAP5 POP POP PUSH1 0x40 DUP7 ADD CALLDATALOAD PUSH8 0xFFFFFFFFFFFFFFFF DUP2 GT ISZERO PUSH2 0x4F4 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH2 0x500 DUP9 DUP3 DUP10 ADD PUSH2 0x319 JUMP JUMPDEST SWAP4 POP POP PUSH1 0x60 PUSH2 0x511 DUP9 DUP3 DUP10 ADD PUSH2 0x437 JUMP JUMPDEST SWAP3 POP POP PUSH1 0x80 PUSH2 0x522 DUP9 DUP3 DUP10 ADD PUSH2 0x305 JUMP JUMPDEST SWAP2 POP POP SWAP3 SWAP6 POP SWAP3 SWAP6 SWAP1 SWAP4 POP JUMP JUMPDEST PUSH2 0x538 DUP2 PUSH2 0x5D6 JUMP JUMPDEST DUP3 MSTORE POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 ADD SWAP1 POP PUSH2 0x553 PUSH1 0x0 DUP4 ADD DUP5 PUSH2 0x52F JUMP JUMPDEST SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x40 MLOAD SWAP1 POP DUP2 DUP2 ADD DUP2 DUP2 LT PUSH8 0xFFFFFFFFFFFFFFFF DUP3 GT OR ISZERO PUSH2 0x57C JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST DUP1 PUSH1 0x40 MSTORE POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 PUSH8 0xFFFFFFFFFFFFFFFF DUP3 GT ISZERO PUSH2 0x59D JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x20 DUP3 MUL SWAP1 POP PUSH1 0x20 DUP2 ADD SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 PUSH8 0xFFFFFFFFFFFFFFFF DUP3 GT ISZERO PUSH2 0x5C5 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x20 DUP3 MUL SWAP1 POP PUSH1 0x20 DUP2 ADD SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 DUP2 ISZERO ISZERO SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF DUP3 AND SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 DUP2 ISZERO ISZERO SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST PUSH1 0x0 DUP2 SWAP1 POP SWAP2 SWAP1 POP JUMP STOP LOG2 PUSH6 0x627A7A723058 KECCAK256 GAS PUSH29 0x70B11E0B0FF981F5DC18D4D8120DA1A77EA1C2141C90C0570E62C3F7FD DUP1 PUSH13 0x6578706572696D656E74616CF5 STOP CALLDATACOPY \",\r\n"
            + "  \"sourceMap\": \"104:755:0:-;;;;8:9:-1;5:2;;;30:1;27;20:12;5:2;104:755:0;;;;;;;\"\r\n"
            + "}";

    public static final String FUNC_VERIFYSINGLEPROOF = "verifySingleProof";

    public static final Event VERIFICATIONRESULT_EVENT = new Event("VerificationResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected MerklePDP_Event(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MerklePDP_Event(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MerklePDP_Event(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MerklePDP_Event(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> verifySingleProof(byte[] leaf, List<byte[]> proof, List<Boolean> location, byte[] hash, String _address) {
        final Function function = new Function(
                FUNC_VERIFYSINGLEPROOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(leaf), 
                new DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.datatypes.generated.Bytes32.class,
                        org.web3j.abi.Utils.typeMap(proof, org.web3j.abi.datatypes.generated.Bytes32.class)), 
                new DynamicArray<Bool>(
                        Bool.class,
                        org.web3j.abi.Utils.typeMap(location, Bool.class)),
                new org.web3j.abi.datatypes.generated.Bytes32(hash), 
                new Address(160, _address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<VerificationResultEventResponse> getVerificationResultEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(VERIFICATIONRESULT_EVENT, transactionReceipt);
        ArrayList<VerificationResultEventResponse> responses = new ArrayList<VerificationResultEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            VerificationResultEventResponse typedResponse = new VerificationResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._address = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.success = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VerificationResultEventResponse> verificationResultEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, VerificationResultEventResponse>() {
            @Override
            public VerificationResultEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(VERIFICATIONRESULT_EVENT, log);
                VerificationResultEventResponse typedResponse = new VerificationResultEventResponse();
                typedResponse.log = log;
                typedResponse._address = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.success = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VerificationResultEventResponse> verificationResultEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VERIFICATIONRESULT_EVENT));
        return verificationResultEventFlowable(filter);
    }

    @Deprecated
    public static MerklePDP_Event load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MerklePDP_Event(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MerklePDP_Event load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MerklePDP_Event(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MerklePDP_Event load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MerklePDP_Event(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MerklePDP_Event load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MerklePDP_Event(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MerklePDP_Event> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MerklePDP_Event.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MerklePDP_Event> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MerklePDP_Event.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<MerklePDP_Event> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MerklePDP_Event.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MerklePDP_Event> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MerklePDP_Event.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class VerificationResultEventResponse extends BaseEventResponse {
        public String _address;

        public Boolean success;
    }
}