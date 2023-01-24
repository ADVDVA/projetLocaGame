package adrien.faouzi.managedBeans;

import adrien.faouzi.convectorCustom.CategoryConverter;
import adrien.faouzi.entities.Category;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.CategoryService;
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
public class CategoryBean extends CrudBean<Category> implements Serializable {

    public void loadCategorySelected(TableFilter tableFilter){

        //when update form from this same form. --->
        if(!tableFilter.getNewRedirect()){ //reload form from same page.
            return; //do not reload entity from db.
        }

        //when first load form in create. --->
        this.modeSelected = tableFilter.getModeRedirection();
        if(modeSelected == 'c') {
            elementCrudSelected = new Category();
            return;
        }

        //get element selected from db by id send by other page.
        elementCrudSelected = CategoryConverter.getAsObjectStatic(String.valueOf(tableFilter.getIdRedirection()));

    }



    public String submitForm(HistoricalBean historicalBean, boolean permission){
        boolean submitIsSuccess = true;

        //do verification.
        if(!permission){
            submitIsSuccess=false;
        }

        if(submitIsSuccess){

            //do create or update.
            if(isModeSelected('c') || isModeSelected('u')){

                EntityManager em = EMF.getEM();
                CategoryService categoryService = new CategoryService();
                EntityTransaction transaction = em.getTransaction();
                try{
                    transaction.begin();
                    if(isModeSelected('c'))
                        categoryService.insertCategory(this.elementCrudSelected, em); //insert.
                    else
                        categoryService.updateCategory(this.elementCrudSelected, em); //update.
                    transaction.commit();
                }catch(Exception e){
                    UtilityProcessing.debug("error catch (in create/update Category) : "+e.getMessage());
                    submitIsSuccess=false;
                }finally{
                    if(transaction.isActive())
                        transaction.rollback();
                    em.close();
                }

            }else{ //error
                submitIsSuccess=false;
            }

        }

        return ((submitIsSuccess)? historicalBean.backLastPageHistoric(): null);

    }

}
