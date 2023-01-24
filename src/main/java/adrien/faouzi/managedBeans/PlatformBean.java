package adrien.faouzi.managedBeans;

import adrien.faouzi.convectorCustom.PlatformConverter;
import adrien.faouzi.entities.Platform;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.PlatformService;
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
public class PlatformBean extends CrudBean<Platform> implements Serializable {

    public void loadPlatformSelected(TableFilter tableFilter){

        //when update form from this same form. --->
        if(!tableFilter.getNewRedirect()){ //reload form from same page.
            return; //do not reload entity from db.
        }

        //when first load form in create. --->
        this.modeSelected = tableFilter.getModeRedirection();
        if(modeSelected == 'c') {
            elementCrudSelected = new Platform();
            return;
        }

        //get element selected from db by id send by other page.
        elementCrudSelected = PlatformConverter.getAsObjectStatic(String.valueOf(tableFilter.getIdRedirection()));

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
                PlatformService platformService = new PlatformService();
                EntityTransaction transaction = em.getTransaction();
                try{
                    transaction.begin();
                    if(isModeSelected('c'))
                        platformService.insertPlatform(this.elementCrudSelected, em); //insert.
                    else
                        platformService.updatePlatform(this.elementCrudSelected, em); //update.
                    transaction.commit();
                }catch(Exception e){
                    UtilityProcessing.debug("error catch (in create/update Platform) : "+e.getMessage());
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

