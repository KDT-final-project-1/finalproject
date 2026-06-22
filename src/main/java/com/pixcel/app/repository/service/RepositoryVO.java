package com.pixcel.app.repository.service;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RepositoryVO {
	private String fileId;
	private String projectId;
	private String versionId;
	private String fileCode;
	private String originalName;
	private String storedName;
	private String filePath;
	private String fileSize;
	private String uploadUserId;
	private Date uploadDate;
	private String fileVersion;
	private String fileUseYn;
	private String connectAddress;
}
