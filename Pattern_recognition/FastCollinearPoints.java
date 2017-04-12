import java.util.ArrayList;
import java.util.Arrays;


public class FastCollinearPoints {
	private int num;
	ArrayList<LineSegment> linesegments;
	public FastCollinearPoints(Point[] points) {

		linesegments = new ArrayList<>();
		Point [] axl = Arrays.copyOf(points, points.length);
		Arrays.sort(axl);
		ArrayList<Point> list = new ArrayList<>();
		for (Point element : points) {
			Arrays.sort(axl, element.slopeOrder());
			for (int i = 1; i < axl.length; i++) {
				if (element.slopeTo(axl[i])) == element.slopeTo(axl[i - 1]) {
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

	public void addtosegmentlist(ArrayList<Piont> list) {
		if (list.size() < 4) return;
		Point [] points = list.toArray(new Point[list.size]);
		Arrays.sort(points);
		LineSegment ls = new LineSegment(points[0], points[points.length - 1]);
		for (Point element : linesegments) {
			if (ls.toString().equals(element.toString())) {
				return;
			}
		}
		linesegments.add(ls)
	}
	public int numberOfSegments() {
		return linesegments.size();
	}
	public LineSegment[] segments() {
		return linesegments.toArray(new LineSegment[linesegments.size()]);
	}
}