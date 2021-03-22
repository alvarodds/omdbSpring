package com.entregable.models.service;

import java.util.List;

import com.entregable.models.component.UsuarioComp;
import com.entregable.models.entity.Usuario;



public interface IUsuarioService {
	
	public List<Usuario> findAll();

	public void save(Usuario usuarioComp);

	public Usuario findOne(String email);
	

	
}
