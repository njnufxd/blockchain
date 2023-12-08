package com.free.fs.blockchain.Controller;

import com.free.fs.blockchain.Model.Merkle.MerkleTree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/merkle")
public class MerkleController {

    private MerkleTree merkleTree = new MerkleTree();

    @PostMapping("/buildTree")
    @ResponseBody
    public MerkleTree buildTree(@RequestParam List<byte[]> leavesNode) throws NoSuchAlgorithmException {
        merkleTree.buildTree(leavesNode);
        return merkleTree;
    }

}

