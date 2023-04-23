package com.example.foleyapp;

import android.content.Context;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AudioManager {
    private final SoundPool soundPool;
    private final HashMap<SoundCategory, List<Integer>> soundMap;
    private final Random random;

    public AudioManager(Context context) {
        soundPool = new SoundPool(5, android.media.AudioManager.STREAM_MUSIC, 0);
        soundMap = new HashMap<>();
        random = new Random();

        // Load all the sounds for each category
        loadSounds(context, SoundCategory.nature, R.raw.nature_light_rain_loop, R.raw.nature_crickets_and_insects_in_the_wild_ambience, R.raw.nature_little_birds_singing_in_the_trees, R.raw.nature_rain_and_thunder_storm);
        loadSounds(context, SoundCategory.animal, R.raw.animal_aggressive_beast_roar, R.raw.animal_angry_wild_cat_roar, R.raw.animal_flock_of_wild_geese, R.raw.animal_dog_barking_twice);
        loadSounds(context, SoundCategory.human, R.raw.human_crowd_laugh, R.raw.human_small_crowd_laugh_and_applause, R.raw.human_stadium_crowd_light_applause, R.raw.human_sick_man_sneeze);
        loadSounds(context, SoundCategory.technology, R.raw.technology_alien_radio_frequency_call, R.raw.technology_arcade_retro_game_over, R.raw.technology_retro_game_emergency_alarm, R.raw.technology_electronics_power_up);
    }

    private void loadSounds(Context context, SoundCategory category, int... soundIds) {
        List<Integer> soundList = new ArrayList<>();
        for (int soundId : soundIds) {
            int sound = soundPool.load(context, soundId, 1);
            soundList.add(sound);
        }
        soundMap.put(category, soundList);
    }

    public void playSound(SoundCategory category, float speed, float volume) {
        List<Integer> soundList = soundMap.get(category);
        if (soundList != null && soundList.size() > 0) {
            int index = random.nextInt(soundList.size());
            int soundId = soundList.get(index);
            soundPool.play(soundId, volume, volume, 1, 0, speed);
        }
    }

    public void resume() {
        soundPool.autoResume();
    }

    public void pause() {
        soundPool.autoPause();
    }

    public enum SoundCategory {
        nature,
        animal,
        human,
        technology
    }
}
