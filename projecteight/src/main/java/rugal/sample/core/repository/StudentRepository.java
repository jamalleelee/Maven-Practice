package rugal.sample.core.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
public interface StudentRepository extends CrudRepository<Student, ObjectId>
{

    public Student findByName(String name);

    Page<Student> findBy(Pageable pageable);
}
