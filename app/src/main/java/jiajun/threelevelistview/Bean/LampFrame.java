package jiajun.threelevelistview.Bean;

import java.util.List;

/**
 * @author 叶家俊
 * @time 2016/7/18  10:30
 * @desc ${TODD}
 */
public class LampFrame {
    private String arrangeState;
    private String consignerTel;
    private String errorCode;
    private String errorMsg;
    private long gmtCreate;
    private long gmtModify;
    private String goodsOwner;
    private String id;
    private String latitudePark;
    private String latitudeSend;
    private String locationSend;
    private String logisticspark;
    private String longitudePark;
    private String longitudeSend;
    private int pageNum;
    private int pageSize;
    private String publishInstitutionsCode;
    private String publishInstitutionsName;
    private String receiptInstitutionsName;
    private String remark;
    private int softDeleteIdentity;
    private boolean success;
    private String supplyName;
    private String supplyframe;
    private int versions;
    private List<SupplyDeal> list;
    /**
     *区分不同的物流园
     */
    private int parkType;
    public String getArrangeState() {
        return arrangeState;
    }

    public void setArrangeState(String arrageState) {
        this.arrangeState = arrageState;
    }

    public String getConsignerTel() {
        return consignerTel;
    }

    public void setConsignerTel(String consignerTel) {
        this.consignerTel = consignerTel;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(long gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getGoodsOwner() {
        return goodsOwner;
    }

    public void setGoodsOwner(String goodsOwner) {
        this.goodsOwner = goodsOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitudePark() {
        return latitudePark;
    }

    public void setLatitudePark(String latitudePark) {
        this.latitudePark = latitudePark;
    }

    public String getLatitudeSend() {
        return latitudeSend;
    }

    public void setLatitudeSend(String latitudeSend) {
        this.latitudeSend = latitudeSend;
    }

    public String getLocationSend() {
        return locationSend;
    }

    public void setLocationSend(String locationSend) {
        this.locationSend = locationSend;
    }

    public String getLogisticspark() {
        return logisticspark;
    }

    public void setLogisticspark(String logisticspark) {
        this.logisticspark = logisticspark;
    }

    public String getLongitudePark() {
        return longitudePark;
    }

    public void setLongitudePark(String longitudePark) {
        this.longitudePark = longitudePark;
    }

    public String getLongitudeSend() {
        return longitudeSend;
    }

    public void setLongitudeSend(String longitudeSend) {
        this.longitudeSend = longitudeSend;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPublishInstitutionsCode() {
        return publishInstitutionsCode;
    }

    public void setPublishInstitutionsCode(String publishInstitutionsCode) {
        this.publishInstitutionsCode = publishInstitutionsCode;
    }

    public String getPublishInstitutionsName() {
        return publishInstitutionsName;
    }

    public void setPublishInstitutionsName(String publishInstitutionsName) {
        this.publishInstitutionsName = publishInstitutionsName;
    }

    public String getReceiptInstitutionsName() {
        return receiptInstitutionsName;
    }

    public void setReceiptInstitutionsName(String receiptInstitutionsName) {
        this.receiptInstitutionsName = receiptInstitutionsName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSoftDeleteIdentity() {
        return softDeleteIdentity;
    }

    public void setSoftDeleteIdentity(int softDeleteIdentity) {
        this.softDeleteIdentity = softDeleteIdentity;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getSupplyframe() {
        return supplyframe;
    }

    public void setSupplyframe(String supplyframe) {
        this.supplyframe = supplyframe;
    }

    public int getVersions() {
        return versions;
    }

    public void setVersions(int versions) {
        this.versions = versions;
    }

    public List<SupplyDeal> getList() {
        return list;
    }

    public void setList(List<SupplyDeal> list) {
        this.list = list;
    }

    public int getParkType() {
        return parkType;
    }

    public void setParkType(int parkType) {
        this.parkType = parkType;
    }

    @Override
    public String toString() {
        return "LampFrame{" +
                "arrageState='" + arrangeState + '\'' +
                ", consignerTel='" + consignerTel + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", goodsOwner='" + goodsOwner + '\'' +
                ", id='" + id + '\'' +
                ", latitudePark='" + latitudePark + '\'' +
                ", latitudeSend='" + latitudeSend + '\'' +
                ", locationSend='" + locationSend + '\'' +
                ", logisticspark='" + logisticspark + '\'' +
                ", longitudePark='" + longitudePark + '\'' +
                ", longitudeSend='" + longitudeSend + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", publishInstitutionsCode='" + publishInstitutionsCode + '\'' +
                ", publishInstitutionsName='" + publishInstitutionsName + '\'' +
                ", receiptInstitutionsName='" + receiptInstitutionsName + '\'' +
                ", remark='" + remark + '\'' +
                ", softDeleteIdentity=" + softDeleteIdentity +
                ", success=" + success +
                ", supplyName='" + supplyName + '\'' +
                ", supplyframe='" + supplyframe + '\'' +
                ", versions=" + versions +
                ", list=" + list +
                '}';
    }
}
