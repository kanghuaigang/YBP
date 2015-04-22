package com.yjy.bsq.service.account;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Encodes;

import com.google.common.collect.Lists;
import com.yjy.bsq.entity.account.Menu;
import com.yjy.bsq.entity.account.Privilege;
import com.yjy.bsq.entity.account.Role;
import com.yjy.bsq.entity.account.UserInfo;
import com.yjy.bsq.repository.account.MenuDao;
import com.yjy.bsq.repository.account.PrivilegeDao;
import com.yjy.bsq.repository.account.RoleDao;
import com.yjy.bsq.repository.account.UserInfoDao;
import com.yjy.bsq.service.ServiceException;
import com.yjy.bsq.service.account.ShiroDbRealm.ShiroUser;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class AccountService {

	@PersistenceContext
	private EntityManager em;

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private static final String ADMIN_ID = "1";

	private final static Logger LOGGER = LoggerFactory
			.getLogger(AccountService.class);

	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private PrivilegeDao privilegeDao;
	@Autowired
	private RoleDao roleDao;

	private Clock clock = Clock.DEFAULT;

	public Page<UserInfo> getAllUser(int start) {

		// StringBuffer sbf=new StringBuffer();
		// sbf.append("select pname from ASC_USER_INFO limit ").append(b);

		// Pageable pageable = new PageRequest(start,10);
		// return userInfoDao.findAll(pageable);
		//
		// Page<UserInfo> page=null;
		PageRequest pageRequest = new PageRequest(start, 10, new Sort(
				Sort.Direction.DESC, "registerDate"));
		// if(null!=user){
		// Specification<UserInfo> spec=buildUserInfoSpecification(user);
		// page=userInfoDao.findAll(spec,pageRequest);
		// }else{
		return userInfoDao.findAll(pageRequest);
		// }
	}

	public UserInfo getUser(Integer id) {
		return userInfoDao.findOne(id);
	}

	public UserInfo findUserByLoginName(String loginName) {
		return userInfoDao.findByLoginName(loginName);
	}

	@Transactional(readOnly = false)
	public void registerUser(UserInfo user) {
		entryptPassword(user);
		user.setRegisterDate(clock.getCurrentDate());
		userInfoDao.save(user);
	}

	@Transactional(readOnly = false)
	public void updateUser(UserInfo user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		this.em.clear();
		userInfoDao.save(user);
	}

	@Transactional(readOnly = false)
	public void sucLoginUser(UserInfo user) {
		userInfoDao.save(user);
	}

	@Transactional(readOnly = false)
	public void deleteUser(Integer id) {
		if (isSupervisor(id)) {
			LOGGER.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		userInfoDao.delete(id);
	}

	@Transactional(readOnly = false)
	public void addUserRoles(Integer userId, List<String> roleIds) {
		UserInfo user = this.getUser(userId);
		user.getRoles().clear();
		for (String roleId : roleIds) {
			if (StringUtils.isNotBlank(roleId))
				user.addRole(roleDao.findOne(roleId));
		}
		this.updateUser(user);
	}

	public List<Role> getUserRoles(Integer id) {
		UserInfo user = this.getUser(id);
		return user.getRoles();
	}

	public List<Menu> getUserMenus(Integer id) {
		List<Role> roles = getUserRoles(id);
		List<Menu> pers = Lists.newArrayList();
		for (Role role : roles) {
			pers.addAll(role.getMenus());
		}
		return pers;
	}

	public List<Role> getAdminRole() {
		return (List<Role>) roleDao.findAll();
	}

	public List<String> getAdminPermission() {
		List<String> perStr = Lists.newArrayList();
		List<Menu> menus = menuDao.findAll();
		List<Privilege> privileges = privilegeDao.findAll();
		for (Menu m : menus) {
			for (Privilege p : privileges) {
				StringBuilder sb = new StringBuilder();
				sb.append(m.getMenuCode()).append("_p:")
						.append(p.getPrivilegeCode());
				perStr.add(sb.toString());
			}
		}
		return perStr;
	}
	
	public void setClock(Clock clock) {
		this.clock = clock;
	}
	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Integer id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(UserInfo user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(),
				salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}


	public void batchUpdateVer(String ver) {
		Query query = em.createQuery("update UserInfo set version=:ver")
				.setParameter("ver", ver);
		query.executeUpdate();
	}

	private Specification<UserInfo> buildUserInfoSpecification(
			final UserInfo user) {
		Specification<UserInfo> spec = new Specification<UserInfo>() {
			@Override
			public Predicate toPredicate(Root<UserInfo> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				Predicate p1 = builder.notEqual(root.<String> get("id"),
						ADMIN_ID);
				predicates.add(p1);
				if (StringUtils.isNotBlank(user.getUserName())) {
					Predicate p = builder.like(root.<String> get("userName"),
							"%" + user.getUserName() + "%");
					predicates.add(p);
				}
				if (StringUtils.isNotBlank(user.getLoginName())) {
					Predicate p = builder.like(root.<String> get("loginName"),
							"%" + user.getLoginName() + "%");
					predicates.add(p);
				}
				if (predicates.size() > 0) {
					return builder.and(predicates
							.toArray(new Predicate[predicates.size()]));
				}
				return builder.conjunction();
			}

		};
		return spec;
	}
}
