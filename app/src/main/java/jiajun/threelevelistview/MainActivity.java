package jiajun.threelevelistview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import jiajun.threelevelistview.Bean.FrameResponse;
import jiajun.threelevelistview.Bean.ParkForFrame;
import jiajun.threelevelistview.adapter.ParentLevelAdapter;
import jiajun.threelevelistview.layout.BaseFooterView;
import jiajun.threelevelistview.layout.BaseHeaderView;
import jiajun.threelevelistview.util.JSONUtil;
import jiajun.threelevelistview.util.SupplyUtils;

public class MainActivity extends AppCompatActivity implements ParentLevelAdapter.FinishSupplyCallBack, BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {
    private ExpandableListView mExpandableListView;
    private TextView createTv;
    private TextView tipTv;
    private BaseHeaderView mRefreshView;
    private List<ParkForFrame> parks;
    private BaseFooterView mLoadMoreView;
    private ViewHandler mHandler =new ViewHandler(MainActivity.this);
    private ParentLevelAdapter mParentLevelAdapter;
    private List<ParkForFrame> datas = new ArrayList<>();
    private static final int REFRESH =1;
    private static final int LOADMORE =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

         mExpandableListView = (ExpandableListView) findViewById(R.id.supply_expandableListView_Parent);
        createTv = (TextView) findViewById(R.id.supply_create_tv);
        tipTv = (TextView) findViewById(R.id.supply_tip);
        initListener();

        if (mExpandableListView != null) {
            mParentLevelAdapter = new ParentLevelAdapter(MainActivity.this,
                    createTv,this);
            datas.addAll(getData());
            mParentLevelAdapter.setData(datas);

            mExpandableListView.setAdapter(mParentLevelAdapter);
            for (int i=0;i<datas.size();i++){
                mExpandableListView.expandGroup(i);
            }
        }
    }

    private void initListener() {
        mRefreshView = (BaseHeaderView) findViewById(R.id.expand_header_view);
        mLoadMoreView = (BaseFooterView) findViewById(R.id.expand_footer_view);
        if(null!= mRefreshView) {
        mRefreshView.setOnRefreshListener(this);
        }
        if(null!=mLoadMoreView) {
            mLoadMoreView.setOnLoadListener(this);
        }
    }

    private List<ParkForFrame> getData() {
        List<ParkForFrame> parkForFrames = new ArrayList<>();
        FrameResponse parks = JSONUtil.getObject(getJson("parks"), FrameResponse.class);
        if(null!=parks) {
            parkForFrames = SupplyUtils.getParkFrame(SupplyUtils.getSuitableFrame
                    (SupplyUtils.divideFrame(parks.getLampFrameList())));

        }
        this.parks=parkForFrames;
            return this.parks.subList(0,10);

    }

    private List<ParkForFrame> getData(int start,int end){
        return parks.subList(start,end);
    }

    /**
     * 读取本地文件中JSON字符串
     *
     * @param fileName
     * @return
     *
     * */
    private String getJson(String fileName) {

                 StringBuilder stringBuilder = new StringBuilder();
            try {
                   BufferedReader bf = new BufferedReader(new InputStreamReader(
                                 getAssets().open(fileName)));
                     String line;
                    while ((line = bf.readLine()) != null) {
                          stringBuilder.append(line);
                        }
                    } catch (IOException e) {
                     e.printStackTrace();
              }
                 return stringBuilder.toString();
        }

    @Override
    public void setView(boolean isHide, String msg) {
        if(isHide) {
            tipTv.setVisibility(View.GONE);
        }else {
            tipTv.setVisibility(View.VISIBLE);
            tipTv.setText(msg);
        }
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.refreshView();
            }
        },2000);
    }

    private void refreshView() {
        mRefreshView.stopRefresh();
        datas= getData(0, 10);
        mParentLevelAdapter.setData(datas);
        mParentLevelAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = mHandler.obtainMessage();
                message.what=MainActivity.LOADMORE;
                mHandler.sendMessage(message);
            }
        },2000);
    }

    private void loadMoreView() {
        mLoadMoreView.stopLoad();
        List<ParkForFrame> data = getData(10, 13);
        datas.add(data.get(0));
        mParentLevelAdapter.setData(datas);
        mParentLevelAdapter.notifyDataSetChanged();
    }


    static class ViewHandler extends Handler {


        WeakReference<MainActivity> mWeakReference;

        ViewHandler(MainActivity layout) {
            mWeakReference = new WeakReference<MainActivity>(layout);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  MainActivity.REFRESH:
                mWeakReference.get().refreshView();
                    break;
                case MainActivity.LOADMORE:
                    mWeakReference.get().loadMoreView();
                    break;
            }
        }
    }
}
