package com.yjy.bsq.entity.member;import java.io.Serializable;import javax.persistence.Table;import com.yjy.bsq.entity.IdEntity;/******************************************************************************* * javaBeans ysq_member_level --> YsqMemberLevel * <table explanation> *  * @author 2015-04-22 22:30:00 *  */@Table(name = "YSQ_MEMBER_LEVEL")public class MemberLevel extends IdEntity implements Serializable {	/**	 * 	 */	private static final long serialVersionUID = -3094180716126072409L;	// field	/**  **/	/** 级别 **/	private int level;	/** 级别名称 **/	private int name;	/** 消费金额 **/	private int amount;	/** 消费次数 **/	private int times;	/** 创建时间 **/	private Object createdTime;	/** 创建人 **/	private String createdBy;	/** 最后更新时间 **/	private Object lastUpdatedTime;	/** 最后更新人 **/	private String lastUpdatedBy;	public int getLevel() {		return level;	}	public void setLevel(int level) {		this.level = level;	}	public int getName() {		return name;	}	public void setName(int name) {		this.name = name;	}	public int getAmount() {		return amount;	}	public void setAmount(int amount) {		this.amount = amount;	}	public int getTimes() {		return times;	}	public void setTimes(int times) {		this.times = times;	}	public Object getCreatedTime() {		return createdTime;	}	public void setCreatedTime(Object createdTime) {		this.createdTime = createdTime;	}	public String getCreatedBy() {		return createdBy;	}	public void setCreatedBy(String createdBy) {		this.createdBy = createdBy;	}	public Object getLastUpdatedTime() {		return lastUpdatedTime;	}	public void setLastUpdatedTime(Object lastUpdatedTime) {		this.lastUpdatedTime = lastUpdatedTime;	}	public String getLastUpdatedBy() {		return lastUpdatedBy;	}	public void setLastUpdatedBy(String lastUpdatedBy) {		this.lastUpdatedBy = lastUpdatedBy;	}}