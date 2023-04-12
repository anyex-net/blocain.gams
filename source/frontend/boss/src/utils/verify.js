// ****** 表单验证 及 一些公共方法 *********

// 获取电脑当前时间
// 时间戳 data转为时间戳
var getNowDate = (CSdate) => { // CSdate自定义时间 如果不传 就默认计算机当前时间
  var currentdate = getNowDate3();
  let timestamp1 = null;
  let timestamp2 = null;
  if (CSdate === undefined) {
    timestamp1 = currentdate.replace(/-/g, '/');
    timestamp2 = new Date(timestamp1).getTime();
  } else {
    timestamp1 = String(CSdate).replace(/-/g, '/');
    timestamp2 = new Date(timestamp1).getTime();
  }
  return timestamp2;
};
// 时间戳 时间戳转为data
var add0 = (m) => {
  return m < 10 ? '0' + m : m;
};
var getNowDate1 = (CSdate) => {
  var time = new Date(CSdate);
  return time;
};
// data 转data YY-MM-DD
var getNowDate2 = (CSdate) => {
  var time = new Date(CSdate);
  var y = time.getFullYear();
  var m = time.getMonth() + 1;
  var d = time.getDate();
  // var h = time.getHours();
  // var mm = time.getMinutes();
  // var s = time.getSeconds();
  // return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);
  return y + '-' + add0(m) + '-' + add0(d);
};
// 获取电脑当前时间
var getNowDate3 = () => {
  var date = new Date();
  var sign1 = '-';
  var sign2 = ':';
  var year = date.getFullYear(); // 年
  var month = date.getMonth() + 1; // 月
  var day = date.getDate(); // 日
  var hour = date.getHours(); // 时
  var minutes = date.getMinutes(); // 分
  var seconds = date.getSeconds(); // 秒
  // var weekArr = [
  //   '星期一',
  //   '星期二',
  //   '星期三',
  //   '星期四',
  //   '星期五',
  //   '星期六',
  //   '星期天'
  // ];
  // var week = weekArr[date.getDay()];
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
  var currentdate =
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

var getNowDate4 = (CSdate) => {
  var time = new Date(CSdate);
  var sign1 = '-';
  var sign2 = ':';
  var year = time.getFullYear(); // 年
  var month = time.getMonth() + 1; // 月
  var day = time.getDate(); // 日
  var hour = time.getHours(); // 时
  var minutes = time.getMinutes(); // 分
  var seconds = time.getSeconds(); // 秒
  // var weekArr = [
  //   '星期一',
  //   '星期二',
  //   '星期三',
  //   '星期四',
  //   '星期五',
  //   '星期六',
  //   '星期天'
  // ];
  // var week = weekArr[date.getDay()];
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
  var currentdate =
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
// 申请界面更新 返回状态判断
var GETresStatus = (res) => {
  let status = null;
  if (res.object === undefined) {
    status = false;
  } else {
    if (res.object.length === 0) {
      status = false;
    } else if (Object.keys(res.object).length === 0) {
      status = false;
    } else {
      status = true;
    }
  }
  return status;
};
// 获取字符串长度
var getStringLength = (str) => {
  // /<summary>获得字符串实际长度，中文2，英文1</summary>
  // /<param name="str">要获得长度的字符串</param>
  var strLength = 0; var len = str.length; var charCode = -1;
  for (var i = 0; i < len; i++) {
    charCode = str.charCodeAt(i);
    if (charCode >= 0 && charCode <= 128) strLength += 1;
    else strLength += 2;
  }
  return strLength;
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
  NowDate3: () => getNowDate3(), // 获取电脑当前时间
  NowDate4: (CSdate) => getNowDate4(CSdate), // data字符->YY-MM-DD 加时分秒
  resStatus: (CSdate) => GETresStatus(CSdate), // 申请界面更新 返回状态判断
  Birth: (rule, value, callback) => getBirth(rule, value, callback), // 出生日期验证- 18岁
  EnglishQ: (rule, value, callback) => getEnglishQ(rule, value, callback), // 字母
  EnglishNumber: (rule, value, callback) => getEnglishNumber(rule, value, callback), // 字母+数字
  Number: (rule, value, callback) => getNumber(rule, value, callback), // 数字
  Wangzhi: (rule, value, callback) => getWangzhi(rule, value, callback), // 网址
  Postbox: (rule, value, callback) => getPostbox(rule, value, callback) // 邮箱

};
export default verificationDFT;
