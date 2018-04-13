package com.qun.test.rxjava_sample;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class HttpUtil {
    public String url = "http://10.8.8.143:8080/HttpDemoServlet/servlet/Servlet1";

    public void testVolleyGsonPost() {
        String url2 = "http://10.8.8.143:8080/HttpDemoServlet/servlet/Servlet2";
        RequestQueue queue = Volley.newRequestQueue(App.sContext);
        GsonRequest<UserInfo> gsonRequest = new GsonRequest<UserInfo>(
                Request.Method.GET,
                url2,
                UserInfo.class,
                new Response.Listener<UserInfo>() {
                    @Override
                    public void onResponse(UserInfo userInfo) {
                        Log.e("weiqun12345", "userInfo=" + userInfo);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(gsonRequest);
    }

    public void testVolleyImageLoader(String url, ImageView imageView) {
        RequestQueue queue = Volley.newRequestQueue(App.sContext);
        ImageLoader imageLoader = new ImageLoader(queue, new BitampCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground);
        imageLoader.get(url, imageListener);
    }

    public void testVolleyJsonArrayPost() {
        String url2 = "http://10.8.8.143:8080/HttpDemoServlet/servlet/Servlet3";
        RequestQueue queue = Volley.newRequestQueue(App.sContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url2, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.has("username")) {
                            Log.e("weiqun12345", "json username=" + jsonObject.getString("username"));
                        }
                        if (jsonObject.has("password")) {
                            Log.e("weiqun12345", "json password=" + jsonObject.getString("password"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(jsonArrayRequest);
    }

    public void testVolleyJsonPost() {
        String url2 = "http://10.8.8.143:8080/HttpDemoServlet/servlet/Servlet2";
        RequestQueue queue = Volley.newRequestQueue(App.sContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("weiqun12345", "Thread id:" + Thread.currentThread().getName());
                try {
                    if (jsonObject.has("username")) {
                        Log.e("weiqun12345", "json username=" + jsonObject.getString("username"));
                    }
                    if (jsonObject.has("password")) {
                        Log.e("weiqun12345", "json password=" + jsonObject.getString("password"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(jsonObjectRequest);
    }

    public void testVolleyPost() {
        RequestQueue queue = Volley.newRequestQueue(App.sContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("weiqun12345", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("weiqun12345", volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", "aaa");
                params.put("password", "bbb");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void testVolleyGet() {
        String url2 = url + "?username=" + "aaa" + "&password=" + "bbb";
        RequestQueue queue = Volley.newRequestQueue(App.sContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("weiqun12345", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("weiqun12345", volleyError.toString());
            }
        });
        queue.add(stringRequest);
    }

    public void testHttpUrlConnectGet() throws Exception {
        URL url1 = new URL(url + "?username=" + "aaa" + "&password=" + "bbb");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(false);
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.connect();
        Log.e("weiqun12345", "urlconnection response headers and requestMethod=" + httpURLConnection.getRequestMethod());
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        Set<Map.Entry<String, List<String>>> entrySet = headerFields.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> next = iterator.next();
            String key = next.getKey();
            List<String> value = next.getValue();
            if (key == null)
                Log.e("weiqun12345", value.toString());
            else
                Log.e("weiqun12345", key + ":" + value.toString());
        }

        if (HttpStatus.SC_OK == httpURLConnection.getResponseCode() || HttpStatus.SC_MOVED_TEMPORARILY == httpURLConnection.getResponseCode()
                ) {
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = br.readLine()) != null) {
                Log.e("weiqun12345", line);
            }
            br.close();
            is.close();
        } else {
            Log.e("weiqun12345", "response code=" + httpURLConnection.getResponseCode());
        }
    }

    public void testHttpUrlConnectPost() throws Exception {
        HttpURLConnection httpURLConnection = createHttpUrlConnection();
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("username", "aaa"));
        nameValuePairs.add(new BasicNameValuePair("password", "bbb"));
        StringBuilder mStringBuilder = new StringBuilder();
        for (NameValuePair nameValuePair : nameValuePairs) {
            if (!TextUtils.isEmpty(mStringBuilder)) {
                mStringBuilder.append("&");
            }
            mStringBuilder.append(URLEncoder.encode(nameValuePair.getName(), "UTF-8"));
            mStringBuilder.append("=");
            mStringBuilder.append(URLEncoder.encode(nameValuePair.getValue(), "UTF-8"));
        }

        Log.e("weiqun12345", "urlconnection request headers");
        Map<String, List<String>> headerFields1 = httpURLConnection.getRequestProperties();
        Set<Map.Entry<String, List<String>>> entrySet1 = headerFields1.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator1 = entrySet1.iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, List<String>> next = iterator1.next();
            String key = next.getKey();
            List<String> value = next.getValue();
            if (key == null)
                Log.e("weiqun12345", value.toString());
            else
                Log.e("weiqun12345", key + ":" + value.toString());
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
        bw.write(mStringBuilder.toString());
        bw.flush();
        bw.close();

        Log.e("weiqun12345", "urlconnection response headers");
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        Set<Map.Entry<String, List<String>>> entrySet = headerFields.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> next = iterator.next();
            String key = next.getKey();
            List<String> value = next.getValue();
            if (key == null)
                Log.e("weiqun12345", value.toString());
            else
                Log.e("weiqun12345", key + ":" + value.toString());
        }

        if (HttpStatus.SC_OK == httpURLConnection.getResponseCode() || HttpStatus.SC_MOVED_TEMPORARILY == httpURLConnection.getResponseCode()
                ) {
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = br.readLine()) != null) {
                Log.e("weiqun12345", line);
            }
            br.close();
            is.close();
        } else {
            Log.e("weiqun12345", "response code=" + httpURLConnection.getResponseCode());
        }


    }


    public HttpURLConnection createHttpUrlConnection() throws Exception {
        URL url1 = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        return httpURLConnection;
    }

    public HttpClient getHttpClient() {
        HttpParams mDefaultHttpParams = new BasicHttpParams();
        //设置连接超时
        HttpConnectionParams.setConnectionTimeout(mDefaultHttpParams, 15000);
        //设置请求超时
        HttpConnectionParams.setSoTimeout(mDefaultHttpParams, 15000);
        HttpConnectionParams.setTcpNoDelay(mDefaultHttpParams, true);
        HttpProtocolParams.setVersion(mDefaultHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(mDefaultHttpParams, HTTP.UTF_8);
        //持续握手
        HttpProtocolParams.setUseExpectContinue(mDefaultHttpParams, true);
        HttpClient client = new DefaultHttpClient(mDefaultHttpParams);
        return client;
    }

    public void testHttpClientPost() throws Exception {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);
        post.addHeader("Connection", "Keep-Alive");
        List<NameValuePair> postParams = new ArrayList<>();
        postParams.add(new BasicNameValuePair("username", "aaa"));
        postParams.add(new BasicNameValuePair("password", "bbb"));
        post.setEntity(new UrlEncodedFormEntity(postParams));
        HttpResponse response = client.execute(post);
        printHeaders("response", response.getAllHeaders());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = br.readLine()) != null) {
                Log.e("weiqun12345", line);
            }
            br.close();
            is.close();
        }
    }

    /**
     * HttpClient
     */
    public void testHttpClientGet() throws Exception {
        HttpClient client = getHttpClient();
        HttpGet get = new HttpGet(url + "?username=" + "aaa" + "&password=" + "bbb");
        get.addHeader("www", "aaa");
        printHeaders("request", get.getAllHeaders());
        HttpResponse response = client.execute(get);
        printHeaders("response", response.getAllHeaders());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = br.readLine()) != null) {
                Log.e("weiqun12345", line);
            }
            br.close();
            is.close();
        }
    }

    public void printHeaders(String type, Header[] headers) {
        Log.e("weiqun12345", type + " header");
        for (Header header : headers) {
            Log.e("weiqun12345", "header :" + header.getName() + ":" + header.getValue());
        }
    }
}
