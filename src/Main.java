import model.Note;
import model.NotesManager;

public class Main {
    public static void main(String[] args) {
        NotesManager manager = new NotesManager();
        manager.add("Hola mundo", "Lo normal");
        manager.add("Prueba", "¿Está en true favoritos?");
        manager.toggleFavorite(2);

        for (Note note : manager.getAll()) {
            System.out.println(note.toString());
        }
    }
}