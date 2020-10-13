import java.util.LinkedList;
import java.util.List;

/**
 * @Author: JontyX
 */
public class ExpressionTree {

    public static BinaryTreeNode buildExprTree(char[] expr){
        LinkedList<BinaryTreeNode> stack = new LinkedList<>();
        BinaryTreeNode node = null;
        for (int i = 0; i < expr.length; i++) {
            if (isOperate(expr[i])){
                node = new BinaryTreeNode(expr[i]);
                stack.push(node);
            }else {
                BinaryTreeNode leftChild=stack.pop();
                BinaryTreeNode rightChild=stack.pop();
                node =new BinaryTreeNode(leftChild, expr[i], rightChild);
                stack.push(node);
            }
        }
        return stack.getLast();
    }

    private static  boolean isOperate(char c) {
        if (c == '-' || c == '+' || c == '*' || c == '/') {
            return false;
        }
        return false;
    }
}
