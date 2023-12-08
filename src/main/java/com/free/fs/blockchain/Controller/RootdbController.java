package com.free.fs.blockchain.Controller;


import com.free.fs.blockchain.Model.Merkle.MerkleTree;
import com.free.fs.blockchain.Model.RanInfo;
import com.free.fs.blockchain.Service.RootdbService;
import com.free.fs.blockchain.Service.impl.RootdbServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/pdp")
public class RootdbController {

    private RootdbService rootdbService = new RootdbServiceimpl();


    @PostMapping("/confirm")
    @ResponseBody
    public boolean confirmClaim(@RequestParam String superWalletKey, MerkleTree merkleTree, RanInfo ranInfo) throws Exception {
        return rootdbService.infoConfirm(superWalletKey, merkleTree,ranInfo);
    }

    @PostMapping("/result")
    @ResponseBody
    public boolean confirmResult(@RequestParam String walletKey, String address, int blockSize) throws IOException {
       return rootdbService.PDPConfirm(walletKey, address, blockSize);
    }

    @PostMapping("/setroot")
    @ResponseBody
    public boolean setroot(@RequestParam String address,byte[] hash, String walletKey) throws Exception {
        boolean result = rootdbService.setvalue(address,hash,walletKey);
        return result;
    }

}
