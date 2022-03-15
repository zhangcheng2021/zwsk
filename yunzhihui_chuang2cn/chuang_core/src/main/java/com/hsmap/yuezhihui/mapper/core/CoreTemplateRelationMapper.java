package com.hsmap.yuezhihui.mapper.core;

import com.hsmap.yuezhihui.entity.core.CoreTemplateRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreTemplateRelationMapper {
    /**
     * 根据Code查询模板关系映射
     * @param code
     * @return
     */
    CoreTemplateRelation findByCode(String code);
}
