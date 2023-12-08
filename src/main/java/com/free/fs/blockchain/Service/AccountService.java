package com.free.fs.blockchain.Service;

import com.free.fs.blockchain.Model.Account.Model.Account;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface AccountService {

    Account creatAccount(String password) throws Exception;

    Boolean checkBalance(String toAddress) throws ExecutionException, InterruptedException;
}
