package com.rest.udemy.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.rest.udemy.entity.Perfil;
import com.rest.udemy.entity.Usuario;
import com.rest.udemy.repository.PerfilRepository;
import com.rest.udemy.repository.UsuarioRepository;


@Component
public class CargaInicial implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PerfilRepository perfilRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		
		System.out.println("Carregando banco de dados");
		
		List<Perfil> perfis = perfilRepository.findAll();

		if (perfis.isEmpty()) {
			perfilRepository.save(new Perfil("ROLE_ADMIN"));
			perfilRepository.save(new Perfil("ROLE_OREIA"));

			Perfil perfil = perfilRepository.findByNome("ROLE_ADMIN");

			List<Perfil> novosPerfis = new ArrayList<>();

			novosPerfis.add(perfil);

			usuarioRepository.save(new Usuario("ADMIN", "admin", "123", novosPerfis));

		}

	}
}