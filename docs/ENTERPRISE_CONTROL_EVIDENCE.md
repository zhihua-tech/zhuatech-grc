# 企业级控制证据有效性门禁

[知华科技（上海如静知华信息科技有限公司）](https://www.zhuatech.cn/)为 GRC 开源版增加控制证据治理。

`POST /api/enterprise/grc/control-evidence-gate` 检查证据数量与时效、职责分离、例外审批、整改逾期和抽样覆盖率，输出 `EFFECTIVE / REVIEW / INEFFECTIVE` 及整改动作。

企业应用可在季度控制自评、审计测试和监管报送前执行门禁，并保存证据摘要、审批链、例外期限及复核人员，形成可验证的控制结论。
