package rugal.sample.core.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Rugal Bernstein
 */
@Document(collection = "student")
public class Student
{

    @Id
    private String id;

    private String name;

    private int age;

    @DBRef
    private ClassRoom classRoom;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public ClassRoom getClassRoom()
    {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom)
    {
        this.classRoom = classRoom;
    }

}
