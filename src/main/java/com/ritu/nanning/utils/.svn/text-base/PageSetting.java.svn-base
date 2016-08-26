package com.ritu.nanning.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageSetting implements Pageable {

	private int offSet = 0;
	private int number = 0;
	private int size = 10;
	private Sort sort;

	public PageSetting(int offSet, int number, int size, String... sortFields) {
		this.offSet = offSet;
		this.number = number;
		this.size = size;
		if (sortFields.length == 0) {
			Sort.Order[] orders = new Sort.Order[1];
			orders[0] = new Sort.Order("updateDate");
			sort = new Sort(orders);
		} else {
			Sort.Order[] orders = new Sort.Order[sortFields.length];
			for (int i = 0; i < sortFields.length; i++) {
				orders[i] = new Sort.Order(sortFields[i]);
			}
			sort = new Sort(orders);
		}
	}

	public PageSetting(int number, int size, String... sortFields) {
		this.offSet = size * (number - 1);
		this.number = number;
		this.size = size;
		if (sortFields.length == 0) {
			Sort.Order[] orders = new Sort.Order[1];
			orders[0] = new Sort.Order("updateDate");
			sort = new Sort(orders);
		} else {
			Sort.Order[] orders = new Sort.Order[sortFields.length];
			for (int i = 0; i < sortFields.length; i++) {
				orders[i] = new Sort.Order(sortFields[i]);
			}
			sort = new Sort(orders);
		}
	}
	
	public PageSetting(int number, int size,boolean isAsc, String... sortFields) {
		this.offSet = size * (number - 1);
		this.number = number;
		this.size = size;
		Sort.Direction direction = Sort.Direction.ASC;
		if(isAsc){
			direction = Sort.Direction.ASC;
		}else{
			direction = Sort.Direction.DESC;
		}
		if (sortFields.length == 0) {
			Sort.Order[] orders = new Sort.Order[1];
			orders[0] = new Sort.Order(direction,"updateDate");
			sort = new Sort(orders);
		} else {
			Sort.Order[] orders = new Sort.Order[sortFields.length];
			for (int i = 0; i < sortFields.length; i++) {
				orders[i] = new Sort.Order(direction,sortFields[i]);
			}
			sort = new Sort(orders);
		}
	}

	@Override
	public int getOffset() {
		return offSet;
	}

	@Override
	public int getPageNumber() {
		return number;
	}

	@Override
	public int getPageSize() {
		return size;
	}

	@Override
	public Sort getSort() {
		return sort;
	}

}
