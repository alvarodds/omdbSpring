package com.entregable.models.dao;

import java.util.List;

import com.entregable.models.component.UsuarioComp;
import com.entregable.models.entity.Usuario;

public interface IUsuarioDao {
	
	public List<Usuario> findAll();
	
	public void save(Usuario usuario);

	Usuario findOne(String email);

}
