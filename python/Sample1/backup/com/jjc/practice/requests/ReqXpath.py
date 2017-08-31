from lxml import etree

import requests

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36'
}
r = requests.get("http://sz.58.com/xixiangsz/chuzu/0/b9", headers=headers)

content = r.content
html = etree.HTML(content)
listUl = html.xpath("//ul[@class='listUl']/li")

for li in listUl:
    try:
        des = li.xpath("./div[@class='des']/h2/a/text()")[0]
        room = li.xpath("./div[@class='des']/p[@class='room']/text()")[0]
        add = li.xpath("./div[@class='des']/p[@class='add']/text()")[0]
        geren = li.xpath("./div[@class='des']/p[@class='geren']/span/text()")[0]
        money = li.xpath("./div[@class='listliright']/div[@class='money']/b/text()")[0]
        print("----------------------------", "")
        print("标题:", des.strip())
        print("房室:", room.strip())
        print("地址:", add)
        print("来源:", geren.strip())
        print("房租:", money.strip(), "元/月")
        print("----------------------------", "\n")
    except:
        print("wrong")
