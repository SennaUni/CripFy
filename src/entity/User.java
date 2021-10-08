package entity;

import java.sql.Timestamp;

public class User {

	 	private Long id;
	    private String userName;
	    private String contato;
		private String senha;
	    private String email;
	    private String preferencias;
	    private Timestamp dataCriacao;
	    private Timestamp dataEdicao;

	    public User() {}

	    public Long getId() {
	        return id;
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	    
	    public String getContato() {
			return contato;
		}

		public void setContato(String contato) {
			this.contato = contato;
		}

	    public String getSenha() {
	        return senha;
	    }

	    public void setSenha(String senha) {
	        this.senha = senha;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPreferencias() {
	        return preferencias;
	    }

	    public void setPreferencias(String preferencias) {
	        this.preferencias = preferencias;
	    }

	    public Timestamp getDataCriacao() {
	        return dataCriacao;
	    }

	    public void setDataCriacao(Timestamp dataCriacao) {
	        this.dataCriacao = dataCriacao;
	    }

	    public Timestamp getDataEdicao() {
	        return dataEdicao;
	    }

	    public void setDataEdicao(Timestamp dataEdicao) {
	        this.dataEdicao = dataEdicao;
	    }

	    @Override
	    public String toString() {
	        return "Usuario{" +
	                "id=" + id +
	                ", userName='" + userName + '\'' +
	                ", senha='" + senha + '\'' +
	                ", email='" + email + '\'' +
	                ", preferencias=" + preferencias +
	                ", dataCriacao=" + dataCriacao +
	                ", dataEdicao=" + dataEdicao +
	                '}';
	    }
}
