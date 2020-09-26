import java.util.Arrays;


public class Sort {

    public static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void bubbleSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++){
                if (arr[j] > arr[j+1]){
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length; i++){
            int min = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[i]){
                    min = j;
                }
            }
            if (min != i){
                swap(arr, i, min);
            }
        }
    }

    public static void insertSort(int[] arr){

        if (arr.length <= 1) return;

        for (int i = 1; i < arr.length; i++){
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0 && tmp < arr[j]; j--){
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }

    public static void insertSort(int[] arr, int l, int r){
        if(r - l < 1) return;

        for(int i = l; i <= r; i++){
            int temp = arr[i];
            int j = i - 1;
            for (; j >= l && temp < arr[j]; j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
    }

    public static void quickSort(int[] arr){
        if (arr.length <= 1) return;
        qsort(arr, 0, arr.length - 1);
    }

    public static void qsort(int[] arr, int l, int r){

        if (l >= r) return;
        if(r - l <= 10){
            insertSort(arr, l, r);
        }
        int x = arr[l + (int) Math.random()*(r - l + 1)];
        int less = l - 1, i = l, more = r + 1;
        while ( i < more){
            if (arr[i] < x){
                swap(arr, ++less, i++);
            }else if (arr[i] > x){
                swap(arr, --more, i);
            }else {
                i++;
            }
        }
        qsort(arr, l, less);
        qsort(arr, more, r);
    }

    public static void mergeSort(int[] arr){
        if(arr.length <= 1) return;
        msort(arr, 0, arr.length - 1);
    }

    public static void msort(int[] arr, int l, int r){
        if (l >= r) return;
        int mid = l + r >> 1;
        msort(arr, l, mid);
        msort(arr, mid + 1, r);
        int[] temp = new int[r - l + 1];
        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r){
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];

        while (j <= r) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, l, k);
    }

    public static void heapSort(int[] arr){
        if (arr.length <= 1) return;
        // build heap
        for (int i = 0; i < arr.length; i++){
            for (int j = i; arr[(j - 1) / 2] < arr[j]; j = (j - 1) / 2){
                swap(arr, (j - 1)/2, j);
            }
        }
        // adjust heap
        for (int i = arr.length - 1; i > 0; i--){
            swap(arr, 0, i);
            int index = 0, left = 1;
            while (left < i){
                int largest = left;
                if (left + 1 < i && arr[left + 1] > arr[left]) {
                    largest = left + 1;
                }
                if (arr[largest] <  arr[index]) {
                    largest = index;
                }
                if (largest == index){
                    break;
                }
                swap(arr, index, largest);
                index = largest;
                left = index * 2 + 1;
            }
        }
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 3, -1, 1, 5, 9, 4, -2, 2, 2, 2,2 ,2 , 2, 2, 2, 2, 2, 3, 10, 12, 1, 0, 5, 3, 2};
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
