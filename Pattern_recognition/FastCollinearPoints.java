import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class FastCollinearPoints {
	private ArrayList<LineSegment> linesegments;
	public FastCollinearPoints(Point[] points) {
		if (points == null)throw new java.lang.NullPointerException();
		checkrepeated(points) ;
		linesegments = new ArrayList<>();
		Point [] axl = Arrays.copyOf(points, points.length);
		//Arrays.sort(axl);
		ArrayList<Point> list = new ArrayList<>();
		for (Point element : points) {
			if (element == null) throw new java.lang.NullPointerException();
			Arrays.sort(axl, element.slopeOrder());
			for (int i = 1; i < axl.length; i++) {
				if (element.slopeTo(axl[i]) == element.slopeTo(axl[i - 1])) {
					list.add(axl[i]);
				} else {
					addtosegmentlist(list);
					list.clear();
					list.add(element);
					list.add(axl[i]);
				}
			}
		}

	}
	private void addtosegmentlist(ArrayList<Point> list) {
		if (list.size() < 4) return;
		Point [] points = list.toArray(new Point[list.size()]);
		Arrays.sort(points);
		LineSegment ls = new LineSegment(points[0], points[points.length - 1]);
		for (LineSegment element : linesegments) {
			if (ls.toString().equals(element.toString())) {
				return;
			}
		}
		linesegments.add(ls);
	}
	public int numberOfSegments() {
		return linesegments.size();
	}

	private void checkrepeated(Point [] points) {
		Point [] axl = Arrays.copyOf(points, points.length);
		Arrays.sort(axl);
		for (int i = 0; i < axl.length - 1; i++) {
			if (axl[i].compareTo(axl[i + 1]) == 0)
				throw new java.lang.IllegalArgumentException();
		}
	}
	public LineSegment[] segments() {
		return linesegments.toArray(new LineSegment[linesegments.size()]);
	}
	public static void main(String[] args) {
		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}