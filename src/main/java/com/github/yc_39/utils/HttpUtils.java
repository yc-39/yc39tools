package com.github.yc_39.utils;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.*;

/**
 * 简单的http请求
 */
public class HttpUtils {
    private static CloseableHttpClient httpClient = null;
    private static HttpPost httpPost = null;
    private static HttpGet httpGet = null;
    private static CloseableHttpResponse httpResponse = null;
    private static HttpEntity entity = null;
    private static InputStream is = null;
    private static BufferedReader br = null;
    private static BufferedOutputStream bos = null;
    private static int cache = 10 * 1024 * 1024; // 缓冲区大小
    private static String[] user_agents = {
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; AcooBrowser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Acoo Browser; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506)",
            "Mozilla/4.0 (compatible; MSIE 7.0; AOL 9.5; AOLBuild 4337.35; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
            "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)",
            "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 1.0.3705; .NET CLR 1.1.4322)",
            "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 5.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.2; .NET CLR 3.0.04506.30)",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/523.15 (KHTML, like Gecko, Safari/419.3) Arora/0.3 (Change: 287 c9dfb30)",
            "Mozilla/5.0 (X11; U; Linux; en-US) AppleWebKit/527+ (KHTML, like Gecko, Safari/419.3) Arora/0.6",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.2pre) Gecko/20070215 K-Ninja/2.1.1",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9) Gecko/20080705 Firefox/3.0 Kapiko/3.0",
            "Mozilla/5.0 (X11; Linux i686; U;) Gecko/20070322 Kazehakase/0.4.5",
            "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.8) Gecko Fedora/1.9.0.8-1.fc10 Kazehakase/0.5.6",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.20 (KHTML, like Gecko) Chrome/19.0.1036.7 Safari/535.20",
            "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; LBBROWSER)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E; LBBROWSER)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 LBBROWSER",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; QQBrowser/7.0.3698.400)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; 360SE)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1",
            "Mozilla/5.0 (iPad; U; CPU OS 4_2_1 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5",
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.0b13pre) Gecko/20110307 Firefox/4.0b13pre",
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:16.0) Gecko/20100101 Firefox/16.0",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11",
            "Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10"
    };

    /**
     * 随机获取一个user_agents
     * @return
     */
    public static String getUserAgentRandom() {
        return user_agents[(int)(Math.random()*user_agents.length)];
    }


    /*** get请求 ***/

    /**
     * get请求
     * @param url
     * @return
     */
    public static String get(String url) {
        // 设置请求头
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Accept", "text/html,application/xhtml+xml, application/xml;q=0.9,image/webp,*/*;q=0.8");
        headerMap.put("Accept-Encoding", "gzip, deflate, sdch, br");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.8");
        headerMap.put("User-Agent", getUserAgentRandom());
        return get(url, headerMap, null);
    }

    /**
     * get请求
     * @param url
     * @param headerMap 请求头
     * @param paramMap 请求参数
     * @return
     */
    public static String get(String url, Map<String, String> headerMap, Map<String, String> paramMap) {
        Header[] headers = headerMap2Array(headerMap);
        return get(url, headers, paramMap);
    }

    /**
     * get请求
     * @param url
     * @param headers 请求头
     * @param paramMap 请求参数
     * @return
     */
    public static String get(String url, Header[] headers, Map<String, String> paramMap) {
//        StringBuffer sb = new StringBuffer();
        String result = null;
        try {
            // 创建默认客户端
            httpClient = HttpClients.createDefault();
            // 拼接参数到URL
            if (paramMap != null && !paramMap.isEmpty()) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (String param : paramMap.keySet()) {
                    params.add(new BasicNameValuePair(param, paramMap.get(param)));
                }
                url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
            }

            // 创建Get请求
            httpGet = new HttpGet(url);

            // 添加头部信息
            if (headers != null && headers.length != 0) {
                httpGet.setHeaders(headers);
            }

            // 执行Get请求
            httpResponse = httpClient.execute(httpGet);
            // 获取请求相应状态码
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            // 处理执行返回结果
            result = doAfterExecute(httpResponse);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }


    /*** post请求 ***/
    /**
     * post请求
     * @param url
     * @return
     */
    public static String post(String url) {
        return post(url, null);
    }

    /**
     * post请求
     * @param url
     * @param paramMap 请求参数
     * @return
     */
    public static String post(String url, Map<String, String> paramMap) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Accept", "text/html,application/xhtml+xml, application/xml;q=0.9,image/webp,*/*;q=0.8");
        headerMap.put("Accept-Encoding", "gzip, deflate, sdch, br");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.8");
        headerMap.put("User-Agent", getUserAgentRandom());
        return post(url, headerMap, paramMap);
    }

    /**
     * post请求
     * @param url
     * @param headerMap 请求头
     * @param paramMap 请求参数
     * @return
     */
    public static String post(String url, Map<String, String> headerMap, Map<String, String> paramMap) {
        Header[] headers = headerMap2Array(headerMap);
        return post(url, headers, paramMap);
    }

    /**
     * post请求
     * @param url
     * @param headers 请求头
     * @param paramMap 请求参数
     * @return
     */
    public static String post(String url, Header[] headers, Map<String, String> paramMap) {
//        StringBuffer sb = new StringBuffer();
        String result = null;
        try {
            // 创建默认客户端
            httpClient = HttpClients.createDefault();

            // 创建post请求
            httpPost = new HttpPost(url);

            // 设置请求头部
            if (headers != null && headers.length != 0) {
                httpPost.setHeaders(headers);
            }

            // 设置请求参数
            if (paramMap != null && !paramMap.isEmpty()) {
                List<NameValuePair> nvps = new ArrayList<>();
                for (String param : paramMap.keySet()) {
                    nvps.add(new BasicNameValuePair(param, paramMap.get(param)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            }

            // 执行请求
            httpResponse = httpClient.execute(httpPost);

            // 处理执行返回结果
            result = doAfterExecute(httpResponse);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }

    /**
     * 处理执行返回结果
     * @param httpResponse
     */
    private static String doAfterExecute(HttpResponse httpResponse) throws IOException{
        StringBuffer sb = new StringBuffer();
//        // 获取请求响应状态码
//        int statusCode = httpResponse.getStatusLine().getStatusCode();
//        // 打印请求结果状态
        System.out.println("[response status]" + httpResponse.getStatusLine().toString());
//        // 打印服务器响应状态码
//        System.out.println("[response status code]" + statusCode);
//        // 打印响应头
//        if (statusCode == 200) {
//            Header[] responseHeaders = httpResponse.getAllHeaders();
//            System.out.println("[response headers]");
//            for (Header header : responseHeaders) {
//                System.out.println(header.getName() + ":" + header.getValue());
//            }
//        }

        // 得到响应体
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            is = entity.getContent();
            br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));
            String temp = br.readLine();
            while (temp != null) {
                sb.append(temp);
                sb.append("\r\n");
                temp = br.readLine();
            }
        }
        return sb.toString();
    }

    /**
     * header 从map转换为数组
     * @param headerMap
     * @return
     */
    private static Header[] headerMap2Array(Map<String, String> headerMap) {
        Header[] headers = null;
        if (headerMap != null && !headerMap.isEmpty()) {
            headers = new Header[headerMap.size()];
            int i = 0;
            for (String headerKey : headerMap.keySet()) {
                headers[i] = new BasicHeader(headerKey, headerMap.get(headerKey));
                i++;
            }
        }
        return headers;
    }


    /**
     * 下载文件
     * @param url 文件url
     * @param path 文件本地路径
     */
    public static void getFile(String url, String path){
        try {
            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet(url);
            httpResponse = httpClient.execute(httpGet);
            entity = httpResponse.getEntity();
            is = entity.getContent();
            bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
            byte[] buffer = new byte[cache];
            int temp = 0;
            while((temp = is.read(buffer)) != -1) {
                bos.write(buffer,0, temp);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    /**
     * 关闭
     */
    private static void closeAll() {
        try {
            if (bos != null) {
                bos.close();
                bos = null;
            }
            if (br != null) {
                br.close();
                br = null;
            }
            if (is != null) {
                is.close();
                is = null;
            }
            if (httpResponse != null) {
                httpResponse.close();
                httpResponse = null;
            }
            if (httpGet != null) {
                httpGet.abort();
                httpGet = null;
            }
            if (httpPost != null) {
                httpPost.abort();
                httpPost = null;
            }
            if (httpClient != null) {
                httpClient.close();
                httpClient = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        String url = "http://www.164yu.com/";

//        String str = post(url);
//        String str = get(url);
//        System.out.println(str);

        String fileUtl = "https://avatar.csdn.net/7/0/2/3_libiao312.jpg";
        String filePath = "d://yc//2_yc_39.jpg";
        try {
            getFile(fileUtl, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
