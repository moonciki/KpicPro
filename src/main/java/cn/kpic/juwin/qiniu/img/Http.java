package cn.kpic.juwin.qiniu.img;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

/**
 * The class Http provides a default HttpConnectionPool implementation. Anyway,
 * you can set an appropriate version according to your application.
 * 
 */
public class Http {

	private static HttpClient client;

	/**
	 * Sets a new HttpClient instance
	 */
	public static void setClient(HttpClient c) {
		client = c;
	}

	/**
	 * Gets the HttpClient instance
	 */
	public static HttpClient getClient() {
		if (client == null) {
			client = makeDefaultClient();
		}
		return client;
	}

	// a default implementation
	private static HttpClient makeDefaultClient() {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory
				.getSocketFactory()));

		PoolingClientConnectionManager cm = new PoolingClientConnectionManager(
				schemeRegistry);
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("locahost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);

		return new DefaultHttpClient(cm);
	}
}
