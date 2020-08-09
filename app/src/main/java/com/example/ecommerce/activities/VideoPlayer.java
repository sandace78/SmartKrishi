package com.example.ecommerce.activities;

import android.os.Bundle;

import com.example.ecommerce.R;
import com.example.ecommerce.helpers.AppActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

public class VideoPlayer extends AppActivity implements YouTubePlayerListener {
    private YouTubePlayerView youTubePlayerView;
    private String videourl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        initializedView();
        initializedListners();
    }

    @Override
    protected void initializedView() {
        youTubePlayerView=findViewById(R.id.youtube_video_player);
   getLifecycle().addObserver(youTubePlayerView);
   videourl=getIntent().getStringExtra("url");

    }

    @Override
    protected void initializedListners() {
        youTubePlayerView.addYouTubePlayerListener(this);

    }

    @Override
    public void onApiChange(@NotNull YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onCurrentSecond(@NotNull YouTubePlayer youTubePlayer, float v) {

    }

    @Override
    public void onError(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerError playerError) {

    }

    @Override
    public void onPlaybackQualityChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackQuality playbackQuality) {

    }

    @Override
    public void onPlaybackRateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackRate playbackRate) {

    }

    @Override
    public void onReady(@NotNull YouTubePlayer youTubePlayer) {
        youTubePlayer.loadVideo(videourl,0);

    }

    @Override
    public void onStateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerState playerState) {

    }

    @Override
    public void onVideoDuration(@NotNull YouTubePlayer youTubePlayer, float v) {

    }

    @Override
    public void onVideoId(@NotNull YouTubePlayer youTubePlayer, @NotNull String s) {

    }

    @Override
    public void onVideoLoadedFraction(@NotNull YouTubePlayer youTubePlayer, float v) {

    }
}
