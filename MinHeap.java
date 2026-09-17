import java.util.*;

public class MinHeap {
    int[] heap;
    int size;
    int capacity;

    MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        heap = new int[capacity];
    }
    // Time Complexity: O(1)
    int left(int i) {
        return 2*i+1;
    }
    // Time Complexity: O(1)
    int right(int i) {
        return 2*i+2;
    }
    // Time Complexity: O(1)
    int parent(int i) {
        return (i-1)/2;
    }
    // Time Complexity: O(1)
    int getMin() {
        if(size == 0) throw new RuntimeException("Heap is Empty");
        return heap[0];
    }
    // Time Complexity: O(1)
    void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    // Time Complexity: O(log n) - Swimming up the heap
    void insert(int key) {
        if(size == capacity) {
            throw new RuntimeException("Heap Overflow");
        }
        int i = size;
        heap[size++] = key;
        while(i!=0 && heap[parent(i)]>heap[i]) {
            swap(i,parent(i));
            i=parent(i);
        }
    }
    // Time Complexity: O(log n) - Sinking down the heap
    void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if(l<size && heap[l]<heap[smallest]) {
            smallest = l;
        }
        if(r<size && heap[r]<heap[smallest]) {
            smallest = r;
        }
        if(smallest != i) {
            swap(i,smallest);
            minHeapify(smallest);
        }
    }
    // Time Complexity: O(log n) - Calls minHeapify
    int extractMin() {
        if(size<=0) {
            throw new RuntimeException("Heap underflow");
        }
        if(size==1) {
            return heap[--size];
        }
        int root = heap[0];
        heap[0] = heap[--size];
        minHeapify(0);
        return root;
    }
    // Time Complexity: O(log n) - Swimming up the heap
    void decreaseKey(int i, int newVal) {
        heap[i] = newVal;
        while(i!=0 && heap[parent(i)]>heap[i]) {
            swap(parent(i),i);
            i=parent(i);
        }
    }
    // Time Complexity: O(log n) - Calls decreaseKey and extractMin
    void deletKey(int i) {
        decreaseKey(i,Integer.MIN_VALUE);
        extractMin();
    }
    // Time Complexity: O(n) - Iterates through all n elements
    void printHeap() {
        for(int i=0; i<size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    // Time Complexity: O(n) - Builds heap in linear time
    void buildHeap(int[] arr) {
        if(arr.length > capacity) {
            throw new RuntimeException("Capacity Exceeded");
        }
        size = arr.length;
        heap = Arrays.copyOf(arr, capacity);
        for(int i=parent(size-1); i>=0; i--) {
            minHeapify(i);
        }
    }
    public static void main(String[] args) {
        
    }
    
}
