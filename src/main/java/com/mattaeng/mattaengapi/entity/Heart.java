package com.mattaeng.mattaengapi.entity;

import com.mattaeng.mattaengapi.common.auditing.BaseTimeEntity;
import com.mattaeng.mattaengapi.common.enums.HeartStatus;
import com.mattaeng.mattaengapi.dto.heart.HeartRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private HeartStatus heartStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feed_id")
	private Feed feed;

	@Builder
	public Heart(Long id, HeartStatus heartStatus, Feed feed) {
		this.id = id;
		this.heartStatus = heartStatus;
		this.feed = feed;
	}

	// TODO: activate and inactivate 나눠서 로직 작성
	public void updateHeart(HeartRequest heartRequest) {
		if (heartRequest.heartStatus() != null) {
			this.heartStatus = HeartStatus.ACTIVATIE;
		}
	}

	public void deleteHeart(HeartRequest heartRequest) {
		if (heartRequest.heartStatus() != null) {
			this.heartStatus = HeartStatus.INACTIVATE;
		}
	}
}
