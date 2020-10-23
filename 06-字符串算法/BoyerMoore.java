import java.util.Arrays;

public class BoyerMoore {

    private int[] right;
    private String pat;
    BoyerMoore(String pat) {
        this.pat = pat;
        int M = pat.length();

        int R = 256;
        right = new int[R];
        // 不包含在模式字符串中的字符值为-1
        Arrays.fill(right, -1);
        for (int i = 0; i < M; i++) {
            // 包含在模式字符串中的字符的值为出现的最右位置
            right[pat.charAt(i)] = i;
        }
    }

    public int search(String txt) {
        int N = txt.length();
        int M = txt.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)){
                    skip = j - right[txt.charAt(i + j)];
                }
            }
            if (skip == 0) return i;
        }
        return N;
    }
}
