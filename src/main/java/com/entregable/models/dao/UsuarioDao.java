package com.entregable.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entregable.models.entity.Pelicula;
import com.entregable.models.entity.Usuario;
import com.entregable.models.service.IPeliculaService;




@Repository
public class UsuarioDao implements IUsuarioDao{

	//El contexto de persistencia se refiere al "traductor" java/Mysql
	@PersistenceContext
	private EntityManager em;
	@Autowired
	IPeliculaService peliculaService;
	
	
	//@Transactional quiere decir que dentro del método se va a realizar una transaccion con la base de datos
	
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() {		
		//CreateQuery sirve para crear una consulta contra el entityManager
		//getResultList Ejecuta la consulta 
		return em.createQuery("select c from Usuario u").getResultList();
		
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		
		if (findOne(usuario.getEmail())!=null) {
			System.out.println("llega al save1");
			if(usuario.getPeliculas()!=null && usuario.getPeliculas().size()>0) {
				System.out.println("llega al save2");
				for(Pelicula p:usuario.getPeliculas()) {
					System.out.println(p);
					peliculaService.save(p);
					
				}
			}
			//Al ya existir el id del cliente se entiende que estoy modificando uno antiguo y hago una "union"
			em.merge(usuario);
		} else {
			em.persist(usuario);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(String email) {
		return em.find(Usuario.class, email);
	}

}
















