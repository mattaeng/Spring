package com.mattaeng.mattaengapi.dto.heart;

public record DeleteHeartResponse(
	boolean deleteHeartResult
) {
	public static DeleteHeartResponse of(boolean deleteHeartResult) {
		return new DeleteHeartResponse(true);
	}
}
