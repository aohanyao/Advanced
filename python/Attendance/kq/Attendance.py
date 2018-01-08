import requests

print("开始查询数据")

headers = {"Cookie": "CurrUser=jiangjunchao; PhoneDeviceId=99000836762455",
           "Content-Type": "application/json",
           "User-Agent": 'Mozilla/5.0 (Linux; Android 7.0; MI MAX Build/NRD90M; wv) '
                         'AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 '
                         'MQQBrowser/6.2 TBS/043805 Mobile Safari/537.36 wxwork/2.4.5 '
                         'MicroMessenger/6.3.22 NetType/WIFI Language/zh',
           }

r = requests.post('http://wx.yidagroup.com:8001/Web/Attendance_on.aspx/LoadData',
                  json={'Lon': '114.062736', 'Lat': '22.535645'}, headers=headers)
print(r.content)
