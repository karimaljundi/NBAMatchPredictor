from bs4 import BeautifulSoup
import requests
import time
import pandas as pd
from selenium import webdriver


driver = webdriver.Chrome()
years = list(range(1990, 2024))

player_stat_url = "https://www.basketball-reference.com/leagues/NBA_{}_per_game.html"


for year in years:
    url = player_stat_url.format(year)
    driver.get(url)
    driver.execute_script("window.scrollTo(1,10000)")
    time.sleep(2)

    html = driver.page_source
    with open("players/{}.html".format(year), "w+") as f:
        f.write(html)

    
# print(html)

