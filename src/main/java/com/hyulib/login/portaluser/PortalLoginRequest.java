package com.hyulib.login.portaluser;

import ezhub.crypt.RSA;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;


/**
 * Created by sklee on 2015. 12. 30..
 */
public class PortalLoginRequest {
    public InputStream portalLogin(String id, String password) {
        System.setProperty("jeus.home", "./libs");  // needed for tomcat
        Random random = new Random();
        String randomNum = String.valueOf(random.nextInt(3) + 1);
        InputStream inputStream = null;
        String keySuffix = "library_00" + randomNum;
        String loginGb = "1";
        String systemGb = "LIBRARY";
        try {
            String encryptId = RSA.encrypt(keySuffix, id);
            String encryptPassword = RSA.encrypt(keySuffix, password);
            URL url = new URL("https://portal.hanyang.ac.kr/sso/lgnp.do");
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);

            PrintWriter outputStream = new PrintWriter(urlConnection.getOutputStream());
            StringBuffer stringBuffer = new StringBuffer("loginGb=" + loginGb);
            stringBuffer.append("&systemGb=").append(URLEncoder.encode(systemGb, "euc-kr"));
            stringBuffer.append("&userId=").append(encryptId);
            stringBuffer.append("&password=").append(encryptPassword);
            stringBuffer.append("&keyNm=").append(URLEncoder.encode(keySuffix, "euc-kr"));
            outputStream.write(stringBuffer.toString());
            outputStream.flush();
            outputStream.close();

            inputStream = urlConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
