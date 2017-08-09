import urllib.request

response = urllib.request.urlopen('http://sz.58.com/xixiangsz/chuzu/0/b9/')

# print(response.read().decode('utf-8'))
print(response.status)
print(response.getheaders())