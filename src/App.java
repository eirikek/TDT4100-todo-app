import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 
         
public class App extends Application {

   @Override 
   public void start(Stage stage) throws Exception {
      try {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
         Parent root = fxmlLoader.load();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         
         String css = this.getClass().getResource("style.css").toExternalForm();
         scene.getStylesheets().add(css);
         stage.setTitle("Kontaktliste");
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }

   public static void main(String args[]){ 
      launch(args);
   } 
}
