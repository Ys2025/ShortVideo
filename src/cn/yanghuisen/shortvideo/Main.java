package cn.yanghuisen.shortvideo;

import java.util.Map;

/**
 * @author 2025
 * @version 1.0
 * @date 2020/1/31 19:42
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Map<String,String> map_ppx = ShortVideo.getPPX("https://h5.pipix.com/s/gUeRpY/",true);
        System.out.println(map_ppx);

        Map<String,String> map_dy = ShortVideo.getDY("https://v.douyin.com/gmP8YY/");
        System.out.println(map_dy);

        Map<String,String> map_ws = ShortVideo.getWS("https://h5.weishi.qq.com/weishi/feed/7dpn6XoUd1IRxamd9/wsfeed?wxplay=1&id=7dpn6XoUd1IRxamd9&spid=8090566225236578305&qua=v1_and_weishi_6.5.1_588_212011448_d&chid=100081014&pkg=3670&attach=cp_reserves3_1000370011");
        System.out.println(map_ws);

        Map<String,String> map_qm = ShortVideo.getQM("https://quanmin.hao222.com/sv2?source=share-h5&pd=qm_share_mvideo&vid=4873157894657749951&shareTime=1580624047&shareid=0847696953&shared_cuid=gu-q8l8U2t0Ouv8Cg8vPt08c2ajruvfq_823a_i82aKXLqqqB&shared_uid=AqqqB");
        System.out.println(map_qm);
    }
}
