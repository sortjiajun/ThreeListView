package jiajun.threelevelistview.Bean;

import java.io.Serializable;

/**
 * 响应结果基类
 * @author xiaomo
 * @version $Id: BaseResponse.java, v 0.1 2015年2月20日 下午6:38:15 xiaomo Exp $
 */
public class BaseResponse implements Serializable {


    /** 错误编码，默认未知异常错误 */
    protected   String            errorCode        = "";

    /** 错误描述，默认未知异常错误 */
    protected   String            errorMsg         = "";


    /** 是否成功 */
    protected   boolean           success          = false;

    /** 图片路径 */
    private   String imageUrl;


    /**
     * Getter method for property <tt>errorCode</tt>.
     *
     * @return property value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     *
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>errorMsg</tt>.
     *
     * @return property value of errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Setter method for property <tt>errorMsg</tt>.
     *
     * @param errorMsg value to be assigned to property errorMsg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    /**
     * Getter method for property <tt>success</tt>.
     *
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", success=" + success +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
