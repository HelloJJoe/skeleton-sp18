public class ArrayDeque<T> implements Deque<T>{

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

    @Override
    public void addFirst(T item){
        if(size == items.length){
            int newAListElementNum = size * 2;
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

    @Override
    public void addLast(T item){
        if(size == items.length){
            int newAListElementNum = size * 2;
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

    @Override
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for(T item : items){
            System.out.print(item + " ");
        }
        System.out.println();
    }


    @Override
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

    @Override
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

    @Override
    public T get(int index){
        if(items[index] == null){
            return null;
        }
        return items[index];
    }




}