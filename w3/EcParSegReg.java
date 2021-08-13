class answer {
	public double u1;
	public double u2;

	public answer(double u1, double u2) {
		this.u1 = u1;
		this.u2 = u2;
	}
}

public class EcParSegReg {
	public double x1;
	public double y1;
	public double x2;
	public double y2;

	public EcParSegReg(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public static answer solve(EcParSegReg ec1, EcParSegReg ec2) {
		// find equation 1
		double m1 = (ec1.y2 - ec1.y1) / (ec1.x2 - ec1.x1);
		double b1 = ec1.y1 - m1 * ec1.x1;
		// find equation 2
		double m2 = (ec2.y2 - ec2.y1) / (ec2.x2 - ec2.x1);
		double b2 = ec2.y1 - m2 * ec2.x1;
		// find intersection point
		double xInter = (b2 - b1) / (m1 - m2);
		double yInter = m1 * xInter + b1;
		// find parameter for each
		double t1 = (xInter - ec1.x1) / (ec1.x2 - ec1.x1);
		double t2 = (xInter - ec2.x1) / (ec2.x2 - ec2.x1);
		return answer(t1, t2);
	}
}
