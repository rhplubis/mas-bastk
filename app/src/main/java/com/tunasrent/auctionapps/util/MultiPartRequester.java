package com.tunasrent.auctionapps.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.mime.MIME;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.util.EntityUtils;

//import java.io.File;
//import java.util.Map;

public class MultiPartRequester {


    private Map<String, String> map;
    private AsyncTaskCompleteListener mAsynclistener;
    private int serviceCode;
    private Activity activity;
    private AsyncHttpRequest request;

//    private Map<String, String> map;
//    private AsyncTaskCompleteListener mAsynclistener;
//    private int serviceCode;
//    private HttpClient httpclient;
//    private Activity activity;
//    private AsyncHttpRequest request;

    public MultiPartRequester(Activity activity, Map<String, String> map,
                              int serviceCode, AsyncTaskCompleteListener asyncTaskCompleteListener) {
        this.map = map;
        this.serviceCode = serviceCode;
        this.activity = activity;

        // is Internet Connection Available...
        if (YuliYanto.isNetworkAvailable(activity)) {
            mAsynclistener = asyncTaskCompleteListener;
            request = (AsyncHttpRequest) new AsyncHttpRequest().execute(map.get("url"));
        } else {
            showToast("Network is not available!!!");
        }


//        if (YuliYanto.isNetworkAvailable(activity)) {
//            mAsynclistener = (AsyncTaskCompleteListener) asyncTaskCompleteListener;
//            request = (AsyncHttpRequest) new AsyncHttpRequest().execute(map
//                    .get("url"));
//        } else {
//            showToast("Network is not available!!!");
//        }

    }

//    class AsyncHttpRequest extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... urls) {
//            map.remove("url");
//            try {
//
//                HttpPost httppost = new HttpPost(urls[0]);
//                httpclient = new DefaultHttpClient();
//
//                HttpConnectionParams.setConnectionTimeout(
//                        httpclient.getParams(), 600000);
//
//                MultipartEntityBuilder builder = MultipartEntityBuilder
//                        .create();
//
//                for (String key : map.keySet()) {
//
//                    if (key.equalsIgnoreCase("filename")) {
//                        File f = new File(map.get(key));
//
//                        builder.addBinaryBody(key, f,
//                                ContentType.MULTIPART_FORM_DATA, f.getName());
//                    } else {
//                        builder.addTextBody(key, map.get(key), ContentType
//                                .create("text/plain", MIME.DEFAULT_CHARSET));
//                    }
//                    Log.d("TAG" + key, key + "---->" + map.get(key));
//
//
//                    // System.out.println(key + "---->" + map.get(key));
//                }
//
//                httppost.setEntity(builder.build());
//
//                ActivityManager manager = (ActivityManager) activity
//                        .getSystemService(Context.ACTIVITY_SERVICE);
//
//                if (manager.getMemoryClass() < 25) {
//                    System.gc();
//                }
//                HttpResponse response = httpclient.execute(httppost);
//
//                String responseBody = EntityUtils.toString(
//                        response.getEntity(), "UTF-8");
//
//                return responseBody;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } catch (OutOfMemoryError oume) {
//                System.gc();
//
//                Toast.makeText(
//                        activity.getParent().getParent(),
//                        "Run out of memory please colse the other background apps and try again!",
//                        Toast.LENGTH_LONG).show();
//            } finally {
//                if (httpclient != null)
//                    httpclient.getConnectionManager().shutdown();
//
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String response) {
//
//            if (mAsynclistener != null) {
//                mAsynclistener.onTaskCompleted(response, serviceCode);
//            }
//        }
//    }

    class AsyncHttpRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String boundary = "===" + System.currentTimeMillis() + "===";
            map.remove("url");

            try {
                // Open connection
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                connection.setConnectTimeout(60000);  // 60 seconds timeout
                connection.setReadTimeout(60000);

                OutputStream outputStream = connection.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                // Add form fields
                for (String key : map.keySet()) {
                    if (key.equalsIgnoreCase("filename")) {
                        File file = new File(map.get(key));
                        addFilePart(dataOutputStream, key, file, boundary);
                    } else {
                        addFormField(dataOutputStream, key, map.get(key), boundary);
                    }
                }

                // End of multipart form data
                dataOutputStream.writeBytes("--" + boundary + "--\r\n");
                dataOutputStream.flush();
                dataOutputStream.close();

                // Read response
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    return readStream(inputStream);
                } else {
                    return null;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            if (mAsynclistener != null) {
                mAsynclistener.onTaskCompleted(response, serviceCode);
            }
        }

        private void addFormField(DataOutputStream outputStream, String fieldName, String fieldValue, String boundary) throws Exception {
            outputStream.writeBytes("--" + boundary + "\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + fieldName + "\"\r\n\r\n");
            outputStream.writeBytes(fieldValue + "\r\n");
        }

        private void addFilePart(DataOutputStream outputStream, String fieldName, File uploadFile, String boundary) throws Exception {
            String fileName = uploadFile.getName();
            outputStream.writeBytes("--" + boundary + "\r\n");
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"\r\n");
            outputStream.writeBytes("Content-Type: " + HttpURLConnection.guessContentTypeFromName(fileName) + "\r\n");
            outputStream.writeBytes("\r\n");

            FileInputStream fileInputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.writeBytes("\r\n");
            fileInputStream.close();
        }

        private String readStream(InputStream inputStream) throws Exception {
            StringBuilder result = new StringBuilder();
            int ch;
            while ((ch = inputStream.read()) != -1) {
                result.append((char) ch);
            }
            return result.toString();
        }
    }



    private void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

}

