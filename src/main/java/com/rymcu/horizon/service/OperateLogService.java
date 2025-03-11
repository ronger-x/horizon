package com.rymcu.horizon.service;

import com.rymcu.horizon.entity.OperateLog;
import com.rymcu.horizon.model.OperateLogSearch;

import java.util.List;

/**
 * Created on 2024/3/13 9:48.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.rymcu.horizon.service
 */
public interface OperateLogService {
    List<OperateLog> findOperateLogs(OperateLogSearch operateLogSearch);
}
