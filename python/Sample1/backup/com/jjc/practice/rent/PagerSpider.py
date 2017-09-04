#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Copyright (c) 2011 -


'''
    抓取58同城上面的租房信息，这个文件主要抓取页码
'''

from lxml import etree

import requests

from backup.com.jjc.practice.rent.RentDetails import RentDetails

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36'
}


class PagerSpider:
    # 详情页面的URL
    __detailUrls = []

    # 下一页面的url
    __allPageUrls = []

    def startSpider(self, startUrl):
        print("开始页码")
        self.__getPage(startUrl)
        # 开始获取每一个页面
        self.__getDetailsLinks()

    def __getPage(self, url):
        print("获得所有的页码")
        # 获取第一个页面
        r = requests.get(url, headers=headers)
        # 获取到html
        html = etree.HTML(r.content)
        # //*[@id="bottom_ad_li"]/div[2]/a[3]/span
        pagers = html.xpath("//*[@id='bottom_ad_li']/div[2]/a[3]/span/text()")[0]
        print(pagers)
        # for i in range(1, int(pagers) + 1):
        for i in range(1, 3):
            self.__allPageUrls.append("http://sz.58.com/xixiangsz/chuzu/0/b9/pn" + str(i) + "/")

        #
        for detail in self.__detailUrls:
            print(detail)

    # 获得所有的详情页面
    def __getDetailsLinks(self):
        for page in self.__allPageUrls:
            r = requests.get(page, headers=headers)
            html = etree.HTML(r.content)
            # 获取到ul
            lis = html.xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li")
            # 遍历 ul 得到li
            try:
                for li in lis:
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
                    self.__detailUrls.append(RentDetails(url.strip(), title.strip(), price.strip(),
                                                         room.strip(), add.strip()))
            except Exception as e:
                e.args
                print(e)
