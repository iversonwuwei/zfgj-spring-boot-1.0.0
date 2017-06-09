package com.dlfc.zfgj.common.pdf.pdf;

import com.housecenter.dlfc.framework.boot.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by walden on 2017/3/22.
 */
@Component(value = "fillIntoMap")
public class FillIntoMap implements Fillable<DocumentVo, Map<String, Object>> {
    @Override
    public Map<String, Object> fill(DocumentVo documentVo) {
        Map<String, Object> voMap = new HashMap<>();

        if (documentVo != null){
            Class<?> clazz = documentVo.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields){
                String name = field.getName();
                String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
                try {
                    Method method = clazz.getDeclaredMethod(strGet);
                    Object object = method.invoke(documentVo);
                    voMap.put(name, object);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return voMap;
    }
}
