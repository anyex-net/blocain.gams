
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
            margin: 60pt 1cm 60pt 1cm;
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
            height: 18px;
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
                <div>Cat. No. 25047Z &#160;  W-8BEN-E  (Rev. 7-2017)</div>
<!--                <div>W-8BEN-E  (Rev. 7-2017)</div>-->
                <div>Page <span id="pages"> of </span></div>
<!--                <div>第 <span id="pages">页，共 </span>页</div>-->
            </span>
        </div>
    </div>
    <div class="page1">
        <div style="height: 109px" class="borderB">
            <div style="width: 20%;border-right: 1px solid #000;height: inherit; vertical-align: middle;" class="inlineBlock">
                <div>Form <h3 style="font-size: 16px; margin: 5px 0px">W-8BEN-E</h3></div>
                <p style="margin: 0px">(Rev. July 2017)</p>
                <div>Department of the Treasury  Internal Revenue Service</div>
            </div>
            <div style="width: 60%;border-right: 1px solid #000;height: inherit;vertical-align: middle; text-align: center;" class="inlineBlock">
                <h3 style="font-size: 14px;margin: 0px">Certificate of Status of Beneficial Owner for  United States Tax Withholding and Reporting (Entities)</h3>
                <div ><strong>▶ For use by entities. Individuals must use Form W-8BEN.</strong></div>
                <div ><strong>▶ Section references are to the Internal Revenue Code.</strong></div>
                <div > <strong>▶ Go to www.irs.gov/FormW8BENE for instructions and the latest information. </strong> </div>
                <div><strong>▶ Give this form to the withholding agent or payer. Do not send to the IRS.</strong></div>
            </div>
            <div style="width: 14%;height: inherit;line-height: 109px; vertical-align: middle;font-size: 10px;text-align: center;" class="inlineBlock">
                OMB No. 1545-1621
            </div>
        </div>
        <div class="clearfix;">
            <div class="clearfix;" style="margin-top: 5px;height: 16px;">
                <div style="float: left;width: 200px">Do NOT use this form for: </div>
                <div style="float: right;width: 140px;text-align: right;">Instead use Form: </div>
            </div>
            <ul class="specialUl">
                <li class="clearfix">
                    <div style="float: left">U.S. entity or U.S. citizen or resident . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                        . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .</div>
                    <div style="float: right;text-align: right;width: 25px"> W-9  </div>
                </li>
                <li class="clearfix">
                    <div style="float: left;width: 510px"> A foreign individual  . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . </div>
                    <div style="float: right;width: 180px;text-align: right;">  W-8BEN (Individual) or Form 8233  </div>
                </li>
                <li class="clearfix">
                    <div style="width: 800px;">A foreign individual or entity claiming that income is effectively connected with the conduct of trade or business within the U.S. </div>
                    <div style="float: left;">(unless claiming treaty benefits). . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                        . . . . . . . . . . . . . . . . . . . . . . . . . . . .</div>
                    <div style="text-align: right;">W-8ECI </div>
                </li>
                <li class="clearfix">
                    <div style="width: 645px;"> A foreign partnership, a foreign simple trust, or a foreign grantor trust (unless claiming treaty benefits) </div>
                    <div style="">(see instructions for exceptions). . . . . . . . . . . . . . . .  . . . . . . . . . . . . . . . . . . .  . . . . . . . . . . . . . . . . . . .
                        . . . . . . . . . . . . . . . . . . . . . . . . . . . .. . . . . . . W-8IMY</div>
                </li>
                <li class="clearfix">
                    <div style="width: 620px;"> A foreign government, international organization, foreign central bank of issue, foreign tax-exempt organization, </div>
                    <div>foreign private foundation, or,government of a U.S. possession claiming that income is effectively connected U.S. income or that is claiming</div>
                    <div> the applicability of section(s) 115(2), 501(c), 892, 895, or 1443(b) (unless claiming treaty benefits) (see instructions for other exceptions) </div>
                    <div style="text-align: right">. . . . . . . . . W-8ECI or W-8EXP</div>
                </li>
                <li class="clearfix">
                    <div style="float: left">Any person acting as an intermediary (including a qualified intermediary acting as a qualified derivatives dealer) . . . . . . . . . . . . . . . .</div>
                    <div style="float: right;width: 55px;text-align: right;">  W-8IMY  </div>
                </li>

            </ul>
        </div>
        <div class="Beneficial">
            <div  class="PartI inlineBlock">Part I</div>
            <div style="width: 400px;" class="inlineBlock"><strong>Identification of Beneficial Owner</strong> (see instructions) </div>
        </div>
        <div class="itemList">
            <div style="width: 65%;border-right: 1px solid #000" class="inlineBlock">
                <div>1 Name of organization that is the beneficial owner</div>
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
            <div>3 Name of disregarded entity receiving the payment (if applicable, see instructions) </div>
            <div>
                ${(w8[0].disregardedEntityName)!"N/A"}
            </div>
        </div>
        <!--第四条-->
        <div class="inputCheckbox itemList">
            <div>
                <div style="width: 40%">4 Chapter 3 Status (entity type) (Must check one box only):</div>
                <div class="inputCheckbox3">
                    <#if w8[0].chapterThreeStatus=="a1">
                        <input type="checkbox"
                               checked="true"
                        />
                    </#if>
                    Corporation
                </div>
                <div class="inputCheckbox4">
                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a2">
                               checked="true"
                                </#if>
                        />
                    Disregarded entity
                </div>
                <div class="inputCheckbox5">
                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a3">
                               checked="true"
                                </#if>
                        />
                    Partnership</div>
            </div>
            <div>
                <div class="inputCheckbox1">
                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a4">
                               checked="true"
                                </#if>
                        />
                    Simple trust
                </div>
                <div class="inputCheckbox2">
                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a5">
                               checked="true"
                                </#if>
                        />
                    Grantor trust
                </div>
                <div class="inputCheckbox3">

                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a6">
                               checked="true"
                                </#if>
                        />

                    Complex trust
                </div>
                <div class="inputCheckbox4">

                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a7">
                               checked="true"
                                </#if>
                        />

                    Estate
                </div>
                <div class="inputCheckbox5">

                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a8">
                               checked="true"
                                </#if>
                        />

                    Government
                </div>
            </div>
            <div>
                <div class="inputCheckbox1">

                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a9">
                               checked="true"
                                </#if>
                        />

                    Central Bank of Issue
                </div>
                <div class="inputCheckbox2">

                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a10">
                               checked="true"
                                </#if>
                        />

                    Tax-exempt organization
                </div>
                <div class="inputCheckbox3">

                        <input type="checkbox"
                                <#if w8[0].chapterThreeStatus=="a11">
                               checked="true"
                                </#if>
                        />

                    Private foundation
                </div>
                <div class="inputCheckbox4">
<#--                    <#if w8[0].chapterThreeStatus=="a1">-->
                        <input type="checkbox"
<#--                               checked="true"-->
                        />
<#--                    </#if>-->
                    International organization
                </div>

            </div>
            <div>If you entered disregarded entity, partnership, simple trust, or grantor trust above, is the entity a hybrid making a treaty</div>
            <div class="clearfix">
                <div style="float: left">claim? If "Yes" complete Part III.</div>
                <div style="float: right">
                    <span>

                            <input type="checkbox"
                                    <#if  w8[0].whether ?? && w8[0].whether=="1">
                                        checked="true"
                                    </#if>
                            />

                        Yes
                    </span>
                    <span>

                            <input type="checkbox"
                                    <#if  w8[0].whether ?? && w8[0].whether=="0">
                                       checked="true"
                                    </#if>
                            />

                        No
                    </span>
                </div>
            </div>
        </div>
        <!--第五条-->
        <div class="itemList clearfix">
            <div>5 Chapter 4 Status (FATCA status) (See instructions for details and complete the  certification below for the entity's applicable status.)</div>
            <div style="float: left;width: 46%; padding-right: 10px;">

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />

                Nonparticipating FFI (including an FFI related to a Reporting IGA FFI other than a deemed-compliant FFI, participating FFI, or exempt beneficial owner).
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />

                Participating FFI.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                    Reporting Model 1 FFI.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                Reporting Model 2 FFI.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
               Registered deemed-compliant FFI (other than a reporting Model 1 FFI, sponsored FFI, or nonreporting IGA FFI covered in Part XII). See instructions.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 Sponsored FFI. Complete Part IV.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                Certified deemed-compliant nonregistering local bank. Complete Part V.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                Certified deemed-compliant FFI with only low-value accounts. Complete Part VI.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                Certified deemed-compliant sponsored, closely held investment vehicle. Complete Part VII.
                <br />

                    <input type="checkbox"
                            <#if w8[0].chapterFourStatus=="b10">
                           checked="true"
                            </#if>
                    />
                 Certified deemed-compliant limited life debt investment entity. Complete Part VIII.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 Certain investment entities that do not maintain financial accounts. Complete Part IX.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                Owner-documented FFI. Complete Part X.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 Restricted distributor. Complete Part XI.
            </div>
            <div style="float: right;width: 50%">

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b1">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                Nonreporting IGA FFI. Complete Part XII.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b15">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 Foreign government, government of a U.S. possession, or foreign central bank of issue. Complete Part XIII.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b16">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 International organization. Complete Part XIV.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b17">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 Exempt retirement plans. Complete Part XV.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b18">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                Entity wholly owned by exempt beneficial owners. Complete Part XVI.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b19">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
               Territory financial institution. Complete Part XVII.
                <br />

                    <input type="checkbox"
                            <#if w8[0].chapterFourStatus=="b15">
                           checked="true"
                            </#if>
                    />
                Excepted nonfinancial group entity. Complete Part XVIII.
                <br />

                    <input type="checkbox"
                            <#if w8[0].chapterFourStatus=="b16">
                           checked="true"
                            </#if>
                    />
                 Excepted nonfinancial start-up company. Complete Part XIX.
                <br />

                    <input type="checkbox"
                            <#if w8[0].chapterFourStatus=="b17">
                           checked="true"
                            </#if>
                    />
               Excepted nonfinancial entity in liquidation or bankruptcy. Complete Part XX.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b23">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
               501(c) organization. Complete Part XXI.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b24">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 Nonprofit organization. Complete Part XXII.
                <br />

                    <input type="checkbox"
                            <#if w8[0].chapterFourStatus=="b18">
                           checked="true"
                            </#if>
                    />
                 Publicly traded NFFE or NFFE affiliate of a publicly traded corporation. Complete Part XXIII.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b26">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 Excepted territory NFFE. Complete Part XXIV.
                <br />

                    <input type="checkbox"
                            <#if w8[0].chapterFourStatus=="b20">
                           checked="true"
                            </#if>
                    />
                 Active NFFE. Complete Part XXV.
                <br />

                    <input type="checkbox"
                            <#if w8[0].chapterFourStatus=="b21">
                           checked="true"
                            </#if>
                    />
                 Passive NFFE. Complete Part XXVI.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                Excepted inter-affiliate FFI. Complete Part XXVII.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 Direct reporting NFFE.
                <br />

                    <input type="checkbox"
                            <#if w8[0].chapterFourStatus=="b22">
                           checked="true"
                            </#if>
                    />
                 Sponsored direct reporting NFFE. Complete Part XXVIII.
                <br />

                    <input type="checkbox"
<#--                            <#if w8[0].chapterFourStatus=="b">-->
<#--                           checked="true"-->
<#--                            </#if>-->
                    />
                 Account that is not a financial account.
                <br />
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
        <div class="itemList">
            <div>7 Mailing address (if different from above) </div>
            <div class="borderB">
                ${(mailAddress.address)!"N/A"}
            </div>
            <div style="width: 65%;border-right: 1px solid #000;" class="inlineBlock">
                <div>City or town, state or province. Include postal code where appropriate.</div>
                <div class="borderB" style="margin-left: 0px;">
                    ${(mailAddress.city + ","+ mailAddress.rovince)!"N/A"}
                </div>
            </div>
            <div style="width: 34%;" class="inlineBlock">
                <div>Country</div>
                <div class="borderB">
                    ${(mailAddress.country.enName)!"N/A"}
                </div>
            </div>
        </div>
        <div class="itemList">
            <div style="width: 36%;border-right: 1px solid #000" class="inlineBlock">
                <div>8 U.S. taxpayer identification number (TIN), if required</div>
                <div>
                    ${(w8[0].usaTin)!"N/A"}
                </div>
            </div>
            <div style="width: 28.5%;border-right: 1px solid #000" class="inlineBlock">
                <div>9a GIIN</div>
                <div>
                    ${(w8[0].giinXxx)!"N/A"}
                </div>
            </div>
            <div style="width: 34%;" class="inlineBlock">
                <div>b Foreign TIN </div>
                <div>
                    ${(w8[0].foreignTin)!"N/A"}
                </div>
            </div>
        </div>
        <div class="itemList">
            <div>10 Reference number(s) (see instructions) </div>
            <div>
                ${(w8[0].referenceNumber)!"N/A"}
            </div>
<!--            <div style="width: 65%;border-right: 1px solid #000" class="inlineBlock">-->
<!--                <div>City or town, state or province. Include postal code where appropriate.</div>-->
<!--                <div>-->
<!--                    ${(perInfo.lastName)!"N/A"}-->
<!--                    ${(perInfo.firstName)!"N/A"}-->
<!--                </div>-->
<!--            </div>-->
<!--            <div style="width: 34%;" class="inlineBlock">-->
<!--                <div>Country</div>-->
<!--                <div>-->
<!--                    ${(identity.issuingCountry.enName)!"N/A"}-->
<!--                </div>-->
<!--            </div>-->
        </div>
        <div>Note: Please complete remainder of the form including signing the form in Part XXX. </div>

<!--        <div class="Beneficial">-->
<!--            <div style="width: 8%" class="PartI inlineBlock" >Part II </div>-->
<!--            <div style="width: 80%" class="inlineBlock"><strong>Disregarded Entity or Branch Receiving Payment.</strong>-->
<!--                (Complete only if a disregarded entity with a GIIN or a </div>-->
<!--        </div>-->
<!--        <div>branch of an FFI in a country other than the FFI's country of residence. See instructions.)</div>-->
<!--        <div>11.Chapter 4 Status (FATCA status) of disregarded entity or branch receiving payment</div>-->
<!--        <div>-->
<!--            <div style="width: 35%;" class="inlineBlock"><input type="checkbox" /> Branch treated as nonparticipating FFI.</div>-->
<!--            <div style="width: 30%;" class="inlineBlock"><input type="checkbox" /> Reporting Model 1 FFI.</div>-->
<!--            <div style="width: 30%;" class="inlineBlock"><input type="checkbox" /> U.S. Branch.</div>-->
<!--        </div>-->
<!--        <div>-->
<!--            <div style="width: 35%;" class="inlineBlock"><input type="checkbox" /> Participating FFI.</div>-->
<!--            <div style="width: 30%;" class="inlineBlock"><input type="checkbox" /> Reporting Model 2 FFI.</div>-->
<!--        </div>-->
<!--        <div>12.Address of disregarded entity or branch (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address). </div>-->
<!--        <div style="height: 18px;line-height: 18px;" class="borderB">23125313</div>-->
<!--        <div>City or town, state or province. Include postal code where appropriate.</div>-->
<!--        <div style="height: 18px;line-height: 18px;" class="borderB">23125313</div>-->
<!--        <div>Country</div>-->
<!--        <div style="height: 18px;line-height: 18px;" class="borderB">23125313</div>-->
<!--        <div>13.GIIN (if any)<span style="height: 16px;width: 800px;line-height: 16px;" class="inlineBlock">23125313</span></div>-->
        <div class="Beneficial">
            <span style="width: 8%" class="PartI inlineBlock">Part III </span>
            <span style="width: 80%;" class="inlineBlock"><strong>Disregarded Entity or Branch Receiving Payment.</strong>
                (if applicable). (For chapter 3 purposes only.) </span>
        </div>
        <div>14 I certify that (check all that apply):</div>
        <div>
            a  <input type="checkbox" /> The beneficial owner is a resident of <span style="width: 400px;text-align: center;" class="borderB"></span>
            within the meaning of the income tax   treaty between the United States and that country.
        </div>
        <div>
            b  <input type="checkbox" /> The beneficial owner derives the item (or items) of income for which the treaty benefits are claimed, and, if applicable,
            meets the requirements of the treaty provision dealing with limitation on benefits. The following are types of
            limitation on benefits provisions that may be included in an applicable tax treaty (check only one; see instructions):
        </div>
        <div style="margin-left: 9px">
            <div style="width: 38%;" class="inlineBlock"><input type="checkbox" /> Government</div>
            <div style="width: 60%;" class="inlineBlock"><input type="checkbox" />
                Company that meets the ownership and base erosion test</div>
        </div>
        <div style="margin-left: 9px">
            <div style="width: 38%;" class="inlineBlock"><input type="checkbox" /> Tax exempt pension trust or pension fund</div>
            <div style="width: 60%;" class="inlineBlock"><input type="checkbox" /> Company that meets the derivative benefits test</div>
        </div>
        <div style="margin-left: 9px">
            <div style="width: 38%;" class="inlineBlock"><input type="checkbox" /> Other tax exempt organization</div>
            <div style="width: 60%;" class="inlineBlock"><input type="checkbox" /> Company with an item of income that meets active trade or business test</div>
        </div>
        <div style="margin-left: 9px">
            <div style="width: 38%;" class="inlineBlock"><input type="checkbox" /> Publicly traded corporation</div>
            <div style="width: 60%;" class="inlineBlock"><input type="checkbox" /> Favorable discretionary determination by the U.S. competent authority received</div>
        </div>
        <div style="margin-left: 9px">
            <div style="width: 38%;" class="inlineBlock"><input type="checkbox" /> Subsidiary of a publicly traded corporation</div>
            <div style="width: 60%;" class="inlineBlock"><input type="checkbox" /> Other (specify Article and paragraph):
                <span style="width: 200px;text-align: center;" class="borderB inlineBlock"></span></div>
        </div>
        <div>
            c  <input type="checkbox" /> The beneficial owner is claiming treaty benefits for U.S. source dividends received from a foreign corporation or interest from a U.S. trade
            or business of a foreign corporation and meets qualified resident status (see instructions).
        </div>

        <div>15.<strong>Special rates and conditions </strong> (if applicable—see instructions): </div>
        <div>
            The beneficial owner is claiming the provisions of Article and paragraph
            <span style="width: 350px;text-align: center;" class="borderB inlineBlock"></span>
        </div>
        <div>
            of the treaty identified on line 14a above to claim a
            <span style="width: 75px;text-align: center;" class="borderB inlineBlock"></span>
            % rate of withholding on (specify type of income):
            <span style="width: 126px;text-align: center;" class="borderB inlineBlock"></span>
        </div>
        <div>
            Explain the additional conditions in the Article the beneficial owner meets to be eligible for the rate of withholding:
        </div>
        <div style="width: 700px;text-align: center;" class=" borderB inlineBlock"></div>
        <br />
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part IV</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Sponsored FFI </strong></span>-->
<!--        </div>-->
<!--        <div>-->
<!--            16.Name of sponsoring entity:-->
<!--            <span style="width: 550px;" class="borderB inlineBlock">454</span>-->
<!--        </div>-->
<!--        <div>17.Check whichever box applies.</div>-->
<!--        <div><input type="checkbox" /> I certify that the entity identified in Part I: </div>-->
<!--        <ul>-->
<!--            <li>Is an investment entity;</li>-->
<!--            <li>Is not a QI, WP (except to the extent permitted in the withholding foreign partnership agreement), or WT; and </li>-->
<!--            <li>Has agreed with the entity identified above (that is not a nonparticipating FFI) to act as the sponsoring entity for this entity. </li>-->
<!--        </ul>-->
<!--        <div><input type="checkbox" /> I certify that the entity identified in Part I: </div>-->
<!--        <ul>-->
<!--            <li>Is a controlled foreign corporation as defined in section 957(a);</li>-->
<!--            <li>Is not a QI, WP, or WT; </li>-->
<!--            <li>Is wholly owned, directly or indirectly, by the U.S. financial institution identified above that agrees to act as the sponsoring entity for this entity; and </li>-->
<!--            <li>-->
<!--                Shares a common electronic account system with the sponsoring entity (identified above) that enables the sponsoring entity to identify all-->
<!--                account holders and payees of the entity and to access all account and customer information maintained by the entity including, but not limited to,-->
<!--                customer identification information, customer documentation, account balance, and all payments made to account holders or payees.-->
<!--            </li>-->
<!--        </ul>-->
<!--        <div class="Beneficial">-->
<!--            <div style="width: 8%" class="PartI inlineBlock">Part V</div>-->
<!--            <div style="width: 80%;" class="inlineBlock"><strong>Certified Deemed-Compliant Nonregistering Local Bank</strong></div>-->
<!--        </div>-->
<!--        <div>-->
<!--            18 <input type="checkbox" /> I certify that the FFI identified in Part I:-->
<!--            <ul>-->
<!--                <li>Operates and is licensed solely as a bank or credit union (or similar cooperative credit organization operated without profit) in its country of incorporation or organization; </li>-->
<!--                <li> Engages primarily in the business of receiving deposits from and making loans to, with respect to a bank, retail customers unrelated to such bank and, with respect to a-->
<!--                    credit union or similar cooperative credit organization, members, provided that no member has a greater than 5% interest in such credit union or cooperative credit organization;</li>-->
<!--                <li>Does not solicit account holders outside its country of organization;</li>-->
<!--                <li>Has no fixed place of business outside such country (for this purpose, a fixed place of business does not include a location that is not advertised to the public and from-->
<!--                    which the FFI performs solely administrative support functions);</li>-->
<!--                <li>Has no more than $175 million in assets on its balance sheet and, if it is a member of an expanded affiliated group, the group has no more than $500 million in total-->
<!--                    assets on its consolidated or combined balance sheets; and </li>-->
<!--                <li>Does not have any member of its expanded affiliated group that is a foreign financial institution, other than a foreign financial institution that is incorporated or-->
<!--                    organized in the same country as the FFI identified in Part I and that meets the requirements set forth in this part. </li>-->
<!--            </ul>-->
<!--        </div>-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part VI</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong> Certified Deemed-Compliant FFI with Only Low-Value Accounts </strong></span>-->
<!--        </div>-->
<!--        <div>-->
<!--            19 <input type="checkbox" /> I certify that the FFI identified in Part I:-->
<!--            <ul>-->
<!--                <li>Is not engaged primarily in the business of investing, reinvesting, or trading in securities, partnership interests, commodities, notional-->
<!--                    principal contracts, insurance or annuity contracts, or any interest (including a futures or forward contract or option) in such security,-->
<!--                    partnership interest, commodity, notional principal contract, insurance contract or annuity contract; </li>-->
<!--                <li>No financial account maintained by the FFI or any member of its expanded affiliated group, if any, has a balance or value in excess of $50-->
<!--                    ,000 (as determined after applying applicable account aggregation rules); and</li>-->
<!--                <li>Neither the FFI nor the entire expanded affiliated group, if any, of the FFI, have more than $50 million in assets on its consolidated or-->
<!--                    combined balance sheet as of the end of its most recent accounting year.</li>-->
<!--            </ul>-->
<!--        </div>-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part VII</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong> Certified Deemed-Compliant Sponsored, Closely Held Investment Vehicle </strong></span>-->
<!--        </div>-->
<!--        <div>-->
<!--            20.Name of sponsoring entity:-->
<!--            <span style="width: 500px;" class="borderB inlineBlock">454</span>-->
<!--        </div>-->
<!--        <div>-->
<!--            21 <input type="checkbox" /> I certify that the entity identified in Part I:-->
<!--            <ul>-->
<!--                <li> Is an FFI solely because it is an investment entity described in Regulations section 1.1471-5(e)(4); </li>-->
<!--                <li>Is not a  QI, WP, or WT; </li>-->
<!--                <li>Will have all of its due diligence, withholding, and reporting responsibilities (determined as if the FFI were a participating FFI) fulfilled by the sponsoring entity identified on line 20; and</li>-->
<!--                <li> 20 or fewer individuals own all of the debt and equity interests in the entity (disregarding debt interests owned by U.S. financial institutions, participating FFIs,-->
<!--                    registered deemed-compliant FFIs, and certified deemed-compliant FFIs and equity-->
<!--                    interests owned by an entity if that entity owns 100% of the equity interests in the FFI and is itself a sponsored FFI). </li>-->
<!--            </ul>-->
<!--        </div>-->
        <#if w8[0].chapterFourStatus=="b10">
            <div class="Beneficial">
                <span style="width: 8%" class="PartI inlineBlock">Part VIII</span>
                <span style="width: 80%;" class="inlineBlock"><strong> Certified Deemed-Compliant Limited Life Debt Investment Entity  </strong></span>
            </div>
            <div>
                22 <input type="checkbox"
                        <#list w8Part.PartVIII.fuxuan1 as v>
                            <#if v?index_of("XRf1")!=-1>
                                checked="true"
                                <#break >
                            </#if>
                        </#list>
                    />
                I certify that the entity identified in Part I:
                <ul>
                    <li>Was in existence as of January 17, 2013;</li>
                    <li> Issued all classes of its debt or equity interests to investors on or before January 17, 2013, pursuant to a trust indenture or similar agreement; and </li>
                    <li> Is certified deemed-compliant because it satisfies the requirements to be treated as a limited life debt investment entity (such as the restrictions with respect
                        to its assets and other requirements under Regulations section 1.1471-5(f)(2)(iv)). </li>
                </ul>
            </div>
        </#if>
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part IX</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Certain Investment Entities that Do Not Maintain Financial Accounts </strong></span>-->
<!--        </div>-->
<!--        <div>-->
<!--            23 <input type="checkbox" /> I certify that the entity identified in Part I:-->
<!--            <ul>-->
<!--                <li>Is a financial institution solely because it is an investment entity described in Regulations section 1.1471-5(e)(4)(i)(A), and</li>-->
<!--                <li>Does not maintain financial accounts.</li>-->
<!--            </ul>-->
<!--        </div>-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part X</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Owner-Documented FFI</strong></span>-->
<!--        </div>-->
<!--        <div>-->
<!--            <strong>Note: </strong>-->
<!--            This status only applies if the U.S. financial institution, participating FFI, or reporting Model 1 FFI to which this form is given has agreed that it-->
<!--            will treat the FFI as an owner-documented FFI (see instructions for eligibility requirements).-->
<!--            In addition, the FFI must make the certifications below.-->
<!--        </div>-->
<!--        <div>-->
<!--            24a <input type="checkbox" /> (All owner-documented FFIs check here) I certify that the FFI identified in Part I:-->
<!--        </div>-->
<!--        <ul>-->
<!--            <li>Does not act as an intermediary;</li>-->
<!--            <li>Does not accept deposits in the ordinary course of a banking or similar business;</li>-->
<!--            <li>Does not hold, as a substantial portion of its business, financial assets for the account of others; </li>-->
<!--            <li>Is not an insurance company (or the holding company of an insurance company) that issues or is obligated to make payments with respect to a financial account; </li>-->
<!--            <li>Is not owned by or in an expanded affiliated group with an entity that accepts deposits in the ordinary course of a banking or similar business, holds, as a substantial portion-->
<!--                of its business, financial assets for the account of others, or is an insurance company (or the holding-->
<!--                company of an insurance company) that issues or is obligated to make payments with respect to a financial account;</li>-->
<!--            <li>Does not maintain a financial account for any nonparticipating FFI; and</li>-->
<!--            <li>Does not have any specified U.S. persons that own an equity interest or debt interest (other than a debt interest that is not a financial account or that has a balance or-->
<!--                value not exceeding $50,000) in the FFI other than those identified on the FFI owner reporting statement.</li>-->
<!--        </ul>-->
<!--        <div><strong>Check box 24b or 24c, whichever applies.</strong></div>-->
<!--        <div>-->
<!--            b <input type="checkbox" /> I certify that the FFI identified in Part I:-->
<!--        </div>-->
<!--        <ul>-->
<!--            <li>-->
<!--                Has provided, or will provide, an FFI owner reporting statement that contains:-->
<!--                <div>(i)The name, address, TIN (if any), chapter 4 status, and type of documentation provided (if required) of every individual and specified U.S. person that-->
<!--                    owns a direct or indirect equity interest in the owner-documented FFI (looking through all entities other than specified U.S. persons);</div>-->
<!--                <div>(ii) The name, address, TIN (if any), and chapter 4 status of every individual and specified U.S. person that owns a debt interest in-->
<!--                    the owner-documented FFI (including any indirect debt interest, which includes-->
<!--                    debt interests in any entity that directly or indirectly owns the payee or any direct or indirect equity interest in-->
<!--                    a debt holder of the payee) that constitutes a financial account in excess of $50,000 (disregarding all such debt interests owned by participating FFIs,-->
<!--                    registered deemed-compliant FFIs, certified deemedcompliant FFIs,-->
<!--                    excepted NFFEs, exempt beneficial owners, or U.S. persons other than specified U.S. persons); and </div>-->
<!--                <div>(iii) Any additional information the withholding agent requests in order to fulfill its obligations with respect to the entity.</div>-->
<!--            </li>-->
<!--            <li>Has provided, or will provide, valid documentation meeting the requirements of Regulations section 1.1471-3(d)(6)(iii) for each person identified in the FFI owner reporting statement.</li>-->
<!--        </ul>-->
<!--        <div>-->
<!--            c <input type="checkbox" /> I certify that the FFI identified in Part I has provided, or will provide, an auditor's letter, signed within 4 years of the date of-->
<!--            payment, from an independent accounting firm or legal representative with a location in the United-->
<!--            States stating that the firm or representative has reviewed the FFI's documentation with respect to all of-->
<!--            its owners and debt holders identified in Regulations section 1.1471-3(d)(6)(iv)(A)(2), and that the FFI meets all the requirements to be an owner-documented FFI. The FFI identified in Part I has also provided,-->
<!--            or will provide, an FFI owner reporting statement of its owners that are specified U.S. persons and  Form(s) W-9, with applicable waivers.-->
<!--        </div>-->
<!--        <br />-->
<!--        <div><strong>Check box 24d if applicable</strong>(optional, see instructions).</div>-->
<!--        <div>-->
<!--            d <input type="checkbox" />  I certify that the entity identified on line 1 is a trust that does not have any contingent beneficiaries or designated classes with unidentified beneficiaries.-->
<!--        </div>-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XI</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Restricted Distributor</strong></span>-->
<!--        </div>-->
<!--        <div>25a <input type="checkbox" />  (All restricted distributors check here) I certify that the entity identified in Part I: </div>-->
<!--        <ul>-->
<!--            <li>Operates as a distributor with respect to debt or equity interests of the restricted fund with respect to which this form is furnished;</li>-->
<!--            <li>Provides investment services to at least 30 customers unrelated to each other and less than half of its customers are related to each other; </li>-->
<!--            <li>Is required to perform AML due diligence procedures under the anti-money laundering laws of its country of organization (which is an FATFcompliant jurisdiction);</li>-->
<!--            <li>Operates solely in its country of incorporation or organization, has no fixed place of business outside of that country, and has the same country of incorporation or-->
<!--                organization as all members of its affiliated group, if any;</li>-->
<!--            <li>Does not solicit customers outside its country of incorporation or organization;</li>-->
<!--            <li>Has no more than $175 million in total assets under management and no more than $7 million in gross revenue on its income statement for the most recent accounting year</li>-->
<!--            <li> Is not a member of an expanded affiliated group that has more than $500 million in total assets under management or more than $20 million in gross revenue for its most-->
<!--                recent accounting year on a combined or consolidated income statement; and</li>-->
<!--            <li>Does not distribute any debt or securities of the restricted fund to specified U.S. persons, passive NFFEs with one or more substantial U.S. owners, or nonparticipating FFIs.</li>-->
<!--        </ul>-->
<!--        <div><strong>Check box 25b or 25c, whichever applies.</strong></div>-->
<!--        <div>I further certify that with respect to all sales of debt or equity interests in the restricted fund with respect to which this form is furnished that-->
<!--            are made after December 31, 2011, the entity identified in Part I:</div>-->
<!--        <div>b <input type="checkbox" /> Has been bound by a distribution agreement that contained a general prohibition on the sale of debt or securities to U.S. entities-->
<!--            and U.S. resident individuals and is currently bound by a distribution agreement that contains a prohibition of the sale of debt-->
<!--            or securities to any specified U.S. person, passive NFFE with one or more substantial U.S. owners, or nonparticipating FFI.</div>-->
<!--        <div>c <input type="checkbox" /> Is currently bound by a distribution agreement that contains a prohibition on the sale of debt or securities to any specified U.S. person, passive NFFE with-->
<!--            one or more substantial U.S. owners, or nonparticipating FFI and,-->
<!--            for all sales made prior to the time that such a restriction was included in its distribution agreement, has reviewed all accounts related to such sales in accordance with the procedures-->
<!--            identified in Regulations section 1.1471-4(c) applicable to preexisting accounts and has redeemed or retired any,-->
<!--            or caused the restricted fund to transfer the securities to a distributor that is a participating FFI or reporting Model-->
<!--            1 FFI securities which were sold to specified U.S. persons, passive NFFEs with one or more substantial U.S. owners, or nonparticipating FFIs.</div>-->
<!--        <br />-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XII</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Nonreporting IGA FFI</strong></span>-->
<!--        </div>-->
<!--        <div>26 <input type="checkbox" /> I certify that the entity identified in Part I: </div>-->
<!--        <ul>-->
<!--            <li>-->
<!--                <div> Meets the requirements to be considered a nonreporting financial institution pursuant to an applicable IGA between the United States and </div>-->
<!--                <div><span style="width: 300px;" class="borderB inlineBlock">454</span>. The applicable IGA is <input-->
<!--                        type="checkbox" /> a Model 1 IGA or a <input type="checkbox" /> Model 2 IGA; and</div>-->
<!--                <div>is treated as a<span style="width: 280px;" class="borderB inlineBlock">454</span>under the provisions of the applicable IGA or Treasury regulations </div>-->
<!--                <div>(if applicable, see instructions); </div>-->
<!--            </li>-->
<!--            <li>If you are a trustee documented trust or a sponsored entity, provide the name of the trustee or sponsor<span style="width: 180px;" class="borderB inlineBlock">454</span></li>-->
<!--        </ul>-->
<!--        <div>The trystee is: <input type="checkbox" /> U.S.  <input type="checkbox" />  Foreign</div>-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XIII</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Foreign Government, Government of a U.S. Possession, or Foreign Central Bank of Issue</strong></span>-->
<!--        </div>-->
<!--        <div>-->
<!--            27 <input type="checkbox" />I certify that the entity identified in Part I is the beneficial owner of the payment, and is not engaged in commercial financial activities of a-->
<!--            type engaged in by an insurance company, custodial institution, or depository institution with respect to the payments,-->
<!--            accounts, or obligations for which this form is submitted (except as permitted in Regulations section 1.1471-6(h)(2)).-->
<!--        </div>-->
<!--        <br />-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XIV</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>International Organization</strong></span>-->
<!--        </div>-->
<!--        <div><strong>Check box 28a or 28b, whichever applies.</strong></div>-->
<!--        <div>28a <input type="checkbox" checked="true" /> I certify that the entity identified in Part I is an international organization described in section 7701(a)(18).</div>-->
<!--        <div>b  <input type="checkbox" /> I certify that the entity identified in Part I:</div>-->
<!--        <ul>-->
<!--            <li>Is comprised primarily of foreign governments; </li>-->
<!--            <li>Is recognized as an intergovernmental or supranational organization under a foreign law similar to the International Organizations Immunities Act or that has in-->
<!--                effect a headquarters agreement with a foreign government;  </li>-->
<!--            <li>The benefit of the entity's income does not inure to any private person; and</li>-->
<!--            <li>Is the beneficial owner of the payment and is not engaged in commercial financial activities of a type engaged in by an insurance company,-->
<!--                custodial institution, or depository institution with respect to the payments, accounts, or obligations for-->
<!--                which this form is submitted (except as permitted in Regulations section 1.1471-6(h)(2)).  </li>-->
<!--        </ul>-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XV</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Exempt Retirement Plans</strong></span>-->
<!--        </div>-->
<!--        <div><strong>Check box 29a, b, c, d, e, or f, whichever applies.</strong></div>-->
<!--        <div>29a  <input type="checkbox" /> I certify that the entity identified in Part I:</div>-->
<!--        <ul>-->
<!--            <li>Is established in a country with which the United States has an income tax treaty in force (see Part III if claiming treaty benefits); </li>-->
<!--            <li>Is operated principally to administer or provide pension or retirement benefits; and </li>-->
<!--            <li> Is entitled to treaty benefits on income that the fund derives from U.S. sources (or would be entitled to benefits if it derived any such income)-->
<!--                as a resident of the other country which satisfies any applicable limitation on benefits requirement.  </li>-->
<!--        </ul>-->
<!--        <div>b  <input type="checkbox" /> I certify that the entity identified in Part I:</div>-->
<!--        <ul>-->
<!--            <li>Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to beneficiaries that are former-->
<!--                employees of one or more employers in consideration for services rendered; </li>-->
<!--            <li> No single beneficiary has a right to more than 5% of the FFI's assets; </li>-->
<!--            <li>Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the country in which the fund is established or operated; and  </li>-->
<!--        </ul>-->
<!--        <div>(i) Is generally exempt from tax on investment income under the laws of the country in which it is established or operates due to its status as a retirement or pension plan; </div>-->
<!--        <div>(ii) Receives at least 50% of its total contributions from sponsoring employers (disregarding transfers of assets from other plans described in this part,-->
<!--            retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, other retirement funds-->
<!--            described in an applicable Model 1 or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A)); </div>-->
<!--        <div>(iii)Either does not permit or penalizes distributions or withdrawals made before the occurrence of specified events related to retirement,-->
<!--            disability, or death (except rollover distributions to accounts described in Regulations section 1.1471-5(b)(2)(i)(A)-->
<!--            (referring to retirement and pension accounts), to retirement and pension accounts described in an applicable Model 1 or Model 2 IGA,-->
<!--            or to other retirement funds described in this part or in an applicable Model 1 or Model 2 IGA); or </div>-->
<!--        <div>(iv) Limits contributions by employees to the fund by reference to earned income of the employee or may not exceed $50,000 annually. </div>-->
<!--        <div>c  <input type="checkbox" /> I certify that the entity identified in Part I:</div>-->
<!--        <ul>-->
<!--            <li>Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to beneficiaries that are former employees of-->
<!--                one or more employers in consideration for services rendered; </li>-->
<!--            <li>Has fewer than 50 participants;</li>-->
<!--            <li>Is sponsored by one or more employers each of which is not an investment entity or passive NFFE;</li>-->
<!--            <li>Employee and employer contributions to the fund (disregarding transfers of assets from other plans described in this part, retirement and pension-->
<!--                accounts described in an applicable Model 1 or Model 2 IGA, or accounts described in Regulations-->
<!--                section 1.1471-5(b)(2)(i)(A)) are limited by reference to earned income and compensation of the employee, respectively;  </li>-->
<!--            <li>Participants that are not residents of the country in which the fund is established or operated are not entitled to more than 20% of the fund's assets; and</li>-->
<!--            <li>Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the country in which the fund is established or operates. </li>-->
<!--            <li></li>-->
<!--        </ul>-->
<!--        <div>d  <input type="checkbox" /> I certify that the entity identified in Part I is formed pursuant to a pension plan that would meet the requirements of section 401(a),-->
<!--            other than the requirement that the plan be funded by a trust created or organized in the United States. </div>-->
<!--        <div>e  <input type="checkbox" /> I certify that the entity identified in Part I is established exclusively to earn income for the benefit of one or more retirement funds described in this part or in an applicable-->
<!--            Model 1 or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A) (referring to retirement and pension accounts),-->
<!--            or retirement and pension accounts described in an applicable Model 1 or Model 2 IGA.</div>-->
<!--        <div>f  <input type="checkbox" /> I certify that the entity identified in Part I:</div>-->
<!--        <ul>-->
<!--            <li>Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession-->
<!--                (each as defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model-->
<!--                1 or Model 2 IGA to provide retirement, disability, or death benefits to beneficiaries or participants-->
<!--                that are current or former employees of the sponsor (or persons designated by such employees); or </li>-->
<!--            <li> Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession-->
<!--                (each as defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model 1 or-->
<!--                Model 2 IGA to provide retirement, disability, or death benefits to beneficiaries or participants that are not current or-->
<!--                former employees of such sponsor, but are in consideration of personal services performed for the sponsor. </li>-->
<!--        </ul>-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XVI</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Entity Wholly Owned by Exempt Beneficial Owners </strong></span>-->
<!--        </div>-->
<!--        <div>30 <input type="checkbox" /> I certify that the entity identified in Part I:  </div>-->
<!--        <ul>-->
<!--            <li>Is an FFI solely because it is an investment entity; </li>-->
<!--            <li>Each direct holder of an equity interest in the investment entity is an exempt beneficial owner described in Regulations section 1.1471-6 or in an applicable Model 1 or Model 2 IGA;</li>-->
<!--            <li>Each direct holder of a debt interest in the investment entity is either a depository institution (with respect to a loan made to such entity) or an exempt beneficial-->
<!--                owner described in Regulations section 1.1471-6 or an applicable Model 1 or Model 2 IGA.  </li>-->
<!--            <li>Has provided an owner reporting statement that contains the name, address, TIN (if any), chapter 4 status, and a description of the type of documentation provided to the withholding agent for every person that owns a-->
<!--                debt interest constituting a financial account or direct equity interest in the entity; and </li>-->
<!--            <li>Has provided documentation establishing that every owner of the entity is an entity described in Regulations section 1.1471-6(b), (c), (d), (e), (f) and/or (g)-->
<!--                without regard to whether such owners are beneficial owners. </li>-->
<!--        </ul>-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XVII</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Territory Financial Institution</strong></span>-->
<!--        </div>-->
<!--        <div>31 <input type="checkbox" /> I certify that the entity identified in Part I is a financial institution (other than an investment entity) that is incorporated or organized under-->
<!--            the laws of a possession of the United States.</div>-->
        <#if w8[0].chapterFourStatus=="b15">
        <div class="Beneficial">
            <span style="width: 8%" class="PartI inlineBlock">Part XVIII</span>
            <span style="width: 80%;" class="inlineBlock"><strong>Excepted Nonfinancial Group Entit</strong></span>
        </div>
        <div>32 <input
                    type="checkbox"
                    <#list w8Part.PartVIII.fuxuan1 as v>
                        <#if v?index_of("XRf2")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
                /> I certify that the entity identified in Part I:
        </div>
        <ul>
            <li>Is a holding company, treasury center, or captive finance company and substantially all of the entity's activities are functions described in Regulations section 1.1471-5(e)(5)(i)(C) through (E);</li>
            <li>Is a member of a nonfinancial group described in Regulations section 1.1471-5(e)(5)(i)(B);</li>
            <li>Is not a depository or custodial institution (other than for members of the entity's expanded affiliated group); and </li>
            <li>Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment
                vehicle with an investment strategy to acquire or fund companies
                and then hold interests in those companies as capital assets for investment purposes. </li>
        </ul>
        </#if>
        <#if w8[0].chapterFourStatus=="b16">
        <div class="Beneficial"> 
            <span style="width: 8%" class="PartI inlineBlock">Part XIX</span>
            <span style="width: 80%;" class="inlineBlock"><strong>Excepted Nonfinancial Start-Up Company</strong></span>
        </div>
        <div>33 <input
                    type="checkbox"
                    <#list w8Part.PartXIX.fuxuan1 as v>
                        <#if v?index_of("f1fa")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
                />
            I certify that the entity identified in Part I:
        </div>
        <ul>
            <li> Was formed on (or, in the case of a new line of business, the date of board resolution approving the new line of business)
                <span style="width: 80px;text-align: center;" class="borderB inlineBlock">
                        ${(w8Part.PartXIX.inputData)!"N/A"}
                </span>(date must be less than 24 months prior to date of payment);
            </li>
            <li>Is not yet operating a business and has no prior operating history or is investing capital in assets with the intent to
                operate a new line of business other than that of a financial institution or passive NFFE; </li>
            <li>Is investing capital into assets with the intent to operate a business other than that of a financial institution; and</li>
            <li>Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle
                whose purpose is to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes. </li>
        </ul>
        </#if>
        <#if w8[0].chapterFourStatus=="b17">
        <div class="Beneficial">
            <span style="width: 8%" class="PartI inlineBlock">Part XX</span>
            <span style="width: 80%;" class="inlineBlock"><strong>Excepted Nonfinancial Entity in Liquidation or Bankruptcy </strong></span>
        </div>
        <div>34 <input type="checkbox"
                    <#list w8Part.PartXX.fuxuan1 as v>
                        <#if v?index_of("f1af")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
                /> I certify that the entity identified in Part I: </div>
        <ul>
            <li>Filed a plan of liquidation, filed a plan of reorganization, or filed for bankruptcy on
                <span style="width: 250px;text-align: center;" class="inlineBlock borderB">
                        ${(w8Part.PartXX.inputData)!"N/A"}
                </span></li>

            <li>During the past 5 years has not been engaged in business as a financial institution or acted as a passive NFFE; </li>
            <li>Is either liquidating or emerging from a reorganization or bankruptcy with the intent to continue or recommence operations as a nonfinancial entity; and</li>
            <li>Has, or will provide, documentary evidence such as a bankruptcy filing or other public documentation that supports its claim if it remains in bankruptcy or liquidation for more than 3 years. </li>
        </ul>
        </#if>
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XXI</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>501(c) Organization</strong></span>-->
<!--        </div>-->
<!--        <div>35 <input type="checkbox" /> I certify that the entity identified in Part I is a 501(c) organization that: </div>-->
<!--        <ul>-->
<!--            <li>Has been issued a determination letter from the IRS that is currently in effect concluding that the payee is a section 501(c) organization that is-->
<!--                dated <span style="width: 100px" class="borderB inlineBlock"></span> ; or</li>-->
<!--            <li>-->
<!--                Has provided a copy of an opinion from U.S. counsel certifying that the payee is a section 501(c) organization-->
<!--                (without regard to whether the payee is a foreign private foundation).-->
<!--            </li>-->
<!--        </ul>-->
<!--        <br />-->
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XXII</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Nonprofit Organization</strong></span>-->
<!--        </div>-->
<!--        <div>36 <input type="checkbox" /> I certify that the entity identified in Part I is a nonprofit organization that meets the following requirements. </div>-->
<!--        <ul>-->
<!--            <li>The entity is established and maintained in its country of residence exclusively for religious, charitable, scientific,-->
<!--                artistic, cultural or educational purposes; </li>-->
<!--            <li>The entity is exempt from income tax in its country of residence;</li>-->
<!--            <li>The entity has no shareholders or members who have a proprietary or beneficial interest in its income or assets; </li>-->
<!--            <li>Neither the applicable laws of the entity's country of residence nor the entity's formation documents permit any income or assets of the entity-->
<!--                to be distributed to, or applied for the benefit of, a private person or noncharitable entity other than pursuant to the conduct of the-->
<!--                entity's charitable activities or as payment of reasonable compensation for services rendered or payment representing the fair market value of-->
<!--                property which the entity has purchased; and </li>-->
<!--            <li>The applicable laws of the entity's country of residence or the entity's formation documents require that, upon the entity's liquidation or-->
<!--                dissolution, all of its assets be distributed to an entity that is a foreign government, an integral part of a foreign government,-->
<!--                a controlled entity of a foreign government, or another organization that is described in this part or escheats to the government of the-->
<!--                entity's country of residence or any political subdivision thereof. </li>-->
<!--        </ul>-->
        <#if w8[0].chapterFourStatus=="b18">
        <div class="Beneficial">
            <span style="width: 8%" class="PartI inlineBlock">Part XXIII </span>
            <span style="width: 80%;" class="inlineBlock"><strong>Publicly Traded NFFE or NFFE Affiliate of a Publicly Traded Corporation</strong></span>
        </div>
        <div><strong>Check box 37a or 37b, whichever applies.</strong></div>
        <div>37a <input type="checkbox"
                    <#list w8Part.PartXXIII.fuxuan1 as v>
                        <#if v?index_of("f1fdsaa")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
                 /> I certify that:</div>
        <ul>
            <li>The entity identified in Part I is a foreign corporation that is not a financial institution; and</li>
            <li> The stock of such corporation is regularly traded on one or more established securities markets, including
                <span style="width: 130px;text-align: center;" class="borderB inlineBlock">
                        ${(w8Part.PartXXIII.input1)!"N/A"}
                </span></li>
        </ul>
        <div style="margin-left: 14px">b <input type="checkbox"
                    <#list w8Part.PartXXIII.fuxuan1 as v>
                        <#if v?index_of("f1fdasf")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I certify that:</div>
        <ul>
            <li>The entity identified in Part I is a foreign corporation that is not a financial institution; </li>
            <li>The entity identified in Part I is a member of the same expanded affiliated group as an entity the stock of which is
                regularly traded on an established securities market; </li>
            <li> The name of the entity, the stock of which is regularly traded on an established securities market, is
                <span style="width: 135px;text-align: center;" class="borderB inlineBlock">
                        ${(w8Part.PartXXIII.input2)!"N/A"}
                </span>; and</li>
            <li>The name of the securities market on which the stock is regularly traded is
                <span style="width: 290px;text-align: center;" class="borderB inlineBlock">
                        ${(w8Part.PartXXIII.input3)!"N/A"}
                </span></li>
        </ul>
        </#if>
<!--        <div class="Beneficial">-->
<!--            <span style="width: 8%" class="PartI inlineBlock">Part XXIV</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Excepted Territory NFFE</strong></span>-->
<!--        </div>-->
<!--        <div>38 <input type="checkbox" /> I certify that:</div>-->
<!--        <ui>-->
<!--            <li>The entity identified in Part I is an entity that is organized in a possession of the United States; </li>-->
<!--            <li>The entity identified in Part I:</li>-->
<!--            <li> All of the owners of the entity identified in Part I are bona fide residents of the possession in which the NFFE is organized or-->
<!--                incorporated. </li>-->
<!--        </ui>-->
        <#if w8[0].chapterFourStatus=="b20">
        <div class="Beneficial">
            <span style="width: 8%" class="PartI inlineBlock">Part XXV</span>
            <span style="width: 80%;" class="inlineBlock"><strong>Active NFFE </strong></span>
        </div>
        <div>39 <input type="checkbox"
                    <#list w8Part.PartXXV.fuxuan1 as v>
                        <#if v?index_of("f1dfass")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
                /> I certify that:</div>
        <ul>
            <li>The entity identified in Part I is a foreign entity that is not a financial institution;  </li>
            <li>Less than 50% of such entity's gross income for the preceding calendar year is passive income; and</li>
            <li> Less than 50% of the assets held by such entity are assets that produce or are held for the production of passive income (calculated as a weighted average of the percentage of
                passive assets measured quarterly) (see instructions for the definition of passive income). </li>
        </ul>
        </#if>
        <#if w8[0].chapterFourStatus=="b21">
        <div class="Beneficial">
            <span style="width: 8%" class="PartI inlineBlock">Part XXVI </span>
            <span style="width: 80%;" class="inlineBlock"><strong>Passive NFFE </strong></span>
        </div>
        <div>40a <input type="checkbox"
                    <#list w8Part.PartXXVI.fuxuan1 as v>
                        <#if v?index_of("f1fas")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
                 /> I certify that the entity identified in Part I is a foreign entity that is not a financial institution
            (other than an investment entity organized in a possession of the United States) and is not certifying its status as a publicly traded NFFE
            (or affiliate), excepted territory NFFE, active NFFE, direct reporting NFFE, or sponsored direct reporting NFFE.</div>
        <br />
        <div><strong></strong>Check box 40b or 40c, whichever applies.</div>
        <div style="margin-left: 14px">b <input type="checkbox"
                    <#list w8Part.PartXXVI.fuxuan1 as v>
                        <#if v?index_of("fdd1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I further certify that the entity identified in Part I has no substantial U.S. owners (or, if applicable, no controlling U.S. persons); or</div>
        <div style="margin-left: 14px">c <input type="checkbox"
                    <#list w8Part.PartXXVI.fuxuan1 as v>
                        <#if v?index_of("fsf1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
            /> I further certify that the entity identified in Part I has provided the name, address, and TIN of each substantial U.S. owner
            (or, if applicable, controlling U.S. person) of the NFFE in Part XXIX.</div>
        </#if>
<#--        <#if w8[0].chapterFourStatus=="b100">-->
<#--        <div class="Beneficial">-->
<#--            <span style="width: 8%" class="PartI inlineBlock">Part XXVII</span>-->
<#--            <span style="width: 80%;" class="inlineBlock"><strong>Excepted Inter-Affiliate FFI </strong></span>-->
<#--        </div>-->
<#--        <div>41 <input type="checkbox" /> I certify that the entity identified in Part I: </div>-->
<#--        <ul>-->
<#--            <li>Is a member of an expanded affiliated group;</li>-->
<#--            <li>Does not maintain financial accounts (other than accounts maintained for members of its expanded affiliated group); </li>-->
<#--            <li>Does not make withholdable payments to any person other than to members of its expanded affiliated group;</li>-->
<#--            <li>Does not hold an account (other than depository accounts in the country in which the entity is operating to pay for expenses) with or receive payments from any withholding agent-->
<#--                other than a member of its expanded affiliated group; and </li>-->
<#--            <li>Has not agreed to report under Regulations section 1.1471-4(d)(2)(ii)(C) or otherwise act as an agent for chapter 4 purposes on behalf of any-->
<#--                financial institution, including a member of its expanded affiliated group</li>-->
<#--        </ul>-->
<#--        </#if>-->
        <#if w8[0].chapterFourStatus=="b22">
        <div class="Beneficial">
            <span style="width: 9%" class="PartI inlineBlock">Part XXVIII </span>
            <span style="width: 80%;" class="inlineBlock"><strong>Sponsored Direct Reporting NFFE (see instructions for when this is permitted) </strong></span>
        </div>
        <div>42 Name of sponsoring entity:  <span style="width: 400px;text-align: center;" class="borderB inlineBlock">
                 ${(w8Part.PartXXVIII.input1)!"N/A"}
            </span></div>
        <div>43 <input type="checkbox"
                    <#list w8Part.PartXXVIII.fuxuan1 as v>
                        <#if v?index_of("ffaaf1")!=-1>
                            checked="true"
                            <#break >
                        </#if>
                    </#list>
                /> I certify that the entity identified in Part I is a direct reporting NFFE that is sponsored by the entity identified on line 42. </div>
        </#if>

        <!--        <div class="Beneficial">-->
<!--            <span style="width: 9%" class="PartI inlineBlock">Part XXIX</span>-->
<!--            <span style="width: 80%;" class="inlineBlock"><strong>Substantial U.S. Owners of Passive NFFE </strong></span>-->
<!--        </div>-->
<!--        <div>As required by Part XXVI, provide the name, address, and TIN of each substantial U.S. owner of the NFFE. Please see the instructions for a definition of substantial U.S. owner. If providing the form to an FFI treated as a reporting Model 1 FFI or reporting Model 2 FFI, an NFFE may also use-->
<!--            this part for reporting its controlling U.S. persons under an applicable IGA.</div>-->
<!--        <table cellpadding="0" cellspacing="0">-->
<!--            <tr>-->
<!--                <td>Name</td>-->
<!--                <td>Address</td>-->
<!--                <td>TIN</td>-->
<!--            </tr>-->
<!--            <tr>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--            </tr>-->
<!--            <tr>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--            </tr>-->
<!--            <tr>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--            </tr>-->
<!--            <tr>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--            </tr>-->
<!--            <tr>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--            </tr>-->
<!--            <tr>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--                <td></td>-->
<!--            </tr>-->
<!--        </table>-->
<!--        <br />-->
        <div class="Beneficial">
            <span style="width: 8%" class="PartI inlineBlock">Part XXX</span>
            <span style="width: 80%;" class="inlineBlock"><strong>Certification </strong></span>
        </div>
        <div>Under penalties of perjury, I declare that I have examined the information on this form and to the best of my knowledge and belief it is true, correct,
            and complete. I further certify under penalties of perjury that: </div>
        <ul>
            <li>The entity identified on line 1 of this form is the beneficial owner of all the income to which this form relates, is using this form to
                certify its status for chapter 4 purposes, or is a merchant submitting this form for purposes of section 6050W;</li>
            <li>The entity identified on line 1 of this form is not a U.S. person;</li>
            <li>The income to which this form relates is: (a) not effectively connected with the conduct of a trade or business in the United States,
                (b) effectively connected but is not subject to tax under an income tax treaty, or (c) the partner's share of a partnership's effectively
                connected income; and</li>
            <li>For broker transactions or barter exchanges, the beneficial owner is an exempt foreign person as defined in the instructions. </li>
        </ul>
        <div>Furthermore, I authorize this form to be provided to any withholding agent that has control, receipt, or custody of the income of which the
            entity on line 1 is the beneficial owner or any withholding agent that can disburse or make payments of the income of which the entity on line 1
            is the beneficial owner. </div>
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
                        <div style="width: 360px;text-align: center;font-size: 9px">Signature of beneficial owner (or individual authorized to sign for beneficial owner) </div>
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
<#--        <div style="margin-left: 85px"><input type="checkbox" /> <strong>I certify that I have the capacity to sign for the entity identified on line 1 of this form.</strong> </div>-->
    </div>
</body>
</html>
