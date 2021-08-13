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

	public static Point4 times(Matrix3x3 matrix, Point3 point) {
		double a = matrix.row1[0] * point.x + matrix.row1[1] * point.y + matrix.row1[2] * point.z
				+ matrix.row1[3] * point.w;
		double b = matrix.row2[0] * point.x + matrix.row2[1] * point.y + matrix.row2[2] * point.z
				+ matrix.row2[3] * point.w;
		double c = matrix.row3[0] * point.x + matrix.row3[1] * point.y + matrix.row3[2] * point.z
				+ matrix.row3[3] * point.w;
		double d = matrix.row4[0] * point.x + matrix.row4[1] * point.y + matrix.row4[2] * point.z
				+ matrix.row4[3] * point.w;
		return new Point4(a, b, c, d);
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
		return new Matrix3x3(row1, row2, row3, row4);
	}

	public String toString() {
		return "" + 
		"[" + this.row1[0] + " " + this.row1[1] + " " + this.row1[2] + " " + this.row1[3] + "]" + "\n" + 
		"[" + this.row2[0] + " " + this.row2[1] + " " + this.row2[2] + " " + this.row2[3] + "]" + "\n" + 
		"[" + this.row3[0] + " " + this.row3[1] + " " + this.row3[2] + " " + this.row3[3] + "]" + "\n" + 
		"[" + this.row4[0] + " " + this.row4[1] + " " + this.row4[2] + " " + this.row4[3] + "]" + "\n" + 

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
		j = scan.nextDouble();
		k = scan.nextDouble();
		l = scan.nextDouble();
		m = scan.nextDouble();
		n = scan.nextDouble();
		o = scan.nextDouble();
		p = scan.nextDouble();
		double[] row1 = { a, b, c, d };
		double[] row2 = { e, f, g, h };
		double[] row3 = { i, j, k, l };
		double[] row4 = { m, n, o, p };

		return new Matrix3x3(row1, row2, row3, row4);
	}

	public static void main(String[] args) {
		Matrix3x3 m1, m2;
		m1 = readMatrix();
		m2 = readMatrix();
		System.out.println(times(m1, m2));
}
