
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
                    ${( personalAddress.city + ","+personalAddress.rovince + ","+personalAddress.zipCode )!"N/A"}
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
                        <#if w8Part.PartVIII.fuxuan1[0] ?? && w8Part.PartVIII.fuxuan1[0]=="XRf1">
                            checked="true"
                        </#if>
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

        <#if w8[0].chapterFourStatus=="b15">
        <div class="Beneficial">
            <span style="width: 8%" class="PartI inlineBlock">Part XVIII</span>
            <span style="width: 80%;" class="inlineBlock"><strong>Excepted Nonfinancial Group Entit</strong></span>
        </div>
        <div>32 <input
                    type="checkbox"
                    <#list w8Part.PartXVIII.fuxuan1 as v>
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
                <span style="width: 135px;;text-align: center;" class="borderB inlineBlock">
                        ${(w8Part.PartXXIII.input2)!"N/A"}
                </span>; and</li>
            <li>The name of the securities market on which the stock is regularly traded is
                <span style="width: 290px;text-align: center;" class="borderB inlineBlock">
                        ${(w8Part.PartXXIII.input3)!"N/A"}
                </span></li>
        </ul>
        </#if>

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

        <#if w8[0].chapterFourStatus=="b22">
        <div class="Beneficial">
            <span style="width: 9%" class="PartI inlineBlock">Part XXVIII </span>
            <span style="width: 80%;" class="inlineBlock"><strong>Sponsored Direct Reporting NFFE (see instructions for when this is permitted) </strong></span>
        </div>
        <div>42 Name of sponsoring entity:
            <span style="width: 400px;;text-align: center;" class="borderB inlineBlock">
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
