import pandas as pd
df = pd.read_csv("nba_games.csv", index_col=0)

# sorting data by ascending dates and ensuring no additional index columns are added
df = df.sort_values("date")
df = df.reset_index(drop=True)

# deleting unnecessary columns from our dataframe
del df["mp.1"]
del df["mp_opp.1"]
del df["index_opp"]

def add_target(team): # Informs us if the team won the next game or not
    team["target"] = team["won"].shift(-1)
    return team


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
print(df)
