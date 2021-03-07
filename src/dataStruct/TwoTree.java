package valStruct;

import java.util.*;

public class TwoTree {
    /**
     * 创建二叉树
     * @param inputList 输入序列
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()){
            return null;
        }
        Integer date = inputList.removeFirst();
        if (date != null){
            node = new TreeNode(date);
            node.left = createBinaryTree(inputList);
            node.right = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 前序遍历
     * @param node 二叉树节点
     */
    static void preOrderTraveral(TreeNode node){
        if (node == null) return;
        //根左右
        System.out.println(node.val);
        preOrderTraveral(node.left);
        preOrderTraveral(node.right);
    }

    /**
     * 中序遍历
     * @param node 二叉树节点
     */
    static void inOrderTreveral(TreeNode node){
        if (node == null) return;
        //左根右
        inOrderTreveral(node.left);
        System.out.println(node.val);
        inOrderTreveral(node.right);
    }

    /**
     * 后序遍历
     * @param node 二叉树节点
     */
    static void postOrderTreveral(TreeNode node){
        if (node == null) return;
        //左右根
        postOrderTreveral(node.left);
        postOrderTreveral(node.right);
        System.out.println(node.val);
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[]{
                3,2,9,null,null,10,null,null,8,null,4
        }));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("前序遍历");
        preOrderTraveral(treeNode);
        System.out.println("中序遍历");
        inOrderTreveral(treeNode);
        System.out.println("后序遍历");
        postOrderTreveral(treeNode);
    }

    /**
     * 前序遍历
     * @param head 二叉树节点
     */
    public void preOrderTraveral2(TreeNode head) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(head);
        while (stack != null && !stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if(node.left == null && node.right == null) continue;
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    /**
     * 广度优先遍历
     * @param node 二叉树节点
     */
    public static void BfsTraveral(TreeNode node) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.push(node);
        while (queue != null && !queue.isEmpty()) {
            TreeNode root = queue.pop();
            System.out.println(root.val);
            if (root.left != null) queue.add(root.left);
            if (root.right != null) queue.add(root.right);
        }
    }
}
/**
 * 二叉树节点
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
