package adrien.faouzi.managedBeans;

import adrien.faouzi.convectorCustom.EditorConverter;
import adrien.faouzi.entities.Editor;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.EditorService;
import adrien.faouzi.utility.CrudBean;
import adrien.faouzi.utility.TableFilter;
import adrien.faouzi.utility.UtilityProcessing;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@Named
@SessionScoped
public class EditorBean extends CrudBean<Editor> implements Serializable {

    public void loadEditorSelected(TableFilter tableFilter){

        //when update form from this same form. --->
        setTableFilter(tableFilter);
        if(!tableFilter.getNewRedirect()){ //reload form from same page.
            return; //do not reload entity from db.
        }

        //when first load form in create. --->
        this.modeSelected = tableFilter.getModeRedirection();
        if(modeSelected == 'c') {
            elementCrudSelected = new Editor();
            return;
        }

        //get element selected from db by id send by other page.
        elementCrudSelected = EditorConverter.getAsObjectStatic(String.valueOf(tableFilter.getIdRedirection()));

    }



    public String submitForm(HistoricalBean historicalBean, boolean permission){
        if(!permission)
            return null;
        boolean submitIsSuccess = true;

        //do create or update.
        if(isModeSelected('c') || isModeSelected('u')){

            EntityManager em = EMF.getEM();
            EditorService editorService = new EditorService();
            EntityTransaction transaction = em.getTransaction();
            try{
                transaction.begin();
                if(isModeSelected('c')) {
                    editorService.insertEditor(this.elementCrudSelected, em); //insert.
                    resetFilterOfTableFilter(); //if create mode and success insert, reset filter from page list.
                }else
                    editorService.updateEditor(this.elementCrudSelected, em); //update.
                transaction.commit();
            }catch(Exception e){
                UtilityProcessing.debug("error catch (in create/update Editor) : "+e.getMessage());
                submitIsSuccess=false;
            }finally{
                if(transaction.isActive())
                    transaction.rollback();
                em.close();
            }

        }else{ //error
            submitIsSuccess=false;
        }

        return ((submitIsSuccess)? historicalBean.backLastPageHistoric(): null);

    }

}

