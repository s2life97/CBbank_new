package com.saleskit.cbbank.util.rx.netmodel;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {


    /**
     * data : [{"customerId":159,"customerType":1,"identityNumber":"58585151518","phoneNumber":"044555555555","firstName":"10","lastName":"1","address":"cff","email":"a@gmail.com","countAll":27,"countAllMobilize":0,"countFinalMobilize":0,"countAllCredit":27,"countFinalCredit":0},{"customerId":160,"customerType":1,"identityNumber":"86888688","phoneNumber":"0985995595","firstName":"2","lastName":"10","address":"gg","email":"t@gmail.com","countAll":26,"countAllMobilize":4,"countFinalMobilize":0,"countAllCredit":22,"countFinalCredit":0},{"customerId":152,"customerType":0,"identityNumber":"98665594997","phoneNumber":"0655655888","firstName":"a","lastName":"aa","address":"hn","email":"hnnjhvv@gmail.com","countAll":1,"countAllMobilize":1,"countFinalMobilize":0,"countAllCredit":0,"countFinalCredit":0},{"customerId":153,"customerType":null,"identityNumber":"9866559499753355","phoneNumber":"0655655888","firstName":"aa","lastName":"a","address":"hn","email":"hnnjhvv@gmail.com","countAll":3,"countAllMobilize":0,"countFinalMobilize":0,"countAllCredit":3,"countFinalCredit":0},{"customerId":123,"customerType":1,"identityNumber":"64946461161","phoneNumber":"07816518161","firstName":"abc","lastName":"123","address":"hn","email":"tudt@buca.vn","countAll":18,"countAllMobilize":3,"countFinalMobilize":0,"countAllCredit":15,"countFinalCredit":0},{"customerId":125,"customerType":1,"identityNumber":"54849494","phoneNumber":"09876543164","firstName":"abc","lastName":"234","address":"hn","email":"tudt@buca.vn","countAll":17,"countAllMobilize":4,"countFinalMobilize":0,"countAllCredit":13,"countFinalCredit":0},{"customerId":126,"customerType":2,"identityNumber":"8484949","phoneNumber":"0978465451","firstName":"abc","lastName":"456","address":"djs","email":"tudt@buca.vn","countAll":6,"countAllMobilize":1,"countFinalMobilize":0,"countAllCredit":5,"countFinalCredit":0},{"customerId":154,"customerType":1,"identityNumber":"156688866","phoneNumber":"0865566855","firstName":"abc","lastName":"abc","address":"hn","email":"f@gmail.com","countAll":0,"countAllMobilize":0,"countFinalMobilize":0,"countAllCredit":0,"countFinalCredit":0},{"customerId":92,"customerType":1,"identityNumber":"123456789","phoneNumber":"0123456789","firstName":"abc","lastName":"def","address":"123","email":"123@buca.vn","countAll":2,"countAllMobilize":0,"countFinalMobilize":0,"countAllCredit":2,"countFinalCredit":0},{"customerId":155,"customerType":1,"identityNumber":"9566888","phoneNumber":"065599868","firstName":"ac","lastName":"ac","address":"hhh","email":"1@gmail.com","countAll":0,"countAllMobilize":0,"countFinalMobilize":0,"countAllCredit":0,"countFinalCredit":0}]
     * totalRecords : 174
     */

    private int totalRecords;
    private List<DataBean> data;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * customerId : 159
         * customerType : 1
         * identityNumber : 58585151518
         * phoneNumber : 044555555555
         * firstName : 10
         * lastName : 1
         * address : cff
         * email : a@gmail.com
         * countAll : 27
         * countAllMobilize : 0
         * countFinalMobilize : 0
         * countAllCredit : 27
         * countFinalCredit : 0
         */

        private int customerId;
        private int customerType;
        private String identityNumber;
        private String phoneNumber;
        private String firstName;
        private String lastName;
        private String address;
        private String email;
        private int countAll;
        private int countAllMobilize;
        private int countFinalMobilize;
        private int countAllCredit;
        private int countFinalCredit;

        private boolean isChosen;

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getCustomerType() {
            return customerType;
        }

        public void setCustomerType(int customerType) {
            this.customerType = customerType;
        }

        public String getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getCountAll() {
            return countAll;
        }

        public void setCountAll(int countAll) {
            this.countAll = countAll;
        }

        public int getCountAllMobilize() {
            return countAllMobilize;
        }

        public void setCountAllMobilize(int countAllMobilize) {
            this.countAllMobilize = countAllMobilize;
        }

        public int getCountFinalMobilize() {
            return countFinalMobilize;
        }

        public void setCountFinalMobilize(int countFinalMobilize) {
            this.countFinalMobilize = countFinalMobilize;
        }

        public int getCountAllCredit() {
            return countAllCredit;
        }

        public void setCountAllCredit(int countAllCredit) {
            this.countAllCredit = countAllCredit;
        }

        public int getCountFinalCredit() {
            return countFinalCredit;
        }

        public void setCountFinalCredit(int countFinalCredit) {
            this.countFinalCredit = countFinalCredit;
        }

        public boolean isChosen() {
            return isChosen;
        }

        public void setChosen(boolean chosen) {
            isChosen = chosen;
        }
    }
}
