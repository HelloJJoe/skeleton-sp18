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
        if(size() == ElementNum()){
            resize(size() * 2);
            return;
        }
        calPosition();
    }

    public void addLast(T item){
        items[nextLast] = item;
        size++;
        nextLast++;
        if(size() == ElementNum()){
            resize(size() * 2);
            return;
        }
        calPosition();
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
        if(size() == 0){
            nextFirst = ElementNum() - 1;
            nextLast = 0;
        }
        calPosition();
        return first;
    }

    public T removeLast(){
        if(size() == 0){
            return null;
        }
        if(nextLast == 0){
            nextLast += 8;
        }
        T last = items[nextLast - 1];
        items[nextLast - 1] = null;
        size--;
        nextLast--;
        if(size() == 0){
            nextFirst = ElementNum() - 1;
            nextLast = 0;
        }
        calPosition();
        return last;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        return items[Math.floorMod(nextFirst + 1 + index, ElementNum())];
    }

    public static void main(String[] args){
        ArrayDeque<Integer> L = new ArrayDeque<>();

        L.addFirst(0);
        L.addFirst(1);
        L.addFirst(2);
        L.addFirst(3);
        L.addFirst(4);
        L.removeLast();
        L.removeLast();

        L.removeLast();
        L.removeLast();
        L.addLast(5);
        System.out.println(L.get(0));
        System.out.println(L.get(1));
        System.out.println(L.get(2));
        System.out.println(L.get(3));




    }


}