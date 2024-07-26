package com.youlai.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.model.bo.TableMetaData;
import com.youlai.system.model.form.GenConfigForm;
import com.youlai.system.model.query.TablePageQuery;
import com.youlai.system.model.vo.GeneratorPreviewVO;
import com.youlai.system.model.vo.TableColumnVO;
import com.youlai.system.model.vo.TablePageVO;

import java.util.List;

/**
 * 代码生成配置接口
 *
 * @author Ray
 * @since 2.10.0
 */
public interface GeneratorService {

    /**
     * 获取数据表分页列表
     *
     * @param queryParams 查询参数
     * @return
     */
    Page<TableMetaData> getTablePage(TablePageQuery queryParams);

    /**
     * 获取预览生成代码
     *
     * @param tableName 表名
     * @return
     */
    List<GeneratorPreviewVO> getTablePreviewData(String tableName);

    /**
     * 获取代码生成配置
     *
     * @param tableName 表名
     * @return
     */
    GenConfigForm getGenConfig(String tableName);

    /**
     * 保存代码生成配置
     *
     * @param formData 表单数据
     * @return
     */
    void saveGenConfig(GenConfigForm formData);


}
