package TwoWaysOfObjectCompare.UseComparable;

/**
 * Created by wzyoung on 2015/2/8.
 */
public class Name implements Comparable<Name> {

    private int id;

    public Name(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Name o) {

        if (o.getId() > getId()) {
            return -1;
        }
        if (o.getId() < getId()) {
            return 1;
        }
        return 0;
    }
}
