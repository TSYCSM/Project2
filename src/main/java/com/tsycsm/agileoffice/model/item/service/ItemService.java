package com.tsycsm.agileoffice.model.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.model.domain.Item;

@Service
public interface ItemService {
	public List selectByOwner(int owner_id);
	public void regist(Item item);
}
