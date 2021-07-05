package ar.com.shows.repository;

import ar.com.shows.model.ButacaTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ButacaTicketRepository extends JpaRepository<ButacaTicket, Long>, JpaSpecificationExecutor {
}
