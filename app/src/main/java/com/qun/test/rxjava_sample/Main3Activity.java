package com.qun.test.rxjava_sample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

public class Main3Activity extends BaseActivity {
    @BindView(R.id.btn1)
    public Button btn1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main3;
    }

    @Override
    public void onViewCreated(Bundle saveInstanceState) {
        Disposable disposable = RxView.clicks(btn1).subscribe(v -> Log.e("weiqun12345", "Main3Activity onClick"));
        addDisposable(disposable);
    }
}
