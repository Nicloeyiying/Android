package com.example.myapplication.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProductListHttpThread extends Thread {
    private String productResult;
    public void run() {
        try {
            URL url = new URL(HttpHelp.URL + "product");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(String.valueOf(MethodEnum.GET));
            httpURLConnection.setConnectTimeout(HttpHelp.CONNECT_TIME_OUT);
            InputStream is = httpURLConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuffer result = new StringBuffer();
            String temp = "";
            while ((temp = br.readLine()) != null){
                result.append(temp);
            }
            productResult = result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getProductResult() {
        return productResult;
    }
}
