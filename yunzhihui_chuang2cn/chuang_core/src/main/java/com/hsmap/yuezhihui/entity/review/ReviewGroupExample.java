package com.hsmap.yuezhihui.entity.review;

import com.hsmap.yuezhihui.base.entity.BaseExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewGroupExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReviewGroupExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name_ is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name_ is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name_ =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name_ <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name_ >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name_ >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name_ <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name_ <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name_ like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name_ not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name_ in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name_ not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name_ between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name_ not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSpecCountIsNull() {
            addCriterion("spec_count is null");
            return (Criteria) this;
        }

        public Criteria andSpecCountIsNotNull() {
            addCriterion("spec_count is not null");
            return (Criteria) this;
        }

        public Criteria andSpecCountEqualTo(Integer value) {
            addCriterion("spec_count =", value, "specCount");
            return (Criteria) this;
        }

        public Criteria andSpecCountNotEqualTo(Integer value) {
            addCriterion("spec_count <>", value, "specCount");
            return (Criteria) this;
        }

        public Criteria andSpecCountGreaterThan(Integer value) {
            addCriterion("spec_count >", value, "specCount");
            return (Criteria) this;
        }

        public Criteria andSpecCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("spec_count >=", value, "specCount");
            return (Criteria) this;
        }

        public Criteria andSpecCountLessThan(Integer value) {
            addCriterion("spec_count <", value, "specCount");
            return (Criteria) this;
        }

        public Criteria andSpecCountLessThanOrEqualTo(Integer value) {
            addCriterion("spec_count <=", value, "specCount");
            return (Criteria) this;
        }

        public Criteria andSpecCountIn(List<Integer> values) {
            addCriterion("spec_count in", values, "specCount");
            return (Criteria) this;
        }

        public Criteria andSpecCountNotIn(List<Integer> values) {
            addCriterion("spec_count not in", values, "specCount");
            return (Criteria) this;
        }

        public Criteria andSpecCountBetween(Integer value1, Integer value2) {
            addCriterion("spec_count between", value1, value2, "specCount");
            return (Criteria) this;
        }

        public Criteria andSpecCountNotBetween(Integer value1, Integer value2) {
            addCriterion("spec_count not between", value1, value2, "specCount");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNull() {
            addCriterion("product_count is null");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNotNull() {
            addCriterion("product_count is not null");
            return (Criteria) this;
        }

        public Criteria andProductCountEqualTo(Integer value) {
            addCriterion("product_count =", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotEqualTo(Integer value) {
            addCriterion("product_count <>", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThan(Integer value) {
            addCriterion("product_count >", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_count >=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThan(Integer value) {
            addCriterion("product_count <", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThanOrEqualTo(Integer value) {
            addCriterion("product_count <=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountIn(List<Integer> values) {
            addCriterion("product_count in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotIn(List<Integer> values) {
            addCriterion("product_count not in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountBetween(Integer value1, Integer value2) {
            addCriterion("product_count between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotBetween(Integer value1, Integer value2) {
            addCriterion("product_count not between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address_ is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address_ is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address_ =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address_ <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address_ >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address_ >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address_ <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address_ <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address_ like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address_ not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address_ in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address_ not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address_ between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address_ not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeIsNull() {
            addCriterion("am_start_time is null");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeIsNotNull() {
            addCriterion("am_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeEqualTo(Integer value) {
            addCriterion("am_start_time =", value, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeNotEqualTo(Integer value) {
            addCriterion("am_start_time <>", value, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeGreaterThan(Integer value) {
            addCriterion("am_start_time >", value, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("am_start_time >=", value, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeLessThan(Integer value) {
            addCriterion("am_start_time <", value, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeLessThanOrEqualTo(Integer value) {
            addCriterion("am_start_time <=", value, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeIn(List<Integer> values) {
            addCriterion("am_start_time in", values, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeNotIn(List<Integer> values) {
            addCriterion("am_start_time not in", values, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeBetween(Integer value1, Integer value2) {
            addCriterion("am_start_time between", value1, value2, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmStartTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("am_start_time not between", value1, value2, "amStartTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeIsNull() {
            addCriterion("am_end_time is null");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeIsNotNull() {
            addCriterion("am_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeEqualTo(Integer value) {
            addCriterion("am_end_time =", value, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeNotEqualTo(Integer value) {
            addCriterion("am_end_time <>", value, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeGreaterThan(Integer value) {
            addCriterion("am_end_time >", value, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("am_end_time >=", value, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeLessThan(Integer value) {
            addCriterion("am_end_time <", value, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeLessThanOrEqualTo(Integer value) {
            addCriterion("am_end_time <=", value, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeIn(List<Integer> values) {
            addCriterion("am_end_time in", values, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeNotIn(List<Integer> values) {
            addCriterion("am_end_time not in", values, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeBetween(Integer value1, Integer value2) {
            addCriterion("am_end_time between", value1, value2, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andAmEndTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("am_end_time not between", value1, value2, "amEndTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeIsNull() {
            addCriterion("pm_start_time is null");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeIsNotNull() {
            addCriterion("pm_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeEqualTo(Integer value) {
            addCriterion("pm_start_time =", value, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeNotEqualTo(Integer value) {
            addCriterion("pm_start_time <>", value, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeGreaterThan(Integer value) {
            addCriterion("pm_start_time >", value, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pm_start_time >=", value, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeLessThan(Integer value) {
            addCriterion("pm_start_time <", value, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeLessThanOrEqualTo(Integer value) {
            addCriterion("pm_start_time <=", value, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeIn(List<Integer> values) {
            addCriterion("pm_start_time in", values, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeNotIn(List<Integer> values) {
            addCriterion("pm_start_time not in", values, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeBetween(Integer value1, Integer value2) {
            addCriterion("pm_start_time between", value1, value2, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmStartTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("pm_start_time not between", value1, value2, "pmStartTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeIsNull() {
            addCriterion("pm_end_time is null");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeIsNotNull() {
            addCriterion("pm_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeEqualTo(Integer value) {
            addCriterion("pm_end_time =", value, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeNotEqualTo(Integer value) {
            addCriterion("pm_end_time <>", value, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeGreaterThan(Integer value) {
            addCriterion("pm_end_time >", value, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pm_end_time >=", value, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeLessThan(Integer value) {
            addCriterion("pm_end_time <", value, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeLessThanOrEqualTo(Integer value) {
            addCriterion("pm_end_time <=", value, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeIn(List<Integer> values) {
            addCriterion("pm_end_time in", values, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeNotIn(List<Integer> values) {
            addCriterion("pm_end_time not in", values, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeBetween(Integer value1, Integer value2) {
            addCriterion("pm_end_time between", value1, value2, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andPmEndTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("pm_end_time not between", value1, value2, "pmEndTime");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsIsNull() {
            addCriterion("territory_ids is null");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsIsNotNull() {
            addCriterion("territory_ids is not null");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsEqualTo(String value) {
            addCriterion("territory_ids =", value, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsNotEqualTo(String value) {
            addCriterion("territory_ids <>", value, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsGreaterThan(String value) {
            addCriterion("territory_ids >", value, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsGreaterThanOrEqualTo(String value) {
            addCriterion("territory_ids >=", value, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsLessThan(String value) {
            addCriterion("territory_ids <", value, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsLessThanOrEqualTo(String value) {
            addCriterion("territory_ids <=", value, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsLike(String value) {
            addCriterion("territory_ids like", value, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsNotLike(String value) {
            addCriterion("territory_ids not like", value, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsIn(List<String> values) {
            addCriterion("territory_ids in", values, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsNotIn(List<String> values) {
            addCriterion("territory_ids not in", values, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsBetween(String value1, String value2) {
            addCriterion("territory_ids between", value1, value2, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andTerritoryIdsNotBetween(String value1, String value2) {
            addCriterion("territory_ids not between", value1, value2, "territoryIds");
            return (Criteria) this;
        }

        public Criteria andStartIsNull() {
            addCriterion("start_ is null");
            return (Criteria) this;
        }

        public Criteria andStartIsNotNull() {
            addCriterion("start_ is not null");
            return (Criteria) this;
        }

        public Criteria andStartEqualTo(Integer value) {
            addCriterion("start_ =", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotEqualTo(Integer value) {
            addCriterion("start_ <>", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThan(Integer value) {
            addCriterion("start_ >", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_ >=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThan(Integer value) {
            addCriterion("start_ <", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThanOrEqualTo(Integer value) {
            addCriterion("start_ <=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartIn(List<Integer> values) {
            addCriterion("start_ in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotIn(List<Integer> values) {
            addCriterion("start_ not in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartBetween(Integer value1, Integer value2) {
            addCriterion("start_ between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotBetween(Integer value1, Integer value2) {
            addCriterion("start_ not between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
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

        public Criteria andBatchIdIsNull() {
            addCriterion("batch_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(Integer value) {
            addCriterion("batch_id =", value, "batchId");
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
