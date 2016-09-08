package jiajun.threelevelistview.util;

import java.util.List;

/**
 * @author 叶家俊
 * @time 2016/8/22  15:15
 * @desc ${TODD}
 */
public class ListUtils {


    public static boolean isEmptyOfList(List list) {
        return null == list || !(list.size() > 0);
    }
}
