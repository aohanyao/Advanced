import requests
from lxml import etree
from lxml.html import Element

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like '
                  'Gecko) Chrome/52.0.2743.116 Safari/537.36'
}

# for index in range(0, 251, 25):
for index in range(0, 51, 25):
    url = "https://movie.douban.com/top250?start=" + str(index)
    # 获取请求结构
    rp = requests.get(url=url, headers=headers)
    # 创建HTML对象
    html = etree.HTML(rp.content)  # type : Element.Element
    # 获取一个条目
    allItems = html.xpath("//ol/li/div[@class='item']")
    # 遍历没一个条目
    for item in allItems:
        try:
            # 获取标题
            title = item.xpath(
                "./div[@class='info']/div[@class='hd']/a/span[@class='title']/text()"
            )[0]
            # 获取链接
            link = item.xpath("./div[@class='info']/div[@class='hd']/a/@href")[0]
            # 获取描述
            des = item.xpath("./div[@class='info']/div[@class='bd']/p[@class='quote']/span/text()")[
                0]
            # 获取评分
            star = item.xpath(
                "./div[@class='info']/div[@class='bd']/div[@class='star']/span["
                "@class='rating_num']/text()")[0]
            # 获取图片地址
            img = item.xpath("./div[@class='pic']/a/img/@src")[0]
            print("----------------------开始---------------------")
            print("名称:", title.strip())
            print("评分:", star)
            print("描述:", des)
            print("链接:", link)
            print("图片:", img)
            print("----------------------结束---------------------\n")
        except:
            print("发生一点错误")
            pass
