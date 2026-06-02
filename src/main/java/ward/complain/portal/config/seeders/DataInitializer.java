package ward.complain.portal.config.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ward.complain.portal.models.Role;
import ward.complain.portal.models.User;
import ward.complain.portal.repository.RoleRepository;
import ward.complain.portal.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDefaultAdmin(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // ✅ Step 1: Ensure roles exist
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN")));

            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));

            // ✅ Step 2: Check if admin user exists
            Optional<User> adminExists = userRepository.findByEmail("admin@gmail.com");

            if (adminExists.isEmpty()) {
                User admin = new User();
                admin.setUuid(UUID.randomUUID());
                admin.setFirstName("System");
                admin.setLastName("Administrator");
                admin.setEmail("admin@gmail.com");
                admin.setPhone("0000000000");
                admin.setPassword(passwordEncoder.encode("admin@123"));

                admin.setRoles(Collections.singleton(adminRole));

                userRepository.save(admin);

                System.out.println("✅ Default admin user created: admin@gmail.com / admin@123");
            } else {
                System.out.println("ℹ️ Admin user already exists.");
            }
        };
    }
}
