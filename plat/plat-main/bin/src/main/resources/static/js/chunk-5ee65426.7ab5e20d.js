(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5ee65426"],{"06c3":function(e,t,n){},"1f27":function(e,t,n){"use strict";n.d(t,"i",(function(){return o})),n.d(t,"h",(function(){return u})),n.d(t,"g",(function(){return s})),n.d(t,"f",(function(){return c})),n.d(t,"e",(function(){return i})),n.d(t,"b",(function(){return d})),n.d(t,"c",(function(){return l})),n.d(t,"a",(function(){return f})),n.d(t,"d",(function(){return h}));var a=n("b775"),r={menuTree:"/menu/tree",menuList:"/menu/find",menuPost:"/menu/post",menuDelete:"/menu/delete",menuPut:"/menu/put",roleMenuPost:"/role_Menu/post",roleMenuList:"/role_Menu/find",userMenuPost:"/userMenu/post",userMenuList:"/userMenu/find"};function o(e){return Object(a["b"])({url:r.userMenuPost,method:"post",data:e})}function u(e){return Object(a["b"])({url:r.userMenuList,method:"post",data:e})}function s(e){return Object(a["b"])({url:r.roleMenuPost,method:"post",data:e})}function c(e){return Object(a["b"])({url:r.roleMenuList,method:"post",data:e})}function i(e){return Object(a["b"])({url:r.menuTree,method:"post",data:e})}function d(e){return Object(a["b"])({url:r.menuList,method:"post",data:e})}function l(e){return Object(a["b"])({url:r.menuPost,method:"post",data:e})}function f(e){return Object(a["b"])({url:r.menuDelete,method:"post",data:e})}function h(e){return Object(a["b"])({url:r.menuPut,method:"post",data:e})}},"5c55":function(e,t,n){},"5c62":function(e,t,n){"use strict";n.d(t,"a",(function(){return c}));var a=n("b49a");function r(e){if(Array.isArray(e))return Object(a["a"])(e)}n("f3dd"),n("0a51"),n("9b11"),n("98e0"),n("a133"),n("e18c"),n("96db"),n("af86");function o(e){if("undefined"!==typeof Symbol&&Symbol.iterator in Object(e))return Array.from(e)}var u=n("271f");function s(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}function c(e){return r(e)||o(e)||Object(u["a"])(e)||s()}},"5c8e":function(e,t,n){"use strict";var a=n("5c55"),r=n.n(a);r.a},"641c":function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("a-card",{attrs:{bordered:!1}},[n("a-row",{attrs:{gutter:24}},[n("a-col",{attrs:{span:4}},[n("a-tabs",{attrs:{"default-active-key":"1",animated:!1},on:{change:e.handleTabChange}},[n("a-tab-pane",{key:"1",attrs:{tab:"角色"}},[n("role-list",{staticClass:"list"})],1),n("a-tab-pane",{key:"2",attrs:{tab:"人员","force-render":""}},[n("user-tree",{staticClass:"list"})],1)],1)],1),n("a-col",{attrs:{span:20}},[n("menu-tree",{ref:"tree"})],1)],1)],1)},r=[],o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("a-button",{attrs:{type:"primary",icon:"save"},on:{click:e.save}},[e._v("保存")]),n("a-tree",{attrs:{checkable:"","expanded-keys":e.expandedKeys,"auto-expand-parent":e.autoExpandParent,"tree-data":e.treeData},on:{expand:e.onExpand,check:e.onCheck},model:{value:e.checkedKeys,callback:function(t){e.checkedKeys=t},expression:"checkedKeys"}})],1)},u=[],s=(n("b4fb"),n("fe59"),n("08ba"),n("5c62")),c=n("1f27"),i=n("1cfa"),d=n.n(i),l={created:function(){var e=this;this.loadMenuTree(),this.eventBus.$on("transferRoleNode",(function(t){e.node=t,e.type="role",e.loadSelectedMenu()})),this.eventBus.$on("transferUserNode",(function(t){e.node=t,e.node.id=t.key,e.type="menu",e.loadSelectedMenu()}))},data:function(){return{eventBus:this.$EventBus(this),expandedKeys:[],autoExpandParent:!0,checkedKeys:[],treeData:[],node:null,type:"",halfCheckedKeys:[]}},methods:{loadMenuTree:function(){var e=this;Object(c["e"])().then((function(t){200===t.code&&(e.treeData=t.result,e.expandedKeys.push(t.result[0].key))})).catch((function(){}))},loadSelectedMenu:function(){var e=this,t=null,n={};"role"===this.type?(t=c["f"],n.roleId=this.node.id):(t=c["h"],n.userId=this.node.id),t(d.a.stringify(n)).then((function(t){200===t.code&&(e.checkedKeys=t.result)})).catch((function(){}))},onExpand:function(e){console.log("onExpand",e),this.expandedKeys=e,this.autoExpandParent=!1},onCheck:function(e,t){this.halfCheckedKeys=t.halfCheckedKeys},save:function(){var e=this,t=[],n="",a=null;null!=this.node?("role"===this.type?(a=c["g"],n="roleId"):(a=c["i"],n="userId"),[].concat(Object(s["a"])(this.checkedKeys),Object(s["a"])(this.halfCheckedKeys)).forEach((function(a){var r={menuId:a};r[n]=e.node.id,t.push(r)})),a(t).then((function(t){200===t.code?e.$message.success("保存成功！"):e.$message.success("保存失败！")})).catch((function(){}))):this.$message.error("请先选中左侧节点！")}}},f=l,h=n("4023"),p=Object(h["a"])(f,o,u,!1,null,null,null),b=p.exports,m=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("a-input-search",{staticStyle:{"margin-bottom":"8px"},attrs:{placeholder:"请输入搜索内容..."},on:{change:e.filterData}}),n("a-list",{attrs:{"item-layout":"horizontal","data-source":e.data},scopedSlots:e._u([{key:"renderItem",fn:function(t){return n("a-list-item",{class:t.id===e.selectedId?"active":"",on:{click:function(n){return e.selectRole(t)}}},[e._v(" "+e._s(t.roleName)+" ")])}}])})],1)},y=[],v=(n("dbb3"),n("ecb4"),n("cc5e")),x={mounted:function(){this.loadData()},data:function(){return{eventBus:this.$EventBus(this),data:[],initData:[],selectedId:""}},methods:{selectRole:function(e){this.selectedId=e.id,this.eventBus.$emit("transferRoleNode",e)},filterData:function(e){var t=e.target.value;if(this.data=this.initData,""!==t){var n=this.data.filter((function(e){return e.roleName.indexOf(t)>-1}));this.data=n}},loadData:function(){var e=this;Object(v["b"])().then((function(t){200===t.code&&(e.data=t.result.data,e.initData=t.result.data)})).catch((function(){}))}}},k=x,j=(n("5c8e"),Object(h["a"])(k,m,y,!1,null,"6ecd2242",null)),E=j.exports,O=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("a-tree",{attrs:{"expanded-keys":e.expandedKeys,"auto-expand-parent":e.autoExpandParent,"tree-data":e.treeData},on:{select:e.onSelect,expand:e.onExpand}})],1)},K=[],M=n("0fea"),g={mounted:function(){this.loadUserTree()},data:function(){return{eventBus:this.$EventBus(this),expandedKeys:[],autoExpandParent:!0,treeData:[]}},methods:{loadUserTree:function(){var e=this;Object(M["n"])().then((function(t){200===t.code&&(e.expandedKeys.push(t.result[0].key),e.recursionUserTree(t.result),e.treeData=t.result)})).catch((function(){}))},onExpand:function(e){console.log("onExpand",e),this.expandedKeys=e,this.autoExpandParent=!1},onSelect:function(e,t){this.eventBus.$emit("transferUserNode",t.node.dataRef)},recursionUserTree:function(e){var t=this;e.forEach((function(e){"user"!==e.type&&(e.disabled=!0),e.children&&e.children.length>0&&t.recursionUserTree(e.children)}))}}},D=g,P=Object(h["a"])(D,O,K,!1,null,null,null),T=P.exports,$={components:{MenuTree:b,RoleList:E,UserTree:T},methods:{handleTabChange:function(){}}},_=$,I=(n("ae10"),Object(h["a"])(_,a,r,!1,null,"172ccbd2",null));t["default"]=I.exports},ae10:function(e,t,n){"use strict";var a=n("06c3"),r=n.n(a);r.a}}]);