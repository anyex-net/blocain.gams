package com.fintech.gams.common.model;

import com.fintech.gams.bean.GenericEntity;
import com.fintech.gams.common.entity.NewsBulletin;
import com.fintech.gams.system.entity.UserInfo;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * @author yann
 * @date 2019-12-04 14:46
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "消息公告查询")
public class NewsBulletinModel extends GenericEntity {


    /**标题*/
    @NotNull(message = "标题不可为空")
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    /**类型(公告）*/
    @NotNull(message = "类型(公告）不可为空")
    @ApiModelProperty(value = "类型(公告）", required = true)
    private String type;

    /**内容*/
    @NotNull(message = "内容不可为空")
    @ApiModelProperty(value = "内容", required = true)
    private String content;

    /**语言(en_US,zh_CN,zh_HK)*/
    @NotNull(message = "语言(en_US,zh_CN,zh_HK)不可为空")
    @ApiModelProperty(value = "语言(en_US,zh_CN,zh_HK)", required = true)
    private String lang;

    /**状态(0待发布、1已发布、2已下架)*/
    @NotNull(message = "状态(0待发布、1已发布、2已下架)不可为空")
    @ApiModelProperty(value = "状态(0待发布、1已发布、2已下架)", required = true)
    private Integer status;

    /**是否定时任务*/
    @NotNull(message = "是否定时任务不可为空")
    @ApiModelProperty(value = "是否定时任务", required = true)
    private Boolean isTimedJob;

    /**创建人*/
    @NotNull(message = "创建人不可为空")
    @ApiModelProperty(value = "创建人", required = true)
    private Long createBy;

    /**创建时间*/
    @NotNull(message = "创建时间不可为空")
    @ApiModelProperty(value = "创建时间", required = true)
    private Long createDate;

    /**修改人*/
    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long updateDate;

    /**定时时间*/
    @ApiModelProperty(value = "定时时间")
    private Long cron;

    /**客户端类型（发布位置）*/
    @NotNull(message = "客户端类型（发布位置）不可为空")
    @ApiModelProperty(value = "客户端类型（发布位置）", required = true)
    private String clientType;

    /**
     * 发布者
     */
    @ApiModelProperty(value = "发布者", required = true)
    private UserInfo publisher;

    /**发布时间*/
    @ApiModelProperty(value = "发布时间")
    private Long releaseDate;

    /**
     * 复制 model 到 entity
     * @return
     */
    public NewsBulletin convertToEntity(){
        NewsBulletinModel.ModelConvert modelConvert = new NewsBulletinModel.ModelConvert();
        NewsBulletin entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public NewsBulletinModel convertToModel(NewsBulletin entity){
        NewsBulletinModel.ModelConvert modelConvert = new NewsBulletinModel.ModelConvert();
        NewsBulletinModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<NewsBulletinModel, NewsBulletin> {
        @Override
        protected NewsBulletin doForward(NewsBulletinModel model) {
            NewsBulletin entity = new NewsBulletin();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected NewsBulletinModel doBackward(NewsBulletin entity) {
            NewsBulletinModel model = new NewsBulletinModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }
}
