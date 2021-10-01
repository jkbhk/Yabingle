package YabinglePack;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sun.management.Agent;

public class Test extends Application {

    private static Test instance;
    private static boolean exist = false;
        static String link;
        static Stage stage;
        static WebView myWebView;
        static WebEngine engine;

        public static void visitSite(String s)
        {  
            link = s;    
            if(!exist)
            {
                Application.launch();
                exist = true;
            }
            else
            {
                stage = new Stage();
                stage.show();
                
                
                
            }
        }
        
        
        @Override
        public void stop()
        {
            Platform.exit();
               
        }
        
        
        
	@Override
	public void start(Stage stage) throws Exception {


		myWebView = new WebView();
		engine = myWebView.getEngine();
		engine.load(link);
                
                engine.setUserAgent("Mozilla/5.0 (Linux; U; Android 2.2.1; en-us; Nexus One Build/FRG83) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        
		//VBox root = new VBox();
                //root.getChildren().addAll(myWebView);
		
		Scene scene = new Scene(myWebView, 800, 500);
		stage.setScene(scene);
                this.stage = stage;		
		stage.show();

	}
        
      
        
    
        


}