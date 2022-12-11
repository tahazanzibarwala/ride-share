package com.halifaxcarpool.customer.database.dao;

import com.halifaxcarpool.commons.business.beans.User;

public abstract class IUserDao {

    public abstract void registerUser(User user);

    public abstract boolean updateUser(User user);

}
