package TwoWaysOfObjectCompare.UseComparable;

import java.util.Arrays;

/**
 * Created by wzyoung on 2015/2/12.
 */
public class TestClient {

    public static void main(String[] args) {


        Name[] nameList = {new Name(2), new Name(1), new Name(3)};

        Arrays.sort(nameList);
        for (Name name : nameList) {
            System.out.println(name.getId());
        }


    }
}
