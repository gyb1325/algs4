import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size_num;
	private Item[] s;
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < size_num; i++)
			copy[i] = s[i];
		s = copy;
	}

	public RandomizedQueue() {
		s = (Item[]) new Object[1];
	}
	public boolean isEmpty() {
		return size_num == 0;
	}
	public int size() {
		return size_num;
	}
	public void enqueue(Item item) {
		if (item == null) throw new java.lang.NullPointerException("The null item is added");
		if (size_num == s.length)resize(2 * s.length);
		s[size_num++] = item;
	}
	public Item dequeue() {
		if (size_num == 0) throw new java.util.NoSuchElementException("No element exists");
		int index = StdRandom.uniform(0, size_num);
		Item item = s[index];
		for (int i = index; i <= (size_num - 2); i++) {
			s[i] = s[i + 1];
		}
		size_num--;
		if (size_num <= s.length / 4) {
			resize(s.length / 2);
		}
		return item;

	}
	public Item sample() {
		if (size_num == 0) throw new java.util.NoSuchElementException("No element exists");
		int index = StdRandom.uniform(0, size_num);
		return s[index];
	}
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	private class RandomizedQueueIterator implements Iterator<Item> {
		private int i = size_num;
		boolean [] indicator = new boolean[size_num];
		public boolean hasNext() { return i > 0;}
		public void remove() { throw new java.lang.UnsupportedOperationException("This operation is not allowed"); }
		public Item next() {
			if (i > 0)
				while (true) {
					int index = StdRandom.uniform(0, size_num);
					if (!indicator[index]) {
						indicator[index] = true;
						i--;
						return s[index];
					}
				}
			else
				throw new java.util.NoSuchElementException("All elements have been accessed");
		}
	}
	public static void main(String[] args) {
		int a;
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(1);
		rq.enqueue(2);
		rq.enqueue(3);
		rq.enqueue(4);
		rq.enqueue(5);
		rq.enqueue(6);
		// StdOut.println("The size is " + rq.size());
		// a = rq.dequeue();
		// StdOut.println("The number is " + a);
		// a = rq.dequeue();
		// StdOut.println("The number is " + a);
		// a = rq.dequeue();
		// StdOut.println("The number is " + a);
		// StdOut.println("The size is " + rq.size());
		// rq.enqueue(1);
		// rq.enqueue(2);
		// rq.enqueue(3);
		// StdOut.println("The size is " + rq.size());
		// a = rq.dequeue();
		// StdOut.println("The number is " + a);
		// a = rq.dequeue();
		// StdOut.println("The number is " + a);
		// a = rq.dequeue();
		Iterator<Integer> it1 = rq.iterator();
		Iterator<Integer> it2 = rq.iterator();
		while (it1.hasNext()) {
			int b = it1.next();
			StdOut.println(b);
		}
		StdOut.println("THe next iterator: ");
		while (it2.hasNext()) {
			int b = it2.next();
			StdOut.println(b);
		}
	}
}