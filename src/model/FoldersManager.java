package model;

import java.util.ArrayList;

// Maneja las funciones básicas de las carpetas (añadir, eliminar, etc).
public class FoldersManager {
    // Atributos.
    // Tiene que venir desde aquí el id, nombre, e id del padre.
    private int nextId = 1; // ID único de cada carpeta.
    private ArrayList<Folder> folders = new ArrayList<>(); // Lista con todos los datos de las carpetas.
    
    public boolean add(String name, int fatherId) {
        // Comprobar si la carpeta está vacía.
        if(name == null || name.isEmpty()) {
            name = "Nueva carpeta " + nextId;
        }

        // Crear nueva carpeta.
        Folder folderNew = new Folder(nextId, name, fatherId);
        // Si fatherId no es -1, es una subcarpeta.
        if (fatherId != -1) {
            Folder father = getById(fatherId);
            if (father == null) {
                return false;
            }
            father.addSubfolderId(nextId);
        }
        // Añadir al ArrayList.
        folders.add(folderNew);

        nextId++;
        return true;
    }

    // Añade directamente la carpeta (sin generar otro id).
    public boolean insert(Folder folder) {
        if (folder == null) {
            return false;
        }

        int id = folder.getId();
        Folder folderIns = getById(id);
        int fatherId = folder.getFatherId();
        // Si la carpeta ya existía (el id existe), no ponemos nada.
        if (folderIns != null) {
            return false;
        }
        // Si fatherId no es -1, es una subcarpeta.
        if (fatherId != -1) {
            Folder father = getById(fatherId);
            if (father == null) {
                return false;
            }
            father.addSubfolderId(id);
        }
        // Hacer que los id coincidan.
        if (id >= nextId) {
            nextId = id + 1;
        }

        // Añadir al ArrayList.
        folders.add(folder);
        return true;
    }

    public ArrayList<Folder> getAll() {
        // Crear una copia de la lista por seguridad.
        ArrayList<Folder> foldersCopy = new ArrayList<>();
        // Meter los datos.
        foldersCopy.addAll(folders);

        return foldersCopy;
    }

    // Obtener apunte por id.
    public Folder getById(int id) {
        // Pillar cada carpeta.
        for(Folder folder: folders) {
            if (folder.getId() == id) {
                return folder;
            }
        }

        // No encontró nada.
        return null;
    }

    // Obtener todas las subcarpetas.
    public ArrayList<Folder> getAllSubfolders(int id) {
        Folder folder = getById(id);
        ArrayList<Folder> subfolders = new ArrayList<>();
        if(folder == null) {
            return subfolders;
        }

        for(int subfolderId : folder.getSubfolderIds()) {
            Folder subfolder = getById(subfolderId);
            // Comprobar que corresponde.
            if(subfolder != null) {
                subfolders.add(subfolder);
            }
        }
        return subfolders;
    }

    // Comprobar si puede eliminarse toda la carpeta (junto a su interior).
    public boolean canRemove(int id) {
        Folder folder = getById(id);
        // Comprobar si el id es nulo.
        if(folder == null) {
            return false;
        }

        // Ver si tiene carpeta padre y borrarla.
        int fatherId = folder.getFatherId();
        // Si no es -1, es una subcarpeta.
        if(fatherId != -1) {
            Folder father = getById(fatherId);
            if (father == null) { 
                return false; 
            }
        }

        for(int subfolderId : folder.getSubfolderIds()) {
            if(!canRemove(subfolderId)) {
                return false;
            }
        }
        // Se puede eliminar; mandamos la señal.
        return true;
    }

    // Después de comprobar, se elimina toda la carpeta.
    public void removeRecursive(int id) {
        Folder folder = getById(id);

        int fatherId = folder.getFatherId();
        if(fatherId != -1) {
            Folder father = getById(fatherId);
            father.removeSubfolderId(id);
        }

        for(int subfolderId : folder.getSubfolderIds()) {
            removeRecursive(subfolderId);
        }

        folders.remove(folder);
    }

    // Eliminar una carpeta por id.
    public boolean remove(int id) {
        // Primero comprobar.
        if(!canRemove(id)) {
            return false;
        }

        // Si está bien, eliminar.
        removeRecursive(id);
        return true;
    }

    // Cambiar carpeta de lugar (comprobar su nuevo padre).
    public boolean changePlace(int folderId, int newFatherId) {
        Folder folder = getById(folderId);
        Folder newFather = null;
        Folder oldFather = null;
        // Comprobar si el id es nulo.
        if(folder == null) {
            return false;
        }
        // Si no es -1, es una subcarpeta.
        if(newFatherId != -1) {
            newFather = getById(newFatherId);
            if (newFather == null) { 
                return false; 
            }
        }

        // ID actual del padre, y comparar con el nuevo.
        int fatherId = folder.getFatherId();
        // Si no es -1, es una subcarpeta.
        if(fatherId != -1) {
            oldFather = getById(fatherId);
            if (oldFather == null) { 
                return false; 
            }
        }
        // Si son iguales, no hace nada.
        if(fatherId == newFatherId) {
            return true;
        }

        // Tratar de encontrar el id de adónde se mueve (otra carpeta o la raíz).
        int cntId = newFatherId;
        while(cntId != -1 && cntId != folderId) {
            // Obtener la carpeta actual y el nuevo padre.
            Folder actualFolder = getById(cntId);
            cntId = actualFolder.getFatherId();
        }

        // Si la carpeta es descendiente de esta, parar el bucle.
        if(cntId == folderId) {
            return false;
        }

        // Actualizar carpetas y subcarpetas.
        // Y comprobar que no sean los padres la raíz.
        if(folderId != -1) {
            oldFather.removeSubfolderId(folderId);
        }
        if(newFatherId != -1) {
            newFather.addSubfolderId(folderId);
        }
        folder.setFatherId(newFatherId);

        return true;
    }

    // Editar el nombre de una carpeta por id.
    public boolean rename(int id, String newName) {
        Folder folderToEdit = getById(id);

        // Comprobar si el id es nulo.
        if(folderToEdit == null) {
            return false;
        }
        // Comprobar si el nombre está vacío.
        if (newName == null || newName.isEmpty()) {
            // Le dejamos el nombre anterior.
            newName = folderToEdit.getName();
        }

        // Modificar el nombre con el setter.
        folderToEdit.setName(newName);
        return true;
    }
}
