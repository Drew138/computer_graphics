public class Matrix4x4 {
	public double[] row1;
	public double[] row2;
	public double[] row3;
	public double[] row4;

	public Matrix4x4(double[] row1, double[] row2, double[] row3, double[] row4) {
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
		this.row4 = row4;
	}

	public static Point4 times(Matrix4x4 matrix, Point4 point) {
		double a = matrix.row1[0] * point.x + matrix.row1[1] * point.y + matrix.row1[2] * point.z
				+ matrix.row1[3] * point.w;
		double b = matrix.row2[0] * point.x + matrix.row2[1] * point.y + matrix.row2[2] * point.z
				+ matrix.row2[3] * point.w;
		double c = matrix.row3[0] * point.x + matrix.row3[1] * point.y + matrix.row3[2] * point.z
				+ matrix.row3[3] * point.w;
		double w = matrix.row4[0] * point.x + matrix.row4[1] * point.y + matrix.row4[2] * point.z
				+ matrix.row4[3] * point.w;
		Point4 newPoint = new Point4(a, b, c, w);
		newPoint.normalize();
		return newPoint;
	}

	public static Matrix4x4 times(Matrix4x4 m1, Matrix4x4 m2) {

		double a = m1.row1[0] * m2.row1[0] + m1.row1[1] * m2.row2[0] + m1.row1[2] * m2.row3[0]
				+ m1.row1[3] * m2.row4[0];
		double b = m1.row1[0] * m2.row1[1] + m1.row1[1] * m2.row2[1] + m1.row1[2] * m2.row3[1]
				+ m1.row1[3] * m2.row4[1];
		double c = m1.row1[0] * m2.row1[2] + m1.row1[1] * m2.row2[2] + m1.row1[2] * m2.row3[2]
				+ m1.row1[3] * m2.row4[2];
		double d = m1.row1[0] * m2.row1[3] + m1.row1[1] * m2.row2[3] + m1.row1[2] * m2.row3[3]
				+ m1.row1[3] * m2.row4[3];

		double e = m1.row2[0] * m2.row1[0] + m1.row2[1] * m2.row2[0] + m1.row2[2] * m2.row3[0]
				+ m1.row2[3] * m2.row4[0];
		double f = m1.row2[0] * m2.row1[1] + m1.row2[1] * m2.row2[1] + m1.row2[2] * m2.row3[1]
				+ m1.row2[3] * m2.row4[1];
		double g = m1.row2[0] * m2.row1[2] + m1.row2[1] * m2.row2[2] + m1.row2[2] * m2.row3[2]
				+ m1.row2[3] * m2.row4[2];
		double h = m1.row2[0] * m2.row1[3] + m1.row2[1] * m2.row2[3] + m1.row2[2] * m2.row3[3]
				+ m1.row2[3] * m2.row4[3];

		double i = m1.row3[0] * m2.row1[0] + m1.row3[1] * m2.row2[0] + m1.row3[2] * m2.row3[0]
				+ m1.row3[3] * m2.row4[0];
		double j = m1.row3[0] * m2.row1[1] + m1.row3[1] * m2.row2[1] + m1.row3[2] * m2.row3[1]
				+ m1.row3[3] * m2.row4[1];
		double k = m1.row3[0] * m2.row1[2] + m1.row3[1] * m2.row2[2] + m1.row3[2] * m2.row3[2]
				+ m1.row3[3] * m2.row4[2];
		double l = m1.row3[0] * m2.row1[3] + m1.row3[1] * m2.row2[3] + m1.row3[2] * m2.row3[3]
				+ m1.row3[3] * m2.row4[3];

		double m = m1.row4[0] * m2.row1[0] + m1.row4[1] * m2.row2[0] + m1.row4[2] * m2.row3[0]
				+ m1.row4[3] * m2.row4[0];
		double n = m1.row4[0] * m2.row1[1] + m1.row4[1] * m2.row2[1] + m1.row4[2] * m2.row3[1]
				+ m1.row4[3] * m2.row4[1];
		double o = m1.row4[0] * m2.row1[2] + m1.row4[1] * m2.row2[2] + m1.row4[2] * m2.row3[2]
				+ m1.row4[3] * m2.row4[2];
		double p = m1.row4[0] * m2.row1[3] + m1.row4[1] * m2.row2[3] + m1.row4[2] * m2.row3[3]
				+ m1.row4[3] * m2.row4[3];
		double[] row1 = { a, b, c, d };
		double[] row2 = { e, f, g, h };
		double[] row3 = { i, j, k, l };
		double[] row4 = { m, n, o, p };
		return new Matrix4x4(row1, row2, row3, row4);
	}

	public static Matrix4x4 createTranslationalMatrix(double dx, double dy, double dz) {
		double[] row1 = { 1.0, 0.0, 0.0, dx };
		double[] row2 = { 0.0, 1.0, 0.0, dy };
		double[] row3 = { 0.0, 0.0, 1.0, dz };
		double[] row4 = { 0.0, 0.0, 0.0, 1.0 };
		return new Matrix4x4(row1, row2, row3, row4);
	}

	public static Matrix4x4 createRotationalMatrixZ(double angle) {
		double rads = Math.PI / 180;
		double[] row1 = { Math.cos(rads * angle), -Math.sin(rads * angle), 0.0, 0.0 };
		double[] row2 = { Math.sin(rads * angle), Math.cos(rads * angle), 0.0, 0.0 };
		double[] row3 = { 0.0, 0.0, 1.0, 0.0 };
		double[] row4 = { 0.0, 0.0, 0.0, 1.0 };
		return new Matrix4x4(row1, row2, row3, row4);
	}

	public static Matrix4x4 createRotationalMatrixX(double angle) {
		double rads = Math.PI / 180;
		double[] row1 = { 1.0, 0.0, 0.0, 0.0 };
		double[] row2 = { 0.0, Math.cos(rads * angle), -Math.sin(rads * angle), 0.0 };
		double[] row3 = { 0.0, Math.sin(rads * angle), Math.cos(rads * angle), 0.0 };
		double[] row4 = { 0.0, 0.0, 0.0, 1.0 };
		return new Matrix4x4(row1, row2, row3, row4);
	}

	public static Matrix4x4 createRotationalMatrixY(double angle) {
		double rads = Math.PI / 180;
		double[] row1 = { Math.cos(rads * angle), 0.0, Math.sin(rads * angle), 0.0 };
		double[] row2 = { 0.0, 1.0, 0.0, 0.0 };
		double[] row3 = { -Math.sin(rads * angle), 0.0, Math.cos(rads * angle), 0.0 };
		double[] row4 = { 0.0, 0.0, 0.0, 1.0 };
		return new Matrix4x4(row1, row2, row3, row4);
	}

	public static Matrix4x4 createScalingMatrix(double x, double y, double z) {
		double[] row1 = { x, 0.0, 0.0, 0.0 };
		double[] row2 = { 0.0, y, 0.0, 0.0 };
		double[] row3 = { 0.0, 0.0, z, 0.0 };
		double[] row4 = { 0.0, 0.0, 0.0, 1.0 };
		return new Matrix4x4(row1, row2, row3, row4);
	}

	public static Matrix4x4 createProjectionMatrix(double d) {
		double[] row1 = { 1.0, 0.0, 0.0, 0.0 };
		double[] row2 = { 0.0, 1.0, 0.0, 0.0 };
		double[] row3 = { 0.0, 0.0, 1.0, 0.0 };
		double[] row4 = { 0.0, 0.0, 1.0 / d, 0.0 };
		return new Matrix4x4(row1, row2, row3, row4);
	}

	public String toString() {
		return "" + "[" + this.row1[0] + " " + this.row1[1] + " " + this.row1[2] + " " + this.row1[3] + "]" + "\n" + "["
				+ this.row2[0] + " " + this.row2[1] + " " + this.row2[2] + " " + this.row2[3] + "]" + "\n" + "["
				+ this.row3[0] + " " + this.row3[1] + " " + this.row3[2] + " " + this.row3[3] + "]" + "\n" + "["
				+ this.row4[0] + " " + this.row4[1] + " " + this.row4[2] + " " + this.row4[3] + "]" + "\n";

	}
}
