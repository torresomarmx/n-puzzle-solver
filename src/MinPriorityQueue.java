import java.util.Comparator;

public class MinPriorityQueue<Key> {
    private Key[] heap;
    private int numberOfElements;
    private static int INITIAL_HEAP_SIZE = 10;
    private Comparator<Key> comparator;
    public MinPriorityQueue() {
        this.heap = (Key[]) new Object[INITIAL_HEAP_SIZE +  1]; // + 1 for easier calculations
        this.numberOfElements = 0;
    }

    public void deleteMin() {

    }

    public void insert(Key x) {
       if (this.numberOfElements == (this.heap.length - 1)) // -1 since first entry is empty
           this.resize(2 * this.heap.length);
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
       while (currentIdx > 1 && this.isGreater(this.heap[currentIdx], this.heap[currentIdx/2])) {
          Key temp = this.heap[currentIdx];
          this.heap[currentIdx] = this.heap[currentIdx/2];
          this.heap[currentIdx/2] = temp;
          currentIdx /= 2;
       }
    }

    private boolean isGreater(Key element, Key elementToCompareTo) {
        if (this.comparator == null) {
            return ((Comparable<Key>) element).compareTo(elementToCompareTo) > 0;
        }
        return this.comparator.compare(element, elementToCompareTo) > 0;
    }
}
