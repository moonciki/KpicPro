package cn.kpic.juwin.qiniu.kpic2;

import cn.kpic.juwin.domain.vo.UploadTokenInfo;

/**
 * Created by bjsunqinwen on 2016/3/23.
 */
public interface QiniuService {

    UploadTokenInfo generateUploadToken();

    String generateUUID();

    UploadTokenInfo generateUploadToken2();

}
