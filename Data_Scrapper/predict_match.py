import pandas as pd
from sklearn.model_selection import TimeSeriesSplit
from sklearn.feature_selection import SequentialFeatureSelector
from sklearn.linear_model import RidgeClassifier
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics import accuracy_score


def add_target(group): # Informs us if the team won the next game or not
    group["target"] = group["won"].shift(-1)
    return group
def backtest(data, model, predictors, start=2, step=1):# It trains a machine learning model on 'start' amount of seasons,
# it splits the data, so that the model cannot see
#the actual decision when it is predicting

    all_predictions = []
    
    seasons = sorted(data["season"].unique())
    
    for i in range(start, len(seasons), step):
        season = seasons[i]
        train = data[data["season"] < season]
        test = data[data["season"] == season]
        
        model.fit(train[predictors], train["target"])
        
        preds = model.predict(test[predictors])
        preds = pd.Series(preds, index=test.index)
        combined = pd.concat([test["target"], preds], axis=1)
        combined.columns = ["actual", "prediction"]

        all_predictions.append(combined)
    if not all_predictions:
        print("Warning: all_predictions is empty. Returning an empty DataFrame.")
        return pd.DataFrame()
    return pd.concat(all_predictions)
def find_team_averages(team): # returns the mean of the team's last 10 games
    rolling = team.rolling(10).mean()
    return rolling
def shift_col(team, col_name): # take the value from the next game and shift it down 1 row
    next_col = team[col_name].shift(-1)
    return next_col
def add_col(df, col_name): 
    return df.groupby("team", group_keys=False).apply(lambda x: shift_col(x, col_name))
def main():

    df = pd.read_csv("nba_games.csv", index_col=0)

    # sorting data by ascending dates and ensuring no additional index columns are added
    df = df.sort_values("date")
    df = df.reset_index(drop=True)

    # deleting unnecessary columns from our dataframe
    del df["mp.1"]
    del df["mp_opp.1"]
    del df["index_opp"]

    # Debugging: Check the shape of df before applying add_target
    print("Shape of df before adding target:", df.shape)
    
    # reassign target values to 0,1 or 2 instead of having a NaN value when its the last game
    df = df.groupby("team", group_keys=False).apply(add_target)
    

    df[df["team"] == "GSW"]

    
    df["target"][pd.isnull(df["target"])] = 2
    df['target'] = df['target'].astype(int, errors="ignore")

    # cleaning up all null values and checking if there are any
    nulls = pd.isnull(df).sum()
    nulls = nulls[nulls> 0]
    valid_cols = df.columns[~df.columns.isin(nulls.index)]

    #  copying dataframe so that it isnt considered as a slice and get a future error
    df = df[valid_cols].copy()

    
    #init Algorithms from sklearn
    rr = RidgeClassifier(alpha=1)
    split = TimeSeriesSplit(n_splits=3)
    sfs = SequentialFeatureSelector(rr, n_features_to_select=30, direction="forward",cv=split, n_jobs=1)

    # Remove the filtered columns from the dataframe since they will not be able to help determine if a team will win or not
    removed_cols = ["season", "date", "won", "target", "team", "team_opp"]
    selected_cols = df.columns[~df.columns.isin(removed_cols)]

    # scale the values so now they are between 0 and 1 so its easier for the ML model to read excluding the excluded columns that have not been touched
    scaler = MinMaxScaler()
    df[selected_cols] = scaler.fit_transform(df[selected_cols])

    sfs.fit(df[selected_cols], df["target"])

    # if the feature selector picks the column and thinks it will be a good fit it will return true
    predictors = list(selected_cols[sfs.get_support()])
    print(predictors)


    predictions = backtest(df, rr, predictors)
    print(predictions)
    predictions = predictions[predictions["actual"]!=2]
    accuracy_score(predictions["actual"], predictions["prediction"])

    # Consider that a team has an advantage when they are the home team so they have a higher probability of winning
    df.groupby("home").apply(lambda x: x[x["won"]==1].shape() / x.shape[0])

    df_rolling = df[list(selected_cols) + ["won", "team", "season"]]
    df_rolling = df_rolling.groupby(["team", "season"], group_keys=False).apply(find_team_averages)
    rolling_cols = [f"{col}_10" for col in df_rolling.columns]
    df_rolling.columns = rolling_cols

    df = pd.concat([df, df_rolling], axis=1)

    df = df.dropna()
    df["home_next"] = add_col(df, "home")
    df["team_opp_next"] = add_col(df, "team_opp")
    df["date_next"] = add_col(df, "date")

    df = df.copy()
    print(df)

if "__main__" == __name__:
    main()