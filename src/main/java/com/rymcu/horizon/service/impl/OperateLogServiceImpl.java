package com.rymcu.horizon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rymcu.horizon.entity.OperateLog;
import com.rymcu.horizon.mapper.OperateLogMapper;
import com.rymcu.horizon.model.OperateLogSearch;
import com.rymcu.horizon.service.OperateLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2024/3/13 9:48.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.service.impl
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements OperateLogService {

    @Override
    public List<OperateLog> findOperateLogs(OperateLogSearch operateLogSearch) {
        return baseMapper.selectOperateLogs(operateLogSearch.getBizNo(), operateLogSearch.getType(), operateLogSearch.getSubType(), operateLogSearch.getStartDate(), operateLogSearch.getEndDate());
    }
}
