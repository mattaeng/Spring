package com.mattaeng.mattaengapi.dto.like;

import com.mattaeng.mattaengapi.common.enums.LikeStatus;
import lombok.Builder;

@Builder
public record UpdateLikeRequest(
    LikeStatus likeStatus
) {

}
