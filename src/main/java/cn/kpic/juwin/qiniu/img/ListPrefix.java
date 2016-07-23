package cn.kpic.juwin.qiniu.img;

import cn.kpic.juwin.qiniu.img.Mac;
import cn.kpic.juwin.qiniu.img.Config;
import cn.kpic.juwin.qiniu.img.ListItem;
import cn.kpic.juwin.qiniu.img.ListPrefixRet;
import cn.kpic.juwin.qiniu.img.RSFClient;

public class ListPrefix {

	public static void main(String[] args) {
		Config.ACCESS_KEY = "hHxSIgvdRg4y-xA1dzoCigBmQreP4KtSkvHuq3MV";
		Config.SECRET_KEY = "cIOm4gyYuwlQNpqBMOv2nCTQvR3bQ0gKGUvo-8at";
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		RSFClient client = new RSFClient(mac);
		ListPrefixRet list = client.listPrifix("kabi1", "", "", 10);
		System.out.println(list.results.size());
		for (ListItem item : list.results) {
			System.out.println(item.key);
		}
	}
}
