package model;

import java.time.LocalDateTime;

public class Note {
    // Atributos de los apuntes.
    private int id;
    private String title;
    private String description;
    private LocalDateTime date = LocalDateTime.now(); // Fecha de creación.
    private boolean favorite = false; // Al principio no es favorito.
    private int folderId = -1; // Filtra si una nota va o no en una carpeta. -1 es 'Sin carpeta'.

    // Lo que recibe NotesManager.
    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Lo que recibe clases como NotesFileManager (necesitan fecha, favoritos, etc).
    // Reutiliza el primer Note.
    public Note(int id, String title, String description, LocalDateTime date, boolean favorite) {
        this(id, title, description);

        this.date = date;
        this.favorite = favorite;
    }

    // Para los que necesitan el folderId.
    public Note(int id, String title, String description, LocalDateTime date, boolean favorite, int folderId) {
        this(id, title, description, date, favorite);

        this.folderId = folderId;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public int getFolderId() {
        return folderId;
    }

    // Setters.
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    // Convertir el objeto Apunte en una cadena legible.
    @Override
    public String toString() {
        return title + "\n" + description + "\n" + date + "\n" + folderId + "\n" + favorite;
    }
}