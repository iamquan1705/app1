package com.NguyenHongQuan.qlns.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.NguyenHongQuan.qlns.R;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_BophanDao;
import com.NguyenHongQuan.qlns.model.Nguyenhongquan_Bophan;

import java.util.List;

public class Nguyenhongquan_BophanAdapter extends BaseAdapter {
    private Context context;
    private List<Nguyenhongquan_Bophan> BPlist;
    private Nguyenhongquan_BophanDao boPhanDao;

    public Nguyenhongquan_BophanAdapter(Context context, List<Nguyenhongquan_Bophan> BPlist) {
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

        Nguyenhongquan_Bophan bp = BPlist.get(position);
        viewHolder.tvTenBoPhan.setText("Tên bộ phân: " +bp.getTenBoPhan());
        viewHolder.tvGhiChu.setText("Ghi chú: " + bp.getGhiChu());
        return convertView;
    }
    class  ViewHolder{
        TextView tvTenBoPhan,tvGhiChu;

    }
}
