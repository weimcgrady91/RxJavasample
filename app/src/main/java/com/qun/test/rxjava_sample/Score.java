package com.qun.test.rxjava_sample;

/**
 * Created by Administrator on 2018/4/11 0011.
 */

public class Score {
    public Score(int score) {
        this.score = score;
    }

    int score;

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                '}';
    }
}
