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

class Lineas extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLUE);

		Line2D.Double linea = new Line2D.Double(Double.valueOf(0.0), Double.valueOf(300.0), Double.valueOf(600.0),
				Double.valueOf(300.0));
		g2d.draw(linea);
		g2d.setColor(Color.GREEN);
		linea = new Line2D.Double(Double.valueOf(300.0), Double.valueOf(0.0), Double.valueOf(300.0),
				Double.valueOf(600.0));
		g2d.draw(linea);

		Point3 initial = new Point3(350.0, 250);
		Point3 end = new Point3(450, 200);

		g2d.setColor(Color.RED);
		linea = new Line2D.Double(initial.x, initial.y, end.x, end.y);
		g2d.draw(linea);
		Matrix3x3 translationMatrix = Matrix3x3.createTranslationalMatrix(-100.0, -50.0);
		Point3 transformedInitial = Matrix3x3.times(translationMatrix, initial);
		Point3 transformedEnd = Matrix3x3.times(translationMatrix, end);
		System.out.print(transformedInitial.x + " " + transformedInitial.y);
		System.out.print(transformedEnd.x + " " + transformedEnd.y);
		linea = new Line2D.Double(transformedInitial.x, transformedInitial.y, transformedEnd.x, transformedEnd.y);
		g2d.setColor(Color.YELLOW);
		g2d.draw(linea);

	}

	public static void main(String[] args) {

		Lineas lineas = new Lineas();
		JFrame frame = new JFrame("Lineas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(lineas);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
