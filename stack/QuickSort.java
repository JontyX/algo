import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    static void qsort(int q[] , int l , int r){
        if(l >= r) return;
        int i = l - 1, j = r + 1 , x = q[l + r >> 1];
        while (i < j){
            do i++; while (q[i] < x);
            do j--; while (q[j] > x);
            if (i < j) swap(q, i,j);
        }
        qsort(q, l, j);
        qsort(q, j+ 1, r);
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3,5,2,8,2,4,9,6,2,5,3,64,2,32,34,85,0,1};
        qsort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
