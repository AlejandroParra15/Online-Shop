package co.edu.icesi.OnlineShop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.icesi.OnlineShop.model.User;
import co.edu.icesi.OnlineShop.repository.UserRepository;
import co.edu.icesi.OnlineShop.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User usuario = userRepository.findByEmail(email);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+email+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+email+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(usuario.getEmail(),usuario.getPassword(),true,true,true,true, authorities);
	}

	@Override
	@Transactional(readOnly=true)
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
