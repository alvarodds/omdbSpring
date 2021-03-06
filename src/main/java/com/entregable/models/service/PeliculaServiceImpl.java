package com.entregable.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entregable.models.dao.IPeliculaDao;
import com.entregable.models.entity.Pelicula;




@Service
public class PeliculaServiceImpl implements IPeliculaService {

	@Autowired
	private IPeliculaDao peliculaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> findAll() {
		// TODO Auto-generated method stub
		return peliculaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Pelicula pelicula) {
		peliculaDao.save(pelicula);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Pelicula findOne(String id) {
		return peliculaDao.findOne(id);
	}

}
