package com.hsmap.yuezhihui.entity.product;

import com.hsmap.yuezhihui.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class ProductInfo extends BaseEntity implements Serializable {
    private Integer id;

    private Integer batchId;

    private String batchName;

    private Integer territoryId;

    private String territoryName;

    private String majorField;

    private Integer typeId;

    private String typeName;

    private String productName;

    private String userName;

    private String mobile;

    private String email;

    private Integer sex;

    private String recommend;

    private String birthday;

    private String nationality;

    private String position;

    private String schoolName;

    private String major;

    private String degree;

    private String workOverseas;

    private String homeTime;

    private String shaoTime;

    private String work;
    //推荐单位，如导入，取work_公司部分
    private String workCompany;

    private String patent;

    /**
     * 是否删除 0有效，-1删除
     */
    private Integer isDel;

    private Integer sort;

    private Date createTime;

    private String remark;

    private String duration;
    private String groupSort;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGroupSort() {
        return groupSort;
    }

    public void setGroupSort(String groupSort) {
        this.groupSort = groupSort;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName == null ? null : batchName.trim();
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
        this.territoryName = territoryName == null ? null : territoryName.trim();
    }

    public String getMajorField() {
        return majorField;
    }

    public void setMajorField(String majorField) {
        this.majorField = majorField == null ? null : majorField.trim();
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
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getWorkOverseas() {
        return workOverseas;
    }

    public void setWorkOverseas(String workOverseas) {
        this.workOverseas = workOverseas == null ? null : workOverseas.trim();
    }

    public String getHomeTime() {
        return homeTime;
    }

    public void setHomeTime(String homeTime) {
        this.homeTime = homeTime == null ? null : homeTime.trim();
    }

    public String getShaoTime() {
        return shaoTime;
    }

    public void setShaoTime(String shaoTime) {
        this.shaoTime = shaoTime == null ? null : shaoTime.trim();
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work == null ? null : work.trim();
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent == null ? null : patent.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getWorkCompany() {
        return workCompany;
    }

    public void setWorkCompany(String workCompany) {
        this.workCompany = workCompany;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", batchId=").append(batchId);
        sb.append(", batchName=").append(batchName);
        sb.append(", territoryId=").append(territoryId);
        sb.append(", territoryName=").append(territoryName);
        sb.append(", majorField=").append(majorField);
        sb.append(", typeId=").append(typeId);
        sb.append(", typeName=").append(typeName);
        sb.append(", productName=").append(productName);
        sb.append(", userName=").append(userName);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", sex=").append(sex);
        sb.append(", recommend=").append(recommend);
        sb.append(", birthday=").append(birthday);
        sb.append(", nationality=").append(nationality);
        sb.append(", position=").append(position);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", major=").append(major);
        sb.append(", degree=").append(degree);
        sb.append(", workOverseas=").append(workOverseas);
        sb.append(", homeTime=").append(homeTime);
        sb.append(", shaoTime=").append(shaoTime);
        sb.append(", work=").append(work);
        sb.append(", patent=").append(patent);
        sb.append(", isDel=").append(isDel);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
