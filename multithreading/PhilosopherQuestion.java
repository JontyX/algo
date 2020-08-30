import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 第一版
 */
public class PhilosopherQuestion {

    static Semaphore mutex = new Semaphore(1);
    static Semaphore[] fork = new Semaphore[5];

    static class Philosopher implements Runnable{
        int i = 0;

        public Philosopher(int i) {
            this.i = i;
        }


        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                try {
//                    mutex.acquire();
                    fork[i].acquire();
                    fork[(i+1) % 5].acquire();
                    System.out.println("哲学家 " + i + " 正在吃饭");
                    fork[(i+1) % 5].release();
                    fork[i].release();
//                    mutex.release();
                    Thread.sleep(500);
                    Thread.yield();
                }catch (InterruptedException e){
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            fork[i] = new Semaphore(1, true);
        }
        for (int i = 0; i < 5; i++) {
            ex.execute(new Philosopher(i));
        }
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            ex.shutdownNow();
        }
    }
}
