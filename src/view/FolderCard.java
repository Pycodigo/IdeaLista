// Cajita de carpeta.
package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FolderCard extends JPanel {
    // Atributos (para que los reciba controller).
    private JButton favoriteBtn;
    private JButton pointsBtn;

    public FolderCard(String icon, boolean favorite, String name, int subfolderCnt, int noteCtn, LocalDateTime date) {
        // Borde principal.
        setLayout(new BorderLayout());

        // Transformar la cadena en imagen.
        ImageIcon iconImg = new ImageIcon(icon);
        // Ajustamos la imagen.
        Image scaledImg = iconImg.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        // La reconvertimos a icono.
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel iconFolder = new JLabel(scaledIcon);
        add(iconFolder, BorderLayout.WEST);

        // Botones con panel nuevo.
        JPanel btnsPanel = new JPanel();
        btnsPanel.setLayout(new BorderLayout());
        pointsBtn = new JButton("⁝");
        // Decidir el botón de favorito con un booleano.
        if (favorite) {
            favoriteBtn = new JButton("⭐");
        } else {
            favoriteBtn = new JButton("✰");
        }
        btnsPanel.add(pointsBtn, BorderLayout.NORTH);
        btnsPanel.add(favoriteBtn, BorderLayout.SOUTH);
        add(btnsPanel, BorderLayout.EAST);

        // Panel sur con los textos.
        JPanel textPanel = new JPanel();
        // Caja vertical.
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        JLabel nameText = new JLabel(name);
        JLabel cntText = new JLabel(subfolderCnt + " carpetas, " + noteCtn + " apuntes");
        // Formatear fecha a formato 'dia/mes/año'.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateFormatted = date.format(format);
        JLabel dateText = new JLabel("Fecha: " + dateFormatted);
        textPanel.add(nameText);
        textPanel.add(cntText);
        textPanel.add(dateText);
        add(textPanel, BorderLayout.SOUTH);
    }

    // Getters.
    public JButton getFavoriteBtn() {
        return favoriteBtn;
    }
    public JButton getPointsBtn() {
        return pointsBtn;
    }
}
