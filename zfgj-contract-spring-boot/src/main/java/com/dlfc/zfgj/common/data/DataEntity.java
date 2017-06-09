package com.dlfc.zfgj.common.data;

import com.dlfc.admin.common.persistence.MyDataEntity;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.IdGen;
import com.dlfc.zfgj.entity.UsrUser;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by K on 2017/3/29.
 */
public abstract class DataEntity<T> extends MyDataEntity<T> {

    public void preInsert(UsrUser user) {
        if (!this.isNewRecord) {
            this.setId(IdGen.uuid());
        }

        if (StringUtils.isNotEmpty(user.getId())) {
            this.setModifyUser(user.getId());
            this.setCreateUser(user.getId());
            this.setModifyUserIdentity(user.getUserIdentity());
            this.setCreateUserIdentity(user.getUserIdentity());
        }

        this.setModifyTime(DateUtils.getSynchTime());
        this.setCreateTime(this.getModifyTime());
        this.setVersion(Integer.valueOf(0));
        this.setDeleteFlg(Short.valueOf((short) 0));
    }

    public void preUpdate(UsrUser user) {
        if (StringUtils.isNotEmpty(user.getId())) {
            this.setModifyUser(user.getId());
        }
        this.setModifyTime(DateUtils.getSynchTime());
    }
}
