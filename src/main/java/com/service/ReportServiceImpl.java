package com.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.entity.CitizenPlan;
import com.entity.SearchRequest;
import com.repo.ReportRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

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

		CitizenPlan entity = new CitizenPlan();

		if (null != req.getPlanName() && !"".equals(req.getPlanName())) {
			entity.setPlanName(req.getPlanName());
		}
		if (null != req.getPlanStatus() && !"".equals(req.getPlanStatus())) {
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

		//Workbook workbook = new XSSFWorkbook();
		Workbook  workbook=new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");
		Row headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benfit Amt");

		List<CitizenPlan> records = reportRepository.findAll();
		int dataRowIndex = 1;
		for (CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getPlanStartDate());
			dataRow.createCell(5).setCellValue(plan.getPlanEndDate());
			if(null != plan.getBenefitAmount()) {
			dataRow.createCell(6).setCellValue(plan.getBenefitAmount());
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			dataRowIndex++;
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
