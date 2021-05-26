package com.rabbit.security.demo.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysPermission implements Serializable {
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "权限code")
    private String permissionCode;

    @ApiModelProperty(value = "权限名")
    private String permissionName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", permissionCode=").append(permissionCode);
        sb.append(", permissionName=").append(permissionName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}