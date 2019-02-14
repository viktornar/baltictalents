package lt.baltictalents.exam;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.baltictalents.exam.Base;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaseJPARepository extends JpaRepository<Base, Long> {
    @Query("SELECT b FROM Base b WHERE b.id = :id")
    Base getById(@Param("id") Long id);
}