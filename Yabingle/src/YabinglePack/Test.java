package YabinglePack;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Test extends Application {

        static String link;
        Stage stage;
        static WebView myWebView;
        static WebEngine engine;

        public static void visitSite(String s)
        {  
            link = s;    
            Application.launch();
        }
        
        
        @Override
        public void stop()
        {
          
               
        }
        
        
	@Override
	public void start(Stage stage) throws Exception {

                
            
            
            
		myWebView = new WebView();
		engine = myWebView.getEngine();
		engine.load(link);
                
                engine.setUserAgent("Mozilla/5.0 (Linux; U; Android 2.2.1; en-us; Nexus One Build/FRG83) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        
		VBox root = new VBox();
                root.getChildren().addAll(myWebView);
		
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
                this.stage = stage;		
		stage.show();

	}
        
      
        
    
        


}