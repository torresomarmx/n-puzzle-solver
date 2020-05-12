import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinPriorityQueue<Key> {
    private Key[] heap;
    private int numberOfElements;
    private static int INITIAL_HEAP_SIZE = 10;
    private Comparator<Key> comparator;
    public MinPriorityQueue() {
        this.heap = (Key[]) new Object[INITIAL_HEAP_SIZE +  1]; // + 1 for easier calculations
        this.numberOfElements = 0;
    }

    public MinPriorityQueue(Comparator<Key> comparator) {
        this.heap = (Key[]) new Object[INITIAL_HEAP_SIZE + 1];
        this.comparator = comparator;
        this.numberOfElements = 0;
    }

    public Key deleteMin() {
       if (numberOfElements == 0) throw new NoSuchElementException();
       Key min = this.heap[1];
       this.heap[1] = this.heap[this.numberOfElements];
       this.heap[this.numberOfElements] = null;
       this.sink(1);
       this.numberOfElements -= 1;
       if (this.numberOfElements == (this.heap.length - 1) / 4)
           this.resize((this.heap.length - 1) / 2);
       return min;
    }

    public void insert(Key x) {
       if (this.numberOfElements == (this.heap.length - 1)) // -1 since first entry is empty
           this.resize(2 * (this.heap.length - 1));
       this.heap[++this.numberOfElements] = x;
       this.swim(this.numberOfElements);
    }

    public int numberOfElements() {
       return this.numberOfElements;
    }


    private void resize(int newSize) {
        Key[] newHeap = (Key[]) new Object[newSize];
        for (int i = 0; i < this.heap.length; i++)
            newHeap[i] = this.heap[i];
        this.heap = newHeap;
    }

    private void swim(int elementIdx) {
       int currentIdx = elementIdx;
       // compare with parent. parent must be smaller
       while (currentIdx > 1 && this.isGreater(this.heap[currentIdx/2], this.heap[currentIdx])) {
          Key temp = this.heap[currentIdx];
          this.heap[currentIdx] = this.heap[currentIdx/2];
          this.heap[currentIdx/2] = temp;
          currentIdx /= 2;
       }
    }

    private void sink(int elementIdx) {
       while ( 2*elementIdx <= this.numberOfElements) {
           int childIdx = elementIdx * 2;
           if (childIdx < this.numberOfElements && this.isGreater(this.heap[childIdx], this.heap[childIdx + 1]))
               childIdx += 1;
           if (this.isGreater(this.heap[childIdx], this.heap[elementIdx]))
               break;
           Key temp = this.heap[elementIdx];
           this.heap[elementIdx] = this.heap[childIdx];
           this.heap[childIdx] = temp;
           elementIdx = childIdx;
       }
    }

    private boolean isGreater(Key element, Key elementToCompareTo) {
        if (this.comparator == null)
            return ((Comparable<Key>) element).compareTo(elementToCompareTo) > 0;
        return this.comparator.compare(element, elementToCompareTo) > 0;
    }

    public static void main(String[] args) {}
}
