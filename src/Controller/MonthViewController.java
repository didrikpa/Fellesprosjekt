package Controller;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Model.PersonalAppointment;
import Server.*;

public class MonthViewController {

    @FXML Pane pane00;
    @FXML Pane pane10;
    @FXML Pane pane20;
    @FXML Pane pane30;
    @FXML Pane pane40;
    @FXML Pane pane50;
    @FXML Pane pane60;
    @FXML Pane pane01;
    @FXML Pane pane11;
    @FXML Pane pane21;
    @FXML Pane pane31;
    @FXML Pane pane41;
    @FXML Pane pane51;
    @FXML Pane pane61;
    @FXML Pane pane02;
    @FXML Pane pane12;
    @FXML Pane pane22;
    @FXML Pane pane32;
    @FXML Pane pane42;
    @FXML Pane pane52;
    @FXML Pane pane62;
    @FXML Pane pane03;
    @FXML Pane pane13;
    @FXML Pane pane23;
    @FXML Pane pane33;
    @FXML Pane pane43;
    @FXML Pane pane53;
    @FXML Pane pane63;
    @FXML Pane pane04;
    @FXML Pane pane14;
    @FXML Pane pane24;
    @FXML Pane pane34;
    @FXML Pane pane44;
    @FXML Pane pane54;
    @FXML Pane pane64;
    @FXML Pane pane05;
    @FXML Pane pane15;
    @FXML Pane pane25;
    @FXML Pane pane35;
    @FXML Pane pane45;
    @FXML Pane pane55;
    @FXML Pane pane65;

    @FXML Label label00;
    @FXML Label label10;
    @FXML Label label20;
    @FXML Label label30;
    @FXML Label label40;
    @FXML Label label50;
    @FXML Label label60;
    @FXML Label label01;
    @FXML Label label11;
    @FXML Label label21;
    @FXML Label label31;
    @FXML Label label41;
    @FXML Label label51;
    @FXML Label label61;
    @FXML Label label02;
    @FXML Label label12;
    @FXML Label label22;
    @FXML Label label32;
    @FXML Label label42;
    @FXML Label label52;
    @FXML Label label62;
    @FXML Label label03;
    @FXML Label label13;
    @FXML Label label23;
    @FXML Label label33;
    @FXML Label label43;
    @FXML Label label53;
    @FXML Label label63;
    @FXML Label label04;
    @FXML Label label14;
    @FXML Label label24;
    @FXML Label label34;
    @FXML Label label44;
    @FXML Label label54;
    @FXML Label label64;
    @FXML Label label05;
    @FXML Label label15;
    @FXML Label label25;
    @FXML Label label35;
    @FXML Label label45;
    @FXML Label label55;
    @FXML Label label65;
    
    @FXML TextField searchBar;
    @FXML ListView<String> searchList;

    @FXML
    GridPane gridPane;
    //Hovedviewet hvor stage hentes fra
    @FXML
    Pane mainMonthViewPane;
    //Underview - mainViewMid inneholder enten month- eller week-kalender
    @FXML 
    Pane mainViewMid;
    DatabaseServer server;
    Stage stage;
//    Viderefører data om innelogget bruker fra loginsekvensen
    public MonthViewController(DatabaseServer loginServer){
    	server = loginServer;
    }
    
// LeftBar code 
    @FXML
    private void chooseAppointment(ActionEvent event){
    	searchList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    	    @Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
    	    	System.out.println("LOL");
			}
    	});
	}
    
  	@FXML
	public void searchEvent(ActionEvent event) throws Exception {
  		if(!searchList.isVisible()){
		eventSearchController evs = new eventSearchController(server);
		ArrayList<PersonalAppointment> pas = new ArrayList<PersonalAppointment>();
		pas = evs.eventSearch(searchBar.getText(), true, server.comingUp(10));
		ArrayList<String> nas = new ArrayList<String>();
		for(PersonalAppointment pa :  pas){
			nas.add(pa.getBeskrivelse());
		}
		searchList.setVisible(true);
		searchList.setItems(FXCollections.observableArrayList(nas));
  		}
  		else{
  			searchList.setVisible(false);
  		}
 	}
    
  	@FXML
	public void logOut(ActionEvent event) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPane.fxml"));
		stage = (Stage) mainMonthViewPane.getScene().getWindow();
		stage.setScene(new Scene(loader.load()));
		stage.setTitle("Login");
		stage.show();
 	}
 	
 	@FXML
	public void editUser(ActionEvent event) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("editUserPane.fxml"));
		loader.setController(new editUserController(server));
		stage = (Stage) mainMonthViewPane.getScene().getWindow();
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		stage.setTitle("Edit user");
		stage.show();
 	}

//  This method makes it possible to click on each of the days in the calendar
//  SetOnMouseClicked is used to tell what should happen when someone clicks on a day
//  the if statement is there so that something only happens if the user doubleclicks
    @FXML
    public void clickGrid(MouseEvent e) {
        pane00.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    System.out.print("Pane 00 ");
                }
            }
        });

        pane10.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 10 ");
            }
        });
        pane20.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 20 ");
            }
        });
        pane30.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 30 ");
            }
        });
        pane40.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 40 ");
            }
        });
        pane50.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 50 ");
            }
        });
        pane60.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 60 ");
            }
        });
        pane01.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    if(e.getClickCount() == 2)
                        System.out.print("Pane 01 ");
                }
        });

        pane11.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 11 ");
            }
        });
        pane21.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 21 ");
            }
        });
        pane31.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 31");
            }
        });
        pane41.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 41 ");
            }
        });
        pane51.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 51 ");
            }
        });
        pane61.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 61 ");
            }
        });
        pane02.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    if(e.getClickCount() == 2)
                        System.out.print("Pane 02 ");
                }
        });

        pane12.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 12 ");
            }
        });
        pane22.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 22 ");
            }
        });
        pane32.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 32 ");
            }
        });
        pane42.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 42 ");
            }
        });
        pane52.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 52 ");
            }
        });
        pane62.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 62 ");
            }
        });

        pane03.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 03 ");
            }
        });
        pane13.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 13 ");
            }
        });
        pane23.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 23 ");
            }
        });
        pane33.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 33 ");
            }
        });
        pane43.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 43 ");
            }
        });
        pane53.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 53 ");
            }
        });
        pane63.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 63 ");
            }
        });
        pane04.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 04 ");
            }
        });

        pane14.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 14 ");
            }
        });
        pane24.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 24 ");
            }
        });
        pane34.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 34 ");
            }
        });
        pane44.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 44 ");
            }
        });
        pane54.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 54 ");
            }
        });
        pane64.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 64 ");
            }
        });
        pane05.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 05 ");
            }
        });

        pane15.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 15 ");
            }
        });
        pane25.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 25 ");
            }
        });
        pane35.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 35 ");
            }
        });
        pane45.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(e.getClickCount() == 2)
                    System.out.print("Pane 45 ");
            }
        });
        pane55.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 55 ");
            }
        });
        pane65.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getClickCount() == 2)
                    System.out.print("Pane 65 ");
            }
        });
    }

}
