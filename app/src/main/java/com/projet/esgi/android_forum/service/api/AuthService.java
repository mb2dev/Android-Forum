package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.retrofit.IRFAuthService;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.IAuthService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;

/**
 * Created by Gabriel on 28/06/2017.
 */

public class AuthService implements IAuthService {

    private IRFAuthService mRfService;
    private IRFAuthService getmRfService(){
        if(mRfService == null)
            mRfService = Session.getDefault().create(IRFAuthService.class);
        return mRfService;
    }

    @Override
    public void login(String email, String password, final IServiceResultListener<String> resultListener) {

    }

    @Override
    public void subscribe(User user, final IServiceResultListener<String> resultListener) {

    }
}
