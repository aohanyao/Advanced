# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


#
# class Scrapypractice1Item(scrapy.Item):
#     # define the fields for your item here like:
#     # name = scrapy.Field()
#     pass
#

class RentDetails(scrapy.Item):
    """
    58租房的数据
    """
    link = scrapy.Field()
    name = scrapy.Field()
    money = scrapy.Field()
    room = scrapy.Field()
    address = scrapy.Field()
    master = scrapy.Field()
