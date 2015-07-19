package CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * countDownLatch 使用
 * <p/>
 * Created by wzyoung on 2015/2/10.
 */
public class Player implements Runnable {


    // test
    private CountDownLatch begin;

    private CountDownLatch end;

    private int i;

    public Player(CountDownLatch begin, CountDownLatch end, int i) {
        this.begin = begin;
        this.end = end;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            begin.await();
            Thread.sleep((long)(Math.random()*10000));
            System.out.println("运动员:" + i + " 到达终点");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            end.countDown();
        }

    }
}
