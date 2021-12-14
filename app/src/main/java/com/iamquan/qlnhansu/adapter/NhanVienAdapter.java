package com.iamquan.qlnhansu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iamquan.qlnhansu.R;
import com.iamquan.qlnhansu.model.NhanVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class NhanVienAdapter extends BaseAdapter {
    private Context context;
    private List<NhanVien> NVList;
    private ArrayList<NhanVien> arl = new ArrayList<>();


    public NhanVienAdapter(Context context, List<NhanVien> SVList) {
        this.context = context;
        this.NVList = SVList;
        this.arl.addAll(SVList);
    }

    @Override
    public int getCount() {
        return NVList.size();
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
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_nhanvien,null);
            // ánh xạ
            viewHolder.tvMaNhanVien=convertView.findViewById(R.id.tvMaNhanVien);
            viewHolder.tvTenNhanVienItem=convertView.findViewById(R.id.tvTenNhanVienItem);
            viewHolder.tvChucVuNhanVienItem=convertView.findViewById(R.id.tvChucVuNhanVienItem);
            viewHolder.imgAnhNhanVienItem=convertView.findViewById(R.id.imgAnhNhanVienItem);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        NhanVien nv=NVList.get(position);
        viewHolder.tvMaNhanVien.setText("Mã nhân viên : "+nv.getMaNhanVien());
        viewHolder.tvTenNhanVienItem.setText("Tên nhân viên: "+ nv.getTenNhanVien());
        viewHolder.tvChucVuNhanVienItem.setText("Chức vụ : "+ nv.getChucVu());
        Bitmap bitmap = BitmapFactory.decodeByteArray(nv.getAnh(), 0, nv.getAnh().length);
        viewHolder.imgAnhNhanVienItem.setImageBitmap(bitmap);
        return convertView;
    }
    class  ViewHolder{
        TextView tvMaNhanVien,tvTenNhanVienItem,tvChucVuNhanVienItem;
        CircleImageView imgAnhNhanVienItem;
    }
    public void filter(String text) {
        String textSearch = text.toLowerCase(Locale.getDefault());
        NVList.clear();
        if (textSearch.length() == 0) {
            NVList.clear();
            NVList.addAll(arl);
        } else {
            for (int i = 0; i < arl.size(); i++) {
                if (arl.get(i).getTenNhanVien().toLowerCase(Locale.getDefault()).contains(textSearch)) {
                    NVList.add(arl.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}

