# ShortVideo(短视频无水印解析)
子面意思，就是短视频无水印解析，目前支持皮皮虾，抖音，微视三个平台

## 使用
* 皮皮虾
  ```
  Map<String,String> map_ppx = ShortVideo.getPPX(皮皮虾短视频链接,层模式);
  ```
  
  返回Map类型数据，键值对照如下<br>

  键 | 值
  -|-
  VideoID|短视频ID
  OriginTitle|短视频标题
  OriginUrl|短视频无水印链接
  AuthorID|短视频作者ID
  AuthorName|短视频作者Name
  
  
* 抖音
  ```
  Map<String,String> map_dy = ShortVideo.getDY(抖音短视频链接);
  ```
  
  返回Map类型数据，键值对照如下<br>

  键 | 值
  -|-
  VideoID|短视频ID
  OriginTitle|短视频标题
  OriginUrl|短视频无水印链接


* 微视
  ```
  Map<String,String> map_ws = ShortVideo.getWS(微视短视频链接);
  ```
  
  返回Map类型数据，键值对照如下<br>

  键 | 值
  -|-
  VideoID|短视频ID
  OriginTitle|短视频标题
  OriginUrl|短视频无水印链接
  AuthorID|短视频作者ID
  AuthorName|短视频作者Name
