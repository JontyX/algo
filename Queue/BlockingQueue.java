import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {
    private final T[] array;
    private int head;
    private int tail;
    private int count;
    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BlockingQueue(int size){
        array = (T[]) new Object[size];
    }

    public void put(T item) throws InterruptedException{
        lock.lock();
        try {
            while (count == array.length){
                notFull.await();
            }
            array[tail] = item;
            tail = (tail + 1) % array.length;
            count++;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException{
        lock.lock();
        try {
            while (count == 0){
                notEmpty.await();
            }
            T item = array[head];
            head = (head + 1) % array.length;
            count--;
            notFull.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue=new BlockingQueue(3);

        new Thread(()->{
            for (int i = 0; i <100000 ; i++) {
                try {
                    System.out.println("生产者生产: "+i);
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(()->{
            for (int i = 0; i <100000 ; i++) {
                try {
                    Integer a= (Integer) queue.take();
                    if (a!=null){
                        System.out.println("消费者消费: "+a);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
