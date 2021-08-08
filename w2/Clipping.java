import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *
 * @author htrefftz
 */
public class Clipping extends JPanel {

    public static int x1;
    public static int x2;
    public static int y1;
    public static int y2;

    public int encodePoint(int x, int y) {
        if (x < -100 && y > 100) {
            return 9;
        } else if (x >= -100 && x <= 100 && y > 100) {
            return 8;
        } else if (x < 100 && y > 100) {
            return 10;
        } else if (x < -100 && y <= 100 && y >= -100) {
            return 1;
        } else if (x >= -100 && x <= 100 && y <= 100 && y >= -100) {
            return 0;
        } else if (x > 100 && y <= 100 && y >= -100) {
            return 2;
        } else if (x < -100 && y < -100) {
            return 5;
        } else if (x >= -100 && x <= 100 && y < -100) {
            return 4;
        } else if (x > 100 && y < -100) {
            return 6;
        }
        return 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int p1 = this.encodePoint(x1, y1);
        int p2 = this.encodePoint(x2, y2);
        if (p1 != 0 || p2 != 0)
            return;
        System.out.println(p1 + "\n" + p2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        int p1x = x1 + 200;
        int p1y = 200 - y1;
        int p2x = x2 + 200;
        int p2y = 200 - y2;

        Line2D.Double linea = new Line2D.Double(p1x, p1y, p2x, p2y);
        g2d.draw(linea);

    }

    public static void main(String[] args) {
        // Crear un nuevo Frame
        Scanner scanner = new Scanner(System.in);
        Clipping.x1 = scanner.nextInt();
        Clipping.y1 = scanner.nextInt();
        Clipping.x2 = scanner.nextInt();
        Clipping.y2 = scanner.nextInt();
        JFrame frame = new JFrame("Lineas");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new Clipping());
        // Asignarle tamaño
        frame.setSize(400, 400);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }

}
