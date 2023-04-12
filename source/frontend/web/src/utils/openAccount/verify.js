// ****** 表单验证 及 一些公共方法 *********
import $vue from '../../main.js';

// 不能包含中文验证
const getNotChinese = (rule, value, callback) => {
  const ch = /^[^\u4e00-\u9fa5]+$/;
  if (value === '' || value === undefined || value === null || String(value).length === 0 || Object.keys(value).length === 0) {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
  } else {
    if (ch.test(value) === true) {
      callback();
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.noChinese'))); // $i18n - 请不要输入中文字符
    }
  }
};
// 不能包含中文验证  --可为空
const getNotChinese1 = (rule, value, callback) => {
  const ch = /^[^\u4e00-\u9fa5]+$/;
  if (value !== '') {
    if (ch.test(value) === true) {
      callback();
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.noChinese'))); // $i18n - 请不要输入中文字符
    }
  } else {
    callback();
  }
};
// 不能包含中文验证  --可为空
const getNotNumber = (rule, value, callback) => {
  const ch = /^[^0-9]+$/;
  if (value !== '') {
    if (ch.test(value) === true) {
      getNotChinese(rule, value, callback);
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.number1'))); // $i18n - 不允许有数字
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.position'))); // $i18n - 请输入职位
  }
};
// 不能包含中文验证  手动 SD -
const getnotChineseSD = (value, yanzhengCallback) => {
  const ch = /^[^\u4e00-\u9fa5]+$/;
  if (ch.test(value) === true) {
    yanzhengCallback(true);
  } else {
    yanzhengCallback(false);
  }
};
// 纯英文字母验证
const getEnglishQ = (rule, value, callback) => {
  const zimu = /^[A-Za-z]+$/;
  if (zimu.test(value) === true) {
    callback();
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.letter'))); // $i18n - 请输入英文字母,不允许有特殊符号
  }
};

// 出生日期验证 -大于18岁
const getBirth = (rule, value, callback, val = '') => {
  let dataTS = null;
  getPresentTime().then(res => {
    const time = new Date(Number(res));
    const y = time.getFullYear();
    const m = time.getMonth() + 1;
    const d = time.getDate();
    if (!(value instanceof Date)) {
      dataTS = String(value).split('-');
    } else {
      dataTS = getNowDate2(value); // data => YY-MM-DD
      dataTS = String(dataTS).split('-');
    }

    if (value !== '') {
      if (Number(dataTS[0]) >= 1900) {
        if (val === 'portfolio' || val === 'margin') {
          if (Number(dataTS[0]) <= y - 21) {
            if (Number(dataTS[0]) === y - 21) {
              if (Number(dataTS[1]) < m) {
                callback();
              } else if (Number(dataTS[1]) === m) {
                if (Number(dataTS[2]) <= d) {
                  callback();
                } else {
                  callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint1'))); // $i18n - 申请者必须年满21周岁才能开立投资组合或保证金账户
                }
              } else {
                callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint1'))); // $i18n - 申请者必须年满21周岁才能开立投资组合或保证金账户
              }
            } else {
              callback();
            }
          } else {
            callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint1'))); // $i18n - 申请者必须年满21周岁才能开立投资组合或保证金账户
          }
        } else {
          if (Number(dataTS[0]) <= y - 18) {
            if (Number(dataTS[0]) === y - 18) {
              if (Number(dataTS[1]) < m) {
                callback();
              } else if (Number(dataTS[1]) === m) {
                if (Number(dataTS[2]) <= d) {
                  callback();
                } else {
                  callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint2'))); // $i18n - 不允许未超过18周岁
                }
              } else {
                callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint2'))); // $i18n - 不允许未超过18周岁
              }
            } else {
              callback();
            }
          } else {
            callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint2'))); // $i18n - 不允许未超过18周岁
          }
        }
      } else {
        callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint3'))); // $i18n - 目前我们的系统不接受1900年以前的日期,请重新输入
      }
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint4'))); // $i18n - 请选择出生日期
    }
  });
};
// 账户保证金 年龄满21限制
const getAccountMargin = (rule, value, callback, birth = '') => {
  let dataTS = null;
  getPresentTime().then(res => {
    const time = new Date(Number(res));
    const y = time.getFullYear();
    const m = time.getMonth() + 1;
    const d = time.getDate();
    if (!(birth instanceof Date)) {
      dataTS = String(birth).split('-');
    } else {
      dataTS = getNowDate2(birth);
      dataTS = String(dataTS).split('-');
    }
    if (value !== '') {
      if (value === 'portfolio' || value === 'margin') {
        if (Number(dataTS[0]) <= y - 21) {
          if (Number(dataTS[0]) === y - 21) {
            if (Number(dataTS[1]) < m) {
              callback();
            } else if (Number(dataTS[1]) === m) {
              if (Number(dataTS[2]) <= d) {
                callback();
              } else {
                callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint1'))); // $i18n - 申请者必须年满21周岁才能开立投资组合或保证金账户
              }
            } else {
              callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint1'))); // $i18n - 申请者必须年满21周岁才能开立投资组合或保证金账户
            }
          } else {
            callback();
          }
        } else {
          callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.dateHint1'))); // $i18n - 申请者必须年满21周岁才能开立投资组合或保证金账户
        }
      } else {
        callback();
      }
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
    }
  });
};
const getPresentTime = () => {
  return new Promise(function(resolve, reject) {
    $vue.$http({
      url: '/common/server/timestamp',
      method: 'get'
    })
      .then(res => {
        if (res.code === 200) {
          resolve(res.object);
        } else {
          $vue.$message.error(res.code || 'Has Error');
        }
      })
      .catch(error => {
        $vue.$message.error(error || 'Has Error');
      });
  });
};
// 字母+数字验证
const getEnglishNumber = (rule, value, callback) => {
  const num = /^[A-Za-z0-9]+$/;
  if (num.test(value) === true) {
    callback();
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.ALPHANUM'))); // $i18n - 请输入英文字母或数字,不允许有特殊符号
  }
};
// 数字正则验证
const getNumber = (rule, value, callback) => {
  const num = /^[0-9]*$/;
  if (value !== '') {
    if (num.test(value) === true) {
      callback();
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.number3'))); // $i18n - 请输入数字,不允许有特殊符号
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
  }
};
// 网址正则验证
const getWangzhi = (rule, value, callback) => {
  const num = /^(www)(\.[\w\d]+)+(\.(\w){2,})$/; // 以www开头的url，
  if (value !== '') {
    if (num.test(value) === true) {
      callback();
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.webSite'))); // $i18n - 网址必须以www开头，且必须是个网 ...
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
  }
};
// 性别验证
const getGender = (rule, value, mate, callback) => {
  if (value !== '') {
    if (mate === '40001') {
      if (value === '29001') {
        callback();
      } else {
        callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.sex'))); // $i18n - 请输入正确性别,和称呼保持一致
      }
    } else if (mate === '40002' || mate === '40003') {
      if (value === '29002') {
        callback();
      } else {
        callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.sex'))); // $i18n - 请输入正确性别,和称呼保持一致
      }
    } else {
      callback();
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
  }
};
// 一级是否btn逻辑判断
const getstairIf = (rule, value, mate, callback) => {
  if (mate === true) {
    if (value === '') {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
    } else {
      getNotChinese(rule, value, callback);
    }
  } else {
    callback();
  }
};
// 签名是否一致逻辑判断
const getSignature = (rule, value, mate, callback) => {
  if (value !== '') {
    if (mate) {
      callback();
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.signature'))); // $i18n - 签名不一致
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
  }
};
// 二级逻辑判断
const getstairIfTwo = (rule, value, mate, callback) => {
  if (mate.one === true) {
    if (value === '') {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
    } else {
      if (mate.two === true) {
        getNotChinese(rule, value, callback);
      } else {
        callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
      }
    }
  } else {
    callback();
  }
};
// 一级是否btn逻辑判断--里面具体某个值可填可不填的判断
const getstairIfone = (rule, value, mate, callback, Passable) => {
  if (mate === true) {
    if (value === '') {
      callback();
    } else {
      getNotChinese(rule, value, callback);
    }
  } else {
    callback();
  }
};
// 纳税识别号 -- 个人
const getSWnasuihao = (rule, value, array1, callback) => {
  for (let i = 0; i < array1.length; i++) {
    if (array1[i].suibie === value) {
      if (array1[i].isSuiwuNO === true) {
        if (value !== '') {
          if (value === 'qita') {
            if (array1[i].suiwuQita) {
              getNotChinese(rule, array1[i].suiwuQita, callback);
            } else {
              callback(
                new Error(
                  $vue.$i18n.t('registerOpenAccount.common.verify.else2')
                )
              ); // $i18n - 您已选择其他,请在输入框填写选择其他的理由,如已填写,则忽略本条提示
            }
          } else {
            callback();
          }
        } else {
          callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
        }
      } else {
        callback();
      }
    }
  }
};
// 安全问题
const getSafety = (rule, value, obj, callback) => {
  if (value !== '') {
    for (let i = 0; i < obj.safetyIssue.length; i++) {
      if (obj.safetyIssue[i].dictValue === value) {
        if (obj.type1 === 1) {
          obj.anquan.wenti2 = i + 1;
          obj.anquan.wenti3 = i + 1;
        } else if (obj.type1 === 2) {
          obj.anquan.wenti1 = i + 1;
          obj.anquan.wenti3a = i + 1;
        } else if (obj.type1 === 3) {
          obj.anquan.wenti1a = i + 1;
          obj.anquan.wenti2a = i + 1;
        }
      } else {
        callback();
      }
    }
    callback();
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
  }
};
const getClearZF = (str) => {
  str = str.replace(/,/g, '');
  return str;
};
// 收入资产
const getIncome = (rule, value, obj, callback) => {
  if (value !== '') {
    const newAsset1 = obj.assetVal1.split('-');
    const newAsset2 = obj.assetVal2.split('-');
    const newAsset3 = obj.assetVal3.split('-');
    if (obj.assetVal1.indexOf('<') !== -1) {
      newAsset1[0] = 39999;
    }
    if (obj.assetVal2.indexOf('<') !== -1) {
      newAsset2[0] = 19999;
    }
    if (obj.assetVal3.indexOf('<') !== -1) {
      newAsset3[0] = 19999;
    }
    if (obj.assetVal1.indexOf('>') !== -1) {
      newAsset1[0] = 1000000;
    }
    if (obj.assetVal2.indexOf('>') !== -1) {
      newAsset2[0] = 50000000;
    }
    if (obj.assetVal3.indexOf('>') !== -1) {
      newAsset3[0] = 50000000;
    }
    if (obj.tyep1 === 1) {
      callback();
    } else if (obj.tyep1 === 2) {
      if (obj.assetVal3 !== '') {
        if (Number(newAsset3[0]) <= Number(newAsset2[0])) {
          callback();
        } else {
          callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.asset1'))); // $i18n - 不能低于流动资产净值
        }
      } else {
        callback();
      }
    } else if (obj.tyep1 === 3) {
      if (obj.assetVal2 !== '') {
        if (Number(newAsset3[0]) <= Number(newAsset2[0])) {
          callback();
        } else {
          callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.asset2'))); // $i18n - 不能高于净资产值
        }
      } else {
        callback();
      }
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
  }
};
const getInvestmentObjective = (val, arr) => { // 投资目标及交易意图
  return new Promise(function(resolve, reject) {
    const r = arr.filter(function(x) {
      let status = true;
      if (val === 'income' && x === 'tradingProfits') { // 收入与交易利润冲突
        status = false;
      } else if (val === 'tradingProfits' && x === 'income') {
        status = false;
      } else if (val === 'income' && x === 'speculate') { // 收入与投机冲突
        status = false;
      } else if (val === 'speculate' && x === 'income') {
        status = false;
      } else if (val === 'capitalPreservation' && x === 'tradingProfits') { // 资本保值与交易利润冲突
        status = false;
      } else if (val === 'tradingProfits' && x === 'capitalPreservation') {
        status = false;
      } else if (val === 'capitalPreservation' && x === 'speculate') { // 资本保值与投机冲突
        status = false;
      } else if (val === 'speculate' && x === 'capitalPreservation') {
        status = false;
      }
      return status;
    });
    resolve(r);
  });
};
const getKnowledge = (rule, value, callback) => { // 知识水平 交易经验 status=true则需判断交易经验
  if (value === 'knowledgeLevelIsZero' || value === 'limitedKnowledgeLevel' || value === 'goodKnowledgeLevel' || value === 'RichKnowledgeLevel') {
    if (value === 'goodKnowledgeLevel' || value === 'RichKnowledgeLevel') {
      callback();
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.knowledge'))); // $i18n - 股票知识水平不足。
    }
  } else if (value === 'zero year trading experience' || value === 'one year trading experience' || value === 'two year trading experience' ||
             value === 'three year trading experience' || value === 'four year trading experience' || value === 'five year trading experience' ||
             value === 'six to ten year trading experience' || value === 'greaterThanTen year trading experience') {
    if (value === 'zero year trading experience') {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.experience'))); // $i18n - 所开通产品的交易经验不能为0
    } else {
      callback();
    }
  } else if (value === 'zeroYearTransactionsPerNumber') {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.strokeCount'))); // $i18n - 所开通产品的交易笔数不能为0
  } else {
    callback();
  }
};
const getExperienceTrading = (rule, value, callback) => { // 两年交易经验判断
  if (value === 'zero year trading experience' || value === 'one year trading experience') {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.twoYears'))); // $i18n - 交易经验必须两年以上
  } else {
    callback();
  }
};
// 所有权验证 -- 个人股东 -法人股东
const getOwnership = (rule, value, sum, callback) => {
  const num = /^[0-9]*$/;
  if (value !== '') {
    if (num.test(value)) {
      if (Number(sum) + Number(value) <= 100) {
        if (Number(sum) + Number(value) > 0) {
          callback();
        } else {
          callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.shareholder1'))); // $i18n - 不允许股权为0%
        }
      } else {
        callback(new Error(`${$vue.$i18n.t('registerOpenAccount.common.verify.shareholder2')} ${sum} ${$vue.$i18n.t('registerOpenAccount.common.verify.shareholder3')}`)); // $i18n - 百分比最大为100%!已添加了   // $i18n - %股东所有权
      }
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.number2'))); // $i18n - 请输入正确信息,且必须为数字
    }
    callback();
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.enter'))); // $i18n - 请输入
  }
};
// 根据国家id获取国家  --税务国家
const getState = (value, arrList, arrList1, _this, lang = 'en_US') => {
  _this.$http({
    url: '/common/region/findByCode',
    method: 'get',
    params: { code: value }
  })
    .then(res => {
      if (res.code === 200) {
        for (let i = 0; i < arrList.length; i++) {
          if (lang === 'en_US') {
            arrList[i].dictDest = arrList1[i].dictDest.replace(/country/g, res.object[0].enName);
          } else {
            arrList[i].dictDest = arrList1[i].dictDest.replace(/country/g, res.object[0].cnName);
          }
        }
      } else {
        _this.$message.error(res.code || 'Has Error');
      }
    })
    .catch(error => {
      _this.$message.error(error || 'Has Error');
    });
};
// 根据国家id获取国家
const getState1 = (state, _this, lang = 'en_US') => {
  return new Promise(function(resolve, reject) {
    _this.$http({
      url: '/common/region/findByCode',
      method: 'get',
      params: { code: state }
    })
      .then(res => {
        if (res.code === 200) {
          if (lang === 'en_US') {
            resolve(res.object[0].enName);
          } else {
            resolve(res.object[0].cnName);
          }
        } else {
          _this.$message.error(res.code || 'Has Error');
        }
      })
      .catch(error => {
        _this.$message.error(error || 'Has Error');
      });
  });
};
// 用户名验证
const getUsername = (rule, value, callback, _this) => {
  const reg = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
  if (value !== '') {
    if (reg.test(value)) {
      checkDuplicateName('accountName', value, _this).then(res => {
        callback();
      }).catch(error => {
        callback(new Error(error));
      });
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.username1'))); // $i18n - 用户名由英文字母和数字组成的4-16位字符，以字母开头
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.username2'))); // $i18n - 请输入用户名
  }
};
// 手机号验证+手机号不能重复验证
const getCellPhoneNumber = (rule, value, callback, _this, areaCode) => {
  const num = /^[0-9]*$/;
  if (value !== '') {
    if (value.length >= 6) {
      if (num.test(value) === true) {
        checkDuplicateName('mobile', value, _this, areaCode).then(res => {
          callback();
        }).catch(error => {
          callback(new Error(error));
        });
      } else {
        callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.number3'))); // $i18n - 请输入数字,不允许有特殊符号
      }
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.register.signIn.PhoneNumber'))); // $i18n - 手机号个数不能小于6位
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.phone'))); // $i18n - 请填入手机号
  }
};
// 手机号验证
const getCellPhoneNumber1 = (rule, value, callback) => {
  const num = /^[0-9]*$/;
  if (value !== '') {
    if (value.length >= 6) {
      if (num.test(value) === true) {
        callback();
      } else {
        callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.number3'))); // $i18n - 请输入数字,不允许有特殊符号
      }
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.register.signIn.PhoneNumber'))); // $i18n - 手机号个数不能小于6位
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.phone'))); // $i18n - 请填入手机号
  }
};
// 邮箱正则验证
const getPostbox = (rule, value, callback) => {
  const num = /^[a-z0-9](\w|\.|-)*@([a-z0-9]+-?[a-z0-9]+\.){1,3}[a-z]{2,4}$/i; // foxmail邮箱
  if (value === '') {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.emailHint1'))); // $i18n - 请输入邮箱
  } else if (num.test(value) === false) {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.emailHint2'))); // $i18n - 邮箱格式不正确!
  } else {
    callback();
  }
};
// 邮箱是否重复验证
const getEmail = (rule, value, callback, _this) => {
  const num = /^[a-z0-9](\w|\.|-)*@([a-z0-9]+-?[a-z0-9]+\.){1,3}[a-z]{2,4}$/i; // foxmail邮箱
  if (value === '') {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.emailHint1'))); // $i18n - 请输入邮箱
  } else if (num.test(value) === false) {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.emailHint2'))); // $i18n - 邮箱格式不正确!
  } else {
    checkDuplicateName('email', value, _this).then(res => {
      callback();
    }).catch(error => {
      callback(new Error(error));
    });
  }
};
const checkDuplicateName = (type, value, _this, areaCode = '') => { // 验证账户名/手机号/邮箱是否重复  accountName/mobile/email
  const data = {
    type,
    value,
    region: areaCode,
    lang: _this.$i18n.locale
  };
  return new Promise(function(resolve, reject) {
    _this.$http({
      url: '/register/checkDuplicateName',
      method: 'post',
      data
    })
      .then(res => {
        if (res.code === 200) {
          resolve(res);
        } else {
          if (res.message) {
            reject(res.message);
          } else {
            reject(res.code);
          }
        }
      })
      .catch(error => {
        reject('error');
        _this.$message.error('' + error + '');
      });
  });
};
// 密码验证
const getPsword = (rule, value, mate, callback) => {
  let intensityStatus = 0; // 密码强度状态
  return new Promise(function(resolve, reject) {
    if (value !== '') {
      if (/^[^\s]+$/.test(value)) {
        if (value.length >= 8 && value.length <= 16) { // 字符长度
          if (value !== mate) { // 用户名和密码是否一致
            if (/[0-9]/.test(value)) { // 数字
              intensityStatus++;
            }
            if (/[a-zA-Z]/.test(value)) { // 字母
              intensityStatus++;
            }
            if (/[^0-9a-zA-Z_]/.test(value)) { // 特殊字符
              intensityStatus++;
            }
            resolve(intensityStatus);
          } else {
            reject(intensityStatus);
            callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.padHint1'))); // $i18n - 用户名和密码不能一致
          }
        } else {
          reject(intensityStatus);
          callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.padHint2'))); // $i18n - 密码长度区间为8-16个字符
        }
      } else {
        callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.blank'))); // $i18n - 不能有空格
      }
    } else {
      reject(intensityStatus);
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.padHint3'))); // $i18n - 请输入密码
    }
  });
};
// 密码验证  --是否一致
const getPswordT = (rule, value, mate, callback) => {
  if (value !== '') {
    if (/^[^\s]+$/.test(value)) {
      if (value === mate) {
        callback();
      } else {
        callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.padHint4'))); // $i18n - 密码不一致
      }
    } else {
      callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.blank'))); // $i18n - 不能有空格
    }
  } else {
    callback(new Error($vue.$i18n.t('registerOpenAccount.common.verify.padHint3'))); // $i18n - 请输入密码
  }
};
// 获取当前电脑时区
// 时间戳 data转为时间戳
const getNowDate = (CSdate) => { // CSdate自定义时间 如果不传 就默认计算机当前时间
  const currentdate = getNowDate3();
  let timestamp1 = null;
  let timestamp2 = null;
  if (!CSdate) {
    timestamp1 = currentdate.replace(/-/g, '/');
    timestamp2 = new Date(timestamp1).getTime();
  } else {
    timestamp1 = String(CSdate).replace(/-/g, '/');
    timestamp2 = new Date(timestamp1).getTime();
  }
  return timestamp2;
};
// 时间戳 时间戳转为data
const add0 = (m) => {
  return m < 10 ? '0' + m : m;
};
const getNowDate1 = (CSdate) => {
  const time = new Date(Number(CSdate));
  return time;
};
// data 转data YY-MM-DD
const getNowDate2 = (CSdate) => {
  const time = new Date(CSdate);
  const y = time.getFullYear();
  const m = time.getMonth() + 1;
  const d = time.getDate();
  // let h = time.getHours();
  // let mm = time.getMinutes();
  // let s = time.getSeconds();
  // return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);

  return y + '-' + add0(m) + '-' + add0(d);
};
// 获取电脑当前时间
const getNowDate3 = (time) => {
  const date = time || new Date();
  const sign1 = '-';
  const sign2 = ':';
  const year = date.getFullYear(); // 年
  let month = date.getMonth() + 1; // 月
  let day = date.getDate(); // 日
  let hour = date.getHours(); // 时
  let minutes = date.getMinutes(); // 分
  let seconds = date.getSeconds(); // 秒
  // let weekArr = [
  //   '星期一',
  //   '星期二',
  //   '星期三',
  //   '星期四',
  //   '星期五',
  //   '星期六',
  //   '星期天'
  // ];
  // let week = weekArr[date.getDay()];
  // 给一位数数据前面加 “0”
  if (month >= 1 && month <= 9) {
    month = '0' + month;
  }
  if (day >= 0 && day <= 9) {
    day = '0' + day;
  }
  if (hour >= 0 && hour <= 9) {
    hour = '0' + hour;
  }
  if (minutes >= 0 && minutes <= 9) {
    minutes = '0' + minutes;
  }
  if (seconds >= 0 && seconds <= 9) {
    seconds = '0' + seconds;
  }
  const currentdate =
    year +
    sign1 +
    month +
    sign1 +
    day +
    ' ' +
    hour +
    sign2 +
    minutes +
    sign2 +
    seconds;
  return currentdate;
};

// 将时间戳转为年月日
const getNowDate4 = (time) => {
  const date = new Date(time) || new Date();
  const sign1 = '-';
  const year = date.getFullYear(); // 年
  let month = date.getMonth() + 1; // 月
  let day = date.getDate(); // 日
  if (month >= 1 && month <= 9) {
    month = '0' + month;
  }
  if (day >= 0 && day <= 9) {
    day = '0' + day;
  }
  const currentdate =
    year +
    sign1 +
    month +
    sign1 +
    day +
    ' ';
  return currentdate;
};
// 申请界面更新 返回状态判断
const GETresStatus = (res) => {
  let status = null;
  if (res.object === undefined) {
    status = false;
  } else {
    if (res.object.length === 0) {
      status = false;
    } else if (Object.keys(res.object).length === 0) {
      status = false;
    } else if (res.object === '') {
      status = false;
    } else {
      status = true;
    }
  }
  return status;
};
// 获取字符串长度
const getStringLength = (str) => {
  // /<summary>获得字符串实际长度，中文2，英文1</summary>
  // /<param name="str">要获得长度的字符串</param>
  let strLength = 0; const len = str.length; let charCode = -1;
  for (let i = 0; i < len; i++) {
    charCode = str.charCodeAt(i);
    if (charCode >= 0 && charCode <= 128) strLength += 1;
    else strLength += 2;
  }
  // 返回值大于一百 说明超出
  return strLength;
};
async function skipLogin(_this) {
  await _this.$store.dispatch('user/logout');
  _this.$router.push({ path: '/login' });
}
const setStaleDated = (_this) => {
  _this.$confirm($vue.$i18n.t('registerOpenAccount.common.verify.pastHint'), { // $i18n - 您的会话已过期,按"确定"重新加载页面并重新进行身 ...
    confirmButtonText: $vue.$i18n.t('registerOpenAccount.common.verify.confirm'), // $i18n - 确定
    cancelButtonText: $vue.$i18n.t('registerOpenAccount.common.verify.cancel'), // $i18n - 取消
    type: 'warning'
  }).then(() => {
    skipLogin(_this);
  }).catch(() => {
    _this.$message({
      type: 'info',
      message: $vue.$i18n.t('registerOpenAccount.common.verify.cancelEnd') // $i18n - 已取消
    });
  });
};

const setRouterPermissions = (route = { path: '/' }) => { // 路由权限-不允许用户直接变更浏览器url
  $vue.$store.commit('user/SET_OPENACCOUNT', { status: true });
  $vue.$store.commit('user/SET_OPENACCOUNT', { currentPosition: route });
  $vue.$router.push(route);
  $vue.$store.commit('user/SET_OPENACCOUNT', { status: false });
};

const getTimestampRandom = () => { // 获取当前时间戳-解决ie下get缓存问题
  const now = new Date();
  const Timestamp = Date.parse(now);
  return Timestamp;
};

const getPreSignedUrl = (url) => { // 获取查看文件预签名
  return new Promise(function(resolve, reject) {
    $vue.$http({
      url: 'openAccount/personal/getPreSignedUrl',
      method: 'get',
      params: {
        url,
        IEgetIssue: getTimestampRandom()
      }
    })
      .then(res => {
        resolve(res);
      })
      .catch(error => {
        reject(error.message || error.code);
      });
  });
};

// rule 必须 基于element-ui
// value 必须 基于element-ui
// callback 必须 基于element-ui
const verificationDFT = {
  ClearZF: (str) => getClearZF(str), // 去除字符串中左右逗号
  StringLength: (str) => getStringLength(str), // 获取字符串长度
  NowDate: (CSdate) => getNowDate(CSdate), // data->时间戳
  NowDate1: (CSdate) => getNowDate1(CSdate), // 时间戳->data
  NowDate2: (CSdate) => getNowDate2(CSdate), // data字符->YY-MM-DD
  NowDate3: (time) => getNowDate3(time), // 获取电脑当前时间
  NowDate4: (time) => getNowDate4(time), // 获取电脑当前时间 转为年月日
  resStatus: (CSdate) => GETresStatus(CSdate), // 申请界面更新 返回状态判断
  Birth: (rule, value, callback, val) => getBirth(rule, value, callback, val), // 出生日期验证- 18岁
  AccountMargin: (rule, value, callback, birth) => getAccountMargin(rule, value, callback, birth), // 账户保证金 年龄满21限制
  EnglishQ: (rule, value, callback) => getEnglishQ(rule, value, callback), // 字母
  EnglishNumber: (rule, value, callback) => getEnglishNumber(rule, value, callback), // 字母+数字
  Number: (rule, value, callback) => getNumber(rule, value, callback), // 数字
  Wangzhi: (rule, value, callback) => getWangzhi(rule, value, callback), // 网址
  Postbox: (rule, value, callback) => getPostbox(rule, value, callback), // 邮箱
  Email: (rule, value, callback, _this) => getEmail(rule, value, callback, _this), // 邮箱
  notChinese: (rule, value, callback) => getNotChinese(rule, value, callback), // 非中文  --不可为空
  notChinese1: (rule, value, callback) => getNotChinese1(rule, value, callback), // 非中文  --可为空
  NotNumber: (rule, value, callback) => getNotNumber(rule, value, callback), // 不能有数字
  notChineseSD: (value, yanzhengCallback) => getnotChineseSD(value, yanzhengCallback), // 手动验证 参数 为value  yanzhengCallback
  Gender: (rule, value, mate, callback) => getGender(rule, value, mate, callback), // 性别判断 mate需比对的值
  stairIf: (rule, value, mate, callback) => getstairIf(rule, value, mate, callback), // 一层逻辑button判断  mate需比对的值
  Signature: (rule, value, mate, callback) => getSignature(rule, value, mate, callback), // 签名判断  mate需比对的值
  stairIfTwo: (rule, value, mate, callback) => getstairIfTwo(rule, value, mate, callback), // 二级逻辑判断    mate{one:一级判断结果,two:2级判断结果}
  stairIfone: (rule, value, mate, callback, Passable) => getstairIfone(rule, value, mate, callback, Passable), // mate需比对的值 一层逻辑button判断 Passable可通过的值 可选
  SWnasuihao: (rule, value, array1, callback) => getSWnasuihao(rule, value, array1, callback), // array1 需要循环的数组
  Safety: (rule, value, obj, callback) => getSafety(rule, value, obj, callback), // obj 1.问题数组 --  2.anquan问题显示控制  3.type第几个问题
  Income: (rule, value, obj, callback) => getIncome(rule, value, obj, callback), // obj 3个资产值的对象
  investmentObjective: (val, arr) => getInvestmentObjective(val, arr), // 投资目标及交易意图 val 当前点击的值(单个)
  Knowledge: (rule, value, callback) => getKnowledge(rule, value, callback), // 知识水平 和 交易经验
  ExperienceTrading: (rule, value, callback) => getExperienceTrading(rule, value, callback), // 交易经验两年以上
  Ownership: (rule, value, num, callback) => getOwnership(rule, value, num, callback), // 所有权验证 sum 总数值
  State: (value, arrList, arrList1, _this, lang) => getState(value, arrList, arrList1, _this, lang), // 根据国家value获取展示值
  State1: (state, _this, lang) => getState1(state, _this, lang), // 根据国家value获取展示国家dest
  Username: (rule, value, callback, _this) => getUsername(rule, value, callback, _this), // 用户名验证
  cellPhoneNumber: (rule, value, callback, _this, areaCode) => getCellPhoneNumber(rule, value, callback, _this, areaCode), // 手机号验证+手机号不能重复验证
  cellPhoneNumber1: (rule, value, callback) => getCellPhoneNumber1(rule, value, callback), // 手机号验证
  Psword: (rule, value, mate, callback) => getPsword(rule, value, mate, callback), // 密码验证
  PswordT: (rule, value, mate, callback) => getPswordT(rule, value, mate, callback), // 密码验证-是否一致 mate  需比对的值
  staleDated: (_this) => setStaleDated(_this), // 登录token过期处理
  routerPermissions: (route) => setRouterPermissions(route), // 路由权限-不允许用户直接变更浏览器url
  timestampRandom: () => getTimestampRandom(), // 获取当前时间戳-解决ie下get缓存问题
  preSignedUrl: (url) => getPreSignedUrl(url) // 获取查看文件预签名
};
export default verificationDFT;
