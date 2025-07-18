package com.kabin.file.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.kabin.common.annotation.Excel;
import com.kabin.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 财务报信息对象 tb_financial_file
 *
 * @author lichuan
 * @date 2025-07-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("财务报信息实体")
public class TbFinancialFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    @ApiModelProperty("文件id")
    private Long id;
    /**
     * 文件名称
     */
    @Excel(name = "文件名称")
    @ApiModelProperty("文件名称")
    private String name;
    /**
     * 文件路径
     */
    @Excel(name = "文件路径")
    @ApiModelProperty("文件路径")
    private String filePath;


}
