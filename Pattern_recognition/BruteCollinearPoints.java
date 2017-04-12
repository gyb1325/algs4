public class BruteCollinearPoints {
	private int size;
	private int num;
	private LineSegment [] seg;
	public BruteCollinearPoints(Point[] points) {
		if (points == null) throw new java.lang.NullPointerException("The Point is null");
		num = 0;

		size = points.length;
		for (int i = 0; i < size; i++) {
			if (point[i] == null) throw new java.lang.NullPointerException("The Point is null");
		}
		seg = new LineSegment[size];
		Point max;
		Point min;
		for (int i = 0; i < (size - 4); i++) {
			Comparator<Point> cmp = points[i].slopeOrder();
			max = points[i];
			min = points[i];
			for (int j = i + 1; j < (size - 3); j++) {
				if (points[j].compareTo(max) > 0) max = points[j];
				if (points[j].compareTo(min) < 0) min = points[j];
				for (int p = j + 1; p < (size - 2); p++) {
					if (points[p].compareTo(max) > 0) max = points[p];
					if (points[p].compareTo(min) < 0) min = points[p];
					for (int q = p + 1; q < (size - 1); q++) {
						if (points[q].compareTo(max) > 0) max = points[q];
						if (points[q].compareTo(min) < 0) min = points[q];
						if (cmp.compare(points[j], points[p]) == 0 && cmp.compare(points[p], points[q]) == 0) {
							seg[num++] = new LineSegment(min, max);
						}


					}
				}
			}
		}

	}
	public int numberOfSegments() {
		return num;

	}
	public LineSegment[] segments()    {
		LineSegment [] res = new LineSegment[num];
		for (int i = 0; i < num; i++) {
			res[i] = seg[i];
		}
		return res;
	}
}