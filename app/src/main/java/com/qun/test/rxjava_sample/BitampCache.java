package com.qun.test.rxjava_sample;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class BitampCache implements ImageLoader.ImageCache {
    public LruCache<String, Bitmap> mLruCache;

    public BitampCache() {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return mLruCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        mLruCache.put(s,bitmap);
    }
}
