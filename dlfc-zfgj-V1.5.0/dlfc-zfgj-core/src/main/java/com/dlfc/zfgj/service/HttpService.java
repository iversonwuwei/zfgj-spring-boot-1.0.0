/**
* @name: HttpService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 向指定URL发送
*
* @version: 1.0
* @date : 2016年3月8日 
* @author: liuyundong 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年3月8日       liuyundong        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @name: HttpService
 * @description: 向指定URL发送
 * @version 1.0
 * @author liuyundong
 *
 */
@Service("httpService")
public class HttpService {
	/** 日志静态变量 */
	private final static Logger logger = LoggerFactory.getLogger(HttpService.class);
	/** 默认超时时间静态变量 */
	public static final int DEFAULT_TIME_OUT = 50000;

	public String post(Map<String, String> param, String charset, String url, int timeout) throws Exception {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse resp = null;
		String result = "";
		int statusCode = 0;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost method = new HttpPost(url);
			// 设置超時
			if (0 == timeout) {
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout)
						.setConnectTimeout(timeout).build();
				method.setConfig(requestConfig);
			}
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Set<String> sets = param.keySet();
			for (String key : sets) {
				String value = param.get(key);
				list.add(new BasicNameValuePair(key, value));
			}

			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				method.setEntity(entity);
			}
			resp = httpclient.execute(method);

			statusCode = resp.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("与【" + url + "】通信发生异常,错误代码【" + statusCode + "】");
			}
			result = EntityUtils.toString(resp.getEntity());
			result = new String(result.getBytes("ISO-8859-1"), "UTF-8");

		} catch (Exception ex) {
			logger.error("HttpService post", ex);
			throw ex;
		} finally {
			if (resp != null) {
				resp.close();
			}
			if (httpclient != null) {
				httpclient.close();
			}
		}
		return result;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			// Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// HttpService httpService = new HttpService();
		// String a =
		// httpService.sendGet(Const.ALIPAY_BANKCARD,"_input_charset=utf-8&cardBinCheck=true&cardNo=4349100410487681");
		// if(a.contains("\"validated\":true")){
		// ObjectMapper mapper = new ObjectMapper();
		// JsonNode jsonNode = mapper.readTree(a);
		// String orderNo = jsonNode.get("bank").textValue();
		// System.out.println(orderNo);
		// }
	}
}
