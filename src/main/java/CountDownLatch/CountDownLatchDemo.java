package CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * demo
 * Created by wzyoung on 2015/2/10.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        // 定义10个运动员

        final int PLAY_NUM = 10;

        // 准备后,放运动员线程
        CountDownLatch begin = new CountDownLatch(1);

        // 结束:最有一个运动员线程跑完后,主线程继续
        CountDownLatch end = new CountDownLatch(PLAY_NUM);

        List<Player> playerList = new ArrayList<Player>();

        for (int i = 0; i < PLAY_NUM; i++) {
            Player player = new Player(begin, end, i);
            playerList.add(player);
        }
        ExecutorService executor = Executors.newFixedThreadPool(PLAY_NUM);

        for (Player player : playerList) {
            executor.execute(player);
        }


        System.out.println("比赛开始!");

        begin.countDown();
        end.await();

        System.out.println("比赛结束!");
        executor.shutdown();

    }
}
