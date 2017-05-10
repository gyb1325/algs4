
import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	private class Node {
		public Point2D p;
		public RectHV rect;
		Node left;
		Node right;
		public Node(Point2D point, RectHV rectangle) {
			p = point;
			rect = rectangle;
			left = null;
			right = null;
		}

	}

	private Node kd_tree;
	private int size_num;

	public KdTree() {                             // construct an empty set of points
		size_num = 0;
	}
	public boolean isEmpty() {                    // is the set empty?
		return (size_num == 0);
	}
	public int size() {                       // number of points in the set
		return size_num;
	}
	public void insert(Point2D p) {            // add the point to the set (if it is not already in the set)
		kd_tree = insert(kd_tree, p, 0.0, 0.0, 1.0, 1.0, 0);
	}

	private Node insert(Node root, Point2D points, double xmin, double ymin, double xmax, double ymax, int level) {
		if (root == null) {
			size_num++;
			return new Node(points, new RectHV(xmin, ymin, xmax, ymax));
		}
		int cmp = cmp(root.p, points, level);
		if (cmp > 0) {
			if (level % 2 == 0) {
				root.left = insert(root.left, points, xmin, ymin, root.p.x(), ymax, level + 1);
			} else {
				root.left = insert(root.left, points, xmin, ymin, xmax, root.p.y(), level + 1);
			}
		} else if (cmp < 0) {
			if (level % 2 == 0) {
				root.right = insert(root.right, points, root.p.x(), ymin, xmax, ymax, level + 1);
			} else {
				root.right = insert(root.right, points, xmin, root.p.y(), xmax, ymax, level + 1);
			}
		}
		return root;
	}
	public boolean contains(Point2D p) {          // does the set contain point p?
		return p.equals(contains(kd_tree, p, 0));
	}

	private int cmp(Point2D a, Point2D b, int level) {
		if (level % 2 == 0) {
			if (a.x() > b.x()) return 1;
			else if (a.x() < b.x()) return -1;
			else return a.compareTo(b);
		} else {
			if (a.y() > b.y()) return 1;
			else if (a.y() < b.y()) return -1;
			else return a.compareTo(b);
		}
	}
	private Point2D contains(Node root, Point2D points, int level) {
		while (root != null) {
			int cmp = cmp(root.p, points, level);
			if (cmp < 0) {
				return contains(root.right, points, level + 1);
			} else if (cmp > 0) {
				return contains(root.left, points, level + 1);
			} else {
				return root.p;
			}
		}
		return null;
	}


	public void draw() {                       // draw all points to standard draw
		StdDraw.clear();
		drawline(kd_tree, 0);
	}
	private void drawline(Node root, int level) {

		if (root != null) {

			StdDraw.setPenRadius();
			if (level % 2 == 0) {
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.line(root.p.x(), root.rect.ymin(), root.p.x(), root.rect.ymax());
			} else {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(root.rect.xmin(), root.p.y() , root.rect.xmax(), root.p.y() );
			}

			StdDraw.setPenRadius(0.01);
			StdDraw.setPenColor(StdDraw.BLACK);
			root.p.draw();
			drawline(root.left, level + 1);
			drawline(root.right, level + 1);
		}
	}
	public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle
		TreeSet<Point2D> set = new TreeSet<Point2D>();
		PointsAdd(kd_tree, rect, set);
		return set;
	}
	private void PointsAdd(Node root, RectHV rectangle, TreeSet<Point2D> set) {
		if (root != null && rectangle.intersects(root.rect)) {
			if (rectangle.contains(root.p)) set.add(root.p);
			PointsAdd(root.left, rectangle, set);
			PointsAdd(root.right, rectangle, set);
		}
	}
	public Point2D nearest(Point2D p) {           // a nearest neighbor in the set to point p; null if the set is empty
		if (size_num == 0) return null;
		else {
			Point2D result = null;
			result = nearest(kd_tree, p, result);
			return result;
		}
	}

	private Point2D nearest(Node root, Point2D points, Point2D min) {
		if (root != null) {
			if (min == null)
				min = root.p;
			if (min.distanceTo(points) >= root.rect.distanceTo(points)) {
				if (root.p.distanceTo(points) < min.distanceTo(points))	min = root.p;
				if (root.right != null && root.right.rect.contains(points)) {
					min = nearest(root.right, points, min);
					min = nearest(root.left, points, min);
				} else {
					min = nearest(root.left, points, min);
					min = nearest(root.right, points, min);
				}

			}
		}
		return min;
	}

	public static void main(String[] args) {                // unit testing of the methods (optional)

	}
}