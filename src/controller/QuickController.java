// Controlador del panel rápido.
package controller;

import view.MainView;
import view.QuickCard;
import view.QuickPanel;


public class QuickController {
    // Recibe la vista, para todo lo que necesita.
    private final MainView mainView;
    // Identifica la sección que está abierta.
    private String activeSection = null;
    // Nombres de las secciones.
    private static final String MARKER = "Marcadores";
    private static final String SEARCH = "Buscador";
    private static final String FAVORITES = "Favoritos";

    public QuickController(MainView mainView) {
        this.mainView = mainView;

        // Obtener los paneles necesarios.
        var tp = mainView.getTopPanel();
        var qp = mainView.getQuickPanel();

        // Saber si se mostró el panel o no.
        boolean panelShowed = false;

        // Enlazar los botones con lo que queremos que hagan.
        tp.getMarkerButton().addActionListener(
            e -> toggleSection(MARKER)
        );

        tp.getSearchButton().addActionListener(
            e -> toggleSection(SEARCH)
        );

        tp.getFavoritesButton().addActionListener(
            e -> toggleSection(FAVORITES)
        );
    }

    // Mostrar, cambiar u ocultar la sección.
    private void toggleSection(String section) {
        QuickPanel qp = mainView.getQuickPanel();

        // Si pulsamos la sección que ya está abierta, ocultamos el panel.
        if (qp.isVisible() && section.equals(activeSection)) {
            qp.setVisible(false);
            activeSection = null;
        } else {
            // Mostrar el panel y seleccionar la sección correspondiente.
            switch (section) {
                case MARKER:
                    qp.showMarker();
                    break;

                case SEARCH:
                    qp.showSearch();
                    break;

                case FAVORITES:
                    qp.showFavorite();
                    break;
            }

            qp.setVisible(true);
            activeSection = section;
        }

        // Reorganizar la ventana.
        qp.revalidate();
        qp.repaint();
    }

    public void refreshFavorites() {
        mainView.getQuickPanel()
                .getFavoritesPanel()
                .refresh();
    }

    // Abrir apunte o carpeta con su id.
    public void openQuickItem(QuickCard qc) {
        if (qc.isFolder()) {
            mainView.getMainController().enterFolder(qc.getId());
            mainView.refreshContent();
        } else {
            mainView.openNote(qc.getId());
        }
    }
}