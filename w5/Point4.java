public class Point4 {

	public double x;
	public double y;
	public double z;
	public double w = 1.0;
	private static double width;
	private static double height;

	public Point4(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point4(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Point4(double x, double y, double z, double wi, double hi) {
		this.x = x;
		this.y = y;
		this.z = z;
		width = wi;
		height = hi;
	}

	public void javaToCartesian() {
		this.x -= (width / 2);
		this.y = (height / 2) - this.y;
	}

	public void cartesianToJava() {
		this.x += (width / 2);
		this.y = (height / 2) - this.y;
	}

	public void normalize() {
		this.x /= this.w;
		this.y /= this.w;
		this.z /= this.w;
		this.w /= this.w;
	}

	public String toString() {
		return "x: " + this.x + " y: " + this.y + " z: " + this.z + " w: " + this.w;
	}
}