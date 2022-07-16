import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import tech.kbtg.entities.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeManagement {
    SessionFactory factory;

    public EmployeeManagement() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public int getAll() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        // ORM :: Object Relational Mapping
        List<?> employees = session.createQuery("FROM tech.kbtg.entities.Employee").list();
        for (Object o : employees) {
            Employee employee = (Employee) o;
            System.out.println("Emp :: " + employee);
        }
        tx.commit();
        session.close();

        return employees.size();
    }

    public Employee findById(Long id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        tx.commit();
        session.close();

        return employee;
    }

    public Employee insert(Employee employee) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(employee);
        tx.commit();
        session.close();

        return employee;
    }

    public Employee updateAge(Long id, int age) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        employee.setAge(age);
        session.saveOrUpdate(employee);
        tx.commit();
        session.close();

        return employee;
    }

    public int deleteAll() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete from tech.kbtg.entities.Employee");
        int i = query.executeUpdate();
        tx.commit();
        session.close();

        return i;
    }

    public long findMaxId() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("select max(e.id) from tech.kbtg.entities.Employee e");
        Long id = (Long) query.getResultList().get(0);

        tx.commit();
        session.close();

        return id;
    }

    public List<Employee> findEmployeeOlderThen50() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);
        criteria.where(
                cb.gt(root.get("age"), 50)
        );
        criteria.orderBy(
                cb.asc(root.get("age")),
                cb.asc(root.get("firstName"))
        );

        Query<Employee> query = session.createQuery(criteria);
        List<Employee> list = query.list();

        tx.commit();
        session.close();

        return list;
    }
}
