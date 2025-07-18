package com.kabin.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.kabin.common.core.controller.BaseController;
import com.kabin.common.core.domain.AjaxResult;
import com.kabin.common.core.domain.model.RegisterBody;
import com.kabin.common.utils.StringUtils;
import com.kabin.framework.web.service.SysRegisterService;
import com.kabin.system.service.ISysConfigService;

/**
 * 注册验证
 *
 * @author kabin
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
