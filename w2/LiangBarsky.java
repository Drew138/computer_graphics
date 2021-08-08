
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author htrefftz
 */
public class LiangBarsky extends JPanel implements MouseListener, MouseMotionListener {

	Line2D.Double linea1;

	public LiangBarsky() {
		linea1 = new Line2D.Double();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		int x1 = (int) (this.linea1.x1);
		int x2 = (int) (this.linea1.x2);
		int y1 = (int) (this.linea1.y1);
		int y2 = (int) (this.linea1.y2);
		this.drawLine(g2d, x1, y1, x2, y2);
		this.drawFrame(g2d);
		this.drawAxis(g2d);
	}

	public void drawFrame(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		Line2D.Double linea = new Line2D.Double(100, 100, 300, 100);
		g.draw(linea);
		linea = new Line2D.Double(100, 300, 300, 300);
		g.draw(linea);
		linea = new Line2D.Double(100, 100, 100, 300);
		g.draw(linea);
		linea = new Line2D.Double(300, 100, 300, 300);
		g.draw(linea);

	}

	public void drawAxis(Graphics2D g) {
		g.setColor(Color.BLUE);
		Line2D.Double linea = new Line2D.Double(100, 200, 300, 200);
		g.draw(linea);

		g.setColor(Color.RED);
		linea = new Line2D.Double(200, 100, 200, 300);
		g.draw(linea);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		linea1.x1 = e.getX();
		linea1.y1 = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		linea1.x2 = e.getX();
		linea1.y2 = e.getY();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		linea1.x2 = e.getX();
		linea1.y2 = e.getY();
		repaint();
	}

	private void plot(Graphics2D g, int x, int y) {
		Line2D.Double linea = new Line2D.Double(x, y, x, y);

		g.draw(linea);
	}

	private void drawLine(Graphics2D g, int x1, int y1, int x2, int y2) {

		Double dx = Double.valueOf(x2 - x1);
		Double dy = Double.valueOf(y2 - y1);
		Double p1 = -dx;
		Double p2 = dx;
		Double p3 = -dy;
		Double p4 = dy;
		Double q1 = Double.valueOf(x1 - 100);
		Double q2 = Double.valueOf(300 - x1);
		Double q3 = Double.valueOf(y1 - 100);
		Double q4 = Double.valueOf(300 - y1);
		Double t1 = 0.0, t2 = 1.0;
		if ((p1 == 0 && q1 < 0) || (p2 == 0 && q2 < 0) || (p3 == 0 && q3 < 0) || (p4 == 0 && q4 < 0)) {
			g.setColor(Color.ORANGE);
			Line2D.Double linea = new Line2D.Double(x1, y1, x2, y2);
			g.draw(linea);
			return;
		}

		if (p1 != 0) {

			if (p1 < 0) {

				if (t1 < q1 / p1)
					t1 = q1 / p1;
				if (t2 > q2 / p2)
					t2 = q2 / p2;
			} else {
				if (t1 < q2 / p2)
					t1 = q2 / p2;
				if (t2 > q1 / p1)
					t2 = q1 / p1;
			}
		}
		if (p3 != 0) {

			if (p3 < 0) {
				if (t1 < q3 / p3)
					t1 = q3 / p3;
				if (t2 > q4 / p4)
					t2 = q4 / p4;
			} else {
				if (t1 < q4 / p4)
					t1 = q4 / p4;
				if (t2 > q3 / p3)
					t2 = q3 / p3;
			}
		}

		if (t1 > t2) {
			g.setColor(Color.ORANGE);
			Line2D.Double linea = new Line2D.Double(x1, y1, x2, y2);
			g.draw(linea);
			return;
		}
		int tmpx1 = (int) x1 + (int) (t1 * p2);
		int tmpy1 = (int) y1 + (int) (t1 * p4);
		int tmpx2 = (int) x1 + (int) (t2 * p2);
		int tmpy2 = (int) y1 + (int) (t2 * p4);

		g.setColor(Color.GREEN);
		Line2D.Double linea = new Line2D.Double(tmpx1, tmpy1, tmpx2, tmpy2);
		g.draw(linea);
		g.setColor(Color.BLACK);

		linea = new Line2D.Double(x1, y1, tmpx1, tmpy1);
		g.draw(linea);
		linea = new Line2D.Double(tmpx2, tmpy2, x2, y2);
		g.draw(linea);

	}

	public static void main(String[] args) {
		// Crear un nuevo Frame
		JFrame frame = new JFrame("Eventos del Mouse");
		// Al cerrar el frame, termina la ejecución de este programa
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Agregar un JPanel que se llama Points (esta clase)
		LiangBarsky ev = new LiangBarsky();
		frame.add(ev);
		// Asignarle tamaño
		frame.setSize(400, 400);
		// Poner el frame en el centro de la pantalla
		frame.setLocationRelativeTo(null);
		// Mostrar el frame
		frame.setVisible(true);
	}

}
