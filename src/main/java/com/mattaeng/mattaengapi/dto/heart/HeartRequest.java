package com.mattaeng.mattaengapi.dto.heart;

import com.mattaeng.mattaengapi.common.enums.HeartStatus;

public record HeartRequest(
	Long id,
	HeartStatus heartStatus
) {

}
