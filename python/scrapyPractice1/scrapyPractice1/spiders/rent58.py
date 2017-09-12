# -*- coding: utf-8 -*-
import scrapy


class Rent58Spider(scrapy.Spider):
    name = 'rent58'
    allowed_domains = ['http://sz.58.com/']
    start_urls = ['http://sz.58.com/xinanlu/chuzu/0/b9/']

    def parse(self, response):
        print(response.body)
        # pass
