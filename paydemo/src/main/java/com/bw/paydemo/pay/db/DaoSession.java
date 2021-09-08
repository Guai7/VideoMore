package com.bw.paydemo.pay.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.bw.paydemo.pay.entity.UserMoney;

import com.bw.paydemo.pay.db.UserMoneyDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userMoneyDaoConfig;

    private final UserMoneyDao userMoneyDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userMoneyDaoConfig = daoConfigMap.get(UserMoneyDao.class).clone();
        userMoneyDaoConfig.initIdentityScope(type);

        userMoneyDao = new UserMoneyDao(userMoneyDaoConfig, this);

        registerDao(UserMoney.class, userMoneyDao);
    }
    
    public void clear() {
        userMoneyDaoConfig.clearIdentityScope();
    }

    public UserMoneyDao getUserMoneyDao() {
        return userMoneyDao;
    }

}
