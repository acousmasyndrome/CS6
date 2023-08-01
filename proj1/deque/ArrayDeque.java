package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int compacity;

    public ArrayDeque() {
        compacity = 8;
        items = (T[]) new Object[compacity];
        size = 0;
        nextFirst = compacity - 1;
        nextLast = size;
    }

    private void resize(double doubleCompacity) {
        int newCompacity = (int) doubleCompacity;
        T[] newItems = (T[]) new Object[newCompacity];
        if (nextLast <= nextFirst + 1) {
            for (int i = (nextFirst + 1) % compacity; i < (nextFirst + 1) % compacity + size; i++) {
                newItems[i - (nextFirst + 1) % compacity] = items[i % compacity];
            }
        } else {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                newItems[i - nextFirst - 1] = items[i];
            }

        }

        items = newItems;
        compacity = newCompacity;
        nextFirst = compacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        if (size == compacity) {
            resize(2 * compacity);
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst + compacity - 1) % compacity;
        size = size + 1;
    }

    @Override
    public void addLast(T x) {
        if (size == compacity) {
            resize(2 * compacity);
        }
        items[nextLast] = x;
        nextLast = (nextLast + 1) % compacity;
        size = size + 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.print("null");
        } else {
            if (nextLast <= nextFirst) {
                for (int i = (nextFirst + 1) % compacity;
                     i < (nextFirst + 1) % compacity + size; i++) {
                    System.out.print(items[i % compacity] + "");
                }
            } else {
                for (int i = nextFirst + 1; i < nextLast; i++) {
                    System.out.print(items[i] + "");
                }
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T marked = items[(nextFirst + 1) % compacity];
            items[(nextFirst + 1) % compacity] = null;
            nextFirst = (nextFirst + 1) % compacity;
            size = size - 1;
            if (size < compacity / 4.0 && compacity >= 16) {
                resize(compacity / 2.0);
            }
            return marked;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T marked = items[(nextLast - 1 + compacity) % compacity];
            items[(nextLast - 1 + compacity) % compacity] = null;
            nextLast = (nextLast - 1 + compacity) % compacity;
            size = size - 1;
            if (size < compacity / 4.0 && compacity >= 16) {
                resize(compacity / 2.0);
            }
            return marked;
        }


    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return items[(nextFirst + 1 + index) % compacity];
        } else {
            return null;
        }

    }

    private class Arrayiterator implements Iterator<T> {
        private int pointer;

        public Arrayiterator() {
            pointer = 0;
        }

        public boolean hasNext() {
            if (pointer < size) {
                return true;
            }
            return false;
        }


        public T next() {
            pointer++;
            return get(pointer - 1);
        }
    }

    public Iterator<T> iterator() {
        return new Arrayiterator();
    }

    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<T> otherobject = (Deque<T>) o;
            if (size == otherobject.size()) {
                if (this == o) {
                    return true;
                }
                if (size == 0) {
                    return true;
                } else {
                    for (int i = 0; i < size; i++) {
                        if (get(i) != otherobject.get(i)) {
                            return false;
                        }
                    }
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}




