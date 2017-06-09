/**
* @name: CmsArticleService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 获取文章信
*
* @version: 1.0
* @date : 2016年4月27日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年4月27日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.utils.SpringContextHolder;
import com.dlfc.zfgj.entity.CmsArticle;
import com.dlfc.zfgj.mapper.CmsArticleMapper;

/**
 * @name: CmsArticleService
 * @description: 获取文章信息
 * @version 1.0
 * @author yuanjw
 *
 */
@Service("CmsArticleService")
public class CmsArticleService {
	/** 文章持久层对象 */
	private static CmsArticleMapper articleMapper = SpringContextHolder.getBean("cmsArticleMapper");

	/**
	 * 获取公告列表
	 * 
	 * @param categoryId
	 *            栏目ID
	 * @return 文章对象列表
	 */
	public static List<CmsArticle> getNotice(String categoryId) {
		CmsArticle cmsArticle = new CmsArticle();
		cmsArticle.setCategoryId(categoryId);
		// 获取文章对象列表
		List<CmsArticle> articleList = articleMapper.getNotice(cmsArticle);
		return articleList;
	}

	/**
	 * 获取公告详情
	 * 
	 * @param id
	 *            文章ID
	 * @return 文章对象
	 */
	public CmsArticle getNoticeDetail(String id) {
		CmsArticle cmsArticle = new CmsArticle();
		cmsArticle.setId(id);
		// 获取文章
		CmsArticle article = articleMapper.getNoticeDetail(cmsArticle);
		return article;
	}

	/**
	 * 分页获取文章列表
	 * 
	 * @param page
	 *            文章分页对象
	 * @param cmsArticle
	 *            文章对象
	 * @return 文章对象列表
	 */
	public BasePage<CmsArticle> getList(BasePage<CmsArticle> page, CmsArticle cmsArticle) {
		cmsArticle.setPage(page);
		page.setList(articleMapper.getList(cmsArticle));
		return page;
	}

	/**
	 * 获取详情内容
	 * 
	 * @param id
	 *            文章ID
	 * @return 文章对象列表
	 */
	public List<CmsArticle> getInfoContent(String id) {
		return articleMapper.getInfoContent(id);
	}

}