<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <style>
        body{
            font-family:Source Han Sans CN Regular,Serif;
            font-size: 8pt;
            line-height: 1.5;
        }
        @media print {
            div.header-left {
                display: block;
                position: running(header-left);
            }
            img{page-break-inside:avoid;}
            h4{page-break-inside:avoid;}
            table div{page-break-inside:avoid;}
            /*table{page-break-inside:avoid;}*/
        }
        @page {
            size: 595.3pt 840pt;
            margin: 80pt 1cm 80pt 1cm;
            @top-right {
                content: element(header-right)
            };
            @top-left {
                content: element(header-left)
            };
            /*@bottom-center {*/
            /*    content : "Page " counter(page) " of " counter(pages);*/
            /*};*/
            @bottom-center {
                content: element(footer)
            }
            /*@bottom-left {*/
            /*    content: element(footer-left)*/
            /*}*/
            /*@bottom-right {*/
            /*    content: element(footer-right)*/
            /*}*/
        }

        #footer {
            position: running(footer);
        }

        #pages:before {
            content: counter(page);
        }

        #pages:after {
            content: counter(pages);
        }
        #header-left div{
            line-height: 20px;
        }
        li{
            position: relative;
        }
        li:nth-child(n)::before{
            content: '';
            position: absolute;
            background: black;
            border-radius: 50%;
            width: 4px;
            height: 4px;
            line-height: 5px;
            left: -12px;
            top: 6px;
        }
        .relates li:nth-child(n)::before{
            content: none;
        }
        ul .relates::before{
            content: none;
        }
        ul{
            list-style-type:none;
            padding-left: 36px;
            margin-top: 5px;
        }
        .specialUl{
            list-style-type:none;
            padding-left: 12px;
            margin-top: 5px;
        }

        .clearfix:after{/*伪元素是行内元素 正常浏览器清除浮动方法*/
            content: "";
            display: block;
            height: 0;
            clear:both;
            visibility: hidden;
        }
        .clearfix{
            *zoom: 1;/*ie6清除浮动的方式 *号只有IE6-IE7执行，其他浏览器不执行*/
        }
        .Beneficial{
            border-top: 1px solid #000;
            border-bottom: 1px solid #000;
            /*height: 18px;*/
            line-height: 18px;
        }
        .BeneficialII{
            padding-right: 10px;
            height: 16px;
            line-height: 16px;
        }
        .PartI{
            color: #fff;
            background: #000;
            width: 40px;
            text-align: center;
        }
        table{
            width: 100%;
            border-bottom:0px;
            border-top:0;
            border-left:0;
            border-right:0px;
        }
        tr{
            display: flex;
        }
        tr .valueTd{
            word-wrap: break-word;
            word-break: break-all;
        }
        td{
            border-top: 0.5px solid #000;
            border-left: 0.5px solid #000;
            border-bottom: 0px;
            line-height: 16px;
            min-height: 30px;
            /*word-break: break-all;*/
            border-right: 0px;
            border-bottom: 0px;
            flex: 1;
            word-break: break-word;
            padding: 4px;
        }
        tr:last-child td{
            border-bottom: 0.5px solid #000;
        }
        td:last-child{
            border-right: 0.5px solid #000;
        }
        h4{
            padding-bottom: 6px;
        }
        input{
            vertical-align: center;
        }
        .inputCheckbox>div>div{
            display: inline-block;
        }
        .inputCheckbox1{
            width: 20%;
        }
        .inputCheckbox2{
            width: 19.7%;
        }
        .inputCheckbox3{
            width: 18.5%;
        }
        .inputCheckbox4{
            width: 20%;
        }
        .inputCheckbox5{
            width: 16%;
        }
        .itemList{
            border-bottom: 1px solid #000;
            font-size: 10px;
        }
        .delineClass{
            display: inline-block;
            border-bottom: 1px solid #000000;
        }
        .inlineBlock{
            display: inline-block;
        }
        .borderB{
            border-bottom: 1px solid #000000;
        }
        /*.ninea input{*/
        /*    padding-right: 50px;*/
        /*  }*/

    </style>
</head>
<body>
<div id="header">
    <div id="header-left" class="header-left">
        <img src="data:${fileType};base64,${file64Str}" width="150px" class="inlineBlock" />
        <span style="margin-left: 20px;text-align: right;float: right">
<!--                <div>MOF Securities W-8 Individual</div>-->
            <!--                <div>Self-Certification Instructions to Account Holder</div>-->
            </span>
    </div>
</div>
<div id="footer">
    <div id="footer-left" class="footer-left" style="text-align: left;float: left">
            <span>
                <div>For Paperwork Reduction Act Notice, see separate instructions.</div>
                <!--                <div>2019  国瑞证券有限公司，版权所有，不得翻印。</div>-->
            </span>

    </div>
    <div id="footer-right" class="footer-right" style="text-align: right;float: right">
            <span>
                <div>Cat. No. 25402Q  &#160; Form  W-8IMY  (Rev. 6-2017)</div>
                <!--                <div>W-8BEN-E  (Rev. 7-2017)</div>-->
                <div>Page <span id="pages"> of </span></div>
                <!--                <div>第 <span id="pages">页，共 </span>页</div>-->
            </span>
    </div>
</div>
<div class="page1">
    <div style="height: 115px" class="borderB">
        <div style="width: 20%;border-right: 1px solid #000;height: inherit; vertical-align: middle;" class="inlineBlock">
            <div>Form <h3 style="font-size: 16px; margin: 5px 0px">W-8IMY</h3></div>
            <p style="margin: 0px">(Rev. June 2017)</p>
            <div>Department of the Treasury  Internal Revenue Service</div>
        </div>
        <div style="width: 60%;border-right: 1px solid #000;height: inherit;vertical-align: middle; text-align: center;" class="inlineBlock">
            <h3 style="font-size: 14px;margin: 0px">Certificate of Foreign Intermediary, Foreign Flow-Through Entity, or Certain U.S. Branches for United States Tax Withholding and Reporting</h3>
            <div ><strong>▶  Section references are to the Internal Revenue Code. </strong></div>
            <div > <strong>▶ Go to www.irs.gov/FormW8IMY for instructions and the latest information.  </strong> </div>
            <div><strong>▶  Give this form to the withholding agent or payer. Do not send to the IRS. </strong></div>
        </div>
        <div style="width: 14%;height: inherit;line-height: 115px; vertical-align: middle;font-size: 10px;text-align: center;" class="inlineBlock">
            OMB No. 1545-1621
        </div>
    </div>
    <div class="clearfix;">
        <div class="clearfix;" style="margin-top: 5px;height: 16px;">
            <div style="float: left;width: 200px">Do not use this form for: </div>
            <div style="float: right;width: 140px;text-align: right;"><strong>Instead, use Form:</strong> </div>
        </div>
        <ul class="specialUl">
            <li class="clearfix">
                <div>A beneficial owner solely claiming foreign status or treaty benefits (other than a qualified intermediary (QI) acting as a qualified </div>
                <div>derivatives dealer (QDD)) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                    . . . . . . . . . . . . . . . . . .. . . . . . . . . . . . . . . . W-8BEN or W-8BEN-E</div>
            </li>
            <li>
                <div>A hybrid entity claiming treaty benefits on its own behalf (other than a QI acting as a QDD) . . . . . . .. . . . . . . . . . . . . .
                    . . . . . . . . . . . . . . W-8BEN-E</div>
            </li>
            <li class="clearfix">
                <div style="float: left;">A foreign person claiming that income is effectively connected with the conduct of a trade or business in the United States
                    . . . . . . . . .W-8ECI</div>
            </li>
            <!--            <li class="clearfix">-->
            <!--                <div style="float: left;width: 510px"> A foreign individual  . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . </div>-->
            <!--                <div style="float: right;width: 180px;text-align: right;">  W-8BEN (Individual) or Form 8233  </div>-->
            <!--            </li>-->
            <!--            <li class="clearfix">-->
            <!--                <div style="width: 800px;">A foreign individual or entity claiming that income is effectively connected with the conduct of trade or business within the U.S. </div>-->
            <!--                <div style="float: left;">(unless claiming treaty benefits). . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .-->
            <!--                    . . . . . . . . . . . . . . . . . . . . . . . . . . . .</div>-->
            <!--                <div style="text-align: right;">W-8ECI </div>-->
            <!--            </li>-->
            <li>
                <div>A disregarded entity with a single foreign owner that is the beneficial owner (other than a QI acting as a QDD) of the income to which this </div>
                <div>form relates. Instead, the single foreign owner should use . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                    . . . . . . . . .W-8BEN, W-8ECI, or W-8BEN-E </div>
            </li>
            <li class="clearfix">
                <div style="width: 645px;"> A foreign government, international organization, foreign central bank of issue, foreign tax-exempt organization, foreign private</div>
                <div style="">foundation, or government of a U.S. possession claiming the applicability of section(s) 115(2), 501(c), 892, 895, or 1443(b).
                    . . . . . . . .W-8EXP</div>
            </li>
            <li class="clearfix">
                <div style="float: left">U.S. entity or U.S. citizen or resident . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                    . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .W-9</div>
            </li>
            <li class="clearfix">
                <div style="float: left">A foreign person documenting itself for purposes of section 6050W . . . . . . . . . . . . . . . . . . . . . . . . . . .. . . . . . .  . .W-8BEN, W-8BEN-E, or W-8ECI</div>
            </li>
        </ul>
    </div>
    <div class="Beneficial">
        <div  class="PartI inlineBlock">Part I</div>
        <div style="width: 400px;" class="inlineBlock"><strong>Identification of Entity</strong></div>
    </div>
    <div class="itemList">
        <div style="width: 65%;border-right: 1px solid #000" class="inlineBlock">
            <div>1 Name of organization that is acting as intermediary</div>
            <div>
                ${(w8[0].organizationName)!"N/A"}
            </div>
        </div>
        <div style="width: 34%;" class="inlineBlock">
            <div>2 Country of incorporation or organization </div>
            <div>
                ${(w8[0].organizationCountry)!"N/A"}
            </div>
        </div>
    </div>
    <div class="itemList">
        <div>3 Name of disregarded entity (if applicable), see instructions</div>
        <div>
            N/A
        </div>
    </div>
    <!--第四条-->
    <div class="inputCheckbox itemList clearfix">
        <div style="width: 40%">4 Chapter 3 Status (entity type) (Must check one box only.):</div>
        <div style="float: left;width: 50.2%">
            <div><input type="checkbox"
                        <#if w8[0].chapterThreeStatus=="a1">
                            checked="true"
                        </#if>
                /> QI (including a QDD). Complete Part III.</div>
            <div><input type="checkbox"  /> Nonqualified intermediary. Complete Part IV.</div>
            <div><input type="checkbox"  /> Territory financial institution. Complete Part V.</div>
            <div><input type="checkbox"  /> U.S. branch. Complete Part VI.</div>
            <div><input type="checkbox"  /> Withholding foreign partnership. Complete Part VII.</div>
        </div>
        <div style="float: right;width: 49.8%">
            <div><input type="checkbox"  /> Withholding foreign trust. Complete Part VII.</div>
            <div><input type="checkbox"
                        <#if w8[0].chapterThreeStatus=="a7">
                            checked="true"
                        </#if>
                /> Nonwithholding foreign partnership. Complete Part VIII.</div>
            <div><input type="checkbox"  /> Nonwithholding foreign simple trust. Complete Part VIII.</div>
            <div><input type="checkbox"  /> Nonwithholding foreign grantor trust. Complete Part VIII.</div>
        </div>
    </div>
    <!--第五条-->
    <div class="itemList clearfix">
        <div>Chapter 4 Status (FATCA status) (See instructions for details and complete the certification below for the entity’s applicable status.)
            (Must check one box only.):</div>
        <div style="float: left;width: 46%; padding-right: 10px;">
            <input type="checkbox" /> Nonparticipating foreign financial institution (FFI) (including an FFI related to a
            Reporting IGA FFI other than a deemed-compliant FFI, participating FFI, or exempt beneficial owner). Complete Part IX (if applicable).
            <br />
            <input type="checkbox" /> Participating FFI.
            <br />
            <input type="checkbox" /> Reporting Model 1 FFI.
            <br />
            <input type="checkbox" /> Reporting Model 2 FFI.
            <br />
            <input type="checkbox" /> Registered deemed-compliant FFI (other than a reporting Model 1 FFI, sponsored FFI, or nonreporting IGA FFI covered in Part XIX).
            <br />
            <input type="checkbox" /> Territory financial institution. Complete Part V.
            <br />
            <input type="checkbox" /> Sponsored FFI (other than a certified deemed-compliant sponsored, closely held investment vehicle). Complete Part X.
            <br />
            <input type="checkbox" /> Certified deemed-compliant nonregistering local bank. Complete Part XII.
            <br />
            <input type="checkbox" /> Certified deemed-compliant FFI with only low-value accounts. Complete Part XIII.
            <br />
            <input type="checkbox" /> Certified deemed-compliant sponsored, closely held investment vehicle. Complete Part XIV.
            <br />
            <input type="checkbox"
                    <#if w8[0].chapterFourStatus=="b11">
                        checked="true"
                    </#if>
            /> Certified deemed-compliant limited life debt investment entity. Complete Part XV.
        </div>
        <div style="float: right;width: 50%">
            <input type="checkbox" /> Certain investment entities that do not maintain financial accounts. Complete Part XVI.
            <br />
            <input type="checkbox" /> Owner-documented FFI. Complete Part XI.
            <br />
            <input type="checkbox" /> Restricted distributor. Complete Part XVII.
            <br />
            <input type="checkbox" /> Foreign central bank of issue. Complete Part XVIII.
            <br />
            <input type="checkbox" /> Nonreporting IGA FFI. Complete Part XIX.
            <br />
            <input type="checkbox"
                    <#if w8[0].chapterFourStatus=="b16">
                        checked="true"
                    </#if>
            /> Exempt retirement plans. Complete Part XX.
            <br />
            <input type="checkbox"
                    <#if w8[0].chapterFourStatus=="b17">
                        checked="true"
                    </#if>
            /> Excepted nonfinancial group entity. Complete Part XXI.
            <br />
            <input type="checkbox"
                    <#if w8[0].chapterFourStatus=="b18">
                        checked="true"
                    </#if>
            /> Excepted nonfinancial start-up company. Complete Part XXII.
            <br />
            <input type="checkbox"
                    <#if w8[0].chapterFourStatus=="b19">
                        checked="true"
                    </#if>
            /> Excepted nonfinancial entity in liquidation or bankruptcy. Complete Part XXIII.
            <br />
            <input type="checkbox"
                    <#if w8[0].chapterFourStatus=="b20">
                        checked="true"
                    </#if>
            /> Publicly traded NFFE or NFFE affiliate of a publicly traded corporation. Complete Part XXIV.
            <br />
            <input type="checkbox" /> Excepted territory NFFE. Complete Part XXV.
            <br />
            <input type="checkbox"
                    <#if w8[0].chapterFourStatus=="b22">
                        checked="true"
                    </#if>
            /> Active NFFE. Complete Part XXVI.
            <br />
            <input type="checkbox"
                    <#if w8[0].chapterFourStatus=="b23">
                        checked="true"
                    </#if>
            /> Passive NFFE. Complete Part XXVII.
            <br />
            <input type="checkbox" /> Direct reporting NFFE.
            <br />
            <input type="checkbox" /> Sponsored direct reporting NFFE. Complete Part XXVIII.
        </div>
    </div>
    <div class="itemList">
        <div>6 Permanent residence address (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</div>
        <div class="borderB">
            ${(personalAddress.address)!"N/A"}
        </div>
        <div style="width: 65%;border-right: 1px solid #000" class="inlineBlock">
            <div>City or town, state or province. Include postal code where appropriate.</div>
            <div>
                ${( personalAddress.city + ","+personalAddress.rovince + ","+personalAddress.zipCode)!"N/A"}
            </div>
        </div>
        <div style="width: 34%;" class="inlineBlock">
            <div>Country</div>
            <div>
                ${(personalAddress.country.enName)!"N/A"}
            </div>
        </div>
    </div>
    <br />
    <br />
    <div class="itemList" style="border-top: 1px solid #000">
        <div>7 Mailing address (if different from above) </div>
        <div class="borderB">
            ${(mailAddress.address)!"N/A"}
        </div>
        <div style="width: 65%;border-right: 1px solid #000;" class="inlineBlock">
            <div>City or town, state or province. Include postal code where appropriate.</div>
            <div style="margin-left: 0px;">
                ${(mailAddress.city + ","+ mailAddress.rovince)!"N/A"}
            </div>
        </div>
        <div style="width: 34%;" class="inlineBlock">
            <div>Country</div>
            <div>
                ${(mailAddress.country.enName)!"N/A"}
            </div>
        </div>
    </div>
    <div class="itemList">
        <div class="inlineBlock">
            <div>8 U.S. taxpayer identification number (TIN), if required <span class="inlineBlock borderB" style="width: 200px;text-align: center;"></span></div>
            <div class="ninea">
                <input type="checkbox"
                        <#if w8[0].ein ?? && w8[0].ein =="c1">
                            checked="true"
                        </#if>
                /> QI-EIN &#160;
                <input type="checkbox" /> WP-EIN &#160;
                <input type="checkbox" /> WT-EIN &#160;
                <input type="checkbox"
                        <#if w8[0].ein ?? && w8[0].ein =="c4">
                            checked="true"
                        </#if>
                /> EIN
            </div>
        </div>
    </div>
    <div class="itemList">
        <div class="inlineBlock">
            <div>9a GIIN (if applicable)</div>
            <div>
                ${(w8[0].gginIf)!"N/A"}
            </div>
        </div>
    </div>
    <div class="itemList">
        <div>10 Reference number(s) (see instructions) </div>
        <div>
            ${(w8[0].referenceNumber)!"N/A"}
        </div>
    </div>
    <#--新增Part II-->
    <div class="Beneficial">
        <div style="width: 8%" class="PartI inlineBlock" >Part II </div>
        <div style="width: 80%" class="inlineBlock"><strong>Disregarded Entity or Branch Receiving </strong></div>
        <div>Payment.(Complete only if a disregarded entity with a GIIN or a branch of an FFI in a country other than the FFI’s country of residence.  </div>
        <div>Do not complete Part II for QDD branches. See instructions.)</div>
    </div>
    <div class="itemList">
        <div class="inlineBlock">
            <div>11  Chapter 4 Status (FATCA status) of disregarded entity or branch receiving payment.</div>
            <div style="width: 750px;">
                <div class="inlineBlock" style="width: 300px;">
                    <div><input type="checkbox"
                                <#if w8[0].ffi ?? && w8[0].ffi=="d1">
                                    checked="true"
                                </#if>
                        /> Branch treated as nonparticipating FFI.</div>
                    <div><input type="checkbox"
                                <#if w8[0].ffi ?? && w8[0].ffi=="d4">
                                    checked="true"
                                </#if>
                        /> Participating FFI.</div>
                </div>
                <div class="inlineBlock" style="width: 250px">
                    <div><input type="checkbox"
                                <#if w8[0].ffi ?? && w8[0].ffi=="d2">
                                    checked="true"
                                </#if>
                        /> Reporting Model 1 FFI.</div>
                    <div><input type="checkbox"
                                <#if w8[0].ffi ?? && w8[0].ffi=="d5">
                                    checked="true"
                                </#if>
                        /> Reporting Model 2 FFI.</div>
                </div>
                <div class="inlineBlock" style="width: 140px;vertical-align: top;">
                    <div><input type="checkbox"
                                <#if w8[0].ffi ?? && w8[0].ffi=="d3">
                                    checked="true"
                                </#if>
                        /> U.S. Branch.</div>
                </div>
            </div>
        </div>
    </div>
    <div class="itemList">
        <div>12 Address of branch (street, apt. or suite no., or rural route). <strong>Do not use a P.O. box or in-care-of address</strong> (other than a registered address). </div>
        <div>
            ${(w8[0].branchAddress)!"N/A"}
        </div>
    </div>
    <div class="itemList">
        <div style="width: 65%;border-right: 1px solid #000;" class="inlineBlock">
            <div>City or town, state or province. Include postal code where appropriate.</div>
            <div style="margin-left: 0px;">
                ${(w8[0].branchCity)!"N/A"}
            </div>
        </div>
        <div style="width: 34%;" class="inlineBlock">
            <div>Country</div>
            <div>
                ${(w8[0].branchCountry)!"N/A"}
            </div>
        </div>
    </div>
    <div class="itemList">
        <div>13 GIIN (if any) <span class="inlineBlock" style="width: 600px">
                  ${(w8[0].giinXxx)!"N/A"}
            </span></div>
    </div>
    <br />
    <br />
    <br />
    <div class="Beneficial">
        <div style="width: 8%" class="PartI inlineBlock" >Part VIII </div>
        <div style="width: 80%" class="inlineBlock"><strong>Nonwithholding Foreign Partnership, Simple Trust, or Grantor Trust </strong></div>
    </div>
    <div><strong>Check all that apply. </strong></div>
    <div>
        21a  <input type="checkbox"
                <#if w8[0].nonwithholdingA=="e1" || w8[0].nonwithholdingA=="e1,e2" >
                    checked="true"
                </#if>
        />  I certify that the entity identified in Part I:
    </div>
    <ul>
        <li>Is a nonwithholding foreign partnership, a nonwithholding foreign simple trust, or a nonwithholding foreign grantor trust and is providing this form for payments that are not effectively connected, or are not treated as effectively
            connected, with the conduct of a trade or business in the United States; and </li>
        <li>Is using this form to transmit withholding certificates and/or other documentation and has provided or will provide a withholding statement, as required for
            purposes of chapters 3 and 4, that is subject to the certifications made on this form.</li>
    </ul>
    <div style="margin-left: 11px;">
        b  <input type="checkbox"
                <#if w8[0].nonwithholdingA=="e2" || w8[0].nonwithholdingA=="e1,e2" >
                    checked="true"
                </#if>
        /> I certify that the entity identified in Part I is a foreign partnership that is a partner in a lower-tier partnership and is providing
        this Form W-8IMY for purposes of section 1446.
    </div>

    <#if w8[0].chapterFourStatus=="b11">
        <div class="Beneficial">
            <div style="width: 8%" class="PartI inlineBlock" >Part XV</div>
            <div style="width: 80%" class="inlineBlock"><strong>Certified Deemed-Compliant Limited Life Debt Investment Entity</strong></div>
        </div>
        <div>
            28  <input type="checkbox"
                    <#list w8Part.PartXV.fuxuan1 as v>
                        <#if v?index_of("f1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that the FFI identified in Part I:
            <ul>
                <li>Was in existence as of January 17, 2013;</li>
                <li>Issued all classes of its debt or equity interests to investors on or before January 17, 2013, pursuant to a trust indenture or similar agreement; and </li>
                <li>Is certified deemed-compliant because it satisfies the requirements to be treated as a limited life debt investment entity (such as the restrictions with respect to its assets and other requirements under
                    Regulations section 1.1471-5(f)(2)(iv)). </li>
            </ul>
        </div>
    </#if>

    <#if w8[0].chapterFourStatus=="b16">
        <div class="Beneficial">
            <div style="width: 8%" class="PartI inlineBlock" >Part XX</div>
            <div style="width: 80%" class="inlineBlock"><strong>Exempt Retirement Plans</strong></div>
        </div>
        <div><strong>Check box 33a, b, c, d, e, or f, whichever applies.</strong></div>
        <div>
            33a  <input type="checkbox"
                    <#list w8Part.PartXIX.fuxuan1 as v>
                        <#if v?index_of("j1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that the FFI identified in Part I:
            <ul>
                <li>Is established in a country with which the United States has an income tax treaty in force;</li>
                <li>Is operated principally to administer or provide pension or retirement benefits; and</li>
                <li>Is entitled to treaty benefits on income that the fund derives from U.S. sources (or would be entitled to benefits if it derived any such income) as a resident of the other country
                    which satisfies any applicable limitation on benefits requirement. </li>
            </ul>
            <div style="margin-left: 9px;">b  <input type="checkbox"
                        <#list w8Part.PartXIX.fuxuan1 as v>
                            <#if v?index_of("j2")!=-1>
                                checked="true"
                                <#break >
                            </#if>
                        </#list>
                /> I certify that the FFI identified in Part I:</div>
            <ul>
                <li>Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to beneficiaries that are former employees of
                    one or more employers in consideration for services rendered;</li>
                <li>No single beneficiary has a right to more than 5% of the FFI’s assets;</li>
                <li>Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the country in
                    which the fund is established or operated; and
                    <div>
                        (i) Is generally exempt from tax on investment income under the laws of the country in which it is established or operates due to its status as a retirement or pension plan;
                    </div>
                    <div>
                        (ii)Receives at least 50% of its total contributions from sponsoring employers (disregarding transfers of assets from other plans described in this part,
                        retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, other retirement funds described in an applicable Model 1
                        or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A));
                    </div>
                    <div>
                        (iii)Either does not permit or penalizes distributions or withdrawals made before the occurrence of specified events related to retirement, disability,
                        or death (except rollover distributions to accounts described in Regulations section 1.1471-5(b)(2)(i)(A) (referring to retirement and pension accounts),
                        to retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, or to other retirement funds described in this part or in an
                        applicable Model 1 or Model 2 IGA); or
                    </div>
                    <div>(iv) Limits contributions by employees to the fund by reference to earned income of the employee or may not exceed $50,000 annually.</div>
                </li>
            </ul>
            <div style="margin-left: 9px;">c  <input type="checkbox"
                        <#list w8Part.PartXIX.fuxuan1 as v>
                            <#if v?index_of("j3")!=-1>
                                checked="true"
                                <#break >
                            </#if>
                        </#list>
                /> I certify that the FFI identified in Part I:</div>
            <ul>
                <li>Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to beneficiaries that are former employees of
                    one or more employers in consideration for services rendered;</li>
                <li>Has fewer than 50 participants;</li>
                <li>Is sponsored by one or more employers, each of which is not an investment entity or passive NFFE;</li>
                <li>Employee and employer contributions to the fund (disregarding transfers of assets from other plans described in this part, retirement and pension accounts
                    described in an applicable Model 1 or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A)) are limited by reference to earned
                    income and compensation of the employee, respectively;</li>
                <li>Participants that are not residents of the country in which the fund is established or operated are not entitled to more than 20% of the fund’s assets; and</li>
                <li>Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the
                    country in which the fund is established or operates.</li>
            </ul>
            <div style="margin-left: 9px;">d  <input type="checkbox"
                        <#list w8Part.PartXIX.fuxuan1 as v>
                            <#if v?index_of("j4")!=-1>
                                checked="true"
                                <#break >
                            </#if>
                        </#list>
                /> I certify that the entity identified in Part I is formed pursuant to a pension plan that would meet the requirements of section 401(a),
                other than the requirement that the plan be funded by a trust created or organized in the United States.</div>
        </div>
        <div>
            <div style="margin-left: 9px;">
                e  <input type="checkbox"
                        <#list w8Part.PartXIX.fuxuan1 as v>
                            <#if v?index_of("j5")!=-1>
                                checked="true"
                                <#break >
                            </#if>
                        </#list>
                /> I certify that the entity identified in Part I is established exclusively to earn income for the benefit of one or more retirement
                funds described in this part or in an applicable Model 1 or Model 2 IGA, accounts described in Regulations section 1.1471-5(b)(2)(i)(A) (referring to retirement
                and pension accounts), or retirement and pension accounts described in an applicable Model 1 or Model 2 IGA.
            </div>
        </div>
        <div>
            <div style="margin-left: 9px;">f  <input type="checkbox"
                        <#list w8Part.PartXIX.fuxuan1 as v>
                            <#if v?index_of("j6")!=-1>
                                checked="true"
                                <#break >
                            </#if>
                        </#list>
                /> I certify that the entity identified in Part I:</div>
            <ul>
                <li>Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession (each
                    as defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model 1 or Model 2 IGA to provide retirement,
                    disability, or death benefits to beneficiaries or participants that are current or former employees of the sponsor (or persons designated by such employees);
                    or</li>
                <li>
                    Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession (each as
                    defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model 1 or Model 2 IGA to provide retirement,
                    disability, or death benefits to beneficiaries or participants that are not current or former employees of such sponsor, but are in consideration of
                    personal services performed for the sponsor.
                </li>
            </ul>
        </div>
    </#if>

    <#if w8[0].chapterFourStatus=="b17">
        <div class="Beneficial">
            <div style="width: 8%" class="PartI inlineBlock" >Part XXI</div>
            <div style="width: 80%" class="inlineBlock"><strong>Excepted Nonfinancial Group Entity</strong></div>
        </div>
        <div>
            34  <input type="checkbox"
                    <#list w8Part.PartXX.fuxuan1 as v>
                        <#if v?index_of("k1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that the entity identified in Part I:
        </div>
        <ul>
            <li>Is a holding company, treasury center, or captive finance company and substantially all of the entity’s activities are functions described in
                Regulations section 1.1471-5(e)(5)(i)(C) through (E);</li>
            <li>Is a member of a nonfinancial group described in Regulations section 1.1471-5(e)(5)(i)(B);</li>
            <li>Is not a depository or custodial institution (other than for members of the entity’s expanded affiliated group); and</li>
            <li>Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment
                vehicle with an investment strategy to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.</li>
        </ul>
    </#if>

    <#if w8[0].chapterFourStatus=="b18">
        <div class="Beneficial">
            <div style="width: 8%" class="PartI inlineBlock" >Part XXII</div>
            <div style="width: 80%" class="inlineBlock"><strong>Excepted Nonfinancial Start-Up Company</strong></div>
        </div>
        <div>
            35  <input type="checkbox"
                    <#list w8Part.PartXXI.fuxuan1 as v>
                        <#if v?index_of("l1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that the entity identified in Part I:
        </div>
        <ul>
            <li>Was formed on (or in the case of a new line of business, the date of board resolution approving the new line of business)
                <span style="width: 200px;text-align: center;" class="borderB inlineBlock">
                ${(w8Part.PartXXI.input1)!"N/A"}
            </span> (date must be less than 24 months prior to date of payment);</li>
            <li>Is not yet operating a business and has no prior operating history or is investing capital in assets with the intent to operate a new line of business
                other than that of a financial institution or passive NFFE; and</li>
            <li>Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment
                vehicle whose purpose is to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes. </li>
        </ul>
    </#if>

    <#if w8[0].chapterFourStatus=="b19">
        <div class="Beneficial">
            <div style="width: 8%" class="PartI inlineBlock" >Part XXIII </div>
            <div style="width: 80%" class="inlineBlock"><strong>Excepted Nonfinancial Entity in Liquidation or Bankruptcy</strong></div>
        </div>
        <div>
            36  <input type="checkbox"
                    <#list w8Part.PartXXII.fuxuan1 as v>
                        <#if v?index_of("m1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that the entity identified in Part I:
        </div>
        <ul>
            <li>Filed a plan of liquidation, filed a plan for reorganization, or filed for bankruptcy on the following date:
                <span style="width: 150px;text-align: center;" class="inlineBlock borderB">
                ${(w8Part.PartXXII.input1)!"N/A"}
            </span>;</li>
            <li>Has not been engaged during the past 5 years in business as a financial institution or acted as a passive NFFE;</li>
            <li>Is either liquidating or emerging from a reorganization or bankruptcy with the intent to continue or recommence operations as a nonfinancial entity; and</li>
            <li>Has provided, or will provide, documentary evidence such as a bankruptcy filing or other public documentation that supports its claim if it remains in
                bankruptcy or liquidation for more than 3 years.</li>
        </ul>
    </#if>

    <#if w8[0].chapterFourStatus=="b20">
        <div class="Beneficial">
            <div style="width: 8%" class="PartI inlineBlock" >Part XXIV </div>
            <div style="width: 80%" class="inlineBlock"><strong>Publicly Traded NFFE or NFFE Affiliate of a Publicly Traded Corporation</strong></div>
        </div>
        <div><strong>Check box 37a or 37b, whichever applies.</strong></div>
        <div>
            37a  <input type="checkbox"
                    <#list w8Part.PartXXIII.fuxuan1 as v>
                        <#if v?index_of("n1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that:
        </div>
        <ul>
            <li> The entity identified in Part I is a foreign corporation that is not a financial institution; and </li>
            <li>The stock of such corporation is regularly traded on one or more established securities markets, including
                <br />
                <span style="width: 650px;text-align: center;" class="inlineBlock borderB">
                    ${(w8Part.PartXXIII.input1)!"N/A"}
            </span>.</li>
        </ul>
        <div style="margin-left: 9px;">
            b  <input type="checkbox"
                    <#list w8Part.PartXXIII.fuxuan1 as v>
                        <#if v?index_of("n1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that:
        </div>
        <ul>
            <li>The entity identified in Part I is a foreign corporation that is not a financial institution; </li>
            <li>The entity identified in Part I is a member of the same expanded affiliated group as an entity the stock of which is regularly traded on an established
                securities market;</li>
            <li>The name of the entity, the stock of which is regularly traded on an established securities market, is
                <br />
                <span style="width: 600px;text-align: center;" class="inlineBlock borderB">
                    ${(w8Part.PartXXIII.input2)!"N/A"}
            </span>;and </li>
            <li>The name of the securities market on which the stock is regularly traded is ▶<span style="width: 250px;text-align: center;" class="inlineBlock borderB">
                    ${(w8Part.PartXXIII.input3)!"N/A"}
            </span>.</li>
        </ul>
    </#if>

    <#if w8[0].chapterFourStatus=="b22">
        <div class="Beneficial">
            <div style="width: 8%" class="PartI inlineBlock" >Part XXVI </div>
            <div style="width: 80%" class="inlineBlock"><strong>Active NFFE</strong></div>
        </div>
        <div>
            39  <input type="checkbox"
                    <#list w8Part.PartXXV.fuxuan1 as v>
                        <#if v?index_of("o1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that:
        </div>
        <ul>
            <li>The entity identified in Part I is a foreign entity that is not a financial institution;</li>
            <li>Less than 50% of such entity’s gross income for the preceding calendar year is passive income; and</li>
            <li>Less than 50% of the assets held by such entity are assets that produce or are held for the production of passive income (calculated as a weighted average of the
                percentage of passive assets measured quarterly). See the instructions for the definition of passive income. </li>
        </ul>
    </#if>

    <#if w8[0].chapterFourStatus=="b23">
        <div class="Beneficial">
            <div style="width: 8%" class="PartI inlineBlock" >Part XXVII </div>
            <div style="width: 80%" class="inlineBlock"><strong>Passive NFFE</strong></div>
        </div>
        <div>
            40  <input type="checkbox"
                    <#list w8Part.PartXXVI.fuxuan1 as v>
                        <#if v?index_of("p1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that the entity identified in Part I:
        </div>

        <ul>
            <li> Is a foreign entity that is not a financial institution (this category includes an entity organized in a possession of the United States that engages
                (or holds itself out as being engaged) primarily in the business of investing, reinvesting, or trading in securities, partnership interests, commodities,
                notional principal contracts, insurance or annuity contracts, or any interest in such security, partnership interest, commodity, notional principal contract,
                insurance contract, or annuity contract); and</li>
            <li>Is using this form to transmit withholding certificates and/or other documentation and has provided or will provide a withholding statement, as required. </li>
        </ul>
    </#if>
    <div><strong>I agree that I will submit a new form within 30 days if any certification on this form becomes incorrect.</strong></div>
    <br />
    <br />
    <br />
    <div class="clearfix">
        <div style="float: left;font-size: 14px;font-weight: bolder;width: 70px;">Sign Here </div>
        <div style="float: right">
            <div>
                <div style="display: inline-block">
                    <div style="width: 360px;height: 20px;border-bottom: 1px solid #000;"></div>
                    <div style="width: 360px;text-align: center;font-size: 9px">Signature of authorized official</div>
                </div>
                <div style="display: inline-block">
                    <div style="width: 100px;height: 20px;border-bottom: 1px solid #000;"></div>
                    <div style="width: 100px;font-size: 9px;text-align: center;">Print Name</div>
                </div>
                <div style="display: inline-block">
                    <div style="width: 150px;height: 20px;border-bottom: 1px solid #000;"></div>
                    <div style="width: 150px;text-align: center;font-size: 9px">Date (MM-DD-YYYY)  </div>
                </div>
            </div>
        </div>

    </div>
    <br />
    <br />
</div>
</body>
</html>
