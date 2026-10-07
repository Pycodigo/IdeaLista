// Maneja las funciones básicas de los apuntes (añadir, eliminar, etc).
package model;

import java.util.ArrayList;

public class NotesManager {
    // Atributos.
    // Tiene que venir desde aquí el id, título, y descripción.
    private int nextId = 1; // ID único de cada apunte.
    private ArrayList<Note> notes = new ArrayList<>(); // Lista con todos los datos de los apuntes.

    public void add(String title, String description, int folderId) {
        // Comprobar si el título está vacío.
        if(title == null || title.isEmpty()) {
            String baseTitle = "Sin título";
            String candidate = baseTitle;
            ArrayList<Note> siblings = getNotesByFolder(folderId);
            int cntNote = 2;
            int noId = -6; // Id que no existe.
            while(titleExists(siblings, candidate, noId)) {
                candidate = baseTitle + " " + cntNote;
                cntNote++;
            }
            title = candidate;
        }

        // Crear un nuevo apunte.
        Note noteNew = new Note(nextId, title, description);
        noteNew.setFolderId(folderId);

        // Añadir al ArrayList.
        notes.add(noteNew);

        nextId++;
    }

    // Comprobar títulos.
    private boolean titleExists(ArrayList<Note> siblings, String title, int ignoreId) {
        for (Note sibling : siblings) {
            if (sibling.getTitle().equals(title) && sibling.getId() != ignoreId) {
                // Coincide título.
                return true;
            }
        }

        // No coincidió ninguno.
        return false;
    }

    // Añade directamente el apunte (sin generar otro id).
    public boolean insert(Note note) {
        if (note == null) {
            return false;
        }

        int id = note.getId();
        Note noteIns = getById(id);
        // Si el apunte ya existía (el id existe), no ponemos nada.
        if (noteIns != null) {
            return false;
        }
        // Hacer que los id coincidan.
        if (id >= nextId) {
            nextId = id + 1;
        }

        // Añadir al ArrayList.
        notes.add(note);
        return true;
    }

    public ArrayList<Note> getAll() {
        // Crear una copia de la lista por seguridad.
        ArrayList<Note> notesCopy = new ArrayList<>();
        // Meter los datos.
        notesCopy.addAll(notes);

        return notesCopy;
    }

    // Obtener apunte por id.
    public Note getById(int id) {
        // Pillar cada apunte.
        for(Note note: notes) {
            if (note.getId() == id) {
                return note;
            }
        }

        // No encontró nada.
        return null;
    }

    // Pillar todos los apuntes dentro de una carpeta.
    public ArrayList<Note> getNotesByFolder(int folderId) {
        ArrayList<Note> notesByFolder = new ArrayList<>();

        for(Note note : notes) {
            if(folderId == note.getFolderId()) {
                notesByFolder.add(note);
            }
        }

        return notesByFolder;
    }

    // Eliminar un apunte por id.
    public boolean remove(int id) {
        Note noteToRemove = getById(id);

        // Comprobar si el id es nulo.
        if(noteToRemove == null) {
            return false;
        }

        // Eliminar y mandar que se cumplió.
        notes.remove(noteToRemove);
        return true;
    }

    // Editar el título de un apunte por id.
    public boolean changeTitle(int id, String newTitle) {
        Note noteToEdit = getById(id);

        // Comprobar si el id es nulo.
        if(noteToEdit == null) {
            return false;
        }

        // Comprobar si el título está vacío.
        if (newTitle == null || newTitle.isEmpty()) {
            ArrayList<Note> siblings = getNotesByFolder(noteToEdit.getFolderId());
            String baseTitle = "Sin título";
            String candidate = baseTitle;
            int cntNote = 2;
            while(titleExists(siblings, candidate, id)) {
                candidate = baseTitle + " " + cntNote;
                cntNote++;
            }
            newTitle = candidate;
        }

        // Modificar el título con el setter.
        noteToEdit.setTitle(newTitle);
        return true;
    }

    // Editar la descripción de un apunte por id.
    public boolean changeDescription(int id, String newDescription) {
        Note noteToEdit = getById(id);

        // Comprobar si el id es nulo.
        if(noteToEdit == null) {
            return false;
        }

        // Modificar la descripción con el setter.
        noteToEdit.setDescription(newDescription);
        return true;
    }

    // Marcar o desmarcar favorito por id.
    public boolean toggleFavorite(int id) {
        Note noteToEdit = getById(id);

        // Comprobar si el id es nulo.
        if(noteToEdit == null) {
            return false;
        }

        // Invertir favorito de true -> false, viceversa.
        noteToEdit.setFavorite(!noteToEdit.isFavorite());
        return true;
    }

    // Setter.
    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    // Actualizar el id.
    public void updateNextId() {
        for (Note note : notes) {
            if (note.getId() >= nextId) {
                nextId = note.getId() + 1;
            }
        }
    }
}