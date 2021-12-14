package com.iamquan.qlnhansu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iamquan.qlnhansu.R;
import com.iamquan.qlnhansu.dao.BoPhanDao;
import com.iamquan.qlnhansu.model.BoPhan;

import java.util.List;

public class BoPhanAdapter extends BaseAdapter {
    private Context context;
    private List<BoPhan> BPlist;
    private BoPhanDao boPhanDao;

    public BoPhanAdapter(Context context, List<BoPhan> BPlist) {
        this.context = context;
        this.BPlist = BPlist;
    }

    @Override
    public int getCount() {
        return BPlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_bophan, null);
            // ánh xạ
            viewHolder.tvTenBoPhan = convertView.findViewById(R.id.tvTenBoPhan);
            viewHolder.tvGhiChu = convertView.findViewById(R.id.tvGhiChu);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BoPhan bp = BPlist.get(position);
        viewHolder.tvTenBoPhan.setText("Tên bộ phân: " +bp.getTenBoPhan());
        viewHolder.tvGhiChu.setText("Ghi chú: " + bp.getGhiChu());
        return convertView;
    }
    class  ViewHolder{
        TextView tvTenBoPhan,tvGhiChu;

    }
}
