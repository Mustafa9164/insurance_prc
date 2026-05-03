package com.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import com.entity.CitizenPlan;
import com.entity.SearchRequest;

import java.util.List;

@Service
public interface ReportService {


    List<String> getPlanNames();

    List<String> getPlanStatus();

    List<CitizenPlan> searchCitizens(SearchRequest req);

    boolean exportExcel(HttpServletResponse response) throws Exception;

    boolean exportPdf(HttpServletResponse response) throws Exception;

	
}
