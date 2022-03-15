package com.hsmap.yuezhihui.entity.review;

import com.hsmap.yuezhihui.base.entity.BaseExample;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目信息复杂查询数据对象
 */
public class ComplexRelationProductDo extends BaseExample implements Serializable {
    private String orderByClause;
    /*-------------review_group---------------*/
    private Integer rg_id;
    private String rg_name_;
    private Integer rg_spec_count;
    private Integer rg_product_count;
    private String rg_address_;
    private String rg_am_start_time;
    private String rg_am_end_time;
    private String rg_pm_start_time;
    private String rg_pm_end_time;
    private String rg_territory_ids;
    private Integer rg_start_;
    private Integer rg_is_del;
    private Integer rg_sort_;
    private Date rg_create_time;
    private String rg_remark_;
    private Integer rg_batch_id;
    private String rg_batch_name;
    /*-------------review_product---------------*/
    private Integer rp_id;
    private Integer rp_product_id;
    private String rp_product_name;
    private Integer rp_group_id;
    private String rp_group_name;
    private Integer rp_rule_id;
    private String rp_rule_name;
    private String rp_duration_;
    private Integer rp_spec_count;
    private Integer rp_review_count;
    private Double rp_score_;
    private Integer rp_weight_score;
    private Integer rp_ranking_;
    private Integer rp_status_;
    private Integer rp_is_del;
    private Integer rp_sort_;
    private Date rp_create_time;
    private String rp_remark_;
    /*-------------product_info---------------*/
    private Integer pi_id;
    private Integer pi_batch_id;
    private String pi_batch_name;
    private Integer pi_territory_id;
    private String pi_territory_name;
    private String pi_major_field;
    private Integer pi_type_id;
    private String pi_type_name;
    private String pi_product_name;
    private String pi_user_name;
    private String pi_mobile_;
    private String pi_email_;
    private Integer pi_sex_;
    private String pi_recommend_;
    private String pi_birthday_;
    private String pi_nationality_;
    private String pi_position_;
    private String pi_school_name;
    private String pi_major_;
    private String pi_degree_;
    private String pi_work_overseas;
    private String pi_home_time;
    private String pi_shao_time;
    private String pi_work_;
    private String pi_work_company;
    private String pi_patent_;
    private Integer pi_is_del;
    private Integer pi_sort_;
    private Date pi_create_time;
    private String pi_remark_;
    /*-------------core_territory---------------*/
    //专业领域
    private String ct_name_;
    /*-------------core_project_type---------------*/
    //人才类型
    private String cpt_name_;
    /*-------------review_batch---------------*/
    //批次
    private String rb_name_;

    /*---------- 查询参数 -----------*/
    /**
     * 审批组ID
     */
    private Integer queryGroupId;
    /**
     * 专业领域ID
     */
    private Integer queryTerritoryId;
    /**
     * 人才类型ID
     */
    private Integer queryTypeId;
    /**
     * 申报人
     */
    private String queryUserName;
    /**
     * 批次
     */
    private Integer queryBatchId;
    /**
     * 申报项目
     */
    private String queryProductName;
    /**
     * 申报项目评审状态 , 0待评审1正在评审2评审完成
     */
    private Integer queryProductReViewStatus;
    /**
     * 项目编号
     */
    private Integer queryProductId;


    public Integer getQueryProductId() {
        return queryProductId;
    }

    public void setQueryProductId(Integer queryProductId) {
        this.queryProductId = queryProductId;
    }

    public Integer getQueryProductReViewStatus() {
        return queryProductReViewStatus;
    }

    public void setQueryProductReViewStatus(Integer queryProductReViewStatus) {
        this.queryProductReViewStatus = queryProductReViewStatus;
    }

    public Integer getQueryGroupId() {
        return queryGroupId;
    }

    public void setQueryGroupId(Integer queryGroupId) {
        this.queryGroupId = queryGroupId;
    }

    public Integer getQueryTerritoryId() {
        return queryTerritoryId;
    }

    public void setQueryTerritoryId(Integer queryTerritoryId) {
        this.queryTerritoryId = queryTerritoryId;
    }

    public Integer getQueryTypeId() {
        return queryTypeId;
    }

    public void setQueryTypeId(Integer queryTypeId) {
        this.queryTypeId = queryTypeId;
    }

    public String getQueryUserName() {
        return queryUserName;
    }

    public void setQueryUserName(String queryUserName) {
        this.queryUserName = queryUserName;
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

    public String getRg_am_start_time() {
        return rg_am_start_time;
    }

    public void setRg_am_start_time(String rg_am_start_time) {
        this.rg_am_start_time = rg_am_start_time;
    }

    public String getRg_am_end_time() {
        return rg_am_end_time;
    }

    public void setRg_am_end_time(String rg_am_end_time) {
        this.rg_am_end_time = rg_am_end_time;
    }

    public String getRg_pm_start_time() {
        return rg_pm_start_time;
    }

    public void setRg_pm_start_time(String rg_pm_start_time) {
        this.rg_pm_start_time = rg_pm_start_time;
    }

    public String getRg_pm_end_time() {
        return rg_pm_end_time;
    }

    public void setRg_pm_end_time(String rg_pm_end_time) {
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

    public Integer getRp_id() {
        return rp_id;
    }

    public void setRp_id(Integer rp_id) {
        this.rp_id = rp_id;
    }

    public Integer getRp_product_id() {
        return rp_product_id;
    }

    public void setRp_product_id(Integer rp_product_id) {
        this.rp_product_id = rp_product_id;
    }

    public String getRp_product_name() {
        return rp_product_name;
    }

    public void setRp_product_name(String rp_product_name) {
        this.rp_product_name = rp_product_name;
    }

    public Integer getRp_group_id() {
        return rp_group_id;
    }

    public void setRp_group_id(Integer rp_group_id) {
        this.rp_group_id = rp_group_id;
    }

    public String getRp_group_name() {
        return rp_group_name;
    }

    public void setRp_group_name(String rp_group_name) {
        this.rp_group_name = rp_group_name;
    }

    public Integer getRp_rule_id() {
        return rp_rule_id;
    }

    public void setRp_rule_id(Integer rp_rule_id) {
        this.rp_rule_id = rp_rule_id;
    }

    public String getRp_rule_name() {
        return rp_rule_name;
    }

    public void setRp_rule_name(String rp_rule_name) {
        this.rp_rule_name = rp_rule_name;
    }

    public String getRp_duration_() {
        return rp_duration_;
    }

    public void setRp_duration_(String rp_duration_) {
        this.rp_duration_ = rp_duration_;
    }

    public Integer getRp_spec_count() {
        return rp_spec_count;
    }

    public void setRp_spec_count(Integer rp_spec_count) {
        this.rp_spec_count = rp_spec_count;
    }

    public Integer getRp_review_count() {
        return rp_review_count;
    }

    public void setRp_review_count(Integer rp_review_count) {
        this.rp_review_count = rp_review_count;
    }

    public Double getRp_score_() {
        return rp_score_;
    }

    public void setRp_score_(Double rp_score_) {
        this.rp_score_ = rp_score_;
    }

    public Integer getRp_weight_score() {
        return rp_weight_score;
    }

    public void setRp_weight_score(Integer rp_weight_score) {
        this.rp_weight_score = rp_weight_score;
    }

    public Integer getRp_ranking_() {
        return rp_ranking_;
    }

    public void setRp_ranking_(Integer rp_ranking_) {
        this.rp_ranking_ = rp_ranking_;
    }

    public Integer getRp_status_() {
        return rp_status_;
    }

    public void setRp_status_(Integer rp_status_) {
        this.rp_status_ = rp_status_;
    }

    public Integer getRp_is_del() {
        return rp_is_del;
    }

    public void setRp_is_del(Integer rp_is_del) {
        this.rp_is_del = rp_is_del;
    }

    public Integer getRp_sort_() {
        return rp_sort_;
    }

    public void setRp_sort_(Integer rp_sort_) {
        this.rp_sort_ = rp_sort_;
    }

    public Date getRp_create_time() {
        return rp_create_time;
    }

    public void setRp_create_time(Date rp_create_time) {
        this.rp_create_time = rp_create_time;
    }

    public String getRp_remark_() {
        return rp_remark_;
    }

    public void setRp_remark_(String rp_remark_) {
        this.rp_remark_ = rp_remark_;
    }

    public Integer getPi_id() {
        return pi_id;
    }

    public void setPi_id(Integer pi_id) {
        this.pi_id = pi_id;
    }

    public Integer getPi_batch_id() {
        return pi_batch_id;
    }

    public void setPi_batch_id(Integer pi_batch_id) {
        this.pi_batch_id = pi_batch_id;
    }

    public String getPi_batch_name() {
        return pi_batch_name;
    }

    public void setPi_batch_name(String pi_batch_name) {
        this.pi_batch_name = pi_batch_name;
    }

    public Integer getPi_territory_id() {
        return pi_territory_id;
    }

    public void setPi_territory_id(Integer pi_territory_id) {
        this.pi_territory_id = pi_territory_id;
    }

    public String getPi_territory_name() {
        return pi_territory_name;
    }

    public void setPi_territory_name(String pi_territory_name) {
        this.pi_territory_name = pi_territory_name;
    }

    public String getPi_major_field() {
        return pi_major_field;
    }

    public void setPi_major_field(String pi_major_field) {
        this.pi_major_field = pi_major_field;
    }

    public Integer getPi_type_id() {
        return pi_type_id;
    }

    public void setPi_type_id(Integer pi_type_id) {
        this.pi_type_id = pi_type_id;
    }

    public String getPi_type_name() {
        return pi_type_name;
    }

    public void setPi_type_name(String pi_type_name) {
        this.pi_type_name = pi_type_name;
    }

    public String getPi_product_name() {
        return pi_product_name;
    }

    public void setPi_product_name(String pi_product_name) {
        this.pi_product_name = pi_product_name;
    }

    public String getPi_user_name() {
        return pi_user_name;
    }

    public void setPi_user_name(String pi_user_name) {
        this.pi_user_name = pi_user_name;
    }

    public String getPi_mobile_() {
        return pi_mobile_;
    }

    public void setPi_mobile_(String pi_mobile_) {
        this.pi_mobile_ = pi_mobile_;
    }

    public String getPi_email_() {
        return pi_email_;
    }

    public void setPi_email_(String pi_email_) {
        this.pi_email_ = pi_email_;
    }

    public Integer getPi_sex_() {
        return pi_sex_;
    }

    public void setPi_sex_(Integer pi_sex_) {
        this.pi_sex_ = pi_sex_;
    }

    public String getPi_recommend_() {
        return pi_recommend_;
    }

    public void setPi_recommend_(String pi_recommend_) {
        this.pi_recommend_ = pi_recommend_;
    }

    public String getPi_birthday_() {
        return pi_birthday_;
    }

    public void setPi_birthday_(String pi_birthday_) {
        this.pi_birthday_ = pi_birthday_;
    }

    public String getPi_nationality_() {
        return pi_nationality_;
    }

    public void setPi_nationality_(String pi_nationality_) {
        this.pi_nationality_ = pi_nationality_;
    }

    public String getPi_position_() {
        return pi_position_;
    }

    public void setPi_position_(String pi_position_) {
        this.pi_position_ = pi_position_;
    }

    public String getPi_school_name() {
        return pi_school_name;
    }

    public void setPi_school_name(String pi_school_name) {
        this.pi_school_name = pi_school_name;
    }

    public String getPi_major_() {
        return pi_major_;
    }

    public void setPi_major_(String pi_major_) {
        this.pi_major_ = pi_major_;
    }

    public String getPi_degree_() {
        return pi_degree_;
    }

    public void setPi_degree_(String pi_degree_) {
        this.pi_degree_ = pi_degree_;
    }

    public String getPi_work_overseas() {
        return pi_work_overseas;
    }

    public void setPi_work_overseas(String pi_work_overseas) {
        this.pi_work_overseas = pi_work_overseas;
    }

    public String getPi_home_time() {
        return pi_home_time;
    }

    public void setPi_home_time(String pi_home_time) {
        this.pi_home_time = pi_home_time;
    }

    public String getPi_shao_time() {
        return pi_shao_time;
    }

    public void setPi_shao_time(String pi_shao_time) {
        this.pi_shao_time = pi_shao_time;
    }

    public String getPi_work_() {
        return pi_work_;
    }

    public void setPi_work_(String pi_work_) {
        this.pi_work_ = pi_work_;
    }

    public String getPi_work_company() {
        return pi_work_company;
    }

    public void setPi_work_company(String pi_work_company) {
        this.pi_work_company = pi_work_company;
    }

    public String getPi_patent_() {
        return pi_patent_;
    }

    public void setPi_patent_(String pi_patent_) {
        this.pi_patent_ = pi_patent_;
    }

    public Integer getPi_is_del() {
        return pi_is_del;
    }

    public void setPi_is_del(Integer pi_is_del) {
        this.pi_is_del = pi_is_del;
    }

    public Integer getPi_sort_() {
        return pi_sort_;
    }

    public void setPi_sort_(Integer pi_sort_) {
        this.pi_sort_ = pi_sort_;
    }

    public Date getPi_create_time() {
        return pi_create_time;
    }

    public void setPi_create_time(Date pi_create_time) {
        this.pi_create_time = pi_create_time;
    }

    public String getPi_remark_() {
        return pi_remark_;
    }

    public void setPi_remark_(String pi_remark_) {
        this.pi_remark_ = pi_remark_;
    }

    public String getCt_name_() {
        return ct_name_;
    }

    public void setCt_name_(String ct_name_) {
        this.ct_name_ = ct_name_;
    }

    public String getCpt_name_() {
        return cpt_name_;
    }

    public void setCpt_name_(String cpt_name_) {
        this.cpt_name_ = cpt_name_;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getRb_name_() {
        return rb_name_;
    }

    public void setRb_name_(String rb_name_) {
        this.rb_name_ = rb_name_;
    }

    public Integer getQueryBatchId() {
        return queryBatchId;
    }

    public void setQueryBatchId(Integer queryBatchId) {
        this.queryBatchId = queryBatchId;
    }

    public String getQueryProductName() {
        return queryProductName;
    }

    public void setQueryProductName(String queryProductName) {
        this.queryProductName = queryProductName;
    }
}
