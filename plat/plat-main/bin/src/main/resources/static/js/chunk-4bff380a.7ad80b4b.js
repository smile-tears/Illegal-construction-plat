(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4bff380a","chunk-3cd1b04b"],{"0388":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("List",{attrs:{status:5}})},o=[],c=a("4de2"),r={components:{List:c["default"]}},i=r,n=a("4023"),l=Object(n["a"])(i,s,o,!1,null,null,null);t["default"]=l.exports},"35f9":function(e,t,a){},"4de2":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("div",{staticClass:"table-page-search-wrapper"},[a("a-form",{attrs:{layout:"inline"}},[a("a-row",{attrs:{gutter:48}},[a("a-col",{attrs:{md:8,sm:24}},[a("a-form-item",{attrs:{label:"案件编号"}},[a("a-input",{attrs:{placeholder:""},model:{value:e.queryParam.caseNo,callback:function(t){e.$set(e.queryParam,"caseNo",t)},expression:"queryParam.caseNo"}})],1)],1),a("a-col",{attrs:{md:8,sm:24}},[a("a-form-item",{attrs:{label:"案件描述"}},[a("a-input",{attrs:{placeholder:""},model:{value:e.queryParam.caseDesc,callback:function(t){e.$set(e.queryParam,"caseDesc",t)},expression:"queryParam.caseDesc"}})],1)],1),e.advanced?[a("a-col",{attrs:{md:8,sm:24}},[a("a-form-item",{attrs:{label:"案件来源","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[a("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["casesource1"],expression:"['casesource1']"}],staticStyle:{width:"125px"},on:{change:e.handleCaseSourceChange}},[a("a-select-option",{attrs:{value:"内部"}},[e._v("内部")]),a("a-select-option",{attrs:{value:"外部"}},[e._v("外部")])],1),a("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["casesource2"],expression:"['casesource2']"}],staticStyle:{width:"140px","margin-left":"5px"}},e._l(e.caseSource,(function(t){return a("a-select-option",{key:t,attrs:{value:t}},[e._v(e._s(t))])})),1)],1)],1),a("a-col",{attrs:{md:8,sm:24}},[a("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"案件类型","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[a("a-tree-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["casetype.id",{rules:[{required:!0,message:"案件类型必填！"}]}],expression:"['casetype.id', { rules: [{ required: true, message: '案件类型必填！' }] }]"}],staticStyle:{width:"100%"},attrs:{"dropdown-style":{maxHeight:"400px",overflow:"auto"},"tree-data":e.caseTypeTreeData,placeholder:"","tree-default-expand-all":""}})],1)],1),a("a-col",{attrs:{md:8,sm:24}},[a("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"所属网格","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[a("a-tree-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["gridCommunity.id",{rules:[{required:!0,message:"所属网格必填！"}]}],expression:"['gridCommunity.id', { rules: [{ required: true, message: '所属网格必填！' }] }]"}],staticStyle:{width:"100%"},attrs:{"dropdown-style":{maxHeight:"400px",overflow:"auto"},"tree-data":e.gridTreeData,placeholder:"","tree-default-expand-all":""},on:{select:e.handleGridSelect}})],1)],1),a("a-col",{attrs:{md:8,sm:24}},[a("a-form-item",{attrs:{label:"上报日期"}},[a("a-date-picker",{staticStyle:{width:"100%"},attrs:{placeholder:"请输入上报日期"},model:{value:e.queryParam.reportDate,callback:function(t){e.$set(e.queryParam,"reportDate",t)},expression:"queryParam.reportDate"}})],1)],1)]:e._e(),a("a-col",{attrs:{md:e.advanced?24:8,sm:24}},[a("span",{staticClass:"table-page-search-submitButtons",style:e.advanced&&{float:"right",overflow:"hidden"}||{}},[a("a-button",{attrs:{type:"primary"},on:{click:e.loadData}},[e._v("查询")]),a("a-button",{staticStyle:{"margin-left":"8px"},on:{click:function(){return e.queryParam={}}}},[e._v("重置")]),a("a",{staticStyle:{"margin-left":"8px"},on:{click:e.toggleAdvanced}},[e._v(" "+e._s(e.advanced?"收起":"展开")+" "),a("a-icon",{attrs:{type:e.advanced?"up":"down"}})],1)],1)])],2)],1)],1),e.isShowTab?a("div",[a("a-tabs",{style:{height:"45px"},attrs:{"default-active-key":"1"},on:{tabClick:e.changeTab}},e._l(this.caseType,(function(t,s,o){return a("a-tab-pane",{key:s},[a("template",{slot:"tab"},[a("a-badge",{attrs:{count:""+t.count}},[e._v(" "+e._s(s)+" ")])],1),e._v(" 正文"+e._s(o)+" ")],2)})),1)],1):e._e(),a("a-table",{ref:"table",attrs:{"row-selection":{selectedRowKeys:e.selectedRowKeys,onChange:e.onSelectChange},columns:e.columns,"data-source":e.data,rowKey:"id",pagination:{current:e.pagination.current,pageSize:e.pagination.pageSize,total:e.pagination.total,"show-size-changer":!0,"show-quick-jumper":!0,"show-total":function(e){return"共 "+e+" 条"}}},scopedSlots:e._u([{key:"serial",fn:function(t,s,o){return a("span",{},[a("a-badge",{attrs:{color:e.caseColors[s.state],text:""}}),e._v(" "+e._s(o+1)+" ")],1)}},{key:"action",fn:function(t,s){return[a("a",{on:{click:function(t){return e.info(s)}}},[e._v("操作")])]}}])}),a("Edit",{attrs:{modalData:e.modalData},on:{handleModalEvent:e.handleModalEvent}})],1)},o=[],c=(a("fe8a"),a("80a0")),r=a("d4f4"),i=a("2395"),n=a("81ef"),l=a("ef43"),u=a("1cfa"),d=a.n(u),p=a("2af9"),h=a("5880"),m=a("e819"),f=a("9452"),y=f["a"].caseType,b=f["a"].caseSource1,g=f["a"].caseSource2,v=f["a"].caseColors,w=[{title:"序号",scopedSlots:{customRender:"serial"}},{title:"案件编号",dataIndex:"caseNo",key:"caseNo"},{title:"案件来源",dataIndex:"casesource",key:"casesource"},{title:"案件类型",dataIndex:"caseType",key:"caseType"},{title:"样本",dataIndex:"sampleName",key:"sampleName"},{title:"案件描述",dataIndex:"caseDesc",key:"caseDesc"},{title:"位置描述",dataIndex:"locationDesc",key:"locationDesc"},{title:"上报时间",dataIndex:"reporTime",key:"reporTime"},{title:"截止日期",dataIndex:"enddate",key:"enddate"},{title:"操作",key:"action",align:"center",scopedSlots:{customRender:"action"}}],T={components:{Edit:i["a"],Ellipsis:p["h"]},props:["status"],computed:Object(c["a"])({},Object(h["mapGetters"])(["userInfo"])),created:function(){this.getGridTree(),this.questionTypeTree(),this.initWebSocket()},destroyed:function(){this.websock.close()},mounted:function(){var e=this,t=this;this.$nextTick((function(){if(t.caseType=JSON.parse(JSON.stringify(y)),t.$props.status)for(var a in t.queryParam.status=t.$props.status,t.isShowTab=!1,t.caseType)t.caseType[a]["count"]=0,t.caseType[a].status==t.queryParam.status&&t.caseType[a].options&&t.changeTab(a);else{for(var a in e.isShowTab=!0,t.caseType)t.caseType[a]["count"]=0,t.caseType[a].status==t.queryParam.status&&t.changeTab(a);t.initTab()}}))},data:function(){return{isShowTab:!0,advanced:!1,caseColors:v,caseSource:[],gridTreeData:[],caseType:{},activeKey:"",selectTab:{},data:[],columns:w,selectedRowKeys:[],pagination:{current:1,pageSize:10,total:0},msgCount:34,modalData:{record:{},selectTab:{options:[]}},queryParam:{},websocket:null,wsUri:"",userId:"",isWebSocket:!1,wsUrl:m["a"].wsUrl}},methods:{initWebSocket:function(){this.userId=this.userInfo.id,this.wsUri=this.wsUrl+"/caseInfo/"+(new Date).getTime()+this.userId,this.websock=new WebSocket(this.wsUri),this.websock.onmessage=this.websocketonmessage,this.websock.onopen=this.websocketonopen,this.websock.onerror=this.websocketonerror},websocketonopen:function(){this.isWebSocket=!0,console.log("链接成功！",this.isWebSocket)},websocketonerror:function(){this.$message.info("重新建立链接中"),this.initWebSocket()},websocketonmessage:function(e){console.log("resdata",e.data);try{var t=JSON.parse(e.data);for(var a in this.caseType)this.caseType[a].status==t.destnodeid&&3!=this.caseType[a].status&&5!=this.caseType[a].status&&(this.caseType[a]["count"]+=1)}catch(e){console.log("websocketonmessage",e)}},websocketclose:function(e){this.isWebSocket=!1,this.websock.close(),console.log("断开连接",e)},loadData:function(){console.log("this",this);var e=this,t=Object(c["a"])({pageNo:this.pagination.current,pageSize:this.pagination.pageSize},this.queryParam);if(t.status)for(var a in this.caseType)this.caseType[a].status==t.status&&(this.caseType[a]["count"]=0);e.$props.status&&(t["operator"]=this.userId),Object(r["c"])(d.a.stringify(t)).then((function(t){e.data=t.result.data,e.pagination.total=t.result.totalCount})).catch((function(e){}))},handleCaseSourceChange:function(e){this.caseSource="内部"===e?b:g},getCaseInfoCityType:function(){var e=this;Object(r["f"])().then((function(t){200===t.code&&(e.caseType=t.result,e.activeKey=Object.keys(y)[0],e.changeTab(e.activeKey))})).catch((function(){}))},initTab:function(){this.caseType=JSON.parse(JSON.stringify(y)),this.activeKey=Object.keys(y)[0],delete this.caseType["案件处置"],delete this.caseType["案件核查"],this.changeTab(this.activeKey)},getGridTree:function(){var e=this;Object(n["g"])().then((function(t){200===t.code&&(e.gridTreeData=t.result)})).catch((function(){}))},questionTypeTree:function(){var e=this;Object(l["e"])().then((function(t){200===t.code&&(e.caseTypeTreeData=t.result)})).catch((function(){}))},info:function(e){this.modalData={record:JSON.parse(JSON.stringify(e)),visible:!0,disabled:!0,title:this.activeKey,selectTab:this.selectTab}},handleModalEvent:function(e){this.loadData()},onSelectChange:function(e){this.selectedRowKeys=e},change:function(e,t,a){this.pagination=e,this.loadData()},changeTab:function(e){this.activeKey=e,this.selectTab=this.caseType[e],console.log("changeTab",e,this.selectTab),this.queryParam.status=this.selectTab.status+"",this.loadData()},toggleAdvanced:function(){this.advanced=!this.advanced}}},k=T,S=(a("9076"),a("4023")),x=Object(S["a"])(k,s,o,!1,null,"4e7f31f5",null);t["default"]=x.exports},9076:function(e,t,a){"use strict";var s=a("35f9"),o=a.n(s);o.a}}]);