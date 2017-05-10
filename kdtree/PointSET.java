import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

public class PointSET {
	private TreeSet<Point2D> set_contain ;
	private TreeSet<Point2D> set;
	private double min_distance;
	private Point2D point_min;
	public         PointSET() {                             // construct an empty set of points
		set = new TreeSet<Point2D>();
		set_contain = new TreeSet<Point2D>();
	}
	public boolean isEmpty() {                    // is the set empty?
		return set.isEmpty();
	}
	public int size() {                       // number of points in the set
		return set.size();
	}
	public void insert(Point2D p) {            // add the point to the set (if it is not already in the set)
		if (p == null) throw new java.lang.NullPointerException();
		if (!set.contains(p)) set.add(p);
	}
	public boolean contains(Point2D p) {          // does the set contain point p?
		if (p == null) throw new java.lang.NullPointerException();
		return set.contains(p);
	}
	public void draw() {                       // draw all points to standard draw
		StdDraw.clear();
		for (Point2D element : set) {
			element.draw();
		}
	}
	public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle
		if (rect == null) throw new java.lang.NullPointerException();

		for (Point2D element : set) {
			if (rect.contains(element)) {
				set_contain.add(element);
			}
		}
		return set_contain;
	}
	public Point2D nearest(Point2D p) {           // a nearest neighbor in the set to point p; null if the set is empty
		if (set.isEmpty()) return null;
		min_distance = Double.MAX_VALUE;
		for (Point2D element : set) {
			if (p.distanceTo(element) < min_distance) {
				min_distance = p.distanceTo(element);
				point_min = element;
			}
		}
		return point_min;
	}
	public static void main(String[] args) {                // unit testing of the methods (optional)

	}
}