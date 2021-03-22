package com.entregable.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entregable.models.entity.Pelicula;




@Repository
public class PeliculaDao implements IPeliculaDao{

	//El contexto de persistencia se refiere al "traductor" java/Mysql
	@PersistenceContext
	private EntityManager em;
	
	//@Transactional quiere decir que dentro del método se va a realizar una transaccion con la base de datos
	@Transactional(readOnly = true)
	@Override
	public List<Pelicula> findAll() {
		//CreateQuery sirve para crear una consulta contra el entityManager
		//getResultList Ejecuta la consulta 
		return em.createQuery("select c from Usuario u").getResultList();
	}

	@Override
	@Transactional
	public void save(Pelicula pelicula) {
		
		if (findOne(pelicula.getImdbId())!=null) {
			//Al ya existir el id del cliente se entiende que estoy modificando uno antiguo y hago una "union"
			em.merge(pelicula);
		} else {
			em.persist(pelicula);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Pelicula findOne(String id) {
		return em.find(Pelicula.class, id);
	}

}
















