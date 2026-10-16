package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuickCard extends JPanel {
    private int id;
    private boolean isFolder;

    public QuickCard(String title, String iconPath, boolean isFolder, int id) {
        // Guardar atributos.
        this.id = id;
        this.isFolder = isFolder;

        setLayout(new BorderLayout(10, 5));
        setOpaque(false);

        // Transformar la cadena en imagen.
        ImageIcon iconImg = new ImageIcon(getClass().getResource(iconPath));
        // Ajustamos la imagen.
        Image scaledImg = iconImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // La reconvertimos a icono.
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel icon = new JLabel(scaledIcon);
        add(icon, BorderLayout.WEST);

        JLabel titleLbl = new JLabel(title);
        titleLbl.setForeground(Color.WHITE);
        // Para que no quede alejado del icono.
        add(titleLbl, BorderLayout.CENTER);
    }

    // Getters.
    public int getId() {
        return id;
    }
    public boolean isFolder() {
        return isFolder;
    }
}
