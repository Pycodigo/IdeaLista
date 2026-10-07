# IdeaLista
<p align="left">🚧<img src="https://img.shields.io/badge/Estado%20-%20En%20desarrollo-red"/>🚧</p>

<h3><u>Índice</u></h3>

- [Descripción del proyecto](#descripción-del-proyecto)
- [Funcionalidades](#funcionalidades)
- [Instalación y uso](#instalación-y-uso)
- [Tecnologías usadas](#tecnologías-usadas)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Roadmap](#roadmap)
- [Objetivos de aprendizaje](#objetivos-de-aprendizaje)
- [Desarrollador](#desarrollador)
- [Licencia](#licencia)

<br/>
<h3>Descripción del proyecto</h3>
<hr/>
<p>Este proyecto consiste en una aplicación destinada a crear, editar, eliminar y ordenar apuntes.</p>
<p>Está diseñada para ser intuitiva y simple de usar, útil para tomar notas de cualquier cosa y tenerlas en un mismo lugar.</p>

<br/>
🛠️<h3>Funcionalidades</h3>🛠️
<hr/>
<b>Actuales</b>
<ul>
  <li>Crear apuntes.</li>
  <li>Editar apuntes.</li>
  <li>Eliminar apuntes.</li>
  <li>Marcar apuntes como favoritos.</li>
  <li>Ordenar por fecha o título.</li>
</ul>
<b>Futuras</b>
<ul>
  <li>Categorías.</li>
  <li>Etiquetas.</li>
  <li>Buscador.</li>
  <li>Exportar e importar apuntes.</li>
  <li>Tema oscuro.</li>
</ul>

<br/>
📁<h3>Instalación y uso</h3>📁
<hr/>
<p>No disponible por el momento.</p>

<br/>
📽️<h3>Tecnologías usadas​</h3>📽️
<hr/>
<ul>
  <li>Java.</li>
  <li>Git</li>
  <li>GitHub</li>
</ul>
<br/>
🏗️<h3>​​Estructura del proyecto​</h3>🏗️
<hr/>
<ul>
  <li>
    controller/
    <ul>
      <li>MainController.java</li>
      <li>ScreenController.java</li>
    </ul>
  </li>
  <li>
    model/
    <ul>
      <li>Note.java</li>
      <li>Folder.java</li>
      <li>FoldersManager.java</li>
      <li>NotesManager.java</li>
      <li>ScreenModels.java</li>
    </ul>
  </li>
  <li>
    storage/
    <ul>
      <li>NotesFileManager.java</li>
      <li>FoldersFileManager.java</li>
      <li>ScreenFileManager.java</li>
    </ul>
  </li>
  <li>view/
    <ul>
      <li>ContentPanel.java</li>
      <li>FolderCard.java</li>
      <li>NoteCard.java</li>
      <li>NotePanel.java</li>
      <li>NoteView.java</li>
      <li>PaintCard.java</li>
      <li>MainView.java</li>
    </ul>
  </li>
  <li>Main.java</li>
</ul>
<br/>
🔨<h3>​​Roadmap​</h3>🔨
<hr/>
<p><b>Básico</b></p>

- [x] Separar correctamente la lógica de negocio de la interfaz (arquitectura MVC).
- [x] Implementar la gestión de apuntes (CRUD).
- [x] Marcar y desmarcar apuntes como favoritos.
- [x] Guardar y cargar apuntes desde el almacenamiento.
- [x] Implementar carpetas y subcarpetas en Model.
- [ ] Crear la interfaz principal.
  - [x] Cabecera.
  - [x] Principal (archivos y apuntes tipo 'cajitas').
  - [ ] Acceso rápido (Izquierda del principal).
  - [x] Subprincipal (cambiar entre carpetas y subcarpetas).
  - [x] Acceder a los apuntes y editarlos.
  - [x] Volver a lo anterior.
  - [x] Marcar como 'favorito'.
  - [ ] Eliminar carpetas (con el interior) y apuntes.
  - [ ] Mover carpetas y apuntes de lugar.
  - [ ] Hacerlo todo bonito visualmente.
- [ ] Conectar controlador con modelo y vista.
- [x] Mostrar y organizar la lista de apuntes y carpetas.
- [ ] Permitir ordenar los apuntes.
<p><b>A futuro</b></p>

- [ ] Añadir modo oscuro.
- [ ] Implementar categorías.
- [ ] Implementar etiquetas.
- [ ] Añadir un buscador.
- [ ] Personalizar fuentes y apariencia del editor.
- [ ] Exportar apuntes.
- [ ] Importar apuntes.
<br/>
<h3>Objetivos de aprendizaje</h3>
<hr/>
<ul>
  <li>Practicar más programación orientada a objetos.</li>
  <li>Diseñar una aplicación de escritorio en Java, para aprender.</li>
  <li>Aplicar buenas prácticas de organización del código.</li>
</ul>
<br/>
🤵<h3>Desarrollador</h3>🤵
<hr/>
<p><b>Pycod</b></p>
<p>Única persona a cargo de todo.</p>
<br/>
<h3><u>Licencia</u></h3>
<hr/>
<p>Este proyecto está licenciado bajo Creative Commons 
Atribución-NoComercial 4.0 Internacional (CC BY-NC 4.0).
https://creativecommons.org/licenses/by-nc/4.0/
</p>
