package com.mycom.project.apt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SidoGugunCodeDto : �õ�, ��������", description = "�õ�, ������ �̸��� ��Ÿ����.")
public class SidoGugunCodeDto {

	@ApiModelProperty(value = "�õ��ڵ�")
	private String sidoCode;
	@ApiModelProperty(value = "�õ��̸�")
	private String sidoName;
	@ApiModelProperty(value = "�����ڵ�")
	private String gugunCode;
	@ApiModelProperty(value = "�����̸�")
	private String gugunName;

	public String getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
}
