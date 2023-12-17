package com.mattaeng.mattaengapi.entity;

import com.mattaeng.mattaengapi.common.auditing.BaseTimeEntity;
import com.mattaeng.mattaengapi.common.enums.LikeStatus;
import com.mattaeng.mattaengapi.dto.like.UpdateLikeRequest;
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
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LikeStatus likeStatus;

    public Like() {

    }

    public Like(Long id, LikeStatus likeStatus) {
        this.id = id;
        this.likeStatus = likeStatus;
    }

    // TODO: activate and inactivate 나눠서 로직 작성
    public void updateLikeStatus(UpdateLikeRequest updateLikeRequest){
        if (updateLikeRequest.likeStatus() != null){
            this.likeStatus = LikeStatus.INACTIVATE;
        }
    }
}
