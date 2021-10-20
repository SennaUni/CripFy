package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.SetPages;
import util.UserLogado;

public class MinhaContaFxml {

	@FXML
    private Label lblContato;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFormulario;

    @FXML
    private Label lblNomeFormulario;

    @FXML
    private Label lblContatoFormulario;

    @FXML
    private Label lblEmailFormulario;

    @FXML
    private Label lblSenhaAtualFormulario;

    @FXML
    private Label lblNovaSenhaFormulario;
    
    @FXML
    private Label lblConfirmarSenhaFormulario;

    @FXML
    private PasswordField tBoxSenha;

    @FXML
    private PasswordField tBoxNovaSenha;

    @FXML
    private PasswordField tBoxConfirmarSenha;

    @FXML
    private TextField tBoxNome;

    @FXML
    private TextField tBoxContato;

    @FXML
    private TextField tBoxEmail;

    @FXML
    private Button btnEditar;

    @FXML
    private Label lblUser;

    @FXML
    void btnDelete(ActionEvent event) {

    }
    
    @FXML
    void btnEditar(ActionEvent event) {

    }

    @FXML
    void btnLogOff(ActionEvent event) throws IOException {
    	UserLogado.LogOffUser();
    	SetPages.LoginPage(event);
    }

    @FXML
    void btnShowForm(ActionEvent event) {
    	showForm();
    	tBoxSenha.setText(UserLogado.getUserPassword());
    	tBoxNome.setText(UserLogado.getUserName());
    	tBoxContato.setText(UserLogado.getUserContato());
    	tBoxEmail.setText(UserLogado.getUserEmail());
    }

    @FXML
    void clickCarteira(MouseEvent event) {

    }

    @FXML
    void clickFavoritos(MouseEvent event) {

    }

    @FXML
    void clickSimularCompra(MouseEvent event) {

    }

    @FXML
    void clickSimularVenda(MouseEvent event) {
    	showForm();
    }
    
    public void showForm() {	
    	lblFormulario.setVisible(true);
    	lblNomeFormulario.setVisible(true);
    	lblEmailFormulario.setVisible(true);
    	lblContatoFormulario.setVisible(true);
    	lblSenhaAtualFormulario.setVisible(true);
    	lblNovaSenhaFormulario.setVisible(true);
    	lblConfirmarSenhaFormulario.setVisible(true);
    	btnEditar.setVisible(true);
    	tBoxSenha.setVisible(true);
    	tBoxNovaSenha.setVisible(true);
    	tBoxConfirmarSenha.setVisible(true);
    	tBoxNome.setVisible(true);
    	tBoxContato.setVisible(true);
    	tBoxEmail.setVisible(true);
    }
    
    public void initialize() {
    	lblContato.setText(UserLogado.getUserContato());
        lblNome.setText(UserLogado.getUserName());
        lblEmail.setText(UserLogado.getUserEmail());
	}
}
