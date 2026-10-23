/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.workbench;

import static cn.zhuatech.grc.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);

    keys(r, "asOf", "controls", "evidence");
    var asOf = date(text(r, "asOf", 10));
    Map<String, List<String>> controls = new TreeMap<>();
    for (var c : rows(r, "controls", 1, 100)) {
      keys(c, "id", "requiredTypes");
      String id = id(c, "id");
      var types = strings(c, "requiredTypes", 1, 50);
      if (new HashSet<>(types).size() != types.size() || controls.putIfAbsent(id, types) != null)
        throw new IllegalArgumentException("控制项或证据类型重复");
    }
    Set<String> ids = new HashSet<>();
    Map<List<String>, Map<String, Object>> selected = new HashMap<>();
    for (var e : rows(r, "evidence", 0, 300)) {
      keys(e, "id", "control", "type", "validFrom", "validTo");
      unique(ids, id(e, "id"));
      String control = id(e, "control"), type = id(e, "type");
      if (!controls.containsKey(control) || !controls.get(control).contains(type))
        throw new IllegalArgumentException("证据引用未知控制或类型");
      var from = date(text(e, "validFrom", 10));
      var to = date(text(e, "validTo", 10));
      if (from.isAfter(to) || selected.putIfAbsent(List.of(control, type), e) != null)
        throw new IllegalArgumentException("证据期限错误或同类型存在多个待审版本");
    }
    List<Map<String, Object>> out = new ArrayList<>();
    int gaps = 0;
    for (var c : controls.entrySet())
      for (String type : c.getValue()) {
        var e = selected.get(List.of(c.getKey(), type));
        String state =
            e == null
                ? "MISSING"
                : asOf.isBefore(date(e.get("validFrom").toString()))
                    ? "NOT_YET_VALID"
                    : asOf.isAfter(date(e.get("validTo").toString())) ? "EXPIRED" : "CURRENT";
        if (!state.equals("CURRENT")) gaps++;
        out.add(
            Map.of(
                "control",
                c.getKey(),
                "type",
                type,
                "state",
                state,
                "evidenceId",
                e == null ? "" : e.get("id")));
      }
    return Map.of(
        "requirements",
        out,
        "gapCount",
        gaps,
        "allCurrent",
        gaps == 0,
        "complianceCertified",
        false);
  }
}
