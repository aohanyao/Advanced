# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html

import pymysql


class Scrapypractice1Pipeline(object):
    def __init__(self):
        print("初始化")

    def process_item(self, item, spider):
        print(item)
        return item
