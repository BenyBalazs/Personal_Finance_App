package Database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.Metadata;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {

    private static ServiceRegistry registry;
    private static SessionFactory sessionFactory;
    private static Logger logger = LoggerFactory.getLogger("DBConnection.class");

    public static SessionFactory getSessionFactory() {
        try {
            if (sessionFactory == null) {
                logger.debug("Load cfg file");
                registry = new StandardServiceRegistryBuilder().
                        configure().build();
                logger.debug("Creating metadata");
                MetadataSources sources = new MetadataSources(registry);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
        } catch (Exception e) {
            logger.error("Database error", e);
        }
        if(registry != null)
            StandardServiceRegistryBuilder.destroy(registry);

        return sessionFactory;
    }
    public static void close(){
        if(registry != null)
            StandardServiceRegistryBuilder.destroy(registry);

    }



}
