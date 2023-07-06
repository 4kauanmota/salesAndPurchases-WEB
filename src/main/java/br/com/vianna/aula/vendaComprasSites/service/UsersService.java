package br.com.vianna.aula.vendaComprasSites.service;

import br.com.vianna.aula.vendaComprasSites.Configuration.User.Login;
import br.com.vianna.aula.vendaComprasSites.model.Clients;
import br.com.vianna.aula.vendaComprasSites.model.Users;
import br.com.vianna.aula.vendaComprasSites.model.dao.AdminsDAO;
import br.com.vianna.aula.vendaComprasSites.model.dao.ClientsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    ClientsDAO client;

    @Autowired
    AdminsDAO admins;

    public Clients getClientInformations(String login){
        return client.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        Users u = client.findByLogin(username);

        if (u == null){
            u = admins.findByLogin(username);
        }
        if (u == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new Login(u);
    }
}
