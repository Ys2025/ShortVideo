# ShortVideo(短视频无水印解析)
字面意思，就是短视频无水印解析，目前支持皮皮虾，抖音，微视三个平台

## 历史版本
  v1.1  增加皮皮虾层模式，层主发的视频也可以解析啦  
  v1.0  短视频无水印解析，支持皮皮虾，抖音，微视三个平台

## 使用
* 皮皮虾
  ```
  Map<String,String> map_ppx = ShortVideo.getPPX(String url, boolean cell);
  ```
  > cell：层模式，'true'代表层模式，'false'代表正常模式
  
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
  Map<String,String> map_dy = ShortVideo.getDY(String url);
  ```
  
  返回Map类型数据，键值对照如下<br>

  键 | 值
  -|-
  VideoID|短视频ID
  OriginTitle|短视频标题
  OriginUrl|短视频无水印链接


* 微视
  ```
  Map<String,String> map_ws = ShortVideo.getWS(String url);
  ```
  
  返回Map类型数据，键值对照如下<br>

  键 | 值
  -|-
  VideoID|短视频ID
  OriginTitle|短视频标题
  OriginUrl|短视频无水印链接
  AuthorID|短视频作者ID
  AuthorName|短视频作者Name
