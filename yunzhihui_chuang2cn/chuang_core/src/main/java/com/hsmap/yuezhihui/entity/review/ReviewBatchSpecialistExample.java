package com.hsmap.yuezhihui.entity.review;

import com.hsmap.yuezhihui.base.entity.BaseExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewBatchSpecialistExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReviewBatchSpecialistExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("v.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSpecIdIsNull() {
            addCriterion("spec_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecIdIsNotNull() {
            addCriterion("spec_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecIdEqualTo(Integer value) {
            addCriterion("spec_id =", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdNotEqualTo(Integer value) {
            addCriterion("spec_id <>", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdGreaterThan(Integer value) {
            addCriterion("spec_id >", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spec_id >=", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdLessThan(Integer value) {
            addCriterion("spec_id <", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdLessThanOrEqualTo(Integer value) {
            addCriterion("spec_id <=", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdIn(List<Integer> values) {
            addCriterion("v.spec_id in", values, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdNotIn(List<Integer> values) {
            addCriterion("spec_id not in", values, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdBetween(Integer value1, Integer value2) {
            addCriterion("spec_id between", value1, value2, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spec_id not between", value1, value2, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecNameIsNull() {
            addCriterion("spec_name is null");
            return (Criteria) this;
        }

        public Criteria andSpecNameIsNotNull() {
            addCriterion("spec_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpecNameEqualTo(String value) {
            addCriterion("spec_name =", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotEqualTo(String value) {
            addCriterion("spec_name <>", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameGreaterThan(String value) {
            addCriterion("spec_name >", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameGreaterThanOrEqualTo(String value) {
            addCriterion("spec_name >=", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameLessThan(String value) {
            addCriterion("spec_name <", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameLessThanOrEqualTo(String value) {
            addCriterion("spec_name <=", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameLike(String value) {
            addCriterion("spec_name like", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotLike(String value) {
            addCriterion("spec_name not like", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameIn(List<String> values) {
            addCriterion("spec_name in", values, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotIn(List<String> values) {
            addCriterion("spec_name not in", values, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameBetween(String value1, String value2) {
            addCriterion("spec_name between", value1, value2, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotBetween(String value1, String value2) {
            addCriterion("spec_name not between", value1, value2, "specName");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNull() {
            addCriterion("v.batch_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("v.batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(Integer value) {
            addCriterion("v.batch_id =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(Integer value) {
            addCriterion("v.batch_id <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(Integer value) {
            addCriterion("v.batch_id >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("v.batch_id >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(Integer value) {
            addCriterion("v.batch_id <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("v.batch_id <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<Integer> values) {
            addCriterion("v.batch_id in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<Integer> values) {
            addCriterion("v.batch_id not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("v.batch_id between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("v.batch_id not between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchNameIsNull() {
            addCriterion("batch_name is null");
            return (Criteria) this;
        }

        public Criteria andBatchNameIsNotNull() {
            addCriterion("batch_name is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNameEqualTo(String value) {
            addCriterion("batch_name =", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotEqualTo(String value) {
            addCriterion("batch_name <>", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameGreaterThan(String value) {
            addCriterion("batch_name >", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameGreaterThanOrEqualTo(String value) {
            addCriterion("batch_name >=", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameLessThan(String value) {
            addCriterion("batch_name <", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameLessThanOrEqualTo(String value) {
            addCriterion("batch_name <=", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameLike(String value) {
            addCriterion("batch_name like", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotLike(String value) {
            addCriterion("batch_name not like", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameIn(List<String> values) {
            addCriterion("batch_name in", values, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotIn(List<String> values) {
            addCriterion("batch_name not in", values, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameBetween(String value1, String value2) {
            addCriterion("batch_name between", value1, value2, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotBetween(String value1, String value2) {
            addCriterion("batch_name not between", value1, value2, "batchName");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdIsNull() {
            addCriterion("territory_id is null");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdIsNotNull() {
            addCriterion("territory_id is not null");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdEqualTo(Integer value) {
            addCriterion("v.territory_id =", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdNotEqualTo(Integer value) {
            addCriterion("v.territory_id <>", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdGreaterThan(Integer value) {
            addCriterion("v.territory_id >", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("v.territory_id >=", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdLessThan(Integer value) {
            addCriterion("v.territory_id <", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("v.territory_id <=", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdIn(List<Integer> values) {
            addCriterion("v.territory_id in", values, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdNotIn(List<Integer> values) {
            addCriterion("v.territory_id not in", values, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdBetween(Integer value1, Integer value2) {
            addCriterion("v.territory_id between", value1, value2, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("v.territory_id not between", value1, value2, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameIsNull() {
            addCriterion("territory_name is null");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameIsNotNull() {
            addCriterion("territory_name is not null");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameEqualTo(String value) {
            addCriterion("territory_name =", value, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameNotEqualTo(String value) {
            addCriterion("territory_name <>", value, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameGreaterThan(String value) {
            addCriterion("territory_name >", value, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("territory_name >=", value, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameLessThan(String value) {
            addCriterion("territory_name <", value, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameLessThanOrEqualTo(String value) {
            addCriterion("territory_name <=", value, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameLike(String value) {
            addCriterion("territory_name like", value, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameNotLike(String value) {
            addCriterion("territory_name not like", value, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameIn(List<String> values) {
            addCriterion("territory_name in", values, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameNotIn(List<String> values) {
            addCriterion("territory_name not in", values, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameBetween(String value1, String value2) {
            addCriterion("territory_name between", value1, value2, "territoryName");
            return (Criteria) this;
        }

        public Criteria andTerritoryNameNotBetween(String value1, String value2) {
            addCriterion("territory_name not between", value1, value2, "territoryName");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("is_use is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("is_use is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(Integer value) {
            addCriterion("v.is_use =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(Integer value) {
            addCriterion("v.is_use <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(Integer value) {
            addCriterion("v.is_use >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("v.is_use >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(Integer value) {
            addCriterion("v.is_use <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(Integer value) {
            addCriterion("v.is_use <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<Integer> values) {
            addCriterion("v.is_use in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<Integer> values) {
            addCriterion("v.is_use not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(Integer value1, Integer value2) {
            addCriterion("v.is_use between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(Integer value1, Integer value2) {
            addCriterion("v.is_use not between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("v.is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("v.is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("v.is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("v.is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("v.is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("v.is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("v.is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("v.is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("v.is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("v.is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("v.is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("v.is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort_ is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort_ is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort_ =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort_ <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort_ >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_ >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort_ <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort_ <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort_ in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort_ not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort_ between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_ not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark_ is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark_ is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark_ =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark_ <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark_ >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark_ >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark_ <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark_ <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark_ like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark_ not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark_ in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark_ not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark_ between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark_ not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdIsNull() {
            addCriterion("v.spec_type_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdIsNotNull() {
            addCriterion("v.spec_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdEqualTo(Integer value) {
            addCriterion("v.spec_type_id =", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdNotEqualTo(Integer value) {
            addCriterion("v.spec_type_id <>", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdGreaterThan(Integer value) {
            addCriterion("v.spec_type_id >", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("v.spec_type_id >=", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdLessThan(Integer value) {
            addCriterion("v.spec_type_id <", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("v.spec_type_id <=", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdIn(List<Integer> values) {
            addCriterion("v.spec_type_id in", values, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdNotIn(List<Integer> values) {
            addCriterion("v.spec_type_id not in", values, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("v.spec_type_id between", value1, value2, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("v.spec_type_id not between", value1, value2, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameIsNull() {
            addCriterion("spec_type_name is null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameIsNotNull() {
            addCriterion("spec_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameEqualTo(String value) {
            addCriterion("spec_type_name =", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameNotEqualTo(String value) {
            addCriterion("spec_type_name <>", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameGreaterThan(String value) {
            addCriterion("spec_type_name >", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("spec_type_name >=", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameLessThan(String value) {
            addCriterion("spec_type_name <", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameLessThanOrEqualTo(String value) {
            addCriterion("spec_type_name <=", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameLike(String value) {
            addCriterion("spec_type_name like", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameNotLike(String value) {
            addCriterion("spec_type_name not like", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameIn(List<String> values) {
            addCriterion("spec_type_name in", values, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameNotIn(List<String> values) {
            addCriterion("spec_type_name not in", values, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameBetween(String value1, String value2) {
            addCriterion("spec_type_name between", value1, value2, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameNotBetween(String value1, String value2) {
            addCriterion("spec_type_name not between", value1, value2, "specTypeName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
