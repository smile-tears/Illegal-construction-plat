(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1d3f2c0a"],{"0621":function(e,t,r){"use strict";var a=r("0e13"),s=r.n(a);s.a},"0e13":function(e,t,r){},"5c62":function(e,t,r){"use strict";r.d(t,"a",(function(){return l}));var a=r("b49a");function s(e){if(Array.isArray(e))return Object(a["a"])(e)}r("f3dd"),r("0a51"),r("9b11"),r("98e0"),r("a133"),r("e18c"),r("96db"),r("af86");function o(e){if("undefined"!==typeof Symbol&&Symbol.iterator in Object(e))return Array.from(e)}var n=r("271f");function i(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}function l(e){return s(e)||o(e)||Object(n["a"])(e)||i()}},"70d7":function(e,t,r){"use strict";r.r(t);var a=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("a-card",{staticClass:"card",attrs:{title:"仓库管理",bordered:!1}},[r("repository-form",{ref:"repository",attrs:{showSubmit:!1}})],1),r("a-card",{staticClass:"card",attrs:{title:"任务管理",bordered:!1}},[r("task-form",{ref:"task",attrs:{showSubmit:!1}})],1),r("a-card",[r("a-table",{attrs:{columns:e.columns,dataSource:e.data,pagination:!1,loading:e.memberLoading},scopedSlots:e._u([e._l(["name","workId","department"],(function(t,a){return{key:t,fn:function(s,o){return[o.editable?r("a-input",{key:t,staticStyle:{margin:"-5px 0"},attrs:{value:s,placeholder:e.columns[a].title},on:{change:function(r){return e.handleChange(r.target.value,o.key,t)}}}):[e._v(e._s(s))]]}}})),{key:"operation",fn:function(t,a){return[a.editable?[a.isNew?r("span",[r("a",{on:{click:function(t){return e.saveRow(a)}}},[e._v("添加")]),r("a-divider",{attrs:{type:"vertical"}}),r("a-popconfirm",{attrs:{title:"是否要删除此行？"},on:{confirm:function(t){return e.remove(a.key)}}},[r("a",[e._v("删除")])])],1):r("span",[r("a",{on:{click:function(t){return e.saveRow(a)}}},[e._v("保存")]),r("a-divider",{attrs:{type:"vertical"}}),r("a",{on:{click:function(t){return e.cancel(a.key)}}},[e._v("取消")])],1)]:r("span",[r("a",{on:{click:function(t){return e.toggle(a.key)}}},[e._v("编辑")]),r("a-divider",{attrs:{type:"vertical"}}),r("a-popconfirm",{attrs:{title:"是否要删除此行？"},on:{confirm:function(t){return e.remove(a.key)}}},[r("a",[e._v("删除")])])],1)]}}],null,!0)}),r("a-button",{staticStyle:{width:"100%","margin-top":"16px","margin-bottom":"8px"},attrs:{type:"dashed",icon:"plus"},on:{click:e.newMember}},[e._v("新增成员")])],1),r("footer-tool-bar",{style:{width:e.isSideMenu()&&e.isDesktop()?"calc(100% - "+(e.sidebarOpened?256:80)+"px)":"100%"}},[r("span",{staticClass:"popover-wrapper"},[r("a-popover",{attrs:{title:"表单校验信息",overlayClassName:"antd-pro-pages-forms-style-errorPopover",trigger:"click",getPopupContainer:function(e){return e.parentNode}}},[r("template",{slot:"content"},e._l(e.errors,(function(t){return r("li",{key:t.key,staticClass:"antd-pro-pages-forms-style-errorListItem",on:{click:function(r){return e.scrollToField(t.key)}}},[r("a-icon",{staticClass:"antd-pro-pages-forms-style-errorIcon",attrs:{type:"cross-circle-o"}}),r("div",{},[e._v(e._s(t.message))]),r("div",{staticClass:"antd-pro-pages-forms-style-errorField"},[e._v(e._s(t.fieldLabel))])],1)})),0),e.errors.length>0?r("span",{staticClass:"antd-pro-pages-forms-style-errorIcon"},[r("a-icon",{attrs:{type:"exclamation-circle"}}),e._v(e._s(e.errors.length)+" ")],1):e._e()],2)],1),r("a-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.validate}},[e._v("提交")])],1)],1)},s=[],o=(r("dbb3"),r("4194"),r("fe59"),r("2eeb"),r("053b"),r("fe8a"),r("e18c"),r("1c2e"),r("96db"),r("08ba"),r("af86"),r("5c62")),n=r("80a0"),i=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("a-form",{staticClass:"form",attrs:{form:e.form},on:{submit:e.handleSubmit}},[r("a-row",{staticClass:"form-row",attrs:{gutter:16}},[r("a-col",{attrs:{lg:6,md:12,sm:24}},[r("a-form-item",{attrs:{label:"仓库名"}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["name",{rules:[{required:!0,message:"请输入仓库名称",whitespace:!0}]}],expression:"[\n            'name',\n            {rules: [{ required: true, message: '请输入仓库名称', whitespace: true}]}\n          ]"}],attrs:{placeholder:"请输入仓库名称"}})],1)],1),r("a-col",{attrs:{xl:{span:7,offset:1},lg:{span:8},md:{span:12},sm:24}},[r("a-form-item",{attrs:{label:"仓库域名"}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["url",{rules:[{required:!0,message:"请输入仓库域名",whitespace:!0},{validator:e.validate}]}],expression:"[\n            'url',\n            {rules: [{ required: true, message: '请输入仓库域名', whitespace: true}, {validator: validate}]}\n          ]"}],attrs:{addonBefore:"http://",addonAfter:".com",placeholder:"请输入"}})],1)],1),r("a-col",{attrs:{xl:{span:9,offset:1},lg:{span:10},md:{span:24},sm:24}},[r("a-form-item",{attrs:{label:"仓库管理员"}},[r("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["owner",{rules:[{required:!0,message:"请选择管理员"}]}],expression:"[ 'owner', {rules: [{ required: true, message: '请选择管理员'}]} ]"}],attrs:{placeholder:"请选择管理员"}},[r("a-select-option",{attrs:{value:"王同学"}},[e._v("王同学")]),r("a-select-option",{attrs:{value:"李同学"}},[e._v("李同学")]),r("a-select-option",{attrs:{value:"黄同学"}},[e._v("黄同学")])],1)],1)],1)],1),r("a-row",{staticClass:"form-row",attrs:{gutter:16}},[r("a-col",{attrs:{lg:6,md:12,sm:24}},[r("a-form-item",{attrs:{label:"审批人"}},[r("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["approver",{rules:[{required:!0,message:"请选择审批员"}]}],expression:"[ 'approver', {rules: [{ required: true, message: '请选择审批员'}]} ]"}],attrs:{placeholder:"请选择审批员"}},[r("a-select-option",{attrs:{value:"王晓丽"}},[e._v("王晓丽")]),r("a-select-option",{attrs:{value:"李军"}},[e._v("李军")])],1)],1)],1),r("a-col",{attrs:{xl:{span:7,offset:1},lg:{span:8},md:{span:12},sm:24}},[r("a-form-item",{attrs:{label:"生效日期"}},[r("a-range-picker",{directives:[{name:"decorator",rawName:"v-decorator",value:["dateRange",{rules:[{required:!0,message:"请选择生效日期"}]}],expression:"[\n            'dateRange',\n            {rules: [{ required: true, message: '请选择生效日期'}]}\n          ]"}],staticStyle:{width:"100%"}})],1)],1),r("a-col",{attrs:{xl:{span:9,offset:1},lg:{span:10},md:{span:24},sm:24}},[r("a-form-item",{attrs:{label:"仓库类型"}},[r("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["type",{rules:[{required:!0,message:"请选择仓库类型"}]}],expression:"[\n            'type',\n            {rules: [{ required: true, message: '请选择仓库类型'}]}\n          ]"}],attrs:{placeholder:"请选择仓库类型"}},[r("a-select-option",{attrs:{value:"公开"}},[e._v("公开")]),r("a-select-option",{attrs:{value:"私密"}},[e._v("私密")])],1)],1)],1)],1),e.showSubmit?r("a-form-item",[r("a-button",{attrs:{htmlType:"submit"}},[e._v("Submit")])],1):e._e()],1)},l=[],c={name:"RepositoryForm",props:{showSubmit:{type:Boolean,default:!1}},data:function(){return{form:this.$form.createForm(this)}},methods:{handleSubmit:function(e){var t=this;e.preventDefault(),this.form.validateFields((function(e,r){e||t.$notification["error"]({message:"Received values of form:",description:r})}))},validate:function(e,t,r){var a=/^user-(.*)$/;a.test(t)||r(new Error("需要以 user- 开头")),r()}}},d=c,u=r("4023"),m=Object(u["a"])(d,i,l,!1,null,"3ae25108",null),p=m.exports,f=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("a-form",{staticClass:"form",attrs:{form:e.form},on:{submit:e.handleSubmit}},[r("a-row",{staticClass:"form-row",attrs:{gutter:16}},[r("a-col",{attrs:{lg:6,md:12,sm:24}},[r("a-form-item",{attrs:{label:"任务名"}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["name2",{rules:[{required:!0,message:"请输入任务名称",whitespace:!0}]}],expression:"[ 'name2', {rules: [{ required: true, message: '请输入任务名称', whitespace: true}]} ]"}],attrs:{placeholder:"请输入任务名称"}})],1)],1),r("a-col",{attrs:{xl:{span:7,offset:1},lg:{span:8},md:{span:12},sm:24}},[r("a-form-item",{attrs:{label:"任务描述"}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["url2",{rules:[{required:!0,message:"请输入任务描述",whitespace:!0}]}],expression:"[ 'url2', {rules: [{ required: true, message: '请输入任务描述', whitespace: true}]} ]"}],attrs:{placeholder:"请输入任务描述"}})],1)],1),r("a-col",{attrs:{xl:{span:9,offset:1},lg:{span:10},md:{span:24},sm:24}},[r("a-form-item",{attrs:{label:"执行人"}},[r("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["owner2",{rules:[{required:!0,message:"请选择执行人"}]}],expression:"[\n            'owner2',\n            {rules: [{ required: true, message: '请选择执行人'}]}\n          ]"}],attrs:{placeholder:"请选择执行人"}},[r("a-select-option",{attrs:{value:"黄丽丽"}},[e._v("黄丽丽")]),r("a-select-option",{attrs:{value:"李大刀"}},[e._v("李大刀")])],1)],1)],1)],1),r("a-row",{staticClass:"form-row",attrs:{gutter:16}},[r("a-col",{attrs:{lg:6,md:12,sm:24}},[r("a-form-item",{attrs:{label:"责任人"}},[r("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["approver2",{rules:[{required:!0,message:"请选择责任人"}]}],expression:"[\n            'approver2',\n            {rules: [{ required: true, message: '请选择责任人'}]}\n          ]"}],attrs:{placeholder:"请选择责任人"}},[r("a-select-option",{attrs:{value:"王伟"}},[e._v("王伟")]),r("a-select-option",{attrs:{value:"李红军"}},[e._v("李红军")])],1)],1)],1),r("a-col",{attrs:{xl:{span:7,offset:1},lg:{span:8},md:{span:12},sm:24}},[r("a-form-item",{attrs:{label:"提醒时间"}},[r("a-time-picker",{directives:[{name:"decorator",rawName:"v-decorator",value:["dateRange2",{rules:[{required:!0,message:"请选择提醒时间"}]}],expression:"[\n            'dateRange2',\n            {rules: [{ required: true, message: '请选择提醒时间'}]}\n          ]"}],staticStyle:{width:"100%"}})],1)],1),r("a-col",{attrs:{xl:{span:9,offset:1},lg:{span:10},md:{span:24},sm:24}},[r("a-form-item",{attrs:{label:"任务类型"}},[r("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["type2",{rules:[{required:!0,message:"请选择任务类型"}]}],expression:"[ 'type2', {rules: [{ required: true, message: '请选择任务类型'}]} ]"}],attrs:{placeholder:"请选择任务类型"}},[r("a-select-option",{attrs:{value:"定时执行"}},[e._v("定时执行")]),r("a-select-option",{attrs:{value:"周期执行"}},[e._v("周期执行")])],1)],1)],1)],1),e.showSubmit?r("a-form-item",[r("a-button",{attrs:{htmlType:"submit"}},[e._v("Submit")])],1):e._e()],1)},v=[],g={name:"TaskForm",props:{showSubmit:{type:Boolean,default:!1}},data:function(){return{form:this.$form.createForm(this)}},methods:{handleSubmit:function(e){var t=this;e.preventDefault(),this.form.validateFields((function(e,r){e||t.$notification["error"]({message:"Received values of form:",description:r})}))}}},h=g,b=Object(u["a"])(h,f,v,!1,null,"1aedc784",null),w=b.exports,y=r("5a70"),k=r("ac0d"),_={name:"仓库名",url:"仓库域名",owner:"仓库管理员",approver:"审批人",dateRange:"生效日期",type:"仓库类型",name2:"任务名",url2:"任务描述",owner2:"执行人",approver2:"责任人",dateRange2:"生效日期",type2:"任务类型"},x={name:"AdvancedForm",mixins:[k["b"],k["c"]],components:{FooterToolBar:y["a"],RepositoryForm:p,TaskForm:w},data:function(){return{description:"高级表单常见于一次性输入和提交大批量数据的场景。",loading:!1,memberLoading:!1,columns:[{title:"成员姓名",dataIndex:"name",key:"name",width:"20%",scopedSlots:{customRender:"name"}},{title:"工号",dataIndex:"workId",key:"workId",width:"20%",scopedSlots:{customRender:"workId"}},{title:"所属部门",dataIndex:"department",key:"department",width:"40%",scopedSlots:{customRender:"department"}},{title:"操作",key:"action",scopedSlots:{customRender:"operation"}}],data:[{key:"1",name:"小明",workId:"001",editable:!1,department:"行政部"},{key:"2",name:"李莉",workId:"002",editable:!1,department:"IT部"},{key:"3",name:"王小帅",workId:"003",editable:!1,department:"财务部"}],errors:[]}},methods:{handleSubmit:function(e){e.preventDefault()},newMember:function(){var e=this.data.length;this.data.push({key:0===e?"1":(parseInt(this.data[e-1].key)+1).toString(),name:"",workId:"",department:"",editable:!0,isNew:!0})},remove:function(e){var t=this.data.filter((function(t){return t.key!==e}));this.data=t},saveRow:function(e){var t=this;this.memberLoading=!0;var r=e.key,a=e.name,s=e.workId,o=e.department;if(!a||!s||!o)return this.memberLoading=!1,void this.$message.error("请填写完整成员信息。");new Promise((function(e){setTimeout((function(){e({loop:!1})}),800)})).then((function(){var e=t.data.find((function(e){return e.key===r}));e.editable=!1,e.isNew=!1,t.memberLoading=!1}))},toggle:function(e){var t=this.data.find((function(t){return t.key===e}));t._originalData=Object(n["a"])({},t),t.editable=!t.editable},getRowByKey:function(e,t){var r=this.data;return(t||r).find((function(t){return t.key===e}))},cancel:function(e){var t=this.data.find((function(t){return t.key===e}));Object.keys(t).forEach((function(e){t[e]=t._originalData[e]})),t._originalData=void 0},handleChange:function(e,t,r){var a=Object(o["a"])(this.data),s=a.find((function(e){return t===e.key}));s&&(s[r]=e,this.data=a)},validate:function(){var e=this,t=this.$refs,r=t.repository,a=t.task,s=this.$notification,o=new Promise((function(e,t){r.form.validateFields((function(r,a){r?t(r):e(a)}))})),i=new Promise((function(e,t){a.form.validateFields((function(r,a){r?t(r):e(a)}))}));this.errors=[],Promise.all([o,i]).then((function(e){s["error"]({message:"Received values of form:",description:JSON.stringify(e)})})).catch((function(){var t=Object.assign({},r.form.getFieldsError(),a.form.getFieldsError()),s=Object(n["a"])({},t);e.errorList(s)}))},errorList:function(e){e&&0!==e.length&&(this.errors=Object.keys(e).filter((function(t){return e[t]})).map((function(t){return{key:t,message:e[t][0],fieldLabel:_[t]}})))},scrollToField:function(e){var t=document.querySelector('label[for="'.concat(e,'"]'));t&&t.scrollIntoView(!0)}}},S=x,q=(r("0621"),Object(u["a"])(S,a,s,!1,null,"3e3d8762",null));t["default"]=q.exports}}]);