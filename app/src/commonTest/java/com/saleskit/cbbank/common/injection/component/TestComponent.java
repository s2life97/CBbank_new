package com.saleskit.cbbank.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.saleskit.cbbank.common.injection.module.ApplicationTestModule;
import com.saleskit.cbbank.injection.component.AppComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends AppComponent {
}
