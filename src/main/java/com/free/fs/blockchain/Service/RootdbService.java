package com.free.fs.blockchain.Service;

import com.free.fs.blockchain.Model.Merkle.MerkleTree;
import com.free.fs.blockchain.Model.RanInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface RootdbService {

    boolean setvalue(String address,byte[] hash,String privatekey) throws Exception;
    boolean PDPConfirm(String walletkey,String address,int blockSize) throws IOException;

    boolean infoConfirm(String walletKey, MerkleTree merkleTree, RanInfo ranInfo) throws Exception;
}
