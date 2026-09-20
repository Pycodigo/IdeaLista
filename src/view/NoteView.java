package view;

import javax.swing.*;
import java.awt.*;

import model.Note;

public class NoteView extends JPanel {

    public NoteView(Note note) {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.decode("#000770"));

        // Título.
        JLabel title = new JLabel(note.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.WHITE);

        // Descripción.
        JTextArea description = new JTextArea(note.getDescription());
        description.setFont(new Font("Arial", Font.PLAIN, 18));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);

        JScrollPane scroll = new JScrollPane(description);

        // Fecha.
        JLabel date = new JLabel(note.getDate().toString());
        date.setForeground(Color.LIGHT_GRAY);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(date, BorderLayout.WEST);

        add(title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}