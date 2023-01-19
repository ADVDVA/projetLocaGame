package adrien.faouzi.managedBeans;

import adrien.faouzi.convectorCustom.ProductConverter;
import adrien.faouzi.entities.Editor;
import adrien.faouzi.entities.Product;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.EditorService;
import adrien.faouzi.utility.CrudBean;
import adrien.faouzi.utility.UtilityProcessing;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProductBean extends CrudBean<Product> implements Serializable {

    //load product selected in previous page.
    public void loadProductSelected(ProductListBean productListBean){

        //when update form from this same form. --->
        boolean isNewRedirect = productListBean.getNewRedirect();
        if(!isNewRedirect){ //reload form from same page.
            return; //do not reload entity from db.
        }

        //when first load form in create. --->
        this.modeSelected = productListBean.getModeRedirection();
        if(modeSelected == 'c') {
            elementCrudSelected = new Product();
            return;
        }

        //get product seleted from db by id send by other page.
        elementCrudSelected = ProductConverter.getAsObjectStatic(String.valueOf(productListBean.getIdRedirection()));

    }



    //submit function.
    public String submitForm(HistoricalBean historicalBean){
        boolean submitIsSuccess = true;

        //do verification.

        if(submitIsSuccess){

            //do create or update.

            if(isModeSelected('c')){

            }else if(isModeSelected('u')){

            }else{ //error
                submitIsSuccess=false;
            }

        }

        return ((submitIsSuccess)? historicalBean.backLastPageHistoric(): null);
    }



    //list editor for select input.
    private List<Editor> allEditor;
    public List<Editor> getAllEditor(){
        return this.allEditor;
    }
    public void initAllEditor(){
        EntityManager em = EMF.getEM();
        EditorService editorService = new EditorService();
        try{
            this.allEditor = editorService.selectEditorAll(em);
        }catch(Exception e){
            this.allEditor = new ArrayList<>();
        }finally{
            em.close();
        }
    }


}
