package io.laokou.admin.domain.sys.repository.dao;

import io.laokou.common.dao.BaseDao;
import io.laokou.admin.domain.sys.entity.SysLogErrorDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 异常日志 dao
 */
@Mapper
@Repository
public interface SysLogErrorDao extends BaseDao<SysLogErrorDO> {
	
}
