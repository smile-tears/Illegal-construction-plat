(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5e1b25ba"],{1483:function(e,a,t){},2535:function(e,a,t){"use strict";var r=t("1483"),l=t.n(r);l.a},"47bc":function(e,a,t){"use strict";t.d(a,"e",(function(){return o})),t.d(a,"b",(function(){return i})),t.d(a,"c",(function(){return s})),t.d(a,"a",(function(){return n})),t.d(a,"d",(function(){return c}));var r=t("b775"),l={sampleTree:"/sample/tree",sampleList:"/sample/find",samplePost:"/sample/post",sampleDelete:"/sample/delete",samplePut:"/sample/put"};function o(e){return Object(r["b"])({url:l.sampleTree,method:"post",data:e})}function i(e){return Object(r["b"])({url:l.sampleList,method:"post",data:e})}function s(e){return Object(r["b"])({url:l.samplePost,method:"post",data:e})}function n(e){return Object(r["b"])({url:l.sampleDelete,method:"post",data:e})}function c(e){return Object(r["b"])({url:l.samplePut,method:"post",data:e})}},"850b":function(e,a,t){"use strict";var r=function(){var e=this,a=this,t=a.$createElement,r=a._self._c||t;return r("a-modal",{attrs:{title:a.modalData.title,width:800,visible:a.modalData.visible,confirmLoading:a.confirmLoading,maskClosable:!1},on:{cancel:a.handleCancel}},[r("template",{slot:"footer"},[r("a-button",{on:{click:a.handleCancel}},[a._v(" 取消 ")]),"问题上报"!==a.modalData.title||a.modalData.disabled?a._e():r("a-button",{on:{click:function(e){return a.handleOk(0)}}},[a._v(" 暂存 ")]),"问题上报"!==a.modalData.title||a.modalData.disabled?a._e():r("a-button",{attrs:{type:"primary"},on:{click:function(e){return a.handleOk(1)}}},[a._v(" 提交 ")]),"立案"===a.modalData.title?r("a-button",{on:{click:function(e){return a.handleOk(-1)}}},[a._v(" 无效上报 ")]):a._e(),"立案"===a.modalData.title?r("a-button",{attrs:{type:"primary"},on:{click:function(e){return a.handleOk(2)}}},[a._v(" 立案 ")]):a._e()],1),r("a-form",{attrs:{layout:a.formLayout,form:a.form}},[r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{label:"id","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["id",{}],expression:"['id', {}]"}],attrs:{disabled:a.modalData.disabled}})],1),r("a-form-item",{attrs:{label:"案件来源","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["casesource1",{rules:[{required:!0,message:"案件来源必填！"}]}],expression:"['casesource1', { rules: [{ required: true, message: '案件来源必填！' }] }]"}],staticStyle:{width:"125px"},on:{change:a.handleCaseSourceChange}},[r("a-select-option",{attrs:{value:"内部"}},[a._v("内部")]),r("a-select-option",{attrs:{value:"外部"}},[a._v("外部")])],1),r("a-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["casesource2",{rules:[{required:!0,message:"案件来源必填！"}]}],expression:"['casesource2', { rules: [{ required: true, message: '案件来源必填！' }] }]"}],staticStyle:{width:"140px","margin-left":"5px"}},a._l(a.caseSource,(function(e){return r("a-select-option",{key:e,attrs:{value:e}},[a._v(a._s(e))])})),1)],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"案件类型","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-tree-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["casetype3",{rules:[{required:!0,message:"案件类型必填！"}]}],expression:"['casetype3', { rules: [{ required: true, message: '案件类型必填！' }] }]"}],staticStyle:{width:"100%"},attrs:{disabled:a.modalData.disabled,"dropdown-style":{maxHeight:"400px",overflow:"auto"},"tree-data":a.caseTypeTreeData,"show-search":"",treeNodeFilterProp:"title",dropdownMatchSelectWidth:"",placeholder:"",treeDefaultExpandedKeys:[a.caseTypeTreeData[0].key]}})],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"所属网格","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-tree-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["gridCommunityId",{rules:[{required:!0,message:"所属网格必填！"}]}],expression:"['gridCommunityId', { rules: [{ required: true, message: '所属网格必填！' }] }]"}],staticStyle:{width:"100%"},attrs:{disabled:a.modalData.disabled,"dropdown-style":{maxHeight:"400px",overflow:"auto"},"tree-data":a.gridTreeData,"show-search":"",treeNodeFilterProp:"title",dropdownMatchSelectWidth:"",placeholder:"",treeDefaultExpandedKeys:[a.gridTreeData[0].key]},on:{select:a.handleGridSelect}})],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"责任部门","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-tree-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["managerDept",{}],expression:"['managerDept', {}]"}],staticStyle:{width:"100%"},attrs:{disabled:a.modalData.disabled,"dropdown-style":{maxHeight:"400px",overflow:"auto"},"tree-data":a.deptTreeData,"show-search":"",treeNodeFilterProp:"title",dropdownMatchSelectWidth:"",placeholder:"",treeDefaultExpandedKeys:[a.deptTreeData[0].key]}})],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"责任人","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-tree-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["manager",{}],expression:"['manager', {}]"}],staticStyle:{width:"100%"},attrs:{disabled:a.modalData.disabled,"dropdown-style":{maxHeight:"400px",overflow:"auto"},"tree-data":a.personTreeData,"show-search":"",treeNodeFilterProp:"title",dropdownMatchSelectWidth:"",placeholder:"",treeDefaultExpandedKeys:[a.personTreeData[0].key]}})],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"样本","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-tree-select",{directives:[{name:"decorator",rawName:"v-decorator",value:["sample3",{}],expression:"['sample3', {}]"}],staticStyle:{width:"100%"},attrs:{disabled:a.modalData.disabled,"dropdown-style":{maxHeight:"400px",overflow:"auto"},"tree-data":a.sampleTreeData,"show-search":"",treeNodeFilterProp:"title",dropdownMatchSelectWidth:"",placeholder:"",treeDefaultExpandedKeys:[a.sampleTreeData[0].key]}})],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"处置时限","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["limittimes",{}],expression:"['limittimes', {}]"}],attrs:{disabled:a.modalData.disabled}})],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"案件计数","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["casecount",{}],expression:"['casecount', {}]"}],attrs:{disabled:a.modalData.disabled}})],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"上报人","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["reportor",{}],expression:"['reportor', {}]"},{name:"show",rawName:"v-show",value:!1,expression:"false"}]}),r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["reportorName",{}],expression:"['reportorName', {}]"}],attrs:{disabled:!0}})],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"联系方式","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["contract",{}],expression:"['contract', {}]"}],attrs:{disabled:a.modalData.disabled}})],1),r("a-form-item",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{label:"标记","label-col":a.labelCol,"wrapper-col":a.wrapperCol}},[r("a-checkbox",{attrs:{checked:a.importantCaseChecked},on:{change:a.onChange1}},[a._v("重大案件")]),r("a-checkbox",{attrs:{checked:a.repeatCaseChecked},on:{change:a.onChange2}},[a._v("重复案件")])],1),r("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"案件描述","label-col":{span:3},"wrapper-col":{span:21}}},[r("a-textarea",{directives:[{name:"decorator",rawName:"v-decorator",value:["caseDesc",{rules:[{required:!0,message:"案件描述必填！"}]}],expression:"['caseDesc', { rules: [{ required: true, message: '案件描述必填！' }] }]"}],attrs:{disabled:a.modalData.disabled,"auto-size":{minRows:2,maxRows:6}}})],1),r("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"经度","label-col":{span:3},"wrapper-col":{span:21}}},[r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["lng",{}],expression:"['lng', {}]"}],staticStyle:{width:"110px"},attrs:{disabled:a.modalData.disabled}}),r("span",{staticStyle:{"margin-left":"10px"}},[a._v("纬度：")]),r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["lat",{}],expression:"['lat', {}]"}],staticStyle:{width:"110px"},attrs:{disabled:a.modalData.disabled}}),r("a-button",{staticStyle:{"margin-left":"10px"},on:{click:a.selectPosition}},[a._v("位置")]),r("a-modal",{staticClass:"mapModal",attrs:{title:"地图定位",width:1e3,visible:a.mapVisible,maskClosable:!1},on:{cancel:function(){e.mapVisible=!1},ok:a.handleMapOk}},[r("div",{staticClass:"map",staticStyle:{width:"100%",height:"600px"},attrs:{id:"container1"}}),r("div",{staticClass:"search"},[r("span",[a._v("请输入关键字：")]),a._v(" "),r("br"),r("a-input",{attrs:{id:"tipinput"}})],1),r("div",{staticClass:"position"},[r("span",[a._v("经度：")]),r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["lnglat.lng",{}],expression:"['lnglat.lng', {}]"}],staticStyle:{width:"140px"}}),r("div",{staticClass:"line"}),r("span",[a._v("纬度：")]),r("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["lnglat.lat",{}],expression:"['lnglat.lat', {}]"}],staticStyle:{width:"140px"}})],1)])],1),r("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"位置描述","label-col":{span:3},"wrapper-col":{span:21}}},[r("a-textarea",{directives:[{name:"decorator",rawName:"v-decorator",value:["locationDesc",{rules:[{required:!0,message:"案件描述必填！"}]}],expression:"['locationDesc', { rules: [{ required: true, message: '案件描述必填！' }] }]"}],attrs:{disabled:a.modalData.disabled,"auto-size":{minRows:2,maxRows:6}}})],1),a.showReport?r("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"上报附件","label-col":{span:3},"wrapper-col":{span:21}}},[r("a-upload",{attrs:{"file-list":a.reportFileList,disabled:!0}})],1):a._e(),a.showReport?r("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"上报图片","label-col":{span:3},"wrapper-col":{span:21}}},[r("a-upload",{attrs:{name:"avatar","list-type":"picture-card","file-list":a.reportImageList,disabled:!0},on:{preview:a.handlePreview}}),r("a-modal",{attrs:{visible:a.previewVisible,footer:null},on:{cancel:a.handlePreviewCancel}},[r("img",{staticStyle:{width:"100%"},attrs:{alt:"example",src:a.previewImage}})])],1):a._e(),r("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"附件上传","label-col":{span:3},"wrapper-col":{span:21}}},[r("a-upload",{attrs:{name:"file",action:"/api/upload",multiple:!0,"file-list":a.fileList,remove:a.handleRemove},on:{change:a.handleFileChange}},[r("a-button",[r("a-icon",{attrs:{type:"upload"}}),a._v("附件上传 ")],1)],1)],1),r("a-form-item",{staticStyle:{width:"735px"},attrs:{label:"图片上传","label-col":{span:3},"wrapper-col":{span:21}}},[r("a-upload",{attrs:{name:"avatar",action:"/api/upload-avatar",multiple:!0,"list-type":"picture-card","file-list":a.imageList,remove:a.handleRemove},on:{preview:a.handlePreview,change:a.handleImageChange}},[r("a-icon",{attrs:{type:"plus"}}),r("div",{staticClass:"ant-upload-text"},[a._v("Upload")])],1),r("a-modal",{attrs:{visible:a.previewVisible,footer:null},on:{cancel:a.handlePreviewCancel}},[r("img",{staticStyle:{width:"100%"},attrs:{alt:"example",src:a.previewImage}})])],1)],1)],2)},l=[],o=(t("fe59"),t("053b"),t("e18c"),t("e35a"),t("9cf3"),t("08ba"),t("b449"),t("5d83")),i=t("80a0"),s=t("81ef"),n=t("0fea"),c=t("47bc"),d=t("ef43"),p=t("d4f4"),u=t("1cfa"),m=t.n(u),h=(t("de52"),t("5880"));function v(e){return new Promise((function(a,t){var r=new FileReader;r.readAsDataURL(e),r.onload=function(){return a(r.result)},r.onerror=function(e){return t(e)}}))}var f=["环卫","执法","区12319转办","专项普查","区12345转办","巡查现场已解决","领导交办","区政府交办","信息采集监督员巡查","标准化现场督察","视频抓拍","网格化执法巡查","水务"],w=["微信平台","视频对接","市局转办","市12345转办","市12319转办","群众来访","新媒体举报"],b={props:["modalData"],data:function(){return{labelCol:{span:6},wrapperCol:{span:18},formLayout:"inline",confirmLoading:!1,caseSource:[],caseTypeTreeData:[],gridTreeData:[],deptTreeData:[],personTreeData:[],sampleTreeData:[],previewVisible:!1,previewImage:"",imageList:[],fileList:[],reportImageList:[],reportFileList:[],importantCaseChecked:void 0,repeatCaseChecked:void 0,mapVisible:!1,lnglat:null,showReport:!1}},computed:Object(i["a"])({},Object(h["mapGetters"])(["userInfo"])),watch:{modalData:function(e){var a=this;this.form.resetFields(),!0===e.visible&&this.$nextTick((function(){a.fileList=[],a.imageList=[],a.repeatCaseChecked=!1,a.importantCaseChecked=!1,a.showReport=1===a.modalData.record.status;var e=a.modalData.record.id,t=a.modalData.record.requestLogId1,r={};e?Object(p["b"])(m.a.stringify({id:e})).then((function(e){r=e.result.data[0],a.importantCaseChecked=0===a.modalData.record.importantCase,a.repeatCaseChecked=0===a.modalData.record.repeatCase;var t={lng:a.modalData.record.lng,lat:a.modalData.record.lat};a.modalData.record.lnglat=t,a.lnglat=t,a.handleCaseSourceChange(a.modalData.record.casesource1),a.form.setFieldsValue(Object(i["a"])({},r))})).catch((function(){})):a.form.setFieldsValue({reportor:a.userInfo.id,reportorName:a.userInfo.name,limittimes:24}),t&&Object(p["h"])(m.a.stringify({id:t})).then((function(e){var t=e.result.data[0].files;a.fileList=[],a.imageList=[],t&&t.forEach((function(e){var t={uid:e.id,name:e.fileName,status:"done",url:"/api"+e.url,thumbUrl:"/api"+e.thumbUrl,response:{result:e}};0===e.fileType?a.fileList.push(t):a.imageList.push(t)}))})).catch((function(){})),1===a.modalData.record.status&&Object(p["h"])(m.a.stringify({caseid:a.modalData.record.id,status:0})).then((function(e){var t=e.result.data[0].files;a.reportFileList=[],a.reportImageList=[],t&&t.forEach((function(e){var t={uid:e.id,name:e.fileName,status:"done",url:"/api"+e.url,thumbUrl:"/api"+e.thumbUrl,response:{result:e}};0===e.fileType?a.reportFileList.push(t):a.reportImageList.push(t)}))})).catch((function(){}))}))}},beforeCreate:function(){this.form=this.$form.createForm(this)},created:function(){this.getGridTree(),this.getSampleTree(),this.getSubCompanyTree(),this.getSubCompanyUserTree(),this.questionTypeTree()},methods:{dateFormat:function(e){var a=new Date(e),t=a.getFullYear(),r=a.getMonth()+1<10?"0"+(a.getMonth()+1):a.getMonth()+1,l=a.getDate()<10?"0"+a.getDate():a.getDate(),o=a.getHours()<10?"0"+a.getHours():a.getHours(),i=a.getMinutes()<10?"0"+a.getMinutes():a.getMinutes(),s=a.getSeconds()<10?"0"+a.getSeconds():a.getSeconds();return t+"-"+r+"-"+l+" "+o+":"+i+":"+s},selectPosition:function(){this.mapVisible=!0;var e=this;this.$nextTick((function(){var a={zoom:13,resizeEnable:!0};e.lnglat&&e.lnglat.lng&&e.lnglat.lat&&(a.center=[e.lnglat.lng,e.lnglat.lat]);var t,r=new AMap.Map("container1",a),l=new AMap.Pixel(-13,-30);r.on("click",(function(a){var o={lng:a.lnglat.getLng(),lat:a.lnglat.getLat()};e.lnglat=o,e.form.setFieldsValue({lnglat:o}),t&&(t.setMap(null),t=null),t=new AMap.Marker({icon:"/img/poi-marker-red.png",position:[a.lnglat.getLng(),a.lnglat.getLat()],offset:l}),t.setMap(r)})),e.lnglat&&e.lnglat.lng&&e.lnglat.lat&&(t=new AMap.Marker({icon:"/img/poi-marker-red.png",position:[e.lnglat.lng,e.lnglat.lat],offset:l}),t.setMap(r));var o={input:"tipinput"};AMap.plugin(["AMap.PlaceSearch","AMap.AutoComplete"],(function(){var e=new AMap.AutoComplete(o),a=new AMap.PlaceSearch({map:r});function t(e){a.setCity(e.poi.adcode),a.search(e.poi.name)}e.on("select",t)}))}))},handleMapOk:function(){this.mapVisible=!1,this.form.setFieldsValue(this.lnglat)},handleOk:function(e){var a=this;this.form.validateFields((function(t,r){if((1===e||2===e)&&!t||0===e||-1===e){r.status=e;var l=void 0===r.id?p["d"]:p["e"];r.importantCase=a.importantCaseChecked?0:1,r.repeatCase=a.repeatCaseChecked?0:1,l(r).then((function(t){var r=a.modalData.record.requestLogId1,l=[];a.fileList.forEach((function(e){null===r&&delete e.response.result.id,e.response&&l.push(e.response.result)})),a.imageList.forEach((function(e){null===r&&delete e.response.result.id,e.response&&l.push(e.response.result)}));var o={};r&&(o.id=r),o.caseid=t.result.id,o.status=void 0===a.modalData.record.status?0:a.modalData.record.status,o.result=e,o.files=l,Object(p["i"])(o).then((function(e){a.$emit("handleModalEvent",e),a.handleCancel()})).catch((function(){}))})).catch((function(){}))}}))},onChange1:function(e){this.importantCaseChecked=e.target.checked},onChange2:function(e){this.repeatCaseChecked=e.target.checked},handleGridSelect:function(e,a){var t=a.dataRef.manager,r=a.dataRef.managerDept;this.form.setFieldsValue({manager:t,managerDept:r})},handleCaseSourceChange:function(e){this.caseSource="内部"===e?f:w},handleCancel:function(){this.modalData.visible=!1},handlePreviewCancel:function(){this.previewVisible=!1},handlePreview:function(e){var a=this;return Object(o["a"])(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:if(e.url||e.preview){t.next=4;break}return t.next=3,v(e.originFileObj);case 3:e.preview=t.sent;case 4:a.previewImage=e.url||e.preview,a.previewVisible=!0;case 6:case"end":return t.stop()}}),t)})))()},handleImageChange:function(e){var a=e.fileList;this.imageList=a},handleFileChange:function(e){var a=e.fileList;this.fileList=a},handleRemove:function(e){var a=e.response.result.id;void 0!==a&&Object(p["g"])(m.a.stringify({id:a})).then((function(e){e.code})).catch((function(){}))},getSampleTree:function(){var e=this;Object(c["e"])().then((function(a){200===a.code&&(e.sampleTreeData=a.result)})).catch((function(){}))},getGridTree:function(){var e=this;Object(s["g"])().then((function(a){200===a.code&&(e.gridTreeData=a.result)})).catch((function(){}))},getSubCompanyTree:function(){var e=this;Object(n["l"])().then((function(a){200===a.code&&(e.deptTreeData=a.result,e.deptTreeData[0].disabled=!0)})).catch((function(){}))},getSubCompanyUserTree:function(){var e=this;Object(n["n"])().then((function(a){200===a.code&&(e.personTreeData=a.result)})).catch((function(){}))},questionTypeTree:function(){var e=this;Object(d["e"])().then((function(a){200===a.code&&(e.caseTypeTreeData=a.result,e.recursionCaseTypeTreeData(a.result))})).catch((function(){}))},recursionCaseTypeTreeData:function(e){var a=this;e.forEach((function(e){e.children&&e.children.length>0&&(e.disabled=!0,a.recursionCaseTypeTreeData(e.children))}))}}},g=b,C=(t("2535"),t("4023")),D=Object(C["a"])(g,r,l,!1,null,"13a63688",null);a["a"]=D.exports}}]);