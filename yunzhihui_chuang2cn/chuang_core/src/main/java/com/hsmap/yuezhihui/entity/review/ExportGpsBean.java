package com.hsmap.yuezhihui.entity.review;

import com.hsmap.yuezhihui.common.DecimalUtil;

import java.util.ArrayList;
import java.util.List;

public class ExportGpsBean {

    private String groupName;
    private List<Product> products = new ArrayList<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 返回第一个项目的专家组列表，后续的都相同
     *
     * @return
     */
    public List<ExportGpsBean.Spec> getSpecFirst() {
        return products.get(0).getSpecs();
    }

    public List<ExportGpsBean.Product> getProducts() {
        return products;
    }

    /**
     * 加入新的项目
     *
     * @param dos
     */
    public Product addProduct(ComplexGPSDo dos) {
        Product product = new Product(dos);
        products.add(product);
        return product;
    }

    public class Product {
        private Integer userId;
        private String userName;
        private String productName;
        private String typeName;
        private String recommend;
        private List<Spec> specs = new ArrayList<>();
        private String score;
        private String level;

        /**
         * 加入新的专家
         *
         * @param specId
         * @param specName
         * @param score
         */
        public void addSpec(Integer specId, String specName, Integer score, String remark) {
            specs.add(new Spec(specId, specName, score, remark));
        }

        /**
         * 计算积分
         */
        public void markScore() {
            int max = 0, min = 100, sum = 0;
            boolean isComplete = true;
            for (Spec s : specs) {
                if (s.getScore() == null) {
                    isComplete = false;
                    break;
                }
                if (s.getScore() < min)
                    min = s.getScore();
                if (s.getScore() > max)
                    max = s.getScore();
                sum += s.getScore();
            }
            if (isComplete) {
                int r = specs.size();
                //更新为以完成，并且打分，
                //R=1，P=R.score = AVG(R.score)
                //R=2, P=AVG(R.score)
                //R>2, (SUM(R.score)-max-min)/(R.size-2)
                double s = 0d;
                if (r < 2) {
                    s = sum * 1.0 / r;
                } else {
                    s = (sum - max - min) * 1.0 / (r - 2);
                }
                score = DecimalUtil.format00(s);
                //≥85分的为A类，属优先推荐项目；75≤分数＜85分为B类，属推荐项目；65≤分数＜75为C类，属一般推荐项目；65分以下为D类 属不推荐项目。
                if (s >= 85) {
                    level = "A类";
                } else if (s >= 75) {
                    level = "B类";
                } else if (s >= 65) {
                    level = "C类";
                } else {
                    level = "D类";
                }
            } else {
                score = "-";
                level = "-";
            }
        }

        public Product(ComplexGPSDo dos) {
            this.userId = dos.getUserId();
            this.userName = dos.getUserName();
            this.productName = dos.getProductName();
            this.typeName = dos.getTypeName();
            this.recommend = dos.getRecommend();
        }

        public String getScore() {
            return score;
        }

        public String getLevel() {
            return level;
        }

        public Integer getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getProductName() {
            return productName;
        }

        public String getTypeName() {
            return typeName;
        }

        public String getRecommend() {
            return recommend;
        }

        public List<Spec> getSpecs() {
            return specs;
        }

    }

    public class Spec {
        private Integer specId;
        private String specName;
        private Integer score;
        private String specRemark;

        public Spec(Integer specId, String specName, Integer score, String specRemark) {
            this.specId = specId;
            this.specName = specName;
            this.score = score;
            this.specRemark = specRemark;
        }

        public Integer getSpecId() {
            return specId;
        }

        public String getSpecName() {
            return specName;
        }

        public Integer getScore() {
            return score;
        }

        public String getSpecRemark() {
            return specRemark;
        }
    }
}
