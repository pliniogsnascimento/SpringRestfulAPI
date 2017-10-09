package com.rest.udemy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.udemy.entity.Usuario;
import com.rest.udemy.service.UsuarioService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Usuario")
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@ApiOperation(value = "Lista todos os usuarios")
	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> listarUsuario() {
		return usuarioService.findAll();
	}
	
	@ApiOperation(value = "Salva um novo usuario")
	@RequestMapping(method = RequestMethod.POST)
	public Usuario salvarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}
	
	@ApiOperation(value = "Edita um usuario")
	@RequestMapping(method = RequestMethod.PUT)
	public Usuario editarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}
	
	@ApiOperation(value = "Exclui usuario pelo id")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public void excluirUsuario(@PathVariable String id) {
		usuarioService.delete(id);
	}
	
	@ApiOperation(value = "Consulta usuario pelo id")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Usuario consultarPorId(@PathVariable String id) {
		return usuarioService.findById(id);
	}
	
	@ApiOperation(value = "Exibe usuarios por paginação, numero e página e usuarios por página")
	@RequestMapping(value = "/{page}/{count}", method = RequestMethod.GET)
	public Page<Usuario> listaPaginada(@PathVariable int count,@PathVariable int page) {
		return this.usuarioService.findByPage(count, page);
	}
	
	@ApiOperation(value = "Consulta por nome")
	@RequestMapping(value = "/{nome}/nome", method = RequestMethod.GET)
	public List<Usuario> listaPaginada(@PathVariable String nome) {
		return this.usuarioService.findByNome(nome);
	}
	
}
