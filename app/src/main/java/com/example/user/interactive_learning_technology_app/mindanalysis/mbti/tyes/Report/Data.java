package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by a2734043 on 2018/7/17.
 */

public class Data {

    @SerializedName("rounds")
    @Expose
    private List<Integer> rounds = null;
    @SerializedName("final")
    @Expose
    private Integer _final;

    public List<Integer> getRounds() {
        return rounds;
    }

    public void setRounds(List<Integer> rounds) {
        this.rounds = rounds;
    }

    public Integer getFinal() {
        return _final;
    }

    public void setFinal(Integer _final) {
        this._final = _final;
    }

}