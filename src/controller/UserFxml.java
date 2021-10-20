package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.SetPages;
import util.util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import entity.User;

public class UserFxml {
	@FXML
    private VBox vBoxCaptcha;
	
	@FXML
    private Text lblCaptcha;
	
	@FXML
    private TextField txtCaptcha;
	
    @FXML
    private TextField txtLoginEmail;

    @FXML
    private PasswordField txtLoginSenha;

    @FXML
    private TextField txtRegisterNome;

    @FXML
    private TextField txtRegisterContato;

    @FXML
    private TextField txtRegisterEmail;

    @FXML
    private PasswordField txtRegisterSenha;
    
    @FXML
    private PasswordField txtRegisterConfirmSenha;
    
    private int count;
    
    public void initialize() {
		count = 0;
		vBoxCaptcha.setVisible(false);
		lblCaptcha.setText(util.getCaptcha());
	}

	@FXML
    void btnLogin(ActionEvent event) throws IOException{
    	UserController userController = new UserController();
    	
    	if(txtLoginEmail.getText().isEmpty() && txtLoginSenha.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Preencha os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else {
    		
    		if(vBoxCaptcha.isVisible()) {	
    			if(txtCaptcha.getText().isEmpty()) {
    				JOptionPane.showMessageDialog(null, "Favor informar o código!", "ERRO", JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    			if(!txtCaptcha.getText().equals(lblCaptcha.getText())) {
    				JOptionPane.showMessageDialog(null, "Os códigos não apresentam igualdade ", "ERRO", JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    		}
    		
    		try {
    			User u = new User();
    			
    			u.setEmail(txtLoginEmail.getText());
    			u.setSenha(txtLoginSenha.getText());
    			
            	Boolean userLog = userController.authUser(u);
            	
            	if (userLog) {
            		SetPages.HomePage(event);
            	} else {
            		JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "ERRO", JOptionPane.ERROR_MESSAGE);
            		lblCaptcha.setText(util.getCaptcha());
            		count++;
            	}
            	
        		if(count >= 4) {
        			vBoxCaptcha.setVisible(true);
        		} 
        		
    		} catch (ClassNotFoundException | SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro na autenticação!" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
    			lblCaptcha.setText(util.getCaptcha());
    			count++;
    		}	
		
    	}
    }

    @FXML
    void btnRegister(ActionEvent event) {
    	UserController userController = new UserController();
    	
    	System.out.println(txtRegisterSenha.getText());
    	System.out.println(txtRegisterConfirmSenha.getText());
    	
    	if(txtRegisterNome.getText().isEmpty() || txtRegisterContato.getText().isEmpty() || txtRegisterEmail.getText().isEmpty() || txtRegisterSenha.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(!util.isNumber(txtRegisterContato.getText()) || txtRegisterContato.getText().length() > 11 || txtRegisterContato.getText().length() < 10){
    		JOptionPane.showMessageDialog(null, "Favor inserir um contato válido", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(!util.isEmail(txtRegisterEmail.getText())) {
    		JOptionPane.showMessageDialog(null, "Favor inserir um e-mail válido", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if (!txtRegisterSenha.getText().equals(txtRegisterConfirmSenha.getText())) {
    		JOptionPane.showMessageDialog(null, "As senhas não apresentam igualdade", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else {
    		try {
    			User u = new User();
    			
    			u.setUserName(txtRegisterNome.getText());
    			u.setContato(txtRegisterContato.getText());
    			u.setEmail(txtRegisterEmail.getText());
    			u.setSenha(txtRegisterSenha.getText());
    			
            	userController.createUser(u);
            	
            	JOptionPane.showMessageDialog(null, "Usuário cadastrado com successo!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            	
            	txtRegisterNome.setText("");
            	txtRegisterContato.setText("");
            	txtRegisterEmail.setText("");
            	txtRegisterSenha.setText("");
            	txtRegisterConfirmSenha.setText("");
        		
    		}catch (ClassNotFoundException | SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro na inserção!" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
    		}	
    	}
    }
    
    @FXML
    void btnRefresh(ActionEvent event){
    	lblCaptcha.setText(util.getCaptcha());
    }
}
