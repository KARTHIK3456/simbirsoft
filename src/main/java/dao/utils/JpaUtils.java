package dao.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class helps to create a EntityManagerFactory from the Hibernate configuration file
 */
public class JpaUtils {
    private static EntityManagerFactory emFactory;

    public static EntityManager getEntityManager() {
        emFactory = Persistence.createEntityManagerFactory("testTask");
        return emFactory.createEntityManager();
    }

    public static void close() {
        emFactory.close();
    }
}
