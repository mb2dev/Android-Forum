package com.projet.esgi.android_forum.service.rfabstract;

import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;

import java.util.List;

/**
 * Created by Gabriel on 28/06/2017.
 */

public interface IUserService {
    void create(User model, IServiceResultListener<String> resultListener);
    void read(String modelID, IServiceResultListener<User> resultListener);
    void list(IServiceResultListener<List<User>> resultListener);
}
