# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class DoubanspliderItem(scrapy.Item):
    # 电影名称
    name = scrapy.Field()
    # 评分
    rating_num = scrapy.Field()
    # 评价人数
    review = scrapy.Field()
    # 简介
    desc = scrapy.Field()
