package algorithm;

import java.util.PriorityQueue;

class KthLargest_0703 {

    PriorityQueue<Integer> pq;
    int k;

    public KthLargest_0703(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(4);
        priorityQueue.offer(8);
        priorityQueue.offer(2);
        System.out.println(priorityQueue.peek());
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */