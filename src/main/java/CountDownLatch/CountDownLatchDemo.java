package CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * demo
 * Created by wzyoung on 2015/2/10.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        final int PLAY_NUM = 10;

        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(PLAY_NUM);
        List<Player> playerList = new ArrayList<Player>();

        for (int i = 0; i < PLAY_NUM; i++) {
            Player player = new Player(begin, end, i);
            playerList.add(player);
        }
        Executor executor = Executors.newFixedThreadPool(PLAY_NUM);
        System.out.println("预备,跑!");

        for (Player player : playerList) {
            executor.execute(player);
        }
        try {
            begin.countDown();
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束!");
    }
}
