package com.mattaeng.mattaengapi.dto.heart;

public record UpdateHeartResponse(
	boolean updateHeartResult
) {
	public static UpdateHeartResponse of(boolean updateHeartResult) {
		return new UpdateHeartResponse(true);
	}
}
