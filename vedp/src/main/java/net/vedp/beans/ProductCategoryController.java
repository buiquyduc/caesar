package net.vedp.beans;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;

import net.vedp.beans.util.JsfUtil;
import net.vedp.dao.ProductCategoryFacade;
import net.vedp.entities.ProductCategory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Named(value = "productCategoryController")
@ViewScoped
public class ProductCategoryController implements Serializable {

    @Inject
    private ProductCategoryFacade productCategoryFacade;
    private List<ProductCategory> productCategories;
    private List<ProductCategory> filteredProductCategories;
    private ProductCategory productCategory;
    private String productCategoryId;
    private String currentPage = "/pages/productCtg/List.xhtml";

    public void prepareCreateProductCategory() {
        productCategory = new ProductCategory();
        productCategory.setActive(Boolean.TRUE);
        currentPage = "/pages/productCtg/Create.xhtml";
    }

    public void deleteProductCategory(Integer id) {
        if (productCategoryExist(id)) {
            try {
                productCategoryFacade.remove(productCategory);
            } catch (Exception e) {
                System.out.println("Error Delete: "+e.getMessage());
                JsfUtil.addWarningMessageDialog("InvalidAction", "ErrorDelete3");
                return;
            }

            JsfUtil.addSuccessMessage("ItemDeleted");
            currentPage = "/pages/productCtg/List.xhtml";
            productCategories.remove(productCategory);
            productCategory = null;

        } else {
            JsfUtil.addWarningMessageDialog("InvalidAction", "ErrorDelete");
        }
    }

    public void cancelEditProductCategory(Integer id) {
        if (productCategoryExist(id)) {
            currentPage = "/pages/productCtg/View.xhtml";
        }
    }

    public void updateProductCategory(Integer id) {
        if (productCategoryExistTwo(id)) {
            productCategory = productCategoryFacade.update(productCategory);
            productCategories = productCategoryFacade.findAll();
            currentPage = "/pages/productCtg/View.xhtml";
        }
    }

    private boolean productCategoryExistTwo(Integer id) {
        if (id != null) {
            ProductCategory productCategory = productCategoryFacade.find(id);
            if (productCategory == null) {
                JsfUtil.addWarningMessage("ItemDoesNotExist");
                productCategories = null;
                currentPage = "/pages/productCtg/List.xhtml";
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public void viewProductCategory() {
        if (productCategoryId != null && JsfUtil.isNumeric(productCategoryId)) {
            Integer id = Integer.valueOf(productCategoryId);
            productCategory = productCategoryFacade.find(id);
            if (productCategory != null) {
                productCategories = productCategoryFacade.findAll();
                currentPage = "/pages/productCtg/View.xhtml";
                return;
            }
        }

        productCategories = productCategoryFacade.findAll();
        currentPage = "/pages/productCtg/List.xhtml";
    }

    public void createProductCategory() {
        if (productCategory != null) {
            productCategory = productCategoryFacade.create(productCategory);
            productCategories = productCategoryFacade.findAll();
            currentPage = "/pages/productCtg/View.xhtml";
        }
    }

    public void prepareEditProductCategory(Integer id) {
        if (productCategoryExist(id)) {
            currentPage = "/pages/productCtg/Edit.xhtml";
        }
    }

    private boolean productCategoryExist(Integer id) {
        if (id != null) {
            productCategory = productCategoryFacade.find(id);
            if (productCategory == null) {
                JsfUtil.addWarningMessage("ItemDoesNotExist");
                productCategories = null;
                currentPage = "/pages/productCtg/List.xhtml";
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public void prepareView() {

        if (productCategory != null) {
            if (productCategoryExist(productCategory.getId())) {
                currentPage = "/pages/productCtg/View.xhtml";
            }
        }
    }

    public void showProductCategoryList() {
        productCategory = null;
        currentPage = "/pages/productCtg/List.xhtml";
    }

    public void showProductCategoryForm() {
        if (productCategories.size() > 0) {
            productCategory = productCategories.get(0);
            currentPage = "/pages/productCtg/View.xhtml";
        }
    }

    public int getProductCategoryIndex() {
        if (productCategories != null && productCategory != null) {
            return productCategories.indexOf(productCategory) + 1;
        }
        return 0;
    }

    public void nextProductCategory() {
        if (productCategories.indexOf(productCategory) == (productCategories.size() - 1)) {
            productCategory = productCategories.get(0);
        } else {
            productCategory = productCategories.get(productCategories.indexOf(productCategory) + 1);
        }
    }

    public void previousProductCategory() {
        if (productCategories.indexOf(productCategory) == 0) {
            productCategory = productCategories.get(productCategories.size() - 1);
        } else {
            productCategory = productCategories.get(productCategories.indexOf(productCategory) - 1);
        }
    }

    public List<ProductCategory> getProductCategories() {
        if (productCategories == null) {
            productCategories = productCategoryFacade.findAll();
        }
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {

        this.productCategories = productCategories;
    }

    public List<ProductCategory> getFilteredProductCategories() {
        return filteredProductCategories;
    }

    public void setFilteredProductCategories(List<ProductCategory> filteredProductCategories) {
        this.filteredProductCategories = filteredProductCategories;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getPage() {
        return currentPage;
    }

    public void setPage(String currentPage) {
        this.currentPage = currentPage;
    }

}
