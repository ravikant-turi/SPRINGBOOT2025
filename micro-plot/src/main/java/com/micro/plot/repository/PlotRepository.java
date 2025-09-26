package com.micro.plot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.plot.entity.Plot;

public interface PlotRepository extends JpaRepository<Plot, String> {

}
