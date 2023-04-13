
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
            /*position: relative;*/
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
        ul{
            padding-left: 15px;
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
        .inlineBlock{
            display: inline-block;
        }
        .borderB{
            border-bottom: 0.5px solid #000000;
        }

    </style>
</head>
<body>
<div id="header">
    <div id="header-left" class="header-left">
        <img src="data:${fileType};base64,${file64Str}" width="150px" style="display: inline-block" />
        <span style="margin-left: 20px;text-align: right;float: right">
            <div>MOF Securities CRS Entity Self-Certification Form(CRS-E)</div>
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
    <div><strong>Please note:</strong></div>
    <ul class="specialUl" style="margin: 0">
        <li><strong>This form is for Entity Account Holders only. </strong>If the <strong>Entity Account Holder</strong> is an individual, please complete the
            CRS Individual Self-Certification.</li>
        <li><strong>This form must be signed and dated.	</strong></li>
        <li>Please complete all parts of this form as directed and provide all information requested.</li>
        <li>If you have any questions regarding how to complete this form, please contact your tax advisor.</li>
        <li>The terms <strong>Account Holder</strong> and <strong>TIN </strong> are defined below. Additional <strong>boldface terms</strong> are defined in the Appendix.
            Capitalized terms that are not defined in the Appendix are set forth in the CRS and associated Commentary.
        </li>
        <li>If any information on this form, including the information related to <strong>Controlling Persons,</strong> becomes incorrect or incomplete, a new form
            must be provided within 30 days of the change.</li>
        <br />
        <li><strong>Account Holder</strong></li>
        <ul>
            <li>For a <strong>Depository Institution</strong> or a <strong>Custodial Institution</strong>, the term <strong>Account Holder</strong> means the person listed or
                identified as the holder of a <strong>Financial Account</strong> by the <strong>Financial Institution</strong> that maintains the account. A person,
                other than a <strong>Financial Institution</strong>, holding a <strong>Financial Account</strong> for the benefit or account of another person as agent,
                custodian, nominee, signatory, investment advisor, or intermediary, is not treated as holding the account.</li>
        </ul>
        <ul>
            <li>For an <strong>Investment Entity</strong>, the term <strong>Account Holder</strong> means any investor listed or identified as holding an equity
                (including a partnership interest or an interest in a trust) or debt interest in the <strong>Investment Entity</strong>. A person, other than a
                <strong>Financial Institution</strong>, holding an equity or debt interest for the benefit or account of another person as agent, custodian, nominee,
                signatory, investment advisor, or intermediary, is not treated as holding the account.</li>
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
    <h4>Part I. Identification of Entity Account Holder</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="2">A．Customer Information</td>
        </tr>
        <tr>
            <td colspan="2">
                <div>1. Legal Name of Entity / Branch</div>
                <div class="valueTd" style="height: 36px;">
                    ${(crsEntity.entityName)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>2. Jurisdiction of Incorporation or Organization</div>
                <div class="valueTd">
                    ${(crsEntity.taxJurisdiction)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td style="width: 150px;">
                <div>3. Registration Number</div>
            </td>
            <td>
                <div class="valueTd">
                    ${(crsEntity.registrationNumber)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">B．Current Business Address</td>
        </tr>
        <tr>
            <td>Address</td>
            <td style="height: 36px;" class="valueTd">
                ${(personalAddress.address)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>City</td>
            <td>
                ${(personalAddress.city)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>State/Province</td>
            <td>
                ${(personalAddress.rovince)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>Country</td>
            <td>
                ${(personalAddress.country.enName)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>Zip/Postal Code</td>
            <td>
                ${(personalAddress.zipCode)!"N/A"}
            </td>
        </tr>
        <tr>
            <td colspan="2">C．Mailing Address (Please complete if different to the current business address)</td>
        </tr>
        <tr>
            <td>Address</td>
            <td style="height: 36px;" class="valueTd">
                ${(crsEntity.otherAddresses)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>City</td>
            <td>
                ${(crsEntity.otherCity)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>State/Province</td>
            <td>
                ${(crsEntity.otherProvince)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>Country</td>
            <td>
                ${(crsEntity.otherCountry)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>Zip/Postal Code</td>
            <td>
                ${(crsEntity.otherZipCode)!"N/A"}
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
    <h4>Part II. Entity Type</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td style="width: 70px;">Financial Institution</td>
            <td>
                <div><input type="checkbox"
                            <#if crsEntity.entityNumber=="Financial Institution">
                                <#if crsEntity.entityCategory=="Custodial Institution, Depository Institution or Specified Insurance Company">
                                    checked="true"
                                </#if>
                            </#if>
                    /> Custodial Institution, Depository Institution or Specified Insurance Company</div>
                <div><input type="checkbox"
                            <#if crsEntity.entityNumber=="Financial Institution">
                                <#if crsEntity.entityCategory=="Investment Entity,except an investment entity that is managed by another financial institution (e.g. with discretion\nto manage the entity’s assets) and located in a non-participating jurisdiction">
                                    checked="true"
                                </#if>
                            </#if>
                    /> Investment Entity, except an investment entity that is managed by another financial institution (e.g. with discretion to manage
                    the entity's assets) and located in a non-participating jurisdiction</div>
            </td>
        </tr>
        <tr>
            <td style="width: 70px;">Active NFE</td>
            <td>
                <div><input type="checkbox"
                            <#if crsEntity.entityNumber=="Active NFE">
                                <#if crsEntity.entityCategory=="1">
                                    checked="true"
                                </#if>
                            </#if>
                    /> NFE the stock of which is regularly traded on
                    <span style="width: 280px;text-align: center;" class="borderB inlineBlock">
                        <#if crsEntity.entityNumber=="Active NFE">
                            <#if crsEntity.entityCategory=="1">
                                ${(crsEntity.entityInput)!"N/A"}
                            </#if>
                        </#if>
                    </span></div>
                <div>which is an established securities market. </div>


                <div><input type="checkbox"
                            <#if crsEntity.entityNumber=="Active NFE">
                                <#if crsEntity.entityCategory=="2">
                                    checked="true"
                                </#if>
                            </#if>
                    /> Related entity of<span style="width: 454px;text-align: center;" class="borderB inlineBlock">
                        <#if crsEntity.entityNumber=="Active NFE">
                            <#if crsEntity.entityCategory=="2">
                                ${(crsEntity.entityInput?substring(0,crsEntity.entityInput?index_of(',')))!"N/A"}
                            </#if>
                        </#if>
                    </span></div>
                <div>,the stock of which is regularly traded on<span style="width: 326px;text-align: center;" class="borderB inlineBlock">
                        <#if crsEntity.entityNumber=="Active NFE">
                            <#if crsEntity.entityCategory=="2">
                                ${(crsEntity.entityInput?substring(crsEntity.entityInput?index_of(','),crsEntity.entityInput?length))!"N/A"}
                            </#if>
                        </#if>
                    </span></div>
<#--                <div><span style="width: 365px" class="borderB inlineBlock"></span>which is an established</div>-->
<#--                <div>securities market </div>-->
                <div>which is an established securities market</div>
                <div><input type="checkbox"
                            <#if crsEntity.entityNumber=="Active NFE">
                                <#if crsEntity.entityCategory=="NFE is a governmental entity a central bank or an entity wholly owned by one or more the foregoing entities">
                                    checked="true"
                                </#if>
                            </#if>
                    /> NFE is a governmental entity, a central bank, or an entity wholly owned by one or more the foregoing entities </div>
                <div><input type="checkbox"
                            <#if crsEntity.entityNumber=="Active NFE">
                                <#if crsEntity.entityCategory=="international organization (for example the United Nations or NATO) or an entity wholly owned by one or more international organizations">
                                    checked="true"
                                </#if>
                            </#if>
                    /> NFE is an international organization (for example the United Nations or NATO) or an entity wholly owned by one
                    or more international organizations. </div>
                <div><input type="checkbox"
                            <#if crsEntity.entityNumber=="Active NFE">
                                <#if crsEntity.entityCategory=="Active NFE other than the above">
                                    checked="true"
                                </#if>
                            </#if>
                    /> Active NFE other than the above </div>
            </td>
        </tr>
        <tr>
            <td style="width: 70px;">Passive NFE</td>
            <td>
                <div><input type="checkbox"
                            <#if crsEntity.entityNumber=="Passive NFE">
                                <#if crsEntity.entityCategory=="Investment entity that is managed by another financial institution and located in a non-participating jurisdiction">
                                    checked="true"
                                </#if>
                            </#if>
                    /> Investment entity that is managed by another financial institution and located in a non-participating jurisdiction </div>
                <div><input type="checkbox"
                            <#if crsEntity.entityNumber=="Passive NFE">
                                <#if crsEntity.entityCategory=="Non-financial entities that are not active non-financial entities">
                                    checked="true"
                                </#if>
                            </#if>
                    />  NFE that is not an active NFE</div>
            </td>
        </tr>
    </table>
    <h4>Part III Controlling Persons (Please complete this part if the entity Account Holder is a Passive NFE)</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="2">Indicate the name of all controlling person(s) of the Account Holder in the table below. If no natural person exercises control over
                an entity which is a legal person, the controlling person will be the individual holding the position of senior managing official. Please complete
                CRS Controlling Person Self-Certification Form for each controlling person.</td>
        </tr>
        <tr>
            <td>1</td>
            <td class="valueTd">
                ${(crsEntityContrs[0].lastName + crsEntityContrs[0].firstName)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>2</td>
            <td class="valueTd">
                ${(crsEntityContrs[1].lastName + crsEntityContrs[1].firstName)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>3</td>
            <td class="valueTd">
                ${(crsEntityContrs[2].lastName + crsEntityContrs[2].firstName)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>4</td>
            <td class="valueTd">
                ${(crsEntityContrs[3].lastName + crsEntityContrs[3].firstName)!"N/A"}
            </td>
        </tr>
        <tr>
            <td>5</td>
            <td class="valueTd">
                ${(crsEntityContrs[4].lastName + crsEntityContrs[4].firstName)!"N/A"}
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
    <h4>Part IV Jurisdiction of Residence and Taxpayer Identification Number or its Functional Equivalent (“TIN”)*</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="2">
                <ul class="specialUl" style="margin: 0">
                    <li>Please complete the following table indicating (a) the jurisdiction of residence (including Hong Kong) where the Account Holder is a
                        resident for tax purpose and (b) the Account Holder’s TIN for each jurisdiction indicated. Indicate all (not restricted to three)
                        jurisdictions of residence.
                    </li>
                    <li style="font-size: 14px;"> If the Account Holder is a tax resident of Hong Kong, the TIN is the Hong Kong Business Registration Number</li>
                    <li>If the Account Holder is not a tax resident in any jurisdiction (e.g. fiscally transparent), indicate the jurisdiction in which its place of
                        effective management is situated.
                    </li>
                </ul>
            </td>
        </tr>
        <tr>
            <td style="width: 120px;" class="valueTd">Do you have a Tax Identification Number?</td>
            <td style="height: 36px;" class="valueTd">
                <#if crsEntity.yesTaxNumber ?? >
                    Yes
                <#else >
                    No
                </#if>
            </td>
        </tr>
        <tr>
            <td style="width: 150px;" class="valueTd">Tax Identification Number</td>
            <td class="valueTd" style="height: 36px;">
                <#if  crsEntity.yesTaxNumber ?? >
                    ${(crsEntity.yesTaxNumber)!"N/A"}
                <#else >
                    N/A
                </#if>
            </td>
        </tr>
        <tr>
            <td style="width: 100px;">Reason</td>
            <td class="valueTd" style="height: 36px;">
                <#if  crsEntity.noTaxNumber ??>
                    <#if crsEntity.noTaxNumber =="The controller cannot obtain the tax number. If you choose this reason, please explain below why the controlling party can not obtain the tax number.">
                        ${(crsEntity.reason)!"N/A"}
                    <#else >
                        ${(crsEntity.noTaxNumber)!"N/A"}
                    </#if>
                <#else >
                    N/A
                </#if>
            </td>
        </tr>
    </table>
    <br />
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