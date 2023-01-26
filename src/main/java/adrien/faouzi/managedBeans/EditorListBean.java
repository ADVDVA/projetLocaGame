package adrien.faouzi.managedBeans;

import adrien.faouzi.convectorCustom.CategoryConverter;
import adrien.faouzi.convectorCustom.EditorConverter;
import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Editor;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.CategoryService;
import adrien.faouzi.services.EditorService;
import adrien.faouzi.utility.TableFilter;
import adrien.faouzi.utility.UtilityProcessing;
import org.primefaces.PrimeFaces;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ManagedBean
@SessionScoped
public class EditorListBean extends TableFilter implements Serializable {

    //editor filtered from db.
    private List<Editor> editorFiltered;
    public List<Editor> getEditorFiltered(){
        return this.editorFiltered;
    }
    public void setEditorFiltered(List<Editor> editorFiltered){
        this.editorFiltered = editorFiltered;
    }

    public void doResearch(){

        EntityManager em = EMF.getEM();
        EditorService editorService = new EditorService();
        try{
            editorFiltered = editorService.findEditorByFilter(this.filter, this.order, this.orderAsc, em);
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            editorFiltered = new ArrayList<>();
        }finally{
            em.close();
        }

    }


    public void deleteEntity(int idEntity, boolean permission){
        if(!permission)
            return;
        int countJoin;

        EntityManager em = EMF.getEM();
        EditorService editorService = new EditorService();
        EntityTransaction transaction = em.getTransaction();
        try{
            countJoin = editorService.getCountOfJoin(idEntity, em);
            if(countJoin==0){ //this entity has no join in db.

                //delete entity
                transaction.begin();
                editorService.delete(EditorConverter.getAsObjectStatic(String.valueOf(idEntity)), em);
                transaction.commit();

            }
        }catch(Exception e){
            UtilityProcessing.debug(e.getMessage());
            countJoin = -1;
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }

        if(countJoin>0){ //impossible delete because join.
            PrimeFaces.current().executeScript("alertI18NMessage(\"errorDeleteBecauseJoin\")");
        }else if(countJoin==0){ //success delete.
            PrimeFaces.current().executeScript("alertI18NMessage(\"successDelete\", true)");
        }else{ //error process.
            PrimeFaces.current().executeScript("alertI18NMessage(\"errorProcessDelete\")");
        }

    }
}
