package com.hsmap.yuezhihui.entity.review;

import com.hsmap.yuezhihui.base.entity.BaseExample;

import java.io.Serializable;
import java.util.Date;

/**
 * 专家复杂查询数据对象
 */
public class ComplexRelationSpecDo extends BaseExample implements Serializable {
    private String orderByClause;
    /*-------------review_group---------------*/
    private Integer rg_id;
    private String rg_name_;
    private Integer rg_spec_count;
    private Integer rg_product_count;
    private String rg_address_;
    private Integer rg_am_start_time;
    private Integer rg_am_end_time;
    private Integer rg_pm_start_time;
    private Integer rg_pm_end_time;
    private String rg_territory_ids;
    private Integer rg_start_;
    private Integer rg_is_del;
    private Integer rg_sort_;
    private Date rg_create_time;
    private String rg_remark_;
    private Integer rg_batch_id;
    private String rg_batch_name;
    /*-------------review_specialist---------------*/
    private Integer rs_id;
    private Integer rs_group_id;
    private String rs_group_name;
    private Integer rs_spec_id;
    private String rs_spec_name;
    private Integer rs_product_count;
    private Integer rs_review_count;
    private Integer rs_status_;
    private Integer rs_is_del;
    private Integer rs_sort_;
    private Date rs_create_time;
    private String rs_remark_;
    /*-------------spec_info---------------*/
    private Integer si_id;
    private String si_user_name;
    private String si_mobile_;
    private String si_age_;
    private Integer si_sex_;
    private String si_company_;
    private String si_rank_;
    private Integer si_title_id;
    private String si_title_name;
    private Integer si_territory_id;
    private String si_territory_name;
    private Integer si_spec_type_id;
    private String si_spec_type_name;
    private String si_subdivision_;
    private Integer si_level_;
    private Integer si_is_del;
    private Integer si_sort_;
    private Date si_create_time;
    private String si_remark_;
    private String si_card_name;
    private String si_identity_no;
    private String si_bank_name;
    private String si_card_no;
    /*-------------core_territory---------------*/
    //专业领域
    private String ct_name_;
    /*-------------core_title---------------*/
    //职称
    private String cti_name_;
    /*-------------core_spec_type---------------*/
    //专家类别
    private String cst_name_;

    /*---------- 查询参数 -----------*/
    /**
     * 审批组ID
     */
    private Integer queryGroupId;


    public Integer getQueryGroupId() {
        return queryGroupId;
    }

    public void setQueryGroupId(Integer queryGroupId) {
        this.queryGroupId = queryGroupId;
    }


    public Integer getRg_id() {
        return rg_id;
    }

    public void setRg_id(Integer rg_id) {
        this.rg_id = rg_id;
    }

    public String getRg_name_() {
        return rg_name_;
    }

    public void setRg_name_(String rg_name_) {
        this.rg_name_ = rg_name_;
    }

    public Integer getRg_spec_count() {
        return rg_spec_count;
    }

    public void setRg_spec_count(Integer rg_spec_count) {
        this.rg_spec_count = rg_spec_count;
    }

    public Integer getRg_product_count() {
        return rg_product_count;
    }

    public void setRg_product_count(Integer rg_product_count) {
        this.rg_product_count = rg_product_count;
    }

    public String getRg_address_() {
        return rg_address_;
    }

    public void setRg_address_(String rg_address_) {
        this.rg_address_ = rg_address_;
    }

    public Integer getRg_am_start_time() {
        return rg_am_start_time;
    }

    public void setRg_am_start_time(Integer rg_am_start_time) {
        this.rg_am_start_time = rg_am_start_time;
    }

    public Integer getRg_am_end_time() {
        return rg_am_end_time;
    }

    public void setRg_am_end_time(Integer rg_am_end_time) {
        this.rg_am_end_time = rg_am_end_time;
    }

    public Integer getRg_pm_start_time() {
        return rg_pm_start_time;
    }

    public void setRg_pm_start_time(Integer rg_pm_start_time) {
        this.rg_pm_start_time = rg_pm_start_time;
    }

    public Integer getRg_pm_end_time() {
        return rg_pm_end_time;
    }

    public void setRg_pm_end_time(Integer rg_pm_end_time) {
        this.rg_pm_end_time = rg_pm_end_time;
    }

    public String getRg_territory_ids() {
        return rg_territory_ids;
    }

    public void setRg_territory_ids(String rg_territory_ids) {
        this.rg_territory_ids = rg_territory_ids;
    }

    public Integer getRg_start_() {
        return rg_start_;
    }

    public void setRg_start_(Integer rg_start_) {
        this.rg_start_ = rg_start_;
    }

    public Integer getRg_is_del() {
        return rg_is_del;
    }

    public void setRg_is_del(Integer rg_is_del) {
        this.rg_is_del = rg_is_del;
    }

    public Integer getRg_sort_() {
        return rg_sort_;
    }

    public void setRg_sort_(Integer rg_sort_) {
        this.rg_sort_ = rg_sort_;
    }

    public Date getRg_create_time() {
        return rg_create_time;
    }

    public void setRg_create_time(Date rg_create_time) {
        this.rg_create_time = rg_create_time;
    }

    public String getRg_remark_() {
        return rg_remark_;
    }

    public void setRg_remark_(String rg_remark_) {
        this.rg_remark_ = rg_remark_;
    }

    public Integer getRg_batch_id() {
        return rg_batch_id;
    }

    public void setRg_batch_id(Integer rg_batch_id) {
        this.rg_batch_id = rg_batch_id;
    }

    public String getRg_batch_name() {
        return rg_batch_name;
    }

    public void setRg_batch_name(String rg_batch_name) {
        this.rg_batch_name = rg_batch_name;
    }

    public Integer getRs_id() {
        return rs_id;
    }

    public void setRs_id(Integer rs_id) {
        this.rs_id = rs_id;
    }

    public Integer getRs_group_id() {
        return rs_group_id;
    }

    public void setRs_group_id(Integer rs_group_id) {
        this.rs_group_id = rs_group_id;
    }

    public String getRs_group_name() {
        return rs_group_name;
    }

    public void setRs_group_name(String rs_group_name) {
        this.rs_group_name = rs_group_name;
    }

    public Integer getRs_spec_id() {
        return rs_spec_id;
    }

    public void setRs_spec_id(Integer rs_spec_id) {
        this.rs_spec_id = rs_spec_id;
    }

    public String getRs_spec_name() {
        return rs_spec_name;
    }

    public void setRs_spec_name(String rs_spec_name) {
        this.rs_spec_name = rs_spec_name;
    }

    public Integer getRs_product_count() {
        return rs_product_count;
    }

    public void setRs_product_count(Integer rs_product_count) {
        this.rs_product_count = rs_product_count;
    }

    public Integer getRs_review_count() {
        return rs_review_count;
    }

    public void setRs_review_count(Integer rs_review_count) {
        this.rs_review_count = rs_review_count;
    }

    public Integer getRs_status_() {
        return rs_status_;
    }

    public void setRs_status_(Integer rs_status_) {
        this.rs_status_ = rs_status_;
    }

    public Integer getRs_is_del() {
        return rs_is_del;
    }

    public void setRs_is_del(Integer rs_is_del) {
        this.rs_is_del = rs_is_del;
    }

    public Integer getRs_sort_() {
        return rs_sort_;
    }

    public void setRs_sort_(Integer rs_sort_) {
        this.rs_sort_ = rs_sort_;
    }

    public Date getRs_create_time() {
        return rs_create_time;
    }

    public void setRs_create_time(Date rs_create_time) {
        this.rs_create_time = rs_create_time;
    }

    public String getRs_remark_() {
        return rs_remark_;
    }

    public void setRs_remark_(String rs_remark_) {
        this.rs_remark_ = rs_remark_;
    }

    public Integer getSi_id() {
        return si_id;
    }

    public void setSi_id(Integer si_id) {
        this.si_id = si_id;
    }

    public String getSi_user_name() {
        return si_user_name;
    }

    public void setSi_user_name(String si_user_name) {
        this.si_user_name = si_user_name;
    }

    public String getSi_mobile_() {
        return si_mobile_;
    }

    public void setSi_mobile_(String si_mobile_) {
        this.si_mobile_ = si_mobile_;
    }

    public String getSi_age_() {
        return si_age_;
    }

    public void setSi_age_(String si_age_) {
        this.si_age_ = si_age_;
    }

    public Integer getSi_sex_() {
        return si_sex_;
    }

    public void setSi_sex_(Integer si_sex_) {
        this.si_sex_ = si_sex_;
    }

    public String getSi_company_() {
        return si_company_;
    }

    public void setSi_company_(String si_company_) {
        this.si_company_ = si_company_;
    }

    public String getSi_rank_() {
        return si_rank_;
    }

    public void setSi_rank_(String si_rank_) {
        this.si_rank_ = si_rank_;
    }

    public Integer getSi_title_id() {
        return si_title_id;
    }

    public void setSi_title_id(Integer si_title_id) {
        this.si_title_id = si_title_id;
    }

    public String getSi_title_name() {
        return si_title_name;
    }

    public void setSi_title_name(String si_title_name) {
        this.si_title_name = si_title_name;
    }

    public Integer getSi_territory_id() {
        return si_territory_id;
    }

    public void setSi_territory_id(Integer si_territory_id) {
        this.si_territory_id = si_territory_id;
    }

    public String getSi_territory_name() {
        return si_territory_name;
    }

    public void setSi_territory_name(String si_territory_name) {
        this.si_territory_name = si_territory_name;
    }

    public Integer getSi_spec_type_id() {
        return si_spec_type_id;
    }

    public void setSi_spec_type_id(Integer si_spec_type_id) {
        this.si_spec_type_id = si_spec_type_id;
    }

    public String getSi_spec_type_name() {
        return si_spec_type_name;
    }

    public void setSi_spec_type_name(String si_spec_type_name) {
        this.si_spec_type_name = si_spec_type_name;
    }

    public String getSi_subdivision_() {
        return si_subdivision_;
    }

    public void setSi_subdivision_(String si_subdivision_) {
        this.si_subdivision_ = si_subdivision_;
    }

    public Integer getSi_level_() {
        return si_level_;
    }

    public void setSi_level_(Integer si_level_) {
        this.si_level_ = si_level_;
    }

    public Integer getSi_is_del() {
        return si_is_del;
    }

    public void setSi_is_del(Integer si_is_del) {
        this.si_is_del = si_is_del;
    }

    public Integer getSi_sort_() {
        return si_sort_;
    }

    public void setSi_sort_(Integer si_sort_) {
        this.si_sort_ = si_sort_;
    }

    public Date getSi_create_time() {
        return si_create_time;
    }

    public void setSi_create_time(Date si_create_time) {
        this.si_create_time = si_create_time;
    }

    public String getSi_remark_() {
        return si_remark_;
    }

    public void setSi_remark_(String si_remark_) {
        this.si_remark_ = si_remark_;
    }

    public String getSi_card_name() {
        return si_card_name;
    }

    public void setSi_card_name(String si_card_name) {
        this.si_card_name = si_card_name;
    }

    public String getSi_identity_no() {
        return si_identity_no;
    }

    public void setSi_identity_no(String si_identity_no) {
        this.si_identity_no = si_identity_no;
    }

    public String getSi_bank_name() {
        return si_bank_name;
    }

    public void setSi_bank_name(String si_bank_name) {
        this.si_bank_name = si_bank_name;
    }

    public String getSi_card_no() {
        return si_card_no;
    }

    public void setSi_card_no(String si_card_no) {
        this.si_card_no = si_card_no;
    }

    public String getCt_name_() {
        return ct_name_;
    }

    public void setCt_name_(String ct_name_) {
        this.ct_name_ = ct_name_;
    }

    public String getCti_name_() {
        return cti_name_;
    }

    public void setCti_name_(String cti_name_) {
        this.cti_name_ = cti_name_;
    }

    public String getCst_name_() {
        return cst_name_;
    }

    public void setCst_name_(String cst_name_) {
        this.cst_name_ = cst_name_;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
}
