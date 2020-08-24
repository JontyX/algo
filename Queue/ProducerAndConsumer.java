import sun.jvm.hotspot.opto.Block;

import java.util.Random;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        final BlockingQueue<Integer> list = new BlockingQueue<>(10);
        Produce produce = new Produce(list);
        Consumer consumer = new Consumer(list);
        produce.start();
        consumer.start();
    }

    static class Produce extends Thread{
        private final BlockingQueue<Integer> list;
        Produce(BlockingQueue list){
            this.list = list;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Integer take = list.take();
                    System.out.println("消费数据" + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread{
        private final BlockingQueue<Integer> list;
        Consumer(BlockingQueue list){
            this.list = list;
        }

        @Override
        public void run() {
            while (true){
                try {

                    int i = new Random().nextInt(100);
                    list.put(i);
                    System.out.println("生产数据" + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
