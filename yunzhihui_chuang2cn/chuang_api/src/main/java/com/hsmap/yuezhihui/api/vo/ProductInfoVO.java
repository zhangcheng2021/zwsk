package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.product.ProductInfo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoVO {

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

    private String duration;
    private String groupSort;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
        this.batchName = batchName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        this.recommend = recommend;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getWorkCompany() {
        return workCompany;
    }

    public void setWorkCompany(String workCompany) {
        this.workCompany = workCompany;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public static List<ProductInfoVO> list2vo(List<ProductInfo> list){
        List<ProductInfoVO> voList = new ArrayList<>();
        for (ProductInfo bean:list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static ProductInfoVO obj2vo(ProductInfo bean){
        ProductInfoVO vo = new ProductInfoVO();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}
