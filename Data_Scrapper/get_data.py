import os
from bs4 import BeautifulSoup
from playwright.async_api import async_playwright, TimeoutError as PlaywrightTimeout
import time

SEASONS = list(range(2016, 2025))

DATA_DIR = "data"

STANDINGS_DIR = os.path.join(DATA_DIR, "standings")
SCORES_DIR = os.path.join(DATA_DIR, "scores")

async def get_html(url, selector, sleep=5, retires=3):
    html = None
    for i in range(1, retires+1):
        time.sleep(sleep *  i)

        try:
            async with async_playwright() as p:
                browser = await p.chromium.launch()
                page = await browser.new_page()
                await page.goto(url)
                print(await page.title())
                html = await page.inner_html(selector)
        except PlaywrightTimeout:
            print(f"Timeout error on {url}")
            continue
        else:
            break
    return html

async def scrape_season(season):
    url = f"https://www.basketball-reference.com/leagues/NBA_{season}_games.html"
    html = await get_html(url=url, selector="#content .filter")
    soup = BeautifulSoup(html)
    links = soup.find_all("a")
    href = [l["href"] for l in links]
    standing_pages = [f"https://basketball-reference.com{l}" for l in href]
    for url in standing_pages:
        save_path = os.path.join(STANDINGS_DIR, url.split("/")[-1])
        if os.path.exists(save_path):
            continue
        html = await get_html(url, "#all_schedule")
        with open(save_path, "w+") as f:
            f.write(html)
async def scrape_game(standings_file):
    with open(standings_file, 'r') as f:
        html = f.read()
    soup = BeautifulSoup(html)
    links = soup.find_all("a")
    hrefs = [f.get("href") for l in links]
    box_scores = [l for l in hrefs if l and "boxscore" in l and ".html" in l]
    box_scores = [f"https://www.basketball-reference.com{l}" for l in box_scores]
    for url in box_scores:
        save_path = os.path.join(SCORES_DIR, url.split("/")[-1])
        if os.path.exists(save_path):
            continue
        html = await get_html(url, "#content")
        if not html:
            continue
        with open(save_path, "w+") as f:
            f.write(html)
async def main():
    for season in SEASONS:
        await scrape_season(season)
    standings_files = os.listdir(STANDINGS_DIR)
    standings_files = [s for s in standings_files if ".html" in s]
    for f in standings_files:
        filepath = os.path.join(STANDINGS_DIR, f)
        await scrape_game(filepath)
if __name__ == "__main__":
    import asyncio
    asyncio.run(main())