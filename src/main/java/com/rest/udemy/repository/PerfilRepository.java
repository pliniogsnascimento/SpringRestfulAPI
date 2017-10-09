package com.rest.udemy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rest.udemy.entity.Perfil;

public interface PerfilRepository extends MongoRepository<Perfil, String>{

	Perfil findByNome(String string);
	
	
}
