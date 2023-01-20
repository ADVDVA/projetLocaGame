package adrien.faouzi.managedBeans;

import adrien.faouzi.convectorCustom.ProductConverter;
import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Editor;
import adrien.faouzi.entities.Languagegame;
import adrien.faouzi.entities.Product;
import adrien.faouzi.enumeration.MultiPlayer;
import adrien.faouzi.enumeration.Pegi;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.CategoryService;
import adrien.faouzi.services.EditorService;
import adrien.faouzi.services.LanguageGameService;
import adrien.faouzi.utility.CrudBean;
import adrien.faouzi.utility.TableFilter;
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
    private boolean isNewRedirect;
    public void loadProductSelected(TableFilter tableFilter){

        //when update form from this same form. --->
        isNewRedirect = tableFilter.getNewRedirect();
        if(!isNewRedirect){ //reload form from same page.
            return; //do not reload entity from db.
        }

        //when first load form in create. --->
        this.modeSelected = tableFilter.getModeRedirection();
        if(modeSelected == 'c') {
            elementCrudSelected = new Product();
            listCategoryApply = new ArrayList<>(); //set list empty for many to many.
            listLanguageApply = new ArrayList<>();
            return;
        }

        //get product seleted from db by id send by other page.
        elementCrudSelected = ProductConverter.getAsObjectStatic(String.valueOf(tableFilter.getIdRedirection()));
        if(elementCrudSelected!=null){
            listCategoryApply = elementCrudSelected.getListCategory(); //set list from db for many to many.
            listLanguageApply = elementCrudSelected.getListLanguageGame();
        }

    }



    //submit function.
    public String submitForm(HistoricalBean historicalBean){
        boolean submitIsSuccess = true;

        //do verification.
        if(isCategoryApplyError(false)){
            submitIsSuccess=false;
        }else if(isLanguageApplyError(false)){
            submitIsSuccess=false;
        }

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



    //list category get all from db.
    private List<Category> allCategory;
    public List<Category> getAllCategory(){
        return this.allCategory;
    }
    public void initAllCategory(){
        EntityManager em = EMF.getEM();
        CategoryService categoryService = new CategoryService();
        try{
            this.allCategory = categoryService.selectCategoryAll(em);
        }catch(Exception e){
            this.allCategory = new ArrayList<>();
        }finally{
            em.close();
        }
    }
    //get set list category into product.
    private List<Category> listCategoryApply;
    public List<Category> getListCategoryApply(){
        return this.listCategoryApply;
    }
    public void setListCategoryApply(List<Category> listCategoryApply){
        this.listCategoryApply = listCategoryApply;
    }
    public boolean isCategoryInCategoryApply(int idCategoryAsk){
        return listCategoryApply.stream().filter(c -> c.getId() == idCategoryAsk)
                .findFirst()
                .orElse(null) != null;
    }
    //when user select category for apply to product.
    public void applyCategory(int idCategory){
        listCategoryApply.add(
                allCategory.stream().filter(c -> c.getId() == idCategory)
                        .findFirst()
                        .orElse(null)
        );
    }
    //when user select category for delete to product.
    public void deleteCategoryApply(int idCategory){
        listCategoryApply.remove(
                listCategoryApply.stream().filter(c -> c.getId() == idCategory)
                        .findFirst()
                        .orElse(null)
        );
    }
    //rendered error for many to many (min relation need).
    public boolean isCategoryApplyError(){
        return isCategoryApplyError(true);
    }
    public boolean isCategoryApplyError(boolean applyVerifyNewRedirect){
        return false; //no restriction for category relation.
        /*
        return ( //it was an error when...
                listCategoryApply.size() < 0 && //length is lower than relation MCD.
                (!applyVerifyNewRedirect || !isNewRedirect) //and don't render error if it's first load. (or unable verification new redirect)
        );
        */
    }



    //multi player enum.
    public List<MultiPlayer> getAllMultiPlayer(){
        return MultiPlayer.getAllMultiPlayer();
    }



    //pegi enum.
    public List<Pegi> getAllPegi(){
        return Pegi.getAllPegi();
    }



    //list language for select input.
    private List<Languagegame> allLanguage;
    public List<Languagegame> getAllLanguage(){
        return this.allLanguage;
    }
    public void initAllLanguage(){
        EntityManager em = EMF.getEM();
        LanguageGameService languageGameService = new LanguageGameService();
        try{
            this.allLanguage = languageGameService.selectLanguageGameAll(em);
        }catch(Exception e){
            this.allLanguage = new ArrayList<>();
        }finally{
            em.close();
        }
    }
    //get set list language into product.
    private List<Languagegame> listLanguageApply;
    public List<Languagegame> getListLanguageApply(){
        return this.listLanguageApply;
    }
    public void setListLanguageApply(List<Languagegame> listLanguageApply){
        this.listLanguageApply = listLanguageApply;
    }
    public boolean isLanguageInLanguageApply(int idLanguageAsk){
        return listLanguageApply.stream().filter(l -> l.getId() == idLanguageAsk)
                .findFirst()
                .orElse(null) != null;
    }
    //when user select language for apply to product.
    public void applyLanguage(int idLanguage){
        listLanguageApply.add(
                allLanguage.stream().filter(l -> l.getId() == idLanguage)
                        .findFirst()
                        .orElse(null)
        );
    }
    //when user select language for delete to product.
    public void deleteLanguageApply(int idLanguage){
        listLanguageApply.remove(
                listLanguageApply.stream().filter(l -> l.getId() == idLanguage)
                .findFirst()
                .orElse(null)
        );
    }
    //rendered error for many to many (min relation need).
    public boolean isLanguageApplyError(){
        return isLanguageApplyError(true);
    }
    public boolean isLanguageApplyError(boolean applyVerifyNewRedirect){
        return ( //it was an error when...
                listLanguageApply.size() < 1 && //length is lower than relation MCD.
                (!applyVerifyNewRedirect || !isNewRedirect) //and don't render error if it's first load. (or unable verification new redirect)
        );
    }


}
