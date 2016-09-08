package jiajun.threelevelistview.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jiajun.threelevelistview.Bean.LampFrame;
import jiajun.threelevelistview.Bean.ParkForFrame;
import jiajun.threelevelistview.R;
import jiajun.threelevelistview.count.SureState;
import jiajun.threelevelistview.dialog.AlertDialog;
import jiajun.threelevelistview.dialog.DialogUtils;
import jiajun.threelevelistview.util.SupplyUtils;
import jiajun.threelevelistview.view.CustomExpListView;


public class ParentLevelAdapter extends BaseExpandableListAdapter implements SecondLevelAdapter.ChildExListViewLisenter {
    private  Context context;
    private List<ParkForFrame> parks;
    private LayoutInflater inflater;
    private TextView createTv;


    private Index mGroupIndex;
    private Index mChildIndex;
    private FinishSupplyCallBack finishCallBack;


    public ParentLevelAdapter(final Context context, TextView createTv,FinishSupplyCallBack
            finishCallBack) {
        super();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.createTv = createTv;
        this.finishCallBack = finishCallBack;
        initListener();
    }

    private void initListener() {
        if (null != createTv) {
            createTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("家俊", "创建订单");
                    ArrayList<LampFrame> lockFrames = getModelLockFrames("2");
                    if (lockFrames.size() > 0) {
                        Log.v("家俊", "refreshData--LockFrames.size()-->" + lockFrames.size());
                        for (int i = 0; i < lockFrames.size(); i++) {
                            modelLockFrameInpark(lockFrames.get(i), "3");
                        }
                        ArrayList<LampFrame> uploadFrames = getModelLockFrames("3");
                        Log.v("家俊", "modelLockFrames" + uploadFrames.size());
                        List<ParkForFrame> parks = SupplyUtils.getParkFrame(uploadFrames);
                        Log.v("家俊", "parks.size()" + parks.size());
                        ParentLevelAdapter.this.setData(parks);
                        createTv.setVisibility(View.GONE);
                        finishCallBack.setView(false, context.getString(R.string.supply_unload_tip));
                        ParentLevelAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        }
    }
    public void setData(List<ParkForFrame> parks) {
        if (!(parks.size() > 0)) {
//            finishCallBack.finish();
        }
        this.parks = parks;
//        initSurplusVehicleFrame();
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {

            CustomExpListView secondLevelExpListView = new CustomExpListView(this.context);
            SecondLevelAdapter secondLevelAdapter = new SecondLevelAdapter( context,
                    secondLevelExpListView, ParentLevelAdapter.this);
            secondLevelExpListView.setGroupIndicator(null);
            secondLevelAdapter.setData(parks.get(groupPosition));
            secondLevelAdapter.setParentPosition(groupPosition);
            secondLevelExpListView.setAdapter(secondLevelAdapter);
            convertView=secondLevelExpListView;
            convertView.setTag(secondLevelAdapter);
        } else {
            SecondLevelAdapter secondLevelAdapter  = (SecondLevelAdapter) convertView.getTag();
            secondLevelAdapter.setData(parks.get(groupPosition));
            secondLevelAdapter.setParentPosition(groupPosition);
            secondLevelAdapter.notifyDataSetChanged();
        }


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.parks.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return parks.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParkForFrame frame = parks.get(groupPosition);
        ParkViewHolder parkViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.supply_expandable, parent, false);
            parkViewHolder = new ParkViewHolder(convertView);
            convertView.setTag(parkViewHolder);
        }else {
            parkViewHolder= (ParkViewHolder) convertView.getTag();
        }

        parkViewHolder.sendPlTv.setText(String.format(context.getString(R.string.send_organization),
                frame.getPublishInstitutionsName()));
        parkViewHolder.parkTv.setText(frame.getLogisticspark());


        parkViewHolder.navigationIv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("家俊", "向发发货地导航-->" + parks.get(groupPosition).getLatitudeSend() + "," + parks
                        .get(groupPosition).getLongitudeSend());
//
//                ArrayList<NaviLatLng> start = new ArrayList<>();
//                start.add(new NaviLatLng(LocalDriverApplication.location.latitude,
//                        LocalDriverApplication.location.longitude));
//
//                ArrayList<NaviLatLng> end = new ArrayList<>();
//                end.add(new NaviLatLng(Double.parseDouble(parks.get(position).getLatitudeSend()),
//                        Double.parseDouble(parks.get(position).getLongitudeSend())));
//                finishCallBack.startNavi(start, end);

            }
        });
        parkViewHolder.navigationIv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("家俊", "向目的地导航" + parks.get(groupPosition).getLatitudePark() + "," + parks.get
                        (groupPosition).getLongitudePark());
//
//                ArrayList<NaviLatLng> start = new ArrayList<>();
//                start.add(new NaviLatLng(LocalDriverApplication.location.latitude,
//                        LocalDriverApplication.location.longitude));
//
//                ArrayList<NaviLatLng> end = new ArrayList<>();
//                end.add(new NaviLatLng(Double.parseDouble(parks.get(position).getLatitudePark()),
//                        Double.parseDouble(parks.get(position).getLongitudePark())));
//                finishCallBack.startNavi(start, end);

            }
        });


        return convertView;
    }

    @Override
    public void GroupClickListener(View view, int recyclerPosition, int groupPosition, int
            groupState) {
        if (null != mGroupIndex) {
            mGroupIndex = null;
        }
        mGroupIndex = new Index();
        mGroupIndex.recyclerPosition = recyclerPosition;
        mGroupIndex.groupPosition = groupPosition;
        mGroupIndex.state = groupState;
        if (groupState == SureState.GROUP_STATE_SURE) {
            DialogUtils.showIOSAlertDialog((Activity) context, "温馨提示", context.getString(R.string
                    .lock_tip), "确认选定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            LampFrame lampFrame = parks.get(mGroupIndex.recyclerPosition).getFrames().get
                    (mGroupIndex.groupPosition);
                    Log.v("家俊", "GroupClickListener-->锁定框");
                    parks.get(mGroupIndex.recyclerPosition).getFrames().get
                            (mGroupIndex.groupPosition).setArrangeState("2");
                    modelLockFrameInpark(lampFrame, "2");
                    ParentLevelAdapter.this.setData(parks);
                    ParentLevelAdapter.this.notifyDataSetChanged();
                }

            }, "返回", new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }
            });

        }

        if (groupState == SureState.GROUP_STATE_CANCEL) {

            DialogUtils.showIOSAlertDialog((Activity) context, "温馨提示", context.getString(R.string
                    .lock_tip), "确认取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("家俊", "GroupClickListener-->取消框");
                    LampFrame lampFrame = parks.get(mGroupIndex.recyclerPosition).getFrames().get
                            (mGroupIndex.groupPosition);
                   parks.get(mGroupIndex.recyclerPosition).getFrames().get
                            (mGroupIndex.groupPosition).setArrangeState("0");
                    modelLockFrameInpark(lampFrame, "0");
                    ParentLevelAdapter.this.setData(parks);
                    ParentLevelAdapter.this.notifyDataSetChanged();

                }

            }, "返回", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if (groupState == SureState.GROUP_STATE_UNLOAD) {

            DialogUtils.showIOSAlertDialog((Activity) context, "温馨提示", context.getString(R.string
                    .unload_tip), "确认卸货", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LampFrame remove = parks.get(mGroupIndex.recyclerPosition).getFrames().remove
                            (mGroupIndex.groupPosition);
                    if (!(parks.get(mGroupIndex.recyclerPosition).getFrames().size() > 0)) {
                        parks.remove(mGroupIndex.recyclerPosition);
                    }
                    if (!(parks.size() > 0)) {
                        new AlertDialog(context).builder().setTitle("任务完成").setMsg(context
                                .getString(R.string.finish_unload_tip)).setNegativeButton
                                ("结束本次任务", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        parks=new ArrayList<ParkForFrame>();
                                        ParentLevelAdapter.this.setData(parks);
                                        ParentLevelAdapter.this.notifyDataSetChanged();
                                    }
                                }).show();
                    } else {
                        ParentLevelAdapter.this.notifyDataSetChanged();
                    }
                }

            }, "返回", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }

    }

    @Override
    public void childClickListener(View view, int recyclerPosition, int groupPosition, int
            childPosition, int deleteState) {
        if (SecondLevelAdapter.DELETE_SUPPLY == deleteState || SecondLevelAdapter
                .DELETE_SUPPLY_AND_FRAME == deleteState) {
            parks.get(recyclerPosition).getFrames().get(groupPosition).getList().remove(childPosition);
            ParentLevelAdapter.this.notifyDataSetChanged();
            if (SecondLevelAdapter.DELETE_SUPPLY_AND_FRAME ==deleteState) {
                LampFrame frame = parks.get(recyclerPosition).getFrames().get
                        (groupPosition);
                parks.get(recyclerPosition).getFrames().remove(frame);
                ParentLevelAdapter.this.notifyDataSetChanged();
            }
        }
    }


    public static class ParkViewHolder {
        private TextView sendPlTv;
        private TextView parkTv;
        private ImageView navigationIv_1;

        private ImageView navigationIv_2;

        public ParkViewHolder(View view) {
            sendPlTv = (TextView) view.findViewById(R.id.expandable_sendPl);
            parkTv = (TextView) view.findViewById(R.id.expandable_park);
            navigationIv_1 = (ImageView) view.findViewById(R.id.expandable_navigation_1);
            navigationIv_2 = (ImageView) view.findViewById(R.id.expandable_navigation_2);

            view.findViewById(R.id.expandable_navigation_1);

        }
    }

    public static class ChildViewHolder {




    }
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



    /**
     * 对框做操作是设置对应的框状态(0-待接单,1-完成订单,2-锁框,待卸货)
     *
     * @param lampFrame 需要操作的框
     */
    private void modelLockFrameInpark(LampFrame lampFrame, String state) {
        boolean islock = false;
        for (int i = 0; i < parks.size(); i++) {
            for (int j = 0; j < parks.get(i).getFrames().size(); j++) {
                if (lampFrame.getId().equals(parks.get(i).getFrames().get(j).getId())) {
                    parks.get(i).getFrames().get(j).setArrangeState(state);
                    parks.get(i).getFrames().get(j).setVersions(lampFrame.getVersions() + 1);
                }
                if ("2".equals(parks.get(i).getFrames().get(j).getArrangeState())) {
                    islock = true;
                }
            }
        }
        if (islock) {
            createTv.setVisibility(View.VISIBLE);
        } else {
            createTv.setVisibility(View.INVISIBLE);
            finishCallBack.setView(false,context.getString(R.string.supply_tip));

        }
    }

    /**
     * 所有操作完成后，隐藏当前界面
     */
    public interface FinishSupplyCallBack {

        void finish();

        void setView(boolean isHide, String msg);

//        void startNavi(ArrayList<NaviLatLng> start, ArrayList<NaviLatLng> end);
    }
    /**
     * 获取指定状态的框
     *
     * @param state
     * @return
     */
    private ArrayList<LampFrame> getModelLockFrames(String state) {

        ArrayList<LampFrame> lockFrames = new ArrayList<>();
        for (ParkForFrame park : this.parks) {
            for (LampFrame lampFrame : park.getFrames()) {
                if (state.equals(lampFrame.getArrangeState())) {
                    lockFrames.add(lampFrame);
                }
            }
        }

        return lockFrames;
    }

    /**
     * 保存二级三级列表传递的position
     */
    private static class Index {
        public int recyclerPosition;
        public int groupPosition;
        public int childPosition;
        public int state;
    }
}
