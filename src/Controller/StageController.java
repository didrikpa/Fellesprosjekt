package Controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageController {
    Stage stage;
    Stage popUp;


    public void setStage(String resource){
        try{
            FXMLLoader stageLoader = new FXMLLoader(this.getClass().getResource("../Views/" + resource));
            Parent root = (Parent) stageLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            System.out.print(e);
        }
    }


    //This is not done yet
    public void setPopUp(String resource){
        try{
            FXMLLoader stageLoader = new FXMLLoader(getClass().getResource(resource));
            Parent root = (Parent) stageLoader.load();
            Scene scene = new Scene(root, 500, 300);
            popUp.setScene(scene);
            popUp.show();
        }
        catch (Exception e){
            System.out.print(e);
        }
    }
}
