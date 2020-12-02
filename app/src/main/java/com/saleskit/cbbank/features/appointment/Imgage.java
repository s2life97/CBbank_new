package com.saleskit.cbbank.features.appointment;

import java.util.List;

public class Imgage {

    /**
     * data : {"uploadFileModels":[{"fileName":"4480d7dc-0a96-4bfe-bb8c-d5e25d3b8af4.exe","path":"/Files/Upload/AppointmentImage/4480d7dc-0a96-4bfe-bb8c-d5e25d3b8af4.exe","extension":".exe","size":374280,"success":true}],"total":1,"success":1}
     * totalRecords : 0
     */

    public DataBean data;
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
         * uploadFileModels : [{"fileName":"4480d7dc-0a96-4bfe-bb8c-d5e25d3b8af4.exe","path":"/Files/Upload/AppointmentImage/4480d7dc-0a96-4bfe-bb8c-d5e25d3b8af4.exe","extension":".exe","size":374280,"success":true}]
         * total : 1
         * success : 1
         */

        private int total;
        private int success;
        public List<UploadFileModelsBean> uploadFileModels;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSuccess() {
            return success;
        }

        public void setSuccess(int success) {
            this.success = success;
        }

        public List<UploadFileModelsBean> getUploadFileModels() {
            return uploadFileModels;
        }

        public void setUploadFileModels(List<UploadFileModelsBean> uploadFileModels) {
            this.uploadFileModels = uploadFileModels;
        }

        public static class UploadFileModelsBean {
            /**
             * fileName : 4480d7dc-0a96-4bfe-bb8c-d5e25d3b8af4.exe
             * path : /Files/Upload/AppointmentImage/4480d7dc-0a96-4bfe-bb8c-d5e25d3b8af4.exe
             * extension : .exe
             * size : 374280
             * success : true
             */

            private String fileName;
            private String path;
            private String extension;
            private int size;
            private boolean success;

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getExtension() {
                return extension;
            }

            public void setExtension(String extension) {
                this.extension = extension;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public boolean isSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }
        }
    }
}
