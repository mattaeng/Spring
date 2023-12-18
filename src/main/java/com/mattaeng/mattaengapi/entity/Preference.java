package com.mattaeng.mattaengapi.entity;

import com.mattaeng.mattaengapi.common.auditing.BaseTimeEntity;
import com.mattaeng.mattaengapi.common.enums.PreferenceStatus;
import com.mattaeng.mattaengapi.dto.preference.UpdatePreferenceRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Builder
public class Preference extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PreferenceStatus preferenceStatus;

    public Preference() {

    }

    public Preference(Long id, PreferenceStatus preferenceStatus) {
        this.id = id;
        this.preferenceStatus = preferenceStatus;
    }

    // TODO: activate and inactivate 나눠서 로직 작성
    public void updateLikeStatus(UpdatePreferenceRequest updatePreferenceRequest){
        if (updatePreferenceRequest.preferenceStatus() != null){
            this.preferenceStatus = PreferenceStatus.INACTIVATE;
        }
    }
}
