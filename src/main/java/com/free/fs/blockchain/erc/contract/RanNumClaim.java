package com.free.fs.blockchain.erc.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class RanNumClaim extends Contract {
    public static final String BINARY = "{\r\n"
            + "  \"linkReferences\": {},\r\n"
            + "  \"object\": \"608060405234801561001057600080fd5b50610731806100206000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063291a3ff41461005c5780635257cd90146100ec57806381c7d9b91461012d575b600080fd5b34801561006857600080fd5b50610071610219565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100b1578082015181840152602081019050610096565b50505050905090810190601f1680156100de5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156100f857600080fd5b50610117600480360381019080803590602001909291905050506102b7565b6040518082815260200191505060405180910390f35b34801561013957600080fd5b5061019e60048036038101908080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506102da565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101de5780820151818401526020810190506101c3565b50505050905090810190601f16801561020b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102af5780601f10610284576101008083540402835291602001916102af565b820191906000526020600020905b81548152906001019060200180831161029257829003601f168201915b505050505081565b6000818154811015156102c657fe5b906000526020600020016000915090505481565b60606000606060008060008711151561035b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600d8152602001807f496e76616c696420696e7075740000000000000000000000000000000000000081525060200191505060405180910390fd5b61036486610626565b9350856001908051906020019061037c929190610634565b506000808161038b91906106b4565b508687026040519080825280602002602001820160405280156103bd5781602001602082028038833980820191505090505b509250600091505b86821015610501576001878802834260405160200180838152602001828152602001925050506040516020818303038152906040526040518082805190602001908083835b60208310151561042f578051825260208201915060208101905060208303925061040a565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206001900481151561046957fe5b060190505b6000838281518110151561047e57fe5b906020019060200201511415156104aa576001878802600183018115156104a157fe5b0601905061046e565b6000819080600181540180825580915050906001820390600052602060002001600090919290919091505550600183828151811015156104e657fe5b906020019060200201818152505081806001019250506103c5565b7f46058d05862434d4f09b911741ae1c9c99f37dfd3c78eafbdaaedc793cd7415d600087604051808060200180602001838103835285818154815260200191508054801561056e57602002820191906000526020600020905b81548152602001906001019080831161055a575b5050838103825284818151815260200191508051906020019080838360005b838110156105a857808201518184015260208101905061058d565b50505050905090810190601f1680156105d55780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a16040805190810160405280600d81526020017f4576656e7420737563636573730000000000000000000000000000000000000081525094505050505092915050565b600060208201519050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061067557805160ff19168380011785556106a3565b828001600101855582156106a3579182015b828111156106a2578251825591602001919060010190610687565b5b5090506106b091906106e0565b5090565b8154818355818111156106db578183600052602060002091820191016106da91906106e0565b5b505050565b61070291905b808211156106fe5760008160009055506001016106e6565b5090565b905600a165627a7a723058200050c94089953acb53fbf084ac2742bc1f1cfd3bc4103494e8f9b55b29124b920029\",\r\n"
            + "  \"opcodes\": \"PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH2 0x10 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x731 DUP1 PUSH2 0x20 PUSH1 0x0 CODECOPY PUSH1 0x0 RETURN STOP PUSH1 0x80 PUSH1 0x40 MSTORE PUSH1 0x4 CALLDATASIZE LT PUSH2 0x57 JUMPI PUSH1 0x0 CALLDATALOAD PUSH29 0x100000000000000000000000000000000000000000000000000000000 SWAP1 DIV PUSH4 0xFFFFFFFF AND DUP1 PUSH4 0x291A3FF4 EQ PUSH2 0x5C JUMPI DUP1 PUSH4 0x5257CD90 EQ PUSH2 0xEC JUMPI DUP1 PUSH4 0x81C7D9B9 EQ PUSH2 0x12D JUMPI JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x68 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x71 PUSH2 0x219 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP1 PUSH1 0x20 ADD DUP3 DUP2 SUB DUP3 MSTORE DUP4 DUP2 DUP2 MLOAD DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP4 DUP4 PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0xB1 JUMPI DUP1 DUP3 ADD MLOAD DUP2 DUP5 ADD MSTORE PUSH1 0x20 DUP2 ADD SWAP1 POP PUSH2 0x96 JUMP JUMPDEST POP POP POP POP SWAP1 POP SWAP1 DUP2 ADD SWAP1 PUSH1 0x1F AND DUP1 ISZERO PUSH2 0xDE JUMPI DUP1 DUP3 SUB DUP1 MLOAD PUSH1 0x1 DUP4 PUSH1 0x20 SUB PUSH2 0x100 EXP SUB NOT AND DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP JUMPDEST POP SWAP3 POP POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0xF8 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x117 PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 DUP1 DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 POP POP POP PUSH2 0x2B7 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x139 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x19E PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 DUP1 DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP3 ADD DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP1 PUSH1 0x1F ADD PUSH1 0x20 DUP1 SWAP2 DIV MUL PUSH1 0x20 ADD PUSH1 0x40 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 SWAP4 SWAP3 SWAP2 SWAP1 DUP2 DUP2 MSTORE PUSH1 0x20 ADD DUP4 DUP4 DUP1 DUP3 DUP5 CALLDATACOPY DUP3 ADD SWAP2 POP POP POP POP POP POP SWAP2 SWAP3 SWAP2 SWAP3 SWAP1 POP POP POP PUSH2 0x2DA JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP1 PUSH1 0x20 ADD DUP3 DUP2 SUB DUP3 MSTORE DUP4 DUP2 DUP2 MLOAD DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP4 DUP4 PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0x1DE JUMPI DUP1 DUP3 ADD MLOAD DUP2 DUP5 ADD MSTORE PUSH1 0x20 DUP2 ADD SWAP1 POP PUSH2 0x1C3 JUMP JUMPDEST POP POP POP POP SWAP1 POP SWAP1 DUP2 ADD SWAP1 PUSH1 0x1F AND DUP1 ISZERO PUSH2 0x20B JUMPI DUP1 DUP3 SUB DUP1 MLOAD PUSH1 0x1 DUP4 PUSH1 0x20 SUB PUSH2 0x100 EXP SUB NOT AND DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP JUMPDEST POP SWAP3 POP POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST PUSH1 0x1 DUP1 SLOAD PUSH1 0x1 DUP2 PUSH1 0x1 AND ISZERO PUSH2 0x100 MUL SUB AND PUSH1 0x2 SWAP1 DIV DUP1 PUSH1 0x1F ADD PUSH1 0x20 DUP1 SWAP2 DIV MUL PUSH1 0x20 ADD PUSH1 0x40 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 SWAP3 SWAP2 SWAP1 DUP2 DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP1 SLOAD PUSH1 0x1 DUP2 PUSH1 0x1 AND ISZERO PUSH2 0x100 MUL SUB AND PUSH1 0x2 SWAP1 DIV DUP1 ISZERO PUSH2 0x2AF JUMPI DUP1 PUSH1 0x1F LT PUSH2 0x284 JUMPI PUSH2 0x100 DUP1 DUP4 SLOAD DIV MUL DUP4 MSTORE SWAP2 PUSH1 0x20 ADD SWAP2 PUSH2 0x2AF JUMP JUMPDEST DUP3 ADD SWAP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 JUMPDEST DUP2 SLOAD DUP2 MSTORE SWAP1 PUSH1 0x1 ADD SWAP1 PUSH1 0x20 ADD DUP1 DUP4 GT PUSH2 0x292 JUMPI DUP3 SWAP1 SUB PUSH1 0x1F AND DUP3 ADD SWAP2 JUMPDEST POP POP POP POP POP DUP2 JUMP JUMPDEST PUSH1 0x0 DUP2 DUP2 SLOAD DUP2 LT ISZERO ISZERO PUSH2 0x2C6 JUMPI INVALID JUMPDEST SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 ADD PUSH1 0x0 SWAP2 POP SWAP1 POP SLOAD DUP2 JUMP JUMPDEST PUSH1 0x60 PUSH1 0x0 PUSH1 0x60 PUSH1 0x0 DUP1 PUSH1 0x0 DUP8 GT ISZERO ISZERO PUSH2 0x35B JUMPI PUSH1 0x40 MLOAD PUSH32 0x8C379A000000000000000000000000000000000000000000000000000000000 DUP2 MSTORE PUSH1 0x4 ADD DUP1 DUP1 PUSH1 0x20 ADD DUP3 DUP2 SUB DUP3 MSTORE PUSH1 0xD DUP2 MSTORE PUSH1 0x20 ADD DUP1 PUSH32 0x496E76616C696420696E70757400000000000000000000000000000000000000 DUP2 MSTORE POP PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 REVERT JUMPDEST PUSH2 0x364 DUP7 PUSH2 0x626 JUMP JUMPDEST SWAP4 POP DUP6 PUSH1 0x1 SWAP1 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 PUSH2 0x37C SWAP3 SWAP2 SWAP1 PUSH2 0x634 JUMP JUMPDEST POP PUSH1 0x0 DUP1 DUP2 PUSH2 0x38B SWAP2 SWAP1 PUSH2 0x6B4 JUMP JUMPDEST POP DUP7 DUP8 MUL PUSH1 0x40 MLOAD SWAP1 DUP1 DUP3 MSTORE DUP1 PUSH1 0x20 MUL PUSH1 0x20 ADD DUP3 ADD PUSH1 0x40 MSTORE DUP1 ISZERO PUSH2 0x3BD JUMPI DUP2 PUSH1 0x20 ADD PUSH1 0x20 DUP3 MUL DUP1 CODESIZE DUP4 CODECOPY DUP1 DUP3 ADD SWAP2 POP POP SWAP1 POP JUMPDEST POP SWAP3 POP PUSH1 0x0 SWAP2 POP JUMPDEST DUP7 DUP3 LT ISZERO PUSH2 0x501 JUMPI PUSH1 0x1 DUP8 DUP9 MUL DUP4 TIMESTAMP PUSH1 0x40 MLOAD PUSH1 0x20 ADD DUP1 DUP4 DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP3 POP POP POP PUSH1 0x40 MLOAD PUSH1 0x20 DUP2 DUP4 SUB SUB DUP2 MSTORE SWAP1 PUSH1 0x40 MSTORE PUSH1 0x40 MLOAD DUP1 DUP3 DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP4 DUP4 JUMPDEST PUSH1 0x20 DUP4 LT ISZERO ISZERO PUSH2 0x42F JUMPI DUP1 MLOAD DUP3 MSTORE PUSH1 0x20 DUP3 ADD SWAP2 POP PUSH1 0x20 DUP2 ADD SWAP1 POP PUSH1 0x20 DUP4 SUB SWAP3 POP PUSH2 0x40A JUMP JUMPDEST PUSH1 0x1 DUP4 PUSH1 0x20 SUB PUSH2 0x100 EXP SUB DUP1 NOT DUP3 MLOAD AND DUP2 DUP5 MLOAD AND DUP1 DUP3 OR DUP6 MSTORE POP POP POP POP POP POP SWAP1 POP ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 KECCAK256 PUSH1 0x1 SWAP1 DIV DUP2 ISZERO ISZERO PUSH2 0x469 JUMPI INVALID JUMPDEST MOD ADD SWAP1 POP JUMPDEST PUSH1 0x0 DUP4 DUP3 DUP2 MLOAD DUP2 LT ISZERO ISZERO PUSH2 0x47E JUMPI INVALID JUMPDEST SWAP1 PUSH1 0x20 ADD SWAP1 PUSH1 0x20 MUL ADD MLOAD EQ ISZERO ISZERO PUSH2 0x4AA JUMPI PUSH1 0x1 DUP8 DUP9 MUL PUSH1 0x1 DUP4 ADD DUP2 ISZERO ISZERO PUSH2 0x4A1 JUMPI INVALID JUMPDEST MOD ADD SWAP1 POP PUSH2 0x46E JUMP JUMPDEST PUSH1 0x0 DUP2 SWAP1 DUP1 PUSH1 0x1 DUP2 SLOAD ADD DUP1 DUP3 SSTORE DUP1 SWAP2 POP POP SWAP1 PUSH1 0x1 DUP3 SUB SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 ADD PUSH1 0x0 SWAP1 SWAP2 SWAP3 SWAP1 SWAP2 SWAP1 SWAP2 POP SSTORE POP PUSH1 0x1 DUP4 DUP3 DUP2 MLOAD DUP2 LT ISZERO ISZERO PUSH2 0x4E6 JUMPI INVALID JUMPDEST SWAP1 PUSH1 0x20 ADD SWAP1 PUSH1 0x20 MUL ADD DUP2 DUP2 MSTORE POP POP DUP2 DUP1 PUSH1 0x1 ADD SWAP3 POP POP PUSH2 0x3C5 JUMP JUMPDEST PUSH32 0x46058D05862434D4F09B911741AE1C9C99F37DFD3C78EAFBDAAEDC793CD7415D PUSH1 0x0 DUP8 PUSH1 0x40 MLOAD DUP1 DUP1 PUSH1 0x20 ADD DUP1 PUSH1 0x20 ADD DUP4 DUP2 SUB DUP4 MSTORE DUP6 DUP2 DUP2 SLOAD DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP DUP1 SLOAD DUP1 ISZERO PUSH2 0x56E JUMPI PUSH1 0x20 MUL DUP3 ADD SWAP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 JUMPDEST DUP2 SLOAD DUP2 MSTORE PUSH1 0x20 ADD SWAP1 PUSH1 0x1 ADD SWAP1 DUP1 DUP4 GT PUSH2 0x55A JUMPI JUMPDEST POP POP DUP4 DUP2 SUB DUP3 MSTORE DUP5 DUP2 DUP2 MLOAD DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP DUP1 MLOAD SWAP1 PUSH1 0x20 ADD SWAP1 DUP1 DUP4 DUP4 PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0x5A8 JUMPI DUP1 DUP3 ADD MLOAD DUP2 DUP5 ADD MSTORE PUSH1 0x20 DUP2 ADD SWAP1 POP PUSH2 0x58D JUMP JUMPDEST POP POP POP POP SWAP1 POP SWAP1 DUP2 ADD SWAP1 PUSH1 0x1F AND DUP1 ISZERO PUSH2 0x5D5 JUMPI DUP1 DUP3 SUB DUP1 MLOAD PUSH1 0x1 DUP4 PUSH1 0x20 SUB PUSH2 0x100 EXP SUB NOT AND DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP JUMPDEST POP SWAP5 POP POP POP POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG1 PUSH1 0x40 DUP1 MLOAD SWAP1 DUP2 ADD PUSH1 0x40 MSTORE DUP1 PUSH1 0xD DUP2 MSTORE PUSH1 0x20 ADD PUSH32 0x4576656E74207375636365737300000000000000000000000000000000000000 DUP2 MSTORE POP SWAP5 POP POP POP POP POP SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x0 PUSH1 0x20 DUP3 ADD MLOAD SWAP1 POP SWAP2 SWAP1 POP JUMP JUMPDEST DUP3 DUP1 SLOAD PUSH1 0x1 DUP2 PUSH1 0x1 AND ISZERO PUSH2 0x100 MUL SUB AND PUSH1 0x2 SWAP1 DIV SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 PUSH1 0x1F ADD PUSH1 0x20 SWAP1 DIV DUP2 ADD SWAP3 DUP3 PUSH1 0x1F LT PUSH2 0x675 JUMPI DUP1 MLOAD PUSH1 0xFF NOT AND DUP4 DUP1 ADD OR DUP6 SSTORE PUSH2 0x6A3 JUMP JUMPDEST DUP3 DUP1 ADD PUSH1 0x1 ADD DUP6 SSTORE DUP3 ISZERO PUSH2 0x6A3 JUMPI SWAP2 DUP3 ADD JUMPDEST DUP3 DUP2 GT ISZERO PUSH2 0x6A2 JUMPI DUP3 MLOAD DUP3 SSTORE SWAP2 PUSH1 0x20 ADD SWAP2 SWAP1 PUSH1 0x1 ADD SWAP1 PUSH2 0x687 JUMP JUMPDEST JUMPDEST POP SWAP1 POP PUSH2 0x6B0 SWAP2 SWAP1 PUSH2 0x6E0 JUMP JUMPDEST POP SWAP1 JUMP JUMPDEST DUP2 SLOAD DUP2 DUP4 SSTORE DUP2 DUP2 GT ISZERO PUSH2 0x6DB JUMPI DUP2 DUP4 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP2 DUP3 ADD SWAP2 ADD PUSH2 0x6DA SWAP2 SWAP1 PUSH2 0x6E0 JUMP JUMPDEST JUMPDEST POP POP POP JUMP JUMPDEST PUSH2 0x702 SWAP2 SWAP1 JUMPDEST DUP1 DUP3 GT ISZERO PUSH2 0x6FE JUMPI PUSH1 0x0 DUP2 PUSH1 0x0 SWAP1 SSTORE POP PUSH1 0x1 ADD PUSH2 0x6E6 JUMP JUMPDEST POP SWAP1 JUMP JUMPDEST SWAP1 JUMP STOP LOG1 PUSH6 0x627A7A723058 KECCAK256 STOP POP 0xc9 BLOCKHASH DUP10 SWAP6 GASPRICE 0xcb MSTORE8 CREATE2 CREATE DUP5 0xac 0x27 TIMESTAMP 0xbc 0x1f SHR REVERT EXTCODESIZE 0xc4 LT CALLVALUE SWAP5 0xe8 0xf9 0xb5 JUMPDEST 0x29 SLT 0x4b SWAP3 STOP 0x29 \",\r\n"
            + "  \"sourceMap\": \"61:1242:0:-;;;;8:9:-1;5:2;;;30:1;27;20:12;5:2;61:1242:0;;;;;;;\"\r\n"
            + "}";

    public static final String FUNC_GENERATERANDOMNUMBERS = "generateRandomNumbers";

    public static final String FUNC_INPUTSTRING = "inputString";

    public static final String FUNC_RANDOMNUMBERS = "randomNumbers";

    public static final Event RANDOMNUMBERSGENERATED_EVENT = new Event("RandomNumbersGenerated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected RanNumClaim(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RanNumClaim(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RanNumClaim(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RanNumClaim(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> generateRandomNumbers(BigInteger n, String input) {
        final Function function = new Function(
                FUNC_GENERATERANDOMNUMBERS, 
                Arrays.<Type>asList(new Uint256(n),
                new Utf8String(input)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<RandomNumbersGeneratedEventResponse> getRandomNumbersGeneratedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(RANDOMNUMBERSGENERATED_EVENT, transactionReceipt);
        ArrayList<RandomNumbersGeneratedEventResponse> responses = new ArrayList<RandomNumbersGeneratedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RandomNumbersGeneratedEventResponse typedResponse = new RandomNumbersGeneratedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.numbers = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.input = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RandomNumbersGeneratedEventResponse> randomNumbersGeneratedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, RandomNumbersGeneratedEventResponse>() {
            @Override
            public RandomNumbersGeneratedEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(RANDOMNUMBERSGENERATED_EVENT, log);
                RandomNumbersGeneratedEventResponse typedResponse = new RandomNumbersGeneratedEventResponse();
                typedResponse.log = log;
                typedResponse.numbers = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.input = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RandomNumbersGeneratedEventResponse> randomNumbersGeneratedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RANDOMNUMBERSGENERATED_EVENT));
        return randomNumbersGeneratedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> inputString() {
        final Function function = new Function(FUNC_INPUTSTRING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> randomNumbers(BigInteger param0) {
        final Function function = new Function(FUNC_RANDOMNUMBERS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static RanNumClaim load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RanNumClaim(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RanNumClaim load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RanNumClaim(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RanNumClaim load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RanNumClaim(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RanNumClaim load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RanNumClaim(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RanNumClaim> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RanNumClaim.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RanNumClaim> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RanNumClaim.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<RanNumClaim> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RanNumClaim.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RanNumClaim> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RanNumClaim.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RandomNumbersGeneratedEventResponse extends BaseEventResponse {
        public List<BigInteger> numbers;

        public String input;
    }
}
