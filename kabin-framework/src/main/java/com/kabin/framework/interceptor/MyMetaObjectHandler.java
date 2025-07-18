package com.kabin.framework.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kabin.common.core.domain.model.LoginUser;
import com.kabin.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "createTime", Date.class, new Date());
        strictInsertFill(metaObject, "createBy", String.class, String.valueOf(loadUserId()));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        strictUpdateFill(metaObject, "updateBy", String.class, String.valueOf(loadUserId()));
    }

    /**
     * 获取当前登录人的ID
     * @return  登录人ID
     */
    public Long loadUserId() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (loginUser != null) {
            return loginUser.getUserId();
        }
        return 1L;
    }
}