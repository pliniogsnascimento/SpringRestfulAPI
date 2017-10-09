package com.rest.udemy.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.udemy.entity.Usuario;
import com.rest.udemy.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public UsuarioService() {

	}
	//					registro por pagina   | páginas
	public Page<Usuario> findByPage(int count, int page) {
		Pageable pages = new PageRequest(page, count);
		return this.usuarioRepository.findAll(pages);
	}
	
	public List<Usuario> findByNome(String nome) {
		return this.usuarioRepository.findByNomeLikeIgnoreCase(nome);
	}
	
	public List<Usuario> findAll() {
		return this.usuarioRepository.findAll();
	}
	
	public Usuario save(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	public void delete(String id) {
		this.usuarioRepository.delete(id);
	}

	public Usuario findById(String id) {
		return this.usuarioRepository.findOne(id);
	}
}
