
import java.util.concurrent.atomic.AtomicReference;

/**
 * 可重入自旋锁
 * 自旋锁适用于保护临界区很小的情况
 */
public class SimpleSpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    private int count = 0;

    public void lock(){
        Thread t = Thread.currentThread();
        if (t == owner.get()){
            count++;
            return;
        }
        while (owner.compareAndSet(null, t)){

        }
    }

    public void unlock(){
        Thread t = Thread.currentThread();
        if (t == owner.get()){
            if (count > 0){
                count--;
            }
            if (count == 0){
                owner.set(null);
            }
        }
    }
}
