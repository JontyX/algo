/**
 * 写两个线程，一个线程打印1~ 52，另一个线程打印A~Z，打印顺序是12A34B…5152Z
 */
public class TakeTurnsPrint2 {
    private boolean flag;
    private int count;

    public synchronized void printNum() {
        for (int i = 0; i < 26; i++) {
            while (flag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = !flag;
            System.out.print(++count);
            System.out.print(++count);
            notify();
        }
    }

    public synchronized void printLetter() {
        for (int i = 0; i < 26; i++) {
            while (!flag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = !flag;
            System.out.print((char) (65 + i));
            notify();
        }
    }

    public static void main(String[] args) {

        TakeTurnsPrint2 turnsPrint2 = new TakeTurnsPrint2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                turnsPrint2.printNum();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                turnsPrint2.printLetter();
            }
        }).start();
    }
}
