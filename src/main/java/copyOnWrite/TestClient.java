package copyOnWrite;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wzyoung on 2015/2/16.
 */
public class TestClient {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

        list.add("a");

        System.out.println(list);
    }
}
