import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class KMP {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        // 模式串p
        String P = reader.readLine();
        char[] p = P.toCharArray();


        int m = Integer.parseInt(reader.readLine());
        String S = reader.readLine();
        // 总串s
        char[] s = S.toCharArray();

        // 构造前缀数组
        int next[] = new int[n];
        if(n == 1) {
            next[0] = -1;
        }else {
            next[0] = -1;
            next[1] = 0;
            int pos = 2, cn = 0;
            while(pos < n) {
                if(p[pos-1] == p[cn]) next[pos++] = ++cn;
                else if(cn > 0) cn = next[cn];
                else next[pos++] = 0;
            }
        }

        int si = 0, pi = 0;
        while(si < m) {
            if(s[si] == p[pi]){
                si++;
                pi++;
            }else if(next[pi] == -1) {
                si++;
            }else {
                pi = next[pi];
            }

            if(pi == n) {
                writer.write(si - n +" ");
                pi = next[pi-1];
                si--;
            }
        }

        writer.flush();//所有write下的内容，会先存在writers中，当启用flush以后，会输出存在其中的内容。如果没有调用flush，则不会将writer中的内容进行输出。
        writer.close();
        reader.close();
    }

}
