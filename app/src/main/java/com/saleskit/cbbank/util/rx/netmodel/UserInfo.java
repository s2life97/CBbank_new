package com.saleskit.cbbank.util.rx.netmodel;

import java.util.List;

public class UserInfo {

    /**
     * data : {"claims":[],"logins":[],"roles":[],"firstName":null,"lastName":null,"identityNumber":null,"dateOfBirth":null,"gender":null,"avatarUrl":null,"accountType":0,"email":"12@1","emailConfirmed":false,"passwordHash":"AIxFPbuwK88QPMyBEm4t8GegzfrFk/xdG33v6lra4fdWhEiAlmMbEoJMuebITbivEA==","securityStamp":"8e81d7ce-4ac0-49f9-b1b2-0e6d7bb92030","phoneNumber":null,"phoneNumberConfirmed":false,"twoFactorEnabled":false,"lockoutEndDateUtc":null,"lockoutEnabled":false,"accessFailedCount":0,"id":"b80ef7eb-5fb5-42e2-8ddd-222db5a822fe","userName":"12@1"}
     * totalRecords : 0
     */

    private DataBean data;
    private int totalRecords;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public static class DataBean {
        /**
         * claims : []
         * logins : []
         * roles : []
         * firstName : null
         * lastName : null
         * identityNumber : null
         * dateOfBirth : null
         * gender : null
         * avatarUrl : null
         * accountType : 0
         * email : 12@1
         * emailConfirmed : false
         * passwordHash : AIxFPbuwK88QPMyBEm4t8GegzfrFk/xdG33v6lra4fdWhEiAlmMbEoJMuebITbivEA==
         * securityStamp : 8e81d7ce-4ac0-49f9-b1b2-0e6d7bb92030
         * phoneNumber : null
         * phoneNumberConfirmed : false
         * twoFactorEnabled : false
         * lockoutEndDateUtc : null
         * lockoutEnabled : false
         * accessFailedCount : 0
         * id : b80ef7eb-5fb5-42e2-8ddd-222db5a822fe
         * userName : 12@1
         */

        private Object firstName;
        private Object lastName;
        private Object identityNumber;
        private Object dateOfBirth;
        private Object gender;
        private Object avatarUrl;
        private int accountType;
        private String email;
        private boolean emailConfirmed;
        private String passwordHash;
        private String securityStamp;
        private Object phoneNumber;
        private boolean phoneNumberConfirmed;
        private boolean twoFactorEnabled;
        private Object lockoutEndDateUtc;
        private boolean lockoutEnabled;
        private int accessFailedCount;
        private String id;
        private String userName;
        private List<?> claims;
        private List<?> logins;
        private List<?> roles;

        public Object getFirstName() {
            return firstName;
        }

        public void setFirstName(Object firstName) {
            this.firstName = firstName;
        }

        public Object getLastName() {
            return lastName;
        }

        public void setLastName(Object lastName) {
            this.lastName = lastName;
        }

        public Object getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(Object identityNumber) {
            this.identityNumber = identityNumber;
        }

        public Object getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Object dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(Object avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public int getAccountType() {
            return accountType;
        }

        public void setAccountType(int accountType) {
            this.accountType = accountType;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isEmailConfirmed() {
            return emailConfirmed;
        }

        public void setEmailConfirmed(boolean emailConfirmed) {
            this.emailConfirmed = emailConfirmed;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public String getSecurityStamp() {
            return securityStamp;
        }

        public void setSecurityStamp(String securityStamp) {
            this.securityStamp = securityStamp;
        }

        public Object getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Object phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public boolean isPhoneNumberConfirmed() {
            return phoneNumberConfirmed;
        }

        public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
            this.phoneNumberConfirmed = phoneNumberConfirmed;
        }

        public boolean isTwoFactorEnabled() {
            return twoFactorEnabled;
        }

        public void setTwoFactorEnabled(boolean twoFactorEnabled) {
            this.twoFactorEnabled = twoFactorEnabled;
        }

        public Object getLockoutEndDateUtc() {
            return lockoutEndDateUtc;
        }

        public void setLockoutEndDateUtc(Object lockoutEndDateUtc) {
            this.lockoutEndDateUtc = lockoutEndDateUtc;
        }

        public boolean isLockoutEnabled() {
            return lockoutEnabled;
        }

        public void setLockoutEnabled(boolean lockoutEnabled) {
            this.lockoutEnabled = lockoutEnabled;
        }

        public int getAccessFailedCount() {
            return accessFailedCount;
        }

        public void setAccessFailedCount(int accessFailedCount) {
            this.accessFailedCount = accessFailedCount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<?> getClaims() {
            return claims;
        }

        public void setClaims(List<?> claims) {
            this.claims = claims;
        }

        public List<?> getLogins() {
            return logins;
        }

        public void setLogins(List<?> logins) {
            this.logins = logins;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }
    }
}
