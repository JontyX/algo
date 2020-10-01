import java.util.Deque;
import java.util.LinkedList;



public class TreeTraversal {

    public static void preorder(BinaryTreeNode root){
        Deque<BinaryTreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BinaryTreeNode node = stack.pop();
            System.out.println(node.data);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    public static void inorder(BinaryTreeNode root){
        Deque<BinaryTreeNode> stack = new LinkedList<>();
        BinaryTreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null){
                stack.push(node);
                node = node.left;
            }else{
                BinaryTreeNode pop = stack.pop();
                System.out.print(pop.data + " ");
                node = pop.right;
            }
        }
    }

    public static void postorder(BinaryTreeNode root) {
        Deque<BinaryTreeNode> stack1 = new LinkedList<>();
        Deque<BinaryTreeNode> stack2 = new LinkedList<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            BinaryTreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null)
                stack1.push(node.left);
            if (node.right != null)
                stack1.push(node.right);
        }
        while (!stack2.isEmpty()){
            System.out.print(stack2.pop().data + " ");
        }
    }

    public static void preMorris(BinaryTreeNode root) {

    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        postorder(root);
    }
}
