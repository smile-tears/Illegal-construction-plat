(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-499f07de"],{"4d15":function(e,t,a){},"7a41":function(e,t,a){},"81ef":function(e,t,a){"use strict";a.d(t,"b",(function(){return r})),a.d(t,"a",(function(){return o})),a.d(t,"i",(function(){return l})),a.d(t,"h",(function(){return s})),a.d(t,"d",(function(){return c})),a.d(t,"e",(function(){return d})),a.d(t,"c",(function(){return u})),a.d(t,"f",(function(){return m})),a.d(t,"g",(function(){return p}));var n=a("b775"),i={gridCommunityList:"/gridCommunity/find",gridCommunityPost:"/gridCommunity/post",gridCommunityDelete:"/gridCommunity/delete",gridCommunityPut:"/gridCommunity/put",gridTree:"/gridCommunity/tree",idAndName:"/gridCommunity/idAndName",userIdAndName:"/gridCommunity/userIdAndName",checkGridName:"/gridCommunity/checkGridName",getTelephone:"/gridCommunity/getTelephone"};function r(e){return Object(n["b"])({url:i.getTelephone,method:"post",data:e})}function o(e){return Object(n["b"])({url:i.checkGridName,method:"post",data:e})}function l(e){return Object(n["b"])({url:i.userIdAndName,method:"post",data:e})}function s(e){return Object(n["b"])({url:i.idAndName,method:"post",data:e})}function c(e){return Object(n["b"])({url:i.gridCommunityList,method:"post",data:e})}function d(e){return Object(n["b"])({url:i.gridCommunityPost,method:"post",data:e})}function u(e){return Object(n["b"])({url:i.gridCommunityDelete,method:"post",data:e})}function m(e){return Object(n["b"])({url:i.gridCommunityPut,method:"post",data:e})}function p(e){return Object(n["b"])({url:i.gridTree,method:"post",data:e})}},"850b":function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("a-modal",{attrs:{title:e.modalData.title,width:800,visible:e.modalData.visible,confirmLoading:e.confirmLoading,maskClosable:!1},on:{cancel:e.handleCancel}},[a("template",{slot:"footer"},[a("a-button",{on:{click:e.handleCancel}},[e._v(" 取消 ")]),a("a-button",{on:{click:function(t){return e.handleOk(0)}}},[e._v(" 暂存 ")]),a("a-button",{attrs:{type:"primary"},on:{click:function(t){return e.handleOk(1)}}},[e._v(" 上报 ")])],1),a("a-form",{attrs:{layout:e.formLayout,form:e.form}},[a("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{label:"id","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[a("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["id",{}],expression:"['id', {}]"}]})],1),a("a-form-item",{attrs:{label:"标题","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[a("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["title",{}],expression:"['title', {}]"}],attrs:{disabled:e.modalData.disabled}})],1),a("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"上报人","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[a("a-tree-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["reportor",{}],expression:"['reportor', {}]"}],staticStyle:{width:"100%"},attrs:{disabled:e.modalData.disabled,"dropdown-style":{maxHeight:"400px",overflow:"auto"},"tree-data":e.personTreeData,placeholder:"","tree-default-expand-all":""}})],1),a("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"处置时限","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[a("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["limittimes",{}],expression:"['limittimes', {}]"}],attrs:{disabled:e.modalData.disabled}})],1),a("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"备注","label-col":{span:3},"wrapper-col":{span:21}}},[a("a-textarea",{directives:[{name:"decorator",rawName:"v-decorator",value:["caseDesc",{rules:[{required:!0,message:"备注必填！"}]}],expression:"['caseDesc', { rules: [{ required: true, message: '备注必填！' }] }]"}],attrs:{disabled:e.modalData.disabled,"auto-size":{minRows:2,maxRows:6}}})],1),a("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"公司","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[a("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["companyId",{}],expression:"['companyId', {}]"}],staticStyle:{width:"200px"},attrs:{disabled:e.modalData.disabled,"show-search":"",placeholder:"","option-filter-prop":"children","filter-option":e.filterOption}},e._l(e.companyManageDataList,(function(t){return a("a-select-option",{key:t.id,attrs:{value:t.id}},[e._v(" "+e._s(t.companyName)+" ")])})),1)],1),a("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"所属网格","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[a("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["gridId",{}],expression:"['gridId', {}]"}],staticStyle:{width:"200px"},attrs:{disabled:e.modalData.disabled,"show-search":"",placeholder:"","option-filter-prop":"children","filter-option":e.filterOption}},e._l(e.gridDataList,(function(t){return a("a-select-option",{key:t.id,attrs:{value:t.id}},[e._v(" "+e._s(t.gridName)+" ")])})),1)],1),a("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"位置描述","label-col":{span:3},"wrapper-col":{span:21}}},[a("a-textarea",{directives:[{name:"decorator",rawName:"v-decorator",value:["locationDesc",{rules:[{required:!0,message:"位置描述必填！"}]}],expression:"['locationDesc', { rules: [{ required: true, message: '位置描述必填！' }] }]"}],attrs:{disabled:e.modalData.disabled,"auto-size":{minRows:2,maxRows:6}}})],1),a("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"图片上传","label-col":{span:3},"wrapper-col":{span:21}}},[a("a-upload",{attrs:{disabled:e.modalData.disabled,name:"avatar",action:e.BASE_URL+"/upload-avatar",multiple:!0,"list-type":"picture-card","file-list":e.imageList},on:{preview:e.handlePreview,change:e.handleImageChange}},[a("a-icon",{attrs:{type:"plus"}}),a("div",{staticClass:"ant-upload-text"},[e._v("上传图片")])],1),a("a-modal",{attrs:{visible:e.previewVisible,footer:null},on:{cancel:e.handlePreviewCancel}},[a("img",{staticStyle:{width:"100%"},attrs:{alt:"example",src:e.previewImage}})])],1)],1)],2)},i=[],r=(a("fe59"),a("ecb4"),a("9302"),a("e18c"),a("e35a"),a("0d7a"),a("08ba"),a("6a61"),a("327b")),o=a("6c6d"),l=a("81ef"),s=a("e473"),c=a("0fea"),d=a("d4f4"),u=a("1cfa"),m=a.n(u),p=(a("caaf"),a("5880")),f="";function h(e){return new Promise((function(t,a){var n=new FileReader;n.readAsDataURL(e),n.onload=function(){return t(n.result)},n.onerror=function(e){return a(e)}}))}var g={props:["modalData"],data:function(){return{BASE_URL:f,labelCol:{span:6},wrapperCol:{span:18},formLayout:"inline",confirmLoading:!1,deptTreeData:[],personTreeData:[],previewVisible:!1,previewImage:"",imageList:[],fileList:[],reportImageList:[],reportFileList:[],gridDataList:[],companyManageDataList:[]}},computed:Object(o["a"])({},Object(p["mapGetters"])(["userInfo"])),watch:{modalData:function(e){var t=this;this.form.resetFields(),!0===e.visible&&this.$nextTick((function(){var e=t.modalData.record.fileName,a=t.modalData.record.filePath;if(t.imageList=[],e&&""!==e){e=e.split(","),a=a.split(",");for(var n=0;n<e.length;n++){var i={uid:n,name:e[n],fileName:e[n],status:"done",url:f+a[n]};t.imageList.push(i)}}t.form.setFieldsValue(Object(o["a"])({},t.modalData.record))}))}},beforeCreate:function(){this.form=this.$form.createForm(this)},created:function(){this.getSubCompanyUserTree(),this.gridList(),this.companyManageList()},methods:{dateFormat:function(e){var t=new Date(e),a=t.getFullYear(),n=t.getMonth()+1<10?"0"+(t.getMonth()+1):t.getMonth()+1,i=t.getDate()<10?"0"+t.getDate():t.getDate(),r=t.getHours()<10?"0"+t.getHours():t.getHours(),o=t.getMinutes()<10?"0"+t.getMinutes():t.getMinutes(),l=t.getSeconds()<10?"0"+t.getSeconds():t.getSeconds();return a+"-"+n+"-"+i+" "+r+":"+o+":"+l},handleOk:function(e){var t=this;this.form.validateFields((function(a,n){if(!a){Object.assign(n,{reportTime:t.dateFormat(new Date),status:e});var i=[],r=[];t.imageList.forEach((function(e){var t=null;t=void 0!==e.response?e.response.result:e,i.push(t.url),r.push(t.fileName)})),i.length>0&&(n.filePath=i.join(","),n.fileName=r.join(","));var o=void 0===n.id?d["c"]:d["d"];o(n).then((function(e){t.$emit("handleModalEvent",e),t.handleCancel()})).catch((function(e){}))}}))},gridList:function(){var e=this;Object(l["d"])().then((function(t){200===t.code&&(e.gridDataList=t.result.data)})).catch((function(e){}))},companyManageList:function(){var e=this;Object(s["b"])().then((function(t){e.companyManageDataList=t.result.data})).catch((function(e){}))},filterOption:function(e,t){return t.componentOptions.children[0].text.toLowerCase().indexOf(e.toLowerCase())>=0},handleCancel:function(){this.modalData.visible=!1},handlePreviewCancel:function(){this.previewVisible=!1},handlePreview:function(e){var t=this;return Object(r["a"])(regeneratorRuntime.mark((function a(){return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:if(e.url||e.preview){a.next=4;break}return a.next=3,h(e.originFileObj);case 3:e.preview=a.sent;case 4:t.previewImage=e.url||e.preview,t.previewVisible=!0;case 6:case"end":return a.stop()}}),a)})))()},handleImageChange:function(e){var t=e.fileList;this.imageList=t},handleFileChange:function(e){var t=e.fileList;this.fileList=t},handleRemove:function(e){var t=e.response.result.id;void 0!==t&&Object(d["f"])(m.a.stringify({id:t})).then((function(e){e.code})).catch((function(){}))},getSubCompanyUserTree:function(){var e=this;Object(c["l"])().then((function(t){200===t.code&&(e.personTreeData=t.result)})).catch((function(){}))}}},b=g,v=(a("9e80"),a("c701")),w=Object(v["a"])(b,n,i,!1,null,"9c9a56a4",null);t["a"]=w.exports},"95ac":function(e,t,a){"use strict";a("7a41")},"9e80":function(e,t,a){"use strict";a("4d15")},e473:function(e,t,a){"use strict";a.d(t,"b",(function(){return r})),a.d(t,"c",(function(){return o})),a.d(t,"a",(function(){return l})),a.d(t,"d",(function(){return s}));var n=a("b775"),i={companyManageList:"/companyManage/find",companyManagePost:"/companyManage/post",companyManageDelete:"/companyManage/delete",companyManagePut:"/companyManage/put"};function r(e){return Object(n["b"])({url:i.companyManageList,method:"post",data:e})}function o(e){return Object(n["b"])({url:i.companyManagePost,method:"post",data:e})}function l(e){return Object(n["b"])({url:i.companyManageDelete,method:"post",data:e})}function s(e){return Object(n["b"])({url:i.companyManagePut,method:"post",data:e})}},ed17:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("div",{staticClass:"table-operator"},[a("a-button",{attrs:{type:"primary",icon:"plus"},on:{click:e.add}},[e._v("新建")]),a("a-button",{attrs:{type:"danger",icon:"delete"},on:{click:function(t){return e.del("batch")}}},[e._v("删除")])],1),a("a-table",{attrs:{"row-selection":{selectedRowKeys:e.selectedRowKeys,onChange:e.onSelectChange},columns:e.columns,"data-source":e.data,rowKey:"id",pagination:{current:e.pagination.current,pageSize:e.pagination.pageSize,total:e.pagination.total,"show-size-changer":!0,"show-quick-jumper":!0,"show-total":function(e){return"共 "+e+" 条"}}},on:{change:e.change},scopedSlots:e._u([{key:"serial",fn:function(t,n,i){return a("span",{},[e._v(" "+e._s(i+1)+" ")])}},{key:"action",fn:function(t,n){return[a("a",{on:{click:function(t){return e.info(n)}}},[e._v("查看")]),a("a-divider",{attrs:{type:"vertical"}}),a("a",{on:{click:function(t){return e.edit(n)}}},[e._v("编辑")]),a("a-divider",{attrs:{type:"vertical"}}),a("a",{on:{click:function(t){return e.del("single",n)}}},[e._v("删除")])]}}])}),a("ReportEdit",{attrs:{modalData:e.modalData},on:{handleModalEvent:e.handleModalEvent}})],1)},i=[],r=a("6c6d"),o=a("d4f4"),l=a("850b"),s=a("1cfa"),c=a.n(s),d=a("2af9"),u=[{title:"序号",scopedSlots:{customRender:"serial"}},{title:"标题",dataIndex:"title",key:"title"},{title:"上报时间",dataIndex:"reportTime",key:"caseType"},{title:"上报人",dataIndex:"reportorName",key:"reportorName"},{title:"处置时限",dataIndex:"limittimes",key:"limittimes"},{title:"备注",dataIndex:"caseDesc",key:"caseDesc"},{title:"位置描述",dataIndex:"locationDesc",key:"locationDesc"},{title:"操作",key:"action",align:"center",scopedSlots:{customRender:"action"}}],m={components:{ReportEdit:l["a"],Ellipsis:d["f"]},mounted:function(){this.loadData()},data:function(){return{data:[],columns:u,selectedRowKeys:[],pagination:{current:1,pageSize:10,total:0},modalData:{},queryParam:{}}},methods:{loadData:function(){var e=this,t=Object(r["a"])({pageNo:this.pagination.current,pageSize:this.pagination.pageSize,status:0},this.queryParam);Object(o["b"])(c.a.stringify(t)).then((function(t){e.data=t.result.data,e.pagination.total=t.result.totalCount})).catch((function(e){}))},add:function(){this.modalData={record:{},visible:!0,disabled:!1,title:"问题上报"}},del:function(e,t){var a=this,n="single"===e?[t.id]:this.selectedRowKeys;Object(o["a"])(n).then((function(e){a.loadData()})).catch((function(e){}))},edit:function(e){this.modalData={record:e,visible:!0,disabled:!1,title:"问题上报"}},info:function(e){this.modalData={record:e,visible:!0,disabled:!0,title:"问题上报"}},handleModalEvent:function(e){this.loadData()},onSelectChange:function(e){this.selectedRowKeys=e},change:function(e,t,a){this.pagination=e,this.loadData()}}},p=m,f=(a("95ac"),a("c701")),h=Object(f["a"])(p,n,i,!1,null,"1aada5ee",null);t["default"]=h.exports}}]);