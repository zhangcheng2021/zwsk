package com.hsmap.yuezhihui.service.core;

import com.hsmap.yuezhihui.entity.core.CoreTemplateRelation;

public interface ICoreTemplateRelationService {
    /**
     * 根据Code查询模板关系映射
     * @param code
     * @return
     */
    CoreTemplateRelation findByCode(String code);
}
