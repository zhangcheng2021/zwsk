package com.hsmap.yuezhihui.mapper.core;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.core.CoreTaskDetail;
import com.hsmap.yuezhihui.entity.core.CoreTaskDetailExample;
import com.hsmap.yuezhihui.entity.core.CoreTaskDetail;
import com.hsmap.yuezhihui.entity.core.CoreTaskDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreTaskDetailMapper extends BaseMapper<CoreTaskDetail,Integer, CoreTaskDetailExample> {

}
