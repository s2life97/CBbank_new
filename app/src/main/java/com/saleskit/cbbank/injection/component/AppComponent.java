package com.saleskit.cbbank.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.saleskit.cbbank.data.DataManager;
import com.saleskit.cbbank.injection.ApplicationContext;
import com.saleskit.cbbank.injection.module.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();
}
