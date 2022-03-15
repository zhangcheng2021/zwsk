package com.hsmap.yuezhihui.entity.core;

public class CoreTemplateRelation {
    /**
     * 模板下载编号
     */
    private String code;
    /**
     * 文件磁盘物理地址
     */
    private String filePath;
    /**
     * 下载的文件名称
     */
    private String downloadName;
    /**
     * 描述
     */
    private String describes;
    /**
     * 能下载的角色编号串
     */
    private String roleIds;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDownloadName() {
        return downloadName;
    }

    public void setDownloadName(String downloadName) {
        this.downloadName = downloadName;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
