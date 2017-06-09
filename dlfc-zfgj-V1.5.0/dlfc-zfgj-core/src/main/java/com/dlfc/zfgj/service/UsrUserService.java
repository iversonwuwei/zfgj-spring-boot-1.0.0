/**
 * @name: UsrUserService.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 
 *
 * @version: 1.0
 * @date : 2015年11月5日 
 * @author: Alex.Ge 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年11月5日       Alex.Ge        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.utils.CacheUtils;
import com.dlfc.admin.common.utils.SpringContextHolder;
import com.dlfc.admin.modules.sys.entity.Menu;
import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.SysPayChannel;
import com.dlfc.zfgj.entity.SysPayChannelExample;
import com.dlfc.zfgj.entity.SysPaychannelBanks;
import com.dlfc.zfgj.entity.SysPaychannelBanksExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.entity.UsrUserExample;
import com.dlfc.zfgj.entity.UsrUserExample.Criteria;
import com.dlfc.zfgj.mapper.SysMenuMapper;
import com.dlfc.zfgj.mapper.SysPayChannelMapper;
import com.dlfc.zfgj.mapper.SysPaychannelBanksMapper;
import com.dlfc.zfgj.mapper.SysRoleMapper;
import com.dlfc.zfgj.mapper.UsrUserBankcardMapper;
import com.dlfc.zfgj.mapper.UsrUserMapper;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: UserService
 * @description: 用户信息
 * @version 1.0
 * @author Alex.Ge
 *
 */
@Service("usrUserService")
public class UsrUserService {
	/** log对象 */
	private final static Logger logger = LoggerFactory.getLogger(UsrUserService.class);
	// 用户缓存
	public static final String USER_CACHE = "userCache";
	// 用户登录名缓存
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	// 用户ID缓存
	public static final String USER_CACHE_ID_ = "id_";
	// 用户菜单列表缓存
	public static final String CACHE_MENU_LIST = "menuList";

	/** 用户持久层对象 */
	@Autowired
	private UsrUserMapper dao;
	/** 角色持久层对象 */
	@Autowired
	private SysRoleMapper sysRoleMapper;
	/** 菜单持久层对象 */
	@Autowired
	private SysMenuMapper sysMenuMapper;
	/** 用户持久层对象 */
	@Autowired
	private UsrUserMapper usrUserMapper;
	/** 用户银行卡持久层对象 */
	@Autowired
	private UsrUserBankcardMapper usrUserBankcardMapper;
	/** 付费持久层对象 */
	@Autowired
	private SysPayChannelMapper sysPayChannelMapper;
	/** 付费银行卡持久层对象 */
	@Autowired
	private SysPaychannelBanksMapper sysPaychannelBanksMapper;

	/**
	 * 查询用户信息
	 * 
	 * @param loginName 登录名
	 * @return 用户对象
	 */
	public User getUserByLoginName(String loginName) {
		// 联合查询用户信息,获取系统用户信息
		User user = dao.getByLoginName(new User(null, loginName));
		if (user == null) {
			return null;
		}
		// 获得权限列表以后重构
		user.setRoleList(sysRoleMapper.findList(new Role(user)));
		CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
		CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		// }
		return user;

	}

	/**
	 * 是否申请过经纪人
	 * 
	 * @param loginName 登录名
	 * @return true:申请过，false:未申请过
	 */
	public Boolean isEmpCerted(User user) {
		return dao.isEmpCerted(user) != 0;
	}

	/**
	 * 是否在审核中
	 * 
	 * @param user 用户对象
	 * @return "0":审核中，"1":审核通过，"2":审核失败
	 */
	public String isEmpCerting(User user) {
		return dao.isEmpCerting(user);
	}

	/**
	 * 获取菜单对象列表
	 * 
	 * @param menu 菜单对象
	 * @return 菜单对象列表
	 */
	public List<Menu> getMenuList(Menu menu) {
		@SuppressWarnings("unchecked")
		// 获取菜单列表
		List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
		if (menuList == null) {
			Menu m = new Menu();
			m.setUserId(UserUtils.getUser().getId());
			// 获取经纪人系统菜单，之后重构
			menuList = sysMenuMapper.findByUserId(menu);
			// 插入Cacahe
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;

	}

	/**
	 * 获取经纪人当前用户授权菜单
	 * 
	 * @param menu 菜单对象
	 * @return 菜单对象列表
	 */
	public static List<Menu> getMenuList2(Menu menu) {
		menu.getUserId();
		// 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
		SysMenuMapper dao = SpringContextHolder.getBean("sysMenuMapper");
		// 获取经纪人系统菜单之后重构
		List<Menu> menuList = dao.findByUserId(menu);

		return menuList;
	}

	/***
	 * 获取session
	 * 
	 * @return session
	 */
	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}

		} catch (InvalidSessionException e) {
			logger.error("UsrUserService getSession", e);

		}
		return null;
	}

	// ============== User Cache ==============

	/**
	 * 获取User Cache
	 * 
	 * @param key 缓存
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取User Cache
	 * @param key 缓存
	 * @param defaultValue 未查找到对象
	 * @return Object:查找到了对象，defaultValue:未查找到对象
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 插入Cacahe
	 * 
	 * @param key 菜单对象列表缓存
	 * @param value 菜单对象列表
	 */
	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 删除Cacahe
	 * 
	 * @param key 缓存
	 */
	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}

	/**
	 * 根据id取得用户信息
	 * 
	 * @param id 用户ID
	 * @return 用户对象
	 */
	public UsrUser findById(String id) {
		return dao.selectByPrimaryKey(id);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param userExample 查询条件对象
	 * @return 用户对象列表
	 */
	public List<UsrUser> listUserInfo(UsrUserExample userExample) {
		return dao.selectByExample(userExample);
	}

	/**
	 * 根据条件更新
	 * @param usr 用户对象
	 * @param userExample 查询条件对象
	 * @return 无
	 */
	public int update(UsrUser usr, UsrUserExample userExample) {
		return dao.updateByExampleSelective(usr, userExample);
	}

	/**
	 * 更新数据
	 * 
	 * @param usr 用户对象
	 * @return 更新成功失败状态
	 */
	public int updateById(UsrUser usr) {
		return dao.updateByPrimaryKeySelective(usr);
	}

	/**
	 * 判断邮箱是否绑定过
	 * @param email 邮箱
	 * @return true:绑定过，false:未绑定过
	 */
	public boolean isMailBinded(String email) {
		if (dao.isMailBinded(email) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断手机是否绑定过
	 * 
	 * @param Mobile 手机号
	 * @return true:绑定过，false:未绑定过
	 */
	public boolean isMobileBinded(String Mobile) {
		if ((int) dao.isMobileBinded(Mobile) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过用户信息ID查询用户对象
	 * 
	 * @param perId 用户信息ID
	 * @return 用户对象
	 */
	public UsrUser getUserByPerId(String perId) {
		UsrUser user = null;
		UsrUserExample example = new UsrUserExample();
		Criteria cri = example.createCriteria();
		cri.andPerIdEqualTo(perId);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		List<UsrUser> list = usrUserMapper.selectAllByExample(example);
		if (list != null && list.size() > 0) {
			user = list.get(0);
		}
		return user;
	}

	/**
	 * 保存用户对象
	 * 
	 * @param user 用户对象
	 */
	public void save(UsrUser user) {
		user.preInsert();
		usrUserMapper.insertSelective(user);
	}

	/**
	 * 通过手机查找用户对象
	 * 
	 * @param mobile 手机号
	 * @return 用户对象
	 */
	public UsrUser getUserByMobile(String mobile) {
		UsrUserExample example = new UsrUserExample();
		Criteria cri = example.createCriteria();
		cri.andMobileEqualTo(mobile);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		List<UsrUser> list = usrUserMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 判断银行卡是否绑定过
	 * 
	 * @param cardNum 银行卡号
	 * @return true:绑定，false:未绑定
	 */
	public boolean isCardBinded(String cardNum) {
		if ((int) usrUserBankcardMapper.isCardBinded(cardNum) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过code查id
	 * 
	 * @param code 支付渠道id
	 * @return 银行卡id
	 */
	public String selectIdByCode(String code) {
		SysPayChannelExample spcExample = new SysPayChannelExample();
		com.dlfc.zfgj.entity.SysPayChannelExample.Criteria crit = spcExample.createCriteria();
		crit.andDeleteFlgEqualTo((short) 0);
		crit.andCodeEqualTo(code);
		List<SysPayChannel> spc = sysPayChannelMapper.selectByExample(spcExample);
		if (spc != null && spc.size() != 0) {
			return spc.get(0).getId();
		} else {
			return null;
		}
	}

	/**
	 * 通过basecode和当前支持的银行卡和卡类型认证接口查询bankcode
	 * 
	 * @param baseCode 银行缩写
	 * @param pcId 银行ID
	 * @param cardType 卡类型
	 * @return 支付银行对象
	 */
	public SysPaychannelBanks selectBankCodeByPayway(String baseCode, String pcId, String cardType) {
		SysPaychannelBanksExample spbExample = new SysPaychannelBanksExample();
		com.dlfc.zfgj.entity.SysPaychannelBanksExample.Criteria crit = spbExample.createCriteria();
		crit.andDeleteFlgEqualTo((short) 0);
		crit.andBaseCodeEqualTo(baseCode);
		crit.andPcIdEqualTo(pcId);
		crit.andCardTypeEqualTo(cardType);
		List<SysPaychannelBanks> spb = sysPaychannelBanksMapper.selectByExample(spbExample);
		if (spb != null && spb.size() != 0) {
			return spb.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 判断用户名是否注册过
	 * 
	 * @param userName 用户名
	 * @return true:注册，false:未被注册
	 */
	public boolean isUserNameBinded(String userName) {
		if (usrUserMapper.isUserNameBinded(userName) > 0) {
			return true;
		} else {
			return false;
		}
	}
}
