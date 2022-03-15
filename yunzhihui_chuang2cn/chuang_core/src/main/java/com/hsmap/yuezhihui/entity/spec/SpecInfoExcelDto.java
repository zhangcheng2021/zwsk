package com.hsmap.yuezhihui.entity.spec;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.hsmap.yuezhihui.base.entity.BaseEntity;
import com.hsmap.yuezhihui.entity.core.CoreSpecType;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import com.hsmap.yuezhihui.entity.core.CoreTitle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SpecInfoExcelDto extends BaseRowModel {
    /*
     0 姓名*
     1 年龄*
     2 性别
     3 单位
     4 职务
     5 职称
     6 联系电话
     7 专业领域
     8 细分领域
     9 专家类别
     10 评价星级
     11 持卡人姓名
     12 身份证号码
     13 银行卡号
     14 开户行
     15 备注
 */
    @ExcelProperty(index = 0)
    private String userName;

    @ExcelProperty(index = 1)
    private String age;

    @ExcelProperty(index = 2)
    private String sex;

    @ExcelProperty(index = 3)
    private String company;

    @ExcelProperty(index = 4)
    private String rank;

    private Integer titleId;

    @ExcelProperty(index = 5)
    private String titleName;

    @ExcelProperty(index = 6)
    private String mobile;

    private Integer territoryId;

    @ExcelProperty(index = 7)
    private String territoryName;

    @ExcelProperty(index = 8)
    private String subdivision;

    private Integer specTypeId;

    @ExcelProperty(index = 9)
    private String specTypeName;

    @ExcelProperty(index = 10)
    private Integer level;

    @ExcelProperty(index = 11)
    private String cardName;

    @ExcelProperty(index = 12)
    private String identityNo;

    @ExcelProperty(index = 13)
    private String bankName;

    @ExcelProperty(index = 14)
    private String cardNo;

    @ExcelProperty(index = 15)
    private String remark;

    private static final long serialVersionUID = 1L;


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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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


    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName == null ? null : titleName.trim();
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
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

    public SpecInfo toEntiry() {
        SpecInfo info = new SpecInfo();
        info.setUserName(this.userName);
        info.setAge(this.age);
        info.setSex("男".equals(this.sex) ? 1 : 0);
        info.setCompany(this.company);
        info.setRank(this.rank);
        info.setTitleId(this.titleId);
        info.setTitleName(this.titleName);
        info.setMobile(this.mobile);
        info.setTerritoryId(this.territoryId);
        info.setTerritoryName(this.territoryName);
        info.setSubdivision(this.subdivision);
        info.setSpecTypeId(this.specTypeId);
        info.setSpecTypeName(this.specTypeName);
        info.setLevel(this.level);
        info.setCardName(this.cardName);
        info.setIdentityNo(this.identityNo);
        info.setBankName(this.bankName);
        info.setCardNo(this.cardNo);
        info.setRemark(this.remark);
        return info;

    }


    public String check(List<CoreTitle> titleList, List<CoreTerritory> territoryList, List<CoreSpecType> specTypeList) {
        StringBuilder errs = new StringBuilder();
        //姓名 *
        if (this.userName == null || this.userName.trim().length() == 0) {
            errs.append("姓名未填写").append(",");
        }
        if (this.userName != null)
            this.userName = this.userName.trim();
        //年龄 *
        if (this.age == null || this.age.trim().length() == 0) {
            errs.append("年龄未填写").append(",");
        }
        if (this.age != null)
            this.age = age.indexOf(".") > -1 ? age.substring(0, age.indexOf(".")) : this.age.trim();
        //性别 *
        if (this.sex == null || this.sex.trim().length() == 0) {
            errs.append("性别未填写").append(",");
        }
        if (this.sex != null)
            this.sex = this.sex.trim();
        if (!("男".equals(this.sex) || "女".equals(this.sex))) {
            errs.append("性别只能填写男/女").append(",");
        }

        //单位 *
        if (this.company == null || this.company.trim().length() == 0) {
            errs.append("单位未填写").append(",");
        }
        if (this.company != null)
            this.company = this.company.trim();

        //职位 *
        if (this.rank == null || this.rank.trim().length() == 0) {
            errs.append("职位未填写").append(",");
        }
        if (this.rank != null)
            this.rank = this.rank.trim();
        //职称 *
        if (this.titleName == null || this.titleName.trim().length() == 0) {
            errs.append("专家职称未填写").append(",");
        } else {
            for (CoreTitle coreTitle : titleList) {
                if (coreTitle.getName().equals(this.titleName.trim())) {
                    this.titleId = coreTitle.getId();
                    break;
                }
            }
            if (this.titleId == null) {
                errs.append("专家职称未匹配到ID").append(",");
            }
        }

        //联系电话 *
        if (this.mobile == null || this.mobile.trim().length() == 0) {
            errs.append("联系电话未填写").append(",");
        }
        if (this.mobile != null)
            this.mobile = this.mobile.trim();

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

        //细分领域 *
        if (this.subdivision == null || this.subdivision.trim().length() == 0) {
            errs.append("细分领域未填写").append(",");
        }
        if (this.subdivision != null)
            this.subdivision = this.subdivision.trim();

        //专家类别 *
        if (this.specTypeName == null || this.specTypeName.trim().length() == 0) {
            errs.append("专家类别未填写").append(",");
        } else {
            for (CoreSpecType coreSpecType : specTypeList) {
                if (coreSpecType.getName().equals(this.specTypeName.trim())) {
                    this.specTypeId = coreSpecType.getId();
                    break;
                }
            }
            if (this.specTypeId == null) {
                errs.append("专家类别未匹配到ID").append(",");
            }
        }

        //评分星级*
        if (this.level == null || String.valueOf(this.level).trim().length() == 0) {
            errs.append("评分星级未填写").append(",");
        }
        if (this.level != null)
            this.level = this.level;

        return errs.toString();
    }
}
