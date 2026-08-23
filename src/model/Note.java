package model;

import java.time.LocalDateTime;

public class Note {
    // Atributos de los apuntes.
    private int id;
    private String title;
    private String description;
    private LocalDateTime date = LocalDateTime.now(); // Fecha de creación.
    private boolean favorite = false; // Al principio no es favorito.

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

    // Convertir el objeto Apunte en una cadena legible.
    @Override
    public String toString() {
        return title + "\n" + description + "\n" + date + "\n" + favorite;
    }
}