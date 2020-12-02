package com.saleskit.cbbank.features.appointment;

import java.io.Serializable;
import java.util.List;

public class AppointmentInsertUpdateModel implements Serializable {

    /**
     * AppointmentId : 0
     * CustomerName : string
     * IdentityNumber : string
     * PhoneNumber : string
     * Email : string
     * AppointmentAddress : string
     * ExpectedProduct : string
     * Description : string
     * AppointmentTime : 2019-09-19T08:02:30.278Z
     * ResultStatus : 0
     * ResultDescription : string
     * NextAppointmentId : 0
     * AppointmentImages : [{"AppointmentImageId":0,"AppointmentId":0,"FileName":"string","Path":"string","Extension":"string","Size":0}]
     */

    private int AppointmentId;
    private String CustomerName;
    private String IdentityNumber;
    private String PhoneNumber;
    private String Email;
    private String CustomerId;
    private String AppointmentAddress;
    private String ExpectedProduct;
    private String Description;
    private String AppointmentTime;
    private int ResultStatus;
    private String ResultDescription;
    private int NextAppointmentId;
    private List<AppointmentImagesBean> AppointmentImages;

    public AppointmentInsertUpdateModel(int appointmentId, String customerName, String identityNumber, String phoneNumber, String email,
                                        String appointmentAddress, String expectedProduct, String description, String customerId) {
        AppointmentId = appointmentId;
        CustomerName = customerName;
        IdentityNumber = identityNumber;
        PhoneNumber = phoneNumber;
        Email = email;
        AppointmentAddress = appointmentAddress;
        ExpectedProduct = expectedProduct;
        Description = description;
        CustomerId = customerId;
    }

    public AppointmentInsertUpdateModel(int appointmentId, String customerName,
                                        String identityNumber, String phoneNumber,
                                        String email, String appointmentAddress, String expectedProduct,
                                         String appointmentTime,
                                        int resultStatus, String resultDescription , String customerId) {
        AppointmentId = appointmentId;
        CustomerName = customerName;
        IdentityNumber = identityNumber;
        PhoneNumber = phoneNumber;
        Email = email;
        AppointmentAddress = appointmentAddress;
        ExpectedProduct = expectedProduct;
        AppointmentTime = appointmentTime;
        ResultStatus = resultStatus;
        ResultDescription = resultDescription;
        CustomerId = customerId;
    }

    public int getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(int AppointmentId) {
        this.AppointmentId = AppointmentId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getIdentityNumber() {
        return IdentityNumber;
    }

    public void setIdentityNumber(String IdentityNumber) {
        this.IdentityNumber = IdentityNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAppointmentAddress() {
        return AppointmentAddress;
    }

    public void setAppointmentAddress(String AppointmentAddress) {
        this.AppointmentAddress = AppointmentAddress;
    }

    public String getExpectedProduct() {
        return ExpectedProduct;
    }

    public void setExpectedProduct(String ExpectedProduct) {
        this.ExpectedProduct = ExpectedProduct;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(String AppointmentTime) {
        this.AppointmentTime = AppointmentTime;
    }

    public int getResultStatus() {
        return ResultStatus;
    }

    public void setResultStatus(int ResultStatus) {
        this.ResultStatus = ResultStatus;
    }

    public String getResultDescription() {
        return ResultDescription;
    }

    public void setResultDescription(String ResultDescription) {
        this.ResultDescription = ResultDescription;
    }

    public int getNextAppointmentId() {
        return NextAppointmentId;
    }

    public void setNextAppointmentId(int NextAppointmentId) {
        this.NextAppointmentId = NextAppointmentId;
    }

    public List<AppointmentImagesBean> getAppointmentImages() {
        return AppointmentImages;
    }

    public void setAppointmentImages(List<AppointmentImagesBean> AppointmentImages) {
        this.AppointmentImages = AppointmentImages;
    }

    public static class AppointmentImagesBean {
        /**
         * AppointmentImageId : 0
         * AppointmentId : 0
         * FileName : string
         * Path : string
         * Extension : string
         * Size : 0
         */

        private int AppointmentImageId;
        private int AppointmentId;
        private String FileName;
        private String Path;
        private String Extension;
        private int Size;

        public AppointmentImagesBean(int appointmentImageId, int appointmentId, String fileName, String path, String extension, int size) {
            AppointmentImageId = appointmentImageId;
            AppointmentId = appointmentId;
            FileName = fileName;
            Path = path;
            Extension = extension;
            Size = size;
        }

        public int getAppointmentImageId() {
            return AppointmentImageId;
        }

        public void setAppointmentImageId(int AppointmentImageId) {
            this.AppointmentImageId = AppointmentImageId;
        }

        public int getAppointmentId() {
            return AppointmentId;
        }

        public void setAppointmentId(int AppointmentId) {
            this.AppointmentId = AppointmentId;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public String getExtension() {
            return Extension;
        }

        public void setExtension(String Extension) {
            this.Extension = Extension;
        }

        public int getSize() {
            return Size;
        }

        public void setSize(int Size) {
            this.Size = Size;
        }
    }
}
