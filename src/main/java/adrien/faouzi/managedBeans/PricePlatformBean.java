package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.*;
import adrien.faouzi.enumeration.MultiPlayer;
import adrien.faouzi.enumeration.Pegi;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.*;
import adrien.faouzi.utility.UtilityProcessing;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.rmi.CORBA.Util;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class PricePlatformBean implements Serializable {

    //object price platform load from other page.
    private Priceplatform pricePlatformSelected;
    public Priceplatform getPricePlatformSelected(){
        return pricePlatformSelected;
    }
    public void setPricePlatformSelected(Priceplatform pricePlatformSelected){ this.pricePlatformSelected = pricePlatformSelected; }
    public boolean pricePlatformSelectedIsErrorLoad(){
        return (this.pricePlatformSelected == null);
    }




    //char for type of page generate (c,r,u,d).
    private char modeSelected = 'r';
    public boolean isModeSelected(char modeAsk){ return (this.modeSelected == modeAsk); }
    public boolean isNotModeSelected(char modeAsk){
        return !isModeSelected(modeAsk);
    }



    //load price platform selected in previous page.
    public void loadPricePlatformSelected(PricePlatformListBean pricePlatformListBean){

        //when update form. --->
        boolean isNewRedirect = pricePlatformListBean.getNewRedirect();
        if(!isNewRedirect){ //reload form from same page.
            return; //do not reload entity from db.
        }

        //when first load form. --->
        this.categorySelected = null;
        this.listCategoryApply = null;
        this.productSelectedId = 0;
        this.languageSelected = null;
        this.listLanguageApply = null;
        this.defaultErrorSubmit = false;
        this.defaultSuccessSubmit = false;
        this.defaultErrorSubmitMessage = "";

        //when first load form in create. --->
        this.modeSelected = pricePlatformListBean.getModeRedirection();
        if(modeSelected == 'c') {
            this.pricePlatformSelected = new Priceplatform();
            this.pricePlatformSelected.setIdProduct(new Product());
            this.editModeForProduct = 0;
            this.pricePlatformSelected.getIdProduct().setIdEditor(new Editor());
            this.editorSelectedId = 0;
            this.listCategoryApply = new ArrayList<>();
            this.listLanguageApply = new ArrayList<>();
            return;
        }

        //when first load form in update or reade. --->
        this.editModeForProduct=2;

        EntityManager em = EMF.getEM();
        PricePlatformService pricePlatformService = new PricePlatformService();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            this.pricePlatformSelected = pricePlatformService.findPricePlatformById(
                    pricePlatformListBean.getIdRedirection(), em
            );
            this.editorSelectedId = this.pricePlatformSelected.getIdProduct().getIdEditor().getId();
            this.listCategoryApply = this.pricePlatformSelected.getIdProduct().getListCategory();
            this.listLanguageApply = this.pricePlatformSelected.getIdProduct().getListLanguageGame();
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.pricePlatformSelected = null;
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }

    }



    //submit form (c,r,u).
    private boolean defaultErrorSubmit = false;
    public boolean getDefaultErrorSubmit(){ return defaultErrorSubmit; }
    private boolean defaultSuccessSubmit = false;
    public boolean getDefaultSuccessSubmit(){ return defaultSuccessSubmit; }
    private String defaultErrorSubmitMessage=null;
    public String getDefaultErrorSubmitMessage(){ return defaultErrorSubmitMessage; }
    public boolean defaultErrorSubmitMessageIsNull(){ return defaultErrorSubmitMessage==null; }
    public boolean defaultErrorSubmitMessageIsNotNull(){ return defaultErrorSubmitMessage!=null; }
    public void eraseMessageDefault(){
        defaultErrorSubmit=false;
        defaultSuccessSubmit = false;
        defaultErrorSubmitMessage=null;
    }
    public void submitForm(){

        if(isModeSelected('r')){ //if Reade -> add basket.

            // add basket.
            defaultSuccessSubmit=true;

        }else if((isModeSelected('c') && getEditModeForProductInput()) || isModeSelected('u')){ //if Create (input) or Update.

            //verification.
            if(listLanguageApply.size()==0){ //min one language for valid.
                defaultErrorSubmit=true;
                defaultErrorSubmitMessage="Aucune langue n'a été selectionnée.";
            }else if(pricePlatformSelected.getIdProduct().getPegi()==null){
                defaultErrorSubmit=true;
                defaultErrorSubmitMessage="Aucune limite d'age n'a été selectionnée.";
            }else if(pricePlatformSelected.getIdProduct().getMultiPlayer()==null){
                defaultErrorSubmit=true;
                defaultErrorSubmitMessage="Le nombre de joueur n'a été selectionnée.";
            }else if(pricePlatformSelected.getIdProduct().getMultiPlayer()==null){
                defaultErrorSubmit=true;
                defaultErrorSubmitMessage="Aucun editeur n'a été selectionnée.";
            }

            if(isModeSelected('c') && getEditModeForProductInput()) { //if Create (input).

                // do insert product.

                // and do list category insert.
                // and do list language insert.
                defaultSuccessSubmit=true;

            }else if(isModeSelected('u')) { //if Update.

                // do update product.

                // and do list category insert/delete.
                // and do list language insert/delete.
                defaultSuccessSubmit=true;

            }else{ //default error.
                defaultErrorSubmit=true;
            }

        }else{ //default error.
            defaultErrorSubmit=true;
        }

    }



    //mode product for mode create.
    private int editModeForProduct = 0;
    public void setEditModeForProductSelector(){
        if(this.editModeForProduct != 2)
            this.editModeForProduct = 1;
    }
    public void setEditModeForProductInput(){
        if(this.editModeForProduct != 1)
            this.editModeForProduct = 2;
    }
    public boolean getEditModeForProductSelector(){ return editModeForProduct==1; }
    public boolean getEditModeForProductInput(){ return editModeForProduct==2; }
    public boolean getEditModeForProductNeutral(){ return editModeForProduct==0; }
    public boolean renderProductSelector(){ return (isModeSelected('c') && getEditModeForProductSelector()); }
    public boolean renderProductInput(){ return (getEditModeForProductInput()); }
    public boolean renderInputCategory(){ return (renderProductInput() && isNotModeSelected('r')); }
    public boolean renderManyToManyCategory(){ return (isModeSelected('r') || isModeSelected('u') || (isModeSelected('c') && getEditModeForProductInput())); }
    public boolean inputProductDisabled(){ return isModeSelected('r'); }
    public boolean disabledSubmitCreate() { return (getEditModeForProductNeutral() || getEditModeForProductSelector()); }

    //edit product mode.
    public void editProductInput(){
        if(getEditModeForProductSelector())
            return;
        setEditModeForProductInput();
    }
    public void editProductSelector(){
        if(getEditModeForProductInput())
            return;
        setEditModeForProductSelector();
    }



    //when another product was selected in form.
    @Min(1)
    private int productSelectedId = 0;
    public int getProductSelectedId(){ return this.productSelectedId; }
    public void setProductSelectedId(int productSelectedId){ this.productSelectedId = productSelectedId; }
    public void inputProductSelectorOnChange(){

        if(this.productSelectedId == 0){
            return;
        }

        if(getEditModeForProductInput())
            return;

        //reload product from db into price platform selected.
        EntityManager em = EMF.getEM();
        ProductService productService = new ProductService();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            this.pricePlatformSelected.setIdProduct(
                    productService.selectProductByIdProduct(this.productSelectedId, em)
            );
            this.editorSelectedId = this.pricePlatformSelected.getIdProduct().getIdEditor().getId();
            this.listCategoryApply = this.pricePlatformSelected.getIdProduct().getListCategory();
            this.listLanguageApply = this.pricePlatformSelected.getIdProduct().getListLanguageGame();
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }
    }



    //list product for select input.
    private List<Product> allProduct;
    public List<Product> getAllProduct(){
        return this.allProduct;
    }
    public void initAllProduct(){
        EntityManager em = EMF.getEM();
        ProductService productService = new ProductService();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            this.allProduct = productService.selectProductAll(em);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.allProduct = new ArrayList<>();
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }
    }



    //list editor for select input.
    private List<Editor> allEditor;
    public List<Editor> getAllEditor(){
        return this.allEditor;
    }
    public void initAllEditor(){
        EntityManager em = EMF.getEM();
        EditorService editorService = new EditorService();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            this.allEditor = editorService.selectEditorAll(em);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.allEditor = new ArrayList<>();
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }
    }

    //managed editor in detail product.
    private int editorSelectedId = 0;
    public int getEditorSelectedId(){
        return this.editorSelectedId;
    }
    public void setEditorSelectedId(int editorSelectedId){
        this.editorSelectedId = editorSelectedId;
    }
    public void inputProductEditorOnChange(){

        if(this.editorSelectedId == 0){
            this.pricePlatformSelected.getIdProduct().setIdEditor(new Editor());
            return;
        }

        if(getEditModeForProductSelector())
            return;

        //reload product from db into price platform selected.
        EntityManager em = EMF.getEM();
        EditorService editorService = new EditorService();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            this.pricePlatformSelected.getIdProduct().setIdEditor(
                    editorService.selectEditorByIdEditor(this.editorSelectedId, em)
            );
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }
    }



    //managed category in detail product.
    private Category categorySelected;
    public Category getCategorySelected(){
        return categorySelected;
    }
    public String getCategorySelectedName(){ return ((categorySelected==null)? "": categorySelected.getCategoryName()); }
    public void setCategorySelectedName(String categorySelectedName){}

    //list category get all from db.
    private List<Category> allCategory;
    public List<Category> getAllCategory(){
        return this.allCategory;
    }
    public void initAllCategory(){
        EntityManager em = EMF.getEM();
        CategoryService categoryService = new CategoryService();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            this.allCategory = categoryService.selectCategoryAll(em);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.allCategory = new ArrayList<>();
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }
    }

    //when user select category for crud.
    public void selectCategory(int idCategory){
        EntityManager em = EMF.getEM();
        CategoryService categoryService = new CategoryService();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            this.categorySelected = categoryService.selectCategoryByIdCategory(idCategory, em);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.categorySelected = null;
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }
    }

    //get set list category into product into price platform selected.
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



    //multi player.
    public List<MultiPlayer> getAllMultiPlayer(){
        return new ArrayList<>();
        //return MultiPlayer.getAllMultiPlayer();
    }



    //pegi.
    public List<Pegi> getAllPegi(){
        return new ArrayList<>();
        //return Pegi.getAllPegi();
    }



    //language.
    private Languagegame languageSelected;
    public Languagegame getLanguageSelected(){
        return languageSelected;
    }
    public String getLanguageSelectedName(){ return ((languageSelected==null)? "": languageSelected.getLanguageName()); }
    public void setLanguageSelectedName(String categorySelectedName){}

    //list language for select input.
    private List<Languagegame> allLanguage;
    public List<Languagegame> getAllLanguage(){
        return this.allLanguage;
    }
    public void initAllLanguage(){
        EntityManager em = EMF.getEM();
        LanguageGameService languageGameService = new LanguageGameService();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            this.allLanguage = languageGameService.selectLanguageGameAll(em);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.allLanguage = new ArrayList<>();
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }
    }


    //when user select language for crud.
    public void selectLanguage(int idLanguage){
        EntityManager em = EMF.getEM();
        LanguageGameService languageGameService = new LanguageGameService();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            this.languageSelected = languageGameService.selectLanguageByIdLanguage(idLanguage, em);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.categorySelected = null;
        }finally{
            if(transaction.isActive())
                transaction.rollback();
            em.close();
        }
    }
    //get set list language into product into price platform selected.
    private List<Languagegame> listLanguageApply;
    public List<Languagegame> getListLanguageApply(){
        return this.listLanguageApply;
    }
    public void setListLanguageApply(List<Languagegame> listLanguageApply){
        this.listLanguageApply = listLanguageApply;
    }
    public boolean isLanguageInLanguageApply(int idLanguageAsk){
        return listLanguageApply.stream().filter(c -> c.getId() == idLanguageAsk)
                .findFirst()
                .orElse(null) != null;
    }
    //when user select category for apply to product.
    public void applyLanguage(int idLanguage){
        listLanguageApply.add(
                allLanguage.stream().filter(c -> c.getId() == idLanguage)
                        .findFirst()
                        .orElse(null)
        );
    }
    //when user select category for delete to product.
    public void deleteLanguageApply(int idLanguage){
        listLanguageApply.remove(
                listLanguageApply.stream().filter(c -> c.getId() == idLanguage)
                        .findFirst()
                        .orElse(null)
        );
    }


}