package com.saleskit.cbbank.features.account;

import java.util.List;

public class LowerUser {

    /**
     * data : [{"id":"0ec63328-675a-4f03-901e-b902332f6ed0","username":"56756765@45345","firstName":"A","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn A"},{"id":"322794b4-d7db-446e-8295-cf29a2a333f3","username":"admin@cbbank.vn","firstName":"Admin","lastName":"CbBank","fullName":"CbBank Admin"},{"id":"0f834ec9-f858-4f2d-bff4-b1df45ff981d","username":"567563765@45345","firstName":"B","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn B"},{"id":"2c850b93-4344-4778-9d95-f048005ed9f6","username":"hoaict@buca.vn","firstName":"C","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn C"},{"id":"3fb512c7-beb3-4549-8774-caeea9321629","username":"hungtd123@buca.com","firstName":"D","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn D"},{"id":"527abee0-d9ad-4f66-94b2-7c43db6ed39f","username":"dqtung2312@gmail.com","firstName":"E","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn E"},{"id":"5394016c-bab3-473c-8906-32e96dab3471","username":"tudt@buca.vn","firstName":"F","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn F"},{"id":"941b3250-7505-4103-af62-b76b020308a6","username":"aaa@gmail.com","firstName":"G","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn G"},{"id":"98242421-1fea-4471-9e33-14bd319c6c3c","username":"duchung.1510@gmail.com","firstName":"H","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn H"},{"id":"b80ef7eb-5fb5-42e2-8ddd-222db5a822fe","username":"12@1","firstName":"I","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn I"},{"id":"b9133b3f-4f05-48cf-a9a1-c6d8930cb81c","username":"hungtd@sada","firstName":"K","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn K"},{"id":"b9cf1304-cbe6-40e2-afe9-7ee988a0314c","username":"phongnh@buca.com","firstName":"L","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn L"},{"id":"c2021153-a647-47aa-a99b-d7cd846f6ccb","username":"1@1","firstName":"M","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn M"},{"id":"dca19280-7d93-4eb2-bd8e-400e8e1a3430","username":"1@45345","firstName":"N","lastName":"Nguyễn Văn","fullName":"Nguyễn Văn N"},{"id":"e69649f1-8c2f-4c2e-93bc-314c39b93c51","username":"tudt1@buca.vn","firstName":"O","lastName":"1","fullName":"1 O"}]
     * totalRecords : 0
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

    public static class DataBean {
        /**
         * id : 0ec63328-675a-4f03-901e-b902332f6ed0
         * username : 56756765@45345
         * firstName : A
         * lastName : Nguyễn Văn
         * fullName : Nguyễn Văn A
         */

        private String id;
        private String username;
        private String firstName;
        private String lastName;
        private String fullName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }
}
