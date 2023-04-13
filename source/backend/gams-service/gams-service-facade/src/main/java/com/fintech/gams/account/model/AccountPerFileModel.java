package com.fintech.gams.account.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.account.entity.AccountPerFile;
import com.fintech.gams.common.entity.Dictionary;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * @author yann
 * @date 2019-08-08 13:58
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "个人文件 + 字典")
public class AccountPerFileModel {

    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**文件路径*/
    @NotNull(message = "文件路径不可为空")
    @ApiModelProperty(value = "文件路径", required = true)
    private String filePath;

    /**文件名*/
    @NotNull(message = "文件名不可为空")
    @ApiModelProperty(value = "文件名", required = true)
    private String fileName;

    /**文件标识(见数据字典文件身份证类型)*/
    @NotNull(message = "文件标识(见数据字典文件身份证类型)不可为空")
    @ApiModelProperty(value = "文件标识(见数据字典文件身份证类型)", required = true)
    private Dictionary fileIdentification;

    /**创建人*/
    @NotNull(message = "创建人不可为空")
    @ApiModelProperty(value = "创建人", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;

    /**创建时间*/
    @NotNull(message = "创建时间不可为空")
    @ApiModelProperty(value = "创建时间", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdDate;

    /**更新人*/
    @ApiModelProperty(value = "更新人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedBy;

    /**更新时间*/
    @ApiModelProperty(value = "更新时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedDate;

    /**
     * 复制 model 到 entity
     * @return
     */
    public AccountPerFile convertToEntity(){
        AccountPerFileModel.ModelConvert modelConvert = new AccountPerFileModel.ModelConvert();
        AccountPerFile entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public AccountPerFileModel convertToModel(AccountPerFile entity){
        AccountPerFileModel.ModelConvert modelConvert = new AccountPerFileModel.ModelConvert();
        AccountPerFileModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<AccountPerFileModel, AccountPerFile> {
        @Override
        protected AccountPerFile doForward(AccountPerFileModel model) {
            AccountPerFile entity = new AccountPerFile();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected AccountPerFileModel doBackward(AccountPerFile entity) {
            AccountPerFileModel model = new AccountPerFileModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
