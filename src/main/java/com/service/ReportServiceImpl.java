package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.entity.CitizenPlan;
import com.entity.SearchRequest;
import com.repo.ReportRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportRepository reportRepository;

	@Override
	public List<String> getPlanNames() {
		
		return reportRepository.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return reportRepository.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> searchCitizens(SearchRequest req) {
		
		CitizenPlan entity=new CitizenPlan();
		
		if(null != req.getPlanName() && !"".equals(req.getPlanName())) {
			entity.setPlanName(req.getPlanName());
		}
		if(null != req.getPlanStatus() && !"".equals(req.getPlanStatus())) {
			entity.setPlanStatus(req.getPlanStatus());
		}
		if (null != req.getGender() && !"".equals(req.getGender())) {
            entity.setGender(req.getGender());
        }

        if (null != req.getPlanStartDate()) {
            entity.setPlanStartDate(req.getPlanStartDate());
        }

        if (null != req.getPlanEndDate()) {
            entity.setPlanEndDate(req.getPlanEndDate());
        }
        
		return reportRepository.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
