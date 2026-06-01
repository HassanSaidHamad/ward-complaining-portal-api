package ward.complain.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ward.complain.portal.models.Complaint;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByWardWardId(Long wardId);

//    List<Complaint> findByUserUserId(Long userId);

//    List<Complaint> findByStatus(String status);

}