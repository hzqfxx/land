package com.test.land.landparent.admin.common.utils;

import java.util.List;

        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.List;

        import org.dozer.DozerBeanMapper;

public class DozerUtil {

    private static DozerBeanMapper mapp = null;;

    public static DozerBeanMapper getMapper() {
        if (mapp == null) {
            mapp = new DozerBeanMapper();
        }
        return mapp;
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return getMapper().map(source, destinationClass);
    }

    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<T>();
        for (Object sourceObject : sourceList) {
            T destinationObject = getMapper().map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }
}
