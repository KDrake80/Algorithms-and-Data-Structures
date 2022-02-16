/*
 * Kevin Drake
 * 2/16/22
 * Wrote this GenericQueue class to use inheritance with linkedList for 
 *  Assignment 24-5. Runs a test method to use the enqueue method and dequeue method.
 */
import java.util.*;

public class GenericQueue<E> extends LinkedList<E>{	
	public void enqueue(E e) {
		addLast(e);
	}
	public E dequeue() {
		return removeFirst();
	}
	public int getSize() {
		return getSize();
	}
	public static void test() {
		GenericQueue<String> q = new GenericQueue<>();
		q.enqueue("The");
		q.enqueue("Car");
		q.enqueue("Is");
		q.enqueue("Green");
		System.out.println(q);
		
		q.dequeue();
		System.out.println(q);
		q.dequeue();
		System.out.println(q);
		
	}
	public static void main(String[] args) {
		test();
	}
}
