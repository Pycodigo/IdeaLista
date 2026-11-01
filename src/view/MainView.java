// Ventana principal con todo.
package view;

import controller.MainController;
import controller.QuickController;
import controller.ScreenController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import model.Note;

public class MainView {
    private JFrame frame;
    private MainController mc;
    private ScreenController sc;
    private QuickController qc;
    private ContentPanel contentP;
    private NotePanel noteP;
    private TopPanel tp;
    private QuickPanel quickP;

    public MainView() {
        // Crear controlador.
        mc = new MainController();

        // Crear ventana principal con título.
        frame = new JFrame();
        frame.setTitle("IdeaLista");
        frame.setSize(1200, 800);
        // Poner un mínimo a la ventana.
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar en la pantalla.

        // Importar cabecera y ponerla arriba.
        tp = new TopPanel();
        frame.add(tp, BorderLayout.NORTH);

        quickP = new QuickPanel(mc);
        frame.add(quickP, BorderLayout.WEST);
        // Ocultarlo al inicio.
        quickP.setVisible(false);

        // Importar panel principal y ponerlo en el centro.
        contentP = new ContentPanel(mc, this);
        frame.add(contentP, BorderLayout.CENTER);

        // Importar controlador de la pantalla.
        sc = new ScreenController(this);
        qc = new QuickController(this);

        // Conectar panel con controlador.
        quickP.getSearchPanel().setQuickController(qc);
        quickP.getFavoritesPanel().setQuickController(qc);

        // Mostrar.
        frame.setVisible(true);
    }

    public void openNote(int noteId) {
        Note note = mc.getNote(noteId);

        // Elimina primero para evitar errores.
        if (noteP != null) {
            frame.remove(noteP);
        }

        noteP = new NotePanel(note, this, mc);

        frame.remove(contentP);

        frame.add(noteP, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    public void showContent() {
        // Elimina para evitar errores.
        if (noteP != null) {
            frame.remove(noteP);
        }

        contentP.refresh();
        frame.add(contentP, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    // Actualizar tras entrar a una carpeta en el panel rápido.
    public void refreshContent() {
        contentP.refresh();
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
    public QuickPanel getQuickPanel() {
        return quickP;
    }
    public MainController getMainController() {
        return mc;
    }
}