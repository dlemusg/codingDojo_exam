package com.example.LoginRegister.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.LoginRegister.models.LoginUser;
import com.example.LoginRegister.models.User;
import com.example.LoginRegister.repositories.UserRepository;

@Service
public class AppService {
	
	@Autowired
	private UserRepository repoUser;
	
	public User register(User nuevoUsuario, BindingResult result) {
		if(!nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirm())){
			result.rejectValue("password", "Matches", "Las contraseñas no coinciden");
		}
		
		String nuevoEmail = nuevoUsuario.getEmail();
		if(repoUser.findByEmail(nuevoEmail).isPresent()) {
			result.rejectValue("email", "Unique", "El correo ya éxiste");
		}
		
		if(result.hasErrors()) {
			return null;
		}else {
			String contraEncriptada = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
			nuevoUsuario.setPassword(contraEncriptada);
			return repoUser.save(nuevoUsuario);
		}
		
	}
	
	public User login(LoginUser nuevoLogin, BindingResult result) {
		
		Optional<User> posibleUsuario = repoUser.findByEmail(nuevoLogin.getEmail());
		if(!posibleUsuario.isPresent()) {
			result.rejectValue("email", "Unique", "Correo no registrado");
			return null;
		}
		
		User userLogin = posibleUsuario.get();
		if(!BCrypt.checkpw(nuevoLogin.getPassword(), userLogin.getPassword())) {
			result.rejectValue("password", "Matches", "Contraseña no valida");
		}
		
		if(result.hasErrors()) {
			return null;
		}else {
			return userLogin;
		}
		
		
		
		
		
	}
	
	
	

}
