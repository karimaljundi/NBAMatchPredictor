import os
from bs4 import BeautifulSoup
import requests
import time
import pandas as pd
from selenium import webdriver



years = list(range(1990, 2024))

# player_stat_url = "https://www.basketball-reference.com/leagues/NBA_{}_per_game.html"
# driver = webdriver.Chrome()
# for year in years:
#     url = player_stat_url.format(year)
#     driver.get(url)
#     driver.execute_script("window.scrollTo(1,10000)")
#     time.sleep(2)

#     html = driver.page_source
#     with open("players/{}.html".format(year), "w+") as f:
#             f.write(html)

df = []
for year in years:
    with open("players/{}.html".format(year)) as f:
        page = f.read()
    soup = BeautifulSoup(page, "html.parser")
    soup.find('tr', class_="thead").decompose()
    player_table = soup.find('table',id="per_game_stats")
    player = pd.read_html(str(player_table))[0]
    player["Year"] = year
    df.append(player)
players = pd.concat(df)

# Remove duplicate columns, keeping the first occurrence
players = players.loc[:, ~players.columns.duplicated()]

# Remove the last row if it contains unwanted text
players = players[~players['Player'].str.contains("league average", na=False)]

# Keep only the latest instance of each player
players = players.sort_values(by='Year').drop_duplicates(subset='Player', keep='last')

players.to_csv("players.csv")
    

