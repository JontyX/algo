import java.util.Arrays;

/**
 * @Author: JontyX
 */
public class HeapSort {

    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        while (size > 0){
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    public static void heapify(int[] arr, int index, int size){
        int left = index * 2 + 1;
        while (left < size){
            int larg = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            larg = arr[index] > arr[larg] ? index : larg;
            if (larg == index) break;
            swap(arr, index, larg);
            // 下沉
            index = larg;
            left = 2 * index  + 1;
        }
    }

    public static void heapInsert(int arr[] , int i){
        while (arr[(i-1)/2] < arr[i]){
            swap(arr, (i-1)/2, i);
            i = (i-1) / 2;
        }
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }



    public static void main(String[] args) {
        int[] arr = {1,2,3,0,-1,8,9,3,0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
    }
}
