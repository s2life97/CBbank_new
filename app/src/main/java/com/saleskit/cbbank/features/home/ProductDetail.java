package com.saleskit.cbbank.features.home;
import java.io.Serializable;
import java.util.List;
public class ProductDetail {
    /**
     * data : {"productId":1,"productName":"Bất động sản (BĐS)","productCode":"SP001","productDetail":"- Tỷ lệ cho vay tối đa lên tới 100% giá trị Tài sản bảo đảm\r\n- Chấp nhận nhiều loại TSBĐ: Giấy tờ có giá/Bất động sản/Phương tiện vận tải,","isActive":true,"productType":1,"productTypeName":"Cho vay","productCategoryId":131,"categoryName":"Gói sản phẩm cho vay bất động sản dành cho KHCN","detailInformations":[{"title":"Đối tượng khách hàng","content":"Khách hàng cá nhân đáp ứng theo quy định.","isBreakLine":true},{"title":"Mục đích vay","content":"- Vay mua nhà ở, đất ở/nhà để ở kết hợp cho thuê \r\n- Vay bù đắp tiền đã mua nhà ở, đất ở/nhà để ở kết hợp cho thuê\r\n- Vay xây mới/sửa chữa nhà ở/ nhà để ở kết hợp cho thuê","isBreakLine":true},{"title":"Mô tả sản phẩm","content":"- Tỷ lệ cho vay tối đa lên tới 100% giá trị Tài sản bảo đảm\r\n- Chấp nhận nhiều loại TSBĐ: Giấy tờ có giá/Bất động sản/Phương tiện vận tải,","isBreakLine":true},{"title":"Lãi suất","content":"Lãi suất cạnh tranh","isBreakLine":false},{"title":"Số tiền cho vay tối đa","content":"5 tỷ VNĐ","isBreakLine":false},{"title":"Thời hạn cho vay tối đa","content":"15 năm","isBreakLine":false},{"title":"Khuyến mãi, ưu đãi đặc biệt","content":"Phương thức trả lãi và nợ gốc linh hoạt\r\nKhách hàng được tư vấn về các sản phẩm dịch vụ khác của Ngân hàng","isBreakLine":true},{"title":"Điều kiện cho vay","content":"Thu nhập tối thiểu 5tr/tháng","isBreakLine":true},{"title":"Thời gian phê duyệt","content":"Thời gian phê duyệt hồ sơ nhanh chóng sau khi nhận đủ hồ sơ của KH","isBreakLine":true},{"title":"Tỉ lệ cho vay","content":"","isBreakLine":false}],"productFileModels":[{"fileId":200,"productId":1,"fileName":"ChoVayPhucVuPTNNNT.pdf","filePath":"/Files/Upload/Product/ChoVayPhucVuPTNNNT.pdf","fileExtension":".pdf","fileSize":226709,"description":"Hồ sơ chứng minh mục đích sử dụng vốn","loanProfileName":"Hồ sơ chứng minh mục đích sử dụng vốn"},{"fileId":201,"productId":1,"fileName":"ChoVayPhucVuPTNNNT(1).pdf","filePath":"/Files/Upload/Product/ChoVayPhucVuPTNNNT(1).pdf","fileExtension":".pdf","fileSize":226709,"description":"Hồ sơ pháp lý","loanProfileName":"Hồ sơ pháp lý"},{"fileId":202,"productId":1,"fileName":"ChoVayPhucVuPTNNNT(2).pdf","filePath":"/Files/Upload/Product/ChoVayPhucVuPTNNNT(2).pdf","fileExtension":".pdf","fileSize":226709,"description":"Giấy đề nghị vay vốn (nhận mẫu miễn phí tại các quầy giao dịch)","loanProfileName":"Giấy đề nghị vay vốn (nhận mẫu miễn phí tại các quầy giao dịch)"},{"fileId":203,"productId":1,"fileName":"ChoVayPhucVuPTNNNT(3).pdf","filePath":"/Files/Upload/Product/ChoVayPhucVuPTNNNT(3).pdf","fileExtension":".pdf","fileSize":226709,"description":"Hồ sơ tài sản bảo đảm","loanProfileName":"Hồ sơ tài sản bảo đảm"},{"fileId":204,"productId":1,"fileName":null,"filePath":null,"fileExtension":null,"fileSize":null,"description":"fghghgj","loanProfileName":"hfghg"}]}
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
         * productId : 1
         * productName : Bất động sản (BĐS)
         * productCode : SP001
         * productDetail : - Tỷ lệ cho vay tối đa lên tới 100% giá trị Tài sản bảo đảm
         - Chấp nhận nhiều loại TSBĐ: Giấy tờ có giá/Bất động sản/Phương tiện vận tải,
         * isActive : true
         * productType : 1
         * productTypeName : Cho vay
         * productCategoryId : 131
         * categoryName : Gói sản phẩm cho vay bất động sản dành cho KHCN
         * detailInformations : [{"title":"Đối tượng khách hàng","content":"Khách hàng cá nhân đáp ứng theo quy định.","isBreakLine":true},{"title":"Mục đích vay","content":"- Vay mua nhà ở, đất ở/nhà để ở kết hợp cho thuê \r\n- Vay bù đắp tiền đã mua nhà ở, đất ở/nhà để ở kết hợp cho thuê\r\n- Vay xây mới/sửa chữa nhà ở/ nhà để ở kết hợp cho thuê","isBreakLine":true},{"title":"Mô tả sản phẩm","content":"- Tỷ lệ cho vay tối đa lên tới 100% giá trị Tài sản bảo đảm\r\n- Chấp nhận nhiều loại TSBĐ: Giấy tờ có giá/Bất động sản/Phương tiện vận tải,","isBreakLine":true},{"title":"Lãi suất","content":"Lãi suất cạnh tranh","isBreakLine":false},{"title":"Số tiền cho vay tối đa","content":"5 tỷ VNĐ","isBreakLine":false},{"title":"Thời hạn cho vay tối đa","content":"15 năm","isBreakLine":false},{"title":"Khuyến mãi, ưu đãi đặc biệt","content":"Phương thức trả lãi và nợ gốc linh hoạt\r\nKhách hàng được tư vấn về các sản phẩm dịch vụ khác của Ngân hàng","isBreakLine":true},{"title":"Điều kiện cho vay","content":"Thu nhập tối thiểu 5tr/tháng","isBreakLine":true},{"title":"Thời gian phê duyệt","content":"Thời gian phê duyệt hồ sơ nhanh chóng sau khi nhận đủ hồ sơ của KH","isBreakLine":true},{"title":"Tỉ lệ cho vay","content":"","isBreakLine":false}]
         * productFileModels : [{"fileId":200,"productId":1,"fileName":"ChoVayPhucVuPTNNNT.pdf","filePath":"/Files/Upload/Product/ChoVayPhucVuPTNNNT.pdf","fileExtension":".pdf","fileSize":226709,"description":"Hồ sơ chứng minh mục đích sử dụng vốn","loanProfileName":"Hồ sơ chứng minh mục đích sử dụng vốn"},{"fileId":201,"productId":1,"fileName":"ChoVayPhucVuPTNNNT(1).pdf","filePath":"/Files/Upload/Product/ChoVayPhucVuPTNNNT(1).pdf","fileExtension":".pdf","fileSize":226709,"description":"Hồ sơ pháp lý","loanProfileName":"Hồ sơ pháp lý"},{"fileId":202,"productId":1,"fileName":"ChoVayPhucVuPTNNNT(2).pdf","filePath":"/Files/Upload/Product/ChoVayPhucVuPTNNNT(2).pdf","fileExtension":".pdf","fileSize":226709,"description":"Giấy đề nghị vay vốn (nhận mẫu miễn phí tại các quầy giao dịch)","loanProfileName":"Giấy đề nghị vay vốn (nhận mẫu miễn phí tại các quầy giao dịch)"},{"fileId":203,"productId":1,"fileName":"ChoVayPhucVuPTNNNT(3).pdf","filePath":"/Files/Upload/Product/ChoVayPhucVuPTNNNT(3).pdf","fileExtension":".pdf","fileSize":226709,"description":"Hồ sơ tài sản bảo đảm","loanProfileName":"Hồ sơ tài sản bảo đảm"},{"fileId":204,"productId":1,"fileName":null,"filePath":null,"fileExtension":null,"fileSize":null,"description":"fghghgj","loanProfileName":"hfghg"}]
         */
        private int productId;
        private String productName;
        private String productCode;
        private String productDetail;
        private boolean isActive;
        private int productType;
        private boolean hasCalculationFormula;
        private String productTypeName;
        private int productCategoryId;
        private String categoryName;
        private List<DetailInformationsBean> detailInformations;
        private List<ProductFileModelsBean> productFileModels;

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public boolean isHasCalculationFormula() {
            return hasCalculationFormula;
        }

        public void setHasCalculationFormula(boolean hasCalculationFormula) {
            this.hasCalculationFormula = hasCalculationFormula;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductDetail() {
            return productDetail;
        }

        public void setProductDetail(String productDetail) {
            this.productDetail = productDetail;
        }

        public boolean isIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public String getProductTypeName() {
            return productTypeName;
        }

        public void setProductTypeName(String productTypeName) {
            this.productTypeName = productTypeName;
        }

        public int getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(int productCategoryId) {
            this.productCategoryId = productCategoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<DetailInformationsBean> getDetailInformations() {
            return detailInformations;
        }

        public void setDetailInformations(List<DetailInformationsBean> detailInformations) {
            this.detailInformations = detailInformations;
        }

        public List<ProductFileModelsBean> getProductFileModels() {
            return productFileModels;
        }

        public void setProductFileModels(List<ProductFileModelsBean> productFileModels) {
            this.productFileModels = productFileModels;
        }

        public static class DetailInformationsBean implements Serializable {
            /**
             * title : Đối tượng khách hàng
             * content : Khách hàng cá nhân đáp ứng theo quy định.
             * isBreakLine : true
             */

            private String title;
            private String content;
            private boolean isBreakLine;
            private boolean isHighlight;

            public boolean isBreakLine() {
                return isBreakLine;
            }

            public void setBreakLine(boolean breakLine) {
                isBreakLine = breakLine;
            }

            public boolean isHighlight() {
                return isHighlight;
            }

            public void setHighlight(boolean highlight) {
                isHighlight = highlight;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public boolean isIsBreakLine() {
                return isBreakLine;
            }

            public void setIsBreakLine(boolean isBreakLine) {
                this.isBreakLine = isBreakLine;
            }
        }

        public static class ProductFileModelsBean {
            /**
             * fileId : 200
             * productId : 1
             * fileName : ChoVayPhucVuPTNNNT.pdf
             * filePath : /Files/Upload/Product/ChoVayPhucVuPTNNNT.pdf
             * fileExtension : .pdf
             * fileSize : 226709
             * description : Hồ sơ chứng minh mục đích sử dụng vốn
             * loanProfileName : Hồ sơ chứng minh mục đích sử dụng vốn
             */

            private int fileId;
            private int productId;
            private String fileName;
            private String filePath;
            private String fileExtension;
            private int fileSize;
            private String description;
            private String loanProfileName;

            public int getFileId() {
                return fileId;
            }

            public void setFileId(int fileId) {
                this.fileId = fileId;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            public String getFileExtension() {
                return fileExtension;
            }

            public void setFileExtension(String fileExtension) {
                this.fileExtension = fileExtension;
            }

            public int getFileSize() {
                return fileSize;
            }

            public void setFileSize(int fileSize) {
                this.fileSize = fileSize;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getLoanProfileName() {
                return loanProfileName;
            }

            public void setLoanProfileName(String loanProfileName) {
                this.loanProfileName = loanProfileName;
            }
        }
    }
}
