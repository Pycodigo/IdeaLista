// Ventana principal con todo.
package view;

import controller.MainController;
import controller.ScreenController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import model.Note;

public class MainView {
    private JFrame frame;
    private MainController mc;
    private ScreenController sc;
    private ContentPanel contentP;
    private NotePanel noteP;
    private TopPanel tp;

    public MainView() {
        // Crear controlador.
        mc = new MainController();

        // Crear ventana principal con título.
        frame = new JFrame();
        frame.setTitle("IdeaLista");
        frame.setSize(1200, 800);
        // Poner un mínimo a la ventana.
        frame.setMinimumSize(new Dimension(500, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar en la pantalla.

        // Importar cabecera y ponerla arriba.
        tp = new TopPanel();
        frame.add(tp, BorderLayout.NORTH);

        // Importar panel principal y ponerlo en el centro.
        contentP = new ContentPanel(mc, this);
        frame.add(contentP, BorderLayout.CENTER);

        // Importar controlador de la pantalla.
        sc = new ScreenController(this);

        // Mostrar.
        frame.setVisible(true);
    }

    public void openNote(int noteId) {
        Note note = mc.getNote(noteId);

        noteP = new NotePanel(note, this, mc);

        frame.remove(contentP);
        frame.add(noteP, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    public void showContent() {
        // SIEMPRE actualizar.
        contentP.refresh();
        frame.remove(noteP);
        frame.add(contentP, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    public void changeWindowSize(int width, int height) {
        // Quitar pantalla completa.
        frame.setExtendedState(JFrame.NORMAL); 
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); 
    }

    public TopPanel getTopPanel() {
        return tp;
    }
}