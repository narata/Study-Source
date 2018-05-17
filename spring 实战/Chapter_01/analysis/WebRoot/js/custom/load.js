//全局变量定义
let myChart_scatter;
console.log($("#info"));
//初始调用
ask_mapdata();
askscatter();
asktabledata();
//散点图请求
function askscatter(){
	$.ajax({
		url:'getClusterGraph.action',
		dataType:'json',
		type:'get',
		success:function(data){
			scatterload(data.data);
//			console.log(data);
		},
		error:function(err){
			console.log(err);
		}
	});
}
//刷新表格2
function updatatable2(ip){
	$.ajax({
		url:'findByIp.action',
		data:{"ip":ip},
		dataType:'json',
		type:'get',
		success:function(data){
			console.log(data);
			$('#tb_2').bootstrapTable('load',data);
		}
	});
}
//表格初始化函数
function tableload(option){//id,url,columns必填id,url,columns,uniqueId,pageSize,pageList,rowStyle,onRefresh,search
    let ButtonInit = function () {
        var oInit = {};
        var postdata = {};
        oInit.Init = function () {
            //初始化页面上面的按钮事件
        };
        return oInit;
    };
    let TableInit=function(){
        let oTableInit={};
        oTableInit.Init=function () {
            $('#'+option.id).bootstrapTable({
                url:option.url,      //请求后台的URL（*）
                method: 'get',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParams: oTableInit.queryParams,//传递参数（*）
                sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber:1,                       //初始化加载第一页，默认第一页
                pageSize: option.pageSize||8,                       //每页的记录行数（*）
                pageList: option.pageList||[6,8,10, 12, 15, 20],        //可供选择的每页的行数（*）
                search: option.search||false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch: true,
                showColumns: false,                  //是否显示所有的列
                showRefresh: option.showRefresh||false,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: option.uniqueId||undefined,                     //每一行的唯一标识，一般为主键列
                showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,
                showHeader:option.showHeader||true,
                classes:option.classes||'table table-hover',
                rowStyle:option.rowStyle||function(row,index){
                    if(index%2==0){
                        return{classes:'success'};
                    }
                    else return{};
                },
                columns: option.columns,               
                onRefresh:option.onRefresh||function(){}
            });
        };
        oTableInit.queryParams=function (params) {
            let temp={
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                departmentname: $("#txt_search_departmentname").val(),
                statu: $("#txt_search_statu").val()
            };
            return temp;
        };
        return oTableInit;
    };
    let oTable=new TableInit();
    oTable.Init();
    let oButtonInit=new ButtonInit();
    oButtonInit.Init();
}
//初始化表格数据
function asktabledata(){
	let onRefresh_1=function(){
		$('#tb_departments').bootstrapTable('load',{'data':$('#hidden_table').bootstrapTable('getData')});
	};
    let option1={
        id:'tb_departments',
        url:'threadTable.action',
        uniqueId:'id',
        search:true,
        showRefresh:true,
        columns:[
            {
                checkbox:true
            },
            {
                field:'ip',
                title:'IP'
            },
            {
                field:'threat_type',
                title:'本次行为'
            },
            {
                field:'threat_level',
                title:'本次威胁等级'
            },
            {
                field:'time_start',
                title:'出现时间'
            },
            {
                field:'history',
                title:'历史行为'
            }
        ],
        onRefresh:onRefresh_1
    };
    let option_hidden={
        id:'hidden_table',
        url:'threadTable.action',
        uniqueId:'id',
        columns:[
            {
                checkbox:true
            },
            {
                field:'ip',
                title:'IP'
            },
            {
                field:'threat_type',
                title:'本次行为'
            },
            {
                field:'threat_level',
                title:'本次威胁等级'
            },
            {
                field:'time_start',
                title:'出现时间'
            },
            {
                field:'history',
                title:'历史行为'
            }
        ]
    };
    let option2={
    	id:"tb_2",
    	url:'',
    	classes:'table table-no-bordered',
    	showHeader:false,
    	columns:[
			{
			    field:'name',
			    title:'字段'
			},
			{
			    field:'info',
			    title:'具体信息'
			}
    	]
    };
    let option3={
        id:'tb_3',
        url:'',
        columns:[
            {
                field:'protocol_name',
                title:'协议'
            },
            {
                field:'number',
                title:'访问数'
            }
        ]
    };
    tableload(option1);
    tableload(option_hidden);
    // tableload(option2);
    tableload(option3);
}
//请求世界地图数据
function ask_mapdata(){
	$.ajax({
		url:'graphPoint.action',
//		data:{"ip":ip},
		dataType:'json',
		type:'get',
		success:function(data){
			mapload(data.data);
		}
	});
}
//加载世界地图
function mapload(data) {
    let myChart=echarts.init(document.getElementById('map'));
    let option={
        title:{
            text:'不知道写啥',
            textStyle:{
                color:'#000'
            },
            subtext:'数据来自www.ditecting.com',
            subtextStyle:{
                color:'#000'
            },
            left: 'center'
        },
        // legend:{
        //     show:true
        // },
        tooltip:{
			trigger:'item',
			formatter:function(params){	
				let res='';
				res+='IP : ';
				for(let i=0;i<params.value[2].length;i++){
					if(i%2==1){
						res+='<br>';
					}
					res+=params.value[2][i];
					res+=' , ';
					
				}
				return res;
			}
		},
		geo:{
			type:'map',
			map:'world',
			roam:true,
			selectedMode:'single',
			zoom:1.2,
			center:[5,20],
			label:{
				emphasis:{
					show:true,
					textStyle:{
					color:'#FFF'
					}
				}
			},
			itemStyle:{
				normal:{
					areaColor:'#323c48',
					borderColor:'#111'
				},
				emphasis:{
					areaColor:'#2a333d'
				}
			}
		},
        series:[
			{
				type:'scatter',
				name:'不知道',
				coordinateSystem:'geo',
				symbolSize:10,
				data:data.map(function(itemOpt){
			        return{                    
			            value:[
			                itemOpt[1],
			                itemOpt[0],
			                itemOpt[2],
			                itemOpt[3]
			            ],
			            label:{
			                emphasis:{
			                    position:'right',
			                    show:true
			                }
			            }				 	
			       }
				})
			}
        ]
    };
    myChart.setOption(option);
    myChart.on('click',function(param){
//    	console.log(param);
    	let newdata=[];
    	let temp=param.data.value[3];
    	for(let i=0;i<temp.length;i++){
    		newdata.push($('#hidden_table').bootstrapTable('getRowByUniqueId',temp[i]));
    	}
//    	console.log(newdata);
    	$('#tb_departments').bootstrapTable('load',{'data':newdata});
//    	console.log($('#tb_departments').bootstrapTable('getRowByUniqueId',1559));
    });
}
//加载散点图
function scatterload(data){
    myChart_scatter=echarts.init(document.getElementById('scatter'));
//    var data = [
//        [[28604,77,17096869,'Australia',1990],[31163,77.4,27662440,'Canada',1990],[1516,68,1154605773,'China',1990],[13670,74.7,10582082,'Cuba',1990],[28599,75,4986705,'Finland',1990],[29476,77.1,56943299,'France',1990],[31476,75.4,78958237,'Germany',1990],[28666,78.1,254830,'Iceland',1990],[1777,57.7,870601776,'India',1990],[29550,79.1,122249285,'Japan',1990],[2076,67.9,20194354,'North Korea',1990],[12087,72,42972254,'South Korea',1990],[24021,75.4,3397534,'New Zealand',1990],[43296,76.8,4240375,'Norway',1990],[10088,70.8,38195258,'Poland',1990],[19349,69.6,147568552,'Russia',1990],[10670,67.3,53994605,'Turkey',1990],[26424,75.7,57110117,'United Kingdom',1990],[37062,75.4,252847810,'United States',1990]],
//        [[44056,81.8,23968973,'Australia',2015],[43294,81.7,35939927,'Canada',2015],[13334,76.9,1376048943,'China',2015],[21291,78.5,11389562,'Cuba',2015],[38923,80.8,5503457,'Finland',2015],[37599,81.9,64395345,'France',2015],[44053,81.1,80688545,'Germany',2015],[42182,82.8,329425,'Iceland',2015],[5903,66.8,1311050527,'India',2015],[36162,83.5,126573481,'Japan',2015],[1390,71.4,25155317,'North Korea',2015],[34644,80.7,50293439,'South Korea',2015],[34186,80.6,4528526,'New Zealand',2015],[64304,81.6,5210967,'Norway',2015],[24787,77.3,38611794,'Poland',2015],[23038,73.13,143456918,'Russia',2015],[19360,76.5,78665830,'Turkey',2015],[38225,81.4,64715810,'United Kingdom',2015],[53354,79.1,321773631,'United States',2015]]
//    ];
    let itemStyle={
    	normal:{
    		opacity: 0.8,
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowOffsetY: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
    	}	
    };
    let schema=['IP地址','通信时间间隔','会话次数','威胁等级'];       
    let option={
        title:{
            text:'还是不知道写啥',
            textStyle:{
                color:'#FFF',
            }
        },
        color:['rgb(145,199,174)','rgb(212,130,101)','rgb(97,160,168)','rgb(194,53,49)','rgb(47,69,84)'],
        tooltip:{
        	padding: 10,
            backgroundColor: '#222',
            borderColor: '#777',
            borderWidth: 1,
            formatter: function (obj) {
                var value = obj.value;
                return '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
                    + obj.seriesName
                    + '</div>'
                    +schema[0]+ '：' + value[3] + '<br>'
                    +schema[1]+ '：' + value[0] + '<br>'
                    +schema[2]+ '：' + value[1] + '<br>'
                    +schema[3]+ '：' + value[4] + '<br>';
            }
        },
        legend:{
        	y:'top',
        	data:['1级','2级','3级','4级','5级'],
        	textStyle:{
        		color:'#000',
        		fontSize:16
        	}
        },
        xAxis:{
            splitLine:{
                lineStyle:{
                    type:'dashed'
                }
            }
        },
        yAxis:{
            splitLine:{
                lineStyle:{
                    type:'dashed'
                }
            },
            scale: true
        },     
        series:[
            {
                name:'1级',
                data:data[0],
                type:'scatter',
                symbolSize:20,
                itemStyle:itemStyle
            },
            {
                name:'2级',
                data:data[1],
                type:'scatter',
                symbolSize:20,
                itemStyle:itemStyle
            },
            {
                name:'3级',
                data:data[2],
                type:'scatter',
                symbolSize:20,
                itemStyle:itemStyle
            },
            {
                name:'4级',
                data:data[3],
                type:'scatter',
                symbolSize:20,
                itemStyle:itemStyle
            },
            {
                name:'5级',
                data:data[4],
                type:'scatter',
                symbolSize:20,
                itemStyle:itemStyle
            }
        ]
    };
    myChart_scatter.setOption(option);
    myChart_scatter.on('click',function(param){
//    	console.log(param);
    	let newdata=[];
    	let temp=param.data[2];
    	newdata.push($('#hidden_table').bootstrapTable('getRowByUniqueId',temp));
//    	console.log(newdata);
    	$('#tb_departments').bootstrapTable('load',{'data':newdata});
    });
}