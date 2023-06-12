package com.example.LoginRegister.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {
	
	@NotEmpty(message="Favor ingresar Email")
	@Email(message="Ingreso correo valido")
	private String email;
	
	@NotEmpty(message="Favor ingresar contraseña")
	@Size(min=6, max=128)
	private String password;

	public LoginUser() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
