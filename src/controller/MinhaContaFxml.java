package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.SetPages;
import util.UserLogado;
import util.util;

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
    	try {
    		if(JOptionPane.showConfirmDialog(null, "Deseja realmente remover?", "Calma lá amigao!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
    			
        		UserController uc = new UserController();
     
        		uc.deleteUser(UserLogado.getUserLogado());
        		JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE); 
        		UserLogado.LogOffUser();
            	SetPages.LoginPage(event);
    		}	
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Porra miranda" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    @FXML
    void btnEditar(ActionEvent event) {
    	if(tBoxSenha.getText().isEmpty() || tBoxNome.getText().isEmpty() || tBoxContato.getText().isEmpty() || tBoxEmail.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(tBoxNome.getText().length() > 60) {
    		JOptionPane.showMessageDialog(null, "O campo nome pode ter no máximo 60 caracteres!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(tBoxEmail.getText().length() > 45) {
    		JOptionPane.showMessageDialog(null, "O campo email deve ter no máximo 45 caracteres!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(tBoxSenha.getText().length() > 16 || tBoxSenha.getText().length() < 6) {
    		JOptionPane.showMessageDialog(null, "O campo senha deve ter no mínimo 6 e no máximo 16 caracteres!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(!util.isNumber(tBoxContato.getText()) || tBoxContato.getText().length() > 11 || tBoxContato.getText().length() < 10){
    		JOptionPane.showMessageDialog(null, "Favor inserir um contato válido", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(!util.isEmail(tBoxEmail.getText())) {
    		JOptionPane.showMessageDialog(null, "Favor inserir um e-mail válido", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if (!tBoxSenha.getText().equals(UserLogado.getUserPassword())) {
    		JOptionPane.showMessageDialog(null, "Favor apresentar a senha atual correta!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if (tBoxSenha.getText().equals(tBoxNovaSenha.getText())) {
    		JOptionPane.showMessageDialog(null, "A nova senha não pode ser igual a atual!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if (tBoxNovaSenha.getText().length() > 16 || tBoxNovaSenha.getText().length() < 6) {
    		JOptionPane.showMessageDialog(null, "O campo nova senha deve ter no mínimo 6 e no máximo 16 caracteres!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if (!tBoxNovaSenha.getText().equals(tBoxConfirmarSenha.getText())) {
    		JOptionPane.showMessageDialog(null, "As senhas não apresentam igualdade", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else {
    		try {
        		
        		User user =  new User();
            	
        		user.setId(UserLogado.getUserId());
            	user.setUserName(tBoxNome.getText());
            	user.setEmail(tBoxEmail.getText());
            	user.setContato(tBoxContato.getText());
            	user.setSenha(tBoxSenha.getText());
            	
            	UserController uc = new UserController();
            	
            	uc.updateUser(user);
            	
            	JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);     	
            	refreshPage();
            	showForm(false);
            	
        	} catch (Exception e) {
        		JOptionPane.showMessageDialog(null, "Este email já esta ocupado favor selecionar outro", "ERRO", JOptionPane.ERROR_MESSAGE);
        	}
    	}
    }

    @FXML
    void btnLogOff(ActionEvent event) throws IOException {
    	UserLogado.LogOffUser();
    	SetPages.LoginPage(event);
    }

    @FXML
    void btnShowForm(ActionEvent event) {
    	showForm(true);
    	tBoxSenha.setText(UserLogado.getUserPassword());
    	tBoxNome.setText(UserLogado.getUserName());
    	tBoxContato.setText(UserLogado.getUserContato());
    	tBoxEmail.setText(UserLogado.getUserEmail());
    }

    @FXML
    void clickCarteira(MouseEvent event) throws IOException {
    	SetPages.CarteiraPage(event);
    }

    @FXML
    void clickDashboard(MouseEvent event) throws IOException {
		SetPages.HomePage(event);
    }
    
    @FXML
    void clickMinhaConta(MouseEvent event) throws IOException {
    	SetPages.PerfilPage(event);
    }
    
    @FXML
    void clickFavoritos(MouseEvent event) throws IOException {
		SetPages.FavPage(event);
    }

    @FXML
    void clickSimularCompra(MouseEvent event) throws IOException {
    	SetPages.CompraPage(event);
    }

    @FXML
    void clickSimularVenda(MouseEvent event) throws IOException {
    	SetPages.VendaPage(event);
    }
    
    public void showForm(Boolean b) {	
    	lblFormulario.setVisible(b);
    	lblNomeFormulario.setVisible(b);
    	lblEmailFormulario.setVisible(b);
    	lblContatoFormulario.setVisible(b);
    	lblSenhaAtualFormulario.setVisible(b);
    	lblNovaSenhaFormulario.setVisible(b);
    	lblConfirmarSenhaFormulario.setVisible(b);
    	btnEditar.setVisible(b);
    	tBoxSenha.setVisible(b);
    	tBoxNovaSenha.setVisible(b);
    	tBoxConfirmarSenha.setVisible(b);
    	tBoxNome.setVisible(b);
    	tBoxContato.setVisible(b);
    	tBoxEmail.setVisible(b);
    }
    
    public void refreshPage() {
    	lblContato.setText(UserLogado.getUserContato());
        lblNome.setText(UserLogado.getUserName());
        lblEmail.setText(UserLogado.getUserEmail());
    }
    
    public void initialize() {
    	lblUser.setText("Seja bem vindo(a), " + UserLogado.fulano());
    	refreshPage();
	}
}
