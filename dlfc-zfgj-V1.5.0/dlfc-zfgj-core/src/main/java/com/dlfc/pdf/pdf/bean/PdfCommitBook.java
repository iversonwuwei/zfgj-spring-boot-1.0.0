package com.dlfc.pdf.pdf.bean;

import com.dlfc.pdf.pdf.AbstractDocumentVo;

public class PdfCommitBook extends AbstractDocumentVo{

	private String companyName; // 公司名称
	private String imageUrl;
	
	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
