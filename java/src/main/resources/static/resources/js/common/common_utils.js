//==============
/** 
 * Common Utils
 * @author : J.ian
 */
//==============

const COMMON_UTILS = {

    /**
     * Null Check Function
     * @param obj
     * @returns
     */
    isEmpty: function(obj) {

        return (obj == '' || obj == null || obj == undefined || obj == NaN || obj == 'null' || obj.length == 0);
    }, 

    /**
     * Save Items to Local Storage
     * @param key
     * @param obj
     * @returns
     */
    saveItemLocalStorage: function(key, obj) {

        localStorage.setItem(key, JSON.stringify(obj));
    },

    /**
     * Remove Item from Local Storage
     * @param key
     * @returns
     */
    removeItemLocalStorage: function(key) {

        localStorage.removeItem(key);
    },

    /**
     * Get Items from Local Storage
     * @param key
     * @returns
     */
    getItemLocalStorage: function(key) {

        return JSON.parse(localStorage.getItem(key));
    },

    /**
     * Change Date Type Function
     * @param date (basic form)
     * @returns date (yyyy-MM-DD HH:mm:ss form)
     */
    changeDateType: function(date) {

        function pad(num) {
            num += '';
            let result = num.length < 2 ? '0' + num : num;
            return result;
        }

        let result = date.getFullYear()               // yyyy
                    + '-'
                    + pad(date.getMonth() + 1)        // MM
                    + '-'
                    + pad(date.getDate())             // DD
                    + " "
                    + pad(date.getHours())            // HH
                    + ":"
                    + pad(date.getMinutes())          // mm
                    + ":"
                    + pad(date.getSeconds());         // ss
        return result;
    }
}