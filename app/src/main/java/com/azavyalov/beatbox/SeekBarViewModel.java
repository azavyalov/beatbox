package com.azavyalov.beatbox;

import android.widget.SeekBar;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import java.text.MessageFormat;

public class SeekBarViewModel extends BaseObservable {

    private BeatBox mBeatBox;
    public ObservableField<String> mPlaybackSpeedRateLabel = new ObservableField<>();
    public ObservableInt mPlaybackSpeedRate = new ObservableInt();

    public SeekBarViewModel(BeatBox beatBox) {
        this.mBeatBox = beatBox;
        mPlaybackSpeedRateLabel.set(formatSpeedLabel(beatBox));
        mPlaybackSpeedRate.set((int) (beatBox.getPlaybackSpeedRate() * 100));
    }

    private String formatSpeedLabel(BeatBox beatBox) {
        return MessageFormat.format(
                beatBox.getPlaybackSpeedLabel(),
                (int) (beatBox.getPlaybackSpeedRate() * 100));
    }

    // Implement onProgressChanged method from OnSeekBarChangeListener using data binding
    public void onPlaybackSpeedChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mPlaybackSpeedRateLabel.set(MessageFormat.format(mBeatBox.getPlaybackSpeedLabel(), progress));
        mPlaybackSpeedRate.set(progress);
        mBeatBox.setPlaybackSpeedRate((float) progress / 100);
    }
}
