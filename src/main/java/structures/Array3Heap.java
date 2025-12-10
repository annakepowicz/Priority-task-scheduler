package structures;

import java.util.Comparator;
import java.util.NoSuchElementException;


public class Array3Heap<T> implements MaxHeap<T> {

    private T[] heap;
    private final Comparator<T> comparator;
    private int size;
    private final int initialCapacity;


    @SuppressWarnings("unchecked")
    public Array3Heap(int initialCapacity, Comparator<T> comparator) {

        heap = (T[])new Object[initialCapacity];
        this.comparator = comparator;
        this.initialCapacity = initialCapacity;
        size = 0;

    }

    private int findKthChild(int index, int which){
        return 3*index + which;
    }

    private int findParent(int index){
        return (index - 1 )/3;
    }

    private boolean isEmpty(){
        return size==0;
    }

    private boolean isFull() {
        return size == heap.length;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(){
        T[] tmp =(T[]) new Object[heap.length*2];
        System.arraycopy(heap, 0, tmp, 0, heap.length);
        heap = tmp;
    }

    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private void swim(int index) {
        if (index > size){
            throw new IndexOutOfBoundsException();
        }
        int parent;
        while(index != 0 &&
                comparator.compare(heap[index], heap[parent = findParent(index)]) > 0){
            swap(index, parent);
            index = parent;
        }
    }

    private int findMaxChild(int index){
        if (index > size){
            throw new IndexOutOfBoundsException();
        }
        int tmp;
        int maxChildIndex = findKthChild(index, 1);
        for (int i = 2; i <= 3; i++) {
            if((tmp = findKthChild(index, i )) >= heap.length){
                return maxChildIndex;
            }
            T tempValue = heap[tmp];
            if(!(tempValue == null)) {
                if (comparator.compare(tempValue, heap[maxChildIndex]) > 0 ){
                    maxChildIndex = tmp;
                }
            }
        }

        return maxChildIndex;
    }

    private void sinkFirst() {
        int index = 0;
        boolean isDone=false;
        int child;

        while(!isDone && findKthChild(index, 1) < size){
            child = findMaxChild(index);
            if(comparator.compare(heap[child], heap[index]) > 0){
                swap(child, index);
            }else{
                isDone = true;
            }
            index = child;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        heap = (T[])new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void add(T element) {
        if(isFull()){
            ensureCapacity();
        }
        heap[size] = element;
        swim(size);
        size++;
    }

    private void removeFirst(){
        swap(0, size-1);
        heap[size-1] = null;
        size--;
        sinkFirst();
    }

    @Override
    public T maximum() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        T returnValue = heap[0];
        removeFirst();
        return returnValue;
    }

    public void printHeap(){
        System.out.print("[ ");
        for (int i = 0; i < size- 1; i++) {
            System.out.print(heap[i]+", ");
        }
        if(size> 0) {
            System.out.print(heap[size - 1]);
        }
        System.out.println("]");
        System.out.println();
    }

    public T nthMaximum(int n) {
        if (isEmpty() || n < 1 || n > size) {
            throw new NoSuchElementException();
        }
        Array3Heap<T> tempHeap = new Array3Heap<>(size, comparator);
        System.arraycopy(heap, 0, tempHeap.heap, 0, size);
        tempHeap.size = size;

        T nthMax = null;
        for (int i = 0; i < n; i++) {
            nthMax = tempHeap.maximum();
        }

        return nthMax;
    }

}
