
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
            margin: 100.0pt 1.27cm 100pt 1.27cm;
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
            margin-top: 0px;
        }
        .deLine{
            height: 25px;
            line-height: 25px;
            border-bottom: 1px solid #000;
        }

    </style>
</head>
<body>
<div id="header">
<!--    <div id="header-right" class="header-right" style="text-align: right">-->
<!--        <span>-->
<!--            <div>Page <span id="pages"> of </span></div>-->
<!--            <div>第 <span id="pages">页，共 </span>页</div>-->
<!--        </span>-->
<!--    </div>-->
    <div id="header-left" class="header-left">
        <img src="data:${fileType};base64,${file64Str}" width="150px" style="display: inline-block" />
        <span style="margin-left: 20px;text-align: right;float: right">
            <div>MOF Securities, Ltd.</div>
            <div>Individual Account Application</div>
            <div>个人账户申请表</div>
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
    <h4>1. Account Information账户信息</h4>
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

    <h4>2. Personal Information 个人信息</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <div>Salutation称呼</div>
            </td>
            <td colspan="3">
                <div>
                    ${(perInfos[0].appellation.dictDest)!}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Last Name姓</div>
                <div class="valueTd">   ${(perInfos[0].lastName)!"N/A"}</div>
            </td>
            <td>
                <div>First Name名</div>
                <div class="valueTd"> ${(perInfos[0].firstName)!"N/A"}</div>
            </td>
            <td style="width: 100px;">
                <div>Gender性别</div>
                <div class="valueTd"> ${(perInfos[0].gender.dictDest)!"N/A"}</div>
            </td>
            <td style="width: 160px">
                <div>Marital Status婚姻状况</div>
                <div class="valueTd"> ${(perInfos[0].maritalStatus.dictDest)!"N/A"}</div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Date of Birth出生日期</div>
                <div class="valueTd">
                    ${(perInfos[0].birthday)!"N/A"}
                </div>
            </td>
            <td colspan="2">
                <div>Number of Dependents家庭成员数目</div>
                <div class="valueTd">
                    ${(perInfos[0].numberOfFamilyMembers.dictDest)!"N/A"}
                </div>
            </td>
        </tr>

        <!--        地址-->
        <tr>
            <td>
                <div>
                    Address
                </div>
                <div class="valueTd">
                    地址
                </div>
            </td>
            <td colspan="3">
                <div style="height: 50px" class="valueTd">
                    ${(personalAddress.address + ","+ personalAddress.city + ","+personalAddress.rovince + ","+personalAddress.country.enName )!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Zip/Postal Code</div>
                <div class="valueTd">
                    邮政编码
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(personalAddress.zipCode)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div style="width: 160px;">
                    Mailing address (if different from above)
                </div>
                <div class="valueTd">
                    邮寄地址*（与上方不同）
                </div>
            </td>
            <td colspan="3">
                <div style="height: 45px" class="valueTd">
                    ${(mailAddress.address + "," + mailAddress.city + "," +mailAddress.rovince + ","+mailAddress.country.enName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Zip/Postal Code</div>
                <div class="valueTd">
                    邮政编码
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(mailAddress.zipCode)!"N/A"}
                </div>
            </td>
        </tr>

        <!--国籍-->
        <tr>
            <td colspan="2">
                <div>Country of Citizenship (if different from above)*</div>
                <div class="valueTd">
                    国籍与上方国家不同*
                </div>
            </td>
            <td colspan="2">
                <!--                <div>Address地址*</div>-->
                <div class="valueTd">
                    ${(personalAddress.otherCountry.enName)!"N/A"}
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div>Country of Birth (if different from above)*</div>
                <div class="valueTd">
                    出生国与上方国家不同*
                </div>
            </td>
            <td colspan="2">
                <div class="valueTd">
                    ${(personalAddress.otherBirthCountry.enName)!"N/A"}
                </div>
            </td>
        </tr>

    <!--联系信息-->
        <tr>
            <td colspan="4">
                <div>Contact Information联系信息</div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Type手机类型</div>
            </td>
            <td>
                <div>Country国家</div>
            </td>
            <td colspan="2" class="valueTd">
                <div>Number电话号码</div>
            </td>
        </tr>
            <tr style="height: 24.5px">
                <td>
                    ${(contacts[0].contactNumberType.dictDest)!}
                </td>
                <td>
                    ${(contacts[0].country.enName)!}
                </td>
                <td colspan="2" class="valueTd">
                    ${(contacts[0].contactNumber)!}
                </td>
            </tr>
            <tr style="height: 24.5px">
                <td>
                    ${(contacts[1].contactNumberType.dictDest)!}
                </td>
                <td>
                    ${(contacts[1].country.enName)!}
                </td>
                <td colspan="2" class="valueTd">
                    ${(contacts[1].contactNumber)!}
                </td>
            </tr>
            <tr style="height: 24.5px">
                <td>
                    ${(contacts[2].contactNumberType.dictDest)!}
                </td>
                <td>
                    ${(contacts[2].country.enName)!}
                </td>
                <td colspan="2" class="valueTd">
                    ${(contacts[2].contactNumber)!}
                </td>
            </tr>
            <tr style="height: 24.5px">
            <td>
                ${(contacts[3].contactNumberType.dictDest)!}
            </td>
            <td>
                ${(contacts[3].country.enName)!}
            </td>
            <td colspan="2" class="valueTd">
                ${(contacts[3].contactNumber)!}
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div>Identification身份证明</div>
            </td>
        </tr>
        <tr>
            <td style="width: 200px;">
                <div>Country of Issuance签发国家</div>
            </td>
            <td>
                <div>Identification Type证件类型</div>
            </td>
            <td colspan="2">
                <div>Identification Number证件号码</div>
            </td>
        </tr>
        <tr>
            <td>
                ${(identity.issuingCountry.enName)!}
            </td>
            <td>
                ${(identity.idCardType.dictDest)!}
            </td>
            <td colspan="2" class="valueTd">
                ${(identity.idNumber)!}
            </td>
        </tr>
    </table>
    <!--  Residency税务居住地  -->
    <br />
    <h4>3. Tax Residency税务居住地</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td style="width: 180px;">
                <div>Country</div>
                <div class="valueTd">
                    国家地区
                </div>
            </td>
            <td colspan="3">
                <div class="valueTd">
                    ${(taxes[0].taxResidenceCountry.enName)!"N/A"}
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
                    <#if (taxes[0].taxResidenceIdCardNumber) ?? && taxes[0].taxResidenceIdCardNumber !="N.A." >
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
                    <#if taxes[0].taxResidenceIdCardNumber ?? && taxes[0].taxResidenceIdCardNumber !="N.A.">
                        ${(taxes[0].taxResidenceIdCardNumber)!"N/A"}
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
                    ${(taxes[0].noTaxIdentificationNumber.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
    </table>

    <!--Employment雇佣-->
    <h4>4. Employment雇佣</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td style="width: 160px;">
                <div>Employment Status</div>
                <div class="valueTd">就业状态</div>
            </td>
            <td colspan="3">
                <div>
                    ${(employments[0].employmentStatus.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Full Employer Name</div>
                <div class="valueTd">雇佣单位名称</div>
            </td>
            <td colspan="3">
                ${(employments[0].companyName)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>
                <div>Nature of Business</div>
                <div class="valueTd">商业性质</div>
            </td>
            <td colspan="3">
                <div>
                    ${(employments[0].commercialNature.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Occupation</div>
                <div class="valueTd">职位</div>
            </td>
            <td colspan="3">
                <div>
                    ${(employments[0].position.dictDest)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Country</div>
                <div class="valueTd">国家</div>
            </td>
            <td colspan="3">
                <div>
                    ${(employmentAddress.country.enName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Address</div>
                <div class="valueTd">地址</div>
            </td>
            <td colspan="3">
                <div>
                    ${(employmentAddress.address)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>City</div>
                <div class="valueTd">城市</div>
            </td>
            <td colspan="3">
                <div>
                    ${(employmentAddress.city)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>State/Province</div>
                <div class="valueTd">省/市</div>
            </td>
            <td colspan="3">
                <div>
                    ${(employmentAddress.rovince)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Zip/Postal Code</div>
                <div class="valueTd">邮政编码</div>
            </td>
            <td colspan="3">
                <div>
                    ${(employmentAddress.zipCode)!"N/A"}
                </div>
            </td>
        </tr>
    </table>

    <!--Wealth财富来源-->
    <h4>5. Source of Wealth财富来源</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr style="height: 28px">
            <td>Type
                <br />收入来源
            </td>
            <td>Percentage<br />比例</td>
            <td colspan="2">Notes
            <br />注释</td>
        </tr>
        <tr style="height: 30px">
            <td>
                ${(wealthSources[0].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[0].percentageOfFunds + "%")!}
            </td>
            <td>
                ${(wealthSources[0].sourceDesc)!}
            </td>
        </tr>
        <tr style="height: 30px">
            <td>
                ${(wealthSources[1].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[1].percentageOfFunds + "%")!}
            </td>
            <td>
                ${(wealthSources[1].sourceDesc)!}
            </td>
        </tr>
        <tr style="height: 30px">
            <td>
                ${(wealthSources[2].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[2].percentageOfFunds + "%")!}
            </td>
            <td>
                ${(wealthSources[2].sourceDesc)!}
            </td>
        </tr>
        <tr style="height: 30px">
            <td>
                ${(wealthSources[3].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[3].percentageOfFunds + "%")!}
            </td>
            <td>
                ${(wealthSources[3].sourceDesc)!}
            </td>
        </tr>
        <tr style="height: 31px">
            <td>
                ${(wealthSources[4].incomeType.dictDest)!}
            </td>
            <td>
                ${(wealthSources[4].percentageOfFunds + "%")!}
            </td>
            <td>
                ${(wealthSources[4].sourceDesc)!}
            </td>
        </tr>
<!--        <#if wealthSources ??>-->
<!--            <#list wealthSources as wealthSource>-->
<!--        <tr>-->
<!--            <td>-->
<!--                ${(wealthSource.incomeType.dictDest)!"N/A"}-->
<!--            </td>-->
<!--            <td>-->
<!--                ${(wealthSource.percentageOfFunds)!"N/A"}-->
<!--            </td>-->
<!--            <td colspan="2">-->
<!--                ${(wealthSource.sourceDesc)!"N/A"}-->
<!--            </td>-->
<!--        </tr>-->
<!--            </#list>-->
<!--        </#if>-->
    </table>

    <!--    规管信息-->
<#--    <br />-->
    <h4 style="margin-top: 11px">6. Regulatory Information规管信息</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <#list regulatorys as regulatory>
        <tr>
            <td>
                <div>Is the account holder or any immediate family member who resides in the same household, registered as a broker-dealer or an employee, director or owner of a securities or commodities brokerage firm, employee of a bank, hedge fund,or other financial services company?</div>
                <div class="valueTd">账户持有人或同一居所内居住的任何直系亲属是否为注册经纪-自营商；证券或商品经纪公司的雇员、董事或所有人；银行、对冲基金交易所或其他金融服务公司的雇员？</div>
            </td>
            <td style="width: 200px;">
                <#if (regulatory.employer) ??>
                    YES
                <#else >
                    NO
                </#if>
            </td>
        </tr>
        <tr>
            <td>
                <div>Company Name</div>
                <div class="valueTd">公司名称</div>
            </td>
            <td style="width: 200px;">
                <div class="valueTd">
                    ${(regulatory.employer)!}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Relationship to Account Holder</div>
                <div class="valueTd">与持有人的关系</div>
            </td>
            <td>
                <div>
                    ${(regulatory.relationshipAccountHolders.dictDest)!}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Family Member Affiliated with Company</div>
                <div class="valueTd">与公司有关联的家庭成员</div>
            </td>
            <td>
                <div>
                    ${(regulatory.familyMembersCompany)!}
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Is the account holder a director, a 10% shareholder or a policy-making officer of any publicly traded company?</div>
                <div class="valueTd">账户持有人是否是一家公共上市公司的董事，持10%股权的股东或决策人？</div>
            </td>
            <td>
                <#if (regulatory.accountHolderCompanyStock) ??>
                    YES
                <#else >
                    NO
                </#if>
            </td>
        </tr>
        <tr>
            <td style="height: 36px">
                <div>stock symbol(s) of the company or companies</div>
                <div class="valueTd">公司的股票代码</div>
            </td>
            <td colspan="3"  class="valueTd">
                ${(regulatory.accountHolderCompanyStock)!}
            </td>
        </tr>
        </#list>
    </table>
    <h4>7. Account Financial Background账户财务背景</h4>
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
                    <#if investTargets ??>
                        <#if (investTargets[0].investmentTarget)! ??>
                            <#list (investTargets[0].investmentTarget)! as i>
                                ${(i.dictDest)!"N/A"}
                                <#if i_has_next>,</#if>
                            </#list>
                        </#if>
                    </#if>
                </div>
            </td>
        </tr>
<!--        <#if tradeExps ??>-->
<!--            <#list tradeExps as tradeExp>-->
<!--        <tr>-->
<!--            <td rowspan="2">-->
<!--                <div class="valueTd">-->
<!--                    ${(tradeExp.tansactionType.dictDest)!"N/A"}-->
<!--                </div>-->
<!--            </td>-->
<!--            <td>-->
<!--                <div>Knowledge Level</div>-->
<!--                <div class="valueTd">-->
<!--                    知识水平-->
<!--                </div>-->
<!--                <div class="valueTd">-->
<!--                    ${(tradeExp.knowledgeLevel.dictDest)!"N/A"}-->
<!--                </div>-->
<!--            </td>-->
<!--            <td>-->
<!--                <div>Trading Experience</div>-->
<!--                <div class="valueTd">-->
<!--                    交易经验-->
<!--                </div>-->
<!--                <div class="valueTd">-->
<!--                    ${(tradeExp.tradingExperience.dictDest)!"N/A"}-->
<!--                </div>-->
<!--            </td>-->
<!--            <td>-->
<!--                <div>Number of transactions per year</div>-->
<!--                <div class="valueTd">-->
<!--                    每年交易笔数-->
<!--                </div>-->
<!--                <div class="valueTd">-->
<!--                    ${(tradeExp.yearTransactionsPerNumber.dictDest)!"N/A"}-->
<!--                </div>-->
<!--            </td>-->
<!--            <td>-->
<!--                <div>Total Trades</div>-->
<!--                <div class="valueTd">-->
<!--                    总交易笔数-->
<!--                </div>-->
<!--                <div class="valueTd">-->
<!--                    ${(tradeExp.transactionsTotalNumber)!"N/A"}-->
<!--                </div>-->
<!--            </td>-->
<!--        </tr>-->
<!--        <tr>-->
<!--            <td colspan="5">-->
<!--                <div>LOCATIONS YOU TRADE</div>-->
<!--                <div class="valueTd">-->
<!--                    交易地点-->
<!--                </div>-->
<!--                <div class="valueTd">-->
<!--                    <#if tradeExp.tradingLocation ??>-->
<!--                        <#list tradeExp.tradingLocation as i>-->
<!--                        ${(i.tansactionLocation)!"N/A"}-->
<!--                        <#if i_has_next>,</#if>-->
<!--                         </#list>-->
<!--                    </#if>-->
<!--                </div>-->
<!--            </td>-->
<!--        </tr>-->
<!--            </#list>-->
<!--        </#if>-->
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
    <h4>8. Investment Experience投资经验</h4>
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
            <td style="width: 130px;height: 36px">
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
                ${(tradeExp.tradingExperience.dictDest)!}
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
            <td style="height: 36px">
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
                ${(tradeExp.tradingExperience.dictDest)!}
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
<#--        <tr>-->
<#--            <td>-->
<#--                <div>Warrants</div>-->
<#--                <div class="valueTd">-->
<#--                    权证-->
<#--                </div>-->
<#--            </td>-->
<#--            <td>-->
<#--                <#if tradeExps ??>-->
<#--                <#list tradeExps as tradeExp>-->
<#--                <#if (tradeExp.tansactionType.dictValue == "Warrant")!>-->
<#--                ${(tradeExp.knowledgeLevel.dictDest)!}-->
<#--                <#break>-->
<#--                </#if>-->
<#--                </#list>-->
<#--                </#if>-->
<#--            </td>-->
<#--            <td>-->
<#--                <#if tradeExps ??>-->
<#--                <#list tradeExps as tradeExp>-->
<#--                <#if (tradeExp.tansactionType.dictValue == "Warrant")!>-->
<#--                ${(tradeExp.tradingExperience.dictDest)!}-->
<#--                <#break>-->
<#--            </#if>-->
<#--            </#list>-->
<#--            </#if>-->
<#--            </td>-->
<#--            <td>-->
<#--                <#if tradeExps ??>-->
<#--                <#list tradeExps as tradeExp>-->
<#--                <#if (tradeExp.tansactionType.dictValue == "Warrant")!>-->
<#--                ${(tradeExp.yearTransactionsPerNumber.dictDest)!}-->
<#--                <#break>-->
<#--            </#if>-->
<#--            </#list>-->
<#--            </#if>-->
<#--            </td>-->
<#--        </tr>-->
        <tr>
            <td style="height: 36px">
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
                ${(tradeExp.tradingExperience.dictDest)!}
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
            <td style="height: 36px;">
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
                ${(tradeExp.tradingExperience.dictDest)!}
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
            <td style="height: 36px">
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
                ${(tradeExp.tradingExperience.dictDest)!}
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
<#--        <tr>-->
<#--            <td>-->
<#--                <div>Single Stock Futures</div>-->
<#--                <div class="valueTd">-->
<#--                    个股期货-->
<#--                </div>-->
<#--            </td>-->
<#--            <td>-->
<#--                <#if tradeExps ??>-->
<#--                <#list tradeExps as tradeExp>-->
<#--                <#if (tradeExp.tansactionType.dictValue == "SingleStockFutures")!>-->
<#--                ${(tradeExp.knowledgeLevel.dictDest)!}-->
<#--                <#break>-->
<#--                </#if>-->
<#--                </#list>-->
<#--                </#if>-->
<#--            </td>-->
<#--            <td>-->
<#--                <#if tradeExps ??>-->
<#--                <#list tradeExps as tradeExp>-->
<#--                <#if (tradeExp.tansactionType.dictValue == "SingleStockFutures")!>-->
<#--                ${(tradeExp.tradingExperience.dictDest)!}-->
<#--                <#break>-->
<#--            </#if>-->
<#--            </#list>-->
<#--            </#if>-->
<#--            </td>-->
<#--            <td>-->
<#--                <#if tradeExps ??>-->
<#--                <#list tradeExps as tradeExp>-->
<#--                <#if (tradeExp.tansactionType.dictValue == "SingleStockFutures")!>-->
<#--                ${(tradeExp.yearTransactionsPerNumber.dictDest)!}-->
<#--                <#break>-->
<#--            </#if>-->
<#--            </#list>-->
<#--            </#if>-->
<#--            </td>-->
<#--        </tr>-->



    </table>
    <h4 style="margin-top: 5px;">9. Account Trading Permissions账户交易许可</h4>
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

<!--    协议披露-->
    <h4>10. CONFIRMATIONS AND ACKNOWLEDGEMENTS 确认及承认</h4>
    <div style="line-height: 20px;padding-left: 12px;">
        <div>a)	Day Trading Risk Disclosure Statement</div>
        <div>b)	Hong Kong Risk Disclosure</div>
        <div>c)	Certification Regarding Trading Control and Ownership of the Account</div>
        <div>d) Risks of After-Hours Trading</div>
        <div>e) US Stock Stop Order Disclosure</div>
        <div>f) Essential Legal Terms for your MOF Account</div>
        <div>g) Portfolio Margin Risk Disclosure</div>
        <div>h) Customer Agreement US</div>
        <br />
        <div>a) 日内交易风险披露声明</div>
        <div>b) 香港风险披露</div>
        <div>c) 账户交易控制及所有权证明</div>
        <div>d) 市后交易风险</div>
        <div>e) 美国股票止损定单披露</div>
        <div>f) 有关您MOF账户的重要法律条款</div>
        <div>g) 投资组合保证金风险披露</div>
        <div>h) 客户协议美国</div>
        <br />
        <br />
        <div style="font-weight: bolder">I have read and agree to the above agreement documents.</div>
        <div style="font-weight: bolder">本人已阅读并同意以上协议文件</div>
    </div>
<!--    <table border="1" cellpadding="0" cellspacing="0">-->
<!--        <tr>-->
<!--            <td>Documents文件</td>-->
<!--            <td>Description描述</td>-->
<!--        </tr>-->
<!--        <#if agreements ??>-->
<!--            <#list agreements as agreement>-->
<!--        <tr>-->
<!--            <td>-->
<!--                <div>-->
<!--                    ${(agreement.enName)!"N/A"}-->
<!--                </div>-->
<!--                <div class="valueTd">-->
<!--                    ${(agreement.cnName)!"N/A"}-->
<!--                </div>-->
<!--            </td>-->
<!--            <td>-->
<!--                <div>-->
<!--                    ${(agreement.agreementDescription)!"N/A"}-->
<!--                </div>-->
<!--            </td>-->
<!--        </tr>-->
<!--            </#list>-->
<!--        </#if>-->
<!--    </table>-->
    <br />
    <br />
    <div style="margin-bottom: 5px;"><strong>Any interpretation of the language in this application is governed by the English version.</strong></div>
    <div><strong>本申请表格的诠释概以英文版为准。</strong></div>
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