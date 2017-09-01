import requests
from lxml import etree

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, '
                  'like Gecko) Chrome/52.0.2743.116 Safari/537.36 ',
    "yst-jsessionid": "70vt0j58f9x9!1504259742395"
}
# yst-jsessionid=70vt0j58f9x9!1504259742395
cookies = {"yst-jsessionid": "70vt0j58f9x9!1504259742395"}
r = requests.get(
    'http://120.24.164.29/manage#/investment_customer?action=customer&projectId=1&projectName'
    '=北京移动硅谷&projectCodetest00200', headers=headers, cookies=cookies)
content = r.content

print(content.title())
html = etree.HTML(r.content)

sum = html.xpath("//*[@id='investment_customer']/div/div/div[1]/div[6]/div[2]/text()")
print("总客户:", sum)
