// Maneja las funciones básicas de los apuntes (añadir, eliminar, etc).
package model;

import java.util.ArrayList;

public class NotesManager {
    // Atributos.
    // Tiene que venir desde aquí el id, título, y descripción.
    private int nextId = 1; // ID único de cada apunte.
    private ArrayList<Note> notes = new ArrayList<>(); // Lista con todos los datos de los apuntes.

    public void add(String title, String description) {
        // Comprobar si el título está vacío.
        if (title == null || title.isEmpty()) {
            title = "Sin título " + nextId;
        }
        // Crear un nuevo apunte.
        Note noteNew = new Note(nextId, title, description);
        // Añadir al ArrayList.
        notes.add(noteNew);

        nextId++;
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
        int nextUntintledId = 1; // Contandor independiente.

        // Comprobar si el id es nulo.
        if(noteToEdit == null) {
            return false;
        }
        // Comprobar si el título está vacío.
        if (newTitle == null || newTitle.isEmpty()) {
            newTitle = "Sin título " + nextUntintledId;
        }
        nextUntintledId++;

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
}