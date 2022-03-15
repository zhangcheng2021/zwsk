package com.hsmap.yuezhihui.service.sms;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.sys.SysSmsBlacklist;
import com.hsmap.yuezhihui.entity.sys.SysSmsBlacklistExample;

import java.util.List;

/**
 * 短信黑名单管理service接口
 */

public interface ISysSmsBlacklistService extends IBaseService<SysSmsBlacklist, Integer, SysSmsBlacklistExample> {

    List<SysSmsBlacklist> findByType(int type);

    PageInfo<SysSmsBlacklist> search(SysSmsBlacklist sysSmsBlacklist, Pageable pageable);

    /**
     * 根据类型将名单同步 type 1-黑名单 2-白名单
     *
     * @param type
     * @return
     */
    public int syncType2Redis(int type);

    /***
     * 查看手机号码是否在名单中  type 1-黑名单 2-白名单
     * @param type
     * @return
     */
    public boolean mobileIsBlackByTypeRedis(String mobile, int type);

    public boolean mobileIsBlackByType(String mobile, int type);


}
