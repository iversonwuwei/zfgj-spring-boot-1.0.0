package com.dlfc.zfgj.common.pdf.pdf;

import com.dlfc.zfgj.common.pdf.utils.JacksonBinder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板中需要的数据视图 抽象类
 * @ClassName: AbstractDocumentVo
 * @Description: 模板中需要的数据视图 抽象类
 * @author lihengjun
 * 修改时间： 2013年11月5日 下午3:22:35
 * 修改内容：新建
 */

@Component
public abstract class AbstractDocumentVo implements DocumentVo{
	/**
	 * ,填充模板中数据,获取模板数据map
	 * @Title: fillDataMap
	 * @Description:  获取模板数据map
	 * @return
	 * @author lihengjun
	 * 修改时间： 2013年11月5日 上午11:19:29
	 * 修改内容：新建
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> fillDataMap(){
		Map<String, Object> map;
		
		DocumentVo vo = this.getDocumentVo();
		map = JacksonBinder.buildNonDefaultBinder().convertValue(vo, HashMap.class);
		
		return map;
	}
	
	protected DocumentVo getDocumentVo() {
		return this;
	}

}
