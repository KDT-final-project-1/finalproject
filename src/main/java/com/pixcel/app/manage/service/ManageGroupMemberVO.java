package com.pixcel.app.manage.service;

import lombok.Data;

@Data
public class ManageGroupMemberVO {

    private String projectMemberId;
    private String projectId;
    private String projectGroupId;
    private String teamMemberId;
    private String roleId;

    private String userId;
    private String loginId;
    private String userName;
    private String email;
    private String phone;

    private String teamId;
    private String teamName;

    private String roleName;
    private String groupName;
}
