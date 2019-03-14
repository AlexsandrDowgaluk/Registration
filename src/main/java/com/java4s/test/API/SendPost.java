package com.java4s.test.API;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.java4s.test.config.Properties;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SendPost {

     String userName;
     String id;
     String contactNumber;

 public void sendPost (String name) throws IOException {
     CloseableHttpClient httpclient = HttpClients.createDefault();

     JSONObject jSONObject = new JSONObject();
     jSONObject.put("Name", name);


     try {
         HttpPost httppost = new HttpPost(Properties.getPostUrl());

         InputStreamEntity reqEntity = new InputStreamEntity(new ByteArrayInputStream(jSONObject.toJSONString().getBytes(StandardCharsets.UTF_8)));

         httppost.addHeader("Content-type", "application/json");
         httppost.setEntity(reqEntity);

         System.out.println("Executing request: " + httppost.getRequestLine());
         CloseableHttpResponse response = httpclient.execute(httppost);
         try {
             System.out.println("----------------------------------------");
             System.out.println(response.getStatusLine());
           //  System.out.println(EntityUtils.toString(response.getEntity()));

             HttpEntity responseEntity = response.getEntity();
             JsonObject result = (JsonObject) new JsonParser().parse(EntityUtils.toString(responseEntity));

             userName = result.getAsJsonPrimitive("Name").getAsString();
             id = result.getAsJsonPrimitive("Id").getAsString();
             contactNumber = result.getAsJsonPrimitive("Contact Number").getAsString();

         } finally {
             response.close();
         }
     } catch (ClientProtocolException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     } finally {
         httpclient.close();
     }
 }

       public String getName(){
            return userName;
       }

       public String getId(){
            return id;
    }

       public String getContactNumber(){
            return contactNumber;
       }

}