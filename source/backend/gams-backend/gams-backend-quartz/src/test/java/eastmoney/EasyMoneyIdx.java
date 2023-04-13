package eastmoney;

import lombok.Data;

/**
 * @program: gams
 * @description: 东方财富指标
 * @author: CG
 * @create: 2019-04-30 13:49
 **/
@Data
public class EasyMoneyIdx {
    /**
     * 内部代码
     */
    private String code;

    /**
     * 名称
     */
    private String title;

    /**
     * 最新价
     */
    private String lastPrice;

    /**
     * 涨跌幅
     */
    private String changePercent;

    /**
     * 涨跌额
     */
    private String changeAmt;
}
