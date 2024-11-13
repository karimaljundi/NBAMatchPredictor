import os
import pandas as pd
from bs4 import BeautifulSoup
from tqdm import tqdm



def parse_html(box_score):
    with open(box_score, encoding='utf-8') as f:
        html = f.read()
    soup = BeautifulSoup(html, features="html.parser")
    [s.decompose() for s in soup.select("tr.over_header")]
    [s.decompose() for s in soup.select("tr.thead")]
    return soup
def read_line_score(soup):
    tables = soup.find_all('table')
    print(f"Number of tables found: {len(tables)}")
    
    table_ids = [table.get('id', 'no-id') for table in tables]
    print(f"Table IDs found: {table_ids}")
    
    try:
        # First try to find the table directly using BeautifulSoup
        line_score_table = soup.find('table', id='line_score')
        if line_score_table is None:
            print("Could not find line_score table")
            return None
            
        # Try reading just this specific table
        line_score = pd.read_html(str(line_score_table))[0]
        
        # If that fails, try reading without the id attribute
        if line_score is None:
            print("Attempting to read table without id attribute")
            line_score = pd.read_html(str(line_score_table), attrs={})[0]
            
        cols = list(line_score.columns)
        cols[0] = "team"
        cols[-1] = "total"
        line_score.columns = cols
        line_score = line_score[["team", "total"]]
        return line_score
    except Exception as e:
        print(f"Error processing table: {str(e)}")
        print("Table HTML:")
        print(str(line_score_table))  # This will help us see the actual HTML structure
        return None
def read_stats(soup, team, stat):
    df = pd.read_html(str(soup), attrs = {'id': f'box-{team}-game-{stat}'}, index_col=0)[0]
    df = df.apply(pd.to_numeric, errors="coerce")
    return df
def read_season_info(soup):
    nav = soup.select("#bottom_nav_container")[0]
    hrefs = [a["href"] for a in nav.find_all("a")]
    season = os.path.basename(hrefs[1]).split("_")[0]
    return season
def main():
    SCORE_DIR = "data/scores"

    box_scores = os.listdir(SCORE_DIR)

    box_scores = os.listdir(SCORE_DIR)
    box_scores = [os.path.join(SCORE_DIR, f) for f in box_scores if f.endswith(".html")]
    base_cols = None
    games = []
    for box_score in tqdm(box_scores, desc="Processing games", unit="game"):
        soup = parse_html(box_score)
        line_score = read_line_score(soup)
        
        if line_score is None:
            print(f"Skipping {box_score} due to missing line score data")
            continue
            
        teams = list(line_score["team"])

        summaries = []
        for team in teams:
            basic = read_stats(soup, team, "basic")
            advanced=read_stats(soup, team, "advanced")
            totals = pd.concat([basic.iloc[-1,:], advanced.iloc[-1,:]])
            totals.index = totals.index.str.lower()

            maxes = pd.concat([basic.iloc[:-1,:].max(), advanced.iloc[:-1,:].max()])
            maxes.index = maxes.index.str.lower() + "_max"
            summary = pd.concat([totals, maxes])
            if base_cols is None:
                base_cols= list(summary.index.drop_duplicates(keep="first"))
                base_cols = [b for b in base_cols if "bpn" not in b]
            summary = summary[base_cols]
            summaries.append(summary)
        summary=pd.concat(summaries, axis=1).T
        game = pd.concat([summary, line_score], axis=1)
        game["home"] = [0, 1]
        game_opp = game.iloc[::-1].reset_index()
        game_opp.columns+="_opp"
        full_game = pd.concat([game, game_opp], axis=1)
        full_game["season"] = read_season_info(soup)
        full_game["date"] = os.path.basename(box_score)[:8]
        full_game["date"] = pd.to_datetime(full_game["date"], format="%Y%m%d")
        full_game["won"] = full_game["total"] > full_game["total_opp"]

        games.append(full_game)
    games_df = pd.concat(games, ignore_index=True)
    games_df.to_csv("nba_games.csv")
if __name__ == "__main__":
    main()