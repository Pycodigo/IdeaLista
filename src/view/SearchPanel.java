package view;

import controller.MainController;
import controller.QuickController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Folder;
import model.Note;

public class SearchPanel extends JPanel {
    private MainController mc;
    private QuickController qc;
    
    private JTextField searchField;
    private JPanel resultsPanel;

    public SearchPanel(MainController mc) {
        this.mc = mc;
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Introducir texto.
        searchField = new JTextField(15); // Ancho mínimo.
        searchField.setMaximumSize(searchField.getPreferredSize());
        add(searchField);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));

        // Quitar fondo.
        resultsPanel = new JPanel();
        resultsPanel.setOpaque(false);
        // Caja vertical.
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        add(resultsPanel);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                refresh();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                refresh();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                refresh();
            }
        });

        refresh();
    }

    // Recorrer todo y crear sus tarjetitas en árbol.
    public void showInTree(int spacing, int folderId) {
        ArrayList<Folder> folders = mc.getSubfolders(folderId);
        ArrayList<Note> notes = mc.getNotesByFolder(folderId);

        for (Note note : notes) {
            // Si se busca, mirar que coincida.
            if (!note.getTitle().toLowerCase().contains(searchField.getText().toLowerCase())
                    && !searchField.getText().isEmpty()) {
                continue;
            }

            QuickCard noteQc = new QuickCard(
                note.getTitle(), 
                "/icons/apunte.png", 
                false, 
                note.getId()
            );
            resultsPanel.add(noteQc);
            // Permitir pulsar con el ratón.
            noteQc.addMouseListener(new MouseAdapter() {
                @Override 
                public void mouseClicked(MouseEvent e) {
                    if (qc != null) {
                        qc.openQuickItem(noteQc);
                    }
                }
            });
            noteQc.setBorder(BorderFactory.createEmptyBorder(10, 10 * spacing, 0, 0));
        }
        for (Folder folder : folders) {
            // Si se busca, mirar que coincida.
            if (folder.getName().toLowerCase().contains(searchField.getText().toLowerCase())
                || searchField.getText().isEmpty()) {
                QuickCard folderQc = new QuickCard(
                    folder.getName(), 
                    folder.getIcon(), 
                    true, 
                    folder.getId()
                );
                resultsPanel.add(folderQc);
                // Permitir pulsar con el ratón.
                folderQc.addMouseListener(new MouseAdapter() {
                    @Override 
                    public void mouseClicked(MouseEvent e) {
                        if (qc != null) {
                            qc.openQuickItem(folderQc);
                        }
                    }
                });
                folderQc.setBorder(BorderFactory.createEmptyBorder(10, 10 * spacing, 0, 0));
            }
            showInTree(
                searchField.getText().isEmpty() ? spacing + 1 : 0,
                folder.getId()
            );
        }
    }

    // Método para actualizar.
    public void refresh() {
        // Quitar todo.
        resultsPanel.removeAll();

        showInTree(0, -1); // En un principio, estará en la raíz.

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    // Setters.
    // Pasar a las tarjetitas.
    public void setQuickController(QuickController qc) {
        this.qc = qc;
    }
}
