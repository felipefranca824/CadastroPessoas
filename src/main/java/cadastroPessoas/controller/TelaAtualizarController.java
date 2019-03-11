package cadastroPessoas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import cadastroPessoas.model.Pessoa;
import cadastroPessoas.repository.PessoaRepositorio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class TelaAtualizarController implements Initializable{
	 @FXML
	 private JFXTextField fieldNome;

	 @FXML
	 private JFXTextField fieldCpf;
	 
	 private PessoaRepositorio repositorio;
	 
	 private Pessoa pessoa;
	 
	 private Stage stage;

	public void setRepositorio(PessoaRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fieldNome.setText(this.pessoa.getNome());
		fieldCpf.setText(this.pessoa.getCpf());
	}
	
	public void confirmar() {
		this.pessoa.setNome(fieldNome.getText());
		this.pessoa.setCpf(fieldCpf.getText());
		this.repositorio.save(this.pessoa);
		cancelar();
	}
	
	public void cancelar() {
		this.stage.close();
	}

}
