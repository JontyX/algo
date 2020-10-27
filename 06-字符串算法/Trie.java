import java.util.Scanner;

/**
 * 数组模拟Trie
 */
public class Trie {


    private static int N = 10010;                 // N 支持插入的最大长度
    private static int[] k =new int[N];           //以该节点结尾的字符串的个数
    private static int[][] c = new int[N][26];    //c['a']['b']:a节点的下一个b节点
    private static int idx = 1;                   //节点地址,0位置为空节点所以从1开始


    public static void insert(char[] arr) {
        int p = 0;
        for(int i = 0; i < arr.length; i++){
            int s = arr[i] - '0';
            if (c[p][s] == 0) c[p][s] = idx++;  //p的下一个s节点为空，就赋值地址
            // p 指向下一个节点
            p = c[p][s];
        }
        k[p]++;
    }

    public static int query(char[] arr) {
        int p = 0;
        for(int i = 0; i < arr.length; i++) {
            int s = arr[i] - '0';
            if (c[p][s] == 0) return 0;
            p = c[p][s];
        }
        return k[p];
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while(n-- > 0){
            String opt = scanner.next();
            String str = scanner.next();
            if("I".equals(opt)){
                insert(str.toCharArray());
            }else if("Q".equals(opt)){
                System.out.println(query(str.toCharArray()));
            }
        }
    }
}
