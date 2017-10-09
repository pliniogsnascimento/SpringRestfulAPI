package com.rest.udemy.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rest.udemy.entity.Usuario;

//																	ID
public interface UsuarioRepository extends MongoRepository<Usuario, String>{
	
	List<Usuario> findByNomeLikeIgnoreCase(String nome);

	Usuario findByEmail(String username);

}
