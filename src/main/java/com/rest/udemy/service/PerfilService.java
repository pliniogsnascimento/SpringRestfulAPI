package com.rest.udemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.udemy.entity.Perfil;
import com.rest.udemy.repository.PerfilRepository;

@Service
public class PerfilService {
	
	@Autowired
	PerfilRepository perfilRepository;

	public List<Perfil> findAll() {
		return this.perfilRepository.findAll();
	}
	
	public Perfil save(Perfil perfil) {
		this.perfilRepository.save(perfil);
		return perfil;
	}
	
	public Perfil findById(String id) {
		return this.perfilRepository.findOne(id);
	}
	
	public Page<Perfil> findByPage(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.perfilRepository.findAll(pages);
	}
	
	public void delete(String id) {
		this.perfilRepository.delete(id);
	}
	
}
