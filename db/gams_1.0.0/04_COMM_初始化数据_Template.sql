-- 初始化消息模版数据;
TRUNCATE table Template;

insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000001, 'sms_send_valid_code', 'en_US', 'sms', 'Send mobile phone verification code', 'Your verification code is:%s', '发送手机验证码', 200000000000, 1501467844534);
insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000002, 'sms_send_valid_code', 'zh_CN', 'sms', '发送手机验证码', 'Your verification code is:%s', '发送手机验证码', 200000000000, 1501467844534);

insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000003, 'sms_send_forget_code', 'en_US', 'sms', 'Send mobile phone verification code', 'Your verification code is:%s', '发送手机验证码', 200000000000, 1501467844534);
insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000004, 'sms_send_forget_code', 'zh_CN', 'sms', '发送手机验证码', 'Your verification code is:%s', '发送手机验证码', 200000000000, 1501467844534);

insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000005, 'sms_send_change_code', 'en_US', 'sms', 'Send mobile phone verification code', 'Your verification code is:%s', '发送手机验证码', 200000000000, 1501467844534);
insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000006, 'sms_send_change_code', 'zh_CN', 'sms', '发送手机验证码', 'Your verification code is:%s', '发送手机验证码', 200000000000, 1501467844534);

insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (210000000007, 'email_forget_pass_code', 'en_US', 'email', 'Password retrieval', '<div style="padding: 10px;margin: 10px;border: 1px solid black;"><p>rest password url is:<strong>%s</strong></p><p>Thanks.</p><p>The GAMS Team</p><div style="border-bottom: 1px solid black;width: 300px;">', '发送忘记密码重置邮件', 200000000000, 1501467844534);
insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (210000000008, 'email_forget_pass_code', 'zh_CN', 'email', '发送忘记密码重置邮件', '<div style="padding: 10px;margin: 10px;border: 1px solid black;"><p>rest password url is:<strong>%s</strong></p><p>Thanks.</p><p>The GAMS Team</p><div style="border-bottom: 1px solid black;width: 300px;">', '发送忘记密码重置邮件', 200000000000, 1501467844534);

insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000009, 'email_send_valid_code', 'en_US', 'email', 'Send email verification code', 'Your verification code is:%s', '邮件验证码', 200000000000, 1501467844534);
insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000010, 'email_send_valid_code', 'zh_CN', 'email', '邮件验证码', 'Your verification code is:%s', '邮件验证码', 200000000000, 1501467844534);

insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000011, 'email_send_change_code', 'en_US', 'email', 'Send email verification code', 'Your verification code is:%s', '发送变更邮箱验证码', 200000000000, 1501467844534);
insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (200000000012, 'email_send_change_code', 'zh_CN', 'email', '发送变更邮箱验证码', 'Your verification code is:%s', '发送变更邮箱验证码', 200000000000, 1501467844534);

insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (210000000013, 'email_send_register', 'en_US', 'email', 'Register confirm', '<div style="padding: 10px;margin: 10px;border: 1px solid black;"><p>account register confirm link url is:<strong>%s</strong></p><p>Thanks.</p><p>The MOFSecurities Team</p><div style="border-bottom: 1px solid black;width: 300px;"></div><p>Copyright 2019 MOFSecurities, Inc, All rights reserved.</p></div>', '发送注册确认邮件', 200000000000, 1501467844534);
insert into Template (id, tpl_key, lang, type, title, content, dest, create_by, create_date)
values (210000000014, 'email_send_register', 'zh_CN', 'email', '发送注册确认邮件', '<div style="padding: 10px;margin: 10px;border: 1px solid black;"><p>account register confirm link url is:<strong>%s</strong></p><p>Thanks.</p><p>The MOFSecurities Team</p><div style="border-bottom: 1px solid black;width: 300px;"></div><p>Copyright 2019 MOFSecurities, Inc, All rights reserved.</p></div>', '发送注册确认邮件', 200000000000, 1501467844534);

INSERT INTO Template (ID, tpl_key, LANG, TYPE, TITLE, CONTENT, DEST, create_by, create_date) 
VALUES (200000000015, 'email_remind_login', 'en_US', 'email', 'Successful Login', '<div style="padding: 10px;margin: 10px;border: 1px solid black;"><img src="%s" style="float: right;display: block;" /><h1>Successful Login</h1><p>Account:<u>%s</u></p><p>Device: [%s]</p><p>Time: [%s] (EDT)</p><p>IP Address:%s</p><p>If this was not you, please change your passwords immediately, as someone else may be accessing your account.</p><p>If you need help, please contact with us.</p><p>Thanks.</p><p>The MOFSecurities Team</p><div style="border-bottom: 1px solid black;width: 300px;"></div><p>Copyright 2019 MOFSecurities, Inc, All rights reserved.</p></div>', '帐户登录提醒邮件', 200000000000, 1511245692686);
INSERT INTO Template (ID, tpl_key, LANG, TYPE, TITLE, CONTENT, DEST, create_by, create_date) 
VALUES (200000000016, 'email_remind_login', 'zh_CN', 'email', 'Successful Login', '<div style="padding: 10px;margin: 10px;border: 1px solid black;"><img src="%s" style="float: right;display: block;" /><h1>Successful Login</h1><p>Account:<u>%s</u></p><p>Device: [%s]</p><p>Time: [%s] (EDT)</p><p>IP Address:%s</p><p>If this was not you, please change your passwords immediately, as someone else may be accessing your account.</p><p>If you need help, please contact with us.</p><p>Thanks.</p><p>The MOFSecurities Team</p><div style="border-bottom: 1px solid black;width: 300px;"></div><p>Copyright 2019 MOFSecurities, Inc, All rights reserved.</p></div>', '帐户登录提醒邮件', 200000000000, 1511245692686);


commit;
