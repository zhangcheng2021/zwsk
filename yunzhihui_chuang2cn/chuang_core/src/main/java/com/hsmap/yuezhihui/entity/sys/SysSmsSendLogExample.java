package com.hsmap.yuezhihui.entity.sys;

import com.hsmap.yuezhihui.base.entity.BaseExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysSmsSendLogExample  extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysSmsSendLogExample() {
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

        public Criteria andMsgIsNull() {
            addCriterion("msg_ is null");
            return (Criteria) this;
        }

        public Criteria andMsgIsNotNull() {
            addCriterion("msg_ is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEqualTo(String value) {
            addCriterion("msg_ =", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotEqualTo(String value) {
            addCriterion("msg_ <>", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThan(String value) {
            addCriterion("msg_ >", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("msg_ >=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThan(String value) {
            addCriterion("msg_ <", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("msg_ <=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLike(String value) {
            addCriterion("msg_ like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotLike(String value) {
            addCriterion("msg_ not like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgIn(List<String> values) {
            addCriterion("msg_ in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotIn(List<String> values) {
            addCriterion("msg_ not in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgBetween(String value1, String value2) {
            addCriterion("msg_ between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotBetween(String value1, String value2) {
            addCriterion("msg_ not between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNull() {
            addCriterion("send_status is null");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNotNull() {
            addCriterion("send_status is not null");
            return (Criteria) this;
        }

        public Criteria andSendStatusEqualTo(Integer value) {
            addCriterion("send_status =", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotEqualTo(Integer value) {
            addCriterion("send_status <>", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThan(Integer value) {
            addCriterion("send_status >", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_status >=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThan(Integer value) {
            addCriterion("send_status <", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThanOrEqualTo(Integer value) {
            addCriterion("send_status <=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusIn(List<Integer> values) {
            addCriterion("send_status in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotIn(List<Integer> values) {
            addCriterion("send_status not in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusBetween(Integer value1, Integer value2) {
            addCriterion("send_status between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("send_status not between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andRptTimeIsNull() {
            addCriterion("rpt_time is null");
            return (Criteria) this;
        }

        public Criteria andRptTimeIsNotNull() {
            addCriterion("rpt_time is not null");
            return (Criteria) this;
        }

        public Criteria andRptTimeEqualTo(Date value) {
            addCriterion("rpt_time =", value, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRptTimeNotEqualTo(Date value) {
            addCriterion("rpt_time <>", value, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRptTimeGreaterThan(Date value) {
            addCriterion("rpt_time >", value, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRptTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rpt_time >=", value, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRptTimeLessThan(Date value) {
            addCriterion("rpt_time <", value, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRptTimeLessThanOrEqualTo(Date value) {
            addCriterion("rpt_time <=", value, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRptTimeIn(List<Date> values) {
            addCriterion("rpt_time in", values, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRptTimeNotIn(List<Date> values) {
            addCriterion("rpt_time not in", values, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRptTimeBetween(Date value1, Date value2) {
            addCriterion("rpt_time between", value1, value2, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRptTimeNotBetween(Date value1, Date value2) {
            addCriterion("rpt_time not between", value1, value2, "rptTime");
            return (Criteria) this;
        }

        public Criteria andRtpStatusIsNull() {
            addCriterion("rtp_status is null");
            return (Criteria) this;
        }

        public Criteria andRtpStatusIsNotNull() {
            addCriterion("rtp_status is not null");
            return (Criteria) this;
        }

        public Criteria andRtpStatusEqualTo(Integer value) {
            addCriterion("rtp_status =", value, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andRtpStatusNotEqualTo(Integer value) {
            addCriterion("rtp_status <>", value, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andRtpStatusGreaterThan(Integer value) {
            addCriterion("rtp_status >", value, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andRtpStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("rtp_status >=", value, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andRtpStatusLessThan(Integer value) {
            addCriterion("rtp_status <", value, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andRtpStatusLessThanOrEqualTo(Integer value) {
            addCriterion("rtp_status <=", value, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andRtpStatusIn(List<Integer> values) {
            addCriterion("rtp_status in", values, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andRtpStatusNotIn(List<Integer> values) {
            addCriterion("rtp_status not in", values, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andRtpStatusBetween(Integer value1, Integer value2) {
            addCriterion("rtp_status between", value1, value2, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andRtpStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("rtp_status not between", value1, value2, "rtpStatus");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Integer value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Integer value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Integer value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Integer value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Integer> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Integer> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
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

        public Criteria andWordSizeIsNull() {
            addCriterion("word_size_ is null");
            return (Criteria) this;
        }

        public Criteria andWordSizeIsNotNull() {
            addCriterion("word_size_ is not null");
            return (Criteria) this;
        }

        public Criteria andWordSizeEqualTo(Integer value) {
            addCriterion("word_size_ =", value, "wordSize");
            return (Criteria) this;
        }

        public Criteria andWordSizeNotEqualTo(Integer value) {
            addCriterion("word_size_ <>", value, "wordSize");
            return (Criteria) this;
        }

        public Criteria andWordSizeGreaterThan(Integer value) {
            addCriterion("word_size_ >", value, "wordSize");
            return (Criteria) this;
        }

        public Criteria andWordSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("word_size_ >=", value, "wordSize");
            return (Criteria) this;
        }

        public Criteria andWordSizeLessThan(Integer value) {
            addCriterion("word_size_ <", value, "wordSize");
            return (Criteria) this;
        }

        public Criteria andWordSizeLessThanOrEqualTo(Integer value) {
            addCriterion("word_size_ <=", value, "wordSize");
            return (Criteria) this;
        }

        public Criteria andWordSizeIn(List<Integer> values) {
            addCriterion("word_size_ in", values, "wordSize");
            return (Criteria) this;
        }

        public Criteria andWordSizeNotIn(List<Integer> values) {
            addCriterion("word_size_ not in", values, "wordSize");
            return (Criteria) this;
        }

        public Criteria andWordSizeBetween(Integer value1, Integer value2) {
            addCriterion("word_size_ between", value1, value2, "wordSize");
            return (Criteria) this;
        }

        public Criteria andWordSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("word_size_ not between", value1, value2, "wordSize");
            return (Criteria) this;
        }

        public Criteria andMsgNumberIsNull() {
            addCriterion("msg_number is null");
            return (Criteria) this;
        }

        public Criteria andMsgNumberIsNotNull() {
            addCriterion("msg_number is not null");
            return (Criteria) this;
        }

        public Criteria andMsgNumberEqualTo(Integer value) {
            addCriterion("msg_number =", value, "msgNumber");
            return (Criteria) this;
        }

        public Criteria andMsgNumberNotEqualTo(Integer value) {
            addCriterion("msg_number <>", value, "msgNumber");
            return (Criteria) this;
        }

        public Criteria andMsgNumberGreaterThan(Integer value) {
            addCriterion("msg_number >", value, "msgNumber");
            return (Criteria) this;
        }

        public Criteria andMsgNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_number >=", value, "msgNumber");
            return (Criteria) this;
        }

        public Criteria andMsgNumberLessThan(Integer value) {
            addCriterion("msg_number <", value, "msgNumber");
            return (Criteria) this;
        }

        public Criteria andMsgNumberLessThanOrEqualTo(Integer value) {
            addCriterion("msg_number <=", value, "msgNumber");
            return (Criteria) this;
        }

        public Criteria andMsgNumberIn(List<Integer> values) {
            addCriterion("msg_number in", values, "msgNumber");
            return (Criteria) this;
        }

        public Criteria andMsgNumberNotIn(List<Integer> values) {
            addCriterion("msg_number not in", values, "msgNumber");
            return (Criteria) this;
        }

        public Criteria andMsgNumberBetween(Integer value1, Integer value2) {
            addCriterion("msg_number between", value1, value2, "msgNumber");
            return (Criteria) this;
        }

        public Criteria andMsgNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_number not between", value1, value2, "msgNumber");
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
