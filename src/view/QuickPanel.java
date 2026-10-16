package view;

import controller.MainController;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuickPanel extends JPanel {
    private CardLayout cards;
    private FavoritesPanel favP;
    // Evitar errores con las letras.
    private static final String MARKER_TEXT = "Marcadores";
    private static final String SEARCH_TEXT = "Buscador";
    private static final String FAV_TEXT = "Favoritos";
    // Controlador.
    private MainController mc;

    public QuickPanel(MainController mc) {
        this.mc = mc;
        cards = new CardLayout();
        setLayout(cards);
        // Fondo azul oscuro.
        setBackground(Color.decode("#252F40"));
        // Ancho preferido.
        setPreferredSize(new Dimension(200, 300)); // La altura (300) aquí da igual.

        // Añadir cartas distintas.
        JPanel markerCard = new JPanel();
        JPanel searchCard = new JPanel();
        JPanel favoriteCard = new JPanel();
        // Poner transparentes.
        markerCard.setOpaque(false);
        searchCard.setOpaque(false);
        favoriteCard.setOpaque(false);

        favoriteCard.setLayout(new BorderLayout());
        
        JLabel markerLbl = new JLabel("Marcadores");
        JLabel searchLbl = new JLabel("Buscar");
        JLabel favLbl = new JLabel("Favoritos");
        markerLbl.setForeground(Color.WHITE);
        searchLbl.setForeground(Color.WHITE);
        favLbl.setForeground(Color.WHITE);
        markerCard.add(markerLbl);
        searchCard.add(searchLbl);
        favoriteCard.add(favLbl, BorderLayout.NORTH);

        favP = new FavoritesPanel(mc);
        favoriteCard.add(favP, BorderLayout.CENTER);

        add(markerCard, MARKER_TEXT);
        add(searchCard, SEARCH_TEXT);
        add(favoriteCard, FAV_TEXT);
    }

    // LLama al panel correspondiente desde fuera.
    public void showMarker() {
        cards.show(this, MARKER_TEXT);
    }
    public void showSearch() {
        cards.show(this, SEARCH_TEXT);
    }
    public void showFavorite() {
        cards.show(this, FAV_TEXT);
    }

    // Getters.
    public FavoritesPanel getFavoritesPanel() {
        return favP;
    }
}
