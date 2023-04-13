package com.fintech.gams.controller.common;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.consts.GlobalConst;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UploadController
 * <p>File: UploadController.java </p>
 * <p>Title: UploadController </p>
 * <p>Description: UploadController </p>
 * <p>Copyright: Copyright (c) 2019-06-06</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(description = "文件上传控制器")
public class UploadController extends GenericController
{

}
