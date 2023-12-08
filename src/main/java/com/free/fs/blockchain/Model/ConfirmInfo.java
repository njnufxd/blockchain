package com.free.fs.blockchain.Model;

import java.util.ArrayList;
import java.util.List;

public class ConfirmInfo {
    byte[] leafNode;
    List<byte[]> proofs = new ArrayList<>();
    List<Boolean> locations = new ArrayList<>();

    public byte[] getLeafNode() {
        return leafNode;
    }

    public void setLeafNode(byte[] leafNode) {
        this.leafNode = leafNode;
    }

    public List<byte[]> getProofs() {
        return proofs;
    }

    public void setProofs(List<byte[]> proofs) {
        this.proofs = proofs;
    }

    public List<Boolean> getLocations() {
        return locations;
    }

    public void setLocations(List<Boolean> locations) {
        this.locations = locations;
    }
}
