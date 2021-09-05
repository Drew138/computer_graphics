import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.FileNotFoundException;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Pair {
	public int from;
	public int to;

	public Pair(int from, int to) {
		this.from = from;
		this.to = to;
	}
}

class Lineas extends JPanel implements KeyListener {

	private ArrayList<Pair> pairs;
	private ArrayList<Point4> points;
	private double width;
	private double height;
	private double d;

	public Lineas(ArrayList<Pair> paris, ArrayList<Point4> points, double width, double height, double d) {
		this.pairs = paris;
		this.points = points;
		this.width = width;
		this.height = height;
		this.d = d;
		// ovalo1 = new Ellipse2D.Double(10, 10, 50, 50);
		// El panel, por defecto no es "focusable".
		// Hay que incluir estas líneas para que el panel pueda
		// agregarse como KeyListsener.
		this.setFocusable(true);
		this.requestFocusInWindow();

		this.addKeyListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLUE);

		Line2D.Double linea = new Line2D.Double(0.0, width / 2, height, height / 2);
		g2d.draw(linea);
		linea = new Line2D.Double(width / 2, 0.0, height / 2, height);
		g2d.setColor(Color.RED);
		g2d.draw(linea);
		Matrix4x4 projectionMatrix = Matrix4x4.createProjectionMatrix(-this.d);
		for (int i = 0; i < this.points.size(); i++) {
			Point4 point = this.points.get(i);
			point.javaToCartesian();
			point = Matrix4x4.times(projectionMatrix, point);
			point.cartesianToJava();
			points.set(i, point);
		}
		for (Pair pair : this.pairs) {

			Point4 from = points.get(pair.from);
			Point4 to = points.get(pair.to);

			linea = new Line2D.Double(from.x, from.y, to.x, to.y);
			g2d.draw(linea);
		}
	}

	public void setNewPoints(Matrix4x4 matrix) {
		ArrayList<Point4> newPoints = new ArrayList<Point4>();
		for (Point4 point : this.points) {
			point.javaToCartesian();
			Point4 p = Matrix4x4.times(matrix, point);
			p.cartesianToJava();
			newPoints.add(p);
		}
		this.points = newPoints;
	}

	public void translation(double x, double y, double z) {
		Matrix4x4 translationMatrix = Matrix4x4.createTranslationalMatrix(x, y, z);
		this.setNewPoints(translationMatrix);
	}

	public void rotationX(double angles) {
		Point4 from = this.points.get(0);
		from.javaToCartesian();
		Matrix4x4 translationMatrixOne = Matrix4x4.createTranslationalMatrix(from.x, from.y, from.z);
		Matrix4x4 rotationMatrix = Matrix4x4.createRotationalMatrixX(angles);
		Matrix4x4 translationMatrixTwo = Matrix4x4.createTranslationalMatrix(-from.x, -from.y, -from.z);
		from.cartesianToJava();
		Matrix4x4 first = Matrix4x4.times(translationMatrixOne, rotationMatrix);
		Matrix4x4 resultingMatrix = Matrix4x4.times(first, translationMatrixTwo);
		this.setNewPoints(resultingMatrix);
		// this.setNewPoints(rotationMatrix);
	}

	public void rotationY(double angles) {
		Point4 from = this.points.get(0);
		from.javaToCartesian();
		System.out.println(from);
		Matrix4x4 translationMatrixOne = Matrix4x4.createTranslationalMatrix(from.x, from.y, from.z);
		Matrix4x4 rotationMatrix = Matrix4x4.createRotationalMatrixY(angles);
		Matrix4x4 translationMatrixTwo = Matrix4x4.createTranslationalMatrix(-from.x, -from.y, -from.z);
		from.cartesianToJava();
		Matrix4x4 first = Matrix4x4.times(translationMatrixOne, rotationMatrix);
		Matrix4x4 resultingMatrix = Matrix4x4.times(first, translationMatrixTwo);
		this.setNewPoints(resultingMatrix);
		// this.setNewPoints(rotationMatrix);
	}

	public void rotationZ(double angles) {
		Point4 from = this.points.get(0);
		from.javaToCartesian();
		Matrix4x4 translationMatrixOne = Matrix4x4.createTranslationalMatrix(from.x, from.y, from.z);
		Matrix4x4 rotationMatrix = Matrix4x4.createRotationalMatrixZ(angles);
		Matrix4x4 translationMatrixTwo = Matrix4x4.createTranslationalMatrix(-from.x, -from.y, -from.z);
		from.cartesianToJava();
		Matrix4x4 first = Matrix4x4.times(translationMatrixOne, rotationMatrix);
		Matrix4x4 resultingMatrix = Matrix4x4.times(first, translationMatrixTwo);
		this.setNewPoints(resultingMatrix);
	}

	public void scaling(double xFactor, double yFactor, double zFactor) {
		Point4 from = this.points.get(0);
		from.javaToCartesian();
		Matrix4x4 translationMatrixOne = Matrix4x4.createTranslationalMatrix(from.x, from.y, from.z);
		Matrix4x4 scalingMatrix = Matrix4x4.createScalingMatrix(xFactor, yFactor, zFactor);
		Matrix4x4 translationMatrixTwo = Matrix4x4.createTranslationalMatrix(-from.x, -from.y, -from.z);
		from.cartesianToJava();
		Matrix4x4 first = Matrix4x4.times(translationMatrixOne, scalingMatrix);
		Matrix4x4 resultingMatrix = Matrix4x4.times(first, translationMatrixTwo);
		this.setNewPoints(resultingMatrix);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println("Key pressed");
		if (key == KeyEvent.VK_W) {
			this.translation(0.0, 10.0, 0.0);
		} else if (key == KeyEvent.VK_S) {
			this.translation(0.0, -10.0, 0.0);
		} else if (key == KeyEvent.VK_A) {
			this.translation(-10.0, 0.0, 0.0);
		} else if (key == KeyEvent.VK_D) {
			this.translation(10.0, 0.0, 0.0);
		} else if (key == KeyEvent.VK_Z) {
			this.translation(0.0, 0.0, 10.0);
		} else if (key == KeyEvent.VK_X) {
			this.translation(0.0, 0.0, -10.0);
		} else if (key == KeyEvent.VK_R) {
			this.rotationX(10.0);
		} else if (key == KeyEvent.VK_F) {
			this.rotationX(-10.0);
		} else if (key == KeyEvent.VK_T) {
			this.rotationY(10.0);
		} else if (key == KeyEvent.VK_G) {
			this.rotationY(-10.0);
		} else if (key == KeyEvent.VK_Y) {
			this.rotationZ(10.0);
		} else if (key == KeyEvent.VK_H) {
			this.rotationZ(-10.0);
		} else if (key == KeyEvent.VK_I) {
			this.d += 10;
		} else if (key == KeyEvent.VK_K) {
			this.d -= 10;
		} else if (key == KeyEvent.VK_J) {
			this.scaling(1.1, 1.1, 1.1);
		} else if (key == KeyEvent.VK_L) {
			this.scaling(0.9, 0.9, 0.9);
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}

public class Edge {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("test.txt"));
		int rows = scan.nextInt();
		int width = 600, height = 600;
		ArrayList<Point4> points = new ArrayList<Point4>();
		while (rows > 0) {
			double x = scan.nextDouble();
			double y = scan.nextDouble();
			double z = scan.nextDouble();
			Point4 point = new Point4(x, y, z, width, height);
			point.cartesianToJava();
			points.add(point);
			rows -= 1;
		}
		rows = scan.nextInt();
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		for (int i = 0; i < rows; i++) {
			int p1 = scan.nextInt();
			int p2 = scan.nextInt();
			pairs.add(new Pair(p1, p2));
		}
		int d = scan.nextInt();
		Lineas lineas = new Lineas(pairs, points, width, height, d);
		JFrame frame = new JFrame("Lineas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(lineas);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
