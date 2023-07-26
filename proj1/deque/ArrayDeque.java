package deque;

public class ArrayDeque<item> {
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
            for(int i=nextFirst+1;i<nextLast+compacity;i++){
               new_items[i-nextFirst-1]=items[i%compacity];
            }
        }else {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                new_items[i-nextFirst-1]=items[i];
            }
            items=new_items;
            compacity=new_compacity;
            nextFirst=compacity-1;
            nextLast=size;
        }


    }
    public void addFirst(item x){
        items[nextFirst]=x;
        nextFirst=nextFirst+compacity-1%compacity;
        size=size+1;
    }
    public void addLast(item x) {
        items[nextLast]=x;
        nextLast=nextLast+1%compacity;
        size=size+1;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        else{
            return false;
        }
    }
    public int size(){
        return size;
    }
    public void printDeque() {
        if(size==0) {
           System.out.print("null");
        }
        else{
        if(nextLast<=nextFirst){
           for(int i=nextFirst+1;i<nextLast+compacity;i++){
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
    public item removeFirst(){
        if(size-1<compacity/4&&compacity>=16){
            resize(compacity/2);
        }
        if(size==0){return null;}
        else {
            item marked = items[nextFirst + 1 % compacity];
            nextFirst = nextFirst + 1 % compacity;
            size = size - 1;
            return marked;
        }
    }
    public item removeLast(){
        if(size-1<compacity/4&&compacity>=16){
            resize(compacity/2);
        }
        if(size==0){return null;}
        else{
            item marked=items[nextLast-1%compacity];
            nextLast=nextLast-1+compacity%compacity;
            size=size-1;
            return marked;
        }


    }
    public item get(int index){
        if(index<size&&index>=0){
            return items[nextFirst+1+index%compacity];
        }
        else{
            return null;
        }

    }
}
