import java.util.Scanner;
import java.lang.Math;

class Matrix3x3 {

	public double[] row1;
	public double[] row2;
	public double[] row3;

	public Matrix3x3(double[] row1, double[] row2, double[] row3) {
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
	}

	public static Point3 times(Matrix3x3 matrix, Point3 point) {
		double a = matrix.row1[0] * point.x + matrix.row1[1] * point.y + matrix.row1[2] * point.w;
		double b = matrix.row2[0] * point.x + matrix.row2[1] * point.y + matrix.row2[2] * point.w;
		double c = matrix.row3[0] * point.x + matrix.row3[1] * point.y + matrix.row3[2] * point.w;
		return new Point3(a, b);
	}

	public static Matrix3x3 times(Matrix3x3 m1, Matrix3x3 m2) {

		double a = m1.row1[0] * m2.row1[0] + m1.row1[1] * m2.row2[0] + m1.row1[2] * m2.row3[0];
		double b = m1.row1[0] * m2.row1[1] + m1.row1[1] * m2.row2[1] + m1.row1[2] * m2.row3[1];
		double c = m1.row1[0] * m2.row1[2] + m1.row1[1] * m2.row2[2] + m1.row1[2] * m2.row3[2];
		double d = m1.row2[0] * m2.row1[0] + m1.row2[1] * m2.row2[0] + m1.row2[2] * m2.row3[0];
		double e = m1.row2[0] * m2.row1[1] + m1.row2[1] * m2.row2[1] + m1.row2[2] * m2.row3[1];
		double f = m1.row2[0] * m2.row1[2] + m1.row2[1] * m2.row2[2] + m1.row2[2] * m2.row3[2];
		double g = m1.row3[0] * m2.row1[0] + m1.row3[1] * m2.row2[0] + m1.row3[2] * m2.row3[0];
		double h = m1.row3[0] * m2.row1[1] + m1.row3[1] * m2.row2[1] + m1.row3[2] * m2.row3[1];
		double i = m1.row3[0] * m2.row1[2] + m1.row3[1] * m2.row2[2] + m1.row3[2] * m2.row3[2];
		double[] row1 = { a, b, c };
		double[] row2 = { d, e, f };
		double[] row3 = { g, h, i };
		return new Matrix3x3(row1, row2, row3);
	}

	public String toString() {
		return "" + "[" + this.row1[0] + " " + this.row1[1] + " " + this.row1[2] + "]" + "\n" + "[" + this.row2[0] + " "
				+ this.row2[1] + " " + this.row2[2] + "]" + "\n" + "[" + this.row3[0] + " " + this.row3[1] + " "
				+ this.row3[2] + "]";
	}

	public static Matrix3x3 readMatrix() {
		double a, b, c, d, e, f, g, h, i;
		Scanner scan = new Scanner(System.in);
		a = scan.nextDouble();
		b = scan.nextDouble();
		c = scan.nextDouble();
		d = scan.nextDouble();
		e = scan.nextDouble();
		f = scan.nextDouble();
		g = scan.nextDouble();
		h = scan.nextDouble();
		i = scan.nextDouble();
		double[] row1 = { a, b, c };
		double[] row2 = { d, e, f };
		double[] row3 = { g, h, i };
		scan.close();
		return new Matrix3x3(row1, row2, row3);
	}

	public static Matrix3x3 createTranslationalMatrix(double dx, double dy) {
		double[] row1 = { 1.0, 0.0, dx };
		double[] row2 = { 0.0, 1.0, dy };
		double[] row3 = { 0.0, 0.0, 1.0 };
		return new Matrix3x3(row1, row2, row3);
	}

	public static Matrix3x3 createRotationalMatrix(double angle) {
		double rads = Math.PI / 180;
		double[] row1 = { Math.cos(rads * angle), -Math.sin(rads * angle), 0.0 };
		double[] row2 = { Math.sin(rads * angle), Math.cos(rads * angle), 0.0 };
		double[] row3 = { 0.0, 0.0, 1.0 };
		return new Matrix3x3(row1, row2, row3);
	}

	public static Matrix3x3 createScalingMatrix(double x, double y) {
		double[] row1 = { x, 0.0, 0.0 };
		double[] row2 = { 0.0, y, 0.0 };
		double[] row3 = { 0.0, 0.0, 1.0 };
		return new Matrix3x3(row1, row2, row3);

	}

	public static void main(String[] args) {
		Matrix3x3 m1, m2;
		m1 = readMatrix();
		m2 = readMatrix();
		System.out.println(times(m1, m2));
	}
}