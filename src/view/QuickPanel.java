package view;

import controller.MainController;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuickPanel extends JPanel {
    private CardLayout cards;
    private SearchPanel sp;
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

        searchCard.setLayout(new BorderLayout());
        favoriteCard.setLayout(new BorderLayout());
        
        JLabel markerLbl = new JLabel("Marcadores");
        JLabel searchLbl = new JLabel("Buscar");
        JLabel favLbl = new JLabel("Favoritos");
        markerLbl.setFont(new Font("Arial", Font.BOLD, 18));
        searchLbl.setFont(new Font("Arial", Font.BOLD, 18));
        favLbl.setFont(new Font("Arial", Font.BOLD, 18));
        markerLbl.setForeground(Color.WHITE);
        searchLbl.setForeground(Color.WHITE);
        favLbl.setForeground(Color.WHITE);
        markerCard.add(markerLbl);
        searchCard.add(searchLbl, BorderLayout.NORTH);
        favoriteCard.add(favLbl, BorderLayout.NORTH);

        sp = new SearchPanel(mc);
        favP = new FavoritesPanel(mc);
        searchCard.add(sp, BorderLayout.CENTER);
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
    public SearchPanel getSearchPanel() {
        return sp;
    }
    public FavoritesPanel getFavoritesPanel() {
        return favP;
    }
}
