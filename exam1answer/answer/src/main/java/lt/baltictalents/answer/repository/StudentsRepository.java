package lt.baltictalents.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.baltictalents.answer.model.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Integer>{

}