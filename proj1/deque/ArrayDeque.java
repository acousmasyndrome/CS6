package deque;

public class ArrayDeque<item> implements Deque<item>{
    private int size;
    private int nextFirst;
    private int nextLast;
    private item[] items;
    private int compacity;
    public ArrayDeque(){
        compacity=8;
        items=(item[]) new Object[compacity];
        size=0;
        nextFirst=compacity-1;
        nextLast=size;
    }
    private void resize(int new_compacity){
        item[] new_items=(item[]) new Object[new_compacity];
        if(nextLast<=nextFirst){
            for(int i=(nextFirst+1)%compacity;i<(nextFirst+1)%compacity+size;i++){
               new_items[i-(nextFirst+1)%compacity]=items[i%compacity];
            }
        }else {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                new_items[i-nextFirst-1]=items[i];
            }

        }

        items=new_items;
        compacity=new_compacity;
        nextFirst=compacity-1;
        nextLast=size;
    }
    @Override
    public void addFirst(item x){
        if(size==compacity){
            resize(2*compacity);
        }
        items[nextFirst]=x;
        nextFirst=(nextFirst+compacity-1)%compacity;
        size=size+1;
    }
    @Override
    public void addLast(item x) {
        if(size==compacity){
            resize(2*compacity);
        }
        items[nextLast]=x;
        nextLast=(nextLast+1)%compacity;
        size=size+1;
    }

    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque() {
        if(size==0) {
           System.out.print("null");
        }
        else{
        if(nextLast<=nextFirst){
           for(int i=(nextFirst+1)%compacity;i<(nextFirst+1)%compacity+size;i++){
               System.out.print(items[i%compacity]+"");
           }
        }else {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.print(items[i] + "");
            }
        }
        }
        System.out.println();
    }
    @Override
    public item removeFirst(){
        if(size-1<compacity/4&&compacity>=16){
            resize(compacity/2);
        }

        if(size==0){return null;}
        else {
            item marked = items[(nextFirst + 1) % compacity];
            nextFirst = (nextFirst + 1 )% compacity;
            size = size - 1;
            return marked;
        }
    }
    @Override
    public item removeLast(){
        if(size-1<compacity/4&&compacity>=16){
            resize(compacity/2);
        }

        if(size==0){return null;}
        else{
            item marked=items[(nextLast-1+compacity)%compacity];
            nextLast=(nextLast-1+compacity)%compacity;
            size=size-1;
            return marked;
        }


    }
    @Override
    public item get(int index){
        if(index<size&&index>=0){
            return items[(nextFirst+1+index)%compacity];
        }
        else{
            return null;
        }

    }
    public class Arrayiterator implements Iterator{
        int pointer=0;

        public boolean hasNext() {
            if(pointer<size){
                return true;
            }
            return false;
        }


        public item next() {
            pointer++;
            return get(pointer);


        }
    }

    public Iterator<item> iterator(){
        return new Arrayiterator();
    }

    public boolean equals(Object o){
        if(o instanceof Deque){
            if(size==((Deque<?>) o).size()){
                for(int i=0;i<size;i++){
                    if(get(i)==((Deque<?>) o).get(i)){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}



