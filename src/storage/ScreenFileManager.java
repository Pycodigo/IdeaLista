// Guardar y cargar el tamaño de ventana.
package storage;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScreenFileManager {
    private String path = "data/screen.txt";

    public boolean saveResolution(int width, int height) {
        boolean resSaved = true;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            // Guardar todo junto.
            bw.write(width+";"+height);
        } catch (IOException ioe) {
            System.out.println("Error al guardar la resolución: " + ioe);
            resSaved = false;
        }
        return resSaved;
    }

    public Dimension loadResolution() {
        Dimension resolution = null;

        try {
            File file = new File(path);

            if (!file.exists()) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                    bw.write("1200;800");
                }

                resolution = new Dimension(1200, 800);
            } else {
                try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                    String resolutionLine = br.readLine();

                    String[] resolutionData = resolutionLine.split(";");

                    String widthRes = resolutionData[0];
                    int widthInt = Integer.parseInt(widthRes);

                    String heightRes = resolutionData[1];
                    int heightInt = Integer.parseInt(heightRes);

                    resolution = new Dimension(widthInt, heightInt);
                }
            }

        } catch (IOException ioe) {
            System.out.println("Error al cargar la resolución: " + ioe);
        }

        return resolution;
    }
}
