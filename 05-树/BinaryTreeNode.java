
public class BinaryTreeNode {

    public int data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(){

    }

    public BinaryTreeNode(int data){
        this.data = data;
    }

    public BinaryTreeNode(BinaryTreeNode left, int data, BinaryTreeNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
