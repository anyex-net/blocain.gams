package eastmoney;

import com.alibaba.fastjson.JSONArray;
import com.fintech.gams.utils.HttpUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: gams
 * @description: 东方财富API测试类
 * @author: CG
 * @create: 2019-04-29 16:02
 **/

@Slf4j
public class EastMoneyAPITest {

    @Test
    public void testEastMoneyIndex() {
        String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx";
        //亚洲市场指数
        String cmd1 = "0000011,3990012,0003001,3990062,3990052,HSI5,HSCEI5,HSCCI5,SPHKG5,N225_UI,KS11_UI,STI_UI,TWII_UI";
        //欧洲市场指数
        String cmd2 = "FTSE_UI,GDAXI_UI,FCHI_UI,IBEX_UI,MIB_UI,AEX_UI,SSMI_UI,OMXSPI_UI,ICEXI_UI,ISEQ_UI,RTS_UI,ASE_UI,BFX_UI,HEX_UI,OSEAX_UI,ATX_UI,WIG_UI";
        //美洲市场指数
        String cmd3 = "DJIA_UI,SPX_UI,NDX_UI,TSX_UI,MXX_UI,BVSP_UI";
        //澳洲市场指数
        String cmd4 = "NZ50_UI,AS51_UI,AORD_UI";
        //
        Map<String, String> requestParams = getEastMoneyRequestParams(cmd1);
        //
        HttpClient client = HttpUtils.getHttpClient();
        String content = HttpUtils.get(client, url, requestParams, null, getHeader());
        //
        Map<String, List<EasyMoneyIdx>> map = dealEastMoneyRes("东方财富各类指数行情", content);
        //
        System.out.println(map);
        // TODO: 2019/4/30
    }

    @Test
    public void testHongKongStocksIndex() {
        String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx";
        //香港市场指数
        String cmd1 = "HSI5,HSCEI5,HSCCI5,SPHKG5";
        //中国南方航空公司
        String cmd2 = "010555";
        //
        Map<String, String> requestParams = getEastMoneyRequestParams(cmd2);
        //
        HttpClient client = HttpUtils.getHttpClient();
        String content = HttpUtils.get(client, url, requestParams, null, getHeader());
        System.out.println(content);
        //
        Map<String, List<EasyMoneyIdx>> map = dealEastMoneyRes("东方财富香港行情", content);
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
     * EastMoney request params
     *
     * @param cmd 股市板块
     * @return
     */
    public Map<String, String> getEastMoneyRequestParams(String cmd) {

        Map<String, String> map = new HashMap<>();
        map.put("cmd", cmd);
        map.put("type", "CT");
        map.put("sty", "FCRH");
        map.put("st", "y");
        map.put("page", "1");
        map.put("pageSize", "13");
        map.put("js", "var RrAmRFTW={rank:[(x)],pages:(pc),total:(tot)}");
        map.put("token", "7bc05d0d4c3c22ef9fca8c2a912d779c");
        map.put("jsName", "quote_123");
        map.put("_g", "0.628606915911589");

        return map;
    }

    /**
     * 根据地区板块 存入集合
     *
     * @param location
     * @param resp
     * @return
     */
    public Map<String, List<EasyMoneyIdx>> dealEastMoneyRes(String location, String resp) {
        Map<String, List<EasyMoneyIdx>> map = new HashMap<>();
        if (resp == null) {
            return null;
        }
        List<EasyMoneyIdx> list = new ArrayList<>();
        EasyMoneyIdx easyMoneyIdx;
        String substring = resp.substring(resp.indexOf("["), resp.lastIndexOf("]") + 1);
        JSONArray array = JSONArray.parseArray(substring);
        String[] split;
        //
        for (int i = 0; i < array.size(); i++) {
            split = array.get(i).toString().split(",");
            easyMoneyIdx = new EasyMoneyIdx();
            easyMoneyIdx.setCode(split[1]);
            easyMoneyIdx.setTitle(split[2]);
            easyMoneyIdx.setLastPrice(split[3]);
            easyMoneyIdx.setChangeAmt(split[4]);
            easyMoneyIdx.setChangePercent(split[5]);
            list.add(easyMoneyIdx);
        }
        map.put(location, list);

        return map;
    }
}
