package tonghuashun;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: gams
 * @description: 同花顺指标
 * @author: CG
 * @create: 2019-04-30 11:10
 **/

@Data
public class TongHuaShunIdx implements Serializable {
    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String title;

    /**
     * 指数
     */
    private String zxj;

    /**
     * 涨跌幅
     */
    private String zdf;
}
