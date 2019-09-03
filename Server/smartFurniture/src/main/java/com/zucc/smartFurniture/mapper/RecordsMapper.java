package com.zucc.smartFurniture.mapper;

import com.sun.prism.impl.Disposer;
import com.zucc.smartFurniture.pojo.Records;

import java.util.List;

public interface RecordsMapper {
    /**获取记录*/
    List<Records> queryByUserId(int userId);
    /**添加记录*/
    int add(Records records);
}
