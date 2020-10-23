package com.family.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @program: simple_tools
 * @description:
 * @author: ChenWenLong
 * @create: 2019-10-17 15:50
 **/
public class HttpUtils {

	
	private static int connectTimeOut=5000;
	private static int readTimeout=5000;
    /**
     * 功能描述:
     * 〈发送get请求,请求参数格式是 name1=value1&name2=value2 的形式。〉
     *
     * @params : [url, param]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/10/17 15:53
     */
    public static String sendGet(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setConnectTimeout(connectTimeOut);
            connection.setReadTimeout(readTimeout);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            //connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (ConnectException e) {
            //此处转成自己的日志记录
            return "调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param;
        } catch (SocketTimeoutException e) {
            //此处转成自己的日志记录
            return "调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param;
        } catch (IOException e) {
            //此处转成自己的日志记录
            return "调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param;
        } catch (Exception e) {
            //此处转成自己的日志记录
            return "调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                //此处转成自己的日志记录
                return "调用in.close Exception, url=" + url + ",param=" + param;
            }
        }
        return result.toString();
    }

    /**
     * 发送post请求
     * @param url  路径
     * @param jsonObject  参数(json类型)
     * @param encoding 编码格式
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String sendPostByJson(String url, JSONObject jsonObject,String encoding) throws ParseException, IOException{
        String body = "";
 
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
 
        //装填参数
        StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                "application/json"));
        //设置参数到请求对象中
        httpPost.setEntity(s);
       // System.out.println("请求地址："+url);
//        System.out.println("请求参数："+nvps.toString());
 
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
//        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
 
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }
    /**
     * 功能描述:
     * 〈发送post请求,请求参数格式是 name1=value1&name2=value2 的形式。〉
     *
     * @params : [url, param]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/10/17 15:54
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setConnectTimeout(connectTimeOut);
            conn.setReadTimeout(readTimeout);
            //conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (ConnectException e) {
            //此处转成自己的日志记录
            return "调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param;
        } catch (SocketTimeoutException e) {
            //此处转成自己的日志记录
            return "调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param="+param;
        } catch (IOException e) {
            //此处转成自己的日志记录
            return "调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param;
        } catch (Exception e) {
            //此处转成自己的日志记录
            return "调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                //此处转成自己的日志记录
                return "调用in.close Exception, url=" + url + ",param=" + param;
            }
        }
        return result.toString();
    }

    /**
     * 功能描述:
     * 〈发送SSL方式的Post方法 请求参数格式是 name1=value1&name2=value2 的形式〉
     *
     * @params : [url, param]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/10/17 16:00
     */
    public static String sendSSLPost(String url, String param) {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setConnectTimeout(connectTimeOut);
            conn.setReadTimeout(readTimeout);
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while ((ret = br.readLine()) != null) {
                if (ret != null && !ret.trim().equals("")) {
                    result.append(new String(ret.getBytes("ISO-8859-1"), "utf-8"));
                }
            }
            conn.disconnect();
            br.close();
        } catch (ConnectException e) {
            return "调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param;
        } catch (SocketTimeoutException e) {
            return "调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param;
        } catch (IOException e) {
            return "调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param;
        } catch (Exception e) {
            return "调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param;
        }
        return result.toString();
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    public static void main(String[] args) {
    	JSONObject jb=new JSONObject();
    	jb.put("password", "123123");
    	jb.put("password2", "234234");
    	jb.put("username", "234234");
    	jb.put("uuid", "23432423");
    	int i=0;
    	while(i<10) {
    		i++;
        	String code=ShareCodeUtil.toSerialCode(1);
        	jb.put("inviteCode", code);
    		String result;
    		try {
    			result = HttpUtils.sendPostByJson("http://d7.2wsj5.cn/login", jb, "utf-8");
    			System.out.println( result);
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
	}
}
