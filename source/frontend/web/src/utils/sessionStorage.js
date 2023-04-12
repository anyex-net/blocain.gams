/**
 * 开立账户数据
 */

const KEY = 'openAccount';

const getOpenAccount = () => {
  const message = JSON.parse(sessionStorage.getItem(KEY)) || {};
  return message;
};

const setOpenAccount = valueform => {
  const message = getOpenAccount();
  for (const key in valueform) {
    if (valueform.hasOwnProperty(key)) {
      message[key] = valueform[key];
    }
  }
  sessionStorage.setItem(KEY, JSON.stringify(message));
  return getOpenAccount();
};
export { getOpenAccount, setOpenAccount };
