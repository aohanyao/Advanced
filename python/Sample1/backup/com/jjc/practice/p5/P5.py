import requests

r = requests.get("http://cuiqingcai.com")

print(r.status_code)
print(r.headers)
