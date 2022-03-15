package com.hsmap.yuezhihui.service.core.impl;

import com.hsmap.yuezhihui.entity.core.CoreTemplateRelation;
import com.hsmap.yuezhihui.mapper.core.CoreTemplateRelationMapper;
import com.hsmap.yuezhihui.service.core.ICoreTemplateRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreTemplateRelationServiceImpl implements ICoreTemplateRelationService {
    @Autowired
    private CoreTemplateRelationMapper coreTemplateRelationMapper;

    @Override
    public CoreTemplateRelation findByCode(String code) {
        return coreTemplateRelationMapper.findByCode(code);
    }
}
