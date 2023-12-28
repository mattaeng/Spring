package com.mattaeng.mattaengapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.heart.DeleteHeartResponse;
import com.mattaeng.mattaengapi.dto.heart.HeartRequest;
import com.mattaeng.mattaengapi.dto.heart.UpdateHeartResponse;
import com.mattaeng.mattaengapi.service.HeartService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/heart")
public class HeartController {
	private final HeartService heartService;

	//TODO: 쿼리에 대한 고민이 필요함
	@Operation(summary = "좋아요 추가")
	@PostMapping("/{id}")
	public Api<UpdateHeartResponse> addHeart(@RequestBody final HeartRequest heartRequest, @PathVariable Long id) {
		return Api.ok(heartService.updateHeart(heartRequest, id));
	}

	@Operation(summary = "좋아요 취소")
	@DeleteMapping("/{id}")
	public Api<DeleteHeartResponse> removeHeart(@RequestBody final HeartRequest heartRequest, @PathVariable Long id) {
		return Api.ok(heartService.deleteHeart(heartRequest, id));
	}

}
