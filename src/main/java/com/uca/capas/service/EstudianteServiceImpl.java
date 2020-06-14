package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.repositories.EstudianteRepo;

@Service
public class EstudianteServiceImpl implements EstudianteService {

	@Autowired
	EstudianteRepo estudianteRepo;
	
	@Override
	public List<Estudiante> findAll() throws DataAccessException {
		return estudianteRepo.mostrarTodos();
	}

	@Override
	public Estudiante findOne(Integer code) throws DataAccessException {
		return estudianteRepo.getOne(code);
	}

	@Override
	@Transactional
	public void insert(Estudiante estudiante) throws DataAccessException {
		estudianteRepo.save(estudiante);
	}

	@Override
	@Transactional
	public void save(Estudiante estudiante) throws DataAccessException {
		estudianteRepo.save(estudiante);
	}
	
	@Override
	@Transactional
	public void delete(Integer c_usuario) throws DataAccessException {
		estudianteRepo.deleteById(c_usuario);
	}


	@Override
	public List<Estudiante> filtrarPor(String cadena) throws DataAccessException{
		return estudianteRepo.mostrarPorNombre(cadena);
	}

	@Override
	public List<Estudiante> empiezaCon(String cadena) throws DataAccessException {
		return estudianteRepo.findByApellidoStartingWith(cadena);
	}
}
