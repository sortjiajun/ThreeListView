package jiajun.threelevelistview.count;

import java.util.List;

/**
 * @author 叶家俊
 * @time 2016/7/29  18:29
 * @desc ${TODD}
 */
public class SureState {


    public static final int GROUP_STATE_SURE = 1;
    public static final int GROUP_STATE_CANCEL = 2;
    public static final int GROUP_STATE_UNLOAD = 3;
    public List<Integer> sureState;

    public List<Integer> getSureState() {
        return sureState;
    }

    public void setSureState(List<Integer> sureState) {
        this.sureState = sureState;
    }
}
