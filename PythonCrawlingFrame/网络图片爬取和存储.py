import requests
import os
url="https://b-ssl.duitang.com/uploads/item/201312/05/20131205172450_dzvYv.thumb.700_0.jpeg"
root="D://迅雷下载"
path=root+url.split('/')[-1]
try:
   if not os.path.exists(root):
       os.mkdir(root)
   if not os.path.exists(path):
       r=requests.get(url)
       with open(path,'wb') as f:
           f.write(r.content)
           f.close()
           print("文件保存成功")
    else print("文件已存在")
except:
    print("爬取失败")
