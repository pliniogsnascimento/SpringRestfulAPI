package com.rest.udemy.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.udemy.entity.Perfil;
import com.rest.udemy.entity.Usuario;
import com.rest.udemy.repository.UsuarioRepository;

@Service
public class MeuUserDetailsService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public MeuUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override //Autentica via email
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario não existe!", username));
		}
		return new UserRepositoryUserDetails(usuario);
	}

	private static final class UserRepositoryUserDetails extends Usuario implements UserDetails {

		private static final Long serialVersionUID = 1L;

		public UserRepositoryUserDetails(Usuario usuario) {
			super(usuario);
		}

		@Override //Configura os papéis de autorização
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return getPerfis();
		}

		@Override
		public String getUsername() {
			return getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public String getPassword() {
			return getSenha();
		}


	}

}
