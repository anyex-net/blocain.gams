
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <style>
        /*@font-face {*/
        /*    font-family: SourceHanSansCN;*/
        /*    src: url('./font/SourceHanSans.ttf');*/
        /*}*/
        body{
            font-family:Source Han Sans CN Regular,Serif;
            font-size: 10pt;
            line-height: 1;
        }
        @media print {
            /*div.header-right {*/
            /*    display: block;*/
            /*    position: running(header-right);*/
            /*}*/
            div.header-left {
                display: block;
                position: running(header-left);
            }
            /*div.bottom-left {*/
            /*    display: inline-block;*/
            /*    position: running(bottom-left);*/
            /*}*/
            /*div.bottom-right {*/
            /*    display: inline-block;*/
            /*    position: running(bottom-right);*/
            /*}*/

            img{page-break-inside:avoid;}
            h4{page-break-inside:avoid;}
            /*table{page-break-inside:avoid;}*/
        }
        @page {
            size: 595.3pt 840pt;
            margin: 90.0pt 1.27cm 90pt 1.27cm;
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
            min-height: 16px;
            /*word-break: break-all;*/
            border-right: 0px;
            border-bottom: 0px;
            flex: 1;
            /*word-break: break-word;*/
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
            margin-top: 0px;
        }
        .deLine{
            height: 25px;
            line-height: 25px;
            border-bottom: 1px solid #000;
        }
        li{
            position: relative;
        }
         li:nth-child(n)::before{
            content: '';
            position: absolute;
            background: black;
            width: 10px;
            height: 10px;
            line-height: 5px;
            left: -18px;
            top: 5px;
        }
        ul li{
            list-style-type:none;
        }
        ul{
            margin: 0px;
        }

    </style>
</head>
<body>
    <div id="header">
        <div id="header-left" class="header-left">
            <img src="data:${fileType};base64,${file64Str}" width="150px" style="display: inline-block" />
            <span style="margin-left: 20px;text-align: right;float: right">
                <div>MOF Securities, Ltd.</div>
                <div>Organization Account Application</div>
                <div>机构账户申请表</div>
            </span>
        </div>
    </div>
    <div id="footer">
    <div id="footer-left" class="footer-left" style="text-align: left;float: left">
        <span>
            <div>MOF Securities Limited</div>
            <div>国瑞证券有限公司</div>
        </span>

    </div>
    <div id="footer-right" class="footer-right" style="text-align: right;float: right">
        <span>
            <div>Page <span id="pages"> of </span></div>
            <div>第 <span id="pages">页，共 </span>页</div>
        </span>
    </div>
</div>
    <div class="page1">
    <div style="height: 30px">
        <span style="float: left">www.mofsecurities.com | +86 4006589990</span>
        <span style="float: right"></span>
    </div>
    <h4>1. Account Information 账户信息</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <div>Username账户名</div>
                <div class="valueTd">${(account.accountName)!"N/A"}</div>
            </td>
            <td>
                <div>Mail Address邮箱地址</div>
                <div class="valueTd">${(account.email)!"N/A"}</div>
            </td>
            <td>
                <div>Country Code国际区号</div>
                <div class="valueTd">${(account.region)!"N/A"}</div>
            </td>
            <td>
                <div>Phone Number手机号</div>
                <div class="valueTd">${(account.mobile)!"N/A"}</div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Residential Country 居住国家</div>
                <div class="valueTd">${(account.country)!"N/A"}</div>
            </td>
            <td colspan="2">
                <div>Country of Legal Residence* 永久居住国家</div>
                <div class="valueTd">${(account.nationality)!"N/A"}</div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Account Type账户类型</div>
                <div class="valueTd">
                    ${(portfolios[0].accountPortfolioType.dictDest)!"N/A"}
                </div>
            </td>
            <td colspan="2">
                <div>Base Currency基础货币</div>
                <div class="valueTd">
                    ${(portfolios[0].baseCurrency.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
    </table>

    <h4>2. Organization Information 机构信息</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="3">
                <div>Company Name企业名称</div>
                <div class="valueTd">
                    ${(institutional[0].institutionalName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <div>Description of Business业务描述</div>
                <div class="valueTd" style="height: 96px;">
                    ${(institutional[0].bsinessDescription)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <div>Business Name商业名称</div>
                <div class="valueTd" style="height: 96px;">
                    ${(institutional[0].businessName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <div>Website Address网站</div>
                <div>
                    ${(institutional[0].websiteUrl)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="height: 96px;">
                <div>Company Nature 企业性质</div>
                <div>${(institutional[0].istitutionalNature[0].dictDest)!}</div>
                <div>${(institutional[0].istitutionalNature[1].dictDest)!}</div>
                <div>${(institutional[0].istitutionalNature[2].dictDest)!}</div>
                <div>${(institutional[0].istitutionalNature[3].dictDest)!}</div>
            </td>
        </tr>
        <tr>
            <td style="width: 200px;">
                <div>Principal Place Of Business</div>
                <div>主要经营地点</div>
            </td>
            <td colspan="2" class="valueTd">
                ${(personalAddress.address +","+personalAddress.city +""+personalAddress.rovince+","+personalAddress.country.enName )!"N/A"}
            </td>
        </tr>
        <tr>
            <td style="width: 200px;">
                <div>Zip/Postal Code</div>
                <div>邮政编码</div>
            </td>
            <td colspan="2" class="valueTd">
                ${(personalAddress.zipCode)!"N/A"}
            </td>
        </tr>
        <tr>
            <td style="width: 200px;">
                <div>Mailing address (if different from above)</div>
                <div>邮寄地址（与上方不同）</div>
            </td>
            <td colspan="2" class="valueTd">
                ${(mailAddress.address +","+mailAddress.city +""+mailAddress.rovince+","+mailAddress.country.enName )!"N/A"}
            </td>
        </tr>
        <tr>
            <td style="width: 100px;">
                <div>Zip/Postal Code</div>
                <div>邮政编码</div>
            </td>
            <td colspan="2" class="valueTd">
                ${(mailAddress.zipCode)!"N/A"}
            </td>
        </tr>
    </table>
        <br />
        <br />
        <br />
        <br />
        <br />
    <table border="1" cellpadding="0" cellspacing="0">
        <!--联系信息-->
        <tr>
            <td colspan="3">Contact Information联系信息</td>
        </tr>
        <tr>
            <td style="width:200px;">Type手机类型</td>
            <td style="width:200px; ">Country国家</td>
            <td>Number电话号码</td>
        </tr>
        <tr>
            <td> ${(institutionalContacts[0].contactNumberType.dictDest)!"N/A"}</td>
            <td> ${(institutionalContacts[0].country.enName)!"N/A"}</td>
            <td> ${(institutionalContacts[0].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td> ${(institutionalContacts[1].contactNumberType.dictDest)!"N/A"}</td>
            <td> ${(institutionalContacts[1].country.enName)!"N/A"}</td>
            <td> ${(institutionalContacts[1].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td> ${(institutionalContacts[2].contactNumberType.dictDest)!"N/A"}</td>
            <td> ${(institutionalContacts[2].country.enName)!"N/A"}</td>
            <td> ${(institutionalContacts[2].contactNumber)!"N/A"}</td>
        </tr>
    </table>
    <!--  Residency税务居住地  -->
    <h4>3. Tax Residency 税务居住地</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td style="width: 260px;">
                <div>Country</div>
                <div class="valueTd">
                    国家地区
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(institutionalTaxes[0].taxResidenceCountry.enName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Do you have a Tax Identification Number? </div>
                <div class="valueTd">
                    您有没有纳税识别号？
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    <#if (institutionalTaxes[0].taxResidenceIdCardNumber) ?? && institutionalTaxes[0].taxResidenceIdCardNumber !="N.A." >
                        YES
                    <#else >
                        NO
                    </#if>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Tax Identification Number</div>
                <div class="valueTd">
                    纳税识别号
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    <#if institutionalTaxes[0].taxResidenceIdCardNumber ?? && institutionalTaxes[0].taxResidenceIdCardNumber !="N.A.">
                        ${(institutionalTaxes[0].taxResidenceIdCardNumber)!"N/A"}
                    <#else >
                        N/A
                    </#if>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Reason</div>
                <div class="valueTd">
                    理由
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(institutionalTaxes[0].noTaxIdentificationNumber.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Different Registration Number</div>
                <div class="valueTd">
                    机构注册号码
                </div>
            </td>
            <td colspan="3" class="valueTd">
                ${(institutionalTaxes[0].differentRegistrationNumber)!"N/A"}
            </td>
        </tr>
    </table>
<#--    <table border="1" cellpadding="0" cellspacing="0">-->
<#--        -->
<#--    </table>-->
    <h4>4. Source of Wealth 财富来源</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td style="width: 150px">
                <div>Type</div>
                <div>收入来源</div></td>
            <td style="width: 80px">
                <div>Percentage</div>
                <div>比例</div></td>
            <td colspan="2">
                <div>Notes</div>
                <div>注释</div>
            </td>
        </tr>
        <tr>
            <td>
                ${(wealthSources[0].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[0].percentageOfFunds + "%")!}
            </td>
            <td style="height: 48px" class="valueTd" colspan="2">
                ${(wealthSources[0].sourceDesc)!}
            </td>
        </tr>
        <tr>
            <td>
                ${(wealthSources[1].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[1].percentageOfFunds + "%")!}
            </td>
            <td class="valueTd" style="height: 48px" colspan="2">
                ${(wealthSources[1].sourceDesc)!}
            </td>
        </tr>
        <tr>
            <td>
                ${(wealthSources[2].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[2].percentageOfFunds + "%")!}
            </td>
            <td class="valueTd" style="height: 48px" colspan="2">
                ${(wealthSources[2].sourceDesc)!}
            </td>
        </tr>
        <tr>
            <td>
                ${(wealthSources[3].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[3].percentageOfFunds + "%")!}
            </td>
            <td class="valueTd" style="height: 48px" colspan="2">
                ${(wealthSources[3].sourceDesc)!}
            </td>
        </tr>
        <tr>
            <td>
                ${(wealthSources[4].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[4].percentageOfFunds + "%")!}
            </td>
            <td class="valueTd" style="height: 48px" colspan="2">
                ${(wealthSources[4].sourceDesc)!}
            </td>
        </tr>
    </table>
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
    <h4>5. Authorized person 被授权人</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>Salutation称呼</td>
            <td colspan="3">
                ${(authorizedPersons[0].accountCoPersonal.appellation.dictDest)!}
            </td>
        </tr>
        <tr>
            <td style="width: 170px;">
                <div>Last Name姓</div>
                <div class="valueTd">
                    ${(authorizedPersons[0].accountCoPersonal.lastName)!}
                </div>
            </td>
            <td style="width: 170px;">
                <div>Middle Name(s)中间名*</div>
                <div>
                    ${(authorizedPersons[0].accountCoPersonal.middleName)!}
                </div>
            </td>
            <td style="width: 150px;">
                <div>First Name名</div>
                <div>
                    ${(authorizedPersons[0].accountCoPersonal.firstName)!}
                </div>
            </td>
            <td>
                <div>Suffix后缀*</div>
                <div>
                    ${(authorizedPersons[0].accountCoPersonal.suffix)!}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Date of Birth出生日期</div>
                <div>
                    ${(authorizedPersons[0].accountCoPersonal.birthday)!"N/A"}
                </div>
            </td>
            <td colspan="2">
                <div>Position职位</div>
                <div>
                    <#if (authorizedPersons[0].accountCoPersonal.companyPosition[0].dictDest) ?? &&authorizedPersons[0].accountCoPersonal.companyPosition[0].dictDest == "mishu">
                        secretary
                    <#elseif (authorizedPersons[0].accountCoPersonal.companyPosition[0].dictDest) ?? &&authorizedPersons[0].accountCoPersonal.companyPosition[0].dictDest == "dongshi">
                        director
                    <#else >
                        ${(authorizedPersons[0].accountCoPersonal.companyPosition[0].dictDest?replace('qitaguanyuan,',''))!"N/A"}
                    </#if>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Residential Address</div>
                <div>居住地址（非办公地点）</div>
            </td>
            <td colspan="3" class="valueTd">
                <#list authorizedPersons[0].accountCoAddressList as address>
                    <#if (address.addressType) ?? && address.addressType == "personalAddress">
                        ${(address.address +","+address.city +""+address.rovince+","+address.country.enName )!"N/A"}
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Zip/Postal Code</div>
                <div>邮政编码</div>
            </td>
            <td colspan="3" class="valueTd">
                <#list authorizedPersons[0].accountCoAddressList as address>
                    <#if (address.addressType) ?? && address.addressType == "personalAddress">
                        ${(address.zipCode)!"N/A"}
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Country of Citizenship (if different from above)</div>
                <div>国籍与上方国家不同*</div>
            </td>
            <td colspan="2">
                <div class="valueTd">
                    ${(authorizedPersons[0].accountCoAddressList[0].otherCountry.enName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Country of Birth (if different from above)</div>
                <div>出生国与上方国家不同*</div>
            </td>
            <td colspan="2">
                <div class="valueTd">
                    ${(authorizedPersons[0].accountCoAddressList[0].otherBirthCountry.enName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">Contact Information联系信息</td>
        </tr>
        <tr>
            <td>
                <div>Email Address</div>
                <div>邮箱地址</div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(authorizedPersons[0].accountCoPersonal.email)!}
                </div>
            </td>
        </tr>
        <tr>
            <td>Type手机类型</td>
            <td>Country国家</td>
            <td colspan="2">Number电话号码</td>
        </tr>
        <tr>
            <td>${(authorizedPersons[0].accountCoContactList[0].contactNumberType.dictDest)!"N/A"}</td>
            <td>${(authorizedPersons[0].accountCoContactList[0].country.enName)!"N/A"}</td>
            <td colspan="2">${(authorizedPersons[0].accountCoContactList[0].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td>${(authorizedPersons[0].accountCoContactList[1].contactNumberType.dictDest)!"N/A"}</td>
            <td>${(authorizedPersons[0].accountCoContactList[1].country.enName)!"N/A"}</td>
            <td colspan="2">${(authorizedPersons[0].accountCoContactList[1].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td>${(authorizedPersons[0].accountCoContactList[2].contactNumberType.dictDest)!"N/A"}</td>
            <td>${(authorizedPersons[0].accountCoContactList[2].country.enName)!"N/A"}</td>
            <td colspan="2">${(authorizedPersons[0].accountCoContactList[2].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td colspan="4"><strong>Identification身份证明</strong></td>
        </tr>
        <tr>
            <td>
                <div>Identification Type</div>
                <div>证件类型</div>
            </td>
            <td colspan="3" class="valueTd">
                ${(authorizedPersons[0].accountCoIdentityList[0].idCardType.dictDest)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>
                <div>Country of Issuance</div>
                <div>签发国家</div>
            </td>
            <td class="valueTd">
                ${(authorizedPersons[0].accountCoIdentityList[0].issuingCountry.enName)!"N/A"}
            </td>
            <td>
                <div>Identification Number</div>
                <div>证件号码</div>
            </td>
            <td class="valueTd">
                ${(authorizedPersons[0].accountCoIdentityList[0].idNumber)!"N/A"}
            </td>
        </tr>
        <tr>
            <td colspan="4">Tax Residency税务居住地</td>
        </tr>
        <tr>
            <td style="width: 230px;">
                <div>Country</div>
                <div class="valueTd">
                    国家地区
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(authorizedPersons[0].accountCoTaxesList[0].taxResidenceCountry.enName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Do you have a Tax Identification Number? </div>
                <div class="valueTd">
                    您有没有纳税识别号？
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    <#if (authorizedPersons[0].accountCoTaxesList[0].taxResidenceIdCardNumber) ?? && authorizedPersons[0].accountCoTaxesList[0].taxResidenceIdCardNumber !="N.A." >
                        YES
                    <#else >
                        NO
                    </#if>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Tax Identification Number</div>
                <div class="valueTd">
                    纳税识别号
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    <#if authorizedPersons[0].accountCoTaxesList[0].taxResidenceIdCardNumber ?? && authorizedPersons[0].accountCoTaxesList[0].taxResidenceIdCardNumber !="N.A.">
                        ${(authorizedPersons[0].accountCoTaxesList[0].taxResidenceIdCardNumber)!"N/A"}
                    <#else >
                        N/A
                    </#if>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Reason</div>
                <div class="valueTd">
                    理由
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(authorizedPersons[0].accountCoTaxesList[0].noTaxIdentificationNumber.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
    </table>
        <br />
<#--    <table border="1" cellpadding="0" cellspacing="0" style="margin-top: 2px;">-->
<#--        -->
<#--    </table>-->
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
<#--        <br />-->
    <h4>6. Tax Classification 税收分类</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td style="width: 300px;">
                <div>Identify the manner in which is treated for US income tax urposes.</div>
                <div>被征收美国个人所得税的方式</div>
            </td>
            <td class="valueTd">
                ${(taxClassifications[0].taxType.dictDest)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>
                <div>Is claiming the benefits of a tax treaty with the United States as a hybrid entity?</div>
                <div>作为混合实体要求美国的税收协定优惠待遇*</div>
            </td>
            <td class="valueTd">
                <#if taxClassifications[0].isPreferentia ??>
                    <#if  taxClassifications[0].isPreferentia == true>
                        YES
                    <#else >
                        NO
                    </#if>
                <#else >
                    N/A
                </#if>
            </td>
        </tr>
    </table>
    <h4>7. Regulatory Information 规管信息</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <div>Foreign banks (established and operated outside the US)？</div>
                <div>外国银行(在美国以外成立及运营)？</div>
            </td>
            <td style="width: 50px;text-align: center;">
                <#list regulatorys as regulatory>
                     <#if regulatory ?? && regulatory.infoType.dictValue == "foreignBank (established and operated outside the United States)">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                         <#break >
                     </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Is the firm operating under a banking license that has been issued by any of the following countries？
                </div>
                <div>公司是否在以下任一国家所发银行许可下运营？*</div>
                <div class="valueTd" style="text-align: justify;text-align-last: justify;">
                    Afghanistan, Angola, Albania, United Arab Emirates, Armenia, Azerbaijan, Burundi, Bangladesh, Bahrain, Bahamas, Bosnia and Herzegovina, Belarus,
                    Belize, Bolivia, Central African Republic, Côte d'Ivoire, Cameroon, Democratic Republic of the Congo, Congo(Brazil) , Colombia, Comoros, Costa
                    Rica, Cuba, Djibouti, Dominican Republic, Algeria, Ecuador, Egypt, Eritrea, Western Sahara, Estonia, Ethiopia, Georgia, Ghana, Guinea, Gambia,
                    Guinea- Bissau, Equatorial Guinea, Grenada, Guatemala, Guyana, Honduras, Croatia, Haiti, Indonesia, Iran(Islamic Republic of), Iraq, Jamaica,
                    Jordan, Kazakhstan, Kenya, Kyrgyzstan, Cambodia, Kuwait, Lao People's Democratic Republic, Lebanon, Liberia, Libyan Arab Jamahiriya, Lithuania,
                    Latvia, Morocco, Republic of Moldova, Madagascar, Former Yugoslav Republic of Macedonia, Mali, Myanmar, Montenegro, Malay Asia, Namibia, Nigeria,
                    Nicaragua, Nauru, Pakistan, Panama, Peru, Philippines, Pelican Islands, Papua New Guinea, Democratic People's Republic of Korea(North Korea),
                    Paraguay, Occupied Palestinian Territory, Russian Federation, Saudi Arabia, Sudan, Sierra Leone, El Salvador, Somalia, Serbia, South Sudan,
                    Sao Tome and Principe, Suriname, Seychelles, Syrian Arab Republic, Turks and Caicos Islands, Chad, Thailand, Tajikistan, Turkmenistan, Trinidad
                    Nida and Tobago, Tunisia, Tuvalu, United Republic of Tanzania, Uganda, Ukraine, Uzbekistan, Venezuela, British Virgin Islands, Vietnam, Vanuatu,
                    Wallis and Futuna, Samoa, Kosovo, Yemen, Zimbabwe
                </div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "foreignBank (established and operated outside the United States)">
                        <#if regulatory.whether == true>
                            <#assign info = regulatory.infoDetails ?eval>
                             <#if info ?? && info.min1 == true>
                                YES
                             <#else >
                                 NO
                             </#if>
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Is the firm operating under an "offshore banking license"?</div>
                <div>公司是否持有“离岸银行许可”运营？*</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "foreignBank (established and operated outside the United States)">
                        <#if regulatory.whether == true>
                            <#assign info = regulatory.infoDetails ?eval>
                            <#if info ?? && info.min2 == true>
                                YES
                            <#else >
                                NO
                            </#if>
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
<!--        <tr>-->
<!--            <td>The firm certifies that it will immediately contact MOF Securities, in writing, via an email in the event that the </td>-->
<!--            <td style="text-align: center;">YES</td>-->
<!--        </tr>-->
    </table>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <div>The firm certifies that it will immediately contact MOF Securities, in writing, via an email in the event that theOrganization begins
                    to operate under a banking license that？</div>
                <div>公司保证如果开始使用下列银行执照运营，将立即通过邮件以书面的形式联系国瑞证券？*</div>
            </td>
            <td style="text-align: center;width: 50px;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "foreignBank (established and operated outside the United States)">
                        <#if regulatory.whether == true>
                            <#assign info = regulatory.infoDetails ?eval>
                            <#if info.min3 == true>
                                YES
                            <#else >
                                NO
                            </#if>
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Licensed securities broker or dealer？</div>
                <div>有牌照的证券经纪商或自营商？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "ALicensedSecuritiesBrokerOrDealer.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Licensed futures commission or futures broker？</div>
                <div>有牌照的期货佣金商或期货经纪商？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "ALicensedFuturesCommissionOrFuturesBroker.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Mutual funds, hedge funds, private equity or venture capital funds, commodity funds or any other type of formal pooled investment organization？</div>
                <div>共同基金、对冲基金、私募股权或创业基金、商品基金或任意其它类型的正式集合投资组织？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "mutual fund, hedge fund, private equity or venture fund, commodity fund or any other type of formal collective investment organization">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Licensed foreign exchange dealer/broker or currency dealer/broker?</div>
                <div>有牌照的外汇自营商/经纪商或货币自营商/经纪商？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "licensed foreign exchange dealer/broker or currency dealer/broker.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Licensed funds transfer agency?</div>
                <div>有牌照的资金转账机构？ </div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "a licensed fund transfer agency.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Employee benefits under the US Retirement Income Protection Regulations (ERISA) or regulated by a state?</div>
                <div>受美国职工退休所得保障条例(ERISA)约束或由某个州监管的员工福利计划？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "an employee benefit plan that is governed by the employee retirement income security act (ERISA) or regulated by a state">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Bank, bank holding company or savings loan agency regulated by the US federal government or the US state government?</div>
                <div>受美国联邦政府或美国州政府监管的银行、银行控股公司或储蓄贷款社？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "a bank, bank holding company, or savings and loan institution regulated by the federal or state governments of the United States.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Interchange dealer registered in the United States?</div>
                <div>在美国注册的互换交易商？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "a swap dealer registered in the United States.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Major swap participants registered in the US?</div>
                <div>在美国注册的主要互换参与者？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "major swap participants registered in the us">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>An insurance company regulated by a certain state in the United States.</div>
                <div>由美国某一州监管的保险公司？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "an insurance company regulated by a state in the United States.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Non-US insurance company regulated by regulatory agencies outside the United States.</div>
                <div>受美国以外的监管机构监管的非美国保险公司？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "non-us insurers regulated by regulators outside the us.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>A US government department or agency, a government department or agency of any US state, or a department or agency of any political division of any US state?</div>
                <div>美国政府部门或机构，美国任意州的政府部门或机构，或美国任意州的任意政治分区的部门或机构？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "a department or agency of the United States government, of any state of the United States, or of any political subdivision in any state of the United States.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Licensed securities or futures or foreign exchange trading advisor, investment manager or fund manager?</div>
                <div>有牌照的证券或期货或外汇交易顾问、投资经理或基金经理？</div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "licensed securities or futures or foreign exchange trading adviser, investment manager or fund manager.">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div>Is the stock of the organization traded on a regulated exchange?That is, whether the organization is a public shareholding entity?</div>
                <div>该组织的股票是否在受监管的交易所交易？即该组织是否为公众持股实体？</div>
                <br />
                <div>
                    <table border="1" cellpadding="0" cellspacing="0">
                        <tr>
                            <td style="width: 250px">Symbol代码</td>
                            <td>Exchange交易所</td>
                        </tr>
                        <tr>
                            <td>
                                <#list regulatorys as regulatory>
                                    <#if regulatory ?? && regulatory.infoType.dictValue == "are its shares traded on regulated exchanges?Is the organization a publicly held entity?">
                                        <#if regulatory.whether == true >
                                            <#assign info = regulatory.infoDetails ?eval>
                                            ${(info.jiaoyitiaomu15[0].jiaoyisuoDaima)!"N/A"}
                                        <#else >
                                            N/A
                                        </#if>
                                        <#break >
                                    </#if>
                                </#list>
                            </td>
                            <td>
                                <#list regulatorys as regulatory>
                                    <#if regulatory ?? && regulatory.infoType.dictValue == "are its shares traded on regulated exchanges?Is the organization a publicly held entity?">
                                        <#if regulatory.whether == true >
                                            <#assign info = regulatory.infoDetails ?eval>
                                            ${(info.jiaoyitiaomu15[0].jiaoyisuoName)!"N/A"}
                                        <#else >
                                            N/A
                                        </#if>
                                        <#break >
                                    </#if>
                                </#list>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <#list regulatorys as regulatory>
                                    <#if regulatory ?? && regulatory.infoType.dictValue == "are its shares traded on regulated exchanges?Is the organization a publicly held entity?">
                                        <#if regulatory.whether == true >
                                            <#assign info = regulatory.infoDetails ?eval>
                                            ${(info.jiaoyitiaomu15[1].jiaoyisuoDaima)!"N/A"}
                                        <#else >
                                            N/A
                                        </#if>
                                        <#break >
                                    </#if>
                                </#list>
                            </td>
                            <td>
                                <#list regulatorys as regulatory>
                                    <#if regulatory ?? && regulatory.infoType.dictValue == "are its shares traded on regulated exchanges?Is the organization a publicly held entity?">
                                        <#if regulatory.whether == true >
                                            <#assign info = regulatory.infoDetails ?eval>
                                            ${(info.jiaoyitiaomu15[1].jiaoyisuoName)!"N/A"}
                                        <#else >
                                            N/A
                                        </#if>
                                        <#break >
                                    </#if>
                                </#list>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <#list regulatorys as regulatory>
                                    <#if regulatory ?? && regulatory.infoType.dictValue == "are its shares traded on regulated exchanges?Is the organization a publicly held entity?">
                                        <#if regulatory.whether == true >
                                            <#assign info = regulatory.infoDetails ?eval>
                                            ${(info.jiaoyitiaomu15[2].jiaoyisuoDaima)!"N/A"}
                                        <#else >
                                            N/A
                                        </#if>
                                        <#break >
                                    </#if>
                                </#list>
                            </td>
                            <td>
                                <#list regulatorys as regulatory>
                                    <#if regulatory ?? && regulatory.infoType.dictValue == "are its shares traded on regulated exchanges?Is the organization a publicly held entity?">
                                        <#if regulatory.whether == true >
                                            <#assign info = regulatory.infoDetails ?eval>
                                            ${(info.jiaoyitiaomu15[2].jiaoyisuoName)!"N/A"}
                                        <#else >
                                            N/A
                                        </#if>
                                        <#break >
                                    </#if>
                                </#list>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "are its shares traded on regulated exchanges?Is the organization a publicly held entity?">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
    </table>
    <br />
    <br />
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <div class="valueTd">
                    Does the institution need to be registered with the Commodity Futures Trading Commission in accordance with Article 1101 of the US Futures
                    Association (NFA), or registered with the Canadian Association of Investment Brokers (IDA), or registered as a futures agent in any other
                    country,introducing brokers , commodity fund manager, commodity trading advisor, retail forex trader or leveraged trader?
                </div>
                <div class="valueTd">
                    机构是否需要按照美国期货协会(NFA)第1101条规定，在商品期货交易委员会注册，或在加拿大投资证券商协会(IDA)注册，或者在其他任一国，注册为期货代理商，介绍经纪商，
                    商品基金经理，商品交易顾问，零售外汇交易商或杠杆交易商？
                </div>
                <br />
                <div>Provide details提供详情*</div>
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr style="width: 100%;">
                        <td>
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether institutions need in accordance with article 1101 of the us futures association (NFA), registered in the commodity futures trading commission, or investment securities association (IDA) registered in Canada, or in other foreign registered as futures agents, brokers, commodity fund managers and commodity trading advisors, retail traders or leveraged trader?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.xiangqing)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                </table>
                <br />
            </td>
            <td style="width: 50px;text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "whether institutions need in accordance with article 1101 of the us futures association (NFA), registered in the commodity futures trading commission, or investment securities association (IDA) registered in Canada, or in other foreign registered as futures agents, brokers, commodity fund managers and commodity trading advisors, retail traders or leveraged trader?">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div class="valueTd">is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the
                    exchange?</div>
                <div>该机构是否为交易所、监管/自律监管机构成员，或身为交易所成员的相关或关联人？</div>
                <br />
                <div>Name of the exchange(s) 交易所名称*</div>
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr style="width: 100%;">
                        <td>
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.jiaoyisuoName)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                </table>
                <br />
                <div>Provide details提供详情*</div>
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr style="width: 100%;">
                        <td>
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.xiangqing)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                </table>
                <br />
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div class="valueTd">Whether the organization has a person in charge, a manager, an authorized trader, or other person listed in the account
                    has been arrested or convicted of a criminal act?</div>
                <div>机构是否有负责人、管理人员、授权交易者或账户中列出的其它人士曾因犯罪行为被逮捕或获罪？</div>
                <br />
                <div>Provide details提供详情*</div>
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr style="width: 100%;">
                        <td>
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "has any principal, officer, authorized trader or other person listed in the account been arrested or convicted of a crime?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.xiangqing)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                </table>
                <br />
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "has any principal, officer, authorized trader or other person listed in the account been arrested or convicted of a crime?">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
        <tr>
            <td>
                <div class="valueTd">Whether the account holder or any of its officials is a listed company: i) a director, ii) a shareholder holding a 10% share, or iii) a
                    decision maker?</div>
                <div>账户持有者或任何其官员是否为上市公司的：i)董事，ii)持有10%股份的股东，或iii)决策人员？</div>
                <br />
                <div>the stock symbol(s) of the company or companies公司股票代码</div>
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr style="width: 100%;">
                        <td>
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "are the account holders or any of their officers: I) directors of the listed company, ii) shareholders holding 10% of the shares, or iii) decision makers?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.daima)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                </table>
                <br />
            </td>
            <td style="text-align: center;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "are the account holders or any of their officers: I) directors of the listed company, ii) shareholders holding 10% of the shares, or iii) decision makers?">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
    </table>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <div>Are any responsible persons or authorized traders of the institution a registered broker; employees, directors or owners of securities or commodity
                    brokerage companies; employees of banks, hedge funds or other financial services companies?</div>
                <div>该机构的任何负责人或授权交易员是否为注册经纪商；证券或商品经纪公司的雇员、董事或所有人；银行、对冲基金或其他金融服务公司的雇员？</div>
                <br />
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="width: 160px;">
                            <div>Company Name</div>
                            <div>公司名称</div>
                        </td>
                        <td class="valueTd">
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.guzhuXiang)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Company Country</div>
                            <div>公司所在国家/地区</div>
                        </td>
                        <td class="valueTd" >
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.state)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Company Address</div>
                            <div>公司所在地址</div>
                        </td>
                        <td class="valueTd" style="height: 73px">
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.site + ","+info.site1)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Company City</div>
                            <div>公司所在城市</div>
                        </td>
                        <td class="valueTd">
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.city)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Company State/Province</div>
                            <div>公司所在省/市</div>
                        </td>
                        <td class="valueTd">
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.province)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Company Zip/Postal Code</div>
                            <div>公司邮政编码</div>
                        </td>
                        <td class="valueTd">
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.postalCode)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Company Email Address</div>
                            <div>邮箱地址</div>
                        </td>
                        <td class="valueTd">
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.postalAddress)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Phone Number</div>
                            <div>电话号码</div>
                        </td>
                        <td class="valueTd">
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.phoneNumber)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Person's Name</div>
                            <div>注册人姓名</div>
                        </td>
                        <td class="valueTd">
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.zhuceName)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Person's Affiliation / Relationship with Organization</div>
                            <div>注册人与机构的关联/关系</div>
                        </td>
                        <td class="valueTd">
                            <#list regulatorys as regulatory>
                                <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                                    <#if regulatory.whether == true >
                                        <#assign info = regulatory.infoDetails ?eval>
                                        ${(info.zhuceGuanxi)!"N/A"}
                                    <#else >
                                        N/A
                                    </#if>
                                    <#break >
                                </#if>
                            </#list>
                        </td>
                    </tr>
                </table>
            </td>
            <td style="text-align: center;width: 50px;">
                <#list regulatorys as regulatory>
                    <#if regulatory ?? && regulatory.infoType.dictValue == "whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?">
                        <#if regulatory.whether == true >
                            YES
                        <#else >
                            NO
                        </#if>
                        <#break >
                    </#if>
                </#list>
            </td>
        </tr>
    </table>
    <h4>8. Account Financial Background 账户财务背景</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <div style="width: 200px">Annual Net Income in USD</div>
                <div class="valueTd">
                        年度净收入（USD）
                </div>
            </td>
            <td colspan="4">
                <div>
                    ${(assets[0].anualNetIncome)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Net Worth in USD</div>
                <div class="valueTd">
                    资产净值（USD）
                </div>
                </td>
            <td colspan="4">
                <div>
                    ${(assets[0].netAssetValue)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Liquid Net Worth in USD</div>
                <div class="valueTd">
                    流动资产净值（USD）
                </div>
            </td>
            <td colspan="4">
                <div>
                    ${(assets[0].netCurrentAssets)!"N/A"}
                </div>
            </td>
        </tr>
            <!--        投资目标-->
        <tr style="height: 41px;">
            <td colspan="5">
                <div>Investment Objectives and Intended Purpose of Trading 投资目标及交易意图</div>
                <div class="valueTd">
                    <#if investmentTargets ??>
                        <#if (investmentTargets[0].investmentTarget)! ??>
                            <#list (investmentTargets[0].investmentTarget)! as i>
                                ${(i.dictDest)!"N/A"}
                                <#if i_has_next>,</#if>
                            </#list>
                        </#if>
                    </#if>
                </div>
            </td>
        </tr>
    </table>
    <br />
    <br />
    <br />
    <br />
    <br />
    <h4 style="margin-top: 24.5px;">9. Investment Experience 投资经验</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <!--交易许可与经验-->
        <tr>
            <td></td>
            <td>
                <div>Knowledge Level</div>
                <div class="valueTd">
                    知识水平
                </div>
            </td>
            <td>
                <div>Trading Experience</div>
                <div class="valueTd">
                    交易经验
                </div>
            </td>
            <td>
                <div>Number of transactions per year</div>
                <div class="valueTd">
                    每年交易笔数
                </div>
            </td>
        </tr>
        <tr>
            <td style="width: 130px">
                <div>Stocks</div>
                <div class="valueTd">
                    股票
                </div>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Stock")!>
                            ${(tradeExp.knowledgeLevel.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Stock")!>
                            ${(tradeExp.tradeExperience.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Stock")!>
                            ${(tradeExp.yearTransactionsPerNumber.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <td>
                <div>Bonds</div>
                <div class="valueTd">
                    债券
                </div>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Bond")!>
                            ${(tradeExp.knowledgeLevel.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Bond")!>
                            ${(tradeExp.tradeExperience.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Bond")!>
                            ${(tradeExp.yearTransactionsPerNumber.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
        </tr>

        <tr>
            <td>
                <div>Options</div>
                <div class="valueTd">
                    期权
                </div>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Options")!>
                            ${(tradeExp.knowledgeLevel.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Options")!>
                            ${(tradeExp.tradeExperience.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Options")!>
                            ${(tradeExp.yearTransactionsPerNumber.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <td style="height: 42px;">
                <div>Futures</div>
                <div class="valueTd">
                    期货
                </div>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Futures")!>
                            ${(tradeExp.knowledgeLevel.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Futures")!>
                            ${(tradeExp.tradeExperience.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "Futures")!>
                            ${(tradeExp.yearTransactionsPerNumber.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <td>
                <div>Futures Opetions</div>
                <div class="valueTd">
                    期货期权
                </div>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "ForwardOption")!>
                            ${(tradeExp.knowledgeLevel.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "ForwardOption")!>
                            ${(tradeExp.tradeExperience.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
            <td>
                <#if tradeExps ??>
                    <#list tradeExps as tradeExp>
                        <#if (tradeExp.tansactionType.dictValue == "ForwardOption")!>
                            ${(tradeExp.yearTransactionsPerNumber.dictDest)!}
                            <#break>
                        </#if>
                    </#list>
                </#if>
            </td>
        </tr>
    </table>
    <h4 style="margin-top: 5px;">10. Account Trading Permissions 账户交易许可</h4>
    <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td style="width: 130px">
                    <div>Stocks</div>
                    <div class="valueTd">股票</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "Stock")!>
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>Bonds</div>
                    <div class="valueTd">债券</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "Bond")!>
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>Warrants</div>
                    <div class="valueTd">权证</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "Warrant")!>
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>Options</div>
                    <div class="valueTd">期权</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "Options")!>
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>Futures</div>
                    <div class="valueTd">期货</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "Futures")!>
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>Futures Opetions</div>
                    <div class="valueTd">期货期权</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "ForwardOption")!>
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>Single Stock Futures</div>
                    <div class="valueTd">个股期货</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "SingleStockFutures")!>
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>Mutual Funds</div>
                    <div class="valueTd">共同基金</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "MutualFund")!>
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>NextShares</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "NextShares")!>
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>Currency Conversion</div>
                    <div class="valueTd">货币转换</div>
                </td>
                <td>
                    <#if tradeExps ??>
                        <#list tradeExps as tradeExp>
                            <#if (tradeExp.tansactionType.dictValue == "CurrencyConverter")!"N/A">
                                <#list (tradeExp.tradingLocation)! as i>
                                    ${(i.tansactionLocation)!"N/A"}
                                    <#if i_has_next>,</#if>
                                </#list>
                                <#break>
                            </#if>
                        </#list>
                    </#if>
                </td>
            </tr>
    </table>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <h4>11.Managerial Authority 经营负责人</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>Salutation称呼</td>
            <td colspan="3">
                ${(beneficiaryOwner.operationPrincipalList[0].accountCoPersonal.appellation.dictDest)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>
                <div>Last Name姓</div>
                <div>
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoPersonal.lastName)!"N/A"}
                </div>
            </td>
            <td style="width: 200px;">
                <div>Middle Name(s)中间名*</div>
                <div>
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoPersonal.middleName)!"N/A"}
                </div>
            </td>
            <td style="width: 200px;">
                <div>First Name名</div>
                <div>
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoPersonal.firstName)!"N/A"}
                </div>
            </td>
            <td style="width: 200px;">
                <div>Suffix后缀*</div>
                <div>
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoPersonal.suffix)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Position职位</div>
                <div>
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoPersonal.position)!"N/A"}
                </div>
            </td>
            <td colspan="2">
                <div>Date of Birth出生日期</div>
                <div>
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoPersonal.birthday)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div>Residential Address居住地址</div>
                <div class="valueTd" style="height: 64px;">
                    <#list beneficiaryOwner.operationPrincipalList[0].accountCoAddressList as address>
                        <#if (address.addressType) ?? && address.addressType == "personalAddress">
                            ${(address.address +","+address.city +""+address.rovince)!"N/A"}
                            <#break >
                        </#if>
                    </#list>
<#--                    <br />-->
<#--                    <br />-->
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Country 国家</div>
                <div>
                    <#list beneficiaryOwner.operationPrincipalList[0].accountCoAddressList as address>
                        <#if (address.addressType) ?? && address.addressType == "personalAddress">
                            ${(address.country.enName )!"N/A"}
                            <#break >
                        </#if>
                    </#list>
                </div>
            </td>
            <td colspan="2">
                <div>Zip/Postal Code邮政编码</div>
                <div>
                    <#list beneficiaryOwner.operationPrincipalList[0].accountCoAddressList as address>
                        <#if (address.addressType) ?? && address.addressType == "personalAddress">
                            ${(address.zipCode)!"N/A"}
                            <#break >
                        </#if>
                    </#list>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Country of Citizenship (if different from above)*</div>
                <div>国籍与上方国家不同*</div>
                <div class="valueTd" style="height: 18px">
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoAddressList[0].otherCountry.enName)!"NO"}
                </div>
            </td>
            <td colspan="2">
                <div>Country of Birth (if different from above)*</div>
                <div>出生国与上方国家不同*</div>
                <div class="valueTd" style="height: 18px">
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoAddressList[0].otherBirthCountry.enName)!"NO"}
                </div>
            </td>
        </tr>
        <tr>
<#--            <td colspan="2">-->
<#--                <div>If you are a U.S. citizen, a U.S. resident or have a U.S Social Security Number, you must provide a Social Security Number</div>-->
<#--                <div>如果您是美国公民、美国居民或拥有美国社会保险号码，请提供社会保险号码：</div>-->
<#--            </td>-->
            <td colspan="4">
                <div>If you are not a U.S. resident, please check which form of identification you will provide to Interactive Brokers (you must provide a copy
                of one of the following):</div>
                <div>如果您不是美国居民，请填写下面的的身份证明</div>
            </td>
        </tr>
        <tr>
<#--            <td rowspan="3" colspan="2">-->
<#--                <div>Number号码</div>-->
<#--            </td>-->
            <td colspan="4">
                <div>Identification Type 证件类型</div>
                <div class="valueTd" style="height: 36px;">
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoIdentityList[0].idCardType.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div>Country of Issuance 签发国家</div>
                <div class="valueTd">
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoIdentityList[0].issuingCountry.enName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div>Number 号码</div>
                <div class="valueTd">
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoIdentityList[0].idNumber)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">Tax Residency税务居住地</td>
        </tr>
        <tr>
            <td style="width: 260px;">
                <div>Country</div>
                <div class="valueTd">
                    国家地区
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoTaxesList[0].taxResidenceCountry.enName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Do you have a Tax Identification Number? </div>
                <div class="valueTd">
                    您有没有纳税识别号？
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    <#if (beneficiaryOwner.operationPrincipalList[0].accountCoTaxesList[0].taxResidenceIdCardNumber) ?? && beneficiaryOwner.operationPrincipalList[0].accountCoTaxesList[0].taxResidenceIdCardNumber !="N.A." >
                        YES
                    <#else >
                        NO
                    </#if>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Tax Identification Number</div>
                <div class="valueTd">
                    纳税识别号
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoTaxesList[0].taxResidenceIdCardNumber)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Reason</div>
                <div class="valueTd">
                    理由
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(beneficiaryOwner.operationPrincipalList[0].accountCoTaxesList[0].noTaxIdentificationNumber.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
    </table>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />

        <#assign x = 0>
        <#assign y = "">
        <#list shareholder as per>
            <#assign x = x + 1>
<#--            <h4 style="margin-top: 11px">12. Owner Information 股东信息</h4>-->
<#--    <table border="1" cellpadding="0" cellspacing="0">-->
            <#if x == 10>
                <#break>
            </#if>
            <#switch x>
                <#case 1>
                    <#assign y = "First">
                    <#break>
                <#case 2>
                <#assign y = "Second">
                    <#break>
                <#case 3>
                <#assign y = "Third">
                    <#break>
                <#case 4>
                <#assign y = "Fourth">
                    <#break>
                <#case 5>
                <#assign y = "Fifth">
                    <#break>
                <#case 6>
                <#assign y = "Sixth">
                    <#break>
                <#case 7>
                <#assign y = "Seventh">
                    <#break>
                <#case 8>
                <#assign y = "Eighth">
                    <#break>
                <#case 9>
                <#assign y = "Ninth">
                    <#break>
                <#case 10>
                <#assign y = "Tenth">
                    <#break>
            </#switch>
            <h4 style="margin-top: 11px">12. Owner Information 股东信息</h4>
            <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="4">
                <div>${y} 10% or Greater Shareholder (individual or entity)</div>
                <div>第${x}个10%以上的股东（个人或实体）</div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div>Owner's Name 所有人名称</div>
                <div style="height: 36px;">
                    <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">
                        ${(per.accountCoPersonal.entityName)!"N/A"}
                    <#else >
                        ${(per.accountCoPersonal.lastName)!"N/A"}
                        ${(per.accountCoPersonal.middleName)!}
                        ${(per.accountCoPersonal.firstName)!}
                        ${(per.accountCoPersonal.suffix)!}
                    </#if>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div>Percentage of Ownership 所有权百分比</div>
                <div>
                    ${(per.accountCoPersonal.prcentageOfOwnership +"%")!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div>Address 地址</div>
                <div style="height: 36px;">
                    <#list per.accountCoAddressList as address>
                        <#if (address.addressType) ?? && address.addressType == "personalAddress">
                            ${(address.address)!"N/A"}
                            <#break >
                        </#if>
                    </#list>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>City 城市</div>
                <div class="valueTd">
                    <#list per.accountCoAddressList as address>
                        <#if (address.addressType) ?? && address.addressType == "personalAddress">
                            ${(address.city)!"N/A"}
                            <#break >
                        </#if>
                    </#list>
                </div>
            </td>
            <td colspan="2">
                <div>State/Province 省/市</div>
                <div class="valueTd">
                    <#list per.accountCoAddressList as address>
                        <#if (address.addressType) ?? && address.addressType == "personalAddress">
                            ${(address.rovince)!"N/A"}
                            <#break >
                        </#if>
                    </#list>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Country 国家</div>
                <div class="valueTd">
                    <#list per.accountCoAddressList as address>
                        <#if (address.addressType) ?? && address.addressType == "personalAddress">
                            ${(address.country.enName)!"N/A"}
                            <#break >
                        </#if>
                    </#list>
                </div>
            </td>
            <td colspan="2">
                <div>Zip/Postal Code 邮政编码</div>
                <div class="valueTd">
                    <#list per.accountCoAddressList as address>
                        <#if (address.addressType) ?? && address.addressType == "personalAddress">
                            ${(address.zipCode)!"N/A"}
                            <#break >
                        </#if>
                    </#list>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3">Contact Information联系信息</td>
        </tr>
        <tr>
            <td style="width:200px;">Type手机类型</td>
            <td style="width:200px; ">Country国家</td>
            <td>Number电话号码</td>
        </tr>
        <tr>
            <td>${(per.accountCoContactList[0].contactNumberType.dictDest)!"N/A"}</td>
            <td>${(per.accountCoContactList[0].country.enName)!"N/A"}</td>
            <td>${(per.accountCoContactList[0].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td>${(per.accountCoContactList[1].contactNumberType.dictDest)!"N/A"}</td>
            <td>${(per.accountCoContactList[1].country.enName)!"N/A"}</td>
            <td>${(per.accountCoContactList[1].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td>${(per.accountCoContactList[2].contactNumberType.dictDest)!"N/A"}</td>
            <td>${(per.accountCoContactList[2].country.enName)!"N/A"}</td>
            <td>${(per.accountCoContactList[2].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td>${(per.accountCoContactList[3].contactNumberType.dictDest)!"N/A"}</td>
            <td>${(per.accountCoContactList[3].country.enName)!"N/A"}</td>
            <td>${(per.accountCoContactList[3].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td>${(per.accountCoContactList[4].contactNumberType.dictDest)!"N/A"}</td>
            <td>${(per.accountCoContactList[4].country.enName)!"N/A"}</td>
            <td>${(per.accountCoContactList[4].contactNumber)!"N/A"}</td>
        </tr>
        <tr>
            <td colspan="4">Tax Residency税务居住地</td>
        </tr>
        <tr>
            <td style="width: 260px;">
                <div>Country</div>
                <div class="valueTd">
                    国家地区
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(per.accountCoTaxesList[0].taxResidenceCountry.enName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Do you have a Tax Identification Number? </div>
                <div class="valueTd">
                    您有没有纳税识别号？
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    <#if (per.accountCoTaxesList[0].taxResidenceIdCardNumber) ?? && per.accountCoTaxesList[0].taxResidenceIdCardNumber !="N.A." >
                        YES
                    <#else >
                        NO
                    </#if>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Tax Identification Number</div>
                <div class="valueTd">
                    纳税识别号
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(per.accountCoTaxesList[0].taxResidenceIdCardNumber)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Reason</div>
                <div class="valueTd">
                    理由
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(per.accountCoTaxesList[0].noTaxIdentificationNumber.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
    </table>
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <table border="1" cellpadding="0" cellspacing="0" style="margin-bottom: 101px;">
        <tr>
            <td colspan="4">
                <div>${y} 10% or Greater Shareholder (individual or entity)</div>
                <div>第${x}个10%以上的股东（个人或实体）</div>
            </td>
        </tr>
        <tr>
            <td colspan="4"><strong>(if an individual) 如是个人</strong></td>
        </tr>
        <tr>
            <td style="width: 170px;height: 36.5px">
                <div>Date of Birth</div>
                <div>出生日期</div>
            </td>
            <td colspan="3">
                <#if per.accountCoPersonal.personalType.dictValue != "legalPersonShareholder">
                    ${(per.accountCoPersonal.birthday)!"N/A"}
                <#else >
                    N/A
                </#if>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Country of Citizenship (if different from above)</div>
                <div>国籍与上方国家不同</div>
            </td>
            <td colspan="2" class="valueTd">
                <#if per.accountCoPersonal.personalType.dictValue != "legalPersonShareholder">
                    ${(per.accountCoAddressList[0].otherCountry.enName)!"NO"}
                <#else >
                    N/A
                </#if>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Country of Birth (if different from above)</div>
                <div>出生国与上方国家不同</div>
            </td>
            <td colspan="2" class="valueTd">
                <#if per.accountCoPersonal.personalType.dictValue != "legalPersonShareholder">
                    ${(per.accountCoAddressList[0].otherBirthCountry.enName)!"NO"}
                <#else >
                    N/A
                </#if>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div>If shareholder is an individual and is not a U.S. resident, please check which form of identification owner uses:</div>
                <div>如果是个人股东且非美国居民，请补充身份证明</div>
                <br />
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="width: 150px">
                            <div>Identification Type </div>
                            <div>证件类型</div>
                        </td>
                        <td colspan="3" class="valueTd">
                            <#if per.accountCoPersonal.personalType.dictValue != "legalPersonShareholder">
                                ${(per.accountCoIdentityList[0].idCardType.dictDest)!"N/A"}
                            <#else >
                                N/A
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div>Country of Issuance</div>
                            <div>签发国家</div>
                        </td>
                        <td style="width: 150px">
                            <div class="valueTd">
                                <#if per.accountCoPersonal.personalType.dictValue != "legalPersonShareholder">
                                    ${(per.accountCoIdentityList[0].issuingCountry.enName)!"N/A"}
                                <#else >
                                    N/A
                                </#if>
                            </div>
                        </td>
                        <td style="width: 150px">
                            <div>Identification Number</div>
                            <div>证件号码</div>
                        </td>
                        <td>
                            <div class="valueTd">
                                <#if per.accountCoPersonal.personalType.dictValue != "legalPersonShareholder">
                                    ${(per.accountCoIdentityList[0].idNumber)!"N/A"}
                                <#else >
                                    N/A
                                </#if>
                            </div>
                        </td>
                    </tr>
                </table>
                <br />
            </td>
        </tr>
        <tr>
            <td colspan="4"><strong>(if an entity) 如是实体</strong></td>
        </tr>
        <tr>
            <td>
                <div>Country of Formation</div>
                <div>成立国家</div>
            </td>
            <td colspan="3">
                <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">
                    ${(per.accountCoContactList[0].foundingCountry.enName)!"N/A"}
                <#else >
                    N/A
                </#if>
            </td>
        </tr>
        <tr>
            <td colspan="4">Signatory Information 签署方信息</td>
        </tr>
        <tr>
            <td>Salutation称呼</td>
            <td colspan="3">
                <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">
                    ${(per.accountCoPersonal.appellation.dictDest)!"N/A"}
                <#else >
                    N/A
                </#if>
            </td>
        </tr>
        <tr>
            <td style="width: 170px;">
                <div>Last Name姓</div>
                <div style="height: 24px;">
                    <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">
                        ${(per.accountCoPersonal.lastName)!"N/A"}
                    <#else >
                        N/A
                    </#if>
                </div>
            </td>
            <td style="width: 170px;">
                <div>Middle Name(s)中间名*</div>
                <div>
                    <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">
                        ${(per.accountCoPersonal.middleName)!"N/A"}
                    <#else >
                        N/A
                    </#if>
                </div>
            </td>
            <td style="width: 170px;">
                <div>First Name名</div>
                <div>
                    <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">
                        ${(per.accountCoPersonal.firstName)!"N/A"}
                    <#else >
                        N/A
                    </#if>
                </div>
            </td>
            <td>
                <div>Suffix后缀*</div>
                <div>
                    <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">
                        ${(per.accountCoPersonal.suffix)!"N/A"}
                    <#else >
                        N/A
                    </#if>
                </div>
            </td>
        </tr>
        <tr>
<#--            <td>-->
<#--                <div>Date of Birth出生日期</div>-->
<#--                <div style="height: 24px;">-->
<#--                    <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">-->
<#--                        ${(per.accountCoPersonal.birthday?number_to_datetime?string('MM-dd-yyyy'))!"N/A"}-->
<#--                    <#else >-->
<#--                        N/A-->
<#--                    </#if>-->
<#--                </div>-->
<#--            </td>-->
            <td colspan="4">
                <div>Email Address邮箱地址</div>
                <div style="height: 24px;">
                    <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">
                        ${(per.accountCoPersonal.email)!"N/A"}
                    <#else >
                        N/A
                    </#if>
                </div>
            </td>
<#--            <td colspan="2">-->
<#--                <div>Country of Legal Residence居住国家</div>-->
<#--                <div>-->
<#--                    <#list per.accountCoAddressList as address>-->
<#--                        <#if (address.addressType) ?? && address.addressType == "mail">-->
<#--                            ${(address.country.enName )!"N/A"}-->
<#--                            <#break >-->
<#--                        </#if>-->
<#--                        <#if address_has_next==false>N/A</#if>-->
<#--                    </#list>-->
<#--                </div>-->
<#--            </td>-->
        </tr>
<#--        <tr>-->
<#--            <td>-->
<#--                <div>Identification Type </div>-->
<#--                <div>证件类型</div>-->
<#--            </td>-->
<#--            <td colspan="3" class="valueTd">-->
<#--                <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">-->
<#--                    ${(per.accountCoIdentityList[0].idCardType.dictDest)!"N/A"}-->
<#--                <#else >-->
<#--                    N/A-->
<#--                </#if>-->
<#--            </td>-->
<#--        </tr>-->
<#--        <tr>-->
<#--            <td>-->
<#--                <div>Country of Issuance</div>-->
<#--                <div>签发国家</div>-->
<#--            </td>-->
<#--            <td class="valueTd">-->
<#--                <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">-->
<#--                    ${(per.accountCoIdentityList[0].issuingCountry.enName)!"N/A"}-->
<#--                <#else >-->
<#--                    N/A-->
<#--                </#if>-->
<#--            </td>-->
<#--            <td>-->
<#--                <div>Identification Number</div>-->
<#--                <div>证件号码</div>-->
<#--            </td>-->
<#--            <td class="valueTd">-->
<#--                <#if per.accountCoPersonal.personalType.dictValue == "legalPersonShareholder">-->
<#--                    ${(per.accountCoIdentityList[0].idNumber)!"N/A"}-->
<#--                <#else >-->
<#--                    N/A-->
<#--                </#if>-->
<#--            </td>-->
<#--        </tr>-->
        <tr>
            <td colspan="3">
                <div>Is the entity publicly traded on a regulated exchange?</div>
                <div>实体是否在监管交易所公开上市交易？</div>
                <br />
                <div>If yes, what Exchange 如果是，请填写交易所</div>
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="width: 100%;">
                            ${(per.accountCoContactList[0].exchange)!"N/A"}
                        </td>
                    </tr>
                </table>
                <br />
                <div>Symbol 代码</div>
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="width: 100%;">
                            ${(per.accountCoContactList[0].code)!"N/A"}
                        </td>
                    </tr>
                </table>
                <br />
            </td>
            <td>
                <#if per.accountCoContactList[0].status ?? && per.accountCoContactList[0].status == true>
                    YES
                <#else >
                    NO
                </#if>
            </td>
        </tr>
    </table><br />

        </#list>
    <br /><br /><br /><br /><br /><br />
    <!--    协议披露-->
    <h4>13. CONFIRMATIONS AND ACKNOWLEDGEMENTS 确认及承认</h4>
    <div style="line-height: 20px;padding-left: 12px;">
        <div>a)	Day Trading Risk Disclosure Statement</div>
        <div>b)	Hong Kong Risk Disclosure</div>
        <div>c)	Certification Regarding Trading Control and Ownership of the Account</div>
        <div>d) Risks of After-Hours Trading</div>
        <div>e) US Stock Stop Order Disclosure</div>
        <div>f) Essential Legal Terms for your MOF Account</div>
        <div>g) Portfolio Margin Risk Disclosure</div>
        <div>h) Customer Agreement Institutional Services</div>
        <br />
        <div>a) 日内交易风险披露声明</div>
        <div>b) 香港风险披露</div>
        <div>c) 账户交易控制及所有权证明</div>
        <div>d) 市后交易风险</div>
        <div>e) 美国股票止损定单披露</div>
        <div>f) 有关您MOF账户的重要法律条款</div>
        <div>g) 投资组合保证金风险披露</div>
        <div>h) 机构协议</div>
        <br />
        <br />
        <div style="font-weight: bolder">I have read and agree to the above agreement documents.</div>
        <div style="font-weight: bolder">本人已阅读并同意以上协议文件</div>
    </div>
    <br />
    <br />
    <div style="font-weight: bold;margin-bottom: 5px;">Any interpretation of the language in this application is governed by the English version.</div>
    <div style="font-weight: bold;">本申请表格的诠释概以英文版为准。</div>
    <br />
    <br />
    <br />
    <br />
    <h4>Signature(s) and Date(s) Required 签署及日期（必须填写）</h4>
    <br />
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td width="40%">
                <div class="deLine"></div>
                <div>Account Holder Signature</div>
                <div>账户持有人签署</div>
            </td>
            <td width="40%">
                <div class="deLine"></div>
                <div>Print Name</div>
                <div>请以正楷填写姓名</div>
            </td>
            <td>
                <div class="deLine"></div>
                <div>Date(mm/dd/yyyy)</div>
                <div>日期(月/日/年)</div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>