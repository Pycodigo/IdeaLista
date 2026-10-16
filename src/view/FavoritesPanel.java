package view;

import controller.MainController;
import controller.QuickController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import model.Folder;
import model.Note;

public class FavoritesPanel extends JPanel {
    private MainController mc;
    private QuickController qc;

    public FavoritesPanel(MainController mc) {
        this.mc = mc;

        // Quitar fondo.
        setOpaque(false);
        // Caja vertical.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        refresh();
    }

    // Método para actualizar.
    public void refresh() {
        // Quitar todo.
        removeAll();

        // Pedir los favoritos.
        ArrayList<Note> notesFavorites = mc.getNotesByFavorites();
        ArrayList<Folder> foldersFavorites = mc.getFoldersByFavorites();

        // Recorrerlos y crear su tarjetita.
        for (Note note : notesFavorites) {
            QuickCard noteQc = new QuickCard(
                note.getTitle(), 
                "/icons/apunte.png", 
                false, 
                note.getId()
            );
            add(noteQc);
            // Permitir pulsar con el ratón.
            noteQc.addMouseListener(new MouseAdapter() {
                @Override 
                public void mouseClicked(MouseEvent e) {
                    if (qc != null) {
                        qc.openQuickItem(noteQc);
                    }
                }
            });
        }
        for (Folder folder : foldersFavorites) {
            QuickCard folderQc = new QuickCard(
                folder.getName(), 
                folder.getIcon(), 
                true, 
                folder.getId()
            );
            add(folderQc);
            // Permitir pulsar con el ratón.
            folderQc.addMouseListener(new MouseAdapter() {
                @Override 
                public void mouseClicked(MouseEvent e) {
                    if (qc != null) {
                        qc.openQuickItem(folderQc);
                    }
                }
            });
        }

        revalidate();
        repaint();
    }

    // Setters.
    // Pasar a las tarjetitas.
    public void setQuickController(QuickController qc) {
        this.qc = qc;
    }
}
