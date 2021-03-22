package com.entregable.models.dao;

import java.util.List;

import com.entregable.models.entity.Pelicula;




public interface IPeliculaDao {
	
	public List<Pelicula> findAll();
	
	public void save(Pelicula pelicula);

	Pelicula findOne(String email);

}
