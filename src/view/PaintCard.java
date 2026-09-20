// Clase base para las cajitas (carpetas y apuntes).
package view;

import javax.swing.*;
import java.awt.*;

public class PaintCard extends JPanel {

    public PaintCard() {
        // Borde principal.
        setLayout(new BorderLayout());
        // Quitar el fondo que no queremos.
        setOpaque(false);
        // Poner tamaño de las cajas.
        setPreferredSize(new Dimension(190, 130));
        // Crear bordes.
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    // Pintar cajas.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Color de fondo.
        g2.setColor(Color.decode("#9CD7FC"));

        // Dibujar el rectángulo redondeado.
        g2.fillRoundRect(
            5, 5,
            getWidth() - 10,
            getHeight() - 10,
            20, 20
        );

        g2.dispose();
    }
}