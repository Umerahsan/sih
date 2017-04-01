package Pack;

import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application; 
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane; 
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage; 

public class Gridfx extends Application { 
        WebView browser = new WebView();
        
        WebEngine webEngine = browser.getEngine();
        double lat=8.893260000000001;
        double lan = 76.61427;  
        SerialTest st= new SerialTest();
        
        public Gridfx()
        {
            st.initialize();
        }
        void moveMarker(double lat ,double lan)
        {
             Platform.runLater(()-> webEngine.executeScript("moveMarker(" +lat+","+lan+")"));
            //webEngine.executeScript("moveMarker(" +lat+","+lan+")");
        }
   @Override 
   public void start(Stage stage) {      
      //creating label email 
        
        
         URL urlHello = getClass().getResource("newhtml.html");
                  webEngine.load(urlHello.toExternalForm());
       
        
      //Creating Buttons 
      Button button1 = new Button("start"); 
      button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
               
                
  
               new Thread(){
                public void run()
                {
                    System.out.println("run");
                    while(true)
                    {  
                      
                       if(st.q.peek()!=null)
                        {
                        double[] received= st.q.remove();
                                                
                           System.out.println("value"+received[0]+","+received[1]);
                            moveMarker(received[0], received[1]);
                            try {
                                sleep(500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Gridfx.class.getName()).log(Level.SEVERE, null, ex);
                               
                            }
                            
                    }
                    }
                }
            }.start();
            }
            
        });
                      TextField tf= new TextField();
      tf.setText("$1:c"); 
      Button button2 = new Button("Send");  
      button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
             
                try {
                    st.send(tf.getText());
                } catch (IOException ex) {
                    Logger.getLogger(Gridfx.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
            
            
            }
        });
    
              
      
      Button button3 = new Button("0");  
      button3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
            try {
                
                    st.send("$1:c0");
                } catch (IOException ex) {
                    Logger.getLogger(Gridfx.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
      
      Button button4 = new Button("1");  
      button4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {       
                try {
                    st.send("$1:c1");
                } catch (IOException ex) {
                    Logger.getLogger(Gridfx.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
      
      //Creating a Grid Pane 
      GridPane gridPane2= new GridPane();
      GridPane gridPane = new GridPane();    
      
      //Setting size for the pane  
      gridPane.setMinSize(400, 200); 
       
      //Setting the padding  
      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
      
      //Setting the vertical and horizontal gaps between the columns 
      gridPane.setVgap(5); 
      gridPane.setHgap(5);       
      tf.setPrefWidth(60);
      //Setting the Grid alignment 
      gridPane.setAlignment(Pos.CENTER); 
      gridPane2.setAlignment(Pos.CENTER); 
      //Arranging all the nodes in the grid 
      gridPane2.add(tf, 0, 0);
      gridPane2.add(button1, 1, 0);
      gridPane2.add(button2, 1, 2);
      gridPane2.add(button3, 2, 1);
      gridPane2.add(button4, 0, 1);
     // gridPane.add(text1, 0, 0); 
      gridPane.add(browser, 1, 0); 
     // gridPane.add(text2, 0, 1);       
      //gridPane.add(textField2, 1, 1); 
      gridPane.add(gridPane2, 1, 3); 
     // gridPane.add(tf, 1, 2);
      //gridPane.add(button2, 1, 2);  
      
      //Creating a scene object 
      Scene scene = new Scene(gridPane);  
      
      //Setting title to the Stage 
      stage.setTitle("Grid Pane Example"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
   
       
      //Displaying the contents of the stage 
      stage.show(); 
   } 
   
 
} 