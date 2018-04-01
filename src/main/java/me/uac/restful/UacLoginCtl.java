package me.uac.restful;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.dragon.wrap.Wrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Title: UacLoginCtl. </p>
 * <p>Description 用户账户中心登录 </p>
 * @author dragon
 * @date 2018/3/30 下午5:35
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/uac/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "UacLoginCtl", tags = "用户账户中心登录接口", description = "用户账户中心登录接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacLoginCtl extends BaseController {

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(notes = "登录结果json", httpMethod = "POST", value = "登录接口")
    public Wrapper<?> doLogin() {
        return null;
    }
}
