package com.rest.udemy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.udemy.entity.Perfil;
import com.rest.udemy.service.PerfilService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Perfil")
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PerfilService perfilService;
	
	@ApiOperation(value = "Lista todos os perfis")
	@RequestMapping(method = RequestMethod.GET)
	public List<Perfil> listarPerfis() {
		return this.perfilService.findAll();
	}
	
	@ApiOperation(value = "Salva um novo perfil")
	@RequestMapping(method = RequestMethod.POST)
	public Perfil salvarPerfil(@RequestBody Perfil perfil) {
		return this.perfilService.save(perfil);
	}
	
	@ApiOperation(value = "Edita um perfil passado no body")
	@RequestMapping(method = RequestMethod.PUT)
	public Perfil editarPerfil(@RequestBody Perfil perfil) {
		return this.perfilService.save(perfil);
	}
	
	@ApiOperation(value = "Deleta um perfil pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletarPerfil(@PathVariable String id) {
		this.perfilService.delete(id);
	}
	
	@ApiOperation(value = "Busca um perfil pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Perfil consultarPorId(String id) {
		return this.perfilService.findById(id);
	}
	
	@ApiOperation(value = "Lista um perfil, passando a página e quantidade por página, respectivamente")
	@RequestMapping(value = "/{page}/{count}", method = RequestMethod.GET)
	public Page<Perfil> listarPorPagina(@PathVariable int page,@PathVariable int count) {
		return this.perfilService.findByPage(page, count);
	}

	
}
