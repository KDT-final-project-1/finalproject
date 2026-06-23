package com.pixcel.app.codevalue.service;

import java.util.List;
import java.util.Map;

public interface CodeValueService {

	// 코드값 전체 조회
	List<CodeValueVO> getCodeValueList(String userId);

	// 검색조건에 맞는 사용자별 코드값 목록을 조회한다.
	List<CodeValueVO> getCodeValueSearchList(CodeValueVO searchVO);

	// 특정 그룹 코드값 조회
	List<CodeValueVO> getCodeValueListByGroup(String userId, String settingGroupName);

	// 화면 출력용 그룹별 코드값 조회
	Map<String, List<CodeValueVO>> getCodeValueGroupMap(String userId);

	// 검색 결과를 화면 출력용 그룹별 코드값으로 변환한다.
	Map<String, List<CodeValueVO>> getCodeValueSearchGroupMap(CodeValueVO searchVO);

	// 코드값 단건 조회
	CodeValueVO getCodeValueDetail(String settingCodeId, String userId);

	// 코드값 등록
	void createCodeValue(CodeValueVO codeValue, String userId);

	// 기본값 설정
	void setDefaultCodeValue(String settingCodeId, String userId);

	// 기본값 취소
	void cancelDefaultCodeValue(String settingCodeId, String userId);

	// 삭제 시 대체값 선택이 필요한지 확인
	boolean isReplacementRequired(String settingCodeId, String userId);

	// 삭제 시 대체 가능한 코드값 목록 조회
	List<CodeValueVO> getReplacementCodeValueList(String deleteSettingCodeId, String userId);

	// 코드값 단순 삭제
	void removeCodeValue(String settingCodeId, String userId);

	// 대체 코드값으로 변경 후 삭제
	void removeCodeValueWithReplacement(String deleteSettingCodeId, String replaceSettingCodeId, String userId);
}