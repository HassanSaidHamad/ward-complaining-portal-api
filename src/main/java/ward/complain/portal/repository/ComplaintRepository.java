package ward.complain.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ward.complain.portal.enums.ComplainStatus;
import ward.complain.portal.models.Complaint;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByWardWardId(long wardId);

    List<Complaint> findByCitizenId(long leaderId);
    List<Complaint> findByWardLeaderId(long leaderId);

    List<Complaint> findByComplainStatus(ComplainStatus status);

    List<Complaint> findByWardLeaderIdAndComplainStatus(Long id, ComplainStatus status);
    Long countByComplainStatus(ComplainStatus status);

    List<Complaint> findByCreatedAtBetween(
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    List<Complaint> findByCreatedAtBetweenAndComplainStatus(
            LocalDateTime startDate,
            LocalDateTime endDate,
            ComplainStatus complainStatus
    );

}