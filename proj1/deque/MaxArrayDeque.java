package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> mycomparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        mycomparator = c;

    }

    public T max() {
        return max(mycomparator);


    }

    public T max(Comparator<T> c) {
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
