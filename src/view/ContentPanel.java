// Panel principal con las carpetas y apuntes.
package view;

import javax.swing.*;

import controller.MainController;
import model.Folder;
import model.Note;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ContentPanel extends JPanel {
    // Recibir controlador.
    private MainController mc;
    private MainView mv;

    public ContentPanel(MainController mc, MainView mv) {
        this.mc = mc;
        this.mv = mv;

        // Borde.
        setLayout(new BorderLayout());

        // Fondo azul oscuro.
        setBackground(Color.decode("#000770"));

        refresh();
    }

    public void refresh() {
        removeAll();

        ArrayList<Folder> folders = mc.getCurrentFolders();
        ArrayList<Note> notes = mc.getCurrentNotes();

        // Migas de pan.
        JPanel breadcrumbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        breadcrumbPanel.setOpaque(false);

        ArrayList<Folder> path = mc.getCurrentPath();

        // Botón raíz.
        JButton rootBtn = new JButton("Inicio");
        rootBtn.setBorderPainted(false);
        rootBtn.setContentAreaFilled(false);
        rootBtn.setForeground(Color.WHITE);

        rootBtn.addActionListener(e -> {
            mc.enterFolder(-1);
            refresh();
        });

        breadcrumbPanel.add(rootBtn);

        // Breadcrumb.
        if (path.size() <= 3) {

            // Mostrar todas las carpetas.
            for (Folder folder : path) {
                JLabel separator = new JLabel(" > ");
                separator.setForeground(Color.WHITE);
                breadcrumbPanel.add(separator);

                JButton folderBtn = new JButton(folder.getName());
                folderBtn.setBorderPainted(false);
                folderBtn.setContentAreaFilled(false);
                folderBtn.setForeground(Color.WHITE);

                folderBtn.addActionListener(e -> {
                    mc.enterFolder(folder.getId());
                    refresh();
                });

                breadcrumbPanel.add(folderBtn);
            }

        } else {

            // Primera carpeta.
            Folder firstFolder = path.get(0);

            JLabel separator = new JLabel(" > ");
            separator.setForeground(Color.WHITE);
            breadcrumbPanel.add(separator);

            JButton firstBtn = new JButton(firstFolder.getName());
            firstBtn.setBorderPainted(false);
            firstBtn.setContentAreaFilled(false);
            firstBtn.setForeground(Color.WHITE);

            firstBtn.addActionListener(e -> {
                mc.enterFolder(firstFolder.getId());
                refresh();
            });

            breadcrumbPanel.add(firstBtn);

            // Puntos suspensivos.
            JLabel dots = new JLabel(" > ...");
            dots.setForeground(Color.WHITE);
            breadcrumbPanel.add(dots);

            // Penúltima carpeta.
            Folder previousFolder = path.get(path.size() - 2);

            JLabel separator2 = new JLabel(" > ");
            separator2.setForeground(Color.WHITE);
            breadcrumbPanel.add(separator2);

            JButton previousBtn = new JButton(previousFolder.getName());
            previousBtn.setBorderPainted(false);
            previousBtn.setContentAreaFilled(false);
            previousBtn.setForeground(Color.WHITE);

            previousBtn.addActionListener(e -> {
                mc.enterFolder(previousFolder.getId());
                refresh();
            });

            breadcrumbPanel.add(previousBtn);

            // Carpeta actual.
            Folder currentFolder = path.get(path.size() - 1);

            JLabel separator3 = new JLabel(" > ");
            separator3.setForeground(Color.WHITE);
            breadcrumbPanel.add(separator3);

            JLabel currentLabel = new JLabel(currentFolder.getName());
            currentLabel.setForeground(Color.WHITE);

            breadcrumbPanel.add(currentLabel);
        }

        // Botones de creación.
        JButton newFolderBtn = new JButton("+ CARPETA");
        JButton newNoteBtn = new JButton("+ APUNTE");

        if (folders.isEmpty() && notes.isEmpty()) {
            newFolderBtn.setFont(new Font("Arial", Font.BOLD, 25));
            newNoteBtn.setFont(new Font("Arial", Font.BOLD, 25));

            newFolderBtn.setPreferredSize(new Dimension(250, 100));
            newNoteBtn.setPreferredSize(new Dimension(250, 100));
        } else {
            // Pequeños.
            newFolderBtn.setPreferredSize(new Dimension(140, 45));
            newNoteBtn.setPreferredSize(new Dimension(140, 45));

            newFolderBtn.setFont(new Font("Arial", Font.BOLD, 16));
            newNoteBtn.setFont(new Font("Arial", Font.BOLD, 16));
        }

        // Panel de botones.
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        actionsPanel.setOpaque(false);
        actionsPanel.add(newFolderBtn);
        actionsPanel.add(newNoteBtn);

        // Panel superior.
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(breadcrumbPanel, BorderLayout.NORTH);
        topPanel.add(actionsPanel, BorderLayout.SOUTH);

        // Crear carpeta.
        newFolderBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(
                this,
                "Nombre de la carpeta:",
                "Nueva carpeta",
                JOptionPane.PLAIN_MESSAGE
            );

            if (name != null) {
                mc.createFolder(name);
                refresh();
            }
        });

        // Crear apunte.
        newNoteBtn.addActionListener(e -> {
            Note note = mc.createNote();

            mv.openNote(note.getId());
        });

        // Si no hay absolutamente nada, mostrar solo los botones grandes.
        if (folders.isEmpty() && notes.isEmpty()) {
            add(breadcrumbPanel, BorderLayout.NORTH);
            add(actionsPanel, BorderLayout.CENTER);

            revalidate();
            repaint();
            return;
        }

        // Carpetas.
        JPanel folderBoxPanel = new JPanel();
        folderBoxPanel.setLayout(new BorderLayout());

        JLabel folderTitle = new JLabel("CARPETAS");
        folderTitle.setFont(new Font("Arial", Font.BOLD, 40));
        folderTitle.setForeground(Color.WHITE);

        JPanel foldersPanel = new JPanel();
        foldersPanel.setLayout(new FlowLayout());

        for (Folder folder : folders) {
            int noteCount = mc.getNoteCount(folder.getId());

            FolderCard folderCard = new FolderCard(
                folder.getId(),
                folder.getIcon(),
                folder.isFavorite(),
                folder.getName(),
                folder.getSubfolderIds().size(),
                noteCount,
                folder.getDate()
            );

            // Favorito.
            folderCard.getFavoriteBtn().addActionListener(e -> {
                mc.toggleFolderFavorite(folderCard.getFolderId());
                refresh();
            });

            // Entrar en carpeta.
            folderCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mc.enterFolder(folderCard.getFolderId());
                    refresh();
                }
            });

            foldersPanel.add(folderCard);
        }

        if (!folders.isEmpty()) {
            folderBoxPanel.add(folderTitle, BorderLayout.NORTH);
            folderBoxPanel.add(foldersPanel, BorderLayout.CENTER);
        }

        folderBoxPanel.setOpaque(false);
        foldersPanel.setOpaque(false);

        // Apuntes.
        JPanel noteBoxPanel = new JPanel();
        noteBoxPanel.setLayout(new BorderLayout());

        JLabel noteTitle = new JLabel("APUNTES");
        noteTitle.setFont(new Font("Arial", Font.BOLD, 40));
        noteTitle.setForeground(Color.WHITE);

        JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new FlowLayout());

        for (Note note : notes) {
            NoteCard noteCard = new NoteCard(
                note.getId(),
                note.isFavorite(),
                note.getTitle(),
                note.getDescription(),
                note.getDate()
            );

            // Favorito.
            noteCard.getFavoriteBtn().addActionListener(e -> {
                mc.toggleNoteFavorite(noteCard.getNoteId());
                refresh();
            });

            // Abrir apunte.
            noteCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int noteId = noteCard.getNoteId();
                    mv.openNote(noteId);
                }
            });

            notesPanel.add(noteCard);
        }

        if (!notes.isEmpty()) {
            noteBoxPanel.add(noteTitle, BorderLayout.NORTH);
            noteBoxPanel.add(notesPanel, BorderLayout.CENTER);
        }

        noteBoxPanel.setOpaque(false);
        notesPanel.setOpaque(false);

        // Añadir todo al panel principal.
        add(topPanel, BorderLayout.NORTH);
        add(folderBoxPanel, BorderLayout.CENTER);
        add(noteBoxPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}