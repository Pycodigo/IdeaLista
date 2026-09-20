// Ventana principal con todo.
package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controller.MainController;
import model.Note;

public class MainView {
    private JFrame frame;
    private MainController mc;
    private ContentPanel contentP;
    private NotePanel noteP;

    public MainView() {
        // Crear controlador.
        mc = new MainController();

        // Crear ventana principal con título.
        frame = new JFrame();
        frame.setTitle("IdeaLista");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Importar cabecera y ponerla arriba.
        TopPanel tp = new TopPanel();
        frame.add(tp, BorderLayout.NORTH);

        // Importar panel principal y ponerlo en el centro.
        contentP = new ContentPanel(mc, this);
        frame.add(contentP, BorderLayout.CENTER);

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
}