package com.tsycsm.agileoffice.model.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ItemService {
	public List selectByOwner(int owner_id);
}
