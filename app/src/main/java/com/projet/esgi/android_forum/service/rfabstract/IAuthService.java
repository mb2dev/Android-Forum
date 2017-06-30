package com.projet.esgi.android_forum.service.rfabstract;

import com.projet.esgi.android_forum.model.User;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IAuthService {
    void login(User user, final IServiceResultListener<String> resultListener);
    void subscribe(User user, final IServiceResultListener<String> resultListener);
}
