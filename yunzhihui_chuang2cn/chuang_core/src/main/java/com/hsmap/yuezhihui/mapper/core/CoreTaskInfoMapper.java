package com.hsmap.yuezhihui.mapper.core;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.core.CoreTaskInfo;
import com.hsmap.yuezhihui.entity.core.CoreTaskInfoExample;
import com.hsmap.yuezhihui.entity.core.CoreTaskInfo;
import com.hsmap.yuezhihui.entity.core.CoreTaskInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreTaskInfoMapper extends BaseMapper<CoreTaskInfo,Integer, CoreTaskInfoExample> {

}
