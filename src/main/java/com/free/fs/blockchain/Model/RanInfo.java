package com.free.fs.blockchain.Model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RanInfo {

    private List<BigInteger> uint256List = new ArrayList<>();

    private String address;

    public List<BigInteger> getUint256List() {
        return uint256List;
    }

    public void setUint256List(List<BigInteger> uint256List) {
        this.uint256List = uint256List;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
