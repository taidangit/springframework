package com.dangphattai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangphattai.dao.ColorDAO;
import com.dangphattai.entity.Color;

@Service
public class ColorServiceImpl implements ColorService {

	@Autowired
	private ColorDAO colorDAO;
	
	
	@Override
	public List<Color> getColors() {
		return colorDAO.getColors();
	}

	
}
