package cn.yanghuisen.shortvideo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * @author 2025
 * @version 1.0
 * @date 2020/1/31 20:36
 * @Description: 抖音
 */
class DY {
    private String videoUrl;
    private String videoJson;

    private String videoId;
    private String videoOriginUrl;
    private String videoOriginTitle;

    public DY(String videoUrl) {
        this.videoUrl = videoUrl;
        this.videoId = getID();
        this.videoJson = getJson();
        this.videoOriginUrl = getOriginUrl();
        this.videoOriginTitle = getOriginTitle();
    }

    private String getJson(){
        String result = "";
        try {
            URL url = new URL("https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids="+this.videoId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line=br.readLine())!=null){
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getID(){
        String id = "";
        try {
            URL url = new URL(this.videoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            String s = conn.getHeaderField("Location");
            id = s.substring(s.indexOf("video/")+6,s.indexOf("/?"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    private String  getOriginUrl(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        JSONArray jsonArray = jsonObject.getJSONArray("item_list");
        jsonObject = jsonArray.getJSONObject(0).getJSONObject("video").getJSONObject("play_addr");
        jsonArray = jsonObject.getJSONArray("url_list");
        HttpURLConnection conn = null;
        String s = "";
        try {
            URL url = new URL(jsonArray.getString(0));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("user-agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            s = conn.getHeaderField("Location");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private String  getOriginTitle(){
        JSONObject jsonObject = JSONObject.parseObject(this.videoJson);
        JSONArray jsonArray = jsonObject.getJSONArray("item_list");
        jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString("desc");
    }

    public String getVideoId() {
        return videoId;
    }

    public String getVideoOriginUrl() {
        return videoOriginUrl;
    }

    public String getVideoOriginTitle() {
        if (this.videoOriginTitle.isEmpty()){
            return new Date().getTime()+"";
        }
        return videoOriginTitle;
    }
}
