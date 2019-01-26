public class ArrayDeque<T>{

    public T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    private void calPosition(){
        if(nextFirst < 0){
            nextFirst += 8;
        }
        if(nextLast > 7){
            nextLast -= 8;
        }

    }


    public void addFirst(T item){
        items[nextFirst] = item;
        nextFirst--;
        calPosition();
        size++;
    }

    public void addLast(T item){
        items[nextLast] = item;
        nextLast++;
        calPosition();
        size++;
    }
    public boolean isEmpty(){
        if(items == null){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public T removeFirst(){
        T first = items[nextFirst + 1];
        return first;
    }

    public void printDeque(){
        for(T item : items){
            System.out.println(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        ArrayDeque<String> L = new ArrayDeque<>();
        L.addFirst("hello");
        L.addFirst("welcom");
        L.addLast("you");
        L.printDeque();
        System.out.println(L.size);
    }


}