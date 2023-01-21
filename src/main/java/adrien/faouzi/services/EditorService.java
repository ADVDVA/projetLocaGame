package adrien.faouzi.services;

import adrien.faouzi.entities.Editor;
import adrien.faouzi.projetlocagame.connexion.EMF;

import javax.persistence.EntityManager;
import java.util.List;

public class EditorService {

    /**
     * select all editor from db.
     * @param em entity manager for aces to db.
     * @return list of all editor from db.
     */
    public List<Editor> selectEditorAll(EntityManager em)
    {
        return em.createNamedQuery("Editor.SelectEditorAll", Editor.class)
                .getResultList();
    }

    /**
     * select one editor from db, selected by id.
     * @param idEditor id of editor research in db.
     * @param em entity manager for aces to db.
     * @return one instance of editor.
     */
    public Editor selectEditorByIdEditor(int idEditor, EntityManager em)
    {
        return em.createNamedQuery("Editor.SelectEditorByIdEditor", Editor.class)
                .setParameter("idEditor", idEditor)
                .getSingleResult();
    }


}
