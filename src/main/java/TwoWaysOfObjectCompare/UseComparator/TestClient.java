package TwoWaysOfObjectCompare.UseComparator;


import java.util.Arrays;
import java.util.Collections;

/**
 * comparator Test
 * Created by wzyoung on 2015/2/12.
 */
public class TestClient {

    public static void main(String[] args) {

        Name[] nameList = {new Name(2), new Name(1), new Name(3)};

        Arrays.sort(nameList, new NameComparator());

        print(nameList);

        Collections.sort(Arrays.asList(nameList), new NameComparatorDesc());

        System.out.println("after desc");

        print(nameList);
    }

    public static void print(Name[] nameList) {
        for (Name name : nameList) {
            System.out.println(name.getId());
        }
    }
}
