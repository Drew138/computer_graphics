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

class Point {
	public int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Pair {
	public Point from;
	public Point to;

	public Pair(Point from, Point to) {
		this.from = from;
		this.to = to;
	}
}

class Lineas extends JPanel {

	public ArrayList<Pair> pairs;

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
			linea = new Line2D.Double(Double.valueOf(pair.from.x), Double.valueOf(pair.from.y),
					Double.valueOf(pair.to.x), Double.valueOf(pair.to.y));
			g2d.draw(linea);
		}
	}
}

public class Edge {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("test.txt"));
		int rows = scan.nextInt();
		ArrayList<Point> points = new ArrayList<Point>();
		while (rows > 0) {
			int x = scan.nextInt() + 300;
			int y = 300 - scan.nextInt();
			System.out.println(x + " " + y);
			points.add(new Point(x, y));
			rows -= 1;
		}
		rows = scan.nextInt();
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		while (rows > 0) {
			Point p1 = points.get(scan.nextInt());
			Point p2 = points.get(scan.nextInt());
			pairs.add(new Pair(p1, p2));
			rows -= 1;
		}
		Lineas lineas = new Lineas();
		lineas.pairs = pairs;
		JFrame frame = new JFrame("Lineas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(lineas);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
