package com.free.fs.blockchain.Model.Merkle;

public class Node {
    public byte[] data;
    Node left;
    Node right;
    Node parent;

    Node(byte[] data) {
        this.data = data;
    }
}


