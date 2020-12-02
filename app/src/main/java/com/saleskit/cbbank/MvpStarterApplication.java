package com.saleskit.cbbank;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.saleskit.cbbank.features.database.DaoMaster;
import com.saleskit.cbbank.features.database.DaoSession;
import com.facebook.stetho.Stetho;
import com.saleskit.cbbank.injection.component.AppComponent;
import com.saleskit.cbbank.injection.component.DaggerAppComponent;
import com.saleskit.cbbank.injection.module.AppModule;
import com.saleskit.cbbank.injection.module.NetworkModule;
import com.orhanobut.hawk.Hawk;
import com.singhajit.sherlock.core.Sherlock;
import com.tspoon.traceur.Traceur;

import org.greenrobot.greendao.database.Database;

import timber.log.Timber;

public class MvpStarterApplication extends MultiDexApplication {

    private AppComponent appComponent;
    private DaoSession daoSession;

    public static MvpStarterApplication get(Context context) {
        return (MvpStarterApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        Hawk.init(this).build();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
//            LeakCanary.install(this);
            Sherlock.init(this);
            Traceur.enableLogging();
        }
//        NotifyUtils.getInstance().init(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .networkModule(new NetworkModule(this, BuildConfig.POKEAPI_API_URL))
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
