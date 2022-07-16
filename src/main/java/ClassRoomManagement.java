import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import tech.kbtg.entities.ClassRoom;
import tech.kbtg.entities.Student;

import java.util.List;

public class ClassRoomManagement {

    private final SessionFactory factory;

    public ClassRoomManagement() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public ClassRoom createClass(ClassRoom c) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(c);
        transaction.commit();
        session.close();

        return c;
    }

    public List findAll() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List classRooms = session.createQuery("from ClassRoom").getResultList();
        transaction.commit();
        session.close();
        return classRooms;
    }

    public int clearData() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        List all = findAll();
        for (Object o : all) {
            session.delete(o);
        }

        transaction.commit();
        session.close();

        return all.size();
    }

    public Student addStudent(Student s) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(s);
        transaction.commit();
        session.close();

        return s;
    }

    public ClassRoom findClassById(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        ClassRoom classRoom = session.get(ClassRoom.class, id);
        transaction.commit();
        session.close();

        return classRoom;
    }

    public Student findStudentById(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        transaction.commit();
        session.close();

        return student;
    }
}
