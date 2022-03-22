package com.emrekara.finalproject.app.user.service.entityservice;

import com.emrekara.finalproject.app.gen.exceptions.GenBusinessException;
import com.emrekara.finalproject.app.gen.service.BaseEntityService;
import com.emrekara.finalproject.app.user.dao.UsUserDao;
import com.emrekara.finalproject.app.user.entity.UsUser;
import com.emrekara.finalproject.app.user.enums.UsrErrorMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsUserEntityService extends BaseEntityService<UsUser, UsUserDao> {
    public UsUserEntityService(UsUserDao dao) {
        super(dao);
    }

    public boolean existsUsUserByUserName(String userName){
        return getDao().existsUsUserByUserName(userName);
    }

    public Optional<UsUser> findByUserName(String userName){
        return getDao().findByUserName(userName);
    }

    public UsUser findUsUserByUserNameWithControl(String userName){

        Optional<UsUser> usUserOptional = getDao().findByUserName(userName);

        if(usUserOptional.isPresent()){
            return usUserOptional.get();
        }else{
            throw new GenBusinessException(UsrErrorMessage.USER_NOT_FOUND_ERROR);
        }

    }

}
