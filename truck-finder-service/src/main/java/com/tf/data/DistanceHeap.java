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
        while (index > 0 && items[index].getDistance() > items[parent(index)].getDistance()) {
            // swap the items
            swap(index, parent(index));
            index = parent(index);
        }

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
