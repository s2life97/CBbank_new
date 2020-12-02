package com.saleskit.cbbank.injection.component;

import dagger.Subcomponent;

import com.saleskit.cbbank.features.customer.add_new_customer.AddCustomerFragment;
import com.saleskit.cbbank.features.customer.add_new_customer.CollateralFragment;
import com.saleskit.cbbank.features.customer.add_new_customer.CreditEvaluationFragment;
import com.saleskit.cbbank.features.home.fragment.SlideMenuFragment;
import com.saleskit.cbbank.injection.PerFragment;
import com.saleskit.cbbank.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(CollateralFragment collateralFragment);
    void inject(CreditEvaluationFragment creditEvaluationFragment);
    void inject(AddCustomerFragment addCustomerFragment);
    void inject(SlideMenuFragment slideMenuFragment);
}
