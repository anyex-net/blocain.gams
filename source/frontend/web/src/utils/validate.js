// 验证是否是[0-1000]的小数
export function isBtnZero(rule, value, callback) {
  if (!value) {
    return callback();
  }
  setTimeout(() => {
    if (!Number(value)) {
      callback(new Error('请输入大于零的数字'));
    } else {
      if (value < 0) {
        callback(new Error('请输入大于零的数字'));
      } else {
        callback();
      }
    }
  }, 100);
}
export function accountNumber(rule, value, callback) {
  if (!value) {
    return callback();
  }
  setTimeout(() => {
    var byteLen = value.length;
    if (byteLen <= 0) {
      callback(new Error('请输入银行账号'));
    } else {
      if (byteLen <= 12 || byteLen >= 32) {
        callback(new Error('请输入长度大于12并且小于32的银行账号'));
      } else {
        callback();
      }
    }
  }, 100);
}
export function amountNumber(rule, value, callback) {
  if (!value) {
    return callback();
  }
  setTimeout(() => {
    if (!value) {
      callback(new Error('请输入金额数量'));
    } else {
      if (value < 0 || value >= 100000000000) {
        callback(new Error('请输入大于0并小于100,000,000,000的数字'));
      } else {
        callback();
      }
    }
  }, 100);
}
export function SWIFTNumber(rule, value, callback) {
  if (!value) {
    return callback();
  }
  setTimeout(() => {
    var byteLen = value.length;
    if (byteLen <= 0) {
      callback(new Error('请输入SWIFT Code'));
    } else {
      if (byteLen >= 32) {
        callback(new Error('请输入长度大于0并且小于32的SWIFT Code'));
      } else {
        callback();
      }
    }
  }, 100);
}
export function checkNum(rule, value, callback) {
  const reg = /(^(-)?[0-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
  if (!value) {
    return callback();
  } else if (!reg.test(value)) {
    return callback(new Error('请填写数字,最多2位小数'));
  } else {
    callback();
  }
}
