package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.CitizenPlan;
import com.entity.SearchRequest;
import com.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/")
	public String loadFormPage(CitizenPlan citizenPlan,Model model) {

	    model.addAttribute("searchRequest", new SearchRequest());
	    model.addAttribute("planNames", reportService.getPlanNames());
	    model.addAttribute("planStatuses", reportService.getPlanStatus());

	    return "index";  
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("searchRequest") SearchRequest request,Model model) {
		
		System.out.println(request);
		List<CitizenPlan> searchCitizens = reportService.searchCitizens(request);
		
		model.addAttribute("plans", searchCitizens);
		
		return "index";
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;fileName=plans.xls");
		reportService.exportExcel(response);
	}

}
