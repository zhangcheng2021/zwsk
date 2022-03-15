package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

public class SpecInfoVO {
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

    private String cardName;

    private String identityNo;

    private String bankName;

    private String cardNo;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
        this.company = company;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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
        this.titleName = titleName;
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
        this.specTypeName = specTypeName;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public static List<SpecInfoVO> list2vo(List<SpecInfo> list){
        List<SpecInfoVO> voList = new ArrayList<>();
        for (SpecInfo bean:list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static SpecInfoVO obj2vo(SpecInfo bean){
        SpecInfoVO vo = new SpecInfoVO();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}
