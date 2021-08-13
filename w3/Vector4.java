import java.util.Math;

public class Vector4 {

	public double x;
	public double y;
	public double z;
	public double w;

	pubic Vector4(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public static Vector4 crossProduct(Vector4 v1, Vector4 v2) {
		double x, y, z, w = 1;
		x = v1.y * v2.z - v1.z * v2.y;
		y = -(v1.x * v2.z - v1.z * v2.x);
		z = v1.x * v2.y - v1.y * v2.x;
		return new Vector4(x, y, z, w);
	}

	public static double dotProduct(Vector4 v1, Vector4 v2) {

		return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z + v1.w * v2.w;
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public void normalize() {
		double norm = this.magnitude();
		x /= norm;
		y /= norm;
		z /= norm;
	}
}
