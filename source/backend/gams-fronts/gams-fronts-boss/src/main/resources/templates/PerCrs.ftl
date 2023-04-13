
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
            <div>MOF Securities CRS Individual</div>
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
        <strong>MOF Securities Limited</strong> is collecting the information in this form to comply with legal requirements based on the Organization for Economic Cooperation and Development (OECD) (2014) Standard for Automatic Exchange of Financial Account Information in Tax Matters (referred to as the Common Reporting Standard, or CRS). Capitalized terms not defined below are defined in the CRS.
    </div>
    <br />
    <div>Please note:</div>
    <ul class="specialUl">
        <li>This form is for Individual Account Holders only. If the Account Holder is an entity, please complete the CRS Entity Self-Certification.</li>
        <li>This form must be signed and dated.</li>
        <li>Complete all parts of this form as directed and provide all information requested.</li>
        <li>If you have any questions regarding the Individual Account Holder's tax residence or how to complete this self-certification, consult your tax advisor.</li>
        <li>The terms Account Holder and TIN are defined below.</li>
        <li>If any information on this form becomes incorrect or incomplete, a new form must be submitted within 30 days of the change.</li>
        <br />
        <li>Account Holder</li>
        <ul>
            <li>For a Depository Institution or a Custodial Institution, the term <strong>Account Holder</strong> means the person listed or identified as the holder of a Financial Account by the Financial Institution that maintains the account. A person, other than a Financial Institution, holding a Financial Account for the benefit or account of another person as agent, custodian, nominee, signatory, investment advisor, or intermediary, is not treated as holding the account. Each account holder of a jointly held account must provide a separate self-certification.</li>
        </ul>
        <ul>
            <li>For an Investment Entity, the term <strong>Account Holder</strong> means any investor listed or identified as holding an equity (including a partnership interest or an interest in a trust) or debt interest in the Investment Entity. A person, other than a Financial Institution, holding an equity or debt interest for the benefit or account of another person as agent, custodian, nominee, signatory, investment advisor, or intermediary, is not treated as holding the account.</li>
        </ul>
        <ul>
            <li>For a Cash Value Insurance Contract or an Annuity Contract, the term <strong>Account Holder</strong> means any person entitled to access the Cash Value or change the beneficiary of the contract. If no person can access the Cash Value or change the beneficiary, the <strong>Account Holder</strong> is any person named as the owner in the contract and any person with a vested entitlement to payment under the terms of the contract. Upon the maturity of a Cash Value Insurance Contract or an Annuity Contract, each person entitled to receive a payment under the contract is treated as an <strong>Account Holder</strong></li>
        </ul>
        <br />
        <li>
            <strong>TIN</strong> means a Taxpayer Identification Number (or a <strong>functional equivalent</strong>). A <strong>TIN</strong> is a unique combination of letters or numbers assigned by a jurisdiction to an individual or an <strong>Entity</strong> and used to identify the individual or Entity for the purposes of administering the tax laws of such
            jurisdiction. Some jurisdictions do not issue a <strong>TIN</strong>. However, these jurisdictions often utilize some other high integrity number with an equivalent level of identification (a <strong>functional equivalent</strong>).
        </li>
    </ul>
    <br />
    <div>To return forms to MOF Securities Limited, please scan and upload as attachment to message sent email to <strong>info@mofsecurities.com</strong></div>
    <br />
    <div>
        Please call +86 4006589990 if you have any questions.
    </div>
    <br />
    <h4>Part I. Identification of Individual Account Holder</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="2">
                <div>1.Last Name</div>
                <div class="valueTd">
                    ${(perInfo.lastName)!"N/A"}
                </div>
            </td>
            <td colspan="2">
                <div>2.First Name</div>
                <div class="valueTd">
                    ${(perInfo.firstName)!"N/A"}
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="4">
                <div>3.Current residence address (number, street name, apt)</div>
                <div class="valueTd">
                    ${(personalAddress.address)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Town/City/Province/County/State</div>
                <div class="valueTd">
                    ${(personalAddress.city + "/"+ personalAddress.rovince)!"N/A"}
                </div>
            </td>
            <td>
                <div>Country (Do not abbreviate)</div>
                <div class="valueTd">
                    ${(personalAddress.country.enName)!"N/A"}
                </div>
            </td>
            <td>
                <div>Postal code</div>
                <div class="valueTd">
                    ${(personalAddress.zipCode)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div>4.Mailing address (number, street name, apt) – complete if different from current residence address above</div>
                <div class="valueTd">
                    ${(crs.otherAddresses)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>Town/City/Province/County/State</div>
                <div class="valueTd">
                    ${(crs.otherProvince + "/"+ crs.otherCity)!"N/A"}
                </div>
            </td>
            <td>
                <div>Country (Do not abbreviate)</div>
                <div class="valueTd">
                    ${(crs.otherCountry)!"N/A"}
                </div>
            </td>
            <td>
                <div>Postal code</div>
                <div class="valueTd">
                    ${(crs.otherZipCode)!"N/A"}
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>5.Date of birth</div>
                <div class="valueTd">
                    ${(perInfo.birthday)!"N/A"}
                </div>
            </td>
            <td colspan="2">
                <div>6.Place of birth</div>
                <div class="valueTd">
                    <#if personalAddress.otherBirthCountry.enName ??>
                        ${(personalAddress.otherBirthCountry.enName)!"N/A"}
                    <#else >
                        ${(personalAddress.country.enName)!"N/A"}
                    </#if>
                </div>
            </td>
        </tr>
    </table>
    <h4>PART II. Country(ies) of Tax Residence and Taxpayer Identification Number(s) (TIN(s))</h4>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="3">
                <div>
                    7.Complete the following table to indicate:
                    <ul>
                        <li>(i) The country or countries of which the Individual Account Holder is a tax resident; and</li>
                        <li>(ii) The <strong>TIN</strong> (or a <strong>functional equivalent</strong>) for each country of tax residence.</li>
                    </ul>
                </div>
<!--                <div>-->
<!--                    Do not abbreviate country names. If no <strong>TIN</strong> is provided, enter reason A, B, or C.-->
<!--                    <ul>-->
<!--                        <li><strong>Reason A</strong> - The country of which the <strong>Individual Account Holder</strong> is a tax resident does not issue TINs to its tax residents.</li>-->
<!--                        <li><strong>Reason B</strong> - The <strong>Individual Account Holder</strong> is otherwise unable to obtain a TIN or equivalent number.</li>-->
<!--                        <li><strong>Reason C</strong> - The domestic law of the I<strong>Individual Account Holder’s</strong> country of tax residence does not require the collection of the TIN issued by that country.</li>-->
<!--                    </ul>-->
<!--                </div>-->
                <div>If the <strong>Individual Account Holder</strong> is a tax resident in more than three countries, attach a separate sheet.</div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Country of tax residence</div>
            </td>
            <td>
                <div><strong>TIN</strong></div>
            </td>
            <td>
                <div>If no <strong>TIN</strong> is provided, enter Reason</div>
            </td>
        </tr>
        <#if taxes ??>
        <tr>
            <td>
                <div>
                    ${(taxes[0].taxResidenceCountry.enName)!"N/A"}
                </div>
            </td>
            <td style="min-width: 50px;">
                <div>
                    ${(crs.yesTaxNumber)!"N/A"}
                </div>
            </td>
            <td>
                <div>
                    ${(crs.noTaxNumber)!"N/A"}
                </div>
            </td>
        </tr>
        </#if>
        <tr>
            <td colspan="4">
                <div>If the controller cannot obtain the tax number, provide the reason</div>
            </td>
        </tr>
        <tr>
            <td>
                <div>Country Name</div>
            </td>
            <td colspan="2">
                <div>Reason</div>
            </td>
        </tr>

        <tr>
            <td style="height: 25px">
                <div>
                    <#if taxes ??>
                        <#if (crs.noTaxNumber) ??>
                            ${(taxes[0].taxResidenceCountry.enName)!"N/A"}
                        </#if>
                    </#if>
                </div>
            </td>
            <td colspan="2">
                <div>
                    <#if taxes ??>
                        <#if (crs.noTaxNumber) ??>
                            ${(crs.reason)!"N/A"}
                        </#if>
                    </#if>
                </div>
            </td>
        </tr>
    </table>
    <br />
    <br />
    <h4>PART III. Declaration and Signature</h4>
    <p>1. I declare that all statements made in this Individual Self-Certification are, to the best of my knowledge and belief, true, correct, and complete.</p>
    <p>2. I agree that if there is a change in circumstances that affects the information provided in this Individual Self-Certification or causes the information provided in this Individual Self-Certification to become incorrect or incomplete, the Individual Account Holder will provide a new Individual Self-Certification within 30 days of the change in circumstances.</p>
    <p>3. I certify that I am the Individual Account Holder identified above, or am authorized to sign for the Individual Account Holder, for all the account(s) to which this form relates.</p>
    <p>4. I understand that to the extent permitted under law (a) information regarding the Individual Account Holder and the Individual Account Holder's account(s) may be reported to the authorities of the country(ies) where the account(s) are maintained; and (b) those authorities may provide such information to the country(ies) where the Individual Account Holder is a tax resident pursuant to the applicable intergovernmental agreement(s) regarding the exchange of financial account information.</p>
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