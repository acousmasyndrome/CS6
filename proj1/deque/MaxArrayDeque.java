package deque;
import java.util.Comparator;
public class MaxArrayDeque<item> extends ArrayDeque<item> {

    Comparator<item> mycomparator;
    public MaxArrayDeque(Comparator<item> c){
        super();
        mycomparator=c;

    }
    public item max(){
        return max(mycomparator);


    }
    public item max(Comparator<item> c){
        if (isEmpty()) {
            return null;
        }

        int maxIndex = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }
}
