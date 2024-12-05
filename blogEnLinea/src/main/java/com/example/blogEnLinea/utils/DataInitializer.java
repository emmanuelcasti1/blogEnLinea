package com.example.blogEnLinea.utils;

import com.example.blogEnLinea.model.Rol;
import com.example.blogEnLinea.model.Usuario;
import com.example.blogEnLinea.repository.IRolRepository;
import com.example.blogEnLinea.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.security.user.password}")
    private String adminPassword;

    @Value("${spring.security.user.name}")
    private String adminUsername;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            // Intentar recuperar el rol ADMIN, si no existe, crearlo
            Optional<Rol> adminRolOpt = rolRepository.findByNombreRol("ADMIN");
            Rol adminRol;
            if (adminRolOpt.isPresent()) {
                adminRol = adminRolOpt.get();
            } else {
                adminRol = new Rol();
                adminRol.setNombreRol("ADMIN");
                adminRol = rolRepository.saveAndFlush(adminRol);
            }

            // Crear usuario ADMIN
            Usuario adminUser = new Usuario();
            adminUser.setUsername(adminUsername);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));
            adminUser.setEnabled(true);
            adminUser.setAccountNotExpired(true);
            adminUser.setAccountNotLocked(true);
            adminUser.setCredentialNotExpired(true);
            adminUser.setRoleList(Set.of(adminRol));
            usuarioRepository.save(adminUser);
        }
    }
}
