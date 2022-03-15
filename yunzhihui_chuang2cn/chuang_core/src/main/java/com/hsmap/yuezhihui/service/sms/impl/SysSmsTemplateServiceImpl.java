package com.hsmap.yuezhihui.service.sms.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.sys.SysSmsTemplate;
import com.hsmap.yuezhihui.entity.sys.SysSmsTemplateExample;
import com.hsmap.yuezhihui.mapper.sys.SysSmsTemplateMapper;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.service.sms.ISysSmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysSmsTemplateServiceImpl extends BaseServiceImpl<SysSmsTemplate,Integer, SysSmsTemplateExample> implements ISysSmsTemplateService {

    @Autowired
    SysSmsTemplateMapper mapper;
    @Autowired
    SysConfig sysConfig;

    private final String _SMS_SMS_TEMPLATE_ = "_SMS_SMS_TEMPLATE_";

    @Override
    public BaseMapper<SysSmsTemplate, Integer, SysSmsTemplateExample> getMapper() {
        return mapper;
    }

    @Override
    public int syncAll2Redis(int status) {
        SysSmsTemplateExample example = new SysSmsTemplateExample();
        SysSmsTemplateExample.Criteria criteria =example.createCriteria();
        criteria.andStatusEqualTo(status);

        List<SysSmsTemplate> list= this.find(example);
        if (list == null || list.size() < 1) {
            LOGGER.info("syncAll2Redis is null ");
            return 0 ;
        }
        for (SysSmsTemplate template:list) {
            this.setRedisValue(template);
        }

        return 0;
    }

    @Override
    public String getTemplateContentByRedis(String key) {
        String val = (String)getRedis(key);
        LOGGER.info("getTemplateContentByRedis,_PAY_KEY:"+key+",value:"+val);
        return val;
    }

    @Override
    public int modify2Redis(SysSmsTemplate model) {
        int count =  this.save(model);
        setRedisValue(model);
        return count;
    }

    @Override
    public int save2Redis(SysSmsTemplate model) {
         int count = this.save(model);
        setRedisValue(model);
        return count;
    }

    @Override
    public int delete2Redis(int id) {
        int count =this.deleteById(id);
        SysSmsTemplate smsTemplate = new SysSmsTemplate();
        removeRedis(_SMS_SMS_TEMPLATE_+smsTemplate.getCode());
        return count;
    }

    @Override
    public PageInfo<SysSmsTemplate> list(SysSmsTemplate template, Pageable pageable) {
        SysSmsTemplateExample example = new SysSmsTemplateExample();
        SysSmsTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(0);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        PageInfo<SysSmsTemplate> pageInfo  = findByPage(example,pageable);
        return pageInfo;
    }

    @Override
    public String getSmsContent(String code, String... key) {
        SysSmsTemplate template = new SysSmsTemplate();
//        String templateContent = (String)getRedis(template.getRedisPrefix()+code);
        String templateContent = getTemplateContentByRedis(_SMS_SMS_TEMPLATE_+code);
        if(CommonUtil.isEmpty(templateContent)) {
            return "";
        }
        LOGGER.info("templateContent:{}", templateContent);
        String msg= templateContent.replaceAll("\\{\\}", "%s");
        //msg =getMsgAddSignature(msg);
        return String.format(msg,key);
    }

    /**
     * 短信内容加签名
     * @param msg
     * @return
     */
    public String getMsgAddSignature(String msg) {
        msg=msg.replaceAll("\\【.*?\\】","");
        msg="【"+sysConfig.getSmsSignature()+"】"+msg;
        return msg;

    }

    private void setRedisValue(SysSmsTemplate template) {
        setRedis(_SMS_SMS_TEMPLATE_+template.getCode(),template.getContent());
    }
}
