package nthu.learncloud.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class CustomBeanUtils {

    //多組屬性為空處理
    public static String[] getNullPropertNames(Object source){
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertNames = new ArrayList<>();
        for(PropertyDescriptor pd:pds) {
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullPropertNames.add(propertyName);
            }
        }
        return  nullPropertNames.toArray(new String[nullPropertNames.size()]);
    }
}
