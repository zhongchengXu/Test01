package com.day01.Utill;

import com.alibaba.fastjson.JSONObject;
import com.day01.Entities.DBChecker;
import com.day01.Entities.DBQueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBCheckUtil {
    public static String doQuery(String querySql) {
        //将脚本字符串封装成对象
        List<DBChecker> dbCheckers = JSONObject.parseArray(querySql,DBChecker.class);
        List<DBQueryResult> dbQueryResults = new ArrayList<DBQueryResult>();
        //循环遍历,取出sql执行脚本
        for (DBChecker dbChecker:dbCheckers) {
            //拿到sql编号
            String no = dbChecker.getNo();
            //拿到sql脚本
            String sql = dbChecker.getSql();
            //执行查询,获得结果
            Map<String,Object> columnLabelAndValues = JDBCUtil.query(sql);
            DBQueryResult dbQueryResult =new DBQueryResult();
            dbQueryResult.setNo(no);
            dbQueryResult.setColumnLabelAndValues(columnLabelAndValues);
            dbQueryResults.add(dbQueryResult);
        }
        //通过fastJson将结果装欢为字符串
        return  JSONObject.toJSONString(dbQueryResults);

    }
}
