package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class weekViewController implements Initializable {

	private int startEventRowCreation;
	private int finalRow;
	private int currentRow;
	private Rectangle eventRect;
	private Color eventColor;
	private Color eventLabelColor;
	private int currentEventMoveIndex;
	
	@FXML
	private Circle greenCircle;
	
	@FXML
	private Circle lightOrangeCircle;
	
	@FXML
	private Circle blueCircle;
	
	@FXML
	private Circle lightBlueCircle;
	
	@FXML
	private Circle redCircle;
	
	@FXML
	private Circle grayCircle;
	
	@FXML
	private GridPane weekGrid;

	@FXML 
	private void changeColorGray(){
		
		this.eventColor = Color.LIGHTGRAY;
		this.eventLabelColor = Color.BLACK;
		
	}
	
	@FXML
	void changeColorOrange(){
		
		this.eventColor = Color.ORANGE;
		this.eventLabelColor = Color.BLACK;
		
	}
	
	@FXML
	void changeColorLightBlue(){
		
		this.eventColor = Color.LIGHTBLUE;
		
	}
	
	@FXML
	void changeColorFuchsia(){
		
		this.eventColor = Color.FUCHSIA;
		this.eventLabelColor = Color.BLACK;
		
		
	}
	
	@FXML
	void changeColorLime(){
		
		this.eventColor = Color.LIME;
		this.eventLabelColor = Color.GRAY;
		
	}
	
	@FXML
	void changeColorBlue(){
		
		this.eventColor = Color.CADETBLUE;
		this.eventLabelColor = Color.WHITESMOKE;
		
	}
	
	private ImageView dragImageView = new ImageView();

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		
		finalRow = 0;
		startEventRowCreation=0;
		this.eventColor = Color.LIME;
		this.eventLabelColor = Color.GRAY;

		Pane[][] pne = new Pane[8][96];	
		for(int i=1; i<8; i++){
            for(int j=0; j<96;j++){            
                    pne[i][j] = new Pane();
                    createThisPane(pne[i][j]);
                    weekGrid.add(pne[i][j], i, j);  
                    }
		}

		
	}
	
	private void createThisPane(Pane newPane){
		
		newPane.setOnDragDetected(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				/* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                Label newEventLabel = new Label();
                newEventLabel.setText("My New Event");
                newEventLabel.setTextFill(eventLabelColor);
                newEventLabel.setAlignment(Pos.TOP_CENTER);         

				eventRect = new Rectangle();
				eventRect.setWidth(120);
				eventRect.setHeight(15);
				eventRect.setArcWidth(20);
				eventRect.setArcHeight(20);
				eventRect.setStrokeType(StrokeType.OUTSIDE);
				eventRect.setFill(eventColor);
				eventRect.setOpacity(0.5);
				
				createThisEvent(eventRect);
				
            	Pane temp = (Pane) event.getSource();
            	startEventRowCreation = GridPane.getRowIndex(temp);
            	finalRow = startEventRowCreation;
            	System.out.println(startEventRowCreation);
            	
            	
            	
            	/* allow any transfer mode */
            	Dragboard db = temp.startDragAndDrop(TransferMode.ANY);
            	
            	/* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                db.setContent(content);
                
            	temp.getChildren().add(eventRect);
            	temp.getChildren().add(newEventLabel);      	
            	event.consume();
            	
			}
        });
       
		newPane.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
            	 /* data is dragged over the target */
            	
            	Pane temp = (Pane) event.getSource();
            	Pane source = (Pane) event.getGestureSource();

            	currentRow = GridPane.getRowIndex(temp);
            	
            	System.out.println("This is the first row: " + startEventRowCreation);
            	System.out.println("This is the final row:" + finalRow);
            	
            	if (eventRect!=null && event.getGestureSource() != temp && GridPane.getColumnIndex(source)==GridPane.getColumnIndex(temp) && finalRow<currentRow){
            	
                System.out.println("onDragOverPlus");
                
                eventRect.setHeight(eventRect.getHeight()+15);
                finalRow = currentRow;

                event.consume();
            }
            	else if (eventRect!=null && event.getGestureSource() != temp && GridPane.getColumnIndex(source)==GridPane.getColumnIndex(temp) && finalRow>currentRow){
            		            		System.out.println("onDragOverMinus");
                    
                    eventRect.setHeight(eventRect.getHeight()-15);
                    finalRow = currentRow;

                    event.consume();
            		
            		
            	}
            	
            	
            }
        });

		newPane.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	
		    	event.acceptTransferModes(TransferMode.ANY);
            	System.out.println("Drop occurred!");
            	
            
            	Pane temp = (Pane) event.getGestureTarget();
            	System.out.println("Is this empty");
            	
//            	if (event.getGestureTarget() instanceof Rectangle){
//            		
//            		
//            		
//            	}
           
	            int startRowIndexForEvent = startEventRowCreation;
	            int numberOfRows = finalRow - startRowIndexForEvent;
	            int heightForRect = (numberOfRows) * 15;

                event.consume();
		    }
		});
		
	}
	
	private void createThisEvent(Rectangle newEvent){
		
		newEvent.setOnDragDetected(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {

				/* drag was detected, start drag-and-drop gesture*/
                System.out.println("Detected RECTANGLE MOVE");

            	Pane temp = (Pane) event.getSource();
            	currentEventMoveIndex=GridPane.getRowIndex(temp);
            	System.out.println(currentEventMoveIndex);

            	/* allow any transfer mode */
            	Dragboard db = temp.startDragAndDrop(TransferMode.ANY);
            	
            	/* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                db.setContent(content);
            	
            	event.consume();
            	
			}
        });
       
		newEvent.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
            	 /* data is dragged over the target */
            	System.out.println("Entered RECTANGLE MOVE");
            	Pane temp = (Pane) event.getSource();
            	weekGrid.add(newEvent, GridPane.getColumnIndex(temp), GridPane.getRowIndex(temp));
                event.consume();
            }
        });

		newEvent.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	
		    	event.acceptTransferModes(TransferMode.ANY);
            	System.out.println("Drop occurred!");
           
	            int startRowIndexForEvent = startEventRowCreation;
	            int numberOfRows = finalRow - startRowIndexForEvent;
	            int heightForRect = (numberOfRows) * 15;

                event.consume();
		    }
		});
		
	}
	
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
