import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/**
 * @Author: JontyX
 */
public class PolishNotation {


    /**
     * 中缀表达式转后缀表达式思路
     * 1. 数字进入输出队列
     * 2. 运算符t
     *      a. 从栈顶开始, 弹出优先级大于等于当前运算符的运算符进入输出队列
     *      b. t 进栈
     * 3. 左括号压入栈
     * 4. 右括号, 将靠近栈顶的第一个左括号上面的运算符弹出, 删除左括号
     * 5. 栈中剩余运算符进入输出队列
     * @param infix
     * @return
     */
    public static List infixToSuffix(String infix) {
        LinkedList<Character> stack = new LinkedList<>();
        List<Character> res = new ArrayList<>();
        int n = infix.length();
        for (int i = 0; i < n; i++) {
            Character temp = null;
            char c = infix.charAt(i);
            switch (c) {
                case ' ':
                    break;
                case '(':{
                    stack.push(c);
                    break;
                }
                case '+':
                case '-':{
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if (temp == '(') {
                            stack.push('(');
                            break;
                        }
                        res.add(temp);
                    }
                    stack.push(c);
                    break;
                }
                case '*':
                case '/':{
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if (temp == '(' || temp == '+' || temp == '-'){
                            stack.push(temp);
                            break;
                        }else {
                            res.add(temp);
                        }
                    }
                    stack.push(c);
                    break;
                }
                case ')':{
                    while (stack.size() != 0){
                        temp = stack.pop();
                        if (temp == '('){
                            break;
                        }else {
                            res.add(temp);
                        }
                    }
                    break;
                }
                default:
                    res.add(c);
            }
        }
        while (stack.size() != 0){
            res.add(stack.pop());
        }
        return res;
    }


    public static double inffixToArithmetic(String expr){
        List<Character> list = infixToSuffix(expr);
        LinkedList<Double> res = new LinkedList<>();
        for (char c : list) {
            if (c >= '0' && c <= '9')
                res.add(Double.valueOf(c - '0'));
            else{
                Double v1 = res.removeLast();
                Double v2 = res.removeLast();
                res.add(getv(c, v1, v2));
            }
        }
        return res.pop();
    }

    public static Double getv(char op, Double v1, Double v2) {

        switch (op){
            case '+':{
                return v1 + v2;
            }
            case '-':{
                return v2 - v1;
            }
            case '*':{
                return v1 * v2;
            }
            case '/':{
                return v2 / v1;
            }
            default:
                return Double.valueOf(0);
        }
    }

    public static void main(String[] args) {
        System.out.println(inffixToArithmetic("(1+2)/(2-0)*2"));
    }
}
