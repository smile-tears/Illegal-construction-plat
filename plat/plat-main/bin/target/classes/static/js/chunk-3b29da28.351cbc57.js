(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3b29da28"],{"05d9":function(e,a,t){"use strict";var n=t("5ad1"),o=t.n(n);o.a},"48a0":function(e,a,t){},"5ad1":function(e,a,t){},8604:function(e,a,t){"use strict";t.r(a);var n=function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"container"},[t("div",{staticClass:"table-page-search-wrapper"},[t("a-form",{attrs:{layout:"inline"}},[t("a-row",{attrs:{gutter:48}},[t("a-col",{attrs:{md:8,sm:8}},[t("a-form-item",{attrs:{label:"角色名称"}},[t("a-input",{attrs:{placeholder:""},model:{value:e.queryParam.roleName,callback:function(a){e.$set(e.queryParam,"roleName",a)},expression:"queryParam.roleName"}})],1)],1),t("a-col",{attrs:{md:8,sm:8}},[t("a-button",{attrs:{type:"primary",icon:"search"},on:{click:e.loadData}},[e._v("查询")]),t("a-button",{staticStyle:{"margin-left":"8px"},on:{click:function(){return e.queryParam={}}}},[e._v("重置")])],1)],1)],1)],1),t("div",{staticClass:"table-operator"},[t("a-button",{attrs:{type:"primary",icon:"plus"},on:{click:e.add}},[e._v("新建")]),t("a-button",{attrs:{type:"danger",icon:"delete"},on:{click:function(a){return e.del("batch")}}},[e._v("删除")])],1),t("a-table",{attrs:{"row-selection":{selectedRowKeys:e.selectedRowKeys,onChange:e.onSelectChange},columns:e.columns,"data-source":e.data,rowKey:"id",pagination:{current:e.pagination.current,pageSize:e.pagination.pageSize,total:e.pagination.total,"show-size-changer":!0,"show-quick-jumper":!0,"show-total":function(e){return"共 "+e+" 条"}}},on:{change:e.change},scopedSlots:e._u([{key:"serial",fn:function(a,n,o){return t("span",{},[e._v(" "+e._s(o+1)+" ")])}},{key:"roleDesc",fn:function(a){return t("span",{},[t("Ellipsis",{attrs:{length:36,tooltip:""}},[e._v(e._s(a))])],1)}},{key:"action",fn:function(a,n){return[t("a",{on:{click:function(a){return e.info(n)}}},[e._v("查看")]),t("a-divider",{attrs:{type:"vertical"}}),t("a",{on:{click:function(a){return e.edit(n)}}},[e._v("编辑")]),t("a-divider",{attrs:{type:"vertical"}}),t("a",{on:{click:function(a){return e.del("single",n)}}},[e._v("删除")])]}}])}),t("RoleEdit",{attrs:{modalData:e.modalData},on:{handleModalEvent:e.handleModalEvent}})],1)},o=[],i=t("80a0"),r=t("cc5e"),l=function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("a-modal",{staticClass:"modal",attrs:{title:e.modalData.title,width:600,visible:e.modalData.visible,footer:null,maskClosable:!1},on:{cancel:e.handleCancel}},[t("a-tabs",{attrs:{"default-active-key":"1",animated:!1},on:{change:e.handleTabChange}},[t("a-tab-pane",{key:"1",attrs:{tab:"角色信息"}},[t("a-form",{attrs:{layout:e.formLayout,form:e.form}},[t("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{label:"角色id","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[t("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["id",{}],expression:"['id', {}]"}],attrs:{disabled:e.modalData.disabled}})],1),t("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"角色名称","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[t("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["roleName",{rules:[{required:!0,message:"角色名称"}]}],expression:"['roleName', {rules: [{ required: true, message: '角色名称'}]}]"}],attrs:{disabled:e.modalData.disabled}})],1),t("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"角色描述","label-col":e.labelCol,"wrapper-col":e.wrapperCol}},[t("a-textarea",{directives:[{name:"decorator",rawName:"v-decorator",value:["roleDesc",{}],expression:"['roleDesc', {}]"}],attrs:{"allow-clear":"",rows:3,disabled:e.modalData.disabled}})],1)],1)],1),t("a-tab-pane",{key:"2",attrs:{tab:"成员列表","force-render":""}},[t("a-table",{attrs:{"row-selection":{selectedRowKeys:e.selectedRowKeys,onChange:e.onSelectChange},columns:e.columns,"data-source":e.data,rowKey:"id",pagination:{current:e.pagination.current,pageSize:e.pagination.pageSize,total:e.pagination.total,"show-size-changer":!0,"show-quick-jumper":!0,"show-total":function(e){return"共 "+e+" 条"}}},on:{change:e.change}})],1),t("div",{staticClass:"btn-container",attrs:{slot:"tabBarExtraContent"},slot:"tabBarExtraContent"},[t("a-button",{directives:[{name:"show",rawName:"v-show",value:1==e.key,expression:"key == 1"}],attrs:{type:"primary"},on:{click:e.handleOk}},[e._v("保存")]),t("a-button",{directives:[{name:"show",rawName:"v-show",value:2==e.key,expression:"key == 2"}],attrs:{type:"primary"},on:{click:e.addUser}},[e._v("新建")]),t("a-button",{directives:[{name:"show",rawName:"v-show",value:2==e.key,expression:"key == 2"}],attrs:{type:"danger"},on:{click:e.delUser}},[e._v("批量删除")])],1)],1),t("a-modal",{staticClass:"modal",attrs:{title:"添加人员",width:500,visible:e.visible2,maskClosable:!1},on:{cancel:e.handleCancel2,ok:e.handleOk2}},[t("a-tree-select",{staticStyle:{width:"100%"},attrs:{"show-search":"",treeData:e.treeData,value:e.value,"dropdown-style":{maxHeight:"400px",overflow:"auto"},placeholder:"Please select","allow-clear":"",multiple:"","tree-default-expand-all":""},on:{change:e.onChange}})],1)],1)},s=[],c=(t("fe59"),t("08ba"),t("0fea")),d=t("1cfa"),u=t.n(d),h=[{title:"用户名",dataIndex:"name",key:"name"},{title:"登录账号",dataIndex:"username",key:"username"}],m={props:["modalData"],data:function(){return{labelCol:{xs:{span:24},sm:{span:5}},wrapperCol:{xs:{span:24},sm:{span:16}},formLayout:"horizontal",key:1,data:[],columns:h,selectedRowKeys:[],pagination:{current:1,pageSize:10,total:0},visible2:!1,treeData:null,value:void 0}},watch:{modalData:function(e){var a=this;this.form.resetFields(),!0===e.visible&&(this.$nextTick((function(){delete a.modalData.record.delTag,a.form.setFieldsValue(Object(i["a"])({},a.modalData.record))})),this.loadUserList())}},beforeCreate:function(){this.form=this.$form.createForm(this)},methods:{handleOk:function(){var e=this;this.form.validateFields((function(a,t){if(!a){var n=void 0===t.id?r["c"]:r["d"];n(t).then((function(a){e.$emit("handleModalEvent",a),e.handleCancel()})).catch((function(){}))}}))},handleOk2:function(){var e=this,a=this.modalData.record.id,t=[];this.value.forEach((function(e){t.push({roleId:a,userId:e})})),Object(r["g"])(t).then((function(a){200===a.code&&(e.handleCancel2(),e.loadUserList())})).catch((function(){}))},addUser:function(){this.visible2=!0},delUser:function(){var e=this;Object(r["e"])(this.selectedRowKeys).then((function(a){200===a.code&&e.loadUserList()})).catch((function(){}))},handleCancel:function(){this.modalData.visible=!1},handleCancel2:function(){this.visible2=!1},handleTabChange:function(e){var a=this;this.key=e,"2"==e&&null==this.treeData&&Object(c["n"])().then((function(e){200===e.code&&(a.recursionUserTree(e.result),a.treeData=e.result)})).catch((function(){}))},loadUserList:function(){var e=this,a={pageNo:this.pagination.current,pageSize:this.pagination.pageSize,roleId:this.modalData.record.id};Object(r["f"])(u.a.stringify(a)).then((function(a){200===a.code&&(e.data=a.result.data)})).catch((function(){}))},recursionUserTree:function(e){var a=this;e.forEach((function(e){"user"!=e.type&&(e.disabled=!0),e.children&&e.children.length>0&&a.recursionUserTree(e.children)}))},onSelectChange:function(e){this.selectedRowKeys=e},change:function(e,a,t){this.pagination=e},onChange:function(e){this.value=e}}},p=m,f=(t("05d9"),t("4023")),v=Object(f["a"])(p,l,s,!1,null,null,null),b=v.exports,w=t("2af9"),g=[{title:"序号",scopedSlots:{customRender:"serial"}},{title:"角色名称",dataIndex:"roleName",key:"roleName"},{title:"角色描述",dataIndex:"roleDesc",key:"roleDesc",width:"30%",scopedSlots:{customRender:"roleDesc"}},{title:"操作",key:"action",align:"center",scopedSlots:{customRender:"action"}}],y={components:{RoleEdit:b,Ellipsis:w["h"]},mounted:function(){this.loadData()},data:function(){return{data:[],columns:g,selectedRowKeys:[],pagination:{current:1,pageSize:10,total:0},modalData:{},queryParam:{}}},methods:{loadData:function(){var e=this,a=Object(i["a"])({pageNo:this.pagination.current,pageSize:this.pagination.pageSize},this.queryParam);Object(r["b"])(u.a.stringify(a)).then((function(a){e.data=a.result.data,e.pagination.total=a.result.totalCount})).catch((function(e){}))},add:function(){this.modalData={record:{},visible:!0,disabled:!1,title:"新建角色"}},del:function(e,a){var t=this,n="single"===e?[a.id]:this.selectedRowKeys;Object(r["a"])(n).then((function(e){t.loadData()})).catch((function(e){}))},edit:function(e){this.modalData={record:e,visible:!0,disabled:!1,title:"编辑角色"}},info:function(e){this.modalData={record:e,visible:!0,disabled:!0,title:"查看角色"}},handleModalEvent:function(e){this.loadData()},onSelectChange:function(e){this.selectedRowKeys=e},change:function(e,a,t){this.pagination=e,this.loadData()}}},k=y,C=(t("dd6f"),Object(f["a"])(k,n,o,!1,null,"b258a110",null));a["default"]=C.exports},dd6f:function(e,a,t){"use strict";var n=t("48a0"),o=t.n(n);o.a}}]);