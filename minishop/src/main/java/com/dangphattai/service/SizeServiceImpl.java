package com.dangphattai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangphattai.dao.SizeDAO;
import com.dangphattai.entity.Size;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeDAO sizeDAO;

	@Override
	public List<Size> getSizes() {
		return sizeDAO.getSizes();
	}
}
