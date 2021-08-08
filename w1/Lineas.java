import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *
 * @author htrefftz
 */
public class Lineas extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);
        int dy = 90, dx = 90;
        int incE = 2 * dy;
        int incNE = 2 * dy - 2 * dx;
        int d = 2 * dy - dx;
        int y = 10;

        for (int x = 10; x <= 90; x++) {
            Line2D.Double linea = new Line2D.Double(x, y, x, y);
            g2d.draw(linea);
            if (d <= 0) {
                d += incE;
            } else {
                d += incNE;
                y += 1;
            }

        }

        // g2d.setColor(Color.RED);
        // Line2D.Double linea2 = new Line2D.Double(200, 20, 20, 80);
        // g2d.draw(linea2);

        // Invocar el algoritmo de Bresenham
        // P1 (10, 10) -> P2 (100, 100)

    }

    // Insertar el algoritmo de Bresenham
    // Dibujar línea desde (x, y, x, y)

    public static void main(String[] args) {
        // Crear un nuevo Frame
        JFrame frame = new JFrame("Lineas");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new Lineas());
        // Asignarle tamaño
        frame.setSize(250, 200);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }

}
