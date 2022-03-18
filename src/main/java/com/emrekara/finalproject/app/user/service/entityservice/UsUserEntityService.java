package com.emrekara.finalproject.app.user.service.entityservice;

import com.emrekara.finalproject.app.gen.service.BaseEntityService;
import com.emrekara.finalproject.app.user.dao.UsUserDao;
import com.emrekara.finalproject.app.user.entity.UsUser;
import org.springframework.stereotype.Service;

@Service
public class UsUserEntityService extends BaseEntityService<UsUser, UsUserDao> {
    public UsUserEntityService(UsUserDao dao) {
        super(dao);
    }
}
