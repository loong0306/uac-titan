package me.uac.restful;

import lombok.extern.slf4j.Slf4j;
import me.dragon.exception.BusinessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * <p>Title: BaseController. </p>
 * @author dragon
 * @date 2018/3/29 下午10:42
 */
@Slf4j
public class BaseController {

    /**
     * <p>Title: handleBindingResult. </p>
     * <p>Hibernate Validator校验结果处理 </p>
     * @param bindingResult
     * @author dragon
     * @date 2018/3/29 下午10:42
     * @return void
     */
    protected void handleBindingResult(BindingResult bindingResult) throws Exception {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (allErrors.isEmpty()) {
            return;
        }
        String defaultMessage = allErrors.get(0).getDefaultMessage();
        throw new BusinessException(defaultMessage);
    }

}
