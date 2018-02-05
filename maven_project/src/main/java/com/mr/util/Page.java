package com.mr.util;

import java.util.List;

public class Page {
	/** 当前页 */
	private Integer currentPage;
	/** 每页条数 */
	private Integer pageItem;
	/** 总条数 */
	private Integer countItem;

	/** 总页数 */
	private Integer countPage;
	/** 起始下标 */
	private Integer start;
	/** 数据 */
	private List list;
	
	//条件查询时用的辅助工具
	/**
	 *条件查询时用的辅助工具,用来放条件对象
	 */
	private Object condition;
	
	private List computerConfigStrList;

	public Integer getCurrentPage() {
		// 如果没有当前页信息:默认第一页
		if (currentPage == null) {
			currentPage = 1;
		}
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getPageItem() {
		// 如果没有每页几条，默认五条
		if (pageItem == null) {
			pageItem = 5;
		}
		return pageItem;
	}

	public void setPageItem(Integer pageItem) {
		this.pageItem = pageItem;
	}

	public Integer getCountItem() {
		return countItem;
	}

	public void setCountItem(Integer countItem) {
		this.countItem = countItem;
	}

	public Integer getCountPage() {
		// 总页数 =总条数/每页条数 向上取证 例如：18/5=3.6 向上取整 4 注意：需要将总条数转为 浮点类型 目的：求余数
		countPage = (int) Math.ceil(getCountItem().doubleValue() / getPageItem());
		return countPage;
	}

	public void setCountPage(Integer countPage) {
		this.countPage = countPage;
	}

	public Integer getStart() {
		// 起始值 = （当前页-1）*每页条数 求二页数据：5-9 （3-1）*5=10
		start = (getCurrentPage() - 1) * getPageItem();
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Object getCondition() {
		return condition;
	}

	public void setCondition(Object condition) {
		this.condition = condition;
	}

	public List getComputerConfigStrList() {
		return computerConfigStrList;
	}

	public void setComputerConfigStrList(List computerConfigStrList) {
		this.computerConfigStrList = computerConfigStrList;
	}



	

}
