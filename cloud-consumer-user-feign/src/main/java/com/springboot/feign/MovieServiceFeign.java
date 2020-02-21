package com.springboot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.bean.Movie;
@FeignClient(value="CLOUD-PROVIDER-MOVIE")
public interface MovieServiceFeign {

	@GetMapping("/movie")
	public Movie getMovie();
}
