package com.mattaeng.mattaengapi.dto.preference;

import com.mattaeng.mattaengapi.common.enums.PreferenceStatus;
import lombok.Builder;

@Builder
public record UpdatePreferenceRequest(
    PreferenceStatus preferenceStatus
) {

}
