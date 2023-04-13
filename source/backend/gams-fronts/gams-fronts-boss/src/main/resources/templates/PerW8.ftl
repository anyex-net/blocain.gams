
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <style>
        body{
            font-family:Source Han Sans CN Regular,Serif;
            font-size: 8pt;
            line-height: 1.1;
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
            width: 5px;
            height: 5px;
            line-height: 5px;
            left: -12px;
            top: 4px;
        }
        .relates li:nth-child(n)::before{
            content: none;
        }
        ul .relates::before{
            content: none;
        }
        ul{
            list-style-type:none;
            padding-left: 12px;
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
            border-bottom: 1px solid #000;
            height: 18px;
            line-height: 18px;
        }
        .PartI{
            color: #fff;
            background: #000;
            width: 40px;
            text-align: center;
        }
        table{
            width: 100%;
        }
        tr{
            display: flex;
        }
        tr .valueTd{
            /*word-wrap: break-word;*/
        }
        td{
            line-height: 18px;
            flex: 1;
            word-break: break-word;
            padding: 4px;
        }
        h4{
            padding-bottom: 6px;
        }

    </style>
</head>
<body>
    <div id="header">
        <div id="header-left" class="header-left">
            <img src="data:${fileType};base64,${file64Str}" width="150px" style="display: inline-block" />
            <span style="margin-left: 20px;text-align: right;float: right">
<#--                <div>MOF Securities W-8 Individual</div>-->
<#--                <div>Self-Certification Instructions to Account Holder</div>-->
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
                <div>Cat. No. 25047Z &#160;  W-8BEN  (Rev. 7-2017)</div>
<!--                <div>Page <span id="pages"> of </span></div>-->
<!--                <div>第 <span id="pages">页，共 </span>页</div>-->
            </span>
        </div>
    </div>
    <div class="page1">
        <div style="border-bottom: 1px solid #000;height: 110px">
            <div style="display: inline-block;width: 20%;border-right: 1px solid #000;height: inherit;vertical-align: middle;">
                <div>Form <h3 style="font-size: 16px;">W-8BEN</h3></div>
                <p>(Rev. July 2017)</p>
                <div>Department of the Treasury  Internal Revenue Servic</div>
            </div>
            <div style="display: inline-block;width: 60%;border-right: 1px solid #000;height: inherit;vertical-align: middle;text-align: center;">
                <h3 style="font-size: 14px;">Certificate of Foreign Status of Beneficial Owner for United     States Tax Withholding and Reporting (Individuals)</h3>
                <div style="padding-bottom: 5px;"><strong>For use by individuals. Entities must use Form W-8BEN-E.</strong></div>
                <div style="padding-bottom: 5px;"> <strong>Go to www.irs.gov/FormW8BEN for instructions and the latest information.</strong> </div>
                <div style="padding-bottom: 5px;"><strong>Give this form to the withholding agent or payer. Do not send to the IRS.</strong></div>
            </div>
            <div style="display: inline-block;width: 14%;height: inherit;line-height: 115px; vertical-align: middle;font-size: 10px;text-align: center;">
                OMB No. 1545-1621
            </div>
        </div>
        <div>
            <div class="clearfix;" style="margin-top: 5px">
                <div style="float: left;width: 200px">Do NOT use this form if: </div>
                <div style="float: right;width: 140px;text-align: right;">Instead, use Form: </div>
            </div>
            <ul>
                <li class="clearfix">
                    <div style="float: left">You are NOT an individual . .  . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .</div>
                    <div style="float: right;width: 55px;text-align: right;">W-8BEN-E </div>
                </li>
                <li class="clearfix">
                    <div style="float: left;width: 660px;">You are a U.S. citizen or other U.S. person, including a resident alien individual . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . </div>
                    <div style="float: right;width: 25px;text-align: right;"> W-9 </div>
                </li>
                <li class="clearfix">
                    <div style="width: 620px;">You are a beneficial owner claiming that income is effectively connected with the conduct of trade or business within the U.S.</div>
                    <div style="float: left;">(other than personal services)  . . . . . . . . . . . . .. . . . . . . . . . . . .. . . . . . . . . . . . . . . . . .. . . . . . . . . . . .. . . . . . . . . . . . . . . . . .. . . . . . . . . . . . . . . . . .</div>
                    <div style="float: right;width: 40px;text-align: right;">W-8ECI </div>
                </li>
                <li class="clearfix">
                    <div style="float: left">You are a beneficial owner who is receiving compensation for personal services performed in the United States  . . . . . . . . . . . . .</div>
                    <div style="float: right;width: 75px;text-align: right;"> 8233 or W-4 </div>
                </li>
                <li class="clearfix">
                    <div style="float: left;width: 645px;"> You are a person acting as an intermediary . . . . . . . . . . . . . . . . . . . .. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . </div>
                    <div style="float: right;text-align: right;width: 45px"> W-8IMY </div>
                </li>
            </ul>
            <hr />
        </div>
        <div><strong>Note:</strong> If you are resident in a FATCA partner jurisdiction (i.e., a Model 1 IGA jurisdiction with reciprocity), certain tax account information may be provided to your jurisdiction of residence.</div>
        <div class="Beneficial">
            <div style="display: inline-block" class="PartI">Part I</div>
            <div style="display: inline-block;width: 400px;"><strong>Identification of Beneficial Owner</strong> (see instructions) </div>
        </div>
        <div style="border-bottom: 1px solid #000">
            <div style="display: inline-block;width: 65%;border-right: 1px solid #000">
                <div>1.Name of individual who is the beneficial owner</div>
                <div>
                    ${(perInfo.lastName)!"N/A"}
                    ${(perInfo.firstName)!"N/A"}
                </div>
            </div>
            <div style="display: inline-block;width: 34%;">
                <div>2.Country of citizenship</div>
                <div>
                    ${(personalAddress.country.enName)!"N/A"}
                </div>
            </div>
        </div>
        <div style="border-bottom: 1px solid #000">
            <div>3.Permanent residence address (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address. </div>
            <div>
                ${(personalAddress.address)!"N/A"}
            </div>
        </div>
        <div style="border-bottom: 1px solid #000">
            <div style="display: inline-block;width: 82%;border-right: 1px solid #000">
                <div>City or town, state or province. Include postal code where appropriate.</div>
                <div>
                    ${(personalAddress.city + ", " + personalAddress.rovince + ", " + personalAddress.zipCode)!"N/A"}
                </div>
            </div>
            <div style="display: inline-block;width: 16%;">
                <div>Country</div>
                <div>
                    ${(personalAddress.country.enName)!"N/A"}
                </div>
            </div>
        </div>
        <div style="border-bottom: 1px solid #000">
            <div>4.Mailing address (if different from above)  </div>
            <div>
                ${(mailAddress.address)!"N/A"}
            </div>
        </div>
        <div style="border-bottom: 1px solid #000">
            <div style="display: inline-block;width: 82%;border-right: 1px solid #000">
                <div>City or town, state or province. Include postal code where appropriate.</div>
                <div>
                    ${(mailAddress.city + ", "+mailAddress.rovince + ", "+mailAddress.zipCode)!"N/A"}
                </div>
            </div>
            <div style="display: inline-block;width: 16%;">
                <div>Country</div>
                <div>
                    ${(mailAddress.country.enName)!"N/A"}
                </div>
            </div>
        </div>
        <div style="border-bottom: 1px solid #000">
            <div style="display: inline-block;width: 58%;">
                <div>5.U.S. taxpayer identification number (SSN or ITIN), if required (see instructions)</div>
                <div>
                    ${(wbenContract.usaItin)!"N/A"}
                </div>
            </div>
            <div style="display: inline-block;width:40%;border-left: 1px solid #000;padding-left: 5px;">
                <div> 6.Foreign tax identifying number (see instructions) </div>
                <div>
                    ${(wbenContract.overseasTaxNumber)!"N/A"}
                </div>
            </div>
        </div>
        <div style="border-bottom: 1px solid #000">
            <div style="display: inline-block;width: 40%;border-right: 1px solid #000">
                <div>7.Reference number(s) (see instructions)</div>
                <div>
                    ${(wbenContract.referenceNumber)!"N/A"}
                </div>
            </div>
            <div style="display: inline-block;width: 58%;">
                <div> 8.Date of birth (see instructions)</div>
                <div>
                    ${(perInfo.birthday)!"N/A"}
                </div>
            </div>
        </div>
        <div class="BeneficialII">
            <div style="display: inline-block" class="PartI">Part II </div>
            <div style="display: inline-block;width: 600px;"><strong>Claim of Tax Treaty Benefits</strong> (for chapter 3 purposes only) (see instructions) </div>
        </div>
        <div>
            <div>
                9.I certify that the beneficial owner is a resident of
                <div style="width: 340px;padding-left: 10px; border-bottom: 1px solid #000;display: inline-block"> ${(wbenContract.usaCountryAgreement.enName)!"N/A"} </div>
                within the meaning of the income tax treaty between the United States and that country.
            </div>
            <div>
                10.Special rates and conditions (if applicable—see instructions): The beneficial owner is claiming the provisions of Article and paragraph
                <div style="width: 290px;padding-left: 10px; border-bottom: 1px solid #000;display: inline-block"></div>
                of the treaty identified on line 9 above to claim a<div style="width: 50px;padding-left: 8px; border-bottom: 1px solid #000;display: inline-block"></div>
                % rate of withholding on (specify type of income):<div style="width: 790px;padding-left: 10px; border-bottom: 1px solid #000;display: inline-block"></div>
                Explain the additional conditions in the Article and paragraph the beneficial owner meets to be eligible for the rate of withholding:
                <div style="border-bottom: 1px solid #000;display: inline-block;word-break: break-all">

                </div>
            </div>
        </div>
        <br />
        <div class="Beneficial">
            <div style="display: inline-block;width: 50px;" class="PartI">Part III </div>
            <div style="display: inline-block;width: 600px;"><strong>Certification </strong> </div>
        </div>
        <div>
            Under penalties of perjury, I declare that I have examined the information on this form and to the best of my knowledge and belief it is true, correct, and complete. I further certify under penalties of perjury that:
        </div>
        <ul>
            <li> I am the individual that is the beneficial owner (or am authorized to sign for the individual that is the beneficial owner) of all the income to which this form relates or am using this form to document myself for chapter 4 purposes,</li>
            <li>The person named on line 1 of this form is not a U.S. person,</li>
            <li>The income to which this form relates is:</li>
            <ul style="list-style-type: none" class="relates">
                <li>(a) not effectively connected with the conduct of a trade or business in the United States, </li>
                <li>(b) effectively connected but is not subject to tax under an applicable income tax treaty, or </li>
                <li>(c) the partner’s share of a partnership's effectively connected income,</li>
            </ul>
            <li>The person named on line 1 of this form is a resident of the treaty country listed on line 9 of the form (if any) within the meaning of the income tax treaty between the United States and that country, and</li>
            <li>For broker transactions or barter exchanges, the beneficial owner is an exempt foreign person as defined in the instructions.</li>
            <li class="relates">Furthermore, I authorize this form to be provided to any withholding agent that has control, receipt, or custody of the income of which I am the beneficial owner or any withholding agent that can disburse or make payments of the income of which I am the beneficial owner. <strong>I agree that I will submit a new form within 30 days if any certification made on this form becomes incorrect.</strong></li>
        </ul>
        <div class="clearfix">
            <div style="float: left;font-size: 14px;font-weight: bolder;width: 70px;">Sign Here </div>
            <div style="float: right">
                <div>
                    <div style="display: inline-block">
                        <div style="width: 420px;height: 20px;border-bottom: 1px solid #000;"></div>
                        <div style="width: 420px">Signature of beneficial owner (or individual authorized to sign for beneficial owner) </div>
                    </div>
                    <div style="display: inline-block">
                        <div style="width: 200px;height: 20px;border-bottom: 1px solid #000;"></div>
                        <div style="width: 200px;text-align: center">Date (MM-DD-YYYY)  </div>
                    </div>
                </div>
                <div>
                    <div style="display: inline-block">
                        <div style="width: 210px;padding-left: 10px;height: 20px;line-height: 24px; border-bottom: 1px solid #000;">
                            ${(perInfo.lastName)!"N/A"}
                            ${(perInfo.firstName)!"N/A"}
                        </div>
                        <div style="width: 220px;">Print name of signer </div>
                    </div>
                    <div style="display: inline-block">
                        <div style="width: 400px;height: 20px;border-bottom: 1px solid #000;"></div>
                        <div style="width: 390px; padding-left:10px;text-align: center;">Capacity in which acting (if form is not signed by beneficial owner)  </div>
                    </div>
                </div>
            </div>

        </div>
        <hr />
<!--        <div>-->
<!--            <div style="display: inline-block;width:55%;">For Paperwork Reduction Act Notice, see separate instructions.</div>-->
<!--            <div style="display: inline-block;width:14%;">Cat. No. 25047Z </div>-->
<!--            <div style="display: inline-block;width:29%;text-align: right">Form  W-8BEN  (Rev. 7-2017)</div>-->
<!--        </div>-->
    </div>

</body>
</html>