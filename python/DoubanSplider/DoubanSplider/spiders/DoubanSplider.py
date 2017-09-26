# -*- coding: utf-8 -*-
from scrapy.spiders import Spider
from scrapy import Request

from DoubanSplider.items import DoubanspliderItem


class DoubanSplider(Spider):
    name = 'DoubanSplider'
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36',
    }

    def start_requests(self):
        url = 'https://movie.douban.com/top250'
        yield Request(url, headers=self.headers)

    def parse(self, response):
        # 获取所有的li
        allLis = response.xpath("//*[@id='content']/div/div[1]/ol/li")
        for li in allLis:
            try:
                item = DoubanspliderItem()
                # 获取电影名称
                item["name"] = li.xpath("./div/div[2]/div[1]/a/span[1]/text()").extract()[0]
                # 评分
                item["rating_num"] = li.xpath("./div/div[2]/div[2]/div/span[2]/text()").extract()[0]
                # 评价人数
                item["review"] = li.xpath("./div/div[2]/div[2]/div/span[4]/text()").extract()[0]
                # 简介
                item["desc"] = li.xpath("./div/div[2]/div[2]/p[2]/span/text()").extract()[0]
                yield item
                # 下一页
                next_page = response.xpath(
                    "//*[@id='content']/div/div[1]/div[2]/span[3]/a/@href").extract()
                if next_page:
                    yield Request(url='https://movie.douban.com/top250' + next_page[0],
                                  headers=self.headers)
            except Exception as e:
                print(e)
