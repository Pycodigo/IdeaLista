package view;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    // Atributos (para que los reciba controller).
    private JButton folderButton;
    private JButton searchButton;
    private JButton favoritesButton;
    private JButton begginingButton;
    private JTextField searchField;

    public TopPanel() {
        // Borde principal superior.
        setLayout(new BorderLayout());

        // Panel de los iconos.
        JPanel iconPanel = new JPanel();
        // Layout para poner los iconos con el mismo tamaño.
        iconPanel.setLayout(new FlowLayout());
        // Botones (con sus iconos).
        folderButton = new JButton("📁");
        searchButton = new JButton("🔍");
        favoritesButton = new JButton("⭐");
        // Añadir.
        iconPanel.add(folderButton);
        iconPanel.add(searchButton);
        iconPanel.add(favoritesButton);
        add(iconPanel, BorderLayout.WEST);

        // Panel del breadcrumb.
        JPanel bcPanel = new JPanel();
        // Un FlowLayout para poder regresar a cualquier carpeta con un click.
        bcPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Botones y separadores '>'.
        begginingButton = new JButton("Inicio");
        // Añadir.
        bcPanel.add(begginingButton);
        add(bcPanel, BorderLayout.CENTER);

        // Panel de búsqueda (en carpeta y apuntes específicos).
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        // Icono de lupa decorativo.
        JLabel searchIcon = new JLabel("🔎︎");
        // Introducir texto (la búsqueda se haría al momento).
        searchField = new JTextField(15); // Ancho mínimo.
        // Añadir.
        searchPanel.add(searchIcon);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.EAST);
    }

    // Getters.
    public JButton getFolderButton() {
        return folderButton;
    }
    public JButton getSearchButton() {
        return searchButton;
    }
    public JButton getFavoritesButton() {
        return favoritesButton;
    }
    public JButton getBegginingButton() {
        return begginingButton;
    }
    public JTextField getSearchField() {
        return searchField;
    }
}
