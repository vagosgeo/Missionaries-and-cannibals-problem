import java.util.Comparator;

public class PQueue{
    private State[] heap; 
    private int size; // current size of the queue
    private Comparator<Integer> comparator; // the comparator to use between the objects
    private static final int DEFAULT_CAPACITY = 4; // default capacity
    private static final int AUTOGROW_SIZE = 4; // default auto grow

    public PQueue(Comparator<Integer> comparator) {
        this.heap = new State[DEFAULT_CAPACITY + 1];
        this.size = 0;
        this.comparator = comparator;
    }

    public void add(State item) {
        if(size == heap.length - 1){
            grow();
        }
        size++;

        //find the next available position in the array
        heap[size] = item;

        //introduced item
        swim(size);

    }

    public State peek() {
        if (size==0) { return null;}
        return heap[1];

    }

    public State getMin() {
        if(size==0){ return null;}

        State element = heap[1];
        heap[1] = heap[size];
        size--;

        sink(1);
        return element;

    }

    private void swim(int i) {
        if (i==1) { return; }
        int parent = i/2;
        while(i!=1 && comparator.compare(heap[i].getHypothesis(),heap[parent].getHypothesis())< 0) {
            this.swap(i, parent);
            i = parent;
            parent = i/2;
        }
    }

    private void sink(int i) {
        int leftChildPos = 2*i;
        int rightChildPos = 2*i + 1;

        if(leftChildPos > size) {
            return;
        }

        while(leftChildPos <= size){
            int max = leftChildPos;
            if(rightChildPos <= size){
                if(comparator.compare(heap[leftChildPos].getHypothesis(), heap[rightChildPos].getHypothesis()) >0 ){
                    max = rightChildPos;
                }
            }

            if(comparator.compare(heap[i].getHypothesis(),heap[max].getHypothesis()) <=0 ) {
                break;
            }else{
                swap(i,max);
                i= max;
                leftChildPos = 2*i;
                rightChildPos = 2*i+1;
            }
        }
    }

    private void swap(int i, int j) {
        State tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void grow() {
        State[] newHeap = new State[heap.length + AUTOGROW_SIZE];

        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }

}