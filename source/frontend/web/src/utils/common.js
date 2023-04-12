import cookies from 'js-cookie';

/**
 * 常用工具方法
 */

export function getTitle() {
  const locale = cookies.get('locale');
  if (locale === 'zh_CN') {
    document.title = '全球资产管理服务|国瑞证券';
  }
  if (locale === 'zh_HK') {
    document.title = '全球资产管理服务|国瑞证券';
  }
  if (locale === 'en_US') {
    document.title = 'Global Asset Management Services|MOF';
  }
}

