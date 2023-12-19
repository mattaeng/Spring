package com.mattaeng.mattaengapi.controller;


import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.service.HeartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/prefernece")
public class PreferenceController {
    private final HeartService heartService;
    @Operation(summary = "좋아요 추가")
    @PostMapping("/like")
    public Api<PostPreferenceResponse> postPreference(@RequestBody PostPreferenceRequest postPreferenceRequest) {
        return Api.ok(heartService.);
    }

}
