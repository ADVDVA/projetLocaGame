package adrien.faouzi.managedBeans;

import adrien.faouzi.convectorCustom.PricePlatformConverter;
import adrien.faouzi.entities.Platform;
import adrien.faouzi.entities.Priceplatform;
import adrien.faouzi.entities.Product;
import adrien.faouzi.projetlocagame.connexion.EMF;
import adrien.faouzi.services.PlatformService;
import adrien.faouzi.services.PricePlatformService;
import adrien.faouzi.services.ProductService;
import adrien.faouzi.utility.CrudBean;
import adrien.faouzi.utility.TableFilter;
import adrien.faouzi.utility.UtilityProcessing;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class PricePlatformBean extends CrudBean<Priceplatform> implements Serializable {

    /**
     * load entity (in parent CrudBean) for crud form.
     * @param tableFilter object parent of listBean contain redirection page information and id of entity selected.
     */
    public void loadPricePlatformSelected(TableFilter<Priceplatform> tableFilter){

        //when update form from this same form. --->
        setTableFilter(tableFilter);
        if(!tableFilter.getNewRedirect()){ //reload form from same page.
            return; //do not reload entity from db.
        }

        //when first load form in create. --->
        this.modeSelected = tableFilter.getModeRedirection();
        if(modeSelected == 'c') {
            elementCrudSelected = new Priceplatform();
            return;
        }

        //get element selected from db by id send by other page.
        elementCrudSelected = PricePlatformConverter.getAsObjectStatic(String.valueOf(tableFilter.getIdRedirection()));

    }



    /**
     * submit form entity (create or update mode).
     * @param historicalBean historic management class.
     * @param permission the permission for submit form (create or update).
     * @return last page historic or null.
     */
    public String submitForm(HistoricalBean historicalBean, boolean permission){
        if(!permission)
            return null;
        boolean submitIsSuccess = true;

        //do create or update.
        if(isModeSelected('c') || isModeSelected('u')){

            EntityManager em = EMF.getEM();
            PricePlatformService pricePlatformService = new PricePlatformService();
            EntityTransaction transaction = em.getTransaction();
            try{
                transaction.begin();

                if(isModeSelected('c')) {
                    this.elementCrudSelected.setAvailableStock(0); //if new price platform, set available stock to 0.
                    pricePlatformService.insert(this.elementCrudSelected, em); //insert.
                    resetFilterOfTableFilter(); //if create mode and success insert, reset filter from page list.
                }else
                    pricePlatformService.update(this.elementCrudSelected, em); //update.

                transaction.commit();
            }catch(Exception e){
                UtilityProcessing.debug("error catch (in create/update PricePlatform) : "+e.getMessage());
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



    //list all product for select.
    private List<Product> allProduct;
    public List<Product> getAllProduct(){ return this.allProduct; }
    public void initAllProduct(){
        EntityManager em = EMF.getEM();
        ProductService productService = new ProductService();
        try{
            this.allProduct = productService.selectProductAll(em);
        }catch(Exception e){
            this.allProduct = new ArrayList<>();
        }finally{
            em.close();
        }
    }



    //list all platform for select.
    private List<Platform> allPlatform;
    public List<Platform> getAllPlatform(){ return this.allPlatform; }
    public void initAllPlatform(){
        EntityManager em = EMF.getEM();
        PlatformService platformService = new PlatformService();
        try{
            this.allPlatform = platformService.selectPlatformAll(em);
        }catch(Exception e){
            this.allPlatform = new ArrayList<>();
        }finally{
            em.close();
        }
    }



    //for image file.
    private UploadedFile imageFile;
    public UploadedFile getImageFile(){ return this.imageFile; }
    public void setImageFile(UploadedFile imageFile){ this.imageFile = imageFile; }
    public boolean imageFileIsNull(){
        return (imageFile==null && (this.elementCrudSelected.getPicture()==null || this.elementCrudSelected.getPicture().equals("")));
    }
    public void fileUploadListener(FileUploadEvent event){
        UtilityProcessing.debug("------------");
        UtilityProcessing.debug("download img");
        UtilityProcessing.debug(String.valueOf(imageFile));
        UtilityProcessing.debug(String.valueOf(event));
        this.imageFile = event.getFile();
        UtilityProcessing.debug(imageFile.getFileName());
        UtilityProcessing.debug("------------");
    }
    public String getUrlImage(){
        return "images/ImageCarousel0"+(int)(Math.random()*4)+".jpg";
    }

}