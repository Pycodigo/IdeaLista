// Cajita de apunte.
package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NoteCard extends PaintCard {
    // Atributos (para que los reciba controller).
    private JButton favoriteBtn;
    private JButton pointsBtn;
    // Guardar id.
    private int noteId;

    public NoteCard(int noteId, boolean favorite, String title, String description, LocalDateTime date) {
        this.noteId = noteId;
        // Borde principal.
        setLayout(new BorderLayout());

        // Transformar la cadena en imagen.
        ImageIcon iconImg = new ImageIcon(getClass().getResource("/icons/apunte.png"));
        // Ajustamos la imagen.
        Image scaledImg = iconImg.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        // La reconvertimos a icono.
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel iconNote = new JLabel(scaledIcon);
        add(iconNote, BorderLayout.WEST);

        // Botones con panel nuevo.
        JPanel btnsPanel = new JPanel();
        // Quitar fondo.
        btnsPanel.setOpaque(false);
        btnsPanel.setLayout(new BorderLayout());
        pointsBtn = new JButton("⁝");
        // Decidir el botón de favorito con un booleano.
        if (favorite) {
            favoriteBtn = new JButton("⭐");
        } else {
            favoriteBtn = new JButton("✰");
        }
        // Quitar fondos de los botones.
        pointsBtn.setOpaque(false);
        pointsBtn.setContentAreaFilled(false);
        favoriteBtn.setOpaque(false);
        favoriteBtn.setContentAreaFilled(false);
        btnsPanel.add(pointsBtn, BorderLayout.NORTH);
        btnsPanel.add(favoriteBtn, BorderLayout.SOUTH);
        add(btnsPanel, BorderLayout.EAST);

        // Panel sur con los textos.
        JPanel textPanel = new JPanel();
        // Quitar fondo.
        textPanel.setOpaque(false);
        // Caja vertical.
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        JLabel nameText = new JLabel(title);
        // Poner solo los primeros caracteres de la descripción (si es más de 20 caracteres).
        String desCutted = "";
        if (description.length() > 20) {
            desCutted = description.substring(0, 20) + "...";
        } else {
            desCutted = description;
        }
        JLabel descriptionText = new JLabel(desCutted);
        // Formatear fecha a formato 'dia/mes/año'.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateFormatted = date.format(format);
        JLabel dateText = new JLabel("Fecha: " + dateFormatted);
        textPanel.add(nameText);
        textPanel.add(descriptionText);
        textPanel.add(dateText);
        add(textPanel, BorderLayout.SOUTH);
    }

    // Getters.
    public int getNoteId() {
        return noteId;
    }
    public JButton getFavoriteBtn() {
        return favoriteBtn;
    }
    public JButton getPointsBtn() {
        return pointsBtn;
    }
}
