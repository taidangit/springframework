package com.tedu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.entity.History;
import com.tedu.entity.Paging;
import com.tedu.service.HistoryService;
import com.tedu.util.Constant;

@Controller
@RequestMapping("/history")
public class HistoryController {

	@Autowired
	private HistoryService historyService;
	
	@GetMapping("/list")
	public String redirect() {
		return "redirect:/history/list/1";
	}
	@RequestMapping(value="/list/{page}")
	public String listHistories(@Valid @ModelAttribute("history") History history,@PathVariable("page") int page, Model model) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<History> histories = historyService.getHistorys(paging);

		model.addAttribute("histories", histories);
		model.addAttribute("pageInfo", paging);
		
		
		return "history";
	}
	
	@GetMapping("/search/{page}/{type}")
	public String searchHistories(@PathVariable("page") int page, @PathVariable("type") int type, Model model) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<History> histories = historyService.searchHistory("type", type, paging);
			
		model.addAttribute("type", type);
		
		model.addAttribute("histories", histories);
		model.addAttribute("pageInfo", paging);
		
		return "history";		
	}
}
