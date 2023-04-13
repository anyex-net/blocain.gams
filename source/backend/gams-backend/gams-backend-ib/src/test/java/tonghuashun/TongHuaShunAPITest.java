package tonghuashun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fintech.gams.utils.HttpUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: gams
 * @description: 同花顺API测试类
 * @author: CG
 * @create: 2019-04-29 16:02
 **/

@Slf4j
public class TongHuaShunAPITest {

    @Test
    public void testTongHuaShunIndex() {
        String url = "http://q.10jqka.com.cn/global/index/ajax/1/";
        //
        HttpClient client = HttpUtils.getHttpClient();
        String content = HttpUtils.get(client, url, null, null, getHeader());
        //
        Map<String, List<TongHuaShunIdx>> map = dealTongHuaShuResp(content);
        //
        System.out.println(map);
        // TODO: 2019/4/30
    }

    /**
     * add请求头
     *
     * @return
     */
    public static Map<String, String> getHeader() {
        Map<String, String> header = Maps.newHashMap();
        header.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        header.put("hexin-v", "AlwerqNCbKiVvxj0TMcCVvGoK3EN1QD_gnkUwzZdaMcqgfKnXuXQj9KJ5FGF"); //necessary

        return header;
    }

    /**
     * 处理同花顺返回信息
     *
     * @param resp
     * @return
     */
    public Map<String, List<TongHuaShunIdx>> dealTongHuaShuResp(String resp) {
        Map<String, List<TongHuaShunIdx>> map = new HashMap<>();
        TongHuaShunIdx tongHuaShunIdx;
        List<TongHuaShunIdx> list;
        JSONObject jsonObject = JSON.parseObject(resp);
        //
        String[] countries = {"美国", "墨西哥", "阿根廷", "巴西", "加拿大", "中国", "印度", "日本", "韩国", "印度尼西亚", "西班牙", "俄罗斯", "挪威", "瑞典", "德国", "法国",
                "新西兰", "澳大利亚", "英国", "马拉西亚", "意大利", "泰国", "新加坡"};
        //
        for (String country : countries) {
            String amc = jsonObject.get(country).toString();
            //
            if (StringUtils.isNotBlank(amc)) {
                JSONArray dataArr = JSON.parseObject(amc).getJSONArray("dataArr");
                list = new ArrayList<>();
                for (int i = 0; i < dataArr.size(); i++) {
                    tongHuaShunIdx = JSON.parseObject(dataArr.get(i).toString(), TongHuaShunIdx.class);
                    if (tongHuaShunIdx != null) {
                        list.add(tongHuaShunIdx);
                    }
                }
                //
                map.put(country, list);
            }
        }

        return map;
    }
}
