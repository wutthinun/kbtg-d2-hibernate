import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import tech.kbtg.entities.Customer;

import java.util.List;

public class CustomerManagement {

    static SessionFactory factory;

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        factory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        getAll();
        Customer customer1 = findById(1L);
        System.out.println(customer1);

        Customer customer2 = findById(2L);
        System.out.println(customer2);

        insert(new Customer("THE NEW CUSTOMER"));
        update(1L);
        delete(3L);
    }

    public static void getAll() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // HQL
        List resultList = session.createQuery("FROM tech.kbtg.entities.Customer").getResultList();
        for (Object o : resultList) {
            Customer c = (Customer) o;
            System.out.println("Customer:: " + c);
        }

        tx.commit();
        session.close();
    }

    public static Customer findById(Long id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Customer customer = session.get(Customer.class, id);

        tx.commit();
        session.close();

        return customer;
    }

    public static Customer insert(Customer c) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(c);

        tx.commit();
        session.close();
        return c;
    }

    public static Customer update(Long id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Customer c = findById(id);
        c.setName("99 สาธุ");

        session.saveOrUpdate(c);

        tx.commit();
        session.close();
        return c;
    }

    public static Customer delete(Long id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Customer c = findById(id);
        session.delete(c);

        tx.commit();
        session.close();
        return c;
    }

    public static void deleteAll() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // HQL
        Query query = session.createQuery("delete from tech.kbtg.entities.Customer");
        query.executeUpdate();

        tx.commit();
        session.close();
    }
}
