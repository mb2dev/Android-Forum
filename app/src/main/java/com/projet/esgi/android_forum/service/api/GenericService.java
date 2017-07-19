package com.projet.esgi.android_forum.service.api;

import com.projet.esgi.android_forum.service.retrofit.IRFGenericService;
import com.projet.esgi.android_forum.service.retrofit.RFHelper;
import com.projet.esgi.android_forum.service.retrofit.Session;
import com.projet.esgi.android_forum.service.rfabstract.IGenericService;
import com.projet.esgi.android_forum.service.rfabstract.IPersistedModel;
import com.projet.esgi.android_forum.service.rfabstract.IServiceResultListener;
import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.rfabstract.PersistedModel;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Gabriel on 30/06/2017.
 */

public class GenericService<T extends IRFGeneric> implements IGenericService<PersistedModel> {

    private static String TOKEN = null;
    private IRFGenericService<PersistedModel> mRfService;
    private IRFGenericService<PersistedModel> getRfService(){
        if(mRfService == null) {

            mRfService = Session.getDefault().create(IRFGenericService.class);
        }
        return mRfService;
    }

    private RFHelper<PersistedModel> mRFHelper;
    private RFHelper<PersistedModel> getRFHelper(){
        if(mRFHelper == null)
            mRFHelper = new RFHelper<PersistedModel>();
        return mRFHelper;
    }

    Class<IRFGenericService<PersistedModel>> clazz;

    public GenericService(Class clazz){
        this.clazz = clazz;
    }

    @Override
    public void create(PersistedModel model, IServiceResultListener<String> resultListener) {
       // getRFHelper().getDefaultCreate(getRfService().create(model.getModelNameForUrlPath(), model), resultListener,model);
    }

    @Override
    public void read(PersistedModel model, IServiceResultListener<PersistedModel> resultListener) {
        String modelID = ""+model.getId();
        Call<PersistedModel> call = getRfService().read(model.getModelNameForUrlPath(), modelID);
        getRFHelper().getDefaultRead(call, resultListener);
    }

    @Override
    public void delete(PersistedModel model, IServiceResultListener<Boolean> resultListener) {

    }

    @Override
    public void list(IServiceResultListener<List<PersistedModel>> resultListener) {

    }

    @Override
    public void update(PersistedModel model, IServiceResultListener<Boolean> resultListener) {

    }

}
