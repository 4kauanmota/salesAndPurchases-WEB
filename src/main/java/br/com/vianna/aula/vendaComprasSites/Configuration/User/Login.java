package br.com.vianna.aula.vendaComprasSites.Configuration.User;

import br.com.vianna.aula.vendaComprasSites.model.Admins;
import br.com.vianna.aula.vendaComprasSites.model.Clients;
import br.com.vianna.aula.vendaComprasSites.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class Login implements UserDetails {

    private Users user;

    public Login(Users user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> roles = new ArrayList<>();

        if (user instanceof Clients) {
            roles.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        } else if (user instanceof Admins) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return roles;
    }

    public Users getUser(){
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
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
}
