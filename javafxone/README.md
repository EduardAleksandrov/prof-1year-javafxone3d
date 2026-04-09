# Для 3d графики требуется подключить для VSCode Ubuntu в launch.json. 3d ускорение для Virtual Box включать не надо.

```
    "vmArgs": [
        // "--module-path", "~/.m2/repository/org/openjfx/",
        // "--add-modules", "javafx.controls,javafx.fxml",
        "-Dcom.sun.javafx.experimental.embedded.3d=true",
        "-Dprism.verbose=true",
        "-Dprism.order=es2",
        "-Dprism.forceGPU=true",
    ]
```
