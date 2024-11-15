from bs4 import BeautifulSoup
import pandas as pd
import requests
import time
from tqdm import tqdm 
import re

years = list(range(1947, 2024))