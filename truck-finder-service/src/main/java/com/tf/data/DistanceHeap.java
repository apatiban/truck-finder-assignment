package com.tf.data;

public class DistanceHeap {

    private int size;
    private Distance[] items = null;
    private int count;

    public DistanceHeap(int size) {
        this.size = size;
        items = new Distance[size];
    }

    public void insert(Distance distance) {
        if (isFull())
            throw new IllegalStateException();
        items[count++] = distance;
        var index = count - 1;
        while (index > 0 && items[index].getDistance() < items[parent(index)].getDistance()) {
            // swap the items
            swap(index, parent(index));
            index = parent(index);
        }

    }

    public Distance remove() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        var root = items[0];
        items[0] = items[--count];
        var index = 0;
        // bubbleDown
        // item(root) < children
        while (index <= count && !isValidParent(index)) {
            // swap it with the larger children (right and left);
            var largerChildIndex = largerChildIndex(index);
            swap(index, largerChildIndex);
            index = largerChildIndex;
        }
        return root;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index))
            return true;
        if (!hasRightChild(index))
            return items[index].getDistance() <= leftChildDistance(index);
        return items[index].getDistance() <= leftChildDistance(index)
                && items[index].getDistance() <= rightChildDistance(index);
    }

    private int largerChildIndex(int index) {
        if (!hasLeftChild(index))
            return index;
        if (!hasRightChild(index))
            return leftchildIndex(index);
        return leftChildDistance(index) > rightChildDistance(index) ? leftchildIndex(index)
                : rightChildIndex(index);
    }

    private double leftChildDistance(int index) {
        return items[leftchildIndex(index)].getDistance();
    }

    private double rightChildDistance(int index) {
        return items[rightChildIndex(index)].getDistance();
    }

    private int leftchildIndex(int index) {
        return index * 2 + 1;
    }

    private boolean hasLeftChild(int index) {
        return leftchildIndex(index) <= count;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) <= count;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private boolean isFull() {
        return count == items.length;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int first, int second) {
        var temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }

}
