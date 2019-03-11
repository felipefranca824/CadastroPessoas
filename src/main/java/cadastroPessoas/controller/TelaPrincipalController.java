package cadastroPessoas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXTextField;

import cadastroPessoas.model.Pessoa;
import cadastroPessoas.repository.PessoaRepositorio;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

}
