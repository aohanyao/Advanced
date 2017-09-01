import requests

url = 'http://httpbin.org/cookies'
cookies = {"yst-jsessionid": "70vt0j58f9x9!1504259742395"}
r = requests.get(url, cookies=cookies)
print(r.text)
