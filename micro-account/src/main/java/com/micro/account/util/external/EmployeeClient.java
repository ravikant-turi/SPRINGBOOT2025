package com.micro.account.util.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.account.dto.external.EmployeeResponse;
import com.micro.account.payload.ApiResponse;

@FeignClient(name = "micro-account", url = "http://localhost:8081/api/employees")

public interface EmployeeClient {
	@GetMapping("{id}")
	public ApiResponse<EmployeeResponse> getEmployeeById(@PathVariable("id") String id);

}
