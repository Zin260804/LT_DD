    package com.example.tuan5_ltdd.adapter;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.example.tuan5_ltdd.R;
    import com.example.tuan5_ltdd.model.MonHoc;

    import java.util.List;

    public class MonhocAdapter extends BaseAdapter {

        private Context context;
        private int layout;
        private List<MonHoc> monHocList;
        @Override
        public int getCount() {
            return monHocList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        private class ViewHolder{
            TextView textName,textDesc;
            ImageView imagePic;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view==null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //gọi view chứa layout
                view = inflater.inflate(layout,null);
                //ánh xạ view
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView) view.findViewById(R.id.textName);
                viewHolder.textDesc = (TextView) view.findViewById(R.id.textDesc);
                viewHolder.imagePic = (ImageView) view.findViewById(R.id.imagePic);
                view.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            MonHoc monHoc = monHocList.get(i);
            viewHolder.textName.setText(monHoc.getName());
            viewHolder.textDesc.setText(monHoc.getDesc());
            viewHolder.imagePic.setImageResource(monHoc.getPic());
            //trả về view
            return view;
        }
        public MonhocAdapter(Context context, int
                layout, List<MonHoc> monHocList) {
            this.context = context;
            this.layout = layout;
            this.monHocList = monHocList;
        }
    }
