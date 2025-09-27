package com.micro.plot.service;

import java.util.List;

import com.micro.plot.dto.PlotDto;
import com.micro.plot.entity.Plot;
import com.micro.plot.payload.ApiResponse;

public interface PlotService {

	ApiResponse<Plot> savePlot(PlotDto plotDto);

	ApiResponse<List<Plot>> findAllPlot();

	ApiResponse<Plot> findPlotByPlotId(String id);
}
