from lxml import etree

import requests

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36'
}
r = requests.get("http://sz.58.com/xixiangsz/chuzu/0/b9", headers=headers)
content = r.content
html = etree.HTML(content)
listUl = html.xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li")

for li in listUl:
    try:
        # title /html/body/div[3]/div[1]/div[5]/div[2]/ul/li[7]/div[2]/h2/a
        title = li.xpath("./div[2]/h2/a/text()")[0]
        url = li.xpath("./div[2]/h2/a/@href")[0]
        # /html/body/div[3]/div[1]/div[5]/div[2]/ul/li[1]/div[2]/p[1]
        # add /html/body/div[3]/div[1]/div[5]/div[2]/ul/li[1]/
        add = li.xpath("./div[2]/p[2]/a[1]/text()")[0]
        # add2 /html/body/div[3]/div[1]/div[5]/div[2]/ul/li[1]/div[2]/p[2]/a[2]
        # add2 = li.xpath("./div[2]/p[2]/a[2]/text()")[0]

        # room /html/body/div[3]/div[1]/div[5]/div[2]/ul/li[7]/div[2]/p[1]
        room = li.xpath("./div[2]/p[1]/text()")[0]

        # price /html/body/div[3]/div[1]/div[5]/div[2]/ul/li[7]/div[3]/div[2]/b
        price = li.xpath("./div[3]/div[2]/b/text()")[0]

        # master /html/body/div[3]/div[1]/div[5]/div[2]/ul/li[8]/div[2]/p[3]/text()
        master = li.xpath("./div[2]/p[3]/text()")[1]
        print("--------------start---------------")
        print("名称：", title.strip())
        print("链接：", url.strip())
        print("厅室：", room.strip())
        print("价格：", price.strip(), "元/月")
        print("地址：", add.strip())
        print("来源：", master.strip("："))
        print("--------------end---------------\n")
    except:
        print("发生错误")
