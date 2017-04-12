package com.salesf.test;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

public class SalesForceC {
    private static final String userName= "username";
    private static final String password= "password";
    private static final String loginURL= "https://login.salesforce.com";
    private static final String grantService = "/services/oauth2/token?grant_type=password";
    private static final String consumerKey = "3MVG9d8..z.hDcPJRt7xI7Jbcvbzxd4zZVRyvwac14pHYQbjJLcTCtGUD16dw8.D.CRd_u0VSDUlgfxPumLLNpQ.Mk";
    private static final String secretKey = "143854324588448242";

    public  static void  getConnection() {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            String logURL = loginURL + grantService + "&client_id=" + consumerKey + "&client_secret=" + secretKey +
                    "&username=" + userName +
                    "&password=" + password;
            System.out.println(logURL);
            HttpPost httpPost = new HttpPost(logURL);
            HttpResponse httpResponse = null;
            httpResponse = client.execute(httpPost);
            final int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Error authenticating to Force.com: " + statusCode);
                return;
            }
            String getResult = EntityUtils.toString(httpResponse.getEntity());
            JSONObject jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
            String loginAccessToken = jsonObject.getString("access_token");
            String loginInstanceURL = jsonObject.getString("instance_url");
            System.out.println(httpResponse.getStatusLine());
            System.out.println("Successful login");
            System.out.println("  instance URL: "+loginInstanceURL);
            System.out.println("  access token/session ID: "+loginAccessToken);
            httpPost.releaseConnection();
        }
        catch(IOException ex)
        {
            System.out.println("IO Exception "+ex.getMessage().toString());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage().toString());
        }

    }
}
