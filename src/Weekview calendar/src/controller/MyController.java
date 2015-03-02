package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyController implements Initializable {
	
	@FXML
	private GridPane weekGrid;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
		
		Pane[][] pne = new Pane[8][25];
		
		for(int i=1; i<8; i++){
            for(int j=1; j<25;j++){            
                    pne[i][j] = new Pane();
                    pne[i][j].setOnDragDetected(new EventHandler<MouseEvent>(){
						@Override
						public void handle(MouseEvent event) {
							Rectangle r = new Rectangle();
                			r.setWidth(175);
                			r.setHeight(60);
                			r.setArcWidth(20);
                			r.setArcHeight(20);
                			r.setStroke(Color.AQUAMARINE);
                			r.setFill(Color.AQUAMARINE);
                        	Pane temp = (Pane) event.getSource();
                        	temp.getChildren().add(r);
                        	temp.startFullDrag();
                        	
                        	/* drag was detected, start drag-and-drop gesture*/

						}
                    });

                    pne[i][j].setOnDragEntered(new EventHandler<DragEvent>(){

						@Override
						public void handle(DragEvent event) {
							
							Pane temp = (Pane) event.getSource();
                        	temp.onDragEnteredProperty();
                        	System.out.println("drag over");
							
						}
                    	
                    	
                    	
                    });
                   
                    weekGrid.add(pne[i][j], i, j);  
                    }
    }
	
	
		
	}

	
	@FXML
	private Label monthLabel;
	
	@FXML
	private Label yearLabel;
	
	@FXML
	private Label mondayDateLabel;
	
	@FXML
	private Label tuesdayDateLabel;
	
	@FXML
	private Label wedensdayDateLabel;
	
	@FXML
	private Label thursdayDateLabel;
	
	@FXML
	private Label fridayDateLabel;
	
	@FXML
	private Label saturdayDateLabel;
	
	@FXML 
	private Label sundayDateLabel;
	

}
