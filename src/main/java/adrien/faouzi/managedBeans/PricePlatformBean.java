package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Editor;
import adrien.faouzi.entities.Priceplatform;
import adrien.faouzi.entities.Product;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.CategoryService;
import adrien.faouzi.services.EditorService;
import adrien.faouzi.services.PricePlatformService;
import adrien.faouzi.services.ProductService;
import adrien.faouzi.utility.UtilityProcessing;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
    public boolean isModeSelected(char modeAsk){
        return (this.modeSelected == modeAsk);
    }
    public boolean isNotModeSelected(char modeAsk){
        return !isModeSelected(modeAsk);
    }


    //load price platform selected in previous page.
    public void loadPricePlatformSelected(PricePlatformListBean pricePlatformListBean){
        boolean isNewRedirect = pricePlatformListBean.getNewRedirect();
        if(!isNewRedirect){ //reload form from same page.
            return; //do not reload entity from db.
        }

        this.categorySelected = null;
        this.listCategoryApply = null;

        this.modeSelected = pricePlatformListBean.getModeRedirection();
        if(modeSelected == 'c') {
            this.pricePlatformSelected = new Priceplatform();
            this.pricePlatformSelected.setIdProduct(new Product());
            productSelectedId = 0;
            this.pricePlatformSelected.getIdProduct().setIdEditor(new Editor());
            editorSelectedId = 0;
            this.listCategoryApply = new ArrayList<>();
            return;
        }

        //set selected option (based on db import).
        editorSelectedId = this.pricePlatformSelected.getIdProduct().getIdEditor().getId();

        EntityManager em = EMF.getEM();
        PricePlatformService pricePlatformService = new PricePlatformService();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            this.pricePlatformSelected = pricePlatformService.findPricePlatformById(
                    pricePlatformListBean.getIdRedirection(), em
            );
            this.listCategoryApply = this.pricePlatformSelected.getIdProduct().getListCategory();
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



    //submit form (c,u).
    public String submitForm(String urlRedirectIfSucces){
        //do Create or Update.
        //if error, return null for stay in page.

        //if Create, verify if productSelectedId!=0, if !=0 not insert Product in DB but update.

        //create or update, need to comparate listCategoryApply width list category into product.

        return urlRedirectIfSucces;
    }



    //when another product was selected in form.
    private int productSelectedId = 0;
    public int getProductSelectedId(){
        return this.productSelectedId;
    }
    public void setProductSelectedId(int productSelectedId){
        this.productSelectedId = productSelectedId;
    }
    public boolean renderSelectProduct(){
        return (this.isModeSelected('c') && this.productSelectedId == 0);
    }
    public void inputProductSelectorOnChange(){
        if(this.productSelectedId == 0){
            //this.pricePlatformSelected.setIdProduct(new Product());
            //this.pricePlatformSelected.getIdProduct().setIdEditor(new Editor());
            //editorSelectedId = 0;
            return;
        }
        //reload product from db into price platform selected.
        EntityManager em = EMF.getEM();
        ProductService productService = new ProductService();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            this.pricePlatformSelected.setIdProduct(
                    productService.selectProductByIdProduct(this.productSelectedId, em)
            );
            editorSelectedId = this.pricePlatformSelected.getIdProduct().getIdEditor().getId();
            this.listCategoryApply = this.pricePlatformSelected.getIdProduct().getListCategory();
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
    public boolean isPricePlatformSelectedHasEditorValid(){
        return (
                this.pricePlatformSelected.getIdProduct() != null &&
                this.pricePlatformSelected.getIdProduct().getIdEditor() != null
        );
    }
    public boolean isPricePlatformSelectedHasNotEditorValid(){
        return !(this.isPricePlatformSelectedHasEditorValid());
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
    public String getCategorySelectedName(){
        return ((categorySelected==null)? "": categorySelected.getCategoryName());
    }
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


}
