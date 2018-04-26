/*亚马逊类对自己数据保护比较好的网站 t.request.headers*/
import requests
url="https://www.amazon.cn/dp/B07BF25DNQ?_encoding=UTF8&ref_=pc_cxrd_658390051_recTab_658390051_t_2&pf_rd_p=7e00fee6-4e12-48f0-b4af-b99068b52067&pf_rd_s=merchandised-search-4&pf_rd_t=101&pf_rd_i=658390051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_r=SNQ5YBY7Y4HJWE56HJE7&pf_rd_r=SNQ5YBY7Y4HJWE56HJE7&pf_rd_p=7e00fee6-4e12-48f0-b4af-b99068b52067"
try:
    kv={'user-agent':'Mozilla/5.0'}
    r=requests.get(url,headers=kv)
    r.raise_for_status()
    r.encoding=r.apparent_encoding
    print(r.text[:1000])
except:
    print('爬取失败')
