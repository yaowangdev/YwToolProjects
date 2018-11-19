package com.appdev.common.lib.validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtils {

    /**
     * 列表校验2
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 列表校验2
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List list) {
        return list != null && list.size() > 0;
    }

    /**
     * 数组校验
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(T... ts) {
        return ts == null || ts.length == 0;
    }

    /**
     * 数组校验2
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> boolean isNotEmpty(T... ts) {
        return ts != null && ts.length > 0;
    }

    /**
     * 数组转列表
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> List<T> arrayToList(T... ts) {
        List<T> resultTs = new ArrayList<>();
        if (!isEmpty(ts)) {
            Collections.addAll(resultTs, ts);
        }
        return resultTs;
    }

    /**
     * 列表转数组
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T[] listToArray(List<T> list) {
        try {
            if (isEmpty(list)) {
                return null;
            }
            T[] array = (T[]) list.toArray(new Object[list.size()]);
            return array;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
