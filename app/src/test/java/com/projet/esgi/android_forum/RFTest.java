package com.projet.esgi.android_forum;

import android.provider.Settings;
import android.util.Log;

import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.model.Topic;
import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.api.AuthService;
import com.projet.esgi.android_forum.service.api.CommentService;
import com.projet.esgi.android_forum.service.api.GenericService;
import com.projet.esgi.android_forum.service.api.HttpBasicAuth;
import com.projet.esgi.android_forum.service.api.UserService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.ServiceException;
import com.projet.esgi.android_forum.service.rfabstract.ServiceExceptionType;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Gabriel on 30/06/2017.
 */

/**
 * https://medium.com/@v.danylo/simple-way-to-test-asynchronous-actions-in-android-service-asynctask-thread-rxjava-etc-d43b0402e005
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RFTest {

    public static final String TAG = RFTest.class.getName();
    Object syncObject;
    String mDataStr;
    ServiceException exception;
    User mDataUser;

    @Before
    public void setUp(){
        exception = null;
        mDataStr = null;
        mDataUser = null;
    }

    @Test
    public void subscribe() throws Exception{
        syncObject = new Object();
        UserService us = new UserService();
        // Si on utilise un email+password déjà existant, l'api ne répond pas => SocketTimedOutException
        User u = new User("bar@foo.com", "bar", "foo", "quux");
        us.create(u, new MyStringServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }

        if(exception != null){
            System.out.println(exception);
            assertTrue(exception.getCode() == 200);
        }else{
            assertTrue(mDataStr != null && mDataStr.contains("users"));
        }
    }

    @Test
    public void login() throws Exception{
        syncObject = new Object();
        AuthService auth = new AuthService();
        User u = new User("bar@foo.com", "bar", "foo", "quux");
        System.out.println(u.toString());
        auth.login(u, new MyStringServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }
        System.out.println(exception);
        assertTrue(HttpBasicAuth.getToken() != null);
    }

    /**
     * Failing but we don't care
     * @throws Exception
     */
    @Test
    public void genericity() throws Exception{
        GenericService<Topic> service = new GenericService<Topic>(Topic.class);
        service.create(new Topic(), new MyStringServiceListener());
    }

    @Test
    public void createComment() throws Exception{
        syncObject = new Object();
        CommentService service = new CommentService();
        // Si on utilise un email+password déjà existant, l'api ne répond pas => SocketTimedOutException
        Comment comment = new Comment();
        service.create(comment, new MyStringServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }

        if(exception != null){
            System.out.println(exception);
            assertTrue(exception.getCode() == 200);
        }else{
            assertTrue(mDataStr != null && mDataStr.contains("users"));
        }
    }

    class MyLogin implements IServiceResultListener<String> {

        @Override
        public void onResult(ServiceResult<String> result) {

        }
    }

    class MyStringServiceListener implements IServiceResultListener<String> {
        @Override
        public void onResult(ServiceResult<String> result) {
            System.out.println("onResult");
            exception = result.getError();
            mDataStr = result.getData();
            synchronized (syncObject){
                syncObject.notify();
            }
        }
    }
}
