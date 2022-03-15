package com.hsmap.yuezhihui.service.sms;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.sys.SysSmsTemplate;
import com.hsmap.yuezhihui.entity.sys.SysSmsTemplateExample;

/***
 * 短信模板service接口
 */
public interface ISysSmsTemplateService  extends IBaseService<SysSmsTemplate,Integer, SysSmsTemplateExample> {

    public int syncAll2Redis(int status);

    public String getTemplateContentByRedis(String key);

    public int modify2Redis(SysSmsTemplate model);

    public int save2Redis(SysSmsTemplate model);

    public int delete2Redis(int id);

    PageInfo<SysSmsTemplate> list(SysSmsTemplate template, Pageable pageable);


    public String getSmsContent(String code, String... key);
    public String getMsgAddSignature(String msg);
}
