package me.uac.restful;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.dragon.exception.BusinessException;
import me.dragon.wrap.WrapMapper;
import me.dragon.wrap.Wrapper;
import me.uac.annotation.BusinessLog;
import me.uac.annotation.PrintParam;
import me.uac.enums.UacExceptionEnums;
import me.uac.model.dto.req.UacRegisterReqDTO;
import me.uac.service.UacRegisterService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * <p>Title: UacRegisterCtl. </p>
 * <p>Description 用户账户中心用户注册 </p>
 * @author dragon
 * @date 2018/4/8 上午10:45
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/uac/auth/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "UacRegisterCtl", tags = "用户账户中心注册接口", description = "用户账户中心注册接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacRegisterCtl extends BaseController {

    @Resource
    private UacRegisterService uacRegisterService;

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(notes = "注册结果json", httpMethod = "POST", value = "用户注册接口")
    @BusinessLog(logInfo = "用户注册接口")
    public Wrapper<?> doRegister(@ApiParam(name = "uacRegisterReqDTO", value = "用户注册请求参数")
                                     @Valid @RequestBody UacRegisterReqDTO uacRegisterReqDTO, BindingResult bindingResult) {
        try {
            Integer registerStatus = uacRegisterService.doRegister(uacRegisterReqDTO);
            if (registerStatus <= 0) {
                throw new BusinessException(UacExceptionEnums.UAC_REGISTER_ERROR_10006.code(), UacExceptionEnums.UAC_REGISTER_ERROR_10006.msg());
            }
        } catch (BusinessException e) {
            log.error("用户账户中心注册接口, 出现异常={}", e.getMessage(), e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        } catch (Exception ex) {
            log.error("用户账户中心注册接口, 出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, ex.getMessage());
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
    }
}
