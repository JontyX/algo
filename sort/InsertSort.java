
public class InsertSort {

    public static void inserSort(int[] nums){
        if (nums == null || nums.length < 2) return;
        for(int i = 1; i < nums.length; i++){
            int temp =  nums[i];
            int j = i - 1;
            for (; j >= 0; j--){
                if (nums[j] > temp){
                    nums[j+1] = nums[j];
                }
                else{
                    break;
                }
            }
            nums[j+1] = temp;
        }
    }
}
