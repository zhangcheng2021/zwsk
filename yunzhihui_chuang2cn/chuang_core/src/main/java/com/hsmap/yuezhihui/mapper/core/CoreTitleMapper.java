package com.hsmap.yuezhihui.mapper.core;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.core.CoreTitle;
import com.hsmap.yuezhihui.entity.core.CoreTitleExample;
import com.hsmap.yuezhihui.entity.core.CoreTitle;
import com.hsmap.yuezhihui.entity.core.CoreTitleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreTitleMapper  extends BaseMapper<CoreTitle,Integer, CoreTitleExample> {

}

