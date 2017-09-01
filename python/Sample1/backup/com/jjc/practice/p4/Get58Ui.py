from lxml import etree

import requests

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36'
}


class Get58Ui:
    url = ""

    def getUl(self, url):
        self.url = url
        r = requests.get(self.url, headers=headers)
        content = r.content
        html = etree.HTML(content)
        listUl = html.xpath("/html/body/div[3]/div[1]/div[5]/div[2]/ul/li")
        return listUl
