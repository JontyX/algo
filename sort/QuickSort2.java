import java.util.Arrays;
import java.util.Stack;


public class QuickSort2 {


    public void quickSort2(int[] a) {
        if (a == null)
            return;
        qSort2(a, 0, a.length - 1);
    }
    /**
     * 非递归
     */
    public void qSort2(int[] a, int low, int high) {
        if (low >= high)
            return;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(low);
        stack.push(high);
        while (!stack.empty()) {
            // 先弹出high,再弹出low
            high = stack.pop();
            low = stack.pop();
            int[] pivot = partition(a, low, high);
            // 先压low,再压high
            if (low < pivot[0] - 1) {
                stack.push(low);
                stack.push(pivot[0] - 1);
            }
            if (pivot[1] + 1 < high) {
                stack.push(pivot[1] + 1);
                stack.push(high);
            }
        }
    }

    private  int[] partition(int[] arr, int l, int r) {
        int less = l-1;
        int more = r+1 ;
        int x = arr[r];
        while (l < more){
            if (arr[l] < x){
                swap(arr, ++less, l++);
            }else if (arr[l] > x){
                //注意这里curr不移动
                swap(arr, --more, l);
            }else {
                l++;
            }
        }
        return new int[] {less+1, more-1};
    }

    public void swap(int[] a, int i, int j) {
        int temp;
        temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    public void test5() {
        int[] a = { -3, 6, 3, 1, 3, 7, 5, 6, 2 , 0 , 0};
        quickSort2(a);
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        QuickSort2 q = new QuickSort2();
        q.test5();
    }
}