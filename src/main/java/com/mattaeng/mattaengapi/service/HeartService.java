package com.mattaeng.mattaengapi.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mattaeng.mattaengapi.dto.heart.DeleteHeartResponse;
import com.mattaeng.mattaengapi.dto.heart.HeartRequest;
import com.mattaeng.mattaengapi.dto.heart.UpdateHeartResponse;
import com.mattaeng.mattaengapi.entity.Feed;
import com.mattaeng.mattaengapi.entity.Heart;
import com.mattaeng.mattaengapi.repository.FeedRepository;
import com.mattaeng.mattaengapi.repository.HeartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeartService {
	private final HeartRepository heartRepository;
	private final FeedRepository feedRepository;

	//TODO: 로직 수정 필요
	@Transactional
	public UpdateHeartResponse updateHeart(HeartRequest heartRequest, Long id) {
		Feed feed = feedRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("존재하지 않는 피드입니다"));
		Heart heart = Heart.builder()
			.id(heartRequest.id())
			.heartStatus(heartRequest.heartStatus())
			.feed(feed)
			.build();
		return heartRepository.findById(heart.getId())
			.map(heart1 -> {
				heart1.updateHeart(heartRequest);
				return UpdateHeartResponse.of(true);
			})
			.orElseThrow(() -> new NoSuchElementException("좋아요 없수"));
	}

	@Transactional
	public DeleteHeartResponse deleteHeart(HeartRequest heartRequest, Long id) {
		Feed feed = feedRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("존재하지 않는 피드입니다."));
		return heartRepository.findById((heartRequest.id()))
			.map(heart -> {
				heart.deleteHeart(heartRequest);
				return DeleteHeartResponse.of(true);
			})
			.orElseThrow(() -> new NoSuchElementException("좋아요가 없수다"));
	}
}
