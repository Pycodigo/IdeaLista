package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Folder {
    // Atributos de las carpetas (compartido con NotesManager).
    private int id;
    private String name;
    private ArrayList<Integer> subfolderIds = new ArrayList<>(); // Por IDs.
    private int fatherId = -1;
    private boolean favorite = false;
    private LocalDateTime date = LocalDateTime.now(); // Fecha de creación.
    private String icon = "icons/carpeta.png";


    // Lo que recibe FoldersManager.
    public Folder(int id, String name, int fatherId) {
        this.id = id;
        this.name = name;
        this.fatherId = fatherId;
    }

    public Folder(int id, String name, String icon, int fatherId, ArrayList<Integer>subfolderIds, LocalDateTime date, boolean favorite) {
        this(id, name, fatherId);
        this.icon = icon;
        this.subfolderIds = subfolderIds;
        this.date = date;
        this.favorite = favorite;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getSubfolderIds() {
        // Crear una copia de la lista por seguridad.
        ArrayList<Integer> subfolderIdsCopy = new ArrayList<>();
        // Meter los datos.
        subfolderIdsCopy.addAll(subfolderIds);
        return subfolderIdsCopy;
    }

    public int getFatherId() {
        return fatherId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getIcon() {
        return icon;
    }


    // Setters.
    public void setName(String name) {
        this.name = name;
    }

    // Añadir un elemento (subcarpeta) a la lista.
    public void addSubfolderId(int subfolderId) {
        subfolderIds.add(subfolderId);
    }
    // Eliminar elemento.
    public void removeSubfolderId(Integer subfolderId) {
        subfolderIds.remove(subfolderId);
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
