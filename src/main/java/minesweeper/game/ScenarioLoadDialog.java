// package minesweeper.game;

// import javafx.scene.control.Dialog;

// public class ScenarioLoadDialog extends Dialog<String> {
//     ListView<String> listView = new ListView<String>();

//     GameLoadChoiceDialog() {
//         setGraphic(null);
//         setHeaderText(null);
//         setTitle("Load Game");
//         try {
//             File[] files = new File("./").listFiles();
//             String fileName;
//             for (File file : files) {
//                 if (file.isFile()) {
//                     fileName = file.getName();
//                     if (fileName.contains(".txt")) {
//                         listView.getItems().add(fileName);
//                     }
//                 }
//             }
//             getItems().add(listView);

//         } catch (NullPointerException e) {
//             System.out.println("GameLoadPopup.GameLoadPopup()");
//             e.printStackTrace();
//         }
//     }

// }