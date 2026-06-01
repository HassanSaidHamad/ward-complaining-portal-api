package ward.complain.portal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ward.complain.portal.models.Citizen;
import ward.complain.portal.models.Leader;
import ward.complain.portal.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    @Query("""
        SELECT u FROM User u
        JOIN u.roles r
        WHERE r.name = :roleName
    """)
    List<User> findByRoleName(String roleName);

    @Query("""
        SELECT u FROM Leader u
        JOIN u.roles r
        WHERE r.name = :roleName
    """)
    List<Leader> findLeadersByRoleName(String roleName);

    @Query("""
        SELECT u FROM User u
        JOIN u.roles r
        WHERE r.name = :roleName
    """)
    List<Citizen> findCitizensByRoleName(String roleName);

    long countByRoles_Name(String roleName);

}