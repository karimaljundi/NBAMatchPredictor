package com.nba.nba_predictor.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="player_stat")
public class Player {

    private int index;
    private int rank;
    @Id
    @Column(name = "Player", unique = true)
    private String pname;
    private int age;
    private String team;
    private String pos;
    private int games_played;
    private int games_started;
    private float minutes_played;
    private float field_goal;
    private float field_goal_attempted;
    private float field_goal_percentage;
    private float three_points;
    private float three_points_attempted;
    private float three_points_percentage;
    private float two_points;
    private float two_points_attempted;
    private float two_points_percentage;
    private float effective_field_goal_percentage;
    private float free_throw;
    private float free_throw_attempted;
    private float free_throw_percentage;
    private float offensive_rebounds;
    private float defensive_rebounds;
    private float rebounds;
    private float assists;
    private float steals;
    private float blocks;
    private float turnovers;
    private float personal_fouls;
    private float points;
    private String awards;
    private int year;

    public Player(int index, int rank, String pname, int age, String team, String pos, int games_played, int games_started, float minutes_played, float field_goal, float field_goal_attempted, float field_goal_percentage, float three_points, float three_points_attempted, float three_points_percentage, float two_points, float two_points_attempted, float two_points_percentage, float effective_field_goal_percentage, float free_throw, float free_throw_attempted, float free_throw_percentage, float offensive_rebounds, float defensive_rebounds, float rebounds, float assists, float steals, float blocks, float turnovers, float personal_fouls, float points, String awards, int year) {
        this.index = index;
        this.rank = rank;
        this.pname = pname;
        this.age = age;
        this.team = team;
        this.pos = pos;
        this.games_played = games_played;
        this.games_started = games_started;
        this.minutes_played = minutes_played;
        this.field_goal = field_goal;
        this.field_goal_attempted = field_goal_attempted;
        this.field_goal_percentage = field_goal_percentage;
        this.three_points = three_points;
        this.three_points_attempted = three_points_attempted;
        this.three_points_percentage = three_points_percentage;
        this.two_points = two_points;
        this.two_points_attempted = two_points_attempted;
        this.two_points_percentage = two_points_percentage;
        this.effective_field_goal_percentage = effective_field_goal_percentage;
        this.free_throw = free_throw;
        this.free_throw_attempted = free_throw_attempted;
        this.free_throw_percentage = free_throw_percentage;
        this.offensive_rebounds = offensive_rebounds;
        this.defensive_rebounds = defensive_rebounds;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.personal_fouls = personal_fouls;
        this.points = points;
        this.awards = awards;
        this.year = year;
    }

    public Player() {

    }

    public int getIndex() {
        return index;
    }

    public int getRank() {
        return rank;
    }

    public String getPname() {
        return pname;
    }

    public int getAge() {
        return age;
    }

    public String getTeam() {
        return team;
    }

    public String getPos() {
        return pos;
    }

    public int getGames_played() {
        return games_played;
    }

    public int getGames_started() {
        return games_started;
    }

    public float getMinutes_played() {
        return minutes_played;
    }

    public float getField_goal() {
        return field_goal;
    }

    public float getField_goal_attempted() {
        return field_goal_attempted;
    }

    public float getField_goal_percentage() {
        return field_goal_percentage;
    }

    public float getThree_points() {
        return three_points;
    }

    public float getThree_points_attempted() {
        return three_points_attempted;
    }

    public float getThree_points_percentage() {
        return three_points_percentage;
    }

    public float getTwo_points() {
        return two_points;
    }

    public float getTwo_points_attempted() {
        return two_points_attempted;
    }

    public float getTwo_points_percentage() {
        return two_points_percentage;
    }

    public float getEffective_field_goal_percentage() {
        return effective_field_goal_percentage;
    }

    public float getFree_throw() {
        return free_throw;
    }

    public float getFree_throw_attempted() {
        return free_throw_attempted;
    }

    public float getFree_throw_percentage() {
        return free_throw_percentage;
    }

    public float getOffensive_rebounds() {
        return offensive_rebounds;
    }

    public float getDefensive_rebounds() {
        return defensive_rebounds;
    }

    public float getRebounds() {
        return rebounds;
    }

    public float getAssists() {
        return assists;
    }

    public float getSteals() {
        return steals;
    }

    public float getBlocks() {
        return blocks;
    }

    public float getTurnovers() {
        return turnovers;
    }

    public float getPersonal_fouls() {
        return personal_fouls;
    }

    public float getPoints() {
        return points;
    }

    public String getAwards() {
        return awards;
    }

    public int getYear() {
        return year;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public void setGames_started(int games_started) {
        this.games_started = games_started;
    }

    public void setMinutes_played(float minutes_played) {
        this.minutes_played = minutes_played;
    }

    public void setField_goal(float field_goal) {
        this.field_goal = field_goal;
    }

    public void setField_goal_attempted(float field_goal_attempted) {
        this.field_goal_attempted = field_goal_attempted;
    }

    public void setField_goal_percentage(float field_goal_percentage) {
        this.field_goal_percentage = field_goal_percentage;
    }

    public void setThree_points(float three_points) {
        this.three_points = three_points;
    }

    public void setThree_points_attempted(float three_points_attempted) {
        this.three_points_attempted = three_points_attempted;
    }

    public void setThree_points_percentage(float three_points_percentage) {
        this.three_points_percentage = three_points_percentage;
    }

    public void setTwo_points(float two_points) {
        this.two_points = two_points;
    }

    public void setTwo_points_attempted(float two_points_attempted) {
        this.two_points_attempted = two_points_attempted;
    }

    public void setTwo_points_percentage(float two_points_percentage) {
        this.two_points_percentage = two_points_percentage;
    }

    public void setEffective_field_goal_percentage(float effective_field_goal_percentage) {
        this.effective_field_goal_percentage = effective_field_goal_percentage;
    }

    public void setFree_throw(float free_throw) {
        this.free_throw = free_throw;
    }

    public void setFree_throw_attempted(float free_throw_attempted) {
        this.free_throw_attempted = free_throw_attempted;
    }

    public void setFree_throw_percentage(float free_throw_percentage) {
        this.free_throw_percentage = free_throw_percentage;
    }

    public void setOffensive_rebounds(float offensive_rebounds) {
        this.offensive_rebounds = offensive_rebounds;
    }

    public void setDefensive_rebounds(float defensive_rebounds) {
        this.defensive_rebounds = defensive_rebounds;
    }

    public void setRebounds(float rebounds) {
        this.rebounds = rebounds;
    }

    public void setAssists(float assists) {
        this.assists = assists;
    }

    public void setSteals(float steals) {
        this.steals = steals;
    }

    public void setBlocks(float blocks) {
        this.blocks = blocks;
    }

    public void setTurnovers(float turnovers) {
        this.turnovers = turnovers;
    }

    public void setPersonal_fouls(float personal_fouls) {
        this.personal_fouls = personal_fouls;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
