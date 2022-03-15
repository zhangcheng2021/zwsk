package com.hsmap.yuezhihui.entity.product;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.hsmap.yuezhihui.base.entity.BaseEntity;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProductInfoExcelDto extends BaseRowModel {
    /*
    0 姓名*
    1 性别*
    2 出生年月
    3 国籍
    4 政治面貌
    5 毕业学校
    6 专业
    7 学位
    8 海外工作单位及职位
    9 回国时间
    10 来绍时间
    11 国内工作单位及职务
    12 项目*
    13 专业领域*
    14 专业方向*
    15 专利情况
    16 创业/创新*
    17 推荐地
    18 联系电话*
    19 邮箱*
    20 备注
*/
    @ExcelProperty(index = 0)
    private String userName;

    @ExcelProperty(index = 1)
    private String sex;

    @ExcelProperty(index = 2)
    private String birthday;

    private Integer territoryId;

    @ExcelProperty(index = 13)
    private String territoryName;

    @ExcelProperty(index = 14)
    private String majorField;

    private Integer typeId;

    @ExcelProperty(index = 16)
    private String typeName;

    @ExcelProperty(index = 12)
    private String productName;

    @ExcelProperty(index = 18)
    private String mobile;

    @ExcelProperty(index = 19)
    private String email;

    @ExcelProperty(index = 17)
    private String recommend;

    @ExcelProperty(index = 3)
    private String nationality;

    @ExcelProperty(index = 4)
    private String position;

    @ExcelProperty(index = 5)
    private String schoolName;

    @ExcelProperty(index = 6)
    private String major;

    @ExcelProperty(index = 7)
    private String degree;

    @ExcelProperty(index = 8)
    private String workOverseas;

    @ExcelProperty(index = 9)
    private String homeTime;

    @ExcelProperty(index = 10)
    private String shaoTime;

    @ExcelProperty(index = 11)
    private String work;

    @ExcelProperty(index = 15)
    private String patent;

    @ExcelProperty(index = 20)
    private String remark;

    public String check(List<CoreProjectType> projectTypeList, List<CoreTerritory> territoryList) {
        StringBuilder errs = new StringBuilder();
        //姓名 *
        if (this.userName == null || this.userName.trim().length() == 0) {
            errs.append("姓名未填写").append(",");
        }
        if (this.userName != null)
            this.userName = this.userName.trim();
        //性别 *
        if (this.sex == null || this.sex.trim().length() == 0) {
            errs.append("性别未填写").append(",");
        }
        if (this.sex != null)
            this.sex = this.sex.trim();
        if (!("男".equals(this.sex) || "女".equals(this.sex))) {
            errs.append("性别只能填写男/女").append(",");
        }
        //项目 *
        if (this.productName == null || this.productName.trim().length() == 0) {
            errs.append("项目未填写").append(",");
        }
        if (this.productName != null)
            this.productName = this.productName.trim();
        //专业领域 *
        if (this.territoryName == null || this.territoryName.trim().length() == 0) {
            errs.append("专业领域未填写").append(",");
        } else {
            for (CoreTerritory territory : territoryList) {
                if (territory.getName().equals(this.territoryName.trim())) {
                    this.territoryId = territory.getId();
                    break;
                }
            }
            if (this.territoryId == null) {
                errs.append("专业领域未匹配到ID").append(",");
            }
        }
        //专业方向 *
        if (this.majorField == null || this.majorField.trim().length() == 0) {
            errs.append("专业方向未填写").append(",");
        }
        if (this.majorField != null)
            this.majorField = this.majorField.trim();
        //非空校验,人才类型,创业创新 *
        if (this.typeName == null || this.typeName.trim().length() == 0) {
            errs.append("创业创新未填写").append(",");
        } else {
            for (CoreProjectType type : projectTypeList) {
                if (type.getName().equals(this.typeName.trim())) {
                    this.typeId = type.getId();
                    break;
                }
            }
            if (this.typeId == null) {
                errs.append("创业创新未匹配到ID").append(",");
            }
        }
        //联系电话 *
        if (this.mobile == null || this.mobile.trim().length() == 0) {
            errs.append("联系电话未填写").append(",");
        }
        if (this.mobile != null)
            this.mobile = this.mobile.trim();
        //邮箱 *
        if (this.email == null || this.email.trim().length() == 0) {
            errs.append("邮箱未填写").append(",");
        }
        if (this.email != null)
            this.email = this.email.trim();

        return errs.toString();
    }

    public ProductInfo toEntiry(Integer batchId) {
        ProductInfo info = new ProductInfo();
        info.setBatchId(batchId);
        info.setUserName(this.userName);
        info.setSex("男".equals(this.sex) ? 1 : 0);
        info.setBirthday(this.birthday);
        info.setTerritoryId(this.territoryId);
        info.setMajorField(this.majorField);
        info.setTypeId(this.typeId);
        info.setTypeName(this.typeName);
        info.setProductName(this.productName);
        info.setMobile(this.mobile);
        info.setEmail(this.email);
        info.setRecommend(this.recommend);
        info.setNationality(this.nationality);
        info.setPosition(this.position);
        info.setSchoolName(this.schoolName);
        info.setMajor(this.major);
        info.setDegree(this.degree);
        info.setWorkOverseas(this.workOverseas);
        info.setHomeTime(this.homeTime);
        info.setShaoTime(this.shaoTime);
        info.setPatent(this.patent);
        info.setRemark(this.remark);
        //获得申请单位
        splitWork(info);
        return info;
    }

    private void splitWork(ProductInfo info) {
        if (this.work != null && this.work.trim().length() > 0) {
            this.work = this.work.trim();
            info.setWork(this.work);
            //先判断逗号
            int i = -1;
            i = this.work.indexOf(",");
            if (i > -1) {
                String c = this.work.substring(0, i);
                info.setWorkCompany(c);
                return;
            }
            //空格
            i = this.work.indexOf(" ");
            if (i > -1) {
                String c = this.work.substring(0, i);
                info.setWorkCompany(c);
                return;
            }
            //中文逗号
            i = this.work.indexOf("，");
            if (i > -1) {
                String c = this.work.substring(0, i);
                info.setWorkCompany(c);
                return;
            }
            //斜线/
            i = this.work.indexOf("/");
            if (i > -1) {
                String c = this.work.substring(0, i);
                info.setWorkCompany(c);
                return;
            }
            //公司
            i = this.work.indexOf("公司");
            if (i > -1) {
                String c = this.work.substring(0, i + 2);
                info.setWorkCompany(c);
                return;
            }
            //最后等于work，没法区分
            info.setWorkCompany(this.work);
        } else {
            info.setWorkCompany("");
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(Integer territoryId) {
        this.territoryId = territoryId;
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(String territoryName) {
        this.territoryName = territoryName;
    }

    public String getMajorField() {
        return majorField;
    }

    public void setMajorField(String majorField) {
        this.majorField = majorField;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getWorkOverseas() {
        return workOverseas;
    }

    public void setWorkOverseas(String workOverseas) {
        this.workOverseas = workOverseas;
    }

    public String getHomeTime() {
        return homeTime;
    }

    public void setHomeTime(String homeTime) {
        this.homeTime = homeTime;
    }

    public String getShaoTime() {
        return shaoTime;
    }

    public void setShaoTime(String shaoTime) {
        this.shaoTime = shaoTime;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ProductInfoExcelDto{" +
                "userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", territoryId='" + territoryId + '\'' +
                ", territoryName='" + territoryName + '\'' +
                ", majorField='" + majorField + '\'' +
                ", typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", productName='" + productName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", recommend='" + recommend + '\'' +
                ", nationality='" + nationality + '\'' +
                ", position='" + position + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", major='" + major + '\'' +
                ", degree='" + degree + '\'' +
                ", workOverseas='" + workOverseas + '\'' +
                ", homeTime='" + homeTime + '\'' +
                ", shaoTime='" + shaoTime + '\'' +
                ", work='" + work + '\'' +
                ", patent='" + patent + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
