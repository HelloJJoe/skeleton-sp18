public class ArrayDeque<T>{

    public T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int elementNum;

    public ArrayDeque(){
        elementNum = 8;
        items = (T []) new Object[elementNum];
        size = 0;
        nextFirst = elementNum / 2;
        nextLast = (elementNum / 2) + 1;
    }

    private void calPosition(){
        if(nextFirst < 0){
            nextFirst = elementNum - 1;
        }
        if(nextLast > elementNum - 1){
            nextLast = 0;
        }

    }

//    private void resize(int capacity){
//        T[] newAList = (T []) new Object[capacity];
//        System.arraycopy(items, 0, newAList, 0, size);
//        items = newAList;
//
//    }


    public void addFirst(T item){
        if(size == items.length){
            int newAListElementNum = size * 3;
            T[] newAList = (T []) new Object[newAListElementNum];
            // copy all of addFirst element to newAList
            int counter = 0;
            int addFirstNum;
            if(nextFirst > elementNum / 2){
                addFirstNum = elementNum / 2 - nextFirst;
            }else{
                addFirstNum = elementNum / 2 - (nextFirst - elementNum);
            }
            while(counter < addFirstNum){
                int oldAListFirstPos = elementNum / 2 - counter;
                if(oldAListFirstPos < 0){
                    oldAListFirstPos += elementNum;
                }
                newAList[newAListElementNum / 2 - counter] = items[oldAListFirstPos];
                counter++;
            }
            items = newAList;
            nextFirst = newAListElementNum / 2 - addFirstNum;
        }
        items[nextFirst] = item;
        nextFirst--;
        calPosition();
        size++;
    }

    public void addLast(T item){
        if(size == items.length){
            int newAListElementNum = size * 3;
            T[] newAList = (T []) new Object[newAListElementNum];

            // copy all of the addLast element to newAList
            int counter = 0;
            int addLastNum;
            int oldAListLastPos;
            if(nextLast > elementNum / 2 + 1){
                addLastNum = nextLast - elementNum / 2 + 1;
            }else{
                addLastNum = nextLast + elementNum - (elementNum / 2 + 1);
            }
            while(counter < addLastNum){
                oldAListLastPos = (elementNum / 2 + 1) + counter;
                if(oldAListLastPos > elementNum - 1){
                    oldAListLastPos -= elementNum;
                }
                newAList[newAListElementNum / 2 + 1 + counter] = items[oldAListLastPos];
                counter++;
            }
            items = newAList;
            nextLast = newAListElementNum / 2 + 1 + addLastNum;
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
        if(items[index] == null){
            return null;
        }
        return items[index];
    }



    public static void main(String[] args){
        ArrayDeque<String> L = new ArrayDeque<>();
        L.addLast("1");
        L.addLast("2");
        L.addLast("3");
        L.addLast("4");
        L.addLast("5");
        L.addLast("6");

        L.addLast("7");
        L.addLast("8");


//        L.addFirst("0");
//        L.addFirst("-1");
//        L.addFirst("-2");
//        L.addFirst("-3");
//        L.addFirst("-4");
//        L.addFirst("-5");
//        L.addFirst("-6");
        L.printDeque();
        System.out.println("size = " + L.size() + " ,nextFirst = " + L.nextFirst + " ,nextLast = " + L.nextLast + "\n");
//
        L.addLast("9");

////        L.addLast("2");

        L.printDeque();
        System.out.println("size = " + L.size() + " ,nextFirst = " + L.nextFirst + " ,nextLast = " + L.nextLast + "\n");
//
//
//
//

//        L.addFirst("-6");
//
//        L.addLast("4");
//        L.addLast("5");

//        System.out.println(L.removeLast());
//        L.removeLast();
//        L.addLast("6");
//        L.addLast("7");
//        L.addLast("8");
//        L.removeFirst();
//        L.printDeque();
//        System.out.println("size = " + L.size() + " ,nextFirst = " + L.nextFirst + " ,nextLast = " + L.nextLast + "\n");
//////

//        L.printDeque();
//        System.out.println("size = " + L.size + " ,nextFirst = " + L.nextFirst + " ,nextLast = " + L.nextLast + "\n");


//        L.printDeque();

    }


}