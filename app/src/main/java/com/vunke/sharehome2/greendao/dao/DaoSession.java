package com.vunke.sharehome2.greendao.dao;

import android.database.sqlite.SQLiteDatabase;

import com.vunke.sharehome2.greendao.dao.bean.CallRecorders;
import com.vunke.sharehome2.greendao.dao.bean.Contact;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig contactDaoConfig;
    private final DaoConfig callRecordersDaoConfig;

    private final ContactDao contactDao;
    private final CallRecordersDao callRecordersDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        contactDaoConfig = daoConfigMap.get(ContactDao.class).clone();
        contactDaoConfig.initIdentityScope(type);

        callRecordersDaoConfig = daoConfigMap.get(CallRecordersDao.class).clone();
        callRecordersDaoConfig.initIdentityScope(type);

        contactDao = new ContactDao(contactDaoConfig, this);
        callRecordersDao = new CallRecordersDao(callRecordersDaoConfig, this);

        registerDao(Contact.class, contactDao);
        registerDao(CallRecorders.class, callRecordersDao);
    }
    
    public void clear() {
        contactDaoConfig.getIdentityScope().clear();
        callRecordersDaoConfig.getIdentityScope().clear();
    }

    public ContactDao getContactDao() {
        return contactDao;
    }

    public CallRecordersDao getCallRecordersDao() {
        return callRecordersDao;
    }

}