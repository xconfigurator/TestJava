package cn.edu.hebau.liuyang.datastructures;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HelloPriorityQueue {

	static int a[] = { 9, 8, 7, 6, 5, 11, 12, 13, 14, 88, 99 };

	public static void main(String[] args) {
		// 小顶堆
		PriorityQueue<Integer> heapMin = new PriorityQueue<>();
		testHeap(heapMin);

		// 大顶堆
		PriorityQueue<Integer> heapMax = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		testHeap(heapMax);
	}

	public static void testHeap(PriorityQueue<Integer> heap) {
		for (int i : a) {
			heap.add(i);
		}
		while (!heap.isEmpty()) {
			System.out.print(heap.poll() + "\t");
		}
		System.out.println("");
	}

}
