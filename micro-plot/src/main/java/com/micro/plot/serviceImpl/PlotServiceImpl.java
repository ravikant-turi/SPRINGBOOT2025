package com.micro.plot.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.plot.dto.PlotDto;
import com.micro.plot.entity.Plot;
import com.micro.plot.exceptions.ResourceNotFoundException;
import com.micro.plot.payload.ApiResponse;
import com.micro.plot.repository.PlotRepository;
import com.micro.plot.service.PlotService;
@Service
public class PlotServiceImpl implements PlotService {

	@Autowired
	private PlotRepository plotRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse<Plot> savePlot(PlotDto plotDto) {
		Plot plot = modelMapper.map(plotDto, Plot.class);
		plot.setId(UUID.randomUUID().toString());
		plot.setDateTime(LocalDateTime.now().toString());
		Plot savedPlot = plotRepository.save(plot);
		return new ApiResponse<>("SUCCESS", "PLOT_SAVED_SUCCESSFULLY", savedPlot);
	}

	@Override
	public ApiResponse<List<Plot>> findAllPlot() {
		List<Plot> plots = plotRepository.findAll();
		return new ApiResponse<>("SUCCESS", "ALL_PLOTS_FETCHED", plots);
	}

	@Override
	public ApiResponse<Plot> findPlotByPlotId(String id) {
		Plot plot = plotRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Plot not found with id: " + id));
		return new ApiResponse<>("SUCCESS", "PLOT_FOUND", plot);
	}

}
