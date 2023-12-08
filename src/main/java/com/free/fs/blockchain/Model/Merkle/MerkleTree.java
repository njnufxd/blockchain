package com.free.fs.blockchain.Model.Merkle;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MerkleTree {

    private Node root;
    public Node getRoot() {
        return root;
    }
    private MessageDigest digest;
    List<byte[]> leavesNodes = new ArrayList<>();

    public byte[] numtoPath(int block) throws NoSuchAlgorithmException {
        return leavesNodes.get(block);
    }

    // 构造Merkle树
    public void buildTree(List<byte[]> leafNodes) throws NoSuchAlgorithmException {
        this.leavesNodes = leafNodes;
        digest = MessageDigest.getInstance("SHA-256");
        List<Node> nodes = new ArrayList<>();
        for (byte[] leaf : leafNodes) {
            Node node = new Node(leaf);
            nodes.add(node);
        }

        while (nodes.size() > 1) {
            List<Node> temp = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i += 2) {
                Node left = nodes.get(i);
                Node right = (i + 1 < nodes.size()) ? nodes.get(i + 1) : null;
                Node parent = new Node(combineHash(left.data, (right != null) ? right.data : null));
                parent.left = left;
                parent.right = right;
                left.parent = parent;
                if (right != null) {
                    right.parent = parent;
                }
                temp.add(parent);
            }
            nodes = temp;
        }
        root = nodes.get(0);
    }
    public List<PathNode> getVerificationPath(byte[] leafNode) throws NoSuchAlgorithmException {
        Node node = findLeaf(root, leafNode);
        if (node == null) {
            return null;
        }
        /*List<byte[]> path = new ArrayList<>();

        while (node != root) {
            Node sibling = (node == node.parent.left) ? node.parent.right : node.parent.left;
            path.add(sibling.data);
            node = node.parent;
        }*/
        List<PathNode> path = new ArrayList<>();
        while (node !=root)
        {
            Node sibling = (node == node.parent.left) ? node.parent.right : node.parent.left;
            PathNode pathNode = new PathNode();
            pathNode.data = sibling.data;
            pathNode.isleft = node == node.parent.left;
            path.add(pathNode);
            node = node.parent;
        }
        return path;

    }
    public void insert(byte[] newData) throws NoSuchAlgorithmException {
        Node newNode = new Node(newData);
        leavesNodes.add(newNode.data);
        if (root == null) {
            root = newNode;
        } else {
            Node node = findInsertPosition(root);
            if (node.left == null) {
                node.left = newNode;
            } else {
                node.right = newNode;
            }
            newNode.parent = node;
            updatePathHash(node);
        }
    }

    public void delete(byte[] data) throws NoSuchAlgorithmException {
        Node node = findLeaf(root, data);
        if (node == null) {
            return; // 节点不存在，无需删除
        }
        for (int i = 0;i<leavesNodes.size();i++)
            if (Arrays.equals(leavesNodes.get(i),node.data))
            {
                leavesNodes.set(i, null);
                break;
            }
        if (node.parent == null) {
            root = null; // 删除根节点
        } else {
            Node parent = node.parent;
            Node sibling = (node == parent.left) ? parent.right : parent.left;
            if (parent.parent != null) {
                if (parent == parent.parent.left) {
                    parent.parent.left = sibling;
                } else {
                    parent.parent.right = sibling;
                }
                sibling.parent = parent.parent;
            } else {
                root = sibling;
                sibling.parent = null;
            }
            updatePathHash(parent.parent);
        }
    }

    public void update(byte[] oldData, byte[] newData) throws NoSuchAlgorithmException {
        Node node = findLeaf(root, oldData);
        if (node == null) {
            return; // 节点不存在，无法更新
        }
        for (int i = 0;i<leavesNodes.size();i++)
            if (Arrays.equals(leavesNodes.get(i),oldData))
            {
                leavesNodes.set(i, newData);
                break;
            }
        node.data = newData;
        updatePathHash(node.parent);
    }

    // 查找叶子节点
    private Node findLeaf(Node node, byte[] data) {
        if (node == null) {
            return null;
        }
        if (isEqual(node.data, data)) {
            return node;
        }
        Node left = findLeaf(node.left, data);
        if (left != null) {
            return left;
        }
        return findLeaf(node.right, data);
    }

    // 找到插入位置
    private Node findInsertPosition(Node node) {
        if (node.left == null || node.right == null) {
            return node;
        }
        int leftHeight = getTreeHeight(node.left);
        int rightHeight = getTreeHeight(node.right);

        if (leftHeight <= rightHeight) {
            return findInsertPosition(node.left);
        } else {
            return findInsertPosition(node.right);
        }
    }

    // 更新路径上的哈希值
    private void updatePathHash(Node node) throws NoSuchAlgorithmException {
        if (node == null) {
            return;
        }
        node.data = combineHash(node.left.data, (node.right != null) ? node.right.data : null);
        updatePathHash(node.parent);
    }

    // 获取树的高度
    private int getTreeHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getTreeHeight(node.left);
        int rightHeight = getTreeHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 合并哈希值
    public byte[] combineHash(byte[] hash1, byte[] hash2) throws NoSuchAlgorithmException {
        if (hash2 == null) {
            return hash1;
        }
        digest.reset();
        byte[] combined = new byte[hash1.length + hash2.length];
        System.arraycopy(hash1, 0, combined, 0, hash1.length);
        System.arraycopy(hash2, 0, combined, hash1.length, hash2.length);
        return digest.digest(combined);
    }

    // 判断两个字节数组是否相等
    private boolean isEqual(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] getSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}