// Panel para visualizar y editar un apunte.
package view;

import javax.swing.*;

import controller.MainController;
import model.Note;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class NotePanel extends JPanel {
    private Note note;

    private JTextField title;
    private JTextArea description;

    public NotePanel(Note note, MainView mv, MainController mc) {
        this.note = note;

        setLayout(new BorderLayout(20, 20));
        setBackground(Color.decode("#000770"));

        // Botón volver.
        JButton backBtn = new JButton("← VOLVER");
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));
        backBtn.setPreferredSize(new Dimension(120, 45));

        backBtn.addActionListener(e -> {
            mc.editNote(
                note.getId(),
                title.getText(),
                description.getText()
            );

            mv.showContent();
        });

        // Título editable.
        title = new JTextField(note.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.WHITE);
        title.setCaretColor(Color.WHITE);
        title.setOpaque(false);
        title.setBorder(BorderFactory.createEmptyBorder());

        // Panel superior.
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setOpaque(false);
        topPanel.add(backBtn, BorderLayout.WEST);
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
        description.setBorder(BorderFactory.createEmptyBorder());

        JScrollPane scroll = new JScrollPane(description);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        // Fecha.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateFormatted = note.getDate().format(format);

        JLabel date = new JLabel("Fecha: " + dateFormatted);
        date.setForeground(Color.LIGHT_GRAY);
        date.setFont(new Font("Arial", Font.PLAIN, 14));

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(date, BorderLayout.SOUTH);
    }
}