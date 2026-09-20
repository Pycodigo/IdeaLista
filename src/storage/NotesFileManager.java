// Guarda y carga los apuntes (archivos).
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
import java.util.regex.Pattern;

import model.Note;

public class NotesFileManager {
    public boolean saveNotes(ArrayList<Note> notes, String path) {
        boolean noteSaved = true;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            // Formatea la fecha para traducirla (a un String).
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

            for (Note note : notes) {
                LocalDateTime myDate = note.getDate();
                // Objeto a texto.
                String dateText = myDate.format(formatter);
                // Poner descripción con saltos de línea.
                String description = note.getDescription().replace("\n", "\\n");
                // Ahora guarda el id de la carpeta.
                String noteLine = note.getId() + "\u0001" + 
                note.getTitle() + "\u0001" + description 
                + "\u0001" + dateText + "\u0001" + note.isFavorite()
                + "\u0001" + note.getFolderId();

                bw.write(noteLine);
                bw.newLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error al guardar apuntes: " + ioe);
            noteSaved = false;
        }
        return noteSaved;
    }

    public ArrayList<Note> loadNotes(String path) {
        ArrayList<Note> notesLoaded = new ArrayList<>();
        String noteLine;
        // Formatea la fecha para traducirla (devuelta a LocalDateTime).
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            // Lee todos los apuntes (al estar en el while), y para cuando es null.
            while ((noteLine = br.readLine()) != null) {
                // Obtener cada parte (id, título) por separado.
                String[] noteData = noteLine.split("\u0001", -1);

                System.out.println("LÍNEA: " + noteLine);
                System.out.println("CAMPOS: " + noteData.length);

                for (String data : noteData) {
                    System.out.println("[" + data + "]");
                }

                // Pillar datos para pasarlos.
                String id = noteData[0];
                int noteId = Integer.parseInt(id);
                String title = noteData[1];
                String description = noteData[2].replace("\\n", "\n");
                String date = noteData[3];
                LocalDateTime noteDate = LocalDateTime.parse(date, formatter);
                String favorite = noteData[4];
                boolean noteFavorite = Boolean.parseBoolean(favorite);
                String folder = noteData[5];
                int folderId = Integer.parseInt(folder);

                Note note = new Note(noteId, title, description, noteDate, noteFavorite, folderId);
                // Añadir a la lista para cargar.
                notesLoaded.add(note);
            }
        }catch (FileNotFoundException fnfe) {
            System.out.println("Error al encontrar los apuntes: " + fnfe);
        } catch (IOException ioe) {
            System.out.println("Error al cargar apuntes: " + ioe);
        }

        return notesLoaded;
    }
}
