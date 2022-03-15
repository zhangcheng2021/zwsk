package com.hsmap.yuezhihui.entity.product;

import com.hsmap.yuezhihui.base.entity.BaseExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductInfoExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductInfoExample() {
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
            addCriterion("id in", values, "id");
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

        public Criteria andBatchIdIsNull() {
            addCriterion("batch_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(Integer value) {
            addCriterion("a.batch_id =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(Integer value) {
            addCriterion("batch_id <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(Integer value) {
            addCriterion("batch_id >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch_id >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(Integer value) {
            addCriterion("batch_id <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("batch_id <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<Integer> values) {
            addCriterion("batch_id in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<Integer> values) {
            addCriterion("batch_id not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("batch_id between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("batch_id not between", value1, value2, "batchId");
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
            addCriterion("a.territory_id =", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdNotEqualTo(Integer value) {
            addCriterion("territory_id <>", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdGreaterThan(Integer value) {
            addCriterion("territory_id >", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("territory_id >=", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdLessThan(Integer value) {
            addCriterion("territory_id <", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("territory_id <=", value, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdIn(List<Integer> values) {
            addCriterion("territory_id in", values, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdNotIn(List<Integer> values) {
            addCriterion("territory_id not in", values, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdBetween(Integer value1, Integer value2) {
            addCriterion("territory_id between", value1, value2, "territoryId");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("territory_id not between", value1, value2, "territoryId");
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

        public Criteria andMajorFieldIsNull() {
            addCriterion("major_field is null");
            return (Criteria) this;
        }

        public Criteria andMajorFieldIsNotNull() {
            addCriterion("major_field is not null");
            return (Criteria) this;
        }

        public Criteria andMajorFieldEqualTo(String value) {
            addCriterion("major_field =", value, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldNotEqualTo(String value) {
            addCriterion("major_field <>", value, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldGreaterThan(String value) {
            addCriterion("major_field >", value, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldGreaterThanOrEqualTo(String value) {
            addCriterion("major_field >=", value, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldLessThan(String value) {
            addCriterion("major_field <", value, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldLessThanOrEqualTo(String value) {
            addCriterion("major_field <=", value, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldLike(String value) {
            addCriterion("major_field like", value, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldNotLike(String value) {
            addCriterion("major_field not like", value, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldIn(List<String> values) {
            addCriterion("major_field in", values, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldNotIn(List<String> values) {
            addCriterion("major_field not in", values, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldBetween(String value1, String value2) {
            addCriterion("major_field between", value1, value2, "majorField");
            return (Criteria) this;
        }

        public Criteria andMajorFieldNotBetween(String value1, String value2) {
            addCriterion("major_field not between", value1, value2, "majorField");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("a.type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("type_name not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("a.product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }


        public Criteria andUserNameLike(String value) {
            addCriterion("a.user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }


        public Criteria andMobileIsNull() {
            addCriterion("mobile_ is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile_ is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile_ =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile_ <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile_ >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_ >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile_ <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile_ <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile_ like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile_ not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile_ in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile_ not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile_ between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile_ not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email_ is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email_ is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email_ =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email_ <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email_ >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email_ >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email_ <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email_ <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email_ like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email_ not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email_ in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email_ not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email_ between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email_ not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex_ is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex_ is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex_ =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex_ <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex_ >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex_ >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex_ <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex_ <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex_ in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex_ not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex_ between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex_ not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andRecommendIsNull() {
            addCriterion("recommend_ is null");
            return (Criteria) this;
        }

        public Criteria andRecommendIsNotNull() {
            addCriterion("recommend_ is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendEqualTo(String value) {
            addCriterion("recommend_ =", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotEqualTo(String value) {
            addCriterion("recommend_ <>", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThan(String value) {
            addCriterion("recommend_ >", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThanOrEqualTo(String value) {
            addCriterion("recommend_ >=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThan(String value) {
            addCriterion("recommend_ <", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThanOrEqualTo(String value) {
            addCriterion("recommend_ <=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLike(String value) {
            addCriterion("recommend_ like", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotLike(String value) {
            addCriterion("recommend_ not like", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendIn(List<String> values) {
            addCriterion("recommend_ in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotIn(List<String> values) {
            addCriterion("recommend_ not in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendBetween(String value1, String value2) {
            addCriterion("recommend_ between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotBetween(String value1, String value2) {
            addCriterion("recommend_ not between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday_ is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday_ is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(String value) {
            addCriterion("birthday_ =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(String value) {
            addCriterion("birthday_ <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(String value) {
            addCriterion("birthday_ >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("birthday_ >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(String value) {
            addCriterion("birthday_ <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(String value) {
            addCriterion("birthday_ <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLike(String value) {
            addCriterion("birthday_ like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotLike(String value) {
            addCriterion("birthday_ not like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<String> values) {
            addCriterion("birthday_ in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<String> values) {
            addCriterion("birthday_ not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(String value1, String value2) {
            addCriterion("birthday_ between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(String value1, String value2) {
            addCriterion("birthday_ not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNull() {
            addCriterion("nationality_ is null");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNotNull() {
            addCriterion("nationality_ is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityEqualTo(String value) {
            addCriterion("nationality_ =", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotEqualTo(String value) {
            addCriterion("nationality_ <>", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThan(String value) {
            addCriterion("nationality_ >", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("nationality_ >=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThan(String value) {
            addCriterion("nationality_ <", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThanOrEqualTo(String value) {
            addCriterion("nationality_ <=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLike(String value) {
            addCriterion("nationality_ like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotLike(String value) {
            addCriterion("nationality_ not like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityIn(List<String> values) {
            addCriterion("nationality_ in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotIn(List<String> values) {
            addCriterion("nationality_ not in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityBetween(String value1, String value2) {
            addCriterion("nationality_ between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotBetween(String value1, String value2) {
            addCriterion("nationality_ not between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position_ is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position_ is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position_ =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position_ <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position_ >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position_ >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position_ <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position_ <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position_ like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position_ not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position_ in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position_ not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position_ between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position_ not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNull() {
            addCriterion("school_name is null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNotNull() {
            addCriterion("school_name is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameEqualTo(String value) {
            addCriterion("school_name =", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotEqualTo(String value) {
            addCriterion("school_name <>", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThan(String value) {
            addCriterion("school_name >", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("school_name >=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThan(String value) {
            addCriterion("school_name <", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("school_name <=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLike(String value) {
            addCriterion("school_name like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotLike(String value) {
            addCriterion("school_name not like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIn(List<String> values) {
            addCriterion("school_name in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotIn(List<String> values) {
            addCriterion("school_name not in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameBetween(String value1, String value2) {
            addCriterion("school_name between", value1, value2, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotBetween(String value1, String value2) {
            addCriterion("school_name not between", value1, value2, "schoolName");
            return (Criteria) this;
        }

        public Criteria andMajorIsNull() {
            addCriterion("major_ is null");
            return (Criteria) this;
        }

        public Criteria andMajorIsNotNull() {
            addCriterion("major_ is not null");
            return (Criteria) this;
        }

        public Criteria andMajorEqualTo(String value) {
            addCriterion("major_ =", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotEqualTo(String value) {
            addCriterion("major_ <>", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThan(String value) {
            addCriterion("major_ >", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThanOrEqualTo(String value) {
            addCriterion("major_ >=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThan(String value) {
            addCriterion("major_ <", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThanOrEqualTo(String value) {
            addCriterion("major_ <=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLike(String value) {
            addCriterion("major_ like", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotLike(String value) {
            addCriterion("major_ not like", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorIn(List<String> values) {
            addCriterion("major_ in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotIn(List<String> values) {
            addCriterion("major_ not in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorBetween(String value1, String value2) {
            addCriterion("major_ between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotBetween(String value1, String value2) {
            addCriterion("major_ not between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNull() {
            addCriterion("degree_ is null");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNotNull() {
            addCriterion("degree_ is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeEqualTo(String value) {
            addCriterion("degree_ =", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotEqualTo(String value) {
            addCriterion("degree_ <>", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThan(String value) {
            addCriterion("degree_ >", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThanOrEqualTo(String value) {
            addCriterion("degree_ >=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThan(String value) {
            addCriterion("degree_ <", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThanOrEqualTo(String value) {
            addCriterion("degree_ <=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLike(String value) {
            addCriterion("degree_ like", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotLike(String value) {
            addCriterion("degree_ not like", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeIn(List<String> values) {
            addCriterion("degree_ in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotIn(List<String> values) {
            addCriterion("degree_ not in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeBetween(String value1, String value2) {
            addCriterion("degree_ between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotBetween(String value1, String value2) {
            addCriterion("degree_ not between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasIsNull() {
            addCriterion("work_overseas is null");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasIsNotNull() {
            addCriterion("work_overseas is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasEqualTo(String value) {
            addCriterion("work_overseas =", value, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasNotEqualTo(String value) {
            addCriterion("work_overseas <>", value, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasGreaterThan(String value) {
            addCriterion("work_overseas >", value, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasGreaterThanOrEqualTo(String value) {
            addCriterion("work_overseas >=", value, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasLessThan(String value) {
            addCriterion("work_overseas <", value, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasLessThanOrEqualTo(String value) {
            addCriterion("work_overseas <=", value, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasLike(String value) {
            addCriterion("work_overseas like", value, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasNotLike(String value) {
            addCriterion("work_overseas not like", value, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasIn(List<String> values) {
            addCriterion("work_overseas in", values, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasNotIn(List<String> values) {
            addCriterion("work_overseas not in", values, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasBetween(String value1, String value2) {
            addCriterion("work_overseas between", value1, value2, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andWorkOverseasNotBetween(String value1, String value2) {
            addCriterion("work_overseas not between", value1, value2, "workOverseas");
            return (Criteria) this;
        }

        public Criteria andHomeTimeIsNull() {
            addCriterion("home_time is null");
            return (Criteria) this;
        }

        public Criteria andHomeTimeIsNotNull() {
            addCriterion("home_time is not null");
            return (Criteria) this;
        }

        public Criteria andHomeTimeEqualTo(String value) {
            addCriterion("home_time =", value, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeNotEqualTo(String value) {
            addCriterion("home_time <>", value, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeGreaterThan(String value) {
            addCriterion("home_time >", value, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("home_time >=", value, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeLessThan(String value) {
            addCriterion("home_time <", value, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeLessThanOrEqualTo(String value) {
            addCriterion("home_time <=", value, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeLike(String value) {
            addCriterion("home_time like", value, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeNotLike(String value) {
            addCriterion("home_time not like", value, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeIn(List<String> values) {
            addCriterion("home_time in", values, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeNotIn(List<String> values) {
            addCriterion("home_time not in", values, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeBetween(String value1, String value2) {
            addCriterion("home_time between", value1, value2, "homeTime");
            return (Criteria) this;
        }

        public Criteria andHomeTimeNotBetween(String value1, String value2) {
            addCriterion("home_time not between", value1, value2, "homeTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeIsNull() {
            addCriterion("shao_time is null");
            return (Criteria) this;
        }

        public Criteria andShaoTimeIsNotNull() {
            addCriterion("shao_time is not null");
            return (Criteria) this;
        }

        public Criteria andShaoTimeEqualTo(String value) {
            addCriterion("shao_time =", value, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeNotEqualTo(String value) {
            addCriterion("shao_time <>", value, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeGreaterThan(String value) {
            addCriterion("shao_time >", value, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeGreaterThanOrEqualTo(String value) {
            addCriterion("shao_time >=", value, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeLessThan(String value) {
            addCriterion("shao_time <", value, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeLessThanOrEqualTo(String value) {
            addCriterion("shao_time <=", value, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeLike(String value) {
            addCriterion("shao_time like", value, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeNotLike(String value) {
            addCriterion("shao_time not like", value, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeIn(List<String> values) {
            addCriterion("shao_time in", values, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeNotIn(List<String> values) {
            addCriterion("shao_time not in", values, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeBetween(String value1, String value2) {
            addCriterion("shao_time between", value1, value2, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andShaoTimeNotBetween(String value1, String value2) {
            addCriterion("shao_time not between", value1, value2, "shaoTime");
            return (Criteria) this;
        }

        public Criteria andWorkIsNull() {
            addCriterion("work_ is null");
            return (Criteria) this;
        }

        public Criteria andWorkIsNotNull() {
            addCriterion("work_ is not null");
            return (Criteria) this;
        }

        public Criteria andWorkEqualTo(String value) {
            addCriterion("work_ =", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotEqualTo(String value) {
            addCriterion("work_ <>", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkGreaterThan(String value) {
            addCriterion("work_ >", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkGreaterThanOrEqualTo(String value) {
            addCriterion("work_ >=", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkLessThan(String value) {
            addCriterion("work_ <", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkLessThanOrEqualTo(String value) {
            addCriterion("work_ <=", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkLike(String value) {
            addCriterion("work_ like", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotLike(String value) {
            addCriterion("work_ not like", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkIn(List<String> values) {
            addCriterion("work_ in", values, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotIn(List<String> values) {
            addCriterion("work_ not in", values, "work");
            return (Criteria) this;
        }

        public Criteria andWorkBetween(String value1, String value2) {
            addCriterion("work_ between", value1, value2, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotBetween(String value1, String value2) {
            addCriterion("work_ not between", value1, value2, "work");
            return (Criteria) this;
        }

        public Criteria andPatentIsNull() {
            addCriterion("patent_ is null");
            return (Criteria) this;
        }

        public Criteria andPatentIsNotNull() {
            addCriterion("patent_ is not null");
            return (Criteria) this;
        }

        public Criteria andPatentEqualTo(String value) {
            addCriterion("patent_ =", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentNotEqualTo(String value) {
            addCriterion("patent_ <>", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentGreaterThan(String value) {
            addCriterion("patent_ >", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentGreaterThanOrEqualTo(String value) {
            addCriterion("patent_ >=", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentLessThan(String value) {
            addCriterion("patent_ <", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentLessThanOrEqualTo(String value) {
            addCriterion("patent_ <=", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentLike(String value) {
            addCriterion("patent_ like", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentNotLike(String value) {
            addCriterion("patent_ not like", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentIn(List<String> values) {
            addCriterion("patent_ in", values, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentNotIn(List<String> values) {
            addCriterion("patent_ not in", values, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentBetween(String value1, String value2) {
            addCriterion("patent_ between", value1, value2, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentNotBetween(String value1, String value2) {
            addCriterion("patent_ not between", value1, value2, "patent");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("a.is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("a.is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("a.is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("a.is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("a.is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("a.is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("a.is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("a.is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("a.is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("a.is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("a.is_del not between", value1, value2, "isDel");
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
