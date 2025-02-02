package expert.os.books.ddd.chapter07.hotels.jpa;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public final class App {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try {
            var sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(GuestJPA.class)
                    .addAnnotatedClass(RoomJPA.class)
                    .buildMetadata()
                    .buildSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.close();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private App() {
    }
}
