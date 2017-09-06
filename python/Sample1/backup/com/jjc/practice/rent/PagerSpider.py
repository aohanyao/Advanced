#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Copyright (c) 2011 -


'''
    抓取58同城上面的租房信息，这个文件主要抓取页码
'''
import pymysql
from lxml import etree

import requests

from backup.com.jjc.practice.rent.RentDetails import RentDetails

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36 '
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
        #
        # for detail in self.__detailUrls:
        #     print(detail.name)
        insertRentList(self.__detailUrls)

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
        for i in range(1, 5):
            self.__allPageUrls.append("http://sz.58.com/xixiangsz/chuzu/0/b9/pn" + str(i) + "/")

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
                    # 写入文件
                    with open("58rent_list.txt", "at", encoding="utf-8") as f:
                        f.writelines("--------------start---------------\n")
                        f.writelines("名称：" + title.strip() + "\n")
                        f.writelines("链接：" + url.strip() + "\n")
                        f.writelines("厅室：" + room.strip() + "\n")
                        f.writelines("价格：" + price.strip() + "元/月\n")
                        f.writelines("地址：" + add.strip() + "\n")
                        f.writelines("来源：" + master.strip("：") + "\n")
                        f.writelines("--------------end---------------\n\n")
                    # 添加对象
                    self.__detailUrls.append(RentDetails(url.strip(), title.strip(), price.strip(),
                                                         room.strip(), add.strip(), master.strip()))
            except Exception as e:
                print(e)


def insertRentList(datas):
    """
    插入数据到数据库中
    :param rentData:
    :return:
    """
    # 打开数据库
    db = pymysql.connect("localhost", "root", "root", "python_58_rent", charset='utf8')
    # 创建游标
    cursor = db.cursor()
    print("开始插入数据")
    try:
        for data in datas:
            # 拼接sql
            sql = "INSERT INTO rent_58_list(name,master,address,link,money,room) " \
                  "VALUES ('" + data.name + "','" + data.master + "','" + data.address + "'," \
                                                                                         "'" + data.link + "','" + data.money + "','" + data.room + "')"
            cursor.execute(sql)
            # cursor.execute(sql)
            db.commit()
    except Exception as e:
        print(e)
        db.rollback()
    print("结束插入数据")
    db.close()
