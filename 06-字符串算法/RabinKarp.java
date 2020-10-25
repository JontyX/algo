
import java.math.BigInteger;

import java.util.Random;

public class RabinKarp {

    private String pat;
    private long patHash;
    // 模式字符串长度
    private int M;
    // 一个很大的素数
    private long Q;
    // 字母表大小
    private int R = 256;
    // R^(M - 1) % Q
    private long RM;

    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1 ; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }

    // 蒙特卡洛算法
    public boolean check(int i){
        return true;
    }

    private int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash && check(0)) return 0;
        for (int i = M; i < N; i++){
            txtHash = (txtHash + Q - RM*txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash) {
                if (check(i - M + 1)) return i - M + 1;
            }
        }
        return N;
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++){
            h = (R  * h + key.charAt(j)) % Q;
        }
        return h;
    }


    private static long longRandomPrime(){
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
}
