# ShortVideo(短视频无水印解析)
字面意思，就是短视频无水印解析，目前支持皮皮虾、抖音、微视和全民小视频四个平台

## 历史版本
  v1.2  增加全民小视频平台，皮皮虾平台和微视平台可以获取作者头像啦  
  v1.1  增加皮皮虾层模式，层主发的视频也可以解析啦  
  v1.0  短视频无水印解析，支持皮皮虾，抖音，微视三个平台

## 使用
* 皮皮虾
  ```
  Map<String,String> map_ppx = ShortVideo.getPPX(String url, boolean cell);
  ```
  > cell：层模式，'true'代表层模式，'false'代表正常模式
  
  返回Map类型数据，键值对照如下<br>

  键 | 值 | 备注
  -|-|-
  VideoID|短视频ID|
  OriginTitle|短视频标题|如果没有标题会返回当前时间戳
  OriginUrl|短视频无水印链接|
  AuthorID|短视频作者ID|
  AuthorName|短视频作者Name|
  AuthorImage|短视频作者头像|
  
* 抖音
  ```
  Map<String,String> map_dy = ShortVideo.getDY(String url);
  ```
  
  返回Map类型数据，键值对照如下<br>

  键 | 值 | 备注
  -|-|-
  VideoID|短视频ID|
  OriginTitle|短视频标题|如果没有标题会返回当前时间戳
  OriginUrl|短视频无水印链接|


* 微视
  ```
  Map<String,String> map_ws = ShortVideo.getWS(String url);
  ```
  
  返回Map类型数据，键值对照如下<br>

  键 | 值 | 备注
  -|-|-
  VideoID|短视频ID|
  OriginTitle|短视频标题|如果没有标题会返回当前时间戳
  OriginUrl|短视频无水印链接|
  AuthorID|短视频作者ID|
  AuthorName|短视频作者Name|
  AuthorImage|短视频作者头像|
  
* 全民小视频
  ```
  Map<String,String> map_qm = ShortVideo.getQM(String url);
  ```
  
  返回Map类型数据，键值对照如下<br>

  键 | 值 | 备注 
  -|-|-
  VideoID|短视频ID|
  OriginTitle|短视频标题|如果没有标题会返回当前时间戳
  OriginUrl|短视频无水印链接|
  AuthorID|短视频作者ID|
  AuthorName|短视频作者Name|
  AuthorImage|短视频作者头像|
