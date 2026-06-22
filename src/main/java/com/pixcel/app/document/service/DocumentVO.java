package com.pixcel.app.document.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class DocumentVO {
	
	private String documentId;
	private String projectId;
	private String documentCategoryId;
	private String versionId;
	private String milestoneId;
	private String createdBy;
	private String title;
	private String description;
	private String statusCode;
	private String statusName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt; 
	private Date updatedAt;
	private String documentVersionId;
	
}
