//==============
/** 
 * Recent View 
 */
//==============

const RECENT_VIEW_PRODUCT_EXPIRATION_DATE = 180;    // Delete period of recently viewed products
const RECENT_VIEW_PRODUCT_MAX_SAVE_COUNT = 20;      // Maximum storage quantity of recently viewed products
const RECENT_VIEW_PRODUCTS_KEY = 'RECENT_VIEW_PRODUCTS';

class ProductInfo {

  constructor(productSn) {
    this.productSn = productSn;
    let nowDate = new Date();
    this.viewTime = nowDate.setDate(nowDate.getDate() + Number(RECENT_VIEW_PRODUCT_EXPIRATION_DATE));
    this.viewTime2 = COMMON_UTILS.changeDateType(nowDate)
  }
}

const RECENT_VIEW = {

  saveRecentViewProdcuInfo: function(productSn) {

    let recentViewProducts = COMMON_UTILS.getItemLocalStorage(RECENT_VIEW_PRODUCTS_KEY);
    let recentViewProduct = new ProductInfo(productSn);
  
    // If there is no recently viewed product information
    if (COMMON_UTILS.isEmpty(recentViewProducts)) {
      let newRecentViewProducts = new Array();
      newRecentViewProducts.push(recentViewProduct);
      COMMON_UTILS.saveItemLocalStorage(RECENT_VIEW_PRODUCTS_KEY, newRecentViewProducts);
      return;
    } 

    // If you have recently viewed product information
    let isExistsProduct = false;
    for (let i = 0; i < recentViewProducts.length; i++) {

      if (recentViewProducts[i].productSn != productSn) continue;
      
      isExistsProduct = true;
      recentViewProducts.splice(i, 1);
      recentViewProducts.unshift(recentViewProduct);
      COMMON_UTILS.saveItemLocalStorage(RECENT_VIEW_PRODUCTS_KEY, recentViewProducts);
      break;
    }

    if (!isExistsProduct) {

      // When the number of saved products is "RECENT_VIEW_PRODUCT_MAX_SAVE_COUNT" or more, the last information is deleted.
      if (recentViewProducts.length == Number(RECENT_VIEW_PRODUCT_MAX_SAVE_COUNT)) {
        recentViewProducts.pop();
      }

      recentViewProducts.unshift(recentViewProduct);
      COMMON_UTILS.saveItemLocalStorage(RECENT_VIEW_PRODUCTS_KEY, recentViewProducts);
    }
  }
}