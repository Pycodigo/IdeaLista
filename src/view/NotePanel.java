
package view;

import controller.MainController;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Note;

// Panel para visualizar y editar un apunte.
public class NotePanel extends JPanel {

    private Note note;
    private String originalTitle;

    private JTextField title;
    private JTextArea description;

    public NotePanel(Note note, MainView mv, MainController mc) {
        this.note = note;
        originalTitle = note.getTitle();

        setLayout(new BorderLayout(20, 20));
        setBackground(Color.decode("#000770"));

        // Título editable.
        title = new JTextField(note.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.WHITE);
        title.setCaretColor(Color.WHITE);
        title.setOpaque(false);
        title.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));

        // Detectar cambios en el título.
        title.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                saveChanges(mc);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveChanges(mc);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveChanges(mc);
            }
        });

        // Actualizar el buscador al perder el foco.
        title.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

                String newTitle = title.getText();

                if (!newTitle.equals(originalTitle)) {

                    // Guardar los cambios.
                    saveChanges(mc);

                    // Actualizar el buscador.
                    mv.getQuickPanel().getSearchPanel().refresh();

                    // Actualizar la referencia original.
                    originalTitle = note.getTitle();
                }
            }
        });

        // Panel superior.
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setOpaque(false);
        topPanel.add(title, BorderLayout.CENTER);

        // Descripción editable.
        description = new JTextArea(note.getDescription());
        description.setFont(new Font("Arial", Font.PLAIN, 18));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(true);
        description.setOpaque(false);
        description.setForeground(Color.WHITE);
        description.setCaretColor(Color.WHITE);
        description.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // Detectar cambios en la descripción.
        description.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                saveChanges(mc);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveChanges(mc);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveChanges(mc);
            }
        });

        // Scroll de la descripción.
        JScrollPane scroll = new JScrollPane(description);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        // Panel inferior.
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setOpaque(false);

        // Fecha.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateFormatted = note.getDate().format(format);

        JLabel date = new JLabel("Fecha: " + dateFormatted);
        date.setForeground(Color.LIGHT_GRAY);
        date.setFont(new Font("Arial", Font.PLAIN, 14));
        date.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));

        // Botón volver.
        JButton backBtn = new JButton("← VOLVER");
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));
        backBtn.setPreferredSize(new Dimension(120, 45));

        backBtn.addActionListener(e -> {
            saveChanges(mc);
            mv.showContent();
        });

        bottomPanel.add(date, BorderLayout.WEST);
        bottomPanel.add(backBtn, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Guardar los cambios del apunte.
    private void saveChanges(MainController mc) {
        mc.editNote(
            note.getId(),
            title.getText(),
            description.getText()
        );
    }
}
