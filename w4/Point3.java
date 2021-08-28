public class Point3 {

	public double x;
	public double y;
	public double w = 1.0;
	private static double width;
	private static double height;

	public Point3(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point3(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}

	public void javaToCartesian() {
		this.x -= (width / 2);
		this.y = (height / 2) - this.y;
	}

	public void cartesianToJava() {
		this.x += (width / 2);
		this.y = (height / 2) - this.y;
	}

	public String toString() {
		return "x: " + this.x + " y: " + this.y;
	}
}