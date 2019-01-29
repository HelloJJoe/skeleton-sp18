
public class ArrayDeque<T>{

    public T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private int ElementNum(){
        return items.length;
    }

    /** Resize the A */
    private void resize(int capacity){
        T[] newAList = (T []) new Object[capacity];
        /** copy the old items element to newAlist **/

        // index of newAList
        int j = 0;
        int i = plusOne(nextFirst);
        int counter = 0;
        while(counter < size()){
            newAList[j] = items[i];
            j++;
            i = plusOne(i);
            counter++;
        }
        nextLast = j;
        nextFirst = capacity - 1;
        items = newAList;
    }


    public void addFirst(T item){
        items[nextFirst] = item;
        size++;
        nextFirst = minusOne(nextFirst);
        if(size() == ElementNum()){
            resize(size() * 2);
            return;
        }
    }



    public void addLast(T item){
        items[nextLast] = item;
        size++;
        nextLast = plusOne(nextLast);
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
        if(size() == 0){
            nextFirst = ElementNum() - 1;
            nextLast = 0;
        }
        if((float)size() / ElementNum() < 0.25){
            resize(ElementNum() / 2);
        }
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
        if((float) size() / ElementNum() < 0.25){
            resize(ElementNum() / 2);
        }
        return last;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        return items[Math.floorMod(nextFirst + 1 + index, ElementNum())];
    }


    private int minusOne(int x){
        return Math.floorMod(x - 1, ElementNum());
    }

    private int plusOne(int x){
        return Math.floorMod(x + 1, ElementNum());
    }

    public static void main(String[] args){
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addFirst(4);
        System.out.println((float) (A.size()/32));

//        for(int i = 0; i < 16; i++){
//            A.addFirst(i);
//        }
//        for(int i = 0; i < 14; i++){
//            A.removeFirst();
//        }



    }


}