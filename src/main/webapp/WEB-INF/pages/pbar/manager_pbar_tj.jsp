<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/4/16
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${pbar.name}-管理中心</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/pbar/pbar_manager.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>

<div class="main">

  <%@include file="../common/manage_pbar_left.jsp" %>
  <div class="main_02">
    <div class="main_02_title">
      <span class="glyphicon glyphicon-tags" style="color:#FF60AF"></span>
      &nbsp;<b>${pbar.name}圈子统计</b>
    </div>
    <div class="main_02_content">
      年份：
      <span id="n_j" class="glyphicon glyphicon-minus-sign" style="cursor: pointer"></span>
      <input type="text" class="form-control" style="color:#ff7ea9; border: solid 1px #ff7ea9;width: 60px;display: inline-block" id="year" value="${year}" disabled="true"/>
      <span id="n_z" class="glyphicon glyphicon-plus-sign" style="cursor: pointer"></span>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      月份：
      <span id="y_j" class="glyphicon glyphicon-minus-sign" style="cursor: pointer"></span>
      <input type="text" class="form-control" style="color:#ff7ea9; border: solid 1px #ff7ea9;width: 60px;display: inline-block" id="month"  value="${month}" disabled="true"/>
      <span id="y_z" class="glyphicon glyphicon-plus-sign" style="cursor: pointer"></span>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" id = "js" class="btn btn-default" style="color:#ff7ea9; border: solid 1px #ff7ea9"><span class="glyphicon glyphicon-retweet"></span>&nbsp;&nbsp;检索</button>
      <input type="hidden" value="${pbar.id}" id="pbarId"/>
      <div id="main" style="margin-top:30px;height:400px; font-family: 'Microsoft Yahei'"></div>
        <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
        <script type="text/javascript">
          // 路径配置
          require.config({
            paths: {
              echarts: 'http://echarts.baidu.com/build/dist'
            }
          });
          require(
                  [
                    'echarts',
                    'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
                  ],
                  function (ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('main'));

                    var option = {
                      title : {
                        text: '${pbar.name}圈子${year}年${month}月访问量统计',
                        subtext: ''
                      },
                      tooltip : {
                        trigger: 'axis'
                      },
                      toolbox: {
                        show : true,
                        feature : {
                          mark : {show: true},
                          dataView : {show: true, readOnly: false},
                          magicType : {show: true, type: ['line', 'bar']},
                          restore : {show: true},
                          saveAsImage : {show: true}
                        },
                        itemStyle:{
                          normal:{color:'#FF6699'}
                        }
                      },
                      calculable : true,
                      xAxis : [
                        {
                          type : 'category',
                          boundaryGap : false,
                          data : ${days},
                          axisLabel : {
                            formatter: '{value}日'
                          }
                        }
                      ],
                      yAxis : [
                        {
                          type : 'value',
                          axisLabel : {
                            formatter: '{value} 次'
                          }
                        }
                      ],
                      series : [
                        {
                          name:'访问量',
                          type:'line',
                          data:${values},
                          markPoint : {
                            data : [
                              {type : 'max', name: '最大值'},
                              {type : 'min', name: '最小值'}
                            ],
                            itemStyle:{
                              normal:{color:'#FF6699'}
                            }
                          },
                          markLine : {
                            data : [
                              {type : 'average', name: '平均值'}
                            ],
                            itemStyle:{
                              normal:{color:'#CC0033'}
                            }
                          },
                          itemStyle:{
                            normal:{color:'#FF99CC'}
                          }
                        }
                      ]
                    }

                    // 为echarts对象加载数据
                    myChart.setOption(option);
                  }
          );
        </script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pbar/manager_pbar_tj.js"></script>
    </div>
  </div>
</div>
<%@include file="../common/foot.jsp" %>
</body>
</html>
