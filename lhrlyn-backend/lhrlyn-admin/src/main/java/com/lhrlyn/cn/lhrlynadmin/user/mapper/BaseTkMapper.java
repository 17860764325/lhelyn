package com.lhrlyn.cn.lhrlynadmin.user.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 *  增强mapper的基类，支持批量操作，其他的mapper继承此mapper
 * @author lhr
 * @date  2022/1/18 11:28 上午
 * @param
 * @return:
 */
public interface BaseTkMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
