package cn.kpic.juwin.qiniu.img;

import org.apache.http.client.methods.HttpPost;

import cn.kpic.juwin.qiniu.img.Mac;
import cn.kpic.juwin.qiniu.img.Client;

public class DigestAuthClient extends Client {
	public Mac mac;
	
	public DigestAuthClient(Mac mac) {
		this.mac = mac;
	}

	@Override
	public void setAuth(HttpPost post) throws AuthException {
		String accessToken = mac.signRequest(post);
		post.setHeader("Authorization", "QBox " + accessToken);
	}

}
