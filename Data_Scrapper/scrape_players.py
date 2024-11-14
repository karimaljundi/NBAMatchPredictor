from bs4 import BeautifulSoup
import pandas as pd
import requests
import time

all_teams = []
html = requests.get('https://www.basketball-reference.com/teams')
soup = BeautifulSoup(html.text, 'lxml')
# Check if request was successful
if html.status_code != 200:
    print(f"Failed to fetch data: Status code {html.status_code}")
    exit()

# The table might be in a different location or have a different ID
table = soup.find('table', id='teams_active')  # Changed from div to table
if table is None:
    print("Could not find the teams table")
    exit()

links = table.find_all('a')
links = [l.get("href").replace("/teams/", '') for l in links]

team_urls = [f"https://www.basketball-reference.com/teams/{l}players.html" for l in links]

for team_url in team_urls:
    players = []
    try:
        response = requests.get(team_url)
        # Add delay between requests
        time.sleep(3)  # Wait 3 seconds between requests
        
        if response.status_code != 200:
            print(f"Failed to fetch {team_url}: Status code {response.status_code}")
            continue
            
        soup = BeautifulSoup(response.text, 'html.parser')
        stats = soup.find('table', id='franchise_register')
        
        if stats:
            # Get all rows from tbody
            rows = stats.find_all('tbody').find('tr')
            print(len(rows))
            break
            # for row in rows:
            #     # Process each row here
            #     print(row.get_text())
            #     pass
                
    except Exception as e:
        print(f"Error processing {team_url}: {e}")

# print(team_urls)