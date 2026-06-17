package ward.complain.portal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ward.complain.portal.models.Citizen;
import ward.complain.portal.models.Leader;
import ward.complain.portal.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

}