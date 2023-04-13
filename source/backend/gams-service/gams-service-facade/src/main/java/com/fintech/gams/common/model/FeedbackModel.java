package com.fintech.gams.common.model;

import com.fintech.gams.bean.GenericEntity;
import com.fintech.gams.common.entity.Feedback;
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
 * @date 2019-12-20 13:42
 * @Description
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "问题反馈")
public class FeedbackModel extends GenericEntity {

    /**1.离线反馈,2.在线客服问题*/
    @NotNull(message = "1.离线反馈,2.在线客服问题不可为空")
    @ApiModelProperty(value = "1.离线反馈,2.在线客服问题", required = true)
    private String type;

    /**语言(en_US,zh_CN)*/
    @NotNull(message = "语言(en_US,zh_CN)不可为空")
    @ApiModelProperty(value = "语言(en_US,zh_CN)", required = true)
    private String lang;

    /**状态(0待解决、1已解决)*/
    @NotNull(message = "状态(0待解决、1已解决)不可为空")
    @ApiModelProperty(value = "状态(0待解决、1已解决)", required = true)
    private Byte status;

    /**姓名*/
    @ApiModelProperty(value = "姓名")
    private String name;

    /**邮箱*/
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**手机号*/
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**回答*/
    @ApiModelProperty(value = "回答")
    private String answer;

    /**反馈内容*/
    @NotNull(message = "反馈内容不可为空")
    @ApiModelProperty(value = "反馈内容", required = true)
    private String content;

    /**创建人*/
    @NotNull(message = "创建人不可为空")
    @ApiModelProperty(value = "创建人", required = true)
    private String createBy;

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


    /**
     * 复制 model 到 entity
     * @return
     */
    public Feedback convertToEntity(){
        FeedbackModel.ModelConvert modelConvert = new FeedbackModel.ModelConvert();
        Feedback entity = modelConvert.convert(this);
        return entity;
    }

    /**
     * 复制 entity 到 model
     * @param entity
     * @return
     */
    public FeedbackModel convertToModel(Feedback entity){
        FeedbackModel.ModelConvert modelConvert = new FeedbackModel.ModelConvert();
        FeedbackModel model = modelConvert.reverse().convert(entity);
        return model;
    }

    private class ModelConvert extends Converter<FeedbackModel, Feedback> {
        @Override
        protected Feedback doForward(FeedbackModel model) {
            Feedback entity = new Feedback();
            BeanUtils.copyProperties(model, entity);
            return entity;
        }

        @Override
        protected FeedbackModel doBackward(Feedback entity) {
            FeedbackModel model = new FeedbackModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        }
    }

}
