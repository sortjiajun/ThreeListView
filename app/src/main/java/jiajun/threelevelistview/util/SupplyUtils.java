package jiajun.threelevelistview.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jiajun.threelevelistview.Bean.LampFrame;
import jiajun.threelevelistview.Bean.ParkForFrame;


/**
 * @author 叶家俊
 * @time 2016/7/30  14:34
 * @desc ${TODD}
 */
public class SupplyUtils {
    public static List<ParkForFrame> getParkFrame(List<LampFrame> frames) {
        return getParkFrame(frames, true);
    }

    public static List<ParkForFrame> getParkFrame(List<LampFrame> frames, boolean hasList) {

        ArrayList<LampFrame> errorFrame = new ArrayList<>();
        if(hasList) {
            //剔除没有订单详情的frame
            for (int i = 0; i < frames.size(); i++) {
                if (!(frames.get(i).getList().size() > 0)) {
                    errorFrame.add(frames.get(i));

                }
            }
            if (errorFrame.size() > 0) {
                frames.removeAll(errorFrame);
                errorFrame = null;
            }
        }


        //将框按物流园分的容器
        ArrayList<ParkForFrame> parkForFrames = new ArrayList<>();
        HashSet<String> groups = new HashSet<String>();
        //分物流园
        for (int i = 0; i < frames.size(); i++) {
            groups.add(frames.get(i).getLogisticspark());
        }
        Log.v("家俊", "物流园个数-->" + groups.size());
        for (String parkName : groups) {
            ParkForFrame park = new ParkForFrame();
            park.setPublishInstitutionsName(frames.get(0).getPublishInstitutionsName());
            park.setLogisticspark(parkName);
            parkForFrames.add(park);
        }
        for (ParkForFrame park : parkForFrames) {

            for (LampFrame frame : frames) {
                if (park.getLogisticspark().equals(frame.getLogisticspark())) {
                    park.setLatitudePark(frame.getLatitudePark());
                    park.setLongitudePark(frame.getLongitudePark());
                    park.setLatitudeSend(frame.getLatitudeSend());
                    park.setLongitudeSend(frame.getLongitudeSend());
                    break;
                }
            }
        }
        //        Log.v("家俊","物流园个数-->"+parkForFrames.size());

        for (int j = 0; j < parkForFrames.size(); j++) {
            ArrayList<LampFrame> frames1 = new ArrayList<>();
            for (int i = 0; i < frames.size(); i++) {
                if (frames.get(i).getLogisticspark().equals(parkForFrames.get(j).getLogisticspark
                        ())) {
                    frames1.add(frames.get(i));
                }
            }
            parkForFrames.get(j).setFrames(frames1);
        }

        return parkForFrames;
    }

    public static List<LampFrame> getSingleFrames(List<LampFrame> frames) {
        ArrayList<LampFrame> singleframes = new ArrayList<>();
        HashSet<String> groups = new HashSet<>();
        //筛选货源
        List<LampFrame> frameList = SupplyUtils.divideFrame(frames);
        //分发货地
        for (int i = 0; i < frameList.size(); i++) {
            groups.add(frameList.get(i).getLocationSend());
        }

        for (String sendName : groups) {
            for (LampFrame frame : frameList) {
                if (sendName.equals(frame.getLocationSend())) {
                    singleframes.add(frame);
                    break;
                }
            }
        }
        return singleframes;
    }

    public static List<LampFrame> divideFrame(List<LampFrame> frames) {

        List<LampFrame> errorFrames = new ArrayList<>();
        for (int i = 0; i < frames.size(); i++) {

            if (StringUtils.isEmpty(frames.get(i).getLogisticspark())) {
                errorFrames.add(frames.get(i));
                continue;
            }

            if (StringUtils.isEmpty(frames.get(i).getLongitudePark())) {
                errorFrames.add(frames.get(i));
                continue;
            }
            if (StringUtils.isEmpty(frames.get(i).getLatitudePark())) {
                errorFrames.add(frames.get(i));
                continue;
            }
        }
        frames.removeAll(errorFrames);

        return frames;
    }

    /**
     * 获取合适司机车容量的框
     */
    public static List<LampFrame> getSuitableFrame(List<LampFrame> frames) {
        List<LampFrame> errorFrames = new ArrayList<>();
        for (int i = 0; i < frames.size(); i++) {
            if (StringUtils.isEmpty(frames.get(i).getSupplyframe())) {
                errorFrames.add(frames.get(i));
                continue;
            }

            if (!StringUtils.isInteger(frames.get(i).getSupplyframe())) {
                errorFrames.add(frames.get(i));

            }
        }
        frames.removeAll(errorFrames);
        return frames;
    }
}
