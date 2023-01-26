package adrien.faouzi.services;

import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Editor;
import adrien.faouzi.entities.Platform;
import adrien.faouzi.entities.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class PlatformService {

    /**
     * get all platform from db.
     */
    public List<Platform> selectPlatformAll(EntityManager em)
    {
        return em.createNamedQuery("Platform.SelectPlatformAll", Platform.class)
                .getResultList();
    }

    /**
     * get platform selected by id.
     */
    public Platform selectPlatformByIdPlatform(int idPlatform, EntityManager em)
    {
        return em.createNamedQuery("Platform.SelectPlatformByIdPlatform", Platform.class)
                .setParameter("idPlatform", idPlatform)
                .getSingleResult();
    }

    /**
     * insert platform in db.
     */
    public Platform insertPlatform(Platform platform, EntityManager em)
    {
        em.persist(platform);
        em.flush();
        return platform;
    }

    /**
     * update platform in db.
     */
    public Platform updatePlatform(Platform platform, EntityManager em)
    {
        em.merge(platform);
        em.flush();
        return platform;
    }



    /**
     * get platform from db with research filter.
     */
    public List<Platform> findPlatformByFilter(String researchWord, String orderBy, boolean asc, EntityManager em)
    {
        if(asc){
            return em.createNamedQuery("Platform.SelectPlatformByFilterAsc", Platform.class)
                    .setParameter("researchWord", researchWord.toLowerCase())
                    .setParameter("orderBy", orderBy)
                    //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                    .getResultList();
        }else{
            return em.createNamedQuery("Platform.SelectPlatformByFilterDesc", Platform.class)
                    .setParameter("researchWord", researchWord.toLowerCase())
                    .setParameter("orderBy", orderBy)
                    //.setParameter("ascOrDesc", ((asc)? "asc": "desc"))
                    .getResultList();
        }
    }

    public int getCountOfJoin(int idPlatform, EntityManager em){
        return em.createNamedQuery("Platform.SelectJoin", Product.class)
                .setParameter("idPlatform", idPlatform)
                .getResultList().size();
    }

    public void delete(Platform platform, EntityManager em){
        if(!em.contains(platform))
            platform = em.merge(platform);
        em.remove(platform);
        em.flush();
    }

}
