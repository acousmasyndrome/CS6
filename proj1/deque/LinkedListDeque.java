package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private Nodes sentinal;
    private int size;

    private class Nodes {
        private T content;
        private Nodes next;
        private Nodes prev;

        public Nodes(T x, Nodes items) {
            content = x;
            next = items;
            if (items != null) {
                items.prev = this;
            }
        }
    }

    public LinkedListDeque() {
        T x = null;
        sentinal = new Nodes(x, null);
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        if (sentinal.next != null) {
            Nodes lastitem = sentinal.next.prev;
            sentinal.next = new Nodes(x, sentinal.next);
            sentinal.next.prev = lastitem;
            lastitem.next = sentinal.next;
        } else {
            sentinal.next = new Nodes(x, null);
            sentinal.next.prev = sentinal.next;
            sentinal.next.next = sentinal.next;

        }

        size = size + 1;

    }

    @Override
    public void addLast(T x) {
        if (sentinal.next != null) {
            Nodes prevLastNodes = sentinal.next.prev;
            Nodes lastNodes = new Nodes(x, sentinal.next);
            prevLastNodes.next = lastNodes;
            lastNodes.prev = prevLastNodes;
            size = size + 1;
        } else {
            addFirst(x);
        }


    }

    @Override
    public int size() {
        return size;
    }

    public void printDeque() {
        Nodes marked = sentinal.next;
        for (int i = 0; i < size; i++) {
            System.out.print(marked.content + "");
            marked = marked.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        Nodes firstitem = sentinal.next;
        if (firstitem != null) {
            if (size > 1) {
                sentinal.next = firstitem.next;
                sentinal.next.prev = firstitem.prev;
                firstitem.prev.next = sentinal.next;
                size = size - 1;
                return firstitem.content;
            } else {
                sentinal.next = null;
                size = size - 1;
                return firstitem.content;
            }
        } else {
            return null;
        }
    }

    @Override
    public T removeLast() {
        if (sentinal.next != null) {
            Nodes lastitem = sentinal.next.prev;
            if (size > 1) {
                lastitem.prev.next = sentinal.next;
                sentinal.next.prev = lastitem.prev;
                size = size - 1;
                return lastitem.content;
            } else {
                sentinal.next = null;
                size = size - 1;
                return lastitem.content;
            }
        } else {
            return null;
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            Nodes marked = sentinal;
            for (int i = 0; i < index + 1; i++) {
                marked = marked.next;
            }
            return marked.content;
        } else {
            return null;
        }
    }

    public T getRecursive(int index) {
        if (index < size && index >= 0) {
            Nodes marked = sentinal.next;
            return getRecursiveHelper(index, marked);
        } else {
            return null;
        }
    }

    //a helper method to better fulfill the mission of get index element recursively.
    private T getRecursiveHelper(int index, Nodes marked) {
        if (index == 0) {
            return marked.content;
        } else {
            return getRecursiveHelper(index - 1, marked.next);
        }
    }

    private class Linkediterator implements Iterator<T> {
        private int pointer;

        public Linkediterator() {
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
        return new Linkediterator();
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


