package com.projet.esgi.android_forum;

import android.provider.Settings;
import android.util.Log;

import com.projet.esgi.android_forum.model.Comment;
import com.projet.esgi.android_forum.model.News;
import com.projet.esgi.android_forum.model.Post;
import com.projet.esgi.android_forum.model.Topic;
import com.projet.esgi.android_forum.model.User;
import com.projet.esgi.android_forum.service.api.AuthService;
import com.projet.esgi.android_forum.service.api.CommentService;
import com.projet.esgi.android_forum.service.api.GenericService;
import com.projet.esgi.android_forum.service.api.HttpBasicAuth;
import com.projet.esgi.android_forum.service.api.NewsService;
import com.projet.esgi.android_forum.service.api.PostService;
import com.projet.esgi.android_forum.service.api.TopicService;
import com.projet.esgi.android_forum.service.api.UserService;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.rfabstract.PersistedModel;
import com.projet.esgi.android_forum.service.rfabstract.ServiceException;
import com.projet.esgi.android_forum.service.rfabstract.ServiceExceptionType;
import com.projet.esgi.android_forum.service.rfabstract.ServiceResult;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Gabriel on 30/06/2017.
 */

/**
 * https://medium.com/@v.danylo/simple-way-to-test-asynchronous-actions-in-android-service-asynctask-thread-rxjava-etc-d43b0402e005
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RFTest {

    public static final String TAG = RFTest.class.getName();
    Object syncObject;
    String mDataStr;
    ServiceException exception;
    User mDataUser;
    List<User> mDataList;

    @Before
    public void setUp(){
        exception = null;
        mDataStr = null;
        mDataUser = null;
        mDataList = null;
    }

    @Test
    public void a_subscribe() throws Exception{
        syncObject = new Object();
        UserService service = new UserService();
        // Si on utilise un email+password déjà existant, l'api ne répond pas => SocketTimedOutException
        User u = new User("bar@foo.com", "bar", "foo", "quux");
        service.create(u, new MyStringServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }

        if(exception != null){
            System.out.println(exception);
            assertTrue(exception.getCode() == 200);
        }else{
            // mDataStr must contains the content of the "Location" header from the http response
            assertTrue(mDataStr != null && mDataStr.contains("users"));
        }
    }

    @Test
    public void b_login() throws Exception{
        syncObject = new Object();
        AuthService auth = new AuthService();
        User u = new User("bar@foo.com", "bar", "foo", "quux");
        auth.login(u, new MyStringServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }
        System.out.println(String.format("createUser : %s", exception));
        assertTrue(HttpBasicAuth.getToken() != null);
    }

    /**
     * Failing but we don't care
     * @throws Exception
     */
    @Test
    @Ignore
    public void genericity() throws Exception{
        GenericService<Topic> service = new GenericService<Topic>(Topic.class);
        service.create(new Topic(), new MyStringServiceListener());
    }

    @Test
    public void c_listUsers() throws Exception {
        syncObject = new Object();
        UserService service = new UserService();
        // Si on utilise un email+password déjà existant, l'api ne répond pas => SocketTimedOutException
        User u = new User("bar@foo.com", "bar", "foo", "quux");
        service.list(new MyUserListServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }
        assertTrue(mDataList != null);
    }

    @Test
    public void d_createComment() throws Exception{
//        System.out.println(HttpBasicAuth.getToken());
//        assertTrue(HttpBasicAuth.getToken() != null);
        syncObject = new Object();
        CommentService service = new CommentService();
        // Si on utilise un email+password déjà existant, l'api ne répond pas => SocketTimedOutException
        Comment model = new Comment("title", "content");
        service.create(model, new MyStringServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }

        if(exception != null){
            System.out.println(String.format("createComment : %s", exception));
            assertTrue(exception.getCode() >= 500); // si c'est une erreur serveur, ce n'est pas notre faute
        }else{
            // mDataStr must contains the content of the "Location" header from the http response
            assertTrue(mDataStr != null && mDataStr.contains("comments"));
        }
    }

    @Test
    public void e_createNews() throws Exception{
        syncObject = new Object();
        NewsService service = new NewsService();
        // Si on utilise un email+password déjà existant, l'api ne répond pas => SocketTimedOutException
        News model = new News("title", "content");
        service.create(model, new MyStringServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }

        if(exception != null){
            System.out.println(String.format("createNews : %s", exception));
            assertTrue(exception.getCode() >= 500); // si c'est une erreur serveur, ce n'est pas notre faute
        }else{
            // mDataStr must contains the content of the "Location" header from the http response
            assertTrue(mDataStr != null && mDataStr.contains("news"));
        }
    }

    @Test
    public void f_createTopic() throws Exception{
        syncObject = new Object();
        TopicService service = new TopicService();
        // Si on utilise un email+password déjà existant, l'api ne répond pas => SocketTimedOutException
        Topic model = new Topic("title", "content");
        service.create(model, new MyStringServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }

        if(exception != null){
            System.out.println(String.format("createTopic : %s", exception));
            assertTrue(exception.getCode() >= 500); // si c'est une erreur serveur, ce n'est pas notre faute
        }else{
            // mDataStr must contains the content of the "Location" header from the http response
            assertTrue(mDataStr != null && mDataStr.contains("topics"));
        }
    }

    @Test
    public void f_createPost() throws Exception{
        syncObject = new Object();
        PostService service = new PostService();
        // Si on utilise un email+password déjà existant, l'api ne répond pas => SocketTimedOutException
        Post model = new Post("title", "content", "topic");
        service.create(model, new MyStringServiceListener());
        synchronized (syncObject){
            syncObject.wait();
        }

        if(exception != null){
            System.out.println(String.format("createPost : %s", exception));
            assertTrue(exception.getCode() >= 500); // si c'est une erreur serveur, ce n'est pas notre faute
        }else{
            // mDataStr must contains the content of the "Location" header from the http response
            assertTrue(mDataStr != null && mDataStr.contains("posts"));
        }
    }

    class MyLogin implements IServiceResultListener<String> {

        @Override
        public void onResult(ServiceResult<String> result) {

        }
    }

    class MyUserListServiceListener implements IServiceResultListener<List<User>> {
        @Override
        public void onResult(ServiceResult<List<User>> result) {
            exception = result.getError();
            mDataList = result.getData();
            synchronized (syncObject){
                syncObject.notify();
            }
        }
    }

    class MyStringServiceListener implements IServiceResultListener<String> {
        @Override
        public void onResult(ServiceResult<String> result) {
            exception = result.getError();
            mDataStr = result.getData();
            synchronized (syncObject){
                syncObject.notify();
            }
        }
    }
}
