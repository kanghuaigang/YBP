package com.asc.bdhub.service.account;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asc.bdhub.common.base.BaseDao;
import com.asc.bdhub.entity.account.Menu;
import com.asc.bdhub.entity.account.Privilege;
import com.asc.bdhub.repository.account.MenuDao;
import com.asc.bdhub.repository.account.PrivilegeDao;

/**
 * 菜单管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional(readOnly = true)
public class MenuService {
	
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private PrivilegeDao privilegeDao;
	
	public Menu getMeun(String id){
		return menuDao.findOne(id);
	}
	@Transactional(readOnly = false)
	public Menu addMenu(Menu per){
		return menuDao.save(per);
	}
	
	public Page<Menu> getAllMenu(int pageNum, int pageSize){
		PageRequest pageRequest=new PageRequest(pageNum-1, pageSize,new Sort(Sort.Direction.DESC, "createDate"));
		Page<Menu> page=menuDao.findAll(pageRequest);
		return page;
	}
	
	public List<Menu> getAllMenu(){
		return menuDao.findAll();
	}
	
	public List<Menu> getParentMenu(){
		Menu menu = new Menu();
		menu.setParentId(null);
		StringBuffer sbf=new StringBuffer();
		sbf.append(" select * from YSQ_MENU where Parent_Id is null order by menu_index");
		List<Menu> menuList=baseDao.findListToBean(sbf.toString(), null, Menu.class);
		
		if(menuList!=null && !menuList.isEmpty()){
			for (Menu me : menuList) {
				String sb="select * from YSQ_MENU where Parent_Id ="+me.getId()+" order by menu_index";
				List<Menu> list=baseDao.findListToBean(sb, null, Menu.class);
				me.setChildren(list);
			}
		}
		
		return menuList;
	}
	
	public List<Menu> getChildrenMenu(Integer parentId){
		Menu menu = new Menu();
		menu.setParentId(parentId);
		Specification<Menu> spec = buildMenuSpecification(menu);
		return menuDao.findAll(spec,new Sort(Sort.Direction.ASC, "menuIndex"));
	}
	
	@Transactional(readOnly = false)
	public void deleteMenu(String id){
		menuDao.delete(id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public void addMenuPrivilege(String menuId,List<String> privilegeIds){
		Menu menu=menuDao.findOne(menuId);
		List<Privilege> menuPri=menu.getMenuPrivilege();
		List<String> priIds=(List<String>) CollectionUtils.collect(menuPri, new Transformer(){
			public Object transform(Object arg0) {
				Privilege u = (Privilege) arg0;
                return u.getId();
            }
		});
		List<String> delIds=ListUtils.subtract(priIds, privilegeIds);
		for (String id : delIds) {
			menu.removePrivilege(privilegeDao.findOne(id));
		}
		for (String id : privilegeIds) {
			if(StringUtils.isNotBlank(id)&&!priIds.contains(id)){
				menu.addPrivilege(privilegeDao.findOne(id));
			}
		}
		menuDao.save(menu);
	}
	
	public Menu checkMenuCode(String menuCode){
		return menuDao.findByMenuCode(menuCode);
	}
	
	private Specification<Menu>  buildMenuSpecification(final Menu menu){
		Specification<Menu> spec=new Specification<Menu>(){
			@Override
			public Predicate toPredicate(Root<Menu> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates=new ArrayList<Predicate>();
				if(menu.getParentId()!=null){
					Predicate p=builder.isNull(root.<String>get("parentId"));
					predicates.add(p);
				}else if(menu.getParentId()!=null){
					Predicate p=builder.equal(root.<String>get("parentId"), menu.getParentId());
					predicates.add(p);
				}
				
				if (predicates.size() > 0) {
					return builder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
		        return builder.conjunction();  
			}
		};
		return spec;
	}
	
}
