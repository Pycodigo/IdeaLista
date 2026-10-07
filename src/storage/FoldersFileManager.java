// Guarda y carga las carpetas.
package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import model.Folder;

public class FoldersFileManager {
    public boolean saveFolders(ArrayList<Folder> folders, String path) {
        boolean folderSaved = true;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            // Formatea la fecha para traducirla (a un String).
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

            for (Folder folder : folders) {
                LocalDateTime myDate = folder.getDate();
                // Objeto a texto.
                String dateText = myDate.format(formatter);
                // Obtener y juntar las subcarpetas (Convierte los id Integer en Strings).
                String subfolder = folder.getSubfolderIds().stream()
                .map(String::valueOf).collect(Collectors.joining("\u0002"));
                String folderLine = folder.getId() + "\u0001" + 
                folder.getName() + "\u0001" + subfolder + "\u0001" + 
                folder.getFatherId() + "\u0001" + folder.isFavorite() 
                + "\u0001" + dateText + "\u0001" + folder.getIcon();

                bw.write(folderLine);
                bw.newLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error al guardar carpetas: " + ioe);
            folderSaved = false;
        }
        return folderSaved;
    }

    public ArrayList<Folder> loadFolders(String path) {
        ArrayList<Folder> foldersLoaded = new ArrayList<>();
        String folderLine;
        // Formatea la fecha para traducirla (devuelta a LocalDateTime).
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            // Lee todos las carpetas (al estar en el while), y para cuando es null.
            while ((folderLine = br.readLine()) != null) {
                // Obtener cada parte (id, nombre) por separado.
                String [] folderData = folderLine.split("\u0001");

                // Pillar datos para pasarlos.
                String id = folderData[0];
                int folderId = Integer.parseInt(id);
                String name = folderData[1];
                String subfolders = folderData[2];
                ArrayList<Integer> subfolderIds = new ArrayList<>();
                String father = folderData[3];
                int fatherId = Integer.parseInt(father);
                String favorite = folderData[4];
                boolean folderFavorite = Boolean.parseBoolean(favorite);
                String date = folderData[5];
                LocalDateTime folderDate = LocalDateTime.parse(date, formatter);
                String icon = folderData[6];

                // Si hay subcarpetas, conseguir sus IDs, y meterlos.
                if(!subfolders.isEmpty()) {
                    for(String subfolder : subfolders.split("\u0002")) {
                        int subfolderId = Integer.parseInt(subfolder);
                        subfolderIds.add(subfolderId);
                    }
                }

                Folder folder = new Folder(folderId, name, icon, fatherId, subfolderIds, folderDate, folderFavorite);
                // Añadir a la lista para cargar.
                foldersLoaded.add(folder);
            }
        }catch (FileNotFoundException fnfe) {
            System.out.println("Error al encontrar las carpetas: " + fnfe);
        } catch (IOException ioe) {
            System.out.println("Error al cargar carpetas: " + ioe);
        }

        return foldersLoaded;
    }
}
