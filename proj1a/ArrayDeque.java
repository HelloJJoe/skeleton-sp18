public class ArrayDeque<T>{

    public T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int oldElementNum;

    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
        oldElementNum = 8;
    }

    private int ElementNum(){
        return items.length;
    }
    private int getOldElementNum(){
        return oldElementNum;
    }

    private void calPosition(){
        if(nextFirst < ElementNum() - getOldElementNum()){
            nextFirst = ElementNum() - 1;
        }
        if(nextLast > ElementNum() - 1){
            nextLast = ElementNum() - getOldElementNum();
        }
    }
    private void reSetAllProperties(int capacity){
        oldElementNum = items.length;
        nextFirst = capacity - 1;
        nextLast = getOldElementNum();
    }

    /** Resize the ArrayDeque */
    private void resize(int capacity){
        T[] newAList = (T []) new Object[capacity];
        /** copy the old items element to newAlist **/

        // index of newAList
        int j = 0;
        int i = nextLast % ElementNum();
        int counter = 0;
        while(counter < ElementNum()){
            newAList[j] = items[i];
            j++;
            i++;
            counter++;
            if(i == ElementNum()){
                i = 0;
            }
        }
        reSetAllProperties(capacity);
        items = newAList;
    }


    public void addFirst(T item){
        items[nextFirst] = item;
        size++;
        nextFirst--;
        calPosition();
        if(size() == ElementNum()){
            resize(size() * 2);
            return;
        }
    }

    public void addLast(T item){
        items[nextLast] = item;
        size++;
        nextLast++;
        calPosition();
        if(size() == ElementNum()){
            resize(size() * 2);
            return;
        }

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
        if(size == 0){
            return null;
        }
        if(nextFirst == ElementNum() - 1){
            nextFirst -= ElementNum();
        }
        T first = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        size--;
        nextFirst++;
        calPosition();
        return first;
    }

    public T removeLast(){
        if(size == 0){
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
        }else if(items[index] == null){
            return null;
        }
        return items[index];
    }



    public static void main(String[] args){
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(1);
        L.addLast(2);
        L.addLast(3);
        L.addFirst(100);
        L.addFirst(101);
        L.addLast(4);
//        L.removeLast();
//        L.removeLast();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeLast();
        L.removeLast();
        L.removeLast();

        L.addLast(6);
        L.addLast(7);
        L.addLast(8);
        L.addLast(9);
        L.addLast(10);
        L.addLast(11);
//        L.addLast(12);
//        L.addLast(13);
//        L.addLast(14);
//        L.addLast(15);
//        L.addLast(16);
//        L.addLast(17);
//        L.addLast(18);


//
        L.addFirst(1);
        L.addFirst(2);
        L.addFirst(3);
        L.addFirst(4);
        L.addFirst(5);
        L.addFirst(6);
        L.addFirst(7);
        L.addFirst(8);
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