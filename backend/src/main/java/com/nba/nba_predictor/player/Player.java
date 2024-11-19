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
    @Column(name = "pname", unique = false)
    private String name;
    private int age;
    private String team;
    private String pos;
    private int games_played;
    private int games_started;
    private Float minutes_played;
    private Float field_goal;
    private Float field_goal_attempted;
    private Float field_goal_percentage;
    private Float three_points;
    private Float three_points_attempted;
    private Float three_points_percentage;
    private Float two_points;
    private Float two_points_attempted;
    private Float two_points_percentage;
    private Float effective_field_goal_percentage;
    private Float free_throw;
    private Float free_throw_attempted;
    private Float free_throw_percentage;
    private Float offensive_rebounds;
    private Float defensive_rebounds;
    private Float rebounds;
    private Float assists;
    private Float steals;
    private Float blocks;
    private Float turnovers;
    private Float personal_fouls;
    private Float points;
    private String awards;
    private int year;

    public Player(int index, int rank, String name, int age, String team, String pos, int games_played, int games_started, Float minutes_played, Float field_goal, Float field_goal_attempted, Float field_goal_percentage, Float three_points, Float three_points_attempted, Float three_points_percentage, Float two_points, Float two_points_attempted, Float two_points_percentage, Float effective_field_goal_percentage, Float free_throw, Float free_throw_attempted, Float free_throw_percentage, Float offensive_rebounds, Float defensive_rebounds, Float rebounds, Float assists, Float steals, Float blocks, Float turnovers, Float personal_fouls, Float points, String awards, int year) {
        this.index = index;
        this.rank = rank;
        this.name = name;
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

    public String getName() {
        return name;
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

    public Float getMinutes_played() {
        return minutes_played;
    }

    public Float getField_goal() {
        return field_goal;
    }

    public Float getField_goal_attempted() {
        return field_goal_attempted;
    }

    public Float getField_goal_percentage() {
        return field_goal_percentage;
    }

    public Float getThree_points() {
        return three_points;
    }

    public Float getThree_points_attempted() {
        return three_points_attempted;
    }

    public Float getThree_points_percentage() {
        return three_points_percentage;
    }

    public Float getTwo_points() {
        return two_points;
    }

    public Float getTwo_points_attempted() {
        return two_points_attempted;
    }

    public Float getTwo_points_percentage() {
        return two_points_percentage;
    }

    public Float getEffective_field_goal_percentage() {
        return effective_field_goal_percentage;
    }

    public Float getFree_throw() {
        return free_throw;
    }

    public Float getFree_throw_attempted() {
        return free_throw_attempted;
    }

    public Float getFree_throw_percentage() {
        return free_throw_percentage;
    }

    public Float getOffensive_rebounds() {
        return offensive_rebounds;
    }

    public Float getDefensive_rebounds() {
        return defensive_rebounds;
    }

    public Float getRebounds() {
        return rebounds;
    }

    public Float getAssists() {
        return assists;
    }

    public Float getSteals() {
        return steals;
    }

    public Float getBlocks() {
        return blocks;
    }

    public Float getTurnovers() {
        return turnovers;
    }

    public Float getPersonal_fouls() {
        return personal_fouls;
    }

    public Float getPoints() {
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

    public void setName(String name) {
        this.name = name;
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

    public void setMinutes_played(Float minutes_played) {
        this.minutes_played = minutes_played;
    }

    public void setField_goal(Float field_goal) {
        this.field_goal = field_goal;
    }

    public void setField_goal_attempted(Float field_goal_attempted) {
        this.field_goal_attempted = field_goal_attempted;
    }

    public void setField_goal_percentage(Float field_goal_percentage) {
        this.field_goal_percentage = field_goal_percentage;
    }

    public void setThree_points(Float three_points) {
        this.three_points = three_points;
    }

    public void setThree_points_attempted(Float three_points_attempted) {
        this.three_points_attempted = three_points_attempted;
    }

    public void setThree_points_percentage(Float three_points_percentage) {
        this.three_points_percentage = three_points_percentage;
    }

    public void setTwo_points(Float two_points) {
        this.two_points = two_points;
    }

    public void setTwo_points_attempted(Float two_points_attempted) {
        this.two_points_attempted = two_points_attempted;
    }

    public void setTwo_points_percentage(Float two_points_percentage) {
        this.two_points_percentage = two_points_percentage;
    }

    public void setEffective_field_goal_percentage(Float effective_field_goal_percentage) {
        this.effective_field_goal_percentage = effective_field_goal_percentage;
    }

    public void setFree_throw(Float free_throw) {
        this.free_throw = free_throw;
    }

    public void setFree_throw_attempted(Float free_throw_attempted) {
        this.free_throw_attempted = free_throw_attempted;
    }

    public void setFree_throw_percentage(Float free_throw_percentage) {
        this.free_throw_percentage = free_throw_percentage;
    }

    public void setOffensive_rebounds(Float offensive_rebounds) {
        this.offensive_rebounds = offensive_rebounds;
    }

    public void setDefensive_rebounds(Float defensive_rebounds) {
        this.defensive_rebounds = defensive_rebounds;
    }

    public void setRebounds(Float rebounds) {
        this.rebounds = rebounds;
    }

    public void setAssists(Float assists) {
        this.assists = assists;
    }

    public void setSteals(Float steals) {
        this.steals = steals;
    }

    public void setBlocks(Float blocks) {
        this.blocks = blocks;
    }

    public void setTurnovers(Float turnovers) {
        this.turnovers = turnovers;
    }

    public void setPersonal_fouls(Float personal_fouls) {
        this.personal_fouls = personal_fouls;
    }

    public void setPoints(Float points) {
        this.points = points;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
