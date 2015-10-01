package com.fh.taolijie.controller.restful;

import cn.fh.security.credential.Credential;
import com.fh.taolijie.component.ResponseText;
import com.fh.taolijie.constant.ErrorCode;
import com.fh.taolijie.service.acc.impl.PhoneValidationService;
import com.fh.taolijie.utils.Constants;
import com.fh.taolijie.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 短信验证码
 * Created by whf on 9/24/15.
 */
@RestController
@RequestMapping("/api/user/code")
public class ValidationCodeCtr {
    @Autowired
    private PhoneValidationService codeService;

    /**
     * 向用户发送短信
     * @return
     */
    @RequestMapping(value = "/sms", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText sendSMS(@RequestParam String mobile,
                                HttpServletRequest req) {
        // 登陆检查
        Credential credential = SessionUtils.getCredential(req);
        if (null == credential) {
            return new ResponseText(ErrorCode.NOT_LOGGED_IN);
        }

        codeService.genSMSValidationCode(credential.getId(), mobile);
        return new ResponseText();
    }

    /**
     * 请求一个验证码
     * @return
     */
    @RequestMapping(value = "/web", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    public ResponseText fetchValidationCode(HttpServletRequest req) {

        Credential credential = SessionUtils.getCredential(req);
        String code = codeService.genWebValidationCode(credential.getId());

        return new ResponseText(code);
    }
}
