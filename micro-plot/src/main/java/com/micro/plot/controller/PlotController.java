package com.micro.plot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.plot.dto.PlotDto;
import com.micro.plot.entity.Plot;
import com.micro.plot.payload.ApiResponse;
import com.micro.plot.service.PlotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/plots")
public class PlotController {

	@Autowired
	private PlotService plotService;

	@PostMapping
	public ResponseEntity<ApiResponse<Plot>> savePlot(@Valid @RequestBody PlotDto PlotDto) {
		ApiResponse<Plot> savedPlotApiResponse = this.plotService.savePlot(PlotDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedPlotApiResponse);

//		return new ResponseEntity<>(savedPlotApiResponse, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<Plot>>> getAllPlots() {

		ApiResponse<List<Plot>> allPlotApiResponse = this.plotService.findAllPlot();

		return ResponseEntity.status(HttpStatus.OK).body(allPlotApiResponse);
	}

	@GetMapping("{id}")
	public ResponseEntity<ApiResponse<Plot>> getPlotById(@PathVariable("id") String id) {
		ApiResponse<Plot> foundApiResponse = this.plotService.findPlotByPlotId(id);
		return ResponseEntity.status(HttpStatus.OK).body(foundApiResponse);

	}

}