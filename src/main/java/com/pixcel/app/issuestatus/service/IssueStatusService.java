package com.pixcel.app.issuestatus.service;

import java.util.List;

public interface IssueStatusService {

    List<IssueStatusVO> getIssueStatusList();

    IssueStatusVO getIssueStatusDetail(String issueStatusId);

    void createIssueStatus(IssueStatusVO issueStatus);

    void modifyIssueStatus(IssueStatusVO issueStatus);

    void removeIssueStatus(String issueStatusId);
}