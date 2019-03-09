package cadastroPessoas.ProjetoGit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.log4j2.SpringBootConfigurationFactory;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class CadastroPessoasApplication extends Application {
	private ConfigurableApplicationContext springContext;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		springContext = SpringApplication.run(CadastroPessoasApplication.class);
		
		Parent root;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/TelaPrincipal.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		root = fxmlLoader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
		
		
	}

}
