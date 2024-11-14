import pandas as pd
from sklearn.model_selection import TimeSeriesSplit
from sklearn.feature_selection import SequentialFeatureSelector
from sklearn.linear_model import RidgeClassifier
from sklearn.preprocessing import MinMaxScaler


def add_target(team): # Informs us if the team won the next game or not
    team["target"] = team["won"].shift(-1)
    return team

def main():

    df = pd.read_csv("nba_games.csv", index_col=0)

    # sorting data by ascending dates and ensuring no additional index columns are added
    df = df.sort_values("date")
    df = df.reset_index(drop=True)

    # deleting unnecessary columns from our dataframe
    del df["mp.1"]
    del df["mp_opp.1"]
    del df["index_opp"]

    # reassign target values to 0,1 or 2 instead of having a NaN value when its the last game
    df = df.groupby("team", group_keys=False).apply(add_target)
    df["target"][pd.isnull(df["target"])] =2
    df['target'] = df['target'].astype(int, errors="ignore")

    # cleaning up all null values and checking if there are any
    nulls = pd.isnull(df)
    nulls = nulls.sum()
    nulls = nulls[nulls> 0]
    valid_cols = df.columns[~df.columns.isin(nulls.index)]

    #  copying dataframe so that it isnt considered as a slice and get a future error
    df = df[valid_cols].copy()

    
    #init Algorithms from sklearn
    rr = RidgeClassifier(alpha=1)
    split = TimeSeriesSplit(n_splits=3)
    sfs = SequentialFeatureSelector(rr, n_features_to_select=30, direction="forward",cv=split)

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

    # print(df)
if "__main__" == __name__:
    main()