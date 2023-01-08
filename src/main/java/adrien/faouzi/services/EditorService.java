package adrien.faouzi.services;

import adrien.faouzi.entities.Editor;
import adrien.faouzi.projetlocagame.connexion.EMF;

import java.util.List;

public class EditorService extends EMF {
    /**
     *  select all editor from db.
     * @return
     */
    public List<Editor> selectEditorAll()
    {
        return em.createNamedQuery("Editor.SelectEditorAll", Editor.class)
                .getResultList();
    }

    /**
     *  select one editor from db, selected by id.
     * @return
     */
    public Editor selectEditorByIdEditor(int idEditor)
    {
        return em.createNamedQuery("Editor.SelectEditorByIdEditor", Editor.class)
                .setParameter("idEditor", idEditor)
                .getSingleResult();
    }


}
