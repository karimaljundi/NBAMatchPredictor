from bs4 import BeautifulSoup
import pandas as pd
import requests
import time
from tqdm import tqdm  # Import tqdm for progress bars

all_teams = []
headers =[]
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

all_player_data = []  # List to hold all player data

# Use tqdm to create a progress bar for the team URLs
for team_url in tqdm(team_urls, desc="Scraping teams", unit="team"):
    players = []
    try:
        response = requests.get(team_url)
        time.sleep(3)  # Wait 3 seconds between requests
        
        if response.status_code != 200:
            print(f"Failed to fetch {team_url}: Status code {response.status_code}")
            continue
            
        soup = BeautifulSoup(response.text, 'html.parser')
        stats = soup.find('table', id='franchise_register')
        
        if stats:
            # Get all rows from tbody
            rows = stats.find('tbody').find_all('tr')
            for row in rows:
                # Extract player data from each row
                headers = [header.get_text(strip=True) for header in stats.find('thead').find_all('th')]
                player_data = [cell.get_text(strip=True) for cell in row.find_all('td')]
                print(f"Extracted player data: {player_data}")  # Debugging line to check data structure
                if player_data:  # Only add if there's data
                    all_player_data.append(player_data)
                
    except Exception as e:
        print(f"Error processing {team_url}: {e}")

# Create a DataFrame and save to CSV
# columns = ["Player", "Year", "Team", "Games", "Points", "Assists", "Rebounds", "Steals", "Blocks", "Turnovers"]  # Adjust columns as needed
headers = headers[5:]
df = pd.DataFrame(all_player_data, columns=headers)
df.to_csv('players_data.csv', index=False)

print("Data scraped and saved to players_data.csv")
