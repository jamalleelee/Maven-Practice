package rugal.sample.core.repository;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rugal.JUnitSpringTestBase;
import rugal.sample.core.entity.Student;

/**
 *
 * @author Rugal Bernstein
 */
public class StudentRepositoryTest extends JUnitSpringTestBase
{

    @Autowired
    private ClassRoomRepository classrepository;

    @Autowired
    private StudentRepository repository;

    public StudentRepositoryTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

//    @Test
    public void testCount()
    {
        System.out.println("count");
        Long result = repository.count();
        System.out.println(result);
    }

    @Test
    public void testSave()
    {
        System.out.println("save");
        Student result = new Student();
        result.setName("Bernstein");
        result.setAge(26);
        result.setClassRoom(classrepository.findOne(new ObjectId("55b9a4cf5aa5f926d81d41cd")));
        repository.save(result);
    }

//    @Test
    public void testGet()
    {
        System.out.println("get");
        Student result = repository.findOne(new ObjectId("55541f64a06657226c35de4f"));
        System.out.println(result.getClassRoom().getName());
    }

//    @Test
    public void testUpdate()
    {
        System.out.println("get");
        Student result = repository.findOne(new ObjectId("555418a10032f55ffbf8ca77"));
        System.out.println(result.getName());
        result.setName("Tenjin");
        repository.save(result);
    }
}
