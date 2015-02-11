package TwoWaysOfObjectCompare.UseComparator;

import java.util.Comparator;

/**
 * Created by wzyoung on 2015/2/12.
 */
public class NameComparatorDesc implements Comparator<Name> {
    @Override
    public int compare(Name o1, Name o2) {
        if (o1.getId() > o2.getId()) {
            return 1;
        }
        if (o1.getId() < o2.getId()) {
            return -1;
        }
        return 0;
    }
}
