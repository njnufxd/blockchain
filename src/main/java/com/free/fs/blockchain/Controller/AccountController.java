package com.free.fs.blockchain.Controller;



import com.free.fs.blockchain.Model.Account.Model.Account;
import com.free.fs.blockchain.Service.AccountService;
import com.free.fs.blockchain.Service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    @ResponseBody
    public Account createAccount(@RequestParam String password) throws Exception {
        Account account = new Account();
        account = accountService.creatAccount(password);
        return account;
    }

    @PostMapping("/checkAccount")
    @ResponseBody
    public boolean checkAccount(@RequestParam String address) throws ExecutionException, InterruptedException {
        accountService.checkBalance(address);
        return true;
    }

}
