package com.platzi.market.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BitzoneUserDetailsService implements UserDetailsService {

    //Aqui debe ir la verificación de usuario en base de datos.
    //He agreado un usuario y contraseña "a la fuerza" debido a que no se tiene en cuenta en la base de datos
    //para este proyecto.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("bitzone", "{noop}12345", new ArrayList<>());
    }
}
