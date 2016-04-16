package cn.kpic.juwin.qiniu.img;

import cn.kpic.juwin.qiniu.img.DigestAuthClient;
import cn.kpic.juwin.qiniu.img.Mac;
import cn.kpic.juwin.qiniu.img.Config;
import cn.kpic.juwin.qiniu.img.CallRet;
import cn.kpic.juwin.qiniu.img.Client;

public class RSFClient {
	public Client conn;
	
	public RSFClient(Mac mac) {
		this.conn = new DigestAuthClient(mac);
	}
	
	public ListPrefixRet listPrifix(String bucketName, String prefix, String marker, int limit) {
		StringBuilder params = new StringBuilder();
		params.append("bucket=").append(bucketName);
		if (marker != null && marker.length() != 0) {
			params.append("&marker=").append(marker);
		}
		if (prefix != null && marker.length() != 0) {
			params.append("&prefix=").append(prefix);
		}
		if (limit > 0) {
			params.append("&limit=").append(limit);
		}
		
		String url = Config.RSF_HOST + "/list?" + params.toString();
		CallRet ret = conn.call(url);
		return new ListPrefixRet(ret);
	}
	
}
