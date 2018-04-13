package com.qun.test.rxjava_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {
    @BindView(R.id.iv)
    public ImageView iv;
    @BindView(R.id.btn)
    public Button btn;
    @BindView(R.id.lv)
    public ListView lv;
    private Main2Adapter mAdapter;
    @BindView(R.id.btn2)
    public Button btn2;
    @BindView(R.id.btn3)
    public Button btn3;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        lv.setAdapter(mAdapter = new Main2Adapter());
//        mDisposable = RxView.clicks(btn2).subscribe(s -> startActivity(new Intent(Main2Activity.this, Main3Activity.class)));
        mDisposable = RxView.clicks(btn2).observeOn(Schedulers.io()).subscribe(s -> {
            HttpUtil httpUtil = new HttpUtil();
//            httpUtil.testHttpClientGet();
//            httpUtil.testHttpClientPost();
//            httpUtil.testHttpUrlConnectPost();
//            httpUtil.testHttpUrlConnectGet();
//            httpUtil.testVolleyGet();
//            httpUtil.testVolleyPost();
//            httpUtil.testVolleyJsonPost();
//            httpUtil.testVolleyJsonArrayPost();
            httpUtil.testVolleyGsonPost();
        });
        RxView.clicks(btn3).subscribe(s -> {
            String imgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523618064597&di=ac7de8388af7ee477a2a790e092b28b3&imgtype=0&src=http%3A%2F%2Feasyread.ph.126.net%2Fmzyb1ugXF8fP4jF6p2NB4A%3D%3D%2F7916994992896150040.jpg";
            HttpUtil httpUtil = new HttpUtil();
            httpUtil.testVolleyImageLoader(imgUrl, iv);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @OnClick(R.id.btn)
    public void onBtnClick() {
        mAdapter.setDatas(searchData());
    }

    public List<String> searchData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add("这是第" + i + "个条目");
        }
        return strings;
    }

    public class Main2Adapter extends BaseAdapter {
        public List<String> data;

        public Main2Adapter() {
        }

        public void setDatas(List<String> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText(data.get(position));
            return convertView;
        }
    }

    public class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
