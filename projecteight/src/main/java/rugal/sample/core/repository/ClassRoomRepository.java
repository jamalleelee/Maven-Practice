package rugal.sample.core.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import rugal.sample.core.entity.ClassRoom;

/**
 *
 * @author Rugal Bernstein
 */
public interface ClassRoomRepository extends CrudRepository<ClassRoom, ObjectId>
{
}
