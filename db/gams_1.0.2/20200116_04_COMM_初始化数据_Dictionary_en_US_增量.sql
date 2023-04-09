-- 增量数据字典数据;

-- 修改 控权人类型子节点展示值 DICT_DEST

UPDATE  Dictionary SET
DICT_DEST='Individual who has a controlling ownership interest (i.e. more than 25% of issued share capital)'
WHERE id = 200000000295;

UPDATE  Dictionary SET
DICT_DEST='Individual who exercises control / is entitled to exercise control through other means (i.e. more than 25% of voting rights)'
WHERE id = 200000000296;

UPDATE  Dictionary SET
DICT_DEST='Other (e.g. individual who exercises control over another entity being the settlor / trustee / protector / beneficiary)'
WHERE id = 200000000302;

commit;
