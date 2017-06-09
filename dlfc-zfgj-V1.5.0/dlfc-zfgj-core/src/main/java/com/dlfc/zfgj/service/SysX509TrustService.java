/**
* @name: SysX509TrustService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年8月15日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月15日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @name: SysX509TrustService.java
 * 
 * @Copyright: (c) 2016 DLFC. All rights reserved.
 * 
 * @description: 绕过证书验证发起https的get请求
 * 
 * @version: 1.0
 * @date : 2016年05月04日
 * @author: zhangyan
 * 
 * @Modification History:<br>
 *               Date Author Version Discription
 * 
 */
public class SysX509TrustService {
	/** log对象 */
	private final static Logger logger = LoggerFactory.getLogger(AllinpayService.class);
	
	/**
	 * 忽视证书HostName
	 */
	private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
		public boolean verify(String s, SSLSession sslsession) {
			return true;
		}
	};

	/**
	 * 重写验证方法，取消检测ssl
	 */
	private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {

		private X509Certificate[] certificates;

		@Override
		public void checkClientTrusted(X509Certificate certificates[], String authType) throws CertificateException {
			if (this.certificates == null) {
				this.certificates = certificates;
			}
		}

		@Override
		public void checkServerTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException {
			if (this.certificates == null) {
				this.certificates = ax509certificate;
			}
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

	/**
	 * 执行get方法
	 * 
	 * @param urlString 访问地址
	 * @return 
	 */
	public static String exeGetMethod(String urlString) {

		ByteArrayOutputStream buffer = null;
		HttpsURLConnection connection = null;
		InputStream reader = null;
		String repString = "";
		try {
			buffer = new ByteArrayOutputStream(512);
			URL url = new URL(urlString);
			/*
			 * 忽略主机验证
			 */
			HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
			connection = (HttpsURLConnection) url.openConnection();

			// 准备 SSL 内容
			TrustManager[] tm = { ignoreCertificationTrustManger };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			connection.setSSLSocketFactory(ssf);

			reader = connection.getInputStream();
			byte[] bytes = new byte[512];
			int length = reader.read(bytes);

			do {
				buffer.write(bytes, 0, length);
				length = reader.read(bytes);
			} while (length > 0);
			repString = new String(buffer.toByteArray());
		}catch (MalformedURLException e) {
				logger.error("SysX509TrustService exeGetMethod URL协议、格式或者路径错误", e);
				return null;
			} catch (IOException e) {
				logger.error("SysX509TrustService exeGetMethod 读写数据流异常", e);
				return null;
			} catch (NoSuchAlgorithmException e) {
				logger.error("SysX509TrustService exeGetMethod 获取SSLContext实例时异常", e);
				return null;
			} catch (NoSuchProviderException e) {
				logger.error("SysX509TrustService exeGetMethod 获取SSLContext实例时异常", e);
				return null;
			} catch (KeyManagementException e) { 
				logger.error("SysX509TrustService exeGetMethod 初始化SSLContext实例时异常", e);
				return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			try {
				if (buffer != null) {
					buffer.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				logger.error("SysX509TrustService exeGetMethod 关闭读写流时异常", e);
			}
		}
		return repString;
	}

}