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
import java.util.stream.Collectors;

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
	private ArrayList<Point3> points;

	public Lineas(ArrayList<Pair> paris, ArrayList<Point3> points) {
		this.pairs = paris;
		this.points = points;
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

		Line2D.Double linea = new Line2D.Double(Double.valueOf(0.0), Double.valueOf(300.0), Double.valueOf(600.0),
				Double.valueOf(300.0));
		g2d.draw(linea);
		linea = new Line2D.Double(Double.valueOf(300.0), Double.valueOf(0.0), Double.valueOf(300.0),
				Double.valueOf(600.0));
		g2d.draw(linea);
		g2d.setColor(Color.RED);
		for (Pair pair : this.pairs) {
			int from = pair.from;
			int to = pair.to;
			// System.out.println("from: " + this.points.get(from) + " to : " +
			// this.points.get(to));

			linea = new Line2D.Double(Double.valueOf(this.points.get(pair.from).x),
					Double.valueOf(this.points.get(pair.from).y), Double.valueOf(this.points.get(pair.to).x),
					Double.valueOf(this.points.get(pair.to).y));
			g2d.draw(linea);
		}
	}

	void setNewPoints(Matrix3x3 matrix) {
		ArrayList<Point3> newPoints = new ArrayList<Point3>();
		for (Point3 point : this.points) {
			point.javaToCartesian();
			Point3 p = Matrix3x3.times(matrix, point);
			p.cartesianToJava();
			newPoints.add(p);
		}
		this.points = newPoints;
	}

	public void translation(double x, double y) {
		Matrix3x3 translationMatrix = Matrix3x3.createTranslationalMatrix(x, y);
		this.setNewPoints(translationMatrix);
	}

	public void rotation(double angles) {
		Point3 from = this.points.get(0);
		from.javaToCartesian();
		Matrix3x3 translationMatrixOne = Matrix3x3.createTranslationalMatrix(from.x, from.y);
		Matrix3x3 rotationMatrix = Matrix3x3.createRotationalMatrix(angles);
		Matrix3x3 translationMatrixTwo = Matrix3x3.createTranslationalMatrix(-from.x, -from.y);
		from.cartesianToJava();
		Matrix3x3 first = Matrix3x3.times(translationMatrixOne, rotationMatrix);
		Matrix3x3 resultingMatrix = Matrix3x3.times(first, translationMatrixTwo);
		this.setNewPoints(resultingMatrix);
	}

	public void scaling(double xFactor, double yFactor) {
		Point3 from = this.points.get(0);
		from.javaToCartesian();
		Matrix3x3 translationMatrixOne = Matrix3x3.createTranslationalMatrix(from.x, from.y);
		// Matrix3x3 rotationMatrix = Matrix3x3.createRotationalMatrix(angles);
		Matrix3x3 scalingMatrix = Matrix3x3.createScalingMatrix(xFactor, yFactor);
		Matrix3x3 translationMatrixTwo = Matrix3x3.createTranslationalMatrix(-from.x, -from.y);
		from.cartesianToJava();
		Matrix3x3 first = Matrix3x3.times(translationMatrixOne, scalingMatrix);
		Matrix3x3 resultingMatrix = Matrix3x3.times(first, translationMatrixTwo);
		this.setNewPoints(resultingMatrix);
		// this.setNewPoints(scalingMatrix);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode();
		// System.out.println("Key pressed");
		if (tecla == KeyEvent.VK_W) {
			this.translation(0.0, 10.0);
		} else if (tecla == KeyEvent.VK_S) {
			this.translation(0.0, -10.0);
		} else if (tecla == KeyEvent.VK_A) {
			this.translation(-10.0, 0.0);
		} else if (tecla == KeyEvent.VK_D) {
			this.translation(10.0, 0.0);
		} else if (tecla == KeyEvent.VK_I) {
			this.rotation(10.0);
		} else if (tecla == KeyEvent.VK_K) {
			this.rotation(-10.0);
		} else if (tecla == KeyEvent.VK_J) {
			this.scaling(1.1, 1.1);
		} else if (tecla == KeyEvent.VK_L) {
			this.scaling(0.9, 0.9);
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
		ArrayList<Point3> points = new ArrayList<Point3>();
		while (rows > 0) {
			double x = scan.nextDouble();
			double y = scan.nextDouble();
			Point3 point = new Point3(x, y, width, height);
			// System.out.println(point);
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
		Lineas lineas = new Lineas(pairs, points);
		JFrame frame = new JFrame("Lineas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(lineas);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
