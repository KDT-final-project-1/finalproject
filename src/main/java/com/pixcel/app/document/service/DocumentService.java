package com.pixcel.app.document.service;

import java.util.List;

public interface DocumentService {
	
	public List<DocumentVO> findAll();
	public int addDocument(DocumentVO documentVO);
	public List<DocumentHistoryVO> findHistoryAll();
	
}
