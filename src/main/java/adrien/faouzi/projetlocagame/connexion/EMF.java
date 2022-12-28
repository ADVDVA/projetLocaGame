package adrien.faouzi.projetlocagame.connexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/** 
 * Class to get a connection to the database
 * 
 * @author Renaud DIANA
 */
public final class EMF {
	
	private static EntityManagerFactory emfInstance =
	        Persistence.createEntityManagerFactory("locagame");

    private EMF() {}

    public static EntityManagerFactory getEMF() {
        return emfInstance;
    }
    
    public static EntityManager getEM() {
        return emfInstance.createEntityManager();
    }
 
 /*	Create EntityManager in others classes
  * EntityManager em = EMF.getEM();
  * try {
  *     // ... do stuff with em ...
  * } finally {
  *        mettre le roolback si jamais
  *        if isActive()
  *         {
  *             rollback
  *         }
  *     em.close();
  * }
  * // voir page 135 du cours de jpa
  * */
}
