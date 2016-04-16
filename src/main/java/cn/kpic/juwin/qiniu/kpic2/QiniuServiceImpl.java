package cn.kpic.juwin.qiniu.kpic2;

import cn.kpic.juwin.domain.vo.UploadTokenInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/*
 * Created by bjsunqinwen on 2016/3/22.
 */

@Service
public class QiniuServiceImpl implements QiniuService{

    @Value("${kpic2_bucket_name}")
    private String bucketName;

    @Value("${kpic2_access_key}")
    private String access_key;

    @Value("${kpic2_secret_key}")
    private String secret_key;

    @Value("${pbar_call_back_url}")
    private String callbackUrl;

    private Auth auth;

    @PostConstruct
    public void init(){
        auth = Auth.create(access_key, secret_key);
    }

    @Override
    public UploadTokenInfo generateUploadToken() {
        UploadTokenInfo uploadTokenInfo = new UploadTokenInfo();

        String key = UUID.randomUUID().toString().replace("-", "");
        uploadTokenInfo.setKey(key);
        uploadTokenInfo.setToken(auth.uploadToken(bucketName));
        return uploadTokenInfo;
    }

    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
