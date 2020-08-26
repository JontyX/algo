import java.util.Arrays;

/**
 * @Author: JontyX
 */
public class MergeSort {

    public static void mergeSort(int[] q, int l, int r){
        if (l >= r) return;
        int mid = l + r >> 1;

        mergeSort(q, l, mid );
        mergeSort(q, mid+ 1, r);

        int[] tmp = new int[r - l + 1];
        int k = 0, i = l, j = mid + 1;

        while (i <= mid && j <=r){
            if (q[i] <= q[j]) tmp[k++] = q[i++];
            else tmp[k++] = q[j++];
        }

        while (i <= mid) tmp[k++] = q[i++];
        while (j <= r) tmp[k++] = q[j++];

        for (i = l, j = 0; i <= r; i++, j++) q[i] = tmp[j];

    }

    public static void main(String[] args) {
        int[] arr = {3,5,2,8,2,4,9,6,2,5,3,64,2,32,34,85,0,1};
        mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
