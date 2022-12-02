package co.edu.icesi.OnlineShop.service;

import co.edu.icesi.OnlineShop.model.User;

public interface IUsuarioService {

	public User findByEmail(String email);
}
