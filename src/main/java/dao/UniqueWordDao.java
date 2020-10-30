package dao;

import dao.utils.JpaUtils;
import model.UniqueWord;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * This is a data access object for UniqueWord class
 */
public class UniqueWordDao {
    private static Logger logger = Logger.getLogger(UniqueWordDao.class);

    /**
     * Saves list of words to the database
     *
     * @param list words to save to the database
     */
    public void addWords(List<UniqueWord> list) {
        EntityManager entityManager = JpaUtils.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            for (int i = 0; i < list.size(); i++) {
                entityManager.persist(list.get(i));
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.error(e.getMessage());
        } finally {
            entityManager.close();
            JpaUtils.close();
        }
    }

    /**
     * This is a method for reading unique words from database
     *
     * @return list of unique words sorted by id
     */
    public List<UniqueWord> findAll() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        String queryString = "select u from UniqueWord u order by u.id";
        Query query = entityManager.createQuery(queryString, UniqueWord.class);
        List<UniqueWord> resultList = query.getResultList();
        entityManager.close();
        JpaUtils.close();
        return resultList;
    }
}
