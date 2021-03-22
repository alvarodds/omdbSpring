package com.entregable.models.service;

import java.util.List;

import com.entregable.models.entity.Pelicula;


public interface IPeliculaService {
	
	public List<Pelicula> findAll();

	public void save(Pelicula pelicula);

	public Pelicula findOne(String id);
	

	
}
