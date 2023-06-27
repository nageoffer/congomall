/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengoofy.congomall.biz.bff.toolkit;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import org.opengoofy.congomall.biz.bff.config.GeeTestProperties;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * 极验验证码工具类
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public class GeeTestLib {
    
    /**
     * 公钥
     */
    private String captchaId = "";
    
    /**
     * 私钥
     */
    private String privateKey = "";
    
    /**
     * 返回字符串
     */
    private String responseStr = "";
    
    public static final boolean NEW_FAIL_BACK = true;
    protected final String apiUrl = "http://api.geetest.com";
    protected final String registerUrl = "/register.php";
    protected final String validateUrl = "/validate.php";
    protected final String json_format = "1";
    
    public GeeTestLib(String captchaId, String privateKey) {
        this.captchaId = captchaId;
        this.privateKey = privateKey;
    }
    
    public GeeTestLib(GeeTestProperties geeTestProperties) {
        this.captchaId = geeTestProperties.getCaptchaId();
        this.privateKey = geeTestProperties.getPrivateKey();
    }
    
    /**
     * 验证初始化预处理
     */
    public int preProcess(HashMap<String, String> data) {
        if (registerChallenge(data) != 1) {
            this.responseStr = this.getFailPreProcessRes();
            return 0;
        }
        return 1;
    }
    
    /**
     * 用 captchaID 进行注册，更新 challenge
     */
    private int registerChallenge(HashMap<String, String> data) {
        try {
            String userId = data.get("user_id");
            String clientType = data.get("client_type");
            String ipAddress = data.get("ip_address");
            String getUrl = apiUrl + registerUrl + "?";
            String param = "gt=" + this.captchaId + "&json_format=" + this.json_format;
            if (userId != null) {
                param = param + "&user_id=" + userId;
            }
            if (clientType != null) {
                param = param + "&client_type=" + clientType;
            }
            if (ipAddress != null) {
                param = param + "&ip_address=" + ipAddress;
            }
            String result_str = readContentFromGet(getUrl + param);
            if (result_str == "fail") {
                return 0;
            }
            JSONObject jsonObject = JSONObject.parseObject(result_str);
            String return_challenge = jsonObject.getString("challenge");
            if (return_challenge.length() == 32) {
                this.responseStr = this.getSuccessPreProcessRes(this.md5Encode(return_challenge + this.privateKey));
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ignored) {
        }
        return 0;
    }
    
    /**
     * 预处理失败后的返回格式串
     */
    private String getFailPreProcessRes() {
        Long rnd1 = Math.round(Math.random() * 100);
        Long rnd2 = Math.round(Math.random() * 100);
        String md5Str1 = md5Encode(rnd1 + "");
        String md5Str2 = md5Encode(rnd2 + "");
        String challenge = md5Str1 + md5Str2.substring(0, 2);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("success", 0);
            jsonObject.put("gt", this.captchaId);
            jsonObject.put("challenge", challenge);
            jsonObject.put("new_captcha", this.NEW_FAIL_BACK);
        } catch (JSONException ignored) {
        }
        return jsonObject.toString();
    }
    
    public String getResponseStr() {
        return responseStr;
    }
    
    /**
     * 发送 GET 请求，获取服务器返回结果
     */
    private String readContentFromGet(String URL) throws IOException {
        java.net.URL getUrl = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) getUrl
                .openConnection();
        connection.setConnectTimeout(2000);
        connection.setReadTimeout(2000);
        connection.connect();
        if (connection.getResponseCode() == 200) {
            StringBuffer sBuffer = new StringBuffer();
            InputStream inStream;
            byte[] buf = new byte[1024];
            inStream = connection.getInputStream();
            for (int n; (n = inStream.read(buf)) != -1; ) {
                sBuffer.append(new String(buf, 0, n, "UTF-8"));
            }
            inStream.close();
            connection.disconnect();
            return sBuffer.toString();
        } else {
            return "fail";
        }
    }
    
    /**
     * 预处理成功后的标准串
     */
    private String getSuccessPreProcessRes(String challenge) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("success", 1);
            jsonObject.put("gt", this.captchaId);
            jsonObject.put("challenge", challenge);
        } catch (JSONException ignored) {
        }
        return jsonObject.toString();
    }
    
    /**
     * MD5 加密
     */
    private String md5Encode(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
    
    /**
     * 服务正常的情况下使用的验证方式，向 gt-server 进行二次验证，获取验证结果
     */
    public int enhancedValidateRequest(String challenge, String validate, String seccode, HashMap<String, String> data) {
        if (!requestIsLegal(challenge, validate, seccode)) {
            return 0;
        }
        String userId = data.get("user_id");
        String clientType = data.get("client_type");
        String ipAddress = data.get("ip_address");
        String postUrl = this.apiUrl + this.validateUrl;
        String param = String.format("challenge=%s&validate=%s&seccode=%s&json_format=%s",
                challenge, validate, seccode, this.json_format);
        if (userId != null) {
            param = param + "&user_id=" + userId;
        }
        if (clientType != null) {
            param = param + "&client_type=" + clientType;
        }
        if (ipAddress != null) {
            param = param + "&ip_address=" + ipAddress;
        }
        String response = "";
        try {
            if (validate.length() <= 0) {
                return 0;
            }
            if (!checkResultByPrivate(challenge, validate)) {
                return 0;
            }
            response = readContentFromPost(postUrl, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String return_seccode;
        try {
            JSONObject return_map = JSONObject.parseObject(response);
            return_seccode = return_map.getString("seccode");
            if (return_seccode.equals(md5Encode(seccode))) {
                return 1;
            } else {
                return 0;
            }
        } catch (JSONException e) {
            return 0;
        }
    }
    
    /**
     * 检查客户端的请求是否合法，三个只要有一个为空，则判断不合法
     */
    private boolean requestIsLegal(String challenge, String validate, String seccode) {
        if (objIsEmpty(challenge)) {
            return false;
        }
        if (objIsEmpty(validate)) {
            return false;
        }
        if (objIsEmpty(seccode)) {
            return false;
        }
        return true;
    }
    
    /**
     * 判断一个表单对象值是否为空
     */
    protected boolean objIsEmpty(Object gtObj) {
        if (gtObj == null) {
            return true;
        }
        if (gtObj.toString().trim().length() == 0) {
            return true;
        }
        return false;
    }
    
    protected boolean checkResultByPrivate(String challenge, String validate) {
        String encodeStr = md5Encode(privateKey + "geetest" + challenge);
        return validate.equals(encodeStr);
    }
    
    /**
     * 发送 POST 请求，获取服务器返回结果
     */
    private String readContentFromPost(String URL, String data) throws IOException {
        URL postUrl = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) postUrl
                .openConnection();
        connection.setConnectTimeout(2000);
        connection.setReadTimeout(2000);
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
        outputStreamWriter.write(data);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        if (connection.getResponseCode() == 200) {
            StringBuffer sBuffer = new StringBuffer();
            InputStream inStream = null;
            byte[] buf = new byte[1024];
            inStream = connection.getInputStream();
            for (int n; (n = inStream.read(buf)) != -1; ) {
                sBuffer.append(new String(buf, 0, n, "UTF-8"));
            }
            inStream.close();
            connection.disconnect();
            return sBuffer.toString();
        } else {
            return "fail";
        }
    }
    
    /**
     * failBack 使用的验证方式
     */
    public int failBackValidateRequest(String challenge, String validate, String seccode) {
        if (!requestIsLegal(challenge, validate, seccode)) {
            return 0;
        }
        return 1;
    }
}
