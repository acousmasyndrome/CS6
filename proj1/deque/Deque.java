package deque;

public interface Deque<item> {
    public void addFirst(item item);
    public void addLast(item item);
    default boolean isEmpty(){
        if(size()==0){
            return true;
        }else{
            return false;
        }
    }
    public int size();
    public void printDeque();
    public item removeFirst();
    public item removeLast();
    public item get(int index);
    Iterator<item> iterator();
    boolean equals(Object o);
}
