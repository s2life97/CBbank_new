package com.saleskit.cbbank.features.account;

import com.saleskit.cbbank.features.appointment.Branch;
import com.saleskit.cbbank.features.appointment.FilterAppointment;
import com.saleskit.cbbank.features.customer.add_new_customer.CreatResultBean;
import com.saleskit.cbbank.features.customer.add_new_customer.model.Category;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CreaditEvaluationInsert;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CustomerEnterpriseRespond;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CustomerPostProfile;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CustomerProfile;
import com.saleskit.cbbank.features.customer.add_new_customer.model.InsertMobilize;
import com.saleskit.cbbank.features.home.AtmResponse;
import com.saleskit.cbbank.features.home.BranchResponse;
import com.saleskit.cbbank.features.home.DepartmentResponse;
import com.saleskit.cbbank.features.home.Product;
import com.saleskit.cbbank.features.home.ProductDetail;
import com.saleskit.cbbank.features.kpi.KpiBranch;
import com.saleskit.cbbank.features.kpi.KpiDepartment;
import com.saleskit.cbbank.features.appointment.AppointDetail;
import com.saleskit.cbbank.features.appointment.AppointmentInsertUpdateModel;
import com.saleskit.cbbank.features.appointment.Imgage;
import com.saleskit.cbbank.features.kpi.MonthDetailEmployee;
import com.saleskit.cbbank.features.kpi.QuaterDetailEmployee;
import com.saleskit.cbbank.features.kpi.YearDetailEmployee;
import com.saleskit.cbbank.features.news.News;
import com.saleskit.cbbank.features.personal.CustomerAllow;
import com.saleskit.cbbank.features.personal.CustomerInfo;
import com.saleskit.cbbank.features.personal.CustomerInfomation;
import com.saleskit.cbbank.util.rx.netmodel.Customer;
import com.saleskit.cbbank.util.rx.netmodel.FilterModel;
import com.saleskit.cbbank.util.rx.netmodel.Token;
import com.saleskit.cbbank.util.rx.netmodel.UserInfo;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APIWebservices {
    //    @FormUrlEncoded
    @POST("cb-identity/user/authenticate")
    Call<Token> doLogin(@Body LoginModel loginModel);

    @GET("api/v1/user-account/get-user-info")
    Call<UserInfo> getUserInfo(@Query("Authorization") String authen);

    @POST("api/v1/customer-information/get-by-paged")
    Call<Customer> getListCustomer(@Body FilterModel filterModel);

    @GET("api/v1/customer-information/get-customer-by-paged")
    Call<Customer> getListCustomer(@Header("Authorization") String author, @QueryMap Map<String, String> options);

    @GET("api/v1/customer-information/get-customer-by-paged-permission-by-user-id")
    Call<Customer> getListPermissionCustomer(@Header("Authorization") String author, @QueryMap Map<String, String> options);

    @GET("api/v1/customer-information/get-customer-by-paged")
    Call<Customer> getSearchListCustomer(@Header("Authorization") String author, @QueryMap Map<String, String> options);

    @GET("api/v1/customer-information/get-detail-by-customer-id")
    Call<CustomerInfo> getCustomerInfo(@Header("Authorization") String author, @QueryMap Map<String, String> options);

    @GET("api/v1/appointment/get-paged-appointment-by-user-id")
    Call<Appointment> getListAppoint(@Header("Authorization") String author, @QueryMap Map<String, String> options);

    @GET("api/v1/appointment/get-by-id/{id}")
    Call<AppointDetail> getAppointDetail(@Header("Authorization") String author, @Path("id") int id);

    @GET("api/v1/product/get-all-by-type")
    Call<Product> getOwnProduct(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("api/v1/product/get-all")
    Call<Product> getAllproduct(@Header("Authorization") String author);

    @Multipart
    @POST("api/v1/appointment/upload-file")
    Call<Imgage> postImage(@Header("Authorization") String author, @Part MultipartBody.Part image);

    @GET("api/v1/appointment/get-recent-appointment-by-user-id")
    Call<Appointment> getNearestAppoint(@Header("Authorization") String author, @QueryMap HashMap<String, String> options);

    @GET("api/v1/appointment/get-paged-appointment-by-user-id")
    Call<Appointment> getAllAppoint(@Header("Authorization") String token, @QueryMap HashMap<String, String> options);

    @POST("api/v1/appointment/insert")
    Call<AppointmentResponse> addNewAppointment(@Header("Authorization") String token, @Body AppointmentInsertUpdateModel appointmentInsertUpdateModel);

    @PUT("api/v1/appointment/update")
    Call<AppointmentResponse> updateInfoAppoint(@Header("Authorization") String token, @Body AppointmentInsertUpdateModel appointmentInsertUpdateModel);

    @PUT("api/v1/appointment/update")
    Call<AppointmentResponse> updateAppointment(@Header("Authorization") String token, @Body AppointmentDetail.DataBean appointmentInsertUpdateModel);

    @GET("api/v1/dropdownlist/get-all-for-create-customer-profile")
    Call<Category> getAllCategory(@Header("Authorization") String author);

    @GET("api/v1/article/get-article-by-paged")
    Call<News> getAllNews(@Header("Authorization") String author, @QueryMap HashMap<String, String> options);

    @GET("api/v1/product/get-detail-by-id")
    Call<ProductDetail> getDetailProduct(@Header("Authorization") String author, @Query("productId") String id);

    @GET("api/v1/appointment/get-by-id/{appointmentId}")
    Call<AppointmentDetail> getAppointDetail(@Header("Authorization") String author, @Path("appointmentId") String id);

    @GET("api/v1/appointment/get-by-id/{appointmentId}")
    Call<Appointment> getAppointDetailbyId(@Header("Authorization") String author, @Path("appointmentId") String id);

    @POST("/api/v1/customer-profile/insert")
    Call<MessageBean> postCustomerProfile(@Header("Authorization") String author, @Body CustomerProfile customerProfile);

    @GET("api/v1/customer-information/get-by-id/{customerId}")
    Call<CustomerInfomation> getCustomerInfomation(@Header("Authorization") String author, @Path("customerId") String id);

    @GET("api/v1/customer-collateral/get-all-by-customer-profile-id")
    Call<Collateral> getListCollaterl(@Header("Authorization") String author, @QueryMap HashMap<String, String> options);

    @POST("api/v1/customer-information/insert")
    Call<MessageBean> postNewCustomer(@Header("Authorization") String author, @Body CustomerPostProfile customerPostProfile);

    @PUT("api/v1/customer-information/update")
    Call<MessageBean> updateCustomer(@Header("Authorization") String author, @Body CustomerPostProfile customerPostProfile);

    @PUT("api/v1/customer-profile/confirm")
    Call<MessageBean> confirmCreat(@Header("Authorization") String author, @Body ConfirmModel confirmModel);

    @GET("api/v1/dropdownlist/get-all-for-create-customer-profile-mobilize")
    Call<MobilizeEnum> getMobilizeEnums(@Header("Authorization") String author);

    @POST("api/v1/customer-profile/insert-mobilize")
    Call<MessageBean> postCustomerProfileMobilize(@Header("Authorization") String author, @Body InsertMobilize insertMobilize);

    @GET("api/v1/customer-profile/get-control-by-customer-profile-id")
    Call<ControlBean> getAllControl(@Header("Authorization") String author, @Query("customerProfileId") String id);

    @POST("api/v1/customer-profile/scroring-credit-record")
    Call<CreatResultBean> checkResult(@Header("Authorization") String author, @Body CreaditEvaluationInsert creaditEvaluationInsert);

    @POST("api/v1/customer-collateral/insert")
    Call<MessageBean> postNewCollaterl(@Header("Authorization") String author, @Body CollateralInsert collateral);

    @PUT("api/v1/customer-profile/change-process")
    Call<MessageBean> changeProcess(@Header("Authorization") String author, @Body ProcessBeam collateral);

    @POST("/api/v1/customer-profile/credit-confirm-step-3")
    Call<MessageBean> changeProcess3(@Header("Authorization") String author, @Query("customerProfileId") String id);

    @DELETE("api/v1/customer-collateral/delete")
    Call<MessageBean> delateItem(@Header("Authorization") String author, @Query("customerCollateralId") String id);

    @PUT("api/v1/customer-collateral/update")
    Call<MessageBean> update(@Header("Authorization") String author, @Body CollateralInsert collateralUpdate);

    @GET("api/v1/customer-profile-allowcation/get-current-allowcation-by-customer-profile-id")
    Call<CustomerAllow> getCustomerAllow(@Header("Authorization") String author, @Query("customerProfileId") String id);

    @GET("api/v1/user-information/get-lower-user")
    Call<LowerUser> getListLoweUser(@Header("Authorization") String author);

    @POST("api/v1/customer-profile-allowcation/insert-by-customer-profile-id-list")
    Call<MessageBean> getRework(@Header("Authorization") String author, @Body CustomerID customerID);

    @DELETE("api/v1/appointment/delete/{appointmentId}")
    Call<MessageBean> deleteAppoint(@Header("Authorization") String author, @Path("appointmentId") int id);

    @POST("api/v1/customer-profile/insert-credit")
    Call<MessageBean> insertCredit(@Header("Authorization") String author, @Body InsertCreditModel insertCreditModel);

    @GET("api/v1/article/get-by-id/{articleId}")
    Call<Article> getArticleDetail(@Header("Authorization") String author, @Path("articleId") int id);

    @POST("api/v1/customer-profile/credit-confirm-step-1")
    Call<CustomerInfoRespond> confirmStep1(@Header("Authorization") String author, @Body CustomerPostProfile customerPostProfile);

    @POST("api/v1/customer-profile/credit-confirm-step-1")
    Call<CodeJson> confirmStep1EnterPrise(@Header("Authorization") String author, @Body CustomerEnterpriseRespond customerPostProfile);

    //    @FormUrlEncoded
    @POST("api/v1/customer-profile/credit-confirm-step-5")
    Call<MessageJson> confirmFinalStep(@Header("Authorization") String author, @Query("customerProfileId") String customerProfileId);

    //    @FormUrlEncoded
    @POST("api/v1/customer-profile/credit-confirm-step-2")
    Call<MessageJson> confirmStep2(@Header("Authorization") String author, @Query("customerProfileId") String customerProfileId);

    @POST("api/v1/customer-profile/mobilize-confirm-step-1")
    Call<CodeJson> confirmMobilize(@Header("Authorization") String author, @Body InsertMobilize insertMobilize);

    @GET("api/v1/customer-profile/get-detail-by-id")
    Call<CustomerProfile> getCustomerProfie(@Header("Authorization") String author, @Query("customerProfileId") String customerProfileId);

    @DELETE("api/v1/customer-profile/delete")
    Call<MessageJson> deleteProfile(@Header("Authorization") String author, @Query("customerProfileId") String id);

    @POST("api/v1/customer-profile/credit-scoring")
    Call<CreatResultBean> checkEvaluadate(@Header("Authorization") String author, @Body CheckEvaluadate checkEvaluadate);

    @GET("kpi/ReportKpiDeparment/getByMonth")
    Call<KpiDepartment> getDepartment(@Header("Authorization") String author, @QueryMap() HashMap<String, String> option);

    @GET("kpi/ReportKpiBranch/getByMonth")
    Call<KpiBranch> getBranch(@Header("Authorization") String author, @QueryMap() HashMap<String, String> options);

    @GET("kpi/ReportKpiPersonal/getByMonth")
    Call<Employee> getEmployeeByMonth(@Header("Authorization") String author, @Query("deparmentId") String departmentId,
                                      @Query("year") int year, @Query("month") int month);

    @GET("kpi/ReportKpiRegion/getPageByMonth")
    Call<KPI> getRegionByMonth(@Header("Authorization") String author, @QueryMap() HashMap<String, String> option);

    @GET("hrm/Employee/getEmployeeInfo")
    Call<InfoPersonJson> getInfoPerson(@Header("Authorization") String author);

    @GET("kpi/ReportKpiRegion/getPageByYear")
    Call<KPI> getRegionByYear(@Header("Authorization") String author, @QueryMap() HashMap<String, String> option);

    @GET("kpi/ReportKpiRegion/getPageByQuarter")
    Call<KPI> getRegionByQuater(@Header("Authorization") String author, @QueryMap() HashMap<String, String> option);

    @GET("kpi/ReportKpiBranch/getPageByMonth")
    Call<KpiBranch> getBranchByMonth(@Header("Authorization") String author, @QueryMap() HashMap<String, String> option);

    @GET("kpi/ReportKpiBranch/getPageByYear")
    Call<KpiBranch> getBranchByYear(@Header("Authorization") String author, @QueryMap() HashMap<String, String> option);

    @GET("kpi/ReportKpiBranch/getPageByQuarter")
    Call<KpiBranch> getBranchByQuater(@Header("Authorization") String author, @QueryMap() HashMap<String, String> option);

    @GET("kpi/ReportKpiDeparment/getPageByMonth")
    Call<KpiDepartment> getDepartMentByMonth(@Header("Authorization") String author, @QueryMap() HashMap<String, String> option);

    @GET("kpi/ReportKpiDeparment/getPageByYear")
    Call<KpiDepartment> getDepartmentByYear(@Header("Authorization") String token, @QueryMap HashMap<String, String> option);

    @GET("kpi/ReportKpiDeparment/getPageByQuarter")
    Call<KpiDepartment> getDepartmentByQuater(@Header("Authorization") String token, @QueryMap HashMap<String, String> option);

    @GET("kpi/ReportKpiPersonal/getPageByMonth")
    Call<KpiEmplyee> getEmployeeByMonth(@Header("Authorization") String token, @QueryMap HashMap<String, String> option);

    @GET("kpi/ReportKpiPersonal/getPageByQuarter")
    Call<KpiEmplyee> getEmployeeByQuter(@Header("Authorization") String token, @QueryMap HashMap<String, String> option);

    @GET("kpi/ReportKpiPersonal/getPageByYear")
    Call<KpiEmplyee> getEmployeeByYear(@Header("Authorization") String token, @QueryMap HashMap<String, String> option);

    @GET("hrm/Region/getCurrentManagePage")
    Call<RegionJson> getAllRegion(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("hrm/Branch/getCurrentManagePageByRegionId")
    Call<TunJson> getBranchbyId(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("hrm/Department/getCurrentManagePageByBranchId")
    Call<TunDepartmentJson> getDepartmentById(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("hrm/Employee/GetCurrentManagePageByDepartmentId")
    Call<EmployeeJson> getAllEmplyee(@Header("Authorization") String auhtor, @QueryMap HashMap<String, String> option);

    @GET("kpi/ReportKpiPersonal/getDetailByMonth")
    Call<MonthDetailEmployee> getDetailEmployeeByMoth(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("kpi/ReportKpiPersonal/getDetailByYear")
    Call<YearDetailEmployee> getDetailEmployeeByYear(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("kpi/ReportKpiPersonal/getDetailByQuarter")
    Call<QuaterDetailEmployee> getDetailEmployeeByQuater(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("api/v1/customer-information/get-all-customer-by-user-id")
    Call<Customer> getReworkCustomers(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("hrm/Employee/getEmployeeInfo")
    Call<EmployeeInfomation> getEmployeeInfo(@Header("Authorization") String author);

    @GET("hrm/Branch/getCurrentManage")
    Call<Branch> getBranch(@Header("Authorization") String author);

    @GET("hrm/Department/getCurrentManageByBranchId")
    Call<Branch> getDepartmentByBranch(@Header("Authorization") String author, @Query("branchId") String branchId);

    @POST("api/v1/customer-profile-allowcation/insert-by-customer-id-list")
    Call<MessageJson> reworkEmployee(@Header("Authorization") String author, @Body CustomerID customerID);

    @GET("hrm/Employee/GetCurrentManageByDepartmentId")
    Call<EmployeeEnum> getEmployeeEnum(@Header("Authorization") String author, @Query("departmentId") String id);

    @GET("api/v1/appointment/get-paged-appointment-belong-period-by-user-id")
    Call<FilterAppointment> getFilterAppoint(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("hrm/Branch/getall")
    Call<Branch> getAllBranch(@Header("Authorization") String athor);

    @GET("hrm/Department/GetByBranchId")
    Call<Branch> getAllDepartMent(@Header("Authorization") String author, @Query("branchId") String branchId);

    @GET("api/v1/customer-collateral/get-property-type")
    Call<PropertyType> getProperty(@Header("Authorization") String author, @Query("productId") int productId);

    @GET("api/v1/customer-collateral/get-property-form")
    Call<PropertyForm> getPropertyForm(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("api/v1/interest-rate-table/get-all")
    Call<InterestMenu> getInterestMenu(@Header("Authorization") String author);

    @GET("api/v1/interest-rate-table/get-detail")
    Call<InterestDetail> getIntersetDetail(@Header("Authorization") String author, @Query("interestRateTableId") String id);

    @GET("api/v1/appointment/get-recent-appointment-by-customer-id")
    Call<Appointment> getNearestAppointByCustomerId(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("api/v1/appointment/get-paged-appointment-by-customer-id")
    Call<Appointment> getAppointmentByCustomerId(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("api/v1/notification/get-paged-notification")
    Call<NotificationJson> getListNoti(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @POST("api/v1/customer-profile-allowcation/insert-by-customer-profile-id-list-search")
    Call<MessageBean> rePickUserLookup(@Header("Authorization") String author, @Body CustomerID customerID);

    @GET("api/v1/notification/read-notification")
    Call<MessageBean> readNoti(@Header("Authorization") String author, @Query("notificationId") int id);

    @GET("api/v1/notification/hide-notification")
    Call<MessageBean> hideNoti(@Header("Authorization") String author, @Query("notificationId") int id);

    @GET("api/v1/notification/get-notification")
    Call<DetailNoti> getDetailNoti(@Header("Authorization") String author, @Query("notificationId") int id);

    @POST("cb-identity/user/authenticate")
    Call<Token> postToken(@Body LoginModel loginMode, @Query("deviceToken") String token);

    @GET("api/v1/notification/get-count-unread-notification")
    Call<Datajson> getCountUnreadMess(@Header("Authorization") String author);

    @GET("api/v1/report/get-report")
    Call<Datajson> getReportFile(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @POST("cb-identity/user/logout")
    Call<MessageBean> doLogOut(@Header("Authorization") String auhtor);

    @GET("api/v1/transaction-location/get-atm-by-paged")
    Call<AtmResponse> getAllAtm(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("api/v1/transaction-location/get-branch-transaction-by-paged")
    Call<BranchResponse> getAllBranch(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);

    @GET("api/v1/transaction-location/get-department-transaction-by-paged")
    Call<DepartmentResponse> getDepartmentByid(@Header("Authorization") String author, @QueryMap HashMap<String, String> option);
}

