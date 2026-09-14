// Ventana principal con todo.
package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainView {
    private JFrame frame;

    public MainView() {
        // Crear ventana principal con título.
        frame = new JFrame();
        frame.setTitle("IdeaLista");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Importar cabecera y ponerla arriba.
        TopPanel tp = new TopPanel();
        frame.add(tp, BorderLayout.NORTH);
        // Mostrar.
        frame.setVisible(true);
    }
}
