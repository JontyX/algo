import java.net.IDN;

/**
 * 实现一个大小固定的有序数组，支持动态增删改操作
 * @Author: JontyX
 */
public class FixedArray {

    public int data[];

    // 数组长度
    private int n;
    // 实际数量
    private int count;

    public FixedArray(int cacacity){
        this.data = new int[cacacity];
        this.n = cacacity;
        this.count = 0;
    }

    public int find(int index){
        if (index < 0 || index >= count) return -1;
        return data[index];
    }

    public boolean insert(int index, int value){
        if (count == n){
            System.out.println("数组已满");
            return false;
        }

        if(index < 0 || index > count){
            System.out.println("位置不合法");
            return false;
        }
        // 下标从  count  开始移动
        for(int i = count ; i > index; i--){
            data[i] = data[i-1];
        }
        data[index] = value;
        count++;
        return true;
    }

    public boolean delete(int index){
        if(index < 0 || index >= count){
            return false;
        }
        for(int i = index + 1; i < count; i++){
            data[i-1] = data[i];
        }
        count--;
        return true;
    }

    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FixedArray array = new FixedArray(5);
        array.printAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        array.printAll();
    }
}
