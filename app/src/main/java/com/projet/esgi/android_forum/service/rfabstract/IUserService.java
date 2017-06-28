package com.projet.esgi.android_forum.service.rfabstract;

import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IUserService {
    void create(User user, IServiceResultListener<String> resultListener);
    void read(String userID, IServiceResultListener<User> resultListener);
}