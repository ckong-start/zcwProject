package com.springboot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.bean.Movie;
import com.springboot.service.MovieFeignExceptionHandlerService;
@FeignClient(value="CLOUD-PROVIDER-MOVIE", fallback=MovieFeignExceptionHandlerService.class)
public interface MovieServiceFeign {

	@GetMapping("/movie")
	public Movie getMovie();
}
