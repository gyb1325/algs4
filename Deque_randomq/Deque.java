/**
 * Auto Generated Java Class.
 */
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Deque<T> implements Iterable<T> {
	private Node first, last = null;
	private int size_num = 0;

	private class Node {
		T item;
		Node next;
		Node prev;
	}

	public Deque() {
	}



	public boolean isEmpty() {
		return size_num == 0;
	}

	public int size() {
		return size_num;
	}

	public void addFirst(T item) {
		if (item == null) throw new java.lang.NullPointerException("Add a null item is not allowed");
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (isEmpty()) last = first;
		if (!isEmpty())
			oldfirst.prev = first;
		size_num++;
	}

	public void addLast(T item) {
		if (item == null) throw new java.lang.NullPointerException("Add a null item is not allowed");
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.prev = oldlast;
		last.next = null;
		if (isEmpty())first = last;
		else oldlast.next = last;
		size_num++;
	}

	public T removeFirst() {
		if (isEmpty())throw new java.lang.UnsupportedOperationException("The deque is empty");
		T item = first.item;
		first = first.next;
		if (first != null)first.prev = null;
		else {
			first = null;
			last = null;
		}
		size_num--;
		return item;
	}

	public T removeLast() {
		if (isEmpty())throw new java.lang.UnsupportedOperationException("The deque is empty");
		T item = last.item;
		last = last.prev;
		if (last != null)last.next = null;
		else {
			first = null;
			last = null;
		}
		size_num--;
		return item;
	}

	public Iterator<T> iterator() { return new Dequeiterator(); }

	private class Dequeiterator implements Iterator<T> {
		private Node current = first;

		public boolean hasNext() {return current != null;}
		public T next() {
			if (current == null)throw new java.util.NoSuchElementException("The iterator has no more elements");
			T item = current.item;
			current = current.next;
			return item;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException("This operation is not allowed");
		}
	}

	public static void main(String[] args) {
		// Deque<Integer> d = new Deque<Integer>();
		// //d.addFirst(null);
		// d.addFirst(1);
		// d.addFirst(2);
		// d.addFirst(3);
		// StdOut.println("The size is" + d.size());
		// d.addFirst(4);
		// d.addLast(5);
		// d.addLast(6);
		// d.addLast(7);
		// d.addLast(8);
		// StdOut.println("The size is" + d.size());
		// d.removeLast();
		// d.removeLast();
		// d.removeLast();
		// d.removeLast();
		// d.removeLast();
		// d.removeLast();
		// StdOut.println("The size is" + d.size());
		// d.removeLast();
		// StdOut.println("The size is" + d.size());
		// d.removeLast();
		// StdOut.println("The size is" + d.size());
		// d.addFirst(4);
		// d.addLast(5);
		// d.addLast(6);
		// StdOut.println("The size is" + d.size());
		// //d.removeLast();
		// //StdOut.println("The size is" + d.size());
		// //d.removeFirst();
		// //StdOut.println("The size is" + d.size());
		// Iterator<Integer> i = d.iterator();
		// while (i.hasNext()) {
		// 	int a = i.next();
		// 	StdOut.println(a);
		//}

	}
}