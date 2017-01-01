package com.luo.mediaplaytest;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	private MediaPlayer mediaPlayer1;
	private MediaPlayer mediaPlayer2;
	private MediaPlayer mediaPlayer3;
	private MediaPlayer mediaPlayer4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mediaPlayer1 = MediaPlayer.create(this, R.raw.beautiful);
		try {
			mediaPlayer1.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mediaPlayer2 = new MediaPlayer();
		try {
			AssetManager am = getAssets();
			AssetFileDescriptor afd = am.openFd("beautiful.mp3");
			mediaPlayer2.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			mediaPlayer2.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mediaPlayer3 = new MediaPlayer();
		try {
			mediaPlayer3.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/next.mp3");
			mediaPlayer3.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mediaPlayer4 = new MediaPlayer();
		try {
			// "http://abv.cn/music/光辉岁月.mp3"
			String uriString = new String("http://abv.cn/music/光辉岁月.mp3".getBytes("UTF-8"));
			Uri uri = Uri.parse("http://abv.cn/music/光辉岁月.mp3");
			mediaPlayer4.setDataSource(this, uri);
			mediaPlayer4.prepare();
//			mediaPlayer4.prepareAsync();
			mediaPlayer4.setOnErrorListener(new OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					switch (extra) {
					case MediaPlayer.MEDIA_ERROR_IO:
						Log.v("haha", "MEDIA_ERROR_IO");
						break;
					case MediaPlayer.MEDIA_ERROR_MALFORMED:
						Log.v("haha", "MEDIA_ERROR_MALFORMED");
						break;
					case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
						Log.v("haha", "MEDIA_ERROR_UNSUPPORTED");
						break;
					case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
						Log.v("haha", "MEDIA_ERROR_TIMED_OUT");
						break;
					}
					return false;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void smfApplication (View view) {
		if (!mediaPlayer1.isPlaying()) {
			mediaPlayer1.start();
		} else if (mediaPlayer1.isPlaying()) {
			mediaPlayer1.pause();
		}
	}
	
	public void smfAssets (View view) {
		if (!mediaPlayer2.isPlaying()) {
			mediaPlayer2.start();
		} else if (mediaPlayer2.isPlaying()) {
			mediaPlayer2.pause();
		}
	}
	
	public void smfSdCard (View view) {
		if (!mediaPlayer3.isPlaying()) {
			mediaPlayer3.start();
		} else {
			mediaPlayer3.pause();
		}
	}
	
	public void smfInternet (View view) {
		
		if (!mediaPlayer4.isPlaying()) {
			mediaPlayer4.start();
		} else {
			mediaPlayer4.pause();
		}
	}
	
}
