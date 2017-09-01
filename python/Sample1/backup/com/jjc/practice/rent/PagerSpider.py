#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Copyright (c) 2011 -


'''
    抓取58同城上面的租房信息，这个文件主要抓取页码
'''

from lxml import etree

import requests

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36'
}


class PagerSpider:
    # 详情页面的URL
    __detailUrls = []

    # 下一页面的url
    __allPageUrls = []

    def startSpider(self, startUrl):
        print("开始页面")
        self.__getPage(startUrl)

    def __getPage(self, url):
        print("获得所有的页码")
        # 获取第一个页面
        r = requests.get(url, headers=headers)
        # 获取到html
        html = etree.HTML(r.content)
        # //*[@id="bottom_ad_li"]/div[2]/a[3]/span
        pagers = html.xpath("//*[@id='bottom_ad_li']/div[2]/a[3]/span/text()")[0]

        for i in  pagers:
            print(i)

        # for page in pagers:
        #     try:
        #         # 获得路径
        #         pagerUrl = page.xpath("./@href")[0]
        #         # 添加链接
        #         self.__allPageUrls.append(pagerUrl)
        #         print(pagerUrl)
        #     except Exception as e:
        #         print("发生错误", e)

    def __getHtml(self, url):
        print("")
