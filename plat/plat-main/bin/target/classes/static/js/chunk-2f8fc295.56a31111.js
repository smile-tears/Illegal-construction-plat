(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2f8fc295"],{"0d6d":function(a,t,e){"use strict";var n=e("58aa"),s=e.n(n);s.a},"3e36":function(a,t,e){"use strict";e.r(t);var n=function(){var a=this,t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"container"},[n("div",{staticClass:"table-page-search-wrapper"},[n("a-form",{attrs:{layout:"inline"}},[n("a-row",{attrs:{gutter:48}},[n("a-col",{attrs:{md:8,sm:24}},[n("a-form-item",{attrs:{label:"案件编号"}},[n("a-input",{attrs:{placeholder:""},model:{value:t.queryParam.caseNo,callback:function(a){t.$set(t.queryParam,"caseNo",a)},expression:"queryParam.caseNo"}})],1)],1),n("a-col",{attrs:{md:8,sm:24}},[n("a-form-item",{attrs:{label:"案件状态"}},[n("a-select",{staticStyle:{width:"125px"},model:{value:t.queryParam.status,callback:function(a){t.$set(t.queryParam,"status",a)},expression:"queryParam.status"}},[n("a-select-option",{attrs:{value:"1"}},[t._v("待立案")]),n("a-select-option",{attrs:{value:"2"}},[t._v("待分派")]),n("a-select-option",{attrs:{value:"3"}},[t._v("案件处置")]),n("a-select-option",{attrs:{value:"4"}},[t._v("处置反馈件")]),n("a-select-option",{attrs:{value:"5"}},[t._v("案件核查")]),n("a-select-option",{attrs:{value:"6"}},[t._v("核查反馈件")]),n("a-select-option",{attrs:{value:"7"}},[t._v("申请回退")]),n("a-select-option",{attrs:{value:"8"}},[t._v("申请延期")]),n("a-select-option",{attrs:{value:"10"}},[t._v("结案")])],1)],1)],1),t.advanced?[n("a-col",{attrs:{md:8,sm:24}},[n("a-form-item",{attrs:{label:"案件描述"}},[n("a-input",{attrs:{placeholder:""},model:{value:t.queryParam.caseDesc,callback:function(a){t.$set(t.queryParam,"caseDesc",a)},expression:"queryParam.caseDesc"}})],1)],1)]:t._e(),n("a-col",{attrs:{md:t.advanced?24:8,sm:24}},[n("span",{staticClass:"table-page-search-submitButtons",style:t.advanced&&{float:"right",overflow:"hidden"}||{}},[n("a-button",{attrs:{type:"primary"},on:{click:t.loadData}},[t._v("查询")]),n("a-button",{staticStyle:{"margin-left":"8px"},on:{click:function(){return t.queryParam={}}}},[t._v("重置")]),n("a",{staticStyle:{"margin-left":"8px"},on:{click:function(){return a.advanced=!a.advanced}}},[t._v(" "+t._s(t.advanced?"收起":"展开")+" "),n("a-icon",{attrs:{type:t.advanced?"up":"down"}})],1)],1)])],2)],1)],1),n("a-table",{attrs:{"row-selection":{selectedRowKeys:t.selectedRowKeys,onChange:t.onSelectChange},columns:t.columns,"data-source":t.data,rowKey:"id",pagination:{current:t.pagination.current,pageSize:t.pagination.pageSize,total:t.pagination.total,"show-size-changer":!0,"show-quick-jumper":!0,"show-total":function(a){return"共 "+a+" 条"}}},on:{change:t.change},scopedSlots:t._u([{key:"serial",fn:function(a,e,s){return n("span",{},[t._v(" "+t._s(s+1)+" ")])}},{key:"action",fn:function(a,e){return[n("a",{on:{click:function(a){return t.info(e)}}},[t._v("查看")])]}}])}),n("Edit",{attrs:{modalData:t.modalData},on:{handleModalEvent:t.handleModalEvent}})],1)},s=[],o=e("80a0"),c=e("d4f4"),i=e("2395"),l=e("1cfa"),r=e.n(l),d=e("2af9"),u=[{title:"序号",scopedSlots:{customRender:"serial"}},{title:"案件编号",dataIndex:"caseNo",key:"caseNo"},{title:"案件来源",dataIndex:"casesource",key:"casesource"},{title:"案件类型",dataIndex:"caseType",key:"caseType"},{title:"样本",dataIndex:"sampleName",key:"sampleName"},{title:"案件描述",dataIndex:"caseDesc",key:"caseDesc"},{title:"位置描述",dataIndex:"locationDesc",key:"locationDesc"},{title:"上报时间",dataIndex:"reporTime",key:"reporTime"},{title:"截止日期",dataIndex:"enddate",key:"enddate"},{title:"操作",key:"action",align:"center",scopedSlots:{customRender:"action"}}],p={components:{Edit:i["a"],Ellipsis:d["h"]},mounted:function(){this.loadData()},data:function(){return{advanced:!1,data:[],columns:u,selectedRowKeys:[],pagination:{current:1,pageSize:10,total:0},modalData:{record:{},selectTab:{options:[]}},queryParam:{}}},methods:{loadData:function(){var a=this,t=Object(o["a"])({pageNo:this.pagination.current,pageSize:this.pagination.pageSize},this.queryParam);Object(c["c"])(r.a.stringify(t)).then((function(t){a.data=t.result.data,a.pagination.total=t.result.totalCount})).catch((function(a){}))},info:function(a){this.modalData={record:a,visible:!0,disabled:!0,title:"综合查询",seen:!1}},handleModalEvent:function(a){this.loadData()},onSelectChange:function(a){this.selectedRowKeys=a},change:function(a,t,e){this.pagination=a,this.loadData()}}},m=p,v=(e("0d6d"),e("4023")),f=Object(v["a"])(m,n,s,!1,null,"9fa85fea",null);t["default"]=f.exports},"58aa":function(a,t,e){}}]);