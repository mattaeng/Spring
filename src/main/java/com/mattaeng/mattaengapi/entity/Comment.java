package com.mattaeng.mattaengapi.entity;

import com.mattaeng.mattaengapi.common.auditing.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String context;

    @ManyToOne
    @JoinColumn(name ="feed_id")
    private Feed feed;

    public Comment() {

    }

    private Comment(Long id, String context, Feed feed){
        this.id = id;
        this.context = context;
        this.feed = feed;
    }
    public void setContext(String context) {
        this.context = context;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
