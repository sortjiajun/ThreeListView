package jiajun.threelevelistview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Formatter;
import java.util.List;

import jiajun.threelevelistview.Bean.LampFrame;
import jiajun.threelevelistview.Bean.ParkForFrame;
import jiajun.threelevelistview.Bean.SupplyDeal;
import jiajun.threelevelistview.R;
import jiajun.threelevelistview.count.SureState;
import jiajun.threelevelistview.util.ListUtils;
import jiajun.threelevelistview.util.StringUtils;
import jiajun.threelevelistview.view.SwipeLayout;

public class SecondLevelAdapter extends BaseExpandableListAdapter {

    public ParkForFrame park;
    private Context mContext;
    private LayoutInflater inflater;
    private ExpandableListView mListView;

    private int parentPosition;



    public static final int DELETE_SUPPLY = 4;
    public static final int DELETE_SUPPLY_AND_FRAME = 5;

    private ChildExListViewLisenter mChildExListViewLisenter;

    public SecondLevelAdapter( Context context, ExpandableListView
            listView, ChildExListViewLisenter childExListViewLisenter) {

        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mListView = listView;
        this.mChildExListViewLisenter = childExListViewLisenter;
    }


    public void setData(ParkForFrame park) {
        this.park = park;

    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.park.getFrames().get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Log.v("家俊","getChildView--position-->"+groupPosition);
        final ChildViewHolder childViewHolder;
        LampFrame lampFrame = park.getFrames().get(groupPosition);
        SupplyDeal supplyDeal = park.getFrames().get(groupPosition).getList().get(childPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.supply_expand_child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }


        if ("2".equals(lampFrame.getArrangeState())) {
            childViewHolder.childSwipeLayout.setCanScroll(true);
        } else {
            childViewHolder.childSwipeLayout.setCanScroll(false);
        }


        childViewHolder.numTv.setText(String.valueOf(childPosition + 1));
        childViewHolder.sendToTv.setText(String.format(mContext.getString(R.string
                .supply_deal_sendTo), supplyDeal.getLocationReceipt()));
        childViewHolder.logisticsTv.setText(String.format(mContext.getString(R.string
                .supply_deal_logistics), supplyDeal.getServiceInstitutionsName()));
        childViewHolder.cubeTv.setText(String.format(mContext.getResources().getString(R.string
                .supply_deal_cube), getCube(supplyDeal.getSupplyCube())));
        childViewHolder.consigneeTv.setText(String.format(mContext.getResources().getString(R
                .string.supply_deal_consignee), supplyDeal.getConsignee()));
        childViewHolder.phoneTv.setText(String.format(mContext.getResources().getString(R.string
                .supply_deal_phone), supplyDeal.getConsignerTel()));
        childViewHolder.count_tv.setText(String.format(mContext.getResources().getString(R.string
                .supply_deal_count), String.valueOf(supplyDeal.getSupplyCount())));

        childViewHolder.deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<SupplyDeal> list = park.getFrames().get(groupPosition).getList();
                if (list.size() > 0 && list.size() == 1) {
                    mChildExListViewLisenter.childClickListener(view, getParentPosition(),
                            groupPosition, childPosition, DELETE_SUPPLY_AND_FRAME);
                } else if (list.size() > 0 && list.size() > 1) {
                    mChildExListViewLisenter.childClickListener(view, getParentPosition(),
                            groupPosition, childPosition, DELETE_SUPPLY);
                }
            }

        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (null != park.getFrames().get(groupPosition).getList()) {

            return park.getFrames().get(groupPosition).getList().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return park.getFrames().get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return park.getFrames().size();
}

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        LampFrame lampFrame = park.getFrames().get(groupPosition);

        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.supply_item_body,parent, false);
            groupViewHolder = new GroupViewHolder(mContext, mListView, convertView);
            convertView.setTag(groupViewHolder);

        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();

        }

            //设置按钮的状态
            if ("2".equals(lampFrame.getArrangeState())) {
                groupViewHolder.sureIv.setVisibility(View.VISIBLE);
                groupViewHolder.sureTv.setTag(SureState.GROUP_STATE_CANCEL);
                groupViewHolder.sureTv.setText("解除锁定");
                groupViewHolder.sureTv.setBackgroundColor(mContext.getResources().getColor(R
                        .color.red_1));
                groupViewHolder.sureTv.setTextColor(mContext.getResources().getColor(R.color
                        .white));
            } else if ("3".equals(lampFrame.getArrangeState())) {
                groupViewHolder.sureIv.setVisibility(View.VISIBLE);
                groupViewHolder.sureTv.setTag(SureState.GROUP_STATE_UNLOAD);
                groupViewHolder.sureTv.setText("完成卸货");
                groupViewHolder.sureTv.setBackgroundColor(mContext.getResources().getColor(R
                        .color.green_2));
                groupViewHolder.sureTv.setTextColor(mContext.getResources().getColor(R.color
                        .white));
            } else {
                groupViewHolder.sureIv.setVisibility(View.GONE);
                groupViewHolder.sureTv.setTag(SureState.GROUP_STATE_SURE);
                groupViewHolder.sureTv.setText("选定装车");
                groupViewHolder.sureTv.setBackgroundColor(mContext.getResources().getColor(R
                        .color.green_2));
                groupViewHolder.sureTv.setTextColor(mContext.getResources().getColor(R.color
                        .white));
            }


        groupViewHolder.numTv.setText(lampFrame.getSupplyframe());

        if(!(StringUtils.isEmpty(lampFrame.getConsignerTel()))) {

            groupViewHolder.consignerTelTv.setText(String.format(mContext.getString(R.string
                    .consigner_phone),lampFrame.getConsignerTel()));
        }else if(!(ListUtils.isEmptyOfList(lampFrame.getList()))) {
            groupViewHolder.consignerTelTv.setText(String.format(mContext.getString(R.string.consigner_phone),lampFrame.getList().get(0)
                    .getConsignerTel()));
        } else {
            groupViewHolder.consignerTelTv.setText(String.format(mContext.getString(R.string
                    .consigner_phone),""));
        }
        if(!(StringUtils.isEmpty(lampFrame.getId()))) {
            groupViewHolder.remarkTv.setText(String.format(mContext.getString(R.string
                    .frame_name),lampFrame.getId()));
        }else {
            groupViewHolder.remarkTv.setText(String.format(mContext.getString(R.string
                    .frame_name),""));

        }
        if(!(StringUtils.isEmpty(lampFrame.getGoodsOwner()))) {

            groupViewHolder.ownerTv.setText(String.format(mContext.getResources().getString(R.string
                    .owner), lampFrame.getGoodsOwner()));
        }else {
            groupViewHolder.ownerTv.setText(String.format(mContext.getResources().getString(R.string
                    .owner),"" ));
        }

        groupViewHolder.frontView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SecondLevelAdapter.this.expandList(groupPosition,mListView);
            }
        });




        // 为sureTv绑定事件，可以用此按钮来实现删除事件
        groupViewHolder.sureTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("家俊", "onClick--groupPosition-->" + groupPosition);
                int supplyframe = Integer.parseInt(park.getFrames().get(groupPosition)
                        .getSupplyframe());
                SwipeLayout.removeSwipeView(groupViewHolder.swipeLayout);
                int state = (int) view.getTag();

                switch (state) {
                    case SureState.GROUP_STATE_SURE:
//                        if (supplyframe > LocalDriverApplication.surplusVehicleFrame) {
//                            ToastUtils.show(context, "车容量不够");
//                            return;
//                        }
                        mChildExListViewLisenter.GroupClickListener(view, getParentPosition(),
                                groupPosition, SureState.GROUP_STATE_SURE);
                        break;
                    case SureState.GROUP_STATE_CANCEL:
                        mChildExListViewLisenter.GroupClickListener(view, getParentPosition(),
                                groupPosition, SureState.GROUP_STATE_CANCEL);
                        break;
                    case SureState.GROUP_STATE_UNLOAD:
                        mChildExListViewLisenter.GroupClickListener(view, getParentPosition(),
                                groupPosition, SureState.GROUP_STATE_UNLOAD);
                        break;
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static class GroupViewHolder {
        public View frontView;
        public TextView ownerTv;
        public TextView consignerTelTv;
        public TextView numTv;
        public TextView sureTv;
        public TextView remarkTv;
        public ImageView sureIv;
        public SwipeLayout swipeLayout;

        public GroupViewHolder(Context context, ExpandableListView exListView, View convertView) {
            remarkTv = (TextView) convertView.findViewById(R.id.frame_remark_tv);
            ownerTv = (TextView) convertView.findViewById(R.id.goodsOwner_tv);
            consignerTelTv = (TextView) convertView.findViewById(R.id.consigner_tel_tv);
            numTv = (TextView) convertView.findViewById(R.id.frame_num_tv);
            sureTv = (TextView) convertView.findViewById(R.id.frame_sure_tv);
            frontView = convertView.findViewById(R.id.frame_front_ll);
            sureIv = (ImageView) convertView.findViewById(R.id.frame_sure_iv);
             swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_layout);
            frontView.setTag(sureTv);

        }

    }



    public static class ChildViewHolder {
        public TextView numTv;
        public TextView sendToTv;
        public TextView logisticsTv;
        public TextView cubeTv;
        public TextView consigneeTv;
        public TextView phoneTv;
        public TextView count_tv;
        public TextView deleteTv;
        public View exFrontView;
        public SwipeLayout childSwipeLayout;

        public ChildViewHolder(View convertView) {
            numTv = (TextView) convertView.findViewById(R.id.supply_deal_num_tv);
            cubeTv = (TextView) convertView.findViewById(R.id.supply_deal_cube_tv);
            phoneTv = (TextView) convertView.findViewById(R.id.supply_deal_phone_tv);
            sendToTv = (TextView) convertView.findViewById(R.id.supply_deal_sendTo_tv);
            count_tv = (TextView) convertView.findViewById(R.id.supply_deal_count_tv);
            logisticsTv = (TextView) convertView.findViewById(R.id.supply_deal_logistics_tv);
            consigneeTv = (TextView) convertView.findViewById(R.id.supply_deal_consignee_tv);
            deleteTv = (TextView) convertView.findViewById(R.id.frame_child_delete_tv);
            exFrontView = convertView.findViewById(R.id.frame_ex_front_ll);
            childSwipeLayout= (SwipeLayout) convertView.findViewById(R.id.child_swipeLayout);
            exFrontView.setTag(deleteTv);
        }
    }


    public interface ChildExListViewLisenter {

        void GroupClickListener(View view, int recyclerPosition, int groupPosition, int groupState);

        void childClickListener(View view, int recyclerPosition, int groupPosition, int
                childPosition, int deleteState);
    }

    /**
     * 体积转换
     * @param sum 要转换的体积
     * @return 转换后的体积
     */
    public static String  getCube(double sum){
        double pow = Math.pow(10, 6);
        double result = sum / pow;

        return new Formatter().format("%.2f", result).toString();
    }


    private void expandList(int groupPosition, ExpandableListView expandLvl) {
        Log.v("家俊","expandList--position-->"+groupPosition);
        if (expandLvl.isGroupExpanded(groupPosition)) {
            expandLvl.collapseGroup(groupPosition);
            Log.v("家俊","收缩");
        } else {
            Log.v("家俊","展开");
            expandLvl.expandGroup(groupPosition);
        }
    }

    public int getParentPosition() {
        return parentPosition;
    }

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }


}
