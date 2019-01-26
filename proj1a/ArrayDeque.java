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

    private void resize(int capacity){
        T[] newAList = (T []) new Object[capacity];
        System.arraycopy(items, 0, newAList, 0, size);
        items = newAList;

    }


    public void addFirst(T item){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst--;
        calPosition();
        size++;
    }

    public void addLast(T item){
        if(size == items.length){
            resize(size * 2);
        }
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

    public void printDeque(){
        for(T item : items){
            System.out.print(item + " ");
        }
        System.out.println();
    }



    public T removeFirst(){

        if(nextFirst == 7){
            nextFirst -= 8;
        }
        T first = items[nextFirst + 1];
        size--;
        nextFirst++;
        calPosition();
        return first;
    }

    public T removeLast(){

        if(nextLast == 0){
            nextLast += 8;
        }
        T last = items[nextLast - 1];
        size--;
        nextLast--;
        calPosition();
        return last;
    }

    public T get(int index){
        if(items[index] == null){
            return null;
        }
        return items[index];
    }



    public static void main(String[] args){
        ArrayDeque<String> L = new ArrayDeque<>();
        L.addFirst("1");
        L.addFirst("-1");
        L.addLast("2");
        L.addLast("3");
        L.addLast("4");
        L.addLast("5");
        L.addLast("6");
        L.addLast("7");
        L.addLast("8");

        L.printDeque();
        System.out.println(L.size);
//        L.removeFirst();
//        L.printDeque();

    }


}