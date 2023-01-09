package adrien.faouzi.managedBeans;

import adrien.faouzi.entities.Category;
import adrien.faouzi.entities.Editor;
import adrien.faouzi.entities.Priceplatform;
import adrien.faouzi.entities.Product;
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

        PricePlatformService pricePlatformService = new PricePlatformService();
        EntityTransaction transaction = pricePlatformService.getTransaction();

        try{
            transaction.begin();
            this.pricePlatformSelected = pricePlatformService.findPricePlatformById(
                    pricePlatformListBean.getIdRedirection()
            );
            this.listCategoryApply = this.pricePlatformSelected.getIdProduct().getListCategory();
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.pricePlatformSelected = null;
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            pricePlatformService.close();
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
        ProductService productService = new ProductService();
        EntityTransaction transaction = productService.getTransaction();
        try{
            transaction.begin();
            this.pricePlatformSelected.setIdProduct(
                    productService.selectProductByIdProduct(this.productSelectedId)
            );
            editorSelectedId = this.pricePlatformSelected.getIdProduct().getIdEditor().getId();
            this.listCategoryApply = this.pricePlatformSelected.getIdProduct().getListCategory();
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            productService.close();
        }
    }



    //list product for select input.
    private List<Product> allProduct;
    public List<Product> getAllProduct(){
        return this.allProduct;
    }
    public void initAllProduct(){
        ProductService productService = new ProductService();
        EntityTransaction transaction = productService.getTransaction();
        try{
            transaction.begin();
            this.allProduct = productService.selectProductAll();
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.allProduct = new ArrayList<>();
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            productService.close();
        }
    }



    //list editor for select input.
    private List<Editor> allEditor;
    public List<Editor> getAllEditor(){
        return this.allEditor;
    }
    public void initAllEditor(){
        EditorService editorService = new EditorService();
        EntityTransaction transaction = editorService.getTransaction();
        try{
            transaction.begin();
            this.allEditor = editorService.selectEditorAll();
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.allEditor = new ArrayList<>();
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            editorService.close();
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
        EditorService editorService = new EditorService();
        EntityTransaction transaction = editorService.getTransaction();
        try{
            transaction.begin();
            this.pricePlatformSelected.getIdProduct().setIdEditor(
                    editorService.selectEditorByIdEditor(this.editorSelectedId)
            );
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            editorService.close();
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
        CategoryService categoryService = new CategoryService();
        EntityTransaction transaction = categoryService.getTransaction();
        try{
            transaction.begin();
            this.allCategory = categoryService.selectCategoryAll();
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.allCategory = new ArrayList<>();
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            categoryService.close();
        }
    }
    //when user select category for crud.
    public void selectCategory(int idCategory){
        CategoryService categoryService = new CategoryService();
        EntityTransaction transaction = categoryService.getTransaction();
        try{
            transaction.begin();
            this.categorySelected = categoryService.selectCategoryByIdCategory(idCategory);
            transaction.commit();
        }catch(Exception e){
            UtilityProcessing.debug("error catch : "+e.getMessage());
            this.categorySelected = null;
            if(transaction.isActive())
                transaction.rollback();
        }finally{
            categoryService.close();
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
