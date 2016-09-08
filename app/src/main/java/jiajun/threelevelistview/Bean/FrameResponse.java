package jiajun.threelevelistview.Bean;

import java.util.List;


/**
 * @author 叶家俊
 * @time 2016/7/18  9:44
 * @desc ${TODD}
 */
public class FrameResponse extends BaseResponse {
    private int pageNum;
    private int pageSize;
    private int totalItems;
    private int totalPages;

    private List<LampFrame> lampFrameList;

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

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<LampFrame> getLampFrameList() {
        return lampFrameList;
    }

    public void setLampFrameList(List<LampFrame> lampFrameList) {
        this.lampFrameList = lampFrameList;
    }

    @Override
    public String toString() {
        return "FrameResponse{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalItems=" + totalItems +
                ", totalPages=" + totalPages +
                ", lampFrameList=" + lampFrameList +
                '}';
    }
}
