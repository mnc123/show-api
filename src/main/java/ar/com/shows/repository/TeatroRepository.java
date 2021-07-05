package ar.com.shows.repository;

import ar.com.shows.model.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeatroRepository extends JpaRepository<Teatro, Long>, JpaSpecificationExecutor {
}
