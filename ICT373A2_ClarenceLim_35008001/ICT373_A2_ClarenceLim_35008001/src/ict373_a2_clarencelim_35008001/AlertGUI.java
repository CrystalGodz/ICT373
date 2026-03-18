package ict373_a2_clarencelim_35008001;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertGUI {
    public void displayAlert(String text) {
        Stage alertWindow = new Stage();
        Scene scene;
        
        //settings of new window
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Javascript Application");
        alertWindow.setResizable(false);
        
        
        //label for custom alert message
        Label alertLabel = new Label(text);
        alertLabel.setStyle("-fx-font-size: 14px; -fx-wrap-text: true;");
        
        
        //setup alertBox
        VBox alertBox = new VBox(10);
        alertBox.setPadding(new Insets(10));
        
        
        //add custom text
        alertBox.getChildren().addAll(alertLabel);
        
        
        //define variables' position on alertBox
        scene = new Scene(alertBox, 300, 100);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }
}
