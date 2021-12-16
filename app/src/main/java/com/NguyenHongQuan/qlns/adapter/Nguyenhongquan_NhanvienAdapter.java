package com.NguyenHongQuan.qlns.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.NguyenHongQuan.qlns.R;
import com.NguyenHongQuan.qlns.activity.Nguyenhongquan_ActivityNhanvienbp;
import com.NguyenHongQuan.qlns.activity.Nguyenhongquan_ActivityThongtinnv;
import com.NguyenHongQuan.qlns.dao.Nguyenhongquan_NhanvienDao;
import com.NguyenHongQuan.qlns.model.Nguyenhongquan_Nhanvien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class Nguyenhongquan_NhanvienAdapter extends BaseAdapter {
    private Context context;
    private List<Nguyenhongquan_Nhanvien> NVList;
    private ArrayList<Nguyenhongquan_Nhanvien> arl = new ArrayList<>();


    public Nguyenhongquan_NhanvienAdapter(Context context, List<Nguyenhongquan_Nhanvien> SVList) {
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_nhanvien, null);
            // ánh xạ
            viewHolder.tvMaNhanVien = convertView.findViewById(R.id.tvMaNhanVien);
            viewHolder.tvTenNhanVienItem = convertView.findViewById(R.id.tvTenNhanVienItem);
            viewHolder.tvChucVuNhanVienItem = convertView.findViewById(R.id.tvChucVuNhanVienItem);
            viewHolder.imgAnhNhanVienItem = convertView.findViewById(R.id.imgAnhNhanVienItem);
            viewHolder.imbXem = convertView.findViewById(R.id.imbXemNhanVien);
            viewHolder.imbXoa = convertView.findViewById(R.id.imbXoa);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Nguyenhongquan_Nhanvien nv = NVList.get(position);
        viewHolder.tvMaNhanVien.setText("Mã nhân viên : " + nv.getMaNhanVien());
        viewHolder.tvTenNhanVienItem.setText("Tên nhân viên: " + nv.getTenNhanVien());
        viewHolder.tvChucVuNhanVienItem.setText("Chức vụ : " + nv.getChucVu());

        Bitmap bitmap = BitmapFactory.decodeByteArray(nv.getAnh(), 0, nv.getAnh().length);
        viewHolder.imgAnhNhanVienItem.setImageBitmap(bitmap);
        String id = nv.getMaNhanVien();
        viewHolder.imbXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(context, Nguyenhongquan_ActivityThongtinnv.class);
                inte.putExtra("maNhanVien", id);
                context.startActivity(inte);
            }
        });
        viewHolder.imbXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_xoanhanvien);
                dialog.setCanceledOnTouchOutside(false);
                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);
                dialog.show();
                Nguyenhongquan_NhanvienDao nvdao = new Nguyenhongquan_NhanvienDao(context);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nvdao.XoaNhanVien(id);
                        notifyDataSetChanged();
                        dialog.cancel();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    class  ViewHolder{
        TextView tvMaNhanVien,tvTenNhanVienItem,tvChucVuNhanVienItem;
        CircleImageView imgAnhNhanVienItem;
        ImageButton imbXem,imbXoa;
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

