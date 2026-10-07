package controller;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JMenuItem;
import model.ScreenModels;
import storage.ScreenFileManager;
import view.MainView;

public class ScreenController {
    private final ScreenModels screenModels;
    private final MainView mainView;
    private final ScreenFileManager screenStorage;

    // Pasamos la vista por parámetro para poder controlarla.
    public ScreenController(MainView mainView) {
        this.mainView = mainView;
        this.screenModels = new ScreenModels(); // Instancia su propio modelo.
        this.screenStorage = new ScreenFileManager();

        // Cargar la resolución guardada.
        Dimension savedResolution = screenStorage.loadResolution();

        // Guardarla en el modelo.
        screenModels.setSelectedResolution(savedResolution);

        // Aplicarla a la ventana.
        mainView.changeWindowSize(
            savedResolution.width,
            savedResolution.height
        );

        // Crear las opciones del menú.
        initializeResolutionMenus();
    }

    private void initializeResolutionMenus() {
        List<Dimension> sizes = screenModels.getValidResolutions();
        var topPanel = mainView.getTopPanel(); 
        
        for (Dimension dim : sizes) {
            // Crear las distintas opciones.
            String etiquette = dim.width + " x " + dim.height;
            JMenuItem item = new JMenuItem(etiquette);
            
            // El controlador especializado maneja el evento.
            item.addActionListener(e -> {
                // Actualizar la resolución seleccionada.
                screenModels.setSelectedResolution(dim);
                // Cambiar el tamaño de la ventana.
                mainView.changeWindowSize(dim.width, dim.height);
                // Guardar la nueva resolución.
                screenStorage.saveResolution(dim.width, dim.height);
            });
            
            topPanel.getMenuWindow().add(item);
        }
    }
}
