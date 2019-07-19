package com.yang.utils;

import com.yang.entity.CollectEn;
import com.yang.entity.DevEn;
import com.yang.entity.StationEn;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisUtils {

    public static String passWord = "Jereh123";

    public static Jedis redisDB= new Jedis("localhost");

    public static void putStationInfo(String stationNo, StationEn stationEn){
        redisDB.auth(passWord);
        redisDB.set(stationNo, JSONObject.fromObject(stationEn).toString());
    };

    public static void putDevInfo(String stationNo, DevEn devEn){
        redisDB.auth(passWord);
        StationEn stationEn = (StationEn) JSONObject.toBean(JSONObject.fromObject(redisDB.get(stationNo)),StationEn.class);
        List<DevEn> devEnList = stationEn.getDevEnList();
        DevEn newDevEn = devEn;
        for(int i=0;i<devEnList.size();i++)
        {
            DevEn oldDevEn = (DevEn)JSONObject.toBean(JSONObject.fromObject(devEnList.get(i)),DevEn.class);
            if(oldDevEn.getDevNo().equalsIgnoreCase(newDevEn.getDevNo())){
                List<CollectEn> collectEnList = newDevEn.getCollectEnList();
                for(int j=0;j<collectEnList.size();j++)
                {
                    putCollectInfo(stationNo,newDevEn.getDevNo(),collectEnList.get(j));
                }
                return;
            }

        }

        devEnList.add(newDevEn);
        stationEn.setDevEnList(devEnList);
        putStationInfo(stationNo,stationEn);
    };

    public static void putCollectInfo(String stationNo, String devNo,CollectEn collectEn){
        redisDB.auth(passWord);
        String dd = redisDB.get(stationNo);
        JSONObject JJ = JSONObject.fromObject(dd);

        StationEn stationEn = (StationEn) JSONObject.toBean(JSONObject.fromObject(redisDB.get(stationNo)),StationEn.class);
        List<DevEn> devEnList = stationEn.getDevEnList();
        for(int i=0;i<devEnList.size();i++)
        {
            DevEn devEn = (DevEn) JSONObject.toBean(JSONObject.fromObject(devEnList.get(i)),DevEn.class);
            if(devEn.getDevNo().equalsIgnoreCase(devNo))
            {
                List<CollectEn> collectEnList = devEn.getCollectEnList();
                CollectEn newCollectEn = collectEn;
                for(int j=0;j<collectEnList.size();j++)
                {
                    CollectEn oldCollectEn = (CollectEn) JSONObject.toBean(JSONObject.fromObject(collectEnList.get(j)),CollectEn.class);
                    if(oldCollectEn.getCollectNo().equalsIgnoreCase(newCollectEn.getCollectNo()))
                    {
                       // newCollectEn.setCollectName(oldCollectEn.getCollectName());
                        collectEnList.remove(j);

                        collectEnList.add(newCollectEn);
                        devEn.setCollectEnList(collectEnList);
                        devEnList.remove(i);
                        devEnList.add(devEn);
                        stationEn.setDevEnList(devEnList);
                        putStationInfo(stationNo,stationEn);
                        return;
                    }

                }
                        collectEnList.add(newCollectEn);
                        devEn.setCollectEnList(collectEnList);
                        devEnList.remove(i);
                        devEnList.add(devEn);
                        stationEn.setDevEnList(devEnList);
                        putStationInfo(stationNo,stationEn);

            }

        }
    };

    public static String getAllInfo(){
        redisDB.auth(passWord);
        String info = "[";
        int index = 0;
        Set<String> keys = redisDB.keys("*");

        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();

            if(index == 0)
            {
                info += redisDB.get(key);
            }else
            {
                info += ","+redisDB.get(key);
            }
            index++;
        }

        info += "]";

        return info;
    }

}

//package com.jerehsoft.lng.redis;
//
//        import java.util.ArrayList;
//        import java.util.HashMap;
//        import java.util.List;
//        import java.util.Map;
//
//        import javax.annotation.Resource;
//
//        import net.sf.json.JSONArray;
//
//        import redis.clients.jedis.Jedis;
//
//        import com.jerehsoft.lng.basedata.entities.LngStationInfo;
//        import com.jerehsoft.lng.basedata.service.ILngStationInfoService;
//        import com.jerehsoft.redis.util.RdisDB;
//        import com.jerehsoft.ums.AuthContext;
//        import com.jerehsoft.web.common.action.BaseDataAction;
//        import com.jerehsoft.webservice.WebServiceUtil;
//
//public class IotGetAllDevs  extends BaseDataAction  {
//
//    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
//    @Resource
//    private ILngStationInfoService stationService;
//
//
//    public String getAllDevs() throws Exception {
//
//        String str = RdisDB.getAllStationinfo();
//
//        JSONArray ja = JSONArray.fromObject(str);
//
//        //System.out.println(ja);
//
//        putValue("devs", ja);
//
//        return SUCCESS;
//
//    }
//
//}




//
//
//<%@ page language="java" contentType="text/html; charset=UTF-8"
//        pageEncoding="UTF-8"%>
//<%@ taglib prefix="s" uri="/struts-tags"%>
//<!DOCTYPE html>
//<html>
//<head>
//<s:include value="/temp/head.jsp"></s:include>
//<title>首页</title>
//<style type="text/css">
//
//</style>
//<script type="text/javascript">
//        $(function(){
//        setInterval(getJSONData,1000);
//        })
//
//        function getJSONData(){
//        var actionData = getSyncJSON('<s:url action="getAllDevs" namespace="/lng/redis" includeParams="all"></s:url>');
//        var en= actionData.data.devs ? actionData.data.devs :{isUse:true};
//        generateDom("RGB(255,66,0)","RGB(0,255,0)","100%","1%","1%","1%","1%","1%","left","left","datadom",en);
//        }
//
//        function generateDom(staBgcolor,devBgcolor,staWidth,staMarginLeft,devMarginLeft,staMarginRight,staMarginTop,devMarginTop,staFloat,devFloat,domId,jsonData)
//        {
//
//        var domDataContent = document.getElementById(domId);  //顶级dom
//        domDataContent.style.display = "flex";
//        domDataContent.style.flexWrap = "wrap";
//
//
//        for(i=0;i<jsonData.length;i++)
//        {
//        var staData = jsonData[i];
//        var devData = staData.devInfo;
//        var staName = staData.stationName;
//        var head_id = "head_"+staData.stationNo; //站的id
//
//        //创建站头
//        if($("#"+head_id).length<=0)
//        {
//        sta_div = document.createElement("div");
//        domDataContent.appendChild(sta_div); //站div附加到顶级dom
//        sta_div.id = head_id;
//        sta_div.style.backgroundColor = "grey";
//        sta_div.style.width = "100%";
//        sta_div.style.marginLeft = staMarginLeft;
//        sta_div.style.marginRight = staMarginRight;
//        sta_div.style.marginTop = staMarginTop;
//        sta_div.innerHTML = '<b>'+staName+'</b>';
//
//
//        sta_div.addEventListener('click',function(e){
//        var div = $(this);
//        var id = div.attr('id');
//
//        var bodyid = id.replace(/head/,"#body");
//        $(bodyid).toggle();
//        var alarmid = id.replace(/head/,"#alarm");
//        $(alarmid).toggle();
//        });
//        }
//
//
//        //创建站body
//        var body_id = "body_"+staData.stationNo; //内容id
//        if($("#"+body_id).length<=0)
//        {
//        var body_div = document.createElement("div"); //内容div
//        domDataContent.appendChild(body_div);//主体div附加到dom
//        body_div.id = body_id;
//        body_div.style.width = "68%";
//        body_div.style.marginLeft = "1%";
//        body_div.style.marginRight = "1%";
//        body_div.style.marginTop = "1%";
//        //body_div.style.backgroundColor = devBgcolor;
//        body_div.style.display = "flex";
//        body_div.style.flexWrap = "wrap";
//
//        }
//
//
//        //创建报警body
//        var alarm_id = "alarm_"+staData.stationNo; //内容id
//        if($("#"+alarm_id).length<=0){
//
//        var alarm_body_div = document.createElement("table"); //内容div
//        domDataContent.appendChild(alarm_body_div);//主体div附加到dom
//        alarm_body_div.id = alarm_id;
//        alarm_body_div.border="1";
//        alarm_body_div.style.backgroundColor = "rgb(128,128,128)";
//        alarm_body_div.innerHTML = '<thead><tr><th>设备名称</th><th>报警名称</th><th>发生时间</th><th>是否确认</th></tr></thead>';
//
//        alarm_body_div.style.width = "28%";
//
//        alarm_body_div.style.marginRight = "1%";
//        alarm_body_div.style.marginTop = "1%";
//
//        }
//
//
//
//        for(j=0;j<devData.length;j++)
//        {
//        var  paramData = devData[j].paramInfo;
//        var dev_id = "dev_"+staData.stationNo+"_"+devData[j].devNo; //站的id
//        if($("#"+dev_id).length<=0)
//        {
//        var dev_div = document.createElement("div"); //设备div
//        body_div.appendChild(dev_div);//设备div附件到主体div
//        dev_div.id = dev_id;
//        //dev_div.style.backgroundColor = "red";
//        dev_div.style.marginLeft="1%";
//        var paramHead = document.createElement("div"); //参数头div
//        paramHead.style.backgroundColor = "grey";
//        paramHead.style.borderRadius = "10px 10px 0px 0px";
//        paramHead.innerHTML = "<div><b>"+devData[j].devName+"采集量</b></div>" ;
//        paramHead.style.padding = "5px";
//        dev_div.appendChild(paramHead);
//        }
//        for(k=0;k<paramData.length;k++)
//        {
//
//        switch(paramData[k].paramFlag)
//        {
//        case 0:
//        {
//        var param_id = "param_"+staData.stationNo+"_"+devData[j].devNo+"_"+paramData[k].paramNo;
//        var paramBody = document.getElementById(param_id);
//        if($("#"+param_id).length<=0)
//        {
//        paramBody = document.createElement("div");//参数体div
//        paramBody.id = param_id;
//
//        dev_div.appendChild(paramBody); //参数div附加到设备div
//        }
//        if(k==(paramData.length)){
//
//        paramBody.style.borderRadius = "0px 0px 10px 10px";
//        }
//
//        paramBody.innerHTML = paramData[k].paramName+":"+paramData[k].paramValue;
//        paramBody.style.backgroundColor = "rgb(126,131,15)";
//        paramBody.style.padding = "5px";
//        }
//        break;
//        case 1:
//        { var now = new Date();
//        var year = now.getFullYear();
//        var mon = now.getMonth()+1;
//        var day = now.getDate();
//        var hour = now.getHours();
//        var min = now.getMinutes();
//        var sec = now.getSeconds();
//        var flag_id = "flag_"+staData.stationNo+"_"+devData[j].devNo+"_"+paramData[k].paramNo;
//        if($("#"+flag_id).length<=0)
//        {
//        $("#"+alarm_id).append("<tr id="+flag_id+"><td>"+devData[j].devName+"</td><td>"+paramData[k].paramName+"</td><td>"+year+"-"+mon+"-"+day+" "+hour+":"+min+":"+sec+"</td><td><button style='width:100%;height:100%;background-color:rgb(127,240,162)' onclick='myok()'>确认</button></td></tr>");
//
//        }
//        }
//        break;
//        }
//
//        }
//        }
//
//
//        }
//        }
//</script>
//</head>
//
//<body style="background-color:rgb(61,61,61)">
//<div id="datadom" style="max-height:1000px;float:left;overflow:auto;width:100%;"></div>
//</body>
//</html>
