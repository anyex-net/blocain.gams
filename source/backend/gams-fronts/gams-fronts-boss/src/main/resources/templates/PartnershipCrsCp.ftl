
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <style>
        body{
            font-family:Source Han Sans CN Regular,Serif;
            font-size: 10pt;
            line-height: 1.5;
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
            table tr{page-break-inside:avoid;}
            table div{page-break-inside:avoid;}
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
        ul{
            padding-left: 15px;
        }
        .specialUl li{
            position: relative;
        }
        .specialUl li:nth-child(n)::before{
            content: '';
            position: absolute;
            background: black;
            border-radius: 50%;
            width: 5px;
            height: 5px;
            line-height: 5px;
            left: -12px;
            top: 8px;
        }
        ul li{
            list-style-type:none;
        }
        table{
            width: 100%;
            border-bottom:0px;
            border-top:0;
            border-left:0;
            border-right:0px;
        }
        tr:last-child td{
            border-bottom: 0.5px solid #000;
        }
        td:last-child{
            border-right: 0.5px solid #000;
        }
        tr{
            display: flex;
        }
        tr .valueTd{
            word-wrap: break-word;
            /*word-break: break-all;*/
        }
        td{
            line-height: 18px;
            flex: 1;
            word-break: break-word;
            border-top: 0.5px solid #000;
            border-left: 0.5px solid #000;
            border-right: 0px;
            border-bottom: 0px;
            padding: 4px;
            min-height: 16px;
        }
        h4{
            padding-bottom: 6px;
            margin-bottom: 0px;
        }

    </style>
</head>
<body>
<div id="header">
    <div id="header-left" class="header-left">
        <img src="data:${fileType};base64,${file64Str}" width="150px" style="display: inline-block" />
        <span style="margin-left: 20px;text-align: right;float: right">
            <div>MOF Securities CRS Controlling Person Self-Certification Form(CRS-CP)</div>
            <div>Self-Certification Instructions to Account Holder</div>
        </span>
    </div>
</div>
<div id="footer">
    <div id="footer-left" class="footer-left" style="text-align: left;float: left">
        <span>
            <div>MOF Securities Limited</div>
<!--            <div>国瑞证券有限公司</div>-->
        </span>

    </div>
    <div id="footer-right" class="footer-right" style="text-align: right;float: right">
        <span>
            <div>Page <span id="pages"> of </span></div>
<!--            <div>第 <span id="pages">页，共 </span>页</div>-->
        </span>
    </div>
</div>
<div class="page1">
    <div style="height: 30px">
        <span style="float: left">www.mofsecurities.com | +86 4006589990</span>
        <span style="float: right"></span>
    </div>
    <div>
        <strong>MOF Securities Limited</strong> is collecting the information in this form to comply with legal requirements based on the Organization for
        Economic Cooperation and Development (OECD) (2014) Standard for Automatic Exchange of Financial Account Information in Tax Matters (referred to as
        the Common Reporting Standard, or CRS). Capitalized terms not defined below are defined in the CRS.
    </div>
    <br />
    <div>Please note:</div>
    <ul class="specialUl" style="margin: 0">
        <li>This form is for Entity Account Holders only. If the Account Holder is an individual, please complete the CRS Individual tax residency self-certification.</li>
        <li>This form must be signed and dated.	</li>
        <li>Complete all parts of this form as directed and provide all information requested.</li>
        <li>If you have any questions regarding the Entity Holder’s tax residence or how to complete this self-certification, consult your tax advisor.</li>
        <li>The terms Account Holder and TIN are defined below.</li>
        <li>If any information on this form becomes incorrect or incomplete, a new form must be submitted within 30 days of the change.</li>
        <br />
        <li>Account Holder</li>
        <ul>
            <li>For a Depository Institution or a Custodial Institution, the term <strong>Account Holder</strong> means the person listed or identified as the holder of a
                Financial Account by the Financial Institution that maintains the account. A person, other than a Financial Institution, holding a
                Financial Account for the benefit or account of another person as agent, custodian, nominee, signatory, investment advisor, or intermediary,
                is not treated as holding the account. Each account holder of a jointly held account must provide a separate self-certification.</li>
        </ul>
        <ul>
            <li>For an Investment Entity, the term <strong>Account Holder</strong> means any investor listed or identified as holding an equity (including a partnership interest
                or an interest in a trust) or debt interest in the Investment Entity. A person, other than a Financial Institution, holding an equity or debt interest for
                the benefit or account of another person as agent, custodian, nominee, signatory, investment advisor, or intermediary, is not treated as holding the account.</li>
        </ul>
        <ul>
            <li>For a Cash Value Insurance Contract or an Annuity Contract, the term <strong>Account Holder</strong> means any person entitled to access the Cash Value or
                change the beneficiary of the contract. If no person can access the Cash Value or change the beneficiary, the <strong>Account Holder</strong> is any
                person named as the owner in the contract and any person with a vested entitlement to payment under the terms of the contract. Upon the maturity of a
                Cash Value Insurance Contract or an Annuity Contract, each person entitled to receive a payment under the contract is treated as an <strong>Account Holder</strong></li>
        </ul>
        <br />
        <li>
            <strong>TIN</strong> means a Taxpayer Identification Number (or a <strong>functional equivalent</strong>). A <strong>TIN</strong> is a unique combination of letters or
            numbers assigned by a jurisdiction to an individual or an <strong>Entity</strong> and used to identify the individual or Entity for the purposes of
            administering the tax laws of such jurisdiction. Some jurisdictions do not issue a <strong>TIN</strong>. However, these jurisdictions often utilize some other
            high integrity number with an equivalent level of identification (a <strong>functional equivalent</strong>).
        </li>
    </ul>
    <div>To return forms to MOF Securities Limited, please scan and upload as attachment to message sent email to <strong>info@mofsecurities.com</strong></div>
    <br />
    <div>
        Please call +86 4006589990 if you have any questions.
    </div>
    <h4>Part I. Identification of Controlling Person</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="3">A．Name of Controlling Person</td>
        </tr>
        <tr>
            <td style="width: 150px;">
                <div>Salutation</div>
                <div class="valueTd" style="height: 36px;">
                    ${(crsControl.appellation.dictDest)!"N/A"}
                </div>
            </td>
            <td style="width: 250px;">
                <div>Last Name</div>
                <div class="valueTd" style="height: 36px;">
                    ${(crsControl.lastName)!"N/A"}
                </div>
            </td>
            <td>
                <div>First Name</div>
                <div class="valueTd" style="height: 36px">
                    ${(crsControl.firstName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td>Identification Type</td>
            <td colspan="2">
                ${(crsControl.idCardType.dictDest)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>Identification Number</td>
            <td colspan="2">
                ${(crsControl.idNumber)!"N/A"}
            </td>
        </tr>
        <tr>
            <td colspan="3">B．Current Residence Address</td>
        </tr>
        <tr>
            <td>Address</td>
            <td colspan="2" style="height: 36px;" class="valueTd">
                ${((crsControl.accountParAddressModelList[0].address)?replace('-#-',','))!"N/A"}
            </td>
        </tr>
        <tr>
            <td>City</td>
            <td colspan="2">
                ${(crsControl.accountParAddressModelList[0].city)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>State/Province</td>
            <td colspan="2">
                ${(crsControl.accountParAddressModelList[0].rovince)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>Country</td>
            <td colspan="2">
                ${(crsControl.accountParAddressModelList[0].country.enName)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>Zip/Postal Code</td>
            <td colspan="2">
                ${(crsControl.accountParAddressModelList[0].zipCode)!"N/A"}
            </td>
        </tr>
        <tr>
            <td colspan="3">C．Mailing Address (Please complete if different to the current residence address)</td>
        </tr>
        <tr>
            <td>Address</td>
            <td colspan="2" style="height: 36px;" class="valueTd">
                ${((crsControl.otherAddresses)?replace('-#-',','))!"N/A"}
            </td>
        </tr>
        <tr>
            <td>City</td>
            <td colspan="2">
                ${(crsControl.otherCity)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>State/Province</td>
            <td colspan="2">
                ${(crsControl.otherProvince)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>Country</td>
            <td colspan="2">
                ${(crsControl.otherCountry)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>Zip/Postal Code</td>
            <td colspan="2">
                ${(crsControl.otherZipCode)!"N/A"}
            </td>
        </tr>
    </table>

    <h4>Part II The Entity Account Holder(s) of which you are a Controlling Person</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td style="width: 100px;">Entity</td>
            <td>Name of the Entity Account Holder</td>
        </tr>
        <tr>
            <td style="width: 100px;">1</td>
            <td style="height: 50px;" class="valueTd">
                ${(entityAccountNames[0])!"N/A"}
            </td>
        </tr>
        <tr>
            <td style="width: 100px;">2</td>
            <td style="height: 50px;" class="valueTd">
                ${(entityAccountNames[1])!"N/A"}
            </td>
        </tr>
        <tr>
            <td style="width: 100px;">3</td>
            <td style="height: 50px;" class="valueTd">
                ${(entityAccountNames[2])!"N/A"}
            </td>
        </tr>

    </table>
    <h4>Part III Jurisdiction of Residence and Taxpayer Identification Number or its Functional Equivalent (“TIN”)*</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="2">
                <ul class="specialUl" style="margin: 0">
                    <li style="font-size: 14px;">Please complete the following table indicating (a) the jurisdiction of residence (including Hong Kong) where the Controlling Person is a
                        resident for tax purposes and (b) the Controlling Person's TIN for each jurisdiction indicated.Indicate all (not restricted to three) the jurisdictions of residence. </li>
                    <li style="font-size: 14px;">If the Controlling Person is a tax resident of Hong Kong, the TIN is the Hong Kong Identity Card Number.</li>
                </ul>
            </td>
        </tr>
<#--        <tr>-->
<#--            <td colspan="2">-->
<#--                <ul class="specialUl" style="margin: 0">-->
<#--                    <li style="font-size: 14px;">If the Controlling Person is a tax resident of Hong Kong, the TIN is the Hong Kong Identity Card Number.</li>-->
<#--                </ul>-->
<#--            </td>-->
<#--        </tr>-->
        <tr>
            <td style="width: 120px;" class="valueTd">Do you have a Tax Identification Number?</td>
            <td style="height: 36px;" class="valueTd">
                <#if (crsControl.yesTaxNumber) ?? >
                    Yes
                <#else >
                    No
                </#if>
            </td>
        </tr>
        <tr>
            <td style="width: 150px;" class="valueTd">Tax Identification Number</td>
            <td class="valueTd" style="height: 36px;">
                <#if  (crsControl.yesTaxNumber) ?? >
                    ${(crsControl.yesTaxNumber)!"N/A"}
                <#else >
                    N/A
                </#if>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;">Reason</td>
            <td class="valueTd" style="height: 36px;">
                <#if  (crsControl.noTaxNumber) ??>
                    <#if crsControl.noTaxNumber =="The controller cannot obtain the tax number. If you choose this reason, please explain below why the controlling party can not obtain the tax number.">
                        ${(crsControl.reason)!"N/A"}
                    <#else >
                        ${(crsControl.noTaxNumber)!"N/A"}
                    </#if>
                <#else >
                    N/A
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

    <h4>Part IV Type of Controlling Person</h4>
    <table border="1" cellpadding="0" cellspacing="0">
<#--        <tr>-->
<#--            <td colspan="5">Part 4 – Type of Controlling Person</td>-->
<#--        </tr>-->
        <tr>
            <td colspan="5">Tick the appropriate box to indicate the type of Controlling Person for each entity stated in Part II.</td>
        </tr>
        <tr>
            <td style="width: 100px;">Type of Entity</td>
            <td>Type of Controlling Person</td>
            <td style="width: 65px;">Entity (1)</td>
            <td style="width: 65px;">Entity (2)</td>
            <td style="width: 65px;">Entity (3)</td>
        </tr>
        <tr>
            <td rowspan="3">Legal Person</td>
            <td class="valueTd">Individual who has a controlling ownership interest (i.e. more than 25% of issued share capital)</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="legalPerson">
                              <#list entity1.controlCategory as controlCategory>
                                   <#if  controlCategory.dictValue=="Individual who has a controlling ownership interest (i.e. more than25% of issued share capital)">
                                       checked="true"
                                   </#if>
                              </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="legalPerson">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual who has a controlling ownership interest (i.e. more than25% of issued share capital)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="legalPerson">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual who has a controlling ownership interest (i.e. more than25% of issued share capital)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Individual who exercises control / is entitled to exercise control
                through other means (i.e. more than 25% of voting rights)</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="legalPerson">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individuals who exercise control by other means or who have the right to exercise control (i.e. not less than 25% of the voting rights)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="legalPerson">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individuals who exercise control by other means or who have the right to exercise control (i.e. not less than 25% of the voting rights)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="legalPerson">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individuals who exercise control by other means or who have the right to exercise control (i.e. not less than 25% of the voting rights)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Individual who holds the position of senior managing official /
                exercises ultimate control over the management of the entity</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="legalPerson">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual who holds the position of senior managing official /exercises ultimate control over the management of the entity">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="legalPerson">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual who holds the position of senior managing official /exercises ultimate control over the management of the entity">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="legalPerson">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual who holds the position of senior managing official /exercises ultimate control over the management of the entity">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td style="border-bottom: 0.5px solid #000" rowspan="5">Trust</td>
            <td>Settlor</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Settlor">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Settlor">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Settlor">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Trustee</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Trustee">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Trustee">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Trustee">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Protector</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Protector">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Protector">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Protector">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Beneficiary or member of the class of beneficiaries</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Beneficiary or member of the class of beneficiaries">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Beneficiary or member of the class of beneficiaries">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Beneficiary or member of the class of beneficiaries">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Other (e.g. individual who exercises control over another entity being
                the settlor / trustee / protector / beneficiary)</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Other (e.g. individual who exercises control over another entity being the settlor /trustee /protector /beneficiary)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Other (e.g. individual who exercises control over another entity being the settlor /trustee /protector /beneficiary)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Other (e.g. individual who exercises control over another entity being the settlor /trustee /protector /beneficiary)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
    </table>
    <br />
    <br />
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td rowspan="5" style="border-bottom: 0.5px solid #000;width: 100px;">Legal Arrangement other than Trust</td>
            <td>Individual in a position equivalent / similar to settlor</td>
            <td style="text-align: center;width: 65px"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to settlor">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center;width: 65px"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to settlor">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center;width: 65px"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to settlor">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Individual in a position equivalent / similar to trustee</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to trustee">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to trustee">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to trustee">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Individual in a position equivalent / similar to protector</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to protector">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to protector">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to protector">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Individual in a position equivalent / similar to beneficiary or member
                of the class of beneficiaries</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to beneficiary or member of the class of beneficiaries">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to beneficiary or member of the class of beneficiaries">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Individual in a position equivalent /similar to beneficiary or member of the class of beneficiaries">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
        <tr>
            <td>Other (e.g. individual who exercises control over another entity being
                equivalent / similar to settlor / trustee / protector / beneficiary)</td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity1 ?? && entity1.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity1.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Other (e.g. individual who exercises control over another entity being equivalent /similar to settlor /trustee /protector /beneficiary)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity2 ?? && entity2.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity2.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Other (e.g. individual who exercises control over another entity being equivalent /similar to settlor /trustee /protector /beneficiary)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
            <td style="text-align: center"><input type="checkbox"
                        <#if  entity3 ?? && entity3.entityCategory.dictValue=="Legal Arrangementother than Trust">
                            <#list entity3.controlCategory as controlCategory>
                                <#if  controlCategory.dictValue=="Other (e.g. individual who exercises control over another entity being equivalent /similar to settlor /trustee /protector /beneficiary)">
                                    checked="true"
                                </#if>
                            </#list>
                        </#if>
                /> </td>
        </tr>
    </table>
    <h4>Part V Declarations and Signature</h4>
    <p>
        1. I declare that the information given and all statements made in this form and declarations are, to the best of my knowledge and belief, true, correct and complete.
    </p>
    <p>
        2. I acknowledge and agree that (a) the information contained in this form is collected and may be kept for the purpose of automatic exchange of financial account
        information, and (b) such information and information regarding the Account Holder and any Reportable Account(s) may be reported by MOF Securities Limited to the Inland
        Revenue Department of the Government of the Hong Kong Special
    </p>
    <p>
        3. I undertake to advise MOF Securities within 30 days of any change in circumstances which affects the tax residency status of the Account Holder identified
        in Part 1 of this form or causes the information contained herein to become incorrect (including any changes to the information on controlling persons identified
        in Part 3), and to provide MOF Securities with a suitably updated self-certification and
    </p>
    <p>
        4. I consent to the collection and distribution of tax forms in an electronic format in lieu of paper, including through the account management portal.
    </p>
    <br />
    <br />
    <br />
    <br />
    <br />
    <hr />
    <div>
        <span style="width: 30%;display: inline-block;">
            Signature
        </span>
        <span style="width: 30%;display: inline-block;">
            Print Name
        </span>
        <span>
            Date(MM/DD/YYYY)
        </span>
    </div>
</div>

</body>
</html>