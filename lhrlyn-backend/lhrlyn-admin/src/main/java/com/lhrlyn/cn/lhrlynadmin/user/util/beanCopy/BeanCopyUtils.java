package com.lhrlyn.cn.lhrlynadmin.user.util.beanCopy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class BeanCopyUtils {
    private static final Logger log = LoggerFactory.getLogger(BeanCopyUtils.class);
    private static final int DEFAULT_SIZE = 300;
    private static final boolean DEFAULT_REMOVE_EMPTY = false;

    public BeanCopyUtils() {
    }

    public static <T> T beanCopy(Object obj, Class<T> clazz) {
        return beanCopy(obj, false, clazz, (Class)null);
    }

    public static <T> T beanCopy(Object obj, Class<T> clazz, Class genericsClass) {
        return beanCopy(obj, false, clazz, genericsClass);
    }

    public static <T> T beanCopy(Object obj, boolean removeEmpty, Class<T> clazz) {
        return beanCopy(obj, removeEmpty, clazz, (Class)null);
    }

    public static <T> T beanCopy(Object obj, boolean removeEmpty, Class<T> clazz, Class genericsClass) {
        if (obj == null) {
            try {
                return clazz.newInstance();
            } catch (Exception var7) {
                log.warn("{}生成实例失败", clazz.getName());
                return null;
            }
        } else if (clazz == null) {
            throw new RuntimeException("bean转换时请显式指定目标bean的类型");
        } else {
            try {
                clazz.getDeclaredConstructor();
            } catch (NoSuchMethodException var9) {
                throw new RuntimeException(clazz + "没有默认无参构造函数，请在类上使用@NoArgsConstructor添加无参构造函数");
            }

            JSONObject object = JSON.parseObject(JSON.toJSONString(obj));
            if (removeEmpty) {
                empty2Null(object);
            }

            Type[] types = null;

            try {
                if (genericsClass == null) {
                    return JSON.parseObject(JSON.toJSONString(object), clazz);
                } else {
                    types = new Type[]{genericsClass};
                    return JSON.parseObject(JSON.toJSONString(object), new ParameterizedTypeImpl(types, (Type)null, clazz), new Feature[0]);
                }
            } catch (Exception var8) {
                log.error("{}转换失败", clazz, var8);
                throw new RuntimeException(clazz + "转换失败，请检查是否与源数据存在参数名一样数据类型不一致的情况");
            }
        }
    }

    public static <T> List<List<T>> listSplitCopy(List list, Class<T> clazz) {
        return listSplitCopy(list, 300, false, clazz, (Class)null);
    }

    public static <T> List<List<T>> listSplitCopy(List list, Class<T> clazz, Class genericsClass) {
        return listSplitCopy(list, 300, false, clazz, genericsClass);
    }

    public static <T> List<List<T>> listSplitCopy(List list, boolean removeEmpty, Class<T> clazz) {
        return listSplitCopy(list, 300, removeEmpty, clazz, (Class)null);
    }

    public static <T> List<List<T>> listSplitCopy(List list, boolean removeEmpty, Class<T> clazz, Class genericsClass) {
        return listSplitCopy(list, 300, removeEmpty, clazz, genericsClass);
    }

    public static <T> List<List<T>> listSplitCopy(List list, int splitSize, Class<T> clazz) {
        return listSplitCopy(list, splitSize, false, clazz, (Class)null);
    }

    public static <T> List<List<T>> listSplitCopy(List list, int splitSize, Class<T> clazz, Class genericsClass) {
        return listSplitCopy(list, splitSize, false, clazz, genericsClass);
    }

    public static <T> List<List<T>> listSplitCopy(List list, int splitSize, boolean removeEmpty, Class<T> clazz) {
        return listSplitCopy(list, splitSize, removeEmpty, clazz, (Class)null);
    }

    public static <T> List<List<T>> listSplitCopy(List list, int splitSize, boolean removeEmpty, Class<T> clazz, Class genericsClass) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        } else {
            List<List<T>> targetList = new ArrayList();
            List<T> entityList = new ArrayList();
            int index = 1;

            for(Iterator var8 = list.iterator(); var8.hasNext(); ++index) {
                Object obj = var8.next();
                entityList.add(beanCopy(obj, removeEmpty, clazz, genericsClass));
                if (splitSize > 0 && index % splitSize == 0) {
                    targetList.add(entityList);
                    entityList = new ArrayList();
                }
            }

            if (splitSize > 0 && (index - 1) % splitSize != 0) {
                targetList.add(entityList);
            }

            return targetList;
        }
    }

    public static <T> List<T> listCopy(List list, Class<T> clazz) {
        return listCopy(list, false, clazz, (Class)null);
    }

    public static <T> List<T> listCopy(List list, Class<T> clazz, Class genericsClass) {
        return listCopy(list, false, clazz, genericsClass);
    }

    public static <T> List<T> listCopy(List list, boolean removeEmpty, Class<T> clazz) {
        return listCopy(list, removeEmpty, clazz, (Class)null);
    }

    public static <T> List<T> listCopy(List list, boolean removeEmpty, Class<T> clazz, Class genericsClass) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        } else {
            List<T> targetList = new ArrayList();
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                Object obj = var5.next();
                targetList.add(beanCopy(obj, removeEmpty, clazz, genericsClass));
            }

            return targetList;
        }
    }

    private static void empty2Null(Object obj) {
        Iterator var1;
        if (obj instanceof JSONObject) {
            var1 = ((JSONObject)obj).entrySet().iterator();

            while(true) {
                while(var1.hasNext()) {
                    Entry entry = (Entry)var1.next();
                    if (entry.getValue() instanceof String && StringUtils.isEmpty((String)entry.getValue())) {
                        entry.setValue((Object)null);
                    } else {
                        empty2Null(entry.getValue());
                    }
                }

                return;
            }
        } else if (obj instanceof JSONArray) {
            var1 = ((JSONArray)obj).iterator();

            while(var1.hasNext()) {
                Object object = var1.next();
                empty2Null(object);
            }
        }

    }
}
