/*京东商品页面爬取通用框架*/
import requests
url="https://item.jd.com/3807143.html"
try:
    r=requests.get(url)
    r.raise_for_status  /* r.status_code*/
    r.encoding=r.apparent_encoding
    print(r.text[:1000])
except:
    print("爬取失败")

/*亚马逊类对自己数据保护比较好的网站*/

