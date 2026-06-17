package ward.complain.portal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ward.complain.portal.models.Citizen;
import ward.complain.portal.models.Leader;

@Repository
public interface LeaderRepository extends JpaRepository<Leader, Long> {

}