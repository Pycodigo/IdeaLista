package view;

import java.awt.*;
import javax.swing.*;

public class TopPanel extends JPanel {
    // Atributos (para que los reciba controller).
    private JButton folderButton;
    private JButton searchButton;
    private JButton favoritesButton;
    private JButton optionsBtn;
    private JButton begginingButton;
    private JTextField searchField;

    // Atributos menús.
    private JMenu menuWindow;

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
        bcPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Botones y separadores '>'.
        begginingButton = new JButton("Inicio");
        // Añadir.
        bcPanel.add(begginingButton);
        add(bcPanel, BorderLayout.CENTER);

        // Panel de búsqueda (en carpeta y apuntes específicos).
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        // Icono de lupa decorativo.
        JLabel searchIcon = new JLabel("🔍");
        // Introducir texto (la búsqueda se haría al momento).
        searchField = new JTextField(15); // Ancho mínimo.
        optionsBtn = new JButton("Ajustes ▾");
        // Añadir.
        searchPanel.add(searchIcon);
        searchPanel.add(searchField);
        searchPanel.add(optionsBtn);
        add(searchPanel, BorderLayout.EAST);

        // Crear el menú popup de ajustes.
        JPopupMenu menuSettings = new JPopupMenu();
        // Crear las opciones como submenús.
        menuWindow = new JMenu("Ajustar tamaño ventana");

        // Añadir los menús.
        menuSettings.add(menuWindow);

        optionsBtn.addActionListener(e -> {
            menuSettings.show(optionsBtn, 0, optionsBtn.getHeight());
        });
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
    public JButton getOptionsButton() {
        return optionsBtn;
    }
    public JButton getBegginingButton() {
        return begginingButton;
    }
    public JTextField getSearchField() {
        return searchField;
    }
    public JMenu getMenuWindow() {
        return menuWindow;
    }
}
