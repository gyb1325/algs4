public class FastCollinearPoints {
	private ArrayList<LineSegment> slopelist;
	private int nums;
	public FastCollinearPoints(Point[] points) {
		slopelist = new ArrayList<>();
		Point [] axl = Arrays.copyOf(points, points.length);
		Arrays.sort(axl);
		ArrayList<Point> list = new ArrayList<>();
		for (Point element : points) {
			Arrays.sort(axl, slopeOrder());
			for (int i = 1; i < points.length; i++) {
				if (element.slopeTo(axl[i]) == element.slopeTo(axl[i - 1])) {
					list.add(slopeTo(axl[i]);
				} else {
					addsegment(list);
					list.clear();
					list.add(element);
					list.add(axl[i]);
				}

			}
		}
	}
	public void addsegment(ArrayList<Point> list) {
		if (list.size < 4) return;
		Point [] copy = list.toArray(new Point[list.size]) ;
		Arrays.sort(copy);
		LineSegment ls = new LineSegment(copy[0], copy[list.size - 1]);
		for (Point element : slopelist) {
			if (ls.toString().equals(element.toString())) return;
		}
		slopelist.add(ls);
	}
	public           int numberOfSegments()
	public LineSegment[] segments() {
		return slopelist.toArray(new LineSegment[slopelist.size()] );
	}
}