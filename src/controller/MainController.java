package controller;

import java.io.File;
import java.util.ArrayList;
import model.Folder;
import model.FoldersManager;
import model.Note;
import model.NotesManager;
import storage.FoldersFileManager;
import storage.NotesFileManager;

public class MainController {
    // Atributos.
    private FoldersManager foldersManager = new FoldersManager();
    private NotesManager notesManager = new NotesManager();
    // -1 es la raíz.
    private int folderId = -1;
    // Para guardar.
    private FoldersFileManager foldersFileManager = new FoldersFileManager();
    private NotesFileManager notesFileManager = new NotesFileManager();

    private final String foldersPath = "data/folders.txt";
    private final String notesPath = "data/notes.txt";

    public MainController() {
        // Crear carpeta data si no existe. 
        File dataFolder = new File("data"); 
        if (!dataFolder.exists()) { 
            dataFolder.mkdirs(); 
        }

        // Cargar carpetas.
        foldersManager.setFolders(
            foldersFileManager.loadFolders(foldersPath)
        );
        foldersManager.updateNextId();
        // Cargar apuntes.
        notesManager.setNotes(
            notesFileManager.loadNotes(notesPath)
        );
        // Actualizar.
        notesManager.updateNextId();
    }

    // Obtener las carpetas y apuntes actuales.
    public ArrayList<Folder> getCurrentFolders() {
        return foldersManager.getByFatherId(folderId);
    }
    public ArrayList<Note> getCurrentNotes() {
        return notesManager.getNotesByFolder(folderId);
    }

    // Conseguir todas las carpetas.
    public ArrayList<Folder> getAllFolders() {
        return foldersManager.getAll();
    }
    // Conseguir la subcarpetas.
    public ArrayList<Folder> getSubfolders(int fatherId) {
        return foldersManager.getByFatherId(fatherId);
    }

    // Conseguir carpeta actual.
    public int getCurrentFolderId() {
        return folderId;
    }
    public ArrayList<Folder> getFoldersByFavorites() {
        return foldersManager.getFavoriteFolders();
    }
    // Volver a una carpeta anterior.
    public void goToFolder(int folderId) {
        this.folderId = folderId;
    }

    // Obtener ruta actual.
    public ArrayList<Folder> getCurrentPath() {
        ArrayList<Folder> path = new ArrayList<>();

        int currentId = folderId;

        while (currentId != -1) {
            Folder folder = foldersManager.getById(currentId);

            if (folder == null) {
                break;
            }

            path.add(0, folder);
            currentId = folder.getFatherId();
        }

        return path;
    }

    // Conseguir apuntes.
    public int getNoteCount(int folderId) {
        return notesManager.getNotesByFolder(folderId).size();
    }
    public ArrayList<Note> getNotesByFavorites() {
        return notesManager.getFavoriteNotes();
    }
    public Note getNote(int noteId) {
        return notesManager.getById(noteId);
    }
    // Obtener apuntes en carpeta.
    public ArrayList<Note> getNotesByFolder(int folderId) {
        return notesManager.getNotesByFolder(folderId);
    }
    // Conseguir todos los apuntes.
    public ArrayList<Note> getAllNotes() {
        return notesManager.getAll();
    }

    // Crear carpeta o apunte y guardarlos.
    public boolean createFolder(String name) {
        boolean created = foldersManager.add(name, folderId);

        if (created) {
            foldersFileManager.saveFolders(
                foldersManager.getAll(),
                foldersPath
            );
        }

        return created;
    }
    public Note createNote() {
        notesManager.add("", "", folderId);

        notesFileManager.saveNotes(
            notesManager.getAll(),
            notesPath
        );

        return notesManager.getAll().get(notesManager.getAll().size() - 1);
    }

    // Obtener id actual.
    public void enterFolder(int folderId) {
        this.folderId = folderId;
    }

    // Marcar favoritos.
    public void toggleFolderFavorite(int folderId) {
        foldersManager.toggleFavorite(folderId);
        foldersFileManager.saveFolders(
            foldersManager.getAll(),
            foldersPath
        );
    }
    public void toggleNoteFavorite(int noteId) {
        notesManager.toggleFavorite(noteId);
        notesFileManager.saveNotes(
            notesManager.getAll(),
            notesPath
        );
    }

    // Poder editar apuntes.
    public void editNote(int noteId, String title, String description) {
        notesManager.changeTitle(noteId, title);
        notesManager.changeDescription(noteId, description);

        notesFileManager.saveNotes(
            notesManager.getAll(),
            notesPath
        );
    }

    // Poder editar carpetas.
    public boolean renameFolder(int folderId, String name) {
        boolean renamed = foldersManager.rename(folderId, name);

        if (renamed) {
            foldersFileManager.saveFolders(
                foldersManager.getAll(),
                foldersPath
            );
        }

        return renamed;
    }

    // Eliminar todos los apuntes de una carpeta y sus descendientes.
    private void removeNotesRecursive(int folderId) {
        // Eliminar apuntes de la carpeta actual.
        ArrayList<Note> notes = notesManager.getNotesByFolder(folderId);

        for (Note note : notes) {
            notesManager.remove(note.getId());
        }

        // Recorrer las subcarpetas.
        ArrayList<Folder> subfolders = getSubfolders(folderId);

        for (Folder subfolder : subfolders) {
            removeNotesRecursive(subfolder.getId());
        }
    }
    
    // Eliminar carpetas y apuntes, uno a uno.
    public boolean removeNote(int noteId) {
        // Comprobar y eliminar el apunte.
        boolean removed = notesManager.remove(noteId);

        if (removed) {
            // Guardar los cambios.
            notesFileManager.saveNotes(
                notesManager.getAll(),
                notesPath
            );
        }

        return removed;
    }
    public boolean removeFolder(int folderId) {
        // Comprobar si se puede eliminar.
        if (!foldersManager.canRemove(folderId)) {
            return false;
        }

        // Eliminar todos los apuntes de la carpeta y sus descendientes.
        removeNotesRecursive(folderId);

        // Eliminar la carpeta y todas sus subcarpetas.
        boolean removed = foldersManager.remove(folderId);

        if (removed) {
            // Guardar los cambios.
            foldersFileManager.saveFolders(
                foldersManager.getAll(),
                foldersPath
            );

            notesFileManager.saveNotes(
                notesManager.getAll(),
                notesPath
            );
        }

        return removed;
    }
}
