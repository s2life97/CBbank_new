package com.saleskit.cbbank.features.customer.add_new_customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.customer.TransferCustomerEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewCustomerActivity extends AppCompatActivity {
    private static final String TAG = "AddNewCustomerActivity";
    public static boolean isFromHome;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_step_1)
    TextView tvStep1;
    @BindView(R.id.v_step_1)
    View vStep1;
    @BindView(R.id.tv_step_1_des)
    TextView tvStep1Des;
    @BindView(R.id.tv_step_2)
    TextView tvStep2;
    @BindView(R.id.v_step_2)
    View vStep2;
    @BindView(R.id.tv_step_2_des)
    TextView tvStep2Des;
    @BindView(R.id.tv_step_3)
    TextView tvStep3;
    @BindView(R.id.v_step_3)
    View vStep3;
    @BindView(R.id.tv_step_3_des)
    TextView tvStep3Des;
    @BindView(R.id.tv_step_4)
    TextView tvStep4;
    @BindView(R.id.v_step_4)
    View vStep4;
    @BindView(R.id.tv_step_4_des)
    TextView tvStep4Des;
    @BindView(R.id.tv_step_5)
    TextView tvStep5;
    @BindView(R.id.v_step_6)
    View vStep6;
    @BindView(R.id.tv_step_5_des)
    TextView tvStep5Des;
    @BindView(R.id.v_step_5)
    View vStep5;
    @BindView(R.id.rl_step_5)
    RelativeLayout rlStep5;
    @BindView(R.id.ll_steps)
    LinearLayout llSteps;

    public static CustomViewPager viewPager;
    @BindView(R.id.ll_icon_2)
    LinearLayout llIcon2;
    @BindView(R.id.ll_icon_3)
    LinearLayout llIcon3;
    @BindView(R.id.ll_icon_4)
    LinearLayout llIcon4;
    @BindView(R.id.ll_icon_5)
    LinearLayout llIcon5;
    @BindView(R.id.viewpager_customer)
    CustomViewPager viewpagerCustomer;
    private FragmentChangedEvent fragmentChangedEvent;

    private boolean finishedAllSteps;
    private int productID;
    public static boolean hasFormular;
    private boolean canEdit;
    private int process;

    @OnClick({R.id.tv_step_1, R.id.tv_step_2, R.id.tv_step_3, R.id.tv_step_4, R.id.tv_step_5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_step_1: {
                viewPager.setCurrentItem(0);
                break;
            }
            case R.id.tv_step_2: {
                viewPager.setCurrentItem(1);
                break;
            }
            case R.id.tv_step_3: {
                viewPager.setCurrentItem(2);
                break;
            }
            case R.id.tv_step_4: {
                viewPager.setCurrentItem(3);
                break;
            }
            case R.id.tv_step_5: {
                viewPager.setCurrentItem(4);
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_customer);
        ButterKnife.bind(this);


        Intent i = getIntent();
        productID = i.getIntExtra(Constants.PRODUCT_ID, 0);

        viewPager = findViewById(R.id.viewpager_customer);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(5);
//        if (AddNewCustomerActivity.hasFormular) {
//            viewPager.setOffscreenPageLimit(5);
//        } else {
//            viewPager.setOffscreenPageLimit(4);
//        }
        viewPager.disableScroll(true);

        ivBack.setOnClickListener(v -> onBackPressed());
        tvStep3.setClickable(false);
        tvStep4.setClickable(false);
        tvStep5.setClickable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Subscribe
    public void onFragmentChanged(FragmentChangedEvent fragmentChangedEvent) {
        this.fragmentChangedEvent = fragmentChangedEvent;
        tvTitle.setText(fragmentChangedEvent.title);

        rlStep5.setVisibility(fragmentChangedEvent.stepIndex != 5 ? View.GONE : View.VISIBLE);

        tvStep1Des.setVisibility(fragmentChangedEvent.stepIndex == 1 ? View.VISIBLE : View.GONE);
        tvStep2Des.setVisibility(fragmentChangedEvent.stepIndex == 2 ? View.VISIBLE : View.GONE);
        tvStep3Des.setVisibility(fragmentChangedEvent.stepIndex == 3 ? View.VISIBLE : View.GONE);
        tvStep4Des.setVisibility(fragmentChangedEvent.stepIndex == 4 ? View.VISIBLE : View.GONE);
        tvStep5Des.setVisibility(fragmentChangedEvent.stepIndex == 5 ? View.VISIBLE : View.GONE);

        tvStep1.setSelected(fragmentChangedEvent.stepIndex > 1);
        tvStep2.setSelected(fragmentChangedEvent.stepIndex > 2);
        tvStep3.setSelected(fragmentChangedEvent.stepIndex > 3);
        tvStep4.setSelected(fragmentChangedEvent.stepIndex > 4);
        tvStep5.setSelected(fragmentChangedEvent.stepIndex > 5);

        vStep1.setSelected(fragmentChangedEvent.stepIndex >= 1);
        vStep2.setSelected(fragmentChangedEvent.stepIndex >= 2);
        vStep3.setSelected(fragmentChangedEvent.stepIndex >= 3);
        vStep4.setSelected(fragmentChangedEvent.stepIndex >= 4);
        vStep5.setSelected(fragmentChangedEvent.stepIndex == 5);
        vStep6.setSelected(fragmentChangedEvent.stepIndex == 5);

        if (finishedAllSteps) return;
        if (canEdit) {
            tvStep2.setClickable((fragmentChangedEvent.stepIndex == 1 && AddCustomerFragment.canNext) || fragmentChangedEvent.stepIndex == 3 || fragmentChangedEvent.stepIndex == 4 || fragmentChangedEvent.stepIndex == 5);
            tvStep3.setClickable((fragmentChangedEvent.stepIndex == 2 && CreditEvaluationFragment.canNext) || fragmentChangedEvent.stepIndex == 4 || fragmentChangedEvent.stepIndex == 5);
            tvStep4.setClickable(fragmentChangedEvent.stepIndex == 3 && CollateralFragment.numberAdd > 0 || fragmentChangedEvent.stepIndex == 5);
            tvStep5.setClickable(fragmentChangedEvent.stepIndex == 4 && RangeCreditFragment.canNext);
        } else {
            if (hasFormular) {
                tvStep2.setClickable(process > 1);
                tvStep3.setClickable(process > 2);
                tvStep4.setClickable(process > 3);
                tvStep5.setClickable(process > 4);
            } else {
                tvStep3.setClickable(process > 1);
                tvStep4.setClickable(process > 2);
                tvStep5.setClickable(process > 3);
            }
        }
    }

    @Override
    public void onBackPressed() {
        try {
            switch (fragmentChangedEvent.stepIndex) {
                case 1: {
                    super.onBackPressed();
                    break;
                }
                case 2: {
                    viewPager.setCurrentItem(0);
                    break;
                }
                case 3: {
                    if (hasFormular) {
                        viewPager.setCurrentItem(1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                    break;
                }
                case 4: {
                    viewPager.setCurrentItem(2);
                    break;
                }
                case 5: {
                    viewPager.setCurrentItem(3);
                    break;
                }
            }
        } catch (Exception e){}


    }

//    @Subscribe
//    public void onBackPersonal(BackPersonalEvent backPersonalEvent) {
//        finish();
//        overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
//    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
//            if (AddNewCustomerActivity.hasFormular) {
//                return 5;
//            } else {
//                return 4;
//            }
            return 5;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new AddCustomerFragment();
                case 1:
//                    if (AddNewCustomerActivity.hasFormular) {
                    return new CreditEvaluationFragment();
//                    } else {
//                        return new CollateralFragment();
//                    }

                case 2:
//                    if (AddNewCustomerActivity.hasFormular) {
                    return new CollateralFragment();
//                    } else {
//                        return new RangeCreditFragment();
//                    }
                case 3:
//                    if (AddNewCustomerActivity.hasFormular) {
                    return new RangeCreditFragment();
//                    } else {
//                        return new CustomerCompletedFragment();
//                    }
                case 4:
//                    if (AddNewCustomerActivity.hasFormular) {
                    return new CustomerCompletedFragment();
//                    } else {
//                        return null;
//                    }
                default:
                    return null;
            }
        }

    }

    @Subscribe
    public void onReceiveFinalEvent(FinalEvent finalEvent) {
        finishedAllSteps = true;
        tvStep1.setClickable(true);
        tvStep2.setClickable(true);
        tvStep3.setClickable(true);
        tvStep4.setClickable(true);
        tvStep5.setClickable(true);
//        viewPager.disableScroll(false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true)
    public void onReceiveProduct(TransferCustomerEvent transferCustomerEvent) {
        process = transferCustomerEvent.process;
        canEdit = transferCustomerEvent.canEdit;
        hasFormular = transferCustomerEvent.hasFormular;
        if (canEdit) {
            if (hasFormular) {
                if (process == 0) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(process - 1);
                }
            } else {
                if (process == 0 || process == 1) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(process);
                }
            }
        } else {
//            viewPager.disableScroll(false);
            viewPager.setCurrentItem(0);

        }
        if (!canEdit) {
            if (hasFormular) {
                switch (process) {
                    case 1:
                        llIcon2.setVisibility(View.GONE);
                        llIcon3.setVisibility(View.GONE);
                        llIcon4.setVisibility(View.GONE);
                        llIcon5.setVisibility(View.GONE);
                        break;
                    case 2:
                        llIcon3.setVisibility(View.GONE);
                        llIcon4.setVisibility(View.GONE);
                        llIcon5.setVisibility(View.GONE);
                        break;
                    case 3:
                        llIcon4.setVisibility(View.GONE);
                        llIcon5.setVisibility(View.GONE);
                        break;
                    case 4:
                        llIcon5.setVisibility(View.GONE);
                        break;
                }
            } else {
                switch (process) {
                    case 1:
                        llIcon2.setVisibility(View.GONE);
                        llIcon3.setVisibility(View.GONE);
                        llIcon4.setVisibility(View.GONE);
                        llIcon5.setVisibility(View.GONE);
                        break;
                    case 2:
                        llIcon4.setVisibility(View.GONE);
                        llIcon5.setVisibility(View.GONE);
                        break;
                    case 3:
                        llIcon5.setVisibility(View.GONE);
                        break;
                }
            }
        }


        if (!hasFormular) {
            tvStep3.setText("2");
            tvStep4.setText("3");
            tvStep5.setText("4");
            tvStep2.setVisibility(View.GONE);
            tvStep2Des.setVisibility(View.GONE);
            vStep2.setVisibility(View.GONE);
        }

    }

}
