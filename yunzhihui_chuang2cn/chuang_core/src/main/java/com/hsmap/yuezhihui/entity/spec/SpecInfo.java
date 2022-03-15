package com.hsmap.yuezhihui.entity.spec;

import com.hsmap.yuezhihui.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class SpecInfo extends BaseEntity implements Serializable {
    private Integer id;

    private String userName;

    private String mobile;

    private String age;

    private Integer sex;

    private String company;

    private String rank;

    private Integer titleId;

    private String titleName;

    private Integer territoryId;

    private String territoryName;

    private Integer specTypeId;

    private String specTypeName;

    private String subdivision;

    private Integer level;

    private Integer isDel;

    private Integer sort;

    private Date createTime;

    private String remark;

    private String cardName;

    private String identityNo;

    private String bankName;

    private String cardNo;
    private String signUrl;
    //项目打分,冗余
    private String recordScore;

    private static final long serialVersionUID = 1L;

    public String getRecordScore() {
        return recordScore;
    }

    public void setRecordScore(String recordScore) {
        this.recordScore = recordScore;
    }

    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName == null ? null : titleName.trim();
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

    public Integer getSpecTypeId() {
        return specTypeId;
    }

    public void setSpecTypeId(Integer specTypeId) {
        this.specTypeId = specTypeId;
    }

    public String getSpecTypeName() {
        return specTypeName;
    }

    public void setSpecTypeName(String specTypeName) {
        this.specTypeName = specTypeName == null ? null : specTypeName.trim();
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision == null ? null : subdivision.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo == null ? null : identityNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", mobile=").append(mobile);
        sb.append(", age=").append(age);
        sb.append(", sex=").append(sex);
        sb.append(", company=").append(company);
        sb.append(", rank=").append(rank);
        sb.append(", titleId=").append(titleId);
        sb.append(", titleName=").append(titleName);
        sb.append(", territoryId=").append(territoryId);
        sb.append(", territoryName=").append(territoryName);
        sb.append(", specTypeId=").append(specTypeId);
        sb.append(", specTypeName=").append(specTypeName);
        sb.append(", subdivision=").append(subdivision);
        sb.append(", level=").append(level);
        sb.append(", isDel=").append(isDel);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", cardName=").append(cardName);
        sb.append(", identityNo=").append(identityNo);
        sb.append(", bankName=").append(bankName);
        sb.append(", cardNo=").append(cardNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
