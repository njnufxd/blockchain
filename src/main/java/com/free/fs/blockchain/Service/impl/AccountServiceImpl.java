package com.free.fs.blockchain.Service.impl;

import com.free.fs.blockchain.Model.Account.Model.Account;
import com.free.fs.blockchain.Model.Account.Operation.CreateAccount;
import com.free.fs.blockchain.Model.Account.Operation.EtherTransferExample;
import com.free.fs.blockchain.Service.AccountService;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Account creatAccount(String password) throws Exception {
        Account account = new Account();
        CreateAccount createAccount = new CreateAccount();
        account = createAccount.creatAccount(password);
        EtherTransferExample example = new EtherTransferExample();
        example.transETH(account.getAddress());
        return account;
    }

    @Override
    public Boolean checkBalance(String toAddress) throws ExecutionException, InterruptedException {
        Web3j web3 = Web3j.build(new HttpService("http://39.101.122.245:8545"));

        BigInteger weiBalance = web3.ethGetBalance(toAddress, DefaultBlockParameterName.LATEST)
                .sendAsync().get().getBalance();

        BigDecimal etherBalance = Convert.fromWei(weiBalance.toString(), Convert.Unit.ETHER);
        System.out.println("账户余额：" + etherBalance.toPlainString() + " ETH");
        if (etherBalance.toPlainString().compareTo("0.2") < 0)
        {
            EtherTransferExample example = new EtherTransferExample();
            example.transETH(toAddress);
            return true;
        }
        return false;
    }
}
