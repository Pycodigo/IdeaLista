import java.io.File;
import java.util.ArrayList;

import model.Note;
import model.NotesManager;
import storage.NotesFileManager;

public class Main {
    public static void main(String[] args) {
        // Obtener separador para cada (Windows, Mac, Linux...).
        String fs = File.separator;
        // Obtener carpeta del usuario (para evitar problemas con el tipo de sistema).
        final String NOTES_PATH = new File ("").getAbsolutePath() + fs + "src" + fs + "YourNotes" + fs + "notes.txt";
        // Crear archivo y carpeta.
        File file = new File(NOTES_PATH);
        File fc = file.getParentFile();
        // Crear los directorios solo si no existen.
        if (!fc.exists()){
            fc.mkdirs();
        }
        NotesManager manager = new NotesManager();
        NotesFileManager fm = new NotesFileManager();

        // Cargar apuntes previos.
        for (Note note : fm.loadNotes(NOTES_PATH)) {
            manager.insert(note);
        }

        manager.toggleFavorite(2);
        manager.toggleFavorite(3);
        for (Note note : manager.getAll()) {
            System.out.println("Id: " + note.getId() + "\n" + note.toString());
        }

        manager.add("Hola mundo", "Lo normal");
        manager.add("Prueba", "¿Está en true favoritos?");
        manager.add("EEEEEE", "EEEEEEEEEEEEE");

        ArrayList<Note> notesToSave = manager.getAll();
        fm.saveNotes(notesToSave, NOTES_PATH);
    }
}



/* Prueba 1.
    manager.add("Hola mundo", "Lo normal");
    manager.add("Prueba", "¿Está en true favoritos?");
    manager.toggleFavorite(2);

    for (Note note : manager.getAll()) {
        System.out.println(note.toString());
    }
*/