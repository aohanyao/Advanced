# -*- coding: utf-8 -*-
import scrapy
from numpy import unicode
from  scrapy import Request
from scrapyPractice1.items import RentDetails


class Rent58Spider(scrapy.Spider):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36',
    }

    name = 'rent58'
    allowed_domains = ['http://sz.58.com/']
    start_urls = ['http://sz.58.com/xinanlu/chuzu/0/b9/']

    def parse(self, response):
        # 获取所有的li
        lis = response.xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li")
        # 所有的数据

        for li in lis:
            try:
                item = RentDetails()
                # 房源名称
                name = li.xpath("./div[2]/h2/a/text()").extract()[0]
                item["name"] = name.strip()
                # item["name"] = unicode(name, "utf-8")
                # 详情链接
                link = li.xpath("./div[2]/h2/a/@href").extract()[0]
                item["link"] = link.strip()
                # 地址
                address = li.xpath("./div[2]/p[2]/a[1]/text()").extract()[0]
                item["address"] = address.strip()
                # 房室
                room = li.xpath("./div[2]/p[1]/text()").extract()[0]
                item["room"] = room.strip()
                # 价格
                money = li.xpath("./div[3]/div[2]/b/text()").extract()[0]
                item["money"] = money.strip()
                # 房东
                master = li.xpath("./div[2]/p[3]/text()").extract()[0]
                item["master"] = master.strip()
                # 加入数据
                yield item
            except Exception as e:
                print(e)
            try:
                # 获取下一页的数据
                nextUrl = response.xpath(
                    "//*[@id='bottom_ad_li']/div[2]/a[@class='next']/@href").extract()
                if nextUrl:
                    print("开始下", nextUrl[0])
                    yield Request(url=nextUrl[0], headers=self.headers)
            except Exception as e:
                print("获取下一页失败", e)
