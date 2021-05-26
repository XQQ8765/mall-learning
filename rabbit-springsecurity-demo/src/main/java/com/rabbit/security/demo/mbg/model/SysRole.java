package com.rabbit.security.demo.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysRole implements Serializable {
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "角色code")
    private String roleCode;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色说明")
    private String roleDescription;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleCode=").append(roleCode);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleDescription=").append(roleDescription);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}