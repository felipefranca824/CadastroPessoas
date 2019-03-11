package cadastroPessoas.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXTextField;

import cadastroPessoas.model.Pessoa;
import cadastroPessoas.repository.PessoaRepositorio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
@Controller
public class TelaPrincipalController implements Initializable {
	@FXML
    private JFXTextField fielNome;

    @FXML
    private JFXTextField fielCpf;

    @FXML
    private Button botaoEnviar;

    @FXML
    private Button botaoCancelar;

    @FXML
    private TableView<Pessoa> tabelaPessoas;
    
    @FXML
    private TableColumn<Pessoa, String> colNome;

    @FXML
    private TableColumn<Pessoa, String> colCpf;
    
    @Autowired
    PessoaRepositorio repositorio;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		colNome.setCellValueFactory(new PropertyValueFactory("nome"));
		colCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
		carregarTabela();
	}
	
	public void salvar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(fielNome.getText());
		pessoa.setCpf(fielCpf.getText());
		repositorio.save(pessoa);
		carregarTabela();
		limparCampos();
	}
	
	private void carregarTabela() {
		tabelaPessoas.setItems(FXCollections.observableArrayList(repositorio.findAll()));
	}
	
	public void limparCampos() {
		fielCpf.clear();
		fielNome.clear();
	}
	
	public void remover() {
		repositorio.delete(tabelaPessoas.getSelectionModel().getSelectedItem());
		carregarTabela();
	}
	
	public void atualizar() {
		
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cadastroPessoas/view/TelaAtualizar.fxml"));
			Stage stage = new Stage();
			
			TelaAtualizarController controllerAtualizar = new TelaAtualizarController();
			Pessoa pessoa = tabelaPessoas.getSelectionModel().getSelectedItem();
			controllerAtualizar.setPessoa(pessoa);
			controllerAtualizar.setRepositorio(repositorio);
			controllerAtualizar.setStage(stage);
			fxmlLoader.setController(controllerAtualizar);
			stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(fxmlLoader.load());
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao criar!!");
		}
		System.out.println("Ja fechou");
		
		carregarTabela();
		
	}

}
