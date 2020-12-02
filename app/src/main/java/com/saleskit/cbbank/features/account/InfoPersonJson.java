package com.saleskit.cbbank.features.account;

import java.util.List;

public class InfoPersonJson {

    /**
     * Data : {"Id":"417SG4pbtuyetdtb","FullName":"Đỗ Thị Bạch Tuyết","UserName":"tuyetdtb","Position":[{"DepartmentId":"417","DepartmentName":"PGD QUAN 12","BranchId":"040","BranchName":"Chi nhánh Sài Gòn","RegionId":"MSG","RegionName":"SÀI GÒN","PositionTitleId":"KSV4","PositionTitleName":"Kiểm soát viên bậc 4 (lương bậc 4)","BeginDate":"2018-10-02T09:47:42.053","IsLeaderRegion":false,"IsLeaderBranch":false,"IsLeaderDepartment":false}]}
     * Message : null
     * Success : true
     * Error : null
     */

    private DataBean Data;
    private Object Message;
    private boolean Success;
    private Object Error;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
        this.Message = Message;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public Object getError() {
        return Error;
    }

    public void setError(Object Error) {
        this.Error = Error;
    }

    public static class DataBean {
        /**
         * Id : 417SG4pbtuyetdtb
         * FullName : Đỗ Thị Bạch Tuyết
         * UserName : tuyetdtb
         * Position : [{"DepartmentId":"417","DepartmentName":"PGD QUAN 12","BranchId":"040","BranchName":"Chi nhánh Sài Gòn","RegionId":"MSG","RegionName":"SÀI GÒN","PositionTitleId":"KSV4","PositionTitleName":"Kiểm soát viên bậc 4 (lương bậc 4)","BeginDate":"2018-10-02T09:47:42.053","IsLeaderRegion":false,"IsLeaderBranch":false,"IsLeaderDepartment":false}]
         */

        private String Id;
        private String FullName;
        private String UserName;
        private List<PositionBean> Position;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public List<PositionBean> getPosition() {
            return Position;
        }

        public void setPosition(List<PositionBean> Position) {
            this.Position = Position;
        }

        public static class PositionBean {
            /**
             * DepartmentId : 417
             * DepartmentName : PGD QUAN 12
             * BranchId : 040
             * BranchName : Chi nhánh Sài Gòn
             * RegionId : MSG
             * RegionName : SÀI GÒN
             * PositionTitleId : KSV4
             * PositionTitleName : Kiểm soát viên bậc 4 (lương bậc 4)
             * BeginDate : 2018-10-02T09:47:42.053
             * IsLeaderRegion : false
             * IsLeaderBranch : false
             * IsLeaderDepartment : false
             */

            private String DepartmentId;
            private String DepartmentName;
            private String BranchId;
            private String BranchName;
            private String RegionId;
            private String RegionName;
            private String PositionTitleId;
            private String PositionTitleName;
            private String BeginDate;
            private boolean IsLeaderRegion;
            private boolean IsLeaderBranch;
            private boolean IsLeaderDepartment;

            public String getDepartmentId() {
                return DepartmentId;
            }

            public void setDepartmentId(String DepartmentId) {
                this.DepartmentId = DepartmentId;
            }

            public String getDepartmentName() {
                return DepartmentName;
            }

            public void setDepartmentName(String DepartmentName) {
                this.DepartmentName = DepartmentName;
            }

            public String getBranchId() {
                return BranchId;
            }

            public void setBranchId(String BranchId) {
                this.BranchId = BranchId;
            }

            public String getBranchName() {
                return BranchName;
            }

            public void setBranchName(String BranchName) {
                this.BranchName = BranchName;
            }

            public String getRegionId() {
                return RegionId;
            }

            public void setRegionId(String RegionId) {
                this.RegionId = RegionId;
            }

            public String getRegionName() {
                return RegionName;
            }

            public void setRegionName(String RegionName) {
                this.RegionName = RegionName;
            }

            public String getPositionTitleId() {
                return PositionTitleId;
            }

            public void setPositionTitleId(String PositionTitleId) {
                this.PositionTitleId = PositionTitleId;
            }

            public String getPositionTitleName() {
                return PositionTitleName;
            }

            public void setPositionTitleName(String PositionTitleName) {
                this.PositionTitleName = PositionTitleName;
            }

            public String getBeginDate() {
                return BeginDate;
            }

            public void setBeginDate(String BeginDate) {
                this.BeginDate = BeginDate;
            }

            public boolean isIsLeaderRegion() {
                return IsLeaderRegion;
            }

            public void setIsLeaderRegion(boolean IsLeaderRegion) {
                this.IsLeaderRegion = IsLeaderRegion;
            }

            public boolean isIsLeaderBranch() {
                return IsLeaderBranch;
            }

            public void setIsLeaderBranch(boolean IsLeaderBranch) {
                this.IsLeaderBranch = IsLeaderBranch;
            }

            public boolean isIsLeaderDepartment() {
                return IsLeaderDepartment;
            }

            public void setIsLeaderDepartment(boolean IsLeaderDepartment) {
                this.IsLeaderDepartment = IsLeaderDepartment;
            }
        }
    }
}
