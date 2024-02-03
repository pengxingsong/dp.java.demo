package dp.java.demo.spring5x.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("TProductCategory")
public class ProductCategoryPO {
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;
    @TableField("Name")
    private String name;
    @TableField("Remark")
    private String remark;

    @TableField("CreateUserId")
    private Integer createUserId;
    @TableField("CreateTime")
    private LocalDateTime createTime;
    @TableField("UpdateUserId")
    private Integer updateUserId;
    @TableField("UpdateTime")
    private LocalDateTime updateTime;
    @TableField("DeleteFlag")
    private Integer deleteFlag;
}

