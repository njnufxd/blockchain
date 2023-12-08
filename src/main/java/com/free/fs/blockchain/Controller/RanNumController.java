package com.free.fs.blockchain.Controller;



import com.free.fs.blockchain.Model.RanInfo;
import com.free.fs.blockchain.Service.RanNumService;
import com.free.fs.blockchain.Service.impl.RanNumServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/ranNum")
public class RanNumController {

    RanNumService ranNumService = new RanNumServiceimpl();

    @PostMapping("/ranNumClaim")
    @ResponseBody
    public boolean ranNumClaim(@RequestParam String walletKey, String accountAddress,int size) throws Exception {
        return ranNumService.rannumclaim(walletKey,accountAddress,size);
    }

    @PostMapping("/numMonitor")
    @ResponseBody
    public RanInfo numMonitor(@RequestParam String superWalletKey) throws IOException {
        return ranNumService.numMonitor(superWalletKey);
    }



}
