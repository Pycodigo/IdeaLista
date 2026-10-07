package model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class ScreenModels {
    private final List<Dimension> validResolutions;
    private Dimension selectedResolution; // Guardar temporalmente la resolución indicada.

    public ScreenModels() {
        this.validResolutions = new ArrayList<>();
        calculateResolutions();
        selectedResolution = new Dimension(1200, 800);

        for (Dimension resolution : validResolutions) {
            if (resolution.width == 1200 && resolution.height == 800) {
                selectedResolution = resolution;
                break;
            }
        }

        // Si 1200x800 no es válida, usar la primera disponible.
        if (!validResolutions.contains(selectedResolution)) {
            selectedResolution = validResolutions.get(0);
        }
    }

    private void calculateResolutions() {
        // Obtener tamaño del monitor actual.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Lista de resoluciones estándar a evaluar.
        int[][] estandars = {
            {500, 600}, // Tamaño mínimo.
            {800, 600},
            {1024, 768},
            {1200, 800}, // Tamaño por defecto.
            {1280, 720},
            {1920, 1080},
            {2560, 1440},
            {3840, 2160}
        };

        boolean isNative = false;

        for (int[] res : estandars) {
            if (res[0] <= screenWidth && res[1] <= screenHeight) {
                validResolutions.add(new Dimension(res[0], res[1]));
                if (res[0] == screenWidth && res[1] == screenHeight) {
                    isNative = true;
                }
            }
        }

        // Si el monitor tiene una resolución no estándar, se añade al final.
        if (!isNative) {
            validResolutions.add(new Dimension(screenWidth, screenHeight));
        }
    }

    // Getters.
    public List<Dimension> getValidResolutions() {
        return validResolutions;
    }
    public Dimension getSelectedResolution() {
        return selectedResolution;
    }

    // Setters.
    public void setSelectedResolution(Dimension resolution) {
        this.selectedResolution = selectedResolution;
    }
}
