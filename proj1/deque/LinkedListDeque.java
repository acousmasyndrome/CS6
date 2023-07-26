package deque;

public class LinkedListDeque<item> implements Deque<item> {
    private Nodes sentinal ;
    private int size;
    public class Nodes {
        public item content;
        public Nodes next;
        public Nodes prev;

        public Nodes(item x, Nodes items) {
            content = x;
            next = items;
            if (items != null) {
                items.prev = this;
            }
        }
    }
    public LinkedListDeque(){
        item x=null;
        sentinal=new Nodes(x,null) ;
        size=0;
    }

    @Override
    public void addFirst(item x){
        if(sentinal.next!=null){
        Nodes lastitem=sentinal.next.prev;
        sentinal.next=new Nodes(x,sentinal.next);
        sentinal.next.prev=lastitem;
        lastitem.next=sentinal.next;}
        else{
            sentinal.next=new Nodes(x,null);
            sentinal.next.prev=sentinal.next;
            sentinal.next.next=sentinal.next;

        }

        size=size+1;

    }
    @Override
    public void addLast(item x){
        if(sentinal.next!=null){
            Nodes prev_lastNodes=sentinal.next.prev;
            Nodes last_nodes=new Nodes(x,sentinal.next);
            prev_lastNodes.next=last_nodes;
            last_nodes.prev=prev_lastNodes;
            size=size+1;}
        else{
            addFirst(x);
        }


    }
    @Override
    public int size(){
        return size;
    }
    public void printDeque(){
        Nodes marked=sentinal.next;
        for(int i=0;i<size;i++){
            System.out.print(marked.content+"");
            marked=marked.next;
        }
        System.out.println();
    }
    @Override
    public item removeFirst() {
        Nodes firstitem=sentinal.next;
        if(firstitem!=null){
            if(size>1) {
                sentinal.next = firstitem.next;
                sentinal.next.prev = firstitem.prev;
                firstitem.prev.next = sentinal.next;
                size = size - 1;
                return firstitem.content;
            }
            else{
                sentinal.next=null;
                size=size-1;
                return firstitem.content;
            }
        }
        else{
            return null;
        }
    }
    @Override
   public item removeLast(){
        if(sentinal.next!=null) {
            Nodes lastitem = sentinal.next.prev;
            if(size>1) {
                lastitem.prev.next = sentinal.next;
                sentinal.next.prev = lastitem.prev;
                size = size - 1;
                return lastitem.content;
            }
            else{
                sentinal.next=null;
                size=size-1;
                return lastitem.content;
            }
        }
        else{
            return null;
        }
   }
    @Override
   public  item get(int index){
        if(index<size&&index>=0){
            Nodes marked=sentinal;
            for (int i = 0; i < index+1; i++) {
                marked = marked.next;
                }
                return marked.content;
        }
        else{
            return null;
        }
   }
   public item getRecursive(int index){
       if(index<size&&index>=0){
           Nodes marked=sentinal.next;
           return getRecursive_helper(index,marked);
       }
       else{
          return null;
       }
   }
   //a helper method to better fulfill the mission of get index element recursively.
   private item getRecursive_helper(int index, Nodes marked){
        if(index==0){
            return marked.content;
        }
        else{
            return getRecursive_helper(index-1,marked.next);
        }
   }


}

