package jiajun.threelevelistview.Bean;

import java.util.List;

/**
 * @author 叶家俊
 * @time 2016/7/20  9:11
 * @desc ${TODD}
 * 物流园包含框的bean
 */
public class ParkForFrame {

    private String publishInstitutionsName;//发货地
    private String logisticspark;//物流园
    private String longitudeSend;
    private String latitudeSend;
    private String latitudePark;
    private String longitudePark;
    private int index;
    List<LampFrame> frames;
    public String getLogisticspark() {
        return logisticspark;
    }

    public void setLogisticspark(String logisticspark) {
        this.logisticspark = logisticspark;
    }

    public List<LampFrame> getFrames() {
        return frames;
    }

    public void setFrames(List<LampFrame> frames) {
        this.frames = frames;
    }



    public String getLongitudeSend() {
        return longitudeSend;
    }

    public void setLongitudeSend(String longitudeSend) {
        this.longitudeSend = longitudeSend;
    }

    public String getLatitudeSend() {
        return latitudeSend;
    }

    public void setLatitudeSend(String latitudeSend) {
        this.latitudeSend = latitudeSend;
    }

    public String getLatitudePark() {
        return latitudePark;
    }

    public void setLatitudePark(String latitudePark) {
        this.latitudePark = latitudePark;
    }

    public String getLongitudePark() {
        return longitudePark;
    }

    public void setLongitudePark(String longitudePark) {
        this.longitudePark = longitudePark;
    }

    public String getPublishInstitutionsName() {
        return publishInstitutionsName;
    }

    public void setPublishInstitutionsName(String publishInstitutionsName) {
        this.publishInstitutionsName = publishInstitutionsName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "ParkForFrame{" +
                ", logisticspark='" + logisticspark + '\'' +
                ", longitudeSend='" + longitudeSend + '\'' +
                ", latitudeSend='" + latitudeSend + '\'' +
                ", latitudePark='" + latitudePark + '\'' +
                ", longitudePark='" + longitudePark + '\'' +
                ", frames=" + frames +
                '}';
    }
}
