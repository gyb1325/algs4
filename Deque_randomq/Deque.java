/**
 * Auto Generated Java Class.
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Node first, last = null;
	private int size_num = 0;

	private class Node() {
		Item item;
		Node next;
		Node prev;
	}

	public Deque() {

	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size_num;
	}

	public void addFirst(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (oldfirst != null)
			oldfirst.prev = first;
		size_num++;
	}

	public void addLast(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.prev = oldlast;
		last.next = null;
		if (isEmpty)first = last;
		else oldlast.next = last;
		size_num++;
	}

	public Item removeFirst() {
		if (isEmpty())throw new java.lang.UnsupportedOperationException("The deque is empty");
		Item item = first.item;
		first = first.next;
		first.prev = null;
		size_num--;
		return item;
	}

	public Item removeLast() {
		if (isEmpty())throw new java.lang.UnsupportedOperationException("The deque is empty");
		Item item = last.item;
		last = last.prev;
		last.next = null;
		size_num--;
		return item;
	}

	public Iterator<Item> iterator() {	return new Dequeiterator();	}

	private class Dequeiterator implements Iterator<item> {
		private Node current = first;

		public boolean hasNext() {return current != null;}
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {

	}
}