import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;


public class BruteCollinearPoints {
	private int size;
	private ArrayList<LineSegment> list = new ArrayList<>();
	public BruteCollinearPoints(Point[] points) {
		if (points == null) throw new java.lang.NullPointerException("The Point is null");
		checkrepeated(points) ;
		Point [] copypoint = Arrays.copyOf(points, points.length);
		Arrays.sort(copypoint);

		size = points.length;
		for (int i = 0; i < size; i++) {
			if (points[i] == null) throw new java.lang.NullPointerException("The Point is null");
		}
		for (int i = 0; i <= (size - 4); i++) {
			for (int j = i + 1; j <= (size - 3); j++) {

				for (int p = j + 1; p <= (size - 2); p++) {
					if (copypoint[i].slopeTo(copypoint[j]) == copypoint[i].slopeTo(copypoint[p])) {
						for (int q = p + 1; q <= (size - 1); q++) {
							if ( copypoint[i].slopeTo(copypoint[j]) == copypoint[i].slopeTo(copypoint[q])) {
								LineSegment ls = new LineSegment(copypoint[i], copypoint[q]);
								list.add(ls);
							}

						}
					}
				}
			}
		}

	}
	public int numberOfSegments() {
		return list.size();

	}
	public LineSegment[] segments()    {

		return list.toArray(new LineSegment[list.size()]);
	}

	private void checkrepeated(Point [] points) {
		for (int i = 0; i < (points.length - 2); i++) {
			for (int j = i + 1;  j < (points.length - 1); j++) {
				if (points[i].compareTo(points[j]) == 0)
					throw new java.lang.IllegalArgumentException();
			}
		}
	}

	public static void main(String[] args) {
		// read the n points from a file
		//In in = new In(args[0]);
		In in = new In("F:/360Downloads/collinear-testing/collinear/input40.txt");
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
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}

}