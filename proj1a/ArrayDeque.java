public class ArrayDeque<T>{

    public T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int oldElementNum;

    public ArrayDeque(){
        oldElementNum = 8;
        items = (T []) new Object[oldElementNum];
        size = 0;
        nextFirst = oldElementNum / 2;
        nextLast = (oldElementNum / 2) + 1;
    }

    private int newElementNum(){
        return items.length;
    }
    private int getOldElementNum(){
        return oldElementNum;
    }

    private void calPosition(){
        if(nextFirst < newElementNum() - getOldElementNum()){
            nextFirst = newElementNum() - 1;
        }
        if(nextLast > newElementNum() - 1){
            nextLast = newElementNum() - getOldElementNum();
        }
    }
    private void reSetAllProperties(int capacity){
        oldElementNum = items.length;
        nextFirst = oldElementNum + (capacity - newElementNum()) / 2;
        nextLast = oldElementNum + (capacity - newElementNum()) / 2 + 1;
    }

    /** Resize the ArrayDeque */
    private void resize(int capacity){
        T[] newAList = (T []) new Object[capacity];
        System.arraycopy(items, 0, newAList, 0, size());
        reSetAllProperties(capacity);
        items = newAList;
    }


    public void addFirst(T item){
        if(size == newElementNum()){
            resize(size() * 2);
        }
        items[nextFirst] = item;
        nextFirst--;
        calPosition();
        size++;
    }

    public void addLast(T item){
        if(size == newElementNum()){
            resize(size() * 2);
        }
        items[nextLast] = item;
        nextLast++;
        calPosition();
        size++;
    }

    public boolean isEmpty(){
        if(size == 0){
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
        if(size == 0 || nextFirst == 0){
            return null;
        }
        if(nextFirst == 7){
            nextFirst -= 8;
        }
        T first = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        size--;
        nextFirst++;
        calPosition();
        return first;
    }

    public T removeLast(){
        if(size == 0 || nextLast == 1){
            return null;
        }

        if(nextLast == 0){
            nextLast += 8;
        }
        T last = items[nextLast - 1];
        items[nextLast - 1] = null;
        size--;
        nextLast--;
        calPosition();
        return last;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        return items[index];
    }



    public static void main(String[] args){
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(1);
        L.addLast(2);
        L.addLast(3);
        L.addLast(4);
        L.addLast(5);
        L.addLast(6);
        L.addLast(7);
        L.addLast(8);
        L.addLast(9);
        L.addLast(10);
        L.addLast(11);
        L.addLast(12);
        L.addLast(13);
        L.addLast(14);
        L.addLast(15);
        L.addLast(16);
        L.addLast(17);
        L.addLast(18);


//
//        L.addFirst(1);
//        L.addFirst(2);
//        L.addFirst(3);
//        L.addFirst(4);
//        L.addFirst(5);
//        L.addFirst(6);
//        L.addFirst(7);
//        L.addFirst(8);

        L.printDeque();
        System.out.println("size = " + L.size() + " ,nextFirst = " + L.nextFirst + " ,nextLast = " + L.nextLast + "\n");
        L.addFirst(9);
        L.addFirst(10);
        L.addFirst(11);
        L.addFirst(12);
        L.addFirst(13);
        L.addFirst(14);
        L.addFirst(15);
        L.addFirst(16);
        L.addFirst(17);
        L.addFirst(18);









    }


}