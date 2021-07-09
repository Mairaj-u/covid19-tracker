package com.msq.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msq.models.LocationStats;
import com.msq.services.CoronaVirusDataService;

@Controller
public class HomeController {
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@RequestMapping("https://mairaj-u.github.io/covid19-tracker/")
	public String home(Model model) {
		List<LocationStats> allStats=coronaVirusDataService.getAllStats();
		int totalReportedCases=allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		int totalNewCases=allStats.stream().mapToInt(stat->stat.getDiffFromPreDay()).sum();

		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		return "https://github.com/Mairaj-u/covid19-tracker/blob/master/coronavirus-tracker/src/main/resources/templates/Home.html";
	}

}
