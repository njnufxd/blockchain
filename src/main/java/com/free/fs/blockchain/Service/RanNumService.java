package com.free.fs.blockchain.Service;

import com.free.fs.blockchain.Model.RanInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public interface RanNumService {

    boolean rannumclaim(String privatekey,String accountAddress,int size) throws Exception;

    RanInfo numMonitor(String walletKey) throws IOException;


}
